package com.example.testing;
import java.util.HashMap; import java.util.Map; import java.util.Optional;
public class UserService {
    private Map<String, User> users; private Calculator calculator;
    public UserService(Calculator calculator) {
        this.users = new HashMap<>(); this.calculator = calculator;
    }
    // ... (methods as in previous details)
}
