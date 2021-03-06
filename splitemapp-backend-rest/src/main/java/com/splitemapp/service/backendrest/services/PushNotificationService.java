package com.splitemapp.service.backendrest.services;

import java.util.List;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.constants.TableField;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.GcmHttpDataDTO;
import com.splitemapp.commons.domain.dto.GcmHttpMessageDTO;
import com.splitemapp.commons.rest.RestUtils;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

public abstract class PushNotificationService {

	UserSessionEndpoint userSessionEndpoint;

	public UserSession getUserSession(String token){
		return userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, token);
	}

	public void sendGcmNotification(UserSession senderUserSession, String action, String projectName, Long projectId){
		// Getting all user sessions related to the one making the push
		List<UserSession> userSessionList;
		if(projectId == null){
			userSessionList = userSessionEndpoint.findCreatedAfter(null, senderUserSession.getUser().getId());
		} else {
			userSessionList = userSessionEndpoint.findPushedAfterByProject(null, projectId);
		}

		// Removing the user that made the update from the notification list
		for(int i=0; i<userSessionList.size();i++){
			Long sessionId = userSessionList.get(i).getId();
			if(sessionId.equals(senderUserSession.getId())){
				userSessionList.remove(i);
				break;
			}
		}

		// We only call the GCM service if we have something to send
		if(userSessionList.size() > 0){
			// Setting the HTTP data pay-load based on the calling service
			GcmHttpDataDTO gcmHttpDataDTO = new GcmHttpDataDTO();
			gcmHttpDataDTO.setSender(senderUserSession.getUser().getFullName());
			gcmHttpDataDTO.setAction(action);
			if(projectId != null){
				gcmHttpDataDTO.setProjectId(projectId.toString());
			}
			gcmHttpDataDTO.setProjectName(projectName);

			// Obtaining registration IDs
			String[] registrationIds = new String[userSessionList.size()];
			for(int i=0; i<userSessionList.size(); i++){
				registrationIds[i] = userSessionList.get(i).getGcmToken();
			}

			// Setting the HTTP message
			GcmHttpMessageDTO gcmHttpMessageDTO = new GcmHttpMessageDTO();
			gcmHttpMessageDTO.setRegistration_ids(registrationIds);
			gcmHttpMessageDTO.setData(gcmHttpDataDTO);

			try{
				RestUtils.callRestService(ServiceConstants.GCM_SERVER_URL, gcmHttpMessageDTO, Object.class);
			} catch (Exception e){
				// Doing nothing
			}
		}
	}

	// Getters and setters
	public UserSessionEndpoint getUserSessionEndpoint() {
		return userSessionEndpoint;
	}

	public void setUserSessionEndpoint(UserSessionEndpoint userSessionEndpoint) {
		this.userSessionEndpoint = userSessionEndpoint;
	}

}
