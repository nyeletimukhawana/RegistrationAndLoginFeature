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
public class MessageTest {
    
    public MessageTest() {
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
    
    @Test
    public void testCheckMessageID() {
        Message instance = new Message(1234567890L, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        boolean result = instance.checkMessageID();
        assertEquals(true, result);
    }
    
    @Test
    public void testCheckRecipientCellSuccess() {
        Message instance = new Message(1234567890L, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String result = instance.checkRecipientCell();
        assertEquals("Cell number successfully captured", result);
    }
    
    @Test
    public void testCheckRecipientCellFailure() {
        Message instance = new Message(1234567890L, "08575975889", "Hi Keegan, did you receive the payment?");
        String result = instance.checkRecipientCell();
        assertEquals("Cell number incorrectly formatted or does not contain international code", result);
    }
    
    @Test
    public void testCreateMessageHash() {
        Message instance = new Message(1234567890L, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String result = instance.createMessageHash();
        assertEquals("12:2:HITONIGHT?", result);
    }
    
    @Test
    public void testSentMessageSend() {
        Message instance = new Message(1234567890L, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String result = instance.sentMessage(1);
        assertEquals("Message successfully sent", result);
    }
    
    @Test
    public void testSentMessageDisregard() {
        Message instance = new Message(1234567890L, "08575975889", "Hi Keegan, did you receive the payment?");
        String result = instance.sentMessage(2);
        assertEquals("Message disregarded", result);
    }
    
    @Test
    public void testSentMessageStore() {
        Message instance = new Message(1234567890L, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        instance.createMessageHash(); 
        String result = instance.sentMessage(3);
        assertEquals("Message successfully stored", result);
    }
    
    @Test
    public void testMessageLengthSuccess() {
        Message instance = new Message(1234567890L, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String result = instance.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }
    
    @Test
    public void testMessageLengthFailure() {
        String longMessage = "a".repeat(251);
        Message instance = new Message(1234567890L, "+27718693002", longMessage);
        String result = instance.checkMessageLength();
        assertEquals("Message exceeds 250 characters by 1; please reduce the size.", result);
    }
    
    @Test
    public void testReturnTotalMessages() {
        Message instance = new Message(1234567890L, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        instance.sentMessage(1);
        int result = instance.returnTotalMessages();
        assertEquals(1, result);
    }
    
    @Test
    public void testPrintMessages() {
        Message instance = new Message(1234567890L, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        instance.createMessageHash(); 
        instance.createMessageHash();
        String result = instance.printMessages();
        assertNotNull(result);
    }
    
    @Test
    public void testFindLongestMessageIndex() {
        String[] messages = {
            "Did you get the cake?",
            "Where are you? You are late! I have asked you to be on time.",
            "Yohoooo, I am at your gate.",
            "It is dinner time !"
        };
        int count = 4;
        
        int result = Message.findLongestMessageIndex(messages, count);
        assertEquals(1, result);
        assertEquals("Where are you? You are late! I have asked you to be on time.", messages[result]);
    }

    @Test
    public void testSearchByMessageID() {
        long[] messageIDs = {1234567890L, 9876543210L, 1122334455L};
        String[] messages = {
            "It is dinner time !",
            "Where are you? You are late!",
            "Ok, I am leaving without you."
        };
        int count = 3;
        
        String result = Message.searchByMessageID(messageIDs, messages, count, "1234567890");
        
        assertEquals("It is dinner time !", result);
    }

    @Test
    public void testSearchByMessageIDNotFound() {
        long[] messageIDs = {1234567890L, 9876543210L};
        String[] messages = {"It is dinner time !", "Ok, I am leaving without you."};
        int count = 2;
        
        String result = Message.searchByMessageID(messageIDs, messages, count, "0000000000");
        
        assertEquals(null, result);
    }

    @Test
    public void testSearchByRecipient() {
        String[] recipients = {"+27834557896", "+27838884567", "+27834484567", "+27838884567"};
        String[] messages = {
            "Did you get the cake?",
            "Where are you? You are late! I have asked you to be on time.",
            "Yohoooo, I am at your gate.",
            "Ok, I am leaving without you."
        };
        int count = 4;
        
        String result = Message.searchByRecipient(recipients, messages, count, "+27838884567");
        
        assertEquals("Where are you? You are late! I have asked you to be on time. Ok, I am leaving without you.", result);
    }

    @Test
    public void testDeleteByHash() {
        String[] messages = {
            "Did you get the cake?",
            "Where are you? You are late!",
            "It is dinner time !"
        };
        String[] recipients = {"+27834557896", "+27838884567", "0838884567"};
        String[] hashes = {"12:0:DIDCAKE?", "98:1:WHERELATE!", "08:2:ITTIME!"};
        long[] ids = {1234567890L, 9876543210L, 8838884567L};
        int[] count = {3};
        
        boolean result = Message.deleteByHash(messages, recipients, hashes, ids, count, "98:1:WHERELATE!");
        
        assertEquals(true, result);
        assertEquals(2, count[0]);
        assertEquals("It is dinner time !", messages[1]);
    }

    @Test
    public void testDeleteByHashNotFound() {
        String[] messages = {"Did you get the cake?"};
        String[] recipients = {"+27834557896"};
        String[] hashes = {"12:0:DIDCAKE?"};
        long[] ids = {1234567890L};
        int[] count = {1};
        
        boolean result = Message.deleteByHash(messages, recipients, hashes, ids, count, "00:0:NOTFOUND");
        
        assertEquals(false, result);
        assertEquals(1, count[0]);
    }
}