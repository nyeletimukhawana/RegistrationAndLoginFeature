/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PC
 */
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    
    private long messageID;
    private String recipient;
    private String messagee;
    private String messageHash;
    private static int messageCount = 0;
    
    
    public Message(long messageID,String recipient, String messagee){
    
        this.messageID = messageID;
        this.messagee = messagee;
        this.recipient = recipient;
    }
    
    public boolean checkMessageID(){
        return String.valueOf(messageID).length() <= 10;
    
    }
    
    public String checkRecipientCell() {
        if(recipient.matches("\\+27[0-9]{9}")){
            return "Cell number successfully captured";
        }
        else{
            return "Cell number incorrectly formatted or does not contain international code";
        }
    }
    
    public String createMessageHash() {
        String[] words;
        words = messagee.split(" ");
        String firstWord;
        String lastWord;
        firstWord = words[0];
        lastWord = words[words.length - 1];
        messageHash = (String.valueOf(messageID).substring(0, 2) + ":" + messageCount + ":" + firstWord + lastWord).toUpperCase();
        
        return messageHash;
    
    }
    
    public String checkMessageLength() {
    if (messagee.length() > 250) {
        int excess;
        excess = messagee.length() - 250;
        return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
    } else {
        return "Message ready to send.";
    }
}
    public String sentMessage(int messageOption) {
        if(messageOption == 1){
            messageCount++;
            return "Message successfully sent";
        }

        else if(messageOption == 2){
            return "Message disregarded";
        }

        else if(messageOption == 3){
            storeMessage();
            return "Message successfully stored";
        }

        return "Invalid option";
}
    
    public String printMessages() {
        return
        "Message ID : " + messageID +
        "\nMessage Hash : " + messageHash +
        "\nRecipient : " + recipient +
        "\nMessage : " + messagee;
    }
    
    public int returnTotalMessages() {
        return messageCount;
    }
    
    public void storeMessage() {
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
    
    //ADDING STATIC METHODS 
    public static int findLongestMessageIndex(String[] messages, int count) {
        int longestIndex = 0;
        for (int k = 1; k < count; k++) {
            if (messages[k].length() > messages[longestIndex].length()) {
                longestIndex = k;
            }
        }
        return longestIndex;
    }
    
    public static String searchByMessageID(long[] messageIDs, String[] messages, int count, String searchID) {
        for (int k = 0; k < count; k++) {
            if (String.valueOf(messageIDs[k]).equals(searchID)) {
                return messages[k];
            }
        }
        return null;
    }
    
    public static String searchByRecipient(String[] recipients, String[] messages, int count, String searchRecipient) {
        String result = "";
        for (int k = 0; k < count; k++) {
            if (recipients[k].equals(searchRecipient)) {
                result += messages[k] + " ";
            }
        }
        return result.trim();
    }
    
    public static boolean deleteByHash(String[] messages, String[] recipients, String[] hashes, long[] ids, int[] count, String hashToDelete) {
        for (int k = 0; k < count[0]; k++) {
            if (hashes[k].equals(hashToDelete)) {
                for (int j = k; j < count[0] - 1; j++) {
                    messages[j] = messages[j + 1];
                    recipients[j] = recipients[j + 1];
                    hashes[j] = hashes[j + 1];
                    ids[j] = ids[j + 1];
                }
                count[0]--;
                return true;
            }
        }
        return false;
    }
}
