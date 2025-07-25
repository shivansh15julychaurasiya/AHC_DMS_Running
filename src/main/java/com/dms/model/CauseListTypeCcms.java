package com.dms.model;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class CauseListTypeCcms {

	
	@JsonProperty("CauselistType")
	String CauselistType;
	
	@JsonProperty("CauseListType")
	String CauseListType;
	
	

	public String getCauseListType() {
		return CauseListType;
	}

	public void setCauseListType(String causeListType) {
		CauseListType = causeListType;
	}

	public String getCauselistType() {
		return CauselistType;
	}

	public void setCauselistType(String causelistType) {
		CauselistType = causelistType;
	}
	
	
	
}
