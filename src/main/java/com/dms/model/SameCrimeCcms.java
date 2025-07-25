package com.dms.model;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class SameCrimeCcms {
	
	@JsonProperty("CaseID")
	String CaseID;
	@JsonProperty("CINO")
	String CINO;
	@JsonProperty("PetName")
	String PetName;
	@JsonProperty("ResName")
	String ResName;
	
	@JsonProperty("CaseStatus")
	String CaseStatus;
	@JsonProperty("DisplayCaseno")
	String DisplayCaseno;
	@JsonProperty("PoliceStName")
	String PoliceStName;
	
	@JsonProperty("OrderDate")
	String OrderDate;
	
	@JsonProperty("judge_code")
	String judge_code;
	
	@JsonProperty("PS_Code")
	String PS_Code;
	
	
	
	public String getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}
	public String getJudge_code() {
		return judge_code;
	}
	public void setJudge_code(String judge_code) {
		this.judge_code = judge_code;
	}
	public String getPS_Code() {
		return PS_Code;
	}
	public void setPS_Code(String pS_Code) {
		PS_Code = pS_Code;
	}
	public String getCaseID() {
		return CaseID;
	}
	public void setCaseID(String caseID) {
		CaseID = caseID;
	}
	public String getCINO() {
		return CINO;
	}
	public void setCINO(String cINO) {
		CINO = cINO;
	}
	public String getPetName() {
		return PetName;
	}
	public void setPetName(String petName) {
		PetName = petName;
	}
	public String getResName() {
		return ResName;
	}
	public void setResName(String resName) {
		ResName = resName;
	}
	public String getCaseStatus() {
		return CaseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		CaseStatus = caseStatus;
	}
	public String getDisplayCaseno() {
		return DisplayCaseno;
	}
	public void setDisplayCaseno(String displayCaseno) {
		DisplayCaseno = displayCaseno;
	}
	public String getPoliceStName() {
		return PoliceStName;
	}
	public void setPoliceStName(String policeStName) {
		PoliceStName = policeStName;
	}
	
	
	

}

