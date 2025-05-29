package com.servion.doctorBot.message.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.servion.doctorBot.exception.ConfigLoadException;
import com.servion.doctorBot.util.LogUtil;

class MessageServiceTest {

	@Test
    public void testGetMessage() {
        MessageService service = MessageService.getInstance("test-good-message.yml");
        String message = service.getMessage();
        assertNotNull(message);
        assertEquals("Hello World!!", message);
    }
	
	@Test
	public void testGetMessageForSyntaxErrors() {
	    String configFile = "test-bad-message.yml";
	    try {
	            MessageService service = MessageService.getInstance(configFile);
	            service.getMessage();
	        }
	    catch(Exception ex) {
	    	LogUtil.logError(ex);
		    assertTrue(ex.getMessage().contains("YAML structure mapping error"));
	    }
	}

	
	@Test
	public void testGetMessageForFileNotPresent() {
	    String configFile = "test-non-existent.yml";
	    try {
	            MessageService service = MessageService.getInstance(configFile);
	            service.getMessage();
	        }
	    catch(Exception ex) {
	    	LogUtil.logError(ex);
		    assertTrue(ex.getMessage().contains("YML file not found at path"));
	    }
	}
	
	@Test
	public void testGetMessageForNullFile() {
	   try {
		   String configFile = null;
		   MessageService service = MessageService.getInstance(configFile);
           service.getMessage();
	   }
	   catch(Exception ex) {
		   LogUtil.logError(ex);
		   assertTrue(ex instanceof ConfigLoadException);
		   assertTrue(ex.getMessage().contains("YML file cannot be null empty or blank"));
	   }
	}
	
	@Test
	public void testGetMessageForEmptyFile() {
	   try {
		   String configFile = " ";
		   MessageService service = MessageService.getInstance(configFile);
           service.getMessage();
	   }
	   catch(Exception ex) {
		   LogUtil.logError(ex);
		   assertTrue(ex instanceof ConfigLoadException);
		   assertTrue(ex.getMessage().contains("YML file cannot be null empty or blank"));
	   }
	}
}
