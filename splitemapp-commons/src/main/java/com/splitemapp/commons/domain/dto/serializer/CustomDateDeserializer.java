package com.splitemapp.commons.domain.dto.serializer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.utils.Utils;

public class CustomDateDeserializer extends StdDeserializer<Date>{

	private static final long serialVersionUID = -1697378077899568775L;

	public CustomDateDeserializer(){
		super(Date.class);
	}

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		Date stringToDate = null;
		
		try {
			stringToDate = Utils.stringToDate(jp.getText(), ServiceConstants.DATE_FORMAT);
		} catch (ParseException e) {
			throw new IOException(e);
		}
		
		return stringToDate;
	}
}
