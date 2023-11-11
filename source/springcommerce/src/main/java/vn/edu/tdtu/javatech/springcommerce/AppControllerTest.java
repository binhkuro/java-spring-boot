package vn.edu.tdtu.javatech.springcommerce;

import javax.annotation.Generated;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Generated(value = "org.junit-tools-1.1.0")
public class AppControllerTest {

	private AppController createTestSubject() {
		return new AppController();
	}

	@Test
	public void testViewHomePage() throws Exception {
		AppController testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.viewHomePage();
	}

	@Test
	public void testLoginPage() throws Exception {
		AppController testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.loginPage();
	}

	@Test
	public void testBackToLogin() throws Exception {
		AppController testSubject;
		Model model = null;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.backToLogin(model);
	}

	@Test
	public void testAdminPage() throws Exception {
		AppController testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.adminPage();
	}

	@Test
	public void testLoginPageAdmin() throws Exception {
		AppController testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.loginPageAdmin();
	}
}