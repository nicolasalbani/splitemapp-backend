package com.splitemapp.commons.utils;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.splitemapp.commons.constants.ServiceConstants;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class MailUtils {

	/**
	 * Sends the email to the appropriate account
	 * @param username
	 * @param password
	 * @param subject
	 * @param toAddress
	 * @param fromAddress
	 * @param content
	 */
	public static void sendMail(final String username, final String password, String subject, String toAddress, String fromAddress, String content){
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", ServiceConstants.SMTP_HOST);
		props.put("mail.smtp.port", ServiceConstants.SMTP_PORT);

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromAddress));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
			message.setSubject(subject);
			message.setContent(content, "text/html");

			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String craftMailText(String templateName, Map<String,String> placeholdersMap){
		// Creating properties to be passed to the velocity engine
		Properties props = new Properties();
        props.setProperty("resource.loader", "class");
        props.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		
		// Initializing the Velocity engine
		VelocityEngine ve = new VelocityEngine(props);
		ve.init();
		
		// Creating a template based on the provided file name
		Template t = ve.getTemplate(templateName);

		// Creating a context and replacing the place holders
		VelocityContext context = new VelocityContext();
		Iterator<String> iterator = placeholdersMap.keySet().iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			context.put(key, placeholdersMap.get(key));
		}
		
		// Rendering the template into a StringWriter
		StringWriter writer = new StringWriter();
		t.merge( context, writer );
		
		return writer.toString();
	}

}
