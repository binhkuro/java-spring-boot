package vn.edu.tdtu.javatech.springcommerce;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        int statusCode = getErrorCode(request);
        String errorMessage = getErrorMessage(statusCode);
        model.addAttribute("statusCode", statusCode);
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        Object errorCode = httpRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        return errorCode == null ? 0 : (Integer) errorCode;
    }

    private String getErrorMessage(int errorCode) {
        switch (errorCode) {
            case 400:
                return "Bad Request";
            case 401:
                return "Unauthorized";
            case 403:
                return "Forbidden";
            case 404:
                return "Page Not Found";
            case 500:
                return "Internal Server Error";
            default:
                return "Unknown Error";
        }
    }

    public String getErrorPath() {
        return "/error";
    }
}