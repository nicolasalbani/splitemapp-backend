package com.splitemapp.commons.utils.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;
import org.jvnet.mock_javamail.Mailbox;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.utils.MailUtils;

public class MailUtilsTest {

	@Before
	public void setUp() {
		//clear Mock JavaMail box
		Mailbox.clearAll();
	}

	@Test
	public void testSendMail() throws MessagingException, IOException {
		// Sending e-mail
		MailUtils mailUtils = new MailUtils();
		
		mailUtils.sendMail(ServiceConstants.INFO_SERVICE_ADDRESS, 
				ServiceConstants.INFO_SERVICE_PASS, 
				"Question from Nicolas Albani",
				"nicolasalbani@gmail.com", 
				"This is an email");
		
		// Waiting for email thread to send the email
		while(!mailUtils.isMailSent()){
			try {
				Thread.sleep(100l);
			} catch (InterruptedException e) {
				// Do nothing
			}
		}

		// Validating that e-mail arrived at mocked inbox
		List<Message> inbox = Mailbox.get("nicolasalbani@gmail.com");
		assertTrue(inbox.size() == 1);
		assertEquals("Question from Nicolas Albani", inbox.get(0).getSubject());
		assertEquals("This is an email", inbox.get(0).getContent());
	}

	@Test
	public void testCraftMailText(){
		// Creating the map with the replacement
		Map<String,String> placeholdersMap = new HashMap<String,String>();
		placeholdersMap.put("FULLNAME", "Peter");
		
		// Crafting text and validating
		String mailText = new MailUtils().craftMailText("mail_template.html", placeholdersMap);
		assertEquals("Welcome to Splitemapp Peter",mailText);
	}
}
