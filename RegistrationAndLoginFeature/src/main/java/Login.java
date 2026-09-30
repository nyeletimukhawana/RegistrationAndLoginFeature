/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PC
 */
public class Login {
    
    private String storedUsername;
    private String storedPassword;
    private String storedCellNumber;
    
     public boolean checkUsername(String username) {
        if (username.length() <= 5 && username.contains("_")) {
            return true;
        } else {
            return false;
        }
    }
     
     public boolean checkPasswordComplexity(String password) {
         if (password.length() >= 8 && password.matches(".*[a-z].*") && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*") && password.matches(".*[^a-zA-Z0-9].*") ) {
             return true;
         } else {
             return false;
         }
     }
     
     public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        return cellPhoneNumber.matches("\\+27[0-9]{9}");
    }
         
     
     public String registerUser(String username, String password, String cellPhoneNumber ) {
         
         if (checkUsername(username) && checkPasswordComplexity(password)&& checkCellPhoneNumber(cellPhoneNumber)) {
             
         storedUsername = username;
         storedPassword = password;
         storedCellNumber = cellPhoneNumber;
         
             return "User is successfully registered";
         } else {
             return "User registration failed";
         }
     }
     
     public boolean loginUser(String username, String password) { 
         return username.equals(storedUsername)
                && password.equals(storedPassword);
     }
     
    public String returnLoginStatus(String username, String password) {
     
         if (loginUser(username, password)) {
            return "A successful login";
        } else {
            return "A failed login";
         }
    }
}


