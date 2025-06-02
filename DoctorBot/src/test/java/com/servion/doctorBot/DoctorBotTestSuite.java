package com.servion.doctorBot;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import com.servion.doctorBot.faq.service.FAQServiceTest;

@Suite
@SelectClasses({FAQServiceTest.class})
public class DoctorBotTestSuite {
	
}
