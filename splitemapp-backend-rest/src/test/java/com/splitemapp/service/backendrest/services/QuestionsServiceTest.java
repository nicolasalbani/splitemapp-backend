package com.splitemapp.service.backendrest.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.junit.Test;
import org.jvnet.mock_javamail.Mailbox;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.domain.dto.QuestionDTO;
import com.splitemapp.commons.domain.dto.request.QuestionsRequest;
import com.splitemapp.commons.domain.dto.response.QuestionsResponse;
import com.splitemapp.commons.rest.RestUtils;

public class QuestionsServiceTest extends BaseServiceTest {

	@Override
	public List<String> getServicesPathList() {
		List<String> servicePathList = new ArrayList<String>();
		servicePathList.add(ServiceConstants.QUESTIONS_PATH);
		return servicePathList;
	}

	@Test
	public void synchronizeContactsServiceTest() throws MessagingException, IOException{
		//clear Mock JavaMail box
		Mailbox.clearAll();

		// Crafting the full service URL
		String serviceUrl = SERVICE_TEST_BASE_PATH + ServiceConstants.QUESTIONS_PATH;

		// Crafting the QuestionDTO object to be sent
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setMessage("This is my question");

		// Crafting the request object
		QuestionsRequest request = new QuestionsRequest();
		request.setQuestionDTO(questionDTO);
		request.setToken(TOKEN);

		// Making rest service call
		QuestionsResponse response = RestUtils.callRestService(serviceUrl, request, QuestionsResponse.class);

		// Validating successful response
		assertTrue(response.getSuccess());

		// Validating that e-mail arrived at mocked inbox
		List<Message> inbox = Mailbox.get("questions@splitemapp.com");
		assertTrue(inbox.size() == 1);
		assertEquals("Question from juan perez", inbox.get(0).getSubject());
		assertEquals("This is my question", inbox.get(0).getContent());
	}
}
