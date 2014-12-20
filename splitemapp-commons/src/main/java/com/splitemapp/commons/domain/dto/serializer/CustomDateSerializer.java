package com.splitemapp.commons.domain.dto.serializer;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.splitemapp.commons.constants.ServiceConstants;
import com.splitemapp.commons.utils.Utils;

public class CustomDateSerializer extends StdSerializer<Date>{

	public CustomDateSerializer(){
		super(Date.class);
	}

	@Override
	public void serialize(Date value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		jgen.writeString(Utils.dateToString(value, ServiceConstants.DATE_FORMAT));
	}
}
