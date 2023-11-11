package vn.edu.tdtu.javatech.springcommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired private ProductRepository repo;
    @Autowired private UserRepository repos;
    @Autowired private UserRepository2 repo2;

    public List<Product> listProduct() {
        return (List<Product>) repo.findAll();
    }
    public void saveProduct(Product p) {
        repo.save(p);
    }
    
    public Product getProduct(Integer id) {
        return repo.findById(id).get();
    }
     
    public void deleteProduct(Integer id) {
        repo.deleteById(id);
    }
    
    public List<Product> findByOxford() {
        return (List<Product>) repo.findByOxford();
    }
    
    public List<Product> findByDerby() {
        return (List<Product>) repo.findByDerby();
    }
    
    public List<Product> findByLoafer() {
        return (List<Product>) repo.findByLoafer();
    }
    
    public List<Product> findByBoots() {
        return (List<Product>) repo.findByBoots();
    }
    
    public List<Product> findBySneaker() {
        return (List<Product>) repo.findBySneaker();
    }
    
    public List<Product> findByMonkstrap() {
        return (List<Product>) repo.findByMonkstrap();
    }
    
    public List<Product> findByWallet() {
        return (List<Product>) repo.findByWallet();
    }
    
    public List<Product> findByHolder() {
        return (List<Product>) repo.findByHolder();
    }
    
    public List<Product> findByBag() {
        return (List<Product>) repo.findByBag();
    }
    
    public List<Product> findByBelt() {
        return (List<Product>) repo.findByBelt();
    }
    
    public List<Product> findBySock() {
        return (List<Product>) repo.findBySock();
    }
    
    public List<Product> findByNewGen() {
        return (List<Product>) repo.findByNewGen();
    }
    
    public List<Product> findByClassic() {
        return (List<Product>) repo.findByClassic();
    }
    
    public List<Product> findByBrogues() {
        return (List<Product>) repo.findByBrogues();
    }
    
    public List<Product> findByJames() {
        return (List<Product>) repo.findByJames();
    }
    
    public List<Product> findByCare() {
        return (List<Product>) repo.findByCare();
    }
    
    public List<Product> findByGift() {
        return (List<Product>) repo.findByGift();
    }
    
    public List<Product> getL(Integer id)
    {
    	return (List<Product>) repo.findAddById(id);
    }

    
    public Product get(Integer id) throws UserNotFoundException {
        Optional<Product> idP =  repo.findById(id);
        if(idP.isPresent()) {
            return idP.get();
        }
        throw new UserNotFoundException("Can't find the user with id: " + id);
    }

    public Optional<Product> findById(Integer id) {
        return repo.findById(id);
    }

    public List<User> listUsers() {
        return (List<User>) repos.findAllUsers();
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo2.countById(id);
        if(count == null || count == 0) {
            throw new UserNotFoundException("Can't find the user with id: " + id);
        }
        repo2.deleteById(id);
    }

    public User CheckAdmin(String email, String password) {
        return repo2.findAdmin(email,password);
    }

    public Product FindIdByName(String name) {
        return repo.findIdByName(name);
    }

    public Integer FindIdUs(String name) {
        return repo.findIdUs(name);
    }
}