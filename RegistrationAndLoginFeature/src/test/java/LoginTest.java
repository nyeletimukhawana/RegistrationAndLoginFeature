/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author PC
 */

public class LoginTest {
    
    public LoginTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of checkUsername method, of class Login.
     */
    @Test
    public void testCheckUsername() {
        System.out.println("checkUsername");
        Login instance = new Login();

        // Username correctly formatted: contains underscore and <= 5 chars
        assertTrue(instance.checkUsername("kyl_1"));

        // Username incorrectly formatted: no underscore and too long
        assertFalse(instance.checkUsername("kyle!!!!!!!"));
    }

    /**
     * Test of checkPasswordComplexity method, of class Login.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        Login instance = new Login();

        // Password meets complexity requirements
        assertTrue(instance.checkPasswordComplexity("Ch&&sec@ke99!"));

        // Password does not meet complexity requirements
        assertFalse(instance.checkPasswordComplexity("password"));
    }

    /**
     * Test of checkCellPhoneNumber method, of class Login.
     */
    @Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        Login instance = new Login();

        // Cell phone number correctly formatted
        assertTrue(instance.checkCellPhoneNumber("+27838968976"));

        // Cell phone number incorrectly formatted (no international code)
        assertFalse(instance.checkCellPhoneNumber("08966553"));
    }

    /**
     * Test of registerUser method, of class Login.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        Login instance = new Login();

        // All fields valid - registration should succeed
        String expResult = "User is successfully registered";
        String result = instance.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(expResult, result);

        // Invalid username - registration should fail
        String expResult2 = "User registration failed";
        String result2 = instance.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(expResult2, result2);
    }

    /**
     * Test of loginUser method, of class Login.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
       Login instance = new Login();
        instance.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");

        // Login successful - correct credentials
        assertTrue(instance.loginUser("kyl_1", "Ch&&sec@ke99!"));

        // Login failed - incorrect credentials
        assertFalse(instance.loginUser("wrong_u", "WrongPass1!"));
    }

    /**
     * Test of returnLoginStatus method, of class Login.
     */
    @Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");
       Login instance = new Login();
        instance.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");

        // Successful login status
        String expResult = "A successful login";
        String result = instance.returnLoginStatus("kyl_1", "Ch&&sec@ke99!");
        assertEquals(expResult, result);

        // Failed login status
        String expResult2 = "A failed login";
        String result2 = instance.returnLoginStatus("wrong_u", "WrongPass1!");
        assertEquals(expResult2, result2);
    }

}
