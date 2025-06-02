package com.servion.doctorBot.faq.service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servion.doctorBot.exception.ConfigLoadException;
import com.servion.doctorBot.exception.TagNotFoundException;
import com.servion.doctorBot.faq.pojo.FAQConfig;
import com.servion.doctorBot.faq.pojo.FAQEntry;

public class FAQService {

	private FAQConfig faqConfig;
	
	private String path;
	
	public FAQService(String path) {
		this.path = path;
		ObjectMapper mapper = new ObjectMapper();
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path)) {
			if (inputStream == null) {
				throw new ConfigLoadException("File not found: " + path);
			}
			faqConfig = mapper.readValue(inputStream, FAQConfig.class);
		} 
		catch (JsonParseException e) {
			throw new ConfigLoadException(
			        "JSON syntax error in "+path+" at line " +
			        e.getLocation().getLineNr() + ", column " +
			        e.getLocation().getColumnNr() + ": " +
			        e.getOriginalMessage(), e
			    );
		} 
		catch (Exception e) {
			throw new ConfigLoadException(e.getMessage());
		}
	}

	public String getWorkingHours() {
		Optional<FAQEntry> entry = getFaqByTopic("working_hours");
		if (entry.isPresent()) {
			FAQEntry faq = entry.get();
			if (faq.getAnswer() != null && faq.getAnswer().getText() != null) {
				return faq.getAnswer().getText();
			} else {
				throw new TagNotFoundException("answer.text for topic 'working_hours' is missing");
			}
		} else {
			throw new TagNotFoundException("Tag 'working_hours' not found in "+path);
		}
	}

	private Optional<FAQEntry> getFaqByTopic(String topic) {
	    if (faqConfig == null) {
	        throw new ConfigLoadException("FAQ configuration is not loaded");
	    }
	    if (faqConfig.getFaq() == null || faqConfig.getFaq().isEmpty()) {
	        throw new TagNotFoundException("FAQ list is empty or missing");
	    }
	    return faqConfig.getFaq()
	            .stream()
	            .filter(faq -> faq.getTopic() != null && faq.getTopic().equalsIgnoreCase(topic))
	            .findFirst();
	}
	
	public String getFees() {
		Optional<FAQEntry> entry = getFaqByTopic("fees");
		if (entry.isPresent()) {
			FAQEntry faq = entry.get();
			if (faq.getAnswer() != null && faq.getAnswer().getText() != null) {
				return faq.getAnswer().getText();
			} else {
				throw new TagNotFoundException("answer.text for topic 'fees' is missing");
			}
		} else {
			throw new TagNotFoundException("Tag 'fees' not found in "+path);
		}
	}
	
	public List<String> getDoctor() {
		Optional<FAQEntry> entry = getFaqByTopic("doctor");
		if (entry.isPresent()) {
			FAQEntry faq = entry.get();
			if (faq.getAnswer() != null && faq.getAnswer().getList() != null) {
				return faq.getAnswer().getList();
			} else {
				throw new TagNotFoundException("answer.list for topic 'doctor' is missing");
			}
		} else {
			throw new TagNotFoundException("Tag 'doctor' not found in "+path);
		}
	}
}
