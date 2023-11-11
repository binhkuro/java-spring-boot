package vn.edu.tdtu.javatech.springcommerce;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String message) {
        super(message);
    }
}
