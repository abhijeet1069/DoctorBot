package com.servion.doctorBot.faq.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servion.doctorBot.faq.service.FAQService;
import com.servion.doctorBot.util.LogUtil;
@RestController
@RequestMapping("/faq")
public class FAQController {

	private final FAQService faqService;

	public FAQController() {
		this.faqService = new FAQService("faq.json");
		LogUtil.logInfo ("Controller initialised: /faq/working-hours");
	}

	@GetMapping("/working-hours")
	public ResponseEntity<String> getWorkingHours() {
		LogUtil.logInfo ("GET /faq/working-hours");
		String answer = faqService.getWorkingHours();
		return ResponseEntity.ok(answer);
	}
	
	@GetMapping("/fees")
	public ResponseEntity<String> getFee() {
		LogUtil.logInfo ("GET /faq/fees");
		String answer = faqService.getFees();
		return ResponseEntity.ok(answer);
	}

	@GetMapping("/doctor")
	public ResponseEntity<List<String>> getDoctor() {
		LogUtil.logInfo ("GET /faq/doctor");
		List<String> answer = faqService.getDoctor();
		return ResponseEntity.ok(answer);
	}
	
}
