/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author PC
 */
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class RegistrationAndLoginFeature {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        String firstName;
        String lastName;
        
        System.out.print("Enter first name: ");
        firstName = input.nextLine();
        
        System.out.print("Enter last name: ");
        lastName = input.nextLine();
        
        
        String username;
        String password;
        String cellPhoneNumber;
        
        System.out.print("Enter username:  ");
        username = input.nextLine();
        
        System.out.print("Enter password:  ");
        password = input.nextLine();
        
        System.out.print("Enter South African cell phone number:  ");
        cellPhoneNumber = input.nextLine();
        
        String result = "";
        String messagea = "";
        String messageb = "";
        String messagec = "";
        
        if (username.length() <= 5 && username.contains("_") ) {
            messagea = "Username Successfully Captured"; 
        }else {
            messagea = "Username is not correctly formatted;please ensure that your username contains an underscore and is no more than five characters in lenghth";
        }
        
         if (password.length() >= 8 && password.matches(".*[a-z].*") && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*") && password.matches(".*[^a-zA-Z0-9].*") ) {
            messageb = "Password Successfully Captured";
        }else {
            messageb = "Password is not correctly formatted,please ensure that the password contains at least eight characters,a letter,a number and a special character";
        }
        
        if (cellPhoneNumber.length() == 12 && cellPhoneNumber.matches("\\+27[0-9]{9}") ) {
            messagec = "Cell Phone Number Successfully Added";
        }else {
            messagec = "Cell phone number incorrectly formatted or does not contain international code";
        }
             
        System.out.println("-----REGISTRATION-----");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Username : " + username);
        System.out.println("Password : " + password);
        System.out.println("Cell Phone Number : " + cellPhoneNumber);
        System.out.println("Result : " + result);
        System.out.println("Message a : " + messagea);
        System.out.println("Message b : " + messageb);
        System.out.println("Message c : " + messagec);
        
        //Login to the account using the same username and password
        System.out.println("LOGIN TO ACCOUNT");
        String loginUsername;
        String loginPassword;
        String message;
        
        System.out.print("Enter username: ");
        loginUsername = input.nextLine();
        
        System.out.print("Enter password: ");
        loginPassword = input.nextLine();
        
        if (loginUsername.equals(username) && loginPassword.equals(password) ) {
            message = "Welcome " + firstName + " "  +  lastName + ", " + "it is great to see you again.";
    }else{ 
        message = "Username or password incorrect, please try again";
    }
        
        System.out.println("--LOGIN--");
        System.out.println("Login username : " + loginUsername);
        System.out.println("Login password : " + loginPassword);
        System.out.println("Message : " + message);
        
         //After a successful login,the user should be able to send messages
        if (loginUsername.equals(username) && loginPassword.equals(password) ) {
            System.out.println("Welcome to QuickChat");
            
            int userOptions = 0;
            int numberOfMessages =0;
            int messageCount =0;
            String messageA = "" ;
            String messageB = "";
            String messageC = "";
            
            //ARRAYS ARE NOW DECLARED BEFORE FOR LOOP
            String[] sentMessages = new String[100];
            String[] disregardedMessages = new String[100];
            String[] storedMessages = new String[100];
            String[] storedRecipients = new String[100];
            String[] messageHashes = new String[100];
            long[] messageIDs = new long[100];
            int sentMessagesCount = 0;
            int disregardedCount = 0;
            int storedCount = 0;

            //READ JSON FILE STORED INTO ARRAYS
            try {
                BufferedReader reader
                        = new BufferedReader(
                                new FileReader("StoredMessage.json"));

                String line;

                String loadedMessage = "";
                String loadedRecipient = "";
                String loadedHash = "";
                long loadedID = 0;

                while ((line = reader.readLine()) != null) {

                    line = line.trim();

                    if (line.startsWith("\"MessageID\"")) {

                        loadedID = Long.parseLong(
                                line.split(":")[1]
                                        .replace("\"", "")
                                        .replace(",", "")
                                        .trim());

                    }

                    if (line.startsWith("\"MessageHash\"")) {

                        loadedHash
                                = line.split(":")[1]
                                        .replace("\"", "")
                                        .replace(",", "")
                                        .trim();

                    }

                    if (line.startsWith("\"Recipient\"")) {

                        loadedRecipient
                                = line.split(":")[1]
                                        .replace("\"", "")
                                        .replace(",", "")
                                        .trim();

                    }

                    if (line.startsWith("\"Message\"")) {

                        loadedMessage
                                = line.substring(line.indexOf(":") + 1)
                                        .replace("\"", "")
                                        .replace(",", "")
                                        .trim();

                        storedMessages[storedCount] = loadedMessage;
                        storedRecipients[storedCount] = loadedRecipient;
                        messageHashes[storedCount] = loadedHash;
                        messageIDs[storedCount] = loadedID;

                        storedCount++;
                    }
                }

                reader.close();

            } catch (IOException e) {
                System.out.println("No stored messages found.");
            }

            while (userOptions != 3) {
            System.out.println("\n---MENU---");
            System.out.println("1. Send Messages");
            System.out.println("2. Recently Sent Messages");
            System.out.println("3. Quit");
            System.out.println("4. Stored Messages");
            
            System.out.print("Options :");
            userOptions = input.nextInt();
            input.nextLine();
            
        if (userOptions == 1) {
            messageA = "Send Messages";
             System.out.print("How many messages do you wish to send: ");
             numberOfMessages = input.nextInt();
             input.nextLine();
            
            for (int i = 0;i < numberOfMessages;i++){
        
        long messageID;
        String messageHash = "";
        String recipient;
        String messagee;

        messageID = (long)(Math.random() * 9000000000L) + 1000000000L;
        
         System.out.print(
        "Enter recipient number : ");
        recipient = input.nextLine();

        if(recipient.matches("\\+27[0-9]{9}")){

            System.out.print(
            "Enter message : ");
            messagee = input.nextLine();
            
               if (messagee.length() > 250) {
                   System.out.println("Please enter a message of less than 250 characters.");
        }
        
        String[] words;
        words = messagee.split(" ");
        String firstWord;
        String lastWord;
        firstWord = words[0];
        lastWord = words[words.length - 1];
        messageHash = (String.valueOf(messageID).substring(0, 2) + ":" + messageCount + ":" + firstWord + lastWord).toUpperCase();
        
        int messageOption;
        
            System.out.println("\n---MESSAGE OPTIONS---");
            System.out.println("1. Send Message");
            System.out.println("2. Disregard Message");
            System.out.println("3. Store Message To Send Later");
            
            System.out.print("Choose option : ");
            messageOption = input.nextInt();
            input.nextLine();
            
            if (messageOption == 1) {
            System.out.println("Message successfully sent");
            messageCount++;
            
            //ADDING SEND MESSAGE ARRAYS
            sentMessages[sentMessagesCount] = messagee;
            messageHashes[sentMessagesCount] = messageHash;
            messageIDs[sentMessagesCount] = messageID;
            sentMessagesCount++;
            
            System.out.println("Message ID : " + messageID);
            System.out.println("Message Hash : " + messageHash);
            System.out.println("Recipient : " + recipient);
            System.out.println("Message : " + messagee);
        
            }
            if (messageOption == 2 ) {
                //ADDING ARRAYS FOR DISREGARD MESSAGE
                disregardedMessages[disregardedCount] = messagee;
                disregardedCount++;
                
                System.out.println("Press 0 to delete the message");
                System.out.print("Press 0 to delete the message : ");
                int deleteMessage;
                
                deleteMessage = input.nextInt();
                input.nextLine();
                if(deleteMessage == 0){
                    System.out.println("Message deleted");
                }
            }
            if (messageOption == 3) {
                //ADDING ARRAYS FOR STORED MESSAGES
                storedMessages[storedCount] = messagee;
                storedRecipients[storedCount] = recipient;
                messageHashes[storedCount] = messageHash;
                messageIDs[storedCount] = messageID;
                storedCount++;
                
                try {
                    FileWriter file = new FileWriter("StoredMessage.json", true);
                    String jsonMessage = "{\n" + "\"MessageID\":\"" + messageID + "\",\n" + "\"MessageHash\":\"" + messageHash + "\",\n" + "\"Recipient\":\"" + recipient + "\",\n" + "\"Message\":\"" + messagee + "\"\n" + "}\n";
                    
                     file.write(jsonMessage);
                     file.close();
                     
                     System.out.println("Message successfully stored");
                     System.out.println("Message ID : " + messageID);
                     System.out.println("Message Hash : " + messageHash);
                     System.out.println("Recipient : " + recipient);
                     System.out.println("Message : " + messagee);
                     
                } catch (IOException e) {
                     System.out.println("Error storing message");
                        }
                    }
                }
            }
        }
        
        if (userOptions == 2) {
                messageB = "Recently Sent Messages";
                System.out.println("\n ---RECENTLY SENT MESSAGES---");  
                System.out.println("Sent " + sentMessagesCount + " " + "message(s)");
                    }
        
        if (userOptions == 3) {
                messageC = "Quit";
                System.out.println("Total messages sent: " + messageCount);
                break;
                    }
        
        if (userOptions == 4) {
            //STORED MESSAGE SUB-MENU
            String storedOptions = "";
            
            System.out.println("\n------STORED MESSAGES------");
            System.out.println("a. Display the sender and recipient of all stored messages");
            System.out.println("b. Display the longest stored message");
            System.out.println("c. Search for a message ID and display the corresponding recipient and message");
            System.out.println("d. Search for all messages stored for a particular recipient");
            System.out.println("e. delete the message using the message Hash");
            System.out.println("f. Display a report that lists the full details of all the stored messages");
            
            System.out.print("Choose Option: ");
            storedOptions = input.nextLine();
            
            if (storedOptions.equals("a")) {
                
                for (int k = 0; k < storedCount; k++) {
                    System.out.println("Sender    : " + username);
                    System.out.println("Recipient : " + storedRecipients[k]);
                }
                if (storedCount == 0) {
                    System.out.println("No Stored Messages!");
                }
                
            }
            
            if (storedOptions.equals("b")) {
                int longestIndex = 0;
                String longest = storedMessages[0];
                
                for (int k = 1; k < storedCount; k++) {
                    if (storedMessages[k].length() > longest.length()) {
                        longest = storedMessages[k];
                        longestIndex = k;
                    }
                }
                System.out.println("Longest Message Stored : " + longest);
                System.out.println("Recipient       : " + storedRecipients[longestIndex]);
                System.out.println("Message ID      : " + messageIDs[longestIndex]);
                System.out.println("Message Hash    : " + messageHashes[longestIndex]);
            }
                
                if (storedOptions.equals("c")) {
                    System.out.print("Enter Message ID to search: ");
                    String searchID = input.nextLine();
                    boolean found = false;
                    
                    for (int k = 0; k < storedCount; k++) {
                        if (String.valueOf(messageIDs[k]).equals(searchID)) {
                            System.out.println("Recipient : " + storedRecipients[k]);
                        System.out.println("Message   : " + storedMessages[k]);
                        found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("No Message Found With That Message ID");
                    }
                }
                if (storedOptions.equals("d")) {
                    System.out.print("Enter recipient number to search: ");
                    String searchRecipientNumber = input.nextLine();
                    boolean found = false;
                    
                    for (int k = 0; k < storedCount; k++) {
                        if (storedRecipients[k].equals(searchRecipientNumber)) {
                                System.out.println("Message ID   : " + messageIDs[k]);
                                System.out.println("Message Hash : " + messageHashes[k]);
                                System.out.println("Message      : " + storedMessages[k]); 
                                found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("No Messages found for That Recipient");
                    }
                }
                
                if (storedOptions.equals("e")) {
                    System.out.print("Enter Message Hash To Delete : ");
                    String messageHashToDelete = input.nextLine();
                    boolean found = false;
                    
                    for (int k = 0; k < storedCount; k++) {
                        if (messageHashes[k].equals(messageHashToDelete)) {
                            System.out.println("Message Deleted : " + storedMessages[k]);
                            for (int j = k; j < storedCount - 1; j++) {
                                storedMessages[j] = storedMessages[j + 1];
                                storedRecipients[j] = storedRecipients[j + 1];
                                messageHashes[j] = messageHashes[j + 1];
                                messageIDs[j] = messageIDs[j + 1];
                            }
                            storedCount--;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                            System.out.println("Message Hash Not Found!");
                        }
                    }
                
                if (storedOptions.equals("f")) {
                System.out.println("----MESSAGE REPORT----");
                System.out.println("Total Messages Stored = " + storedCount);
                
                for (int k = 0; k < storedCount; k++) {
                System.out.println("Recipient : " + storedRecipients[k]);
                System.out.println("Message ID   : " + messageIDs[k]);
                System.out.println("Message Hash : " + messageHashes[k]);
                System.out.println("Message      : " + storedMessages[k]);
                        }
                    }
                }
            }
        }
    }
}
