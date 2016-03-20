package com.splitemapp.commons.domain;

import java.util.Date;

public interface PushableEntity {

	public Date getUpdatedAt();
	public void setUpdatedAt(Date updatedAt);
	
	public Date getPushedAt();
	public void setPushedAt(Date pushedAt);
	
	public User getUpdatedBy();
	public void setUpdatedBy(User updatedBy);
	
	public User getPushedBy();
	public void setPushedBy(User pushedBy);

}
