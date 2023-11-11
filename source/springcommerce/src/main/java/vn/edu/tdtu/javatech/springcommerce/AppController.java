package vn.edu.tdtu.javatech.springcommerce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CartService cartservice;
	
	@Autowired 
	private ProductService productService;

	@Autowired 
	private ContactRepository contactRepo;

	@Autowired 
	private OrderRepository orderRepo;
	
	@GetMapping("")
	public String viewHomePage() {
		return "redirect:/shophome";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		
		return "register_success";
	}


	@GetMapping("/cart/null")
	public String backToLogin(Model model) {
		return "redirect:/login";
	}
	@GetMapping("/cart")
	public String openCart(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    model.addAttribute("usName", currentUserName);

		    // Get the ID to avoid errors when checking out on the cart
			Product product = productService.FindIdByName(currentUserName);
			if (product != null) {
				System.out.println(product.getId());
				model.addAttribute("id", product.getId());
			}

			// Retrieve the cart list based on the login user's ID
		    User user = userRepo.findByEmail(currentUserName);
		    List<Cart> listCart = cartservice.listCart(user.getId());
			model.addAttribute("listCart",listCart);
		    return "cart";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/cart/{id}")
	public String showCartList(Model model, @PathVariable int id) {
		// Check if the user has already logged in
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
		    model.addAttribute("usName", currentUserName);

		    // Retrieve product information when the "add" button is clicked.
		    List<Product> listProduct = productService.getL(id);
		    Product product = listProduct.get(0);

		    // Retrieve the name of the currently logged-in user.
		    User user = userRepo.findByEmail(currentUserName);

		    // Create variables to add to the cart.
		    Integer userIdAdd = user.getId();
		    Integer productIdAdd = product.getId();
		    String imageAdd = product.getImage();
		    String nameAdd = product.getName();
		    Integer quantityAdd = 1;
		    Double priceAdd = product.getPrice();

			if(cartservice.checkTrung(userIdAdd, productIdAdd ) != null) {
				Cart updateCart = cartservice.checkTrung(userIdAdd, productIdAdd );
				updateCart.setQuantity(updateCart.getQuantity() + 1);
				cartservice.save(updateCart);
			} else {
				Cart newCart = new Cart(userIdAdd, productIdAdd, imageAdd, nameAdd, quantityAdd, priceAdd);
				cartservice.save(newCart);
			}

		    List<Cart> oldListCart = cartservice.listCart(user.getId());
		    // Insert the newCart into the database.

		    List<Cart> listCart = cartservice.listCart(user.getId());

			model.addAttribute("id",userIdAdd);
			model.addAttribute("listCart",listCart);
			return "cart";
		}
		return "redirect:/login";
	}

	@GetMapping("/cart/cartDetail")
	public String cartDetail(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		User user = userRepo.findByEmail(currentUserName);
	    List<Cart> listCart = cartservice.listCart(user.getId());
		double result = 0.0;
		if(listCart.size() != 0) {
			for (int i = 0; i < listCart.size(); i++) {
				int num = listCart.get(i).getQuantity();
				Double price = listCart.get(i).getPrice();
				result += price * num;
			}
		}
		String kq = String.valueOf(result);
		switch(kq.length()) {
			case 6:
				kq = kq.substring(0, 1) + "." + kq.substring(1, kq.length());
				break;
			case 7:
				kq = kq.substring(0, 2) + "." + kq.substring(2, kq.length());
				break;
			case 8:
				kq = kq.substring(0, 3) + "." + kq.substring(3, kq.length());
				break;
			case 9:
				kq = kq.substring(0, 1) + "." + kq.substring(1, 4) + "." + kq.substring(4, kq.length());
				break;
			default:
				kq = kq;
		}
		model.addAttribute("total",kq);
		model.addAttribute("listCart",listCart);
		return "cart_detail";
	}
	@PostMapping("/sendCart")
	public String SendCart(String tongtien, String sdt, String location, Integer id) {
		Order order = new Order(id,sdt, location,tongtien,"Chờ xác nhận" );
		orderRepo.save(order);
		return "send_donhang";
	}
	
	//Delete Selected User
	@RequestMapping("/cart/delete/{id}")
	public String deleteCartElement(@PathVariable(name = "id") int id) {
	    cartservice.delete(id);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
//			model.addAttribute("usName", currentUserName);
		}
	    return "redirect:/cart";
	}

	@GetMapping("/about-us")
	public String aboutUs(Model model) {
		// Check if the user has already logged in.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// Retrieve information of the current logged-in user through the CustomUserDetails class.
		    CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();

		    // Retrieve the ID to avoid errors when checking out on the cart.
			int idU = productService.FindIdUs(currentUserName);
			if (idU != 0) {
				model.addAttribute("id", idU);
			}

			// Pass the logged-in user's name to the View using the userName variable.

		    model.addAttribute("usName", currentUserName);
		    model.addAttribute("loginOk", "Ok");

		}
		// If there is no login, it defaults to Guest view.
		else model.addAttribute("loginNotOk", "Not Ok");
		return "about_us";
	}

	@GetMapping("/contact")
	public String Contact(Model model) {
		// Check if the user has already logged in.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// Retrieve information of the current logged-in user through the CustomUserDetails class.
		    CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();

		    // Retrieve the ID to avoid errors when checking out on the cart.
			int idU = productService.FindIdUs(currentUserName);
			if (idU != 0) {
				model.addAttribute("id", idU);
			}

			// Pass the logged-in user's name to the View using the userName variable.

		    model.addAttribute("usName", currentUserName);
		    model.addAttribute("loginOk", "Ok");

		}
		// If there is no login, it defaults to Guest view.
		else model.addAttribute("loginNotOk", "Not Ok");
		return "contact";
	}
	@PostMapping("/contact/send_contact")
	public String SendSuccess(String name, String email, String subject, String message) {
		Contact contact = new Contact(name, email, subject, message );
		contactRepo.save(contact);
		return "send_contact_success";
	}

	
	@GetMapping(value = {"/shophome", "/", "/products"})
	public String showProductList(Model model) {
		// Retrieve the list of products and pass the data to the View using the listProduct variable.
		List<Product> listProduct = productService.listProduct();
		model.addAttribute("listProduct", listProduct);
		
		// Check if the user has already logged in.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// Retrieve information of the current logged-in user through the CustomUserDetails class.
		    CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();

		    // Retrieve the ID to avoid errors when checking out on the cart.
			int idU = productService.FindIdUs(currentUserName);
			if (idU != 0) {
				model.addAttribute("id", idU);
			}

		    // Pass the logged-in user's name to the View using the userName variable.

		    model.addAttribute("usName", currentUserName);
		    model.addAttribute("loginOk", "Ok");

		}
		// If there is no login, it defaults to Guest view.
		else model.addAttribute("loginNotOk", "Not Ok");
		return "shophome";
	}

	@GetMapping("/products/oxford")
	public String getAllOxfords(Model model) {
		List<Product> listProduct = productService.findByOxford();
		model.addAttribute("listProduct",listProduct);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();
		    model.addAttribute("usName", currentUserName);
		}
		else model.addAttribute("usName", "Guest");
		return "shophome";
	}
	
	@GetMapping("/products/derby")
	public String getAllDerbys(Model model) {
		List<Product> listProduct = productService.findByDerby();
		model.addAttribute("listProduct",listProduct);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();
		    model.addAttribute("usName", currentUserName);
		}
		else model.addAttribute("usName", "Guest");
		return "shophome";
	}
	
	@GetMapping("/products/loafer")
	public String getAllLoafers(Model model) {
		List<Product> listProduct = productService.findByLoafer();
		model.addAttribute("listProduct",listProduct);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();
		    model.addAttribute("usName", currentUserName);
		}
		else model.addAttribute("usName", "Guest");
		return "shophome";
	}
	
	@GetMapping("/products/boots")
	public String getAllBoots(Model model) {
		List<Product> listProduct = productService.findByBoots();
		model.addAttribute("listProduct",listProduct);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();
		    model.addAttribute("usName", currentUserName);
		}
		else model.addAttribute("usName", "Guest");
		return "shophome";
	}

	@GetMapping("/products/sneaker")
	public String getAllSneakers(Model model) {
		List<Product> listProduct = productService.findBySneaker();
		model.addAttribute("listProduct",listProduct);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();
		    model.addAttribute("usName", currentUserName);
		}
		else model.addAttribute("usName", "Guest");
		return "shophome";
	}
	
	@GetMapping("/products/monkstrap")
	public String getAllMonkstraps(Model model) {
		List<Product> listProduct = productService.findByMonkstrap();
		model.addAttribute("listProduct",listProduct);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();
		    model.addAttribute("usName", currentUserName);
		}
		else model.addAttribute("usName", "Guest");
		return "shophome";
	}
	
	@GetMapping("/products/wallets")
	public String getAllWallets(Model model) {
		List<Product> listProduct = productService.findByWallet();
		model.addAttribute("listProduct",listProduct);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();
		    model.addAttribute("usName", currentUserName);
		}
		else model.addAttribute("usName", "Guest");
		return "shophome";
	}
	
	@GetMapping("/products/holders")
	public String getAllHolders(Model model) {
		List<Product> listProduct = productService.findByHolder();
		model.addAttribute("listProduct",listProduct);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();
		    model.addAttribute("usName", currentUserName);
		}
		else model.addAttribute("usName", "Guest");
		return "shophome";
	}
	
	@GetMapping("/products/bags")
	public String getAllBags(Model model) {
		List<Product> listProduct = productService.findByBag();
		model.addAttribute("listProduct",listProduct);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();
		    model.addAttribute("usName", currentUserName);
		}
		else model.addAttribute("usName", "Guest");
		return "shophome";
	}
	
	@GetMapping("/products/socks")
	public String getAllSocks(Model model) {
		List<Product> listProduct = productService.findBySock();
		model.addAttribute("listProduct",listProduct);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();
		    model.addAttribute("usName", currentUserName);
		}
		else model.addAttribute("usName", "Guest");
		return "shophome";
	}
	
	@GetMapping("/products/belts")
	public String getAllBelts(Model model) {
		List<Product> listProduct = productService.findByBelt();
		model.addAttribute("listProduct",listProduct);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();
		    model.addAttribute("usName", currentUserName);
		}
		else model.addAttribute("usName", "Guest");
		return "shophome";
	}
	
	@GetMapping("/products/new-gen")
	public String getAllNewGen(Model model) {
		List<Product> listProduct = productService.findByNewGen();
		model.addAttribute("listProduct",listProduct);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();
		    model.addAttribute("usName", currentUserName);
		}
		else model.addAttribute("usName", "Guest");
		return "shophome";
	}
	
	@GetMapping("/products/classic")
	public String getAllClassic(Model model) {
		List<Product> listProduct = productService.findByClassic();
		model.addAttribute("listProduct",listProduct);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();
		    model.addAttribute("usName", currentUserName);
		}
		else model.addAttribute("usName", "Guest");
		return "shophome";
	}
	
	@GetMapping("/products/brogues")
	public String getAllBrogues(Model model) {
		List<Product> listProduct = productService.findByBrogues();
		model.addAttribute("listProduct",listProduct);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();
		    model.addAttribute("usName", currentUserName);
		}
		else model.addAttribute("usName", "Guest");
		return "shophome";
	}
	
	@GetMapping("/products/james")
	public String getAllJames(Model model) {
		List<Product> listProduct = productService.findByJames();
		model.addAttribute("listProduct",listProduct);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();
		    model.addAttribute("usName", currentUserName);
		}
		else model.addAttribute("usName", "Guest");
		return "shophome";
	}
	
	@GetMapping("/products/care-products")
	public String getAllCares(Model model) {
		List<Product> listProduct = productService.findByCare();
		model.addAttribute("listProduct",listProduct);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();
		    model.addAttribute("usName", currentUserName);
		}
		else model.addAttribute("usName", "Guest");
		return "shophome";
	}
	
	@GetMapping("/products/gift-cards")
	public String getAllGifts(Model model) {
		List<Product> listProduct = productService.findByGift();
		model.addAttribute("listProduct",listProduct);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
		    String currentUserName = currentUser.getFullName();
		    model.addAttribute("usName", currentUserName);
		}
		else model.addAttribute("usName", "Guest");
		return "shophome";
	}
	
	@GetMapping("products/{id}")
	public String findByIdProduct(@PathVariable("id") Integer id, Model model) throws UserNotFoundException {
		Product product = productService.get(id);
		model.addAttribute("product", product);
		return "product_info";
	}

	@GetMapping("/shophome/{id}")
	public String showInfoProduct(@PathVariable("id") Integer id, Model model, RedirectAttributes re) {
		try {
			Product product = productService.get(id);
			model.addAttribute("product", product);
			model.addAttribute("id",id );
			return "redirect:/products/{id}";
		} catch (UserNotFoundException e) {
			return "redirect:/shophome";
		}
	}

	@GetMapping("/admin")
	public String adminPage() {
		return "admin_home";
	}

	@GetMapping("/admin/manageUser")
	public String adminPageManageUser(Model model) {
		List<User> listUsers = productService.listUsers();
		model.addAttribute("listUsers",listUsers);
		return "manageUser";
	}

	@GetMapping("/admin/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes re) {
		try {
			re.addFlashAttribute("message","The user " + id + " has been deleted.");
			productService.delete(id);
		} catch (UserNotFoundException e) {
			re.addFlashAttribute("message",e.getMessage());
		}
		return "redirect:/admin/manageUser";
	}
	
	//Product Management
	@GetMapping("/admin/manageProduct")
	public String adminPageManageProduct(Model model) {
		List<Product> listProduct = productService.listProduct();
		model.addAttribute("listProduct",listProduct);
		return "manageProduct";
	}
	
	//Add Product
	@RequestMapping("/admin/manageProduct/newProduct")
	public String addProductPage(Model model) {
	    Product product = new Product();
	    model.addAttribute("product", product);
	    return "new_product";
	}
	//Edit Selected Product
	@RequestMapping("/admin/manageProduct/editProduct/{id}")
	public ModelAndView editProductPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("edit_product");
	    Product product = productService.getProduct(id);
	    mav.addObject("product", product);
	    return mav;
	}
	
	//Delete Selected Product
	@RequestMapping("/admin/manageProduct/deleteProduct/{id}")
	public String deleteSelectedProduct(@PathVariable(name = "id") int id, RedirectAttributes re) {
		productService.deleteProduct(id);
		re.addFlashAttribute("message","The product " + id + " has been deleted.");
	    return "redirect:/admin/manageProduct";
	}
	
	//Save Change Product
	@RequestMapping(value = "/admin/manageProduct/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
	    productService.saveProduct(product);     
	    return "redirect:/admin/manageProduct";
	}

	//Admin Login
	@GetMapping("/admin/login")
	public String loginPageAdmin() {
		return "admin_login";
	}

	@PostMapping("/admin/login")
	public String checkAdmin(String email, String password) {
		if (email.equals("admin@gmail.com") && !email.trim().isEmpty() && !password.trim().isEmpty()) {
			User user = productService.CheckAdmin(email, password);
			if (user != null) {
				return "admin_home";
			}
		}
		return "admin_login";
	}
	
	@GetMapping("/admin/userResponse")
	public String UserResponse(Model model) {
		List<Contact> listContact = (List<Contact>) contactRepo.findAll();
		model.addAttribute("listContact",listContact);
		return "manageResponse";
	}

	@GetMapping("/admin/manage-order")
	public String ManageOrders(Model model) {
		List<Order> listOrder = (List<Order>) orderRepo.findAll();
		model.addAttribute("listOrder",listOrder);
		return "manageOrder";
	}

	@GetMapping("/admin/manageOrder/delete/{id}")
	public String deleteOrder(@PathVariable("id") Integer id, RedirectAttributes re) {
		if(id != 0) {
			re.addFlashAttribute("message","The order " + id + " has been deleted.");
			orderRepo.deleteById(id);
		}
		return "redirect:/admin/manage-order";
	}

	@GetMapping("/admin/manageOrder/accept/{id}")
	public String acceptOrder(@PathVariable("id") Integer id, RedirectAttributes re) {
		if(id != 0) {
			Order order = orderRepo.updateStatus(id);
			order.setStatus("Đã xác nhận");
			orderRepo.save(order);
			re.addFlashAttribute("message", "The order " + id + " has been accepted.");
		}
		return "redirect:/admin/manage-order";
	}

	@GetMapping("/admin/manageOrder/detail/{id}/{price}")
	public String detailOrder(@PathVariable("id") Integer id,@PathVariable("price") String price, Model model) {
//		System.out.println(id);
		if(id != 0) {
			List<Cart> listDetail = cartservice.detailOrder(id);
//			System.out.println(listDetail.size());
			model.addAttribute("listDetail", listDetail);
			model.addAttribute("total", price);
		}
		return "detailOrder";
	}

}
