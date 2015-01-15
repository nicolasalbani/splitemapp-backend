package com.splitemapp.service.backendrest.services;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.constants.TableField;
import com.splitemapp.commons.constants.TableFieldCod;
import com.splitemapp.commons.domain.Group;
import com.splitemapp.commons.domain.GroupStatus;
import com.splitemapp.commons.domain.UserSession;
import com.splitemapp.commons.domain.dto.GroupDTO;
import com.splitemapp.commons.domain.dto.request.PushRequest;
import com.splitemapp.commons.domain.dto.response.PushResponse;
import com.splitemapp.commons.domain.id.IdUpdate;
import com.splitemapp.commons.utils.Utils;
import com.splitemapp.service.backendrest.endpoint.GroupEndpoint;
import com.splitemapp.service.backendrest.endpoint.GroupStatusEndpoint;
import com.splitemapp.service.backendrest.endpoint.UserSessionEndpoint;

@Service
@Path(ServiceConstants.PUSH_GROUPS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PushGroupsService {

	UserSessionEndpoint userSessionEndpoint;
	GroupStatusEndpoint groupStatusEndpoint;
	GroupEndpoint groupEndpoint;

	@GET
	public String printMessage() {
		return this.getClass().getSimpleName() +" - "+ ServiceConstants.GET_SUCCESS;
	}
	
	@POST
	public PushResponse<Long> printMessage(PushRequest<GroupDTO> request) throws ParseException {

		// We create a pull groups response object setting success to false by default
		PushResponse<Long> response = new PushResponse<Long>();
		response.setSuccess(false);

		UserSession userSession = userSessionEndpoint.findByField(TableField.USER_SESSION_TOKEN, request.getToken());

		if(userSession != null){
			// We add or update each one of the items in the DTO list
			for(GroupDTO groupDTO:request.getItemList()){
				if(Utils.isDateGreaterThan(groupDTO.getCreatedAt(),request.getLastPushSuccessAt())){
					// We create the group object with an active group status
					GroupStatus groupStatus = groupStatusEndpoint.findByField(TableField.ALTER_TABLE_COD, TableFieldCod.GROUP_STATUS_ACTIVE);
					Group group = new Group(groupStatus, groupDTO);
					
					// We persist the entry to the database
					groupEndpoint.persist(group);
					
					// We add the IdUpdate element to the response list
					response.getIdUpdateList().add(new IdUpdate<Long>(groupDTO.getId(), group.getId()));
				} else {
					// We get the group entry from the database
					Group group = groupEndpoint.findById(groupDTO.getId());
					
					// We update the group status
					GroupStatus groupStatus = groupStatusEndpoint.findById(groupDTO.getGroupStatusId());
					group.setGroupStatus(groupStatus);
					
					// We update the rest of the values
					group.setCod(groupDTO.getCod());
					group.setImgCover(groupDTO.getImgCover());
					group.setTitle(groupDTO.getTitle());
					group.setUpdatedAt(groupDTO.getUpdatedAt());
					
					// We merge the entry to the database
					groupEndpoint.merge(group);
				}
			}

			// We set the success flag
			response.setSuccess(true);
		}

		return response;
	}


	// Getters and setters

	public GroupEndpoint getGroupEndpoint() {
		return groupEndpoint;
	}

	public void setGroupEndpoint(GroupEndpoint groupEndpoint) {
		this.groupEndpoint = groupEndpoint;
	}

	public GroupStatusEndpoint getGroupStatusEndpoint() {
		return groupStatusEndpoint;
	}

	public void setGroupStatusEndpoint(GroupStatusEndpoint groupStatusEndpoint) {
		this.groupStatusEndpoint = groupStatusEndpoint;
	}
}