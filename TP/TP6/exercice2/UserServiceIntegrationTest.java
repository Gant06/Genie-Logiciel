package com.example.testing;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;
@DisplayName("Tests d'intégration et structurels UserService")
class UserServiceIntegrationTest {
    private UserService userService; private Calculator calculator;
    @BeforeEach void setUp() { calculator = new Calculator(); userService = new UserService(calculator); }
    // ... (test methods as in previous details)
}
