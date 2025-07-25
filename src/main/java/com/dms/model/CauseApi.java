package com.dms.model;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class CauseApi {
	@JsonProperty("hc_id")
	 String hc_id;
	@JsonProperty("SerialNo")
	 String SerialNo;
	@JsonProperty("Case_Number")
		String Case_Number;	
	@JsonProperty("Display_Case_no")
		String Display_Case_no;
	@JsonProperty("cino")
		String cino;
	@JsonProperty("case_type_name")
		String case_type_name;
	@JsonProperty("regcase_type")
		String regcase_type;
	@JsonProperty("reg_no")
		String reg_no;
	@JsonProperty("reg_year")
		String reg_year;
	@JsonProperty("for_bench_id")
		String for_bench_id;
	
	@JsonProperty("Jo_code")
	String Jo_code;
	
	
	
	@JsonProperty("room_no")
	String room_no;
@JsonProperty("Bench_Name")
	String Bench_Name;
@JsonProperty("causelist_type")
	String causelist_type;
@JsonProperty("Column1")
	String Column1;
@JsonProperty("causelist_short_code")
	String causelist_short_code;


@JsonProperty("causelist_date")
String causelist_date;
@JsonProperty("Pet_Name")
String Pet_Name;
@JsonProperty("Res_Name")
String Res_Name;
@JsonProperty("pet_adv")
String pet_adv;
@JsonProperty("connectedCase")
String connectedCase;


@JsonProperty("res_adv")
String res_adv;
@JsonProperty("ia_no")
String ia_no;
@JsonProperty("ia_year")
String ia_year;

@JsonProperty("room_no1")
String room_no1;


@JsonProperty("DisplayCaseno")
String DisplayCaseno;


@JsonProperty("cause_list_type_id")
String cause_list_type_id;



@JsonProperty("Petname")
String Petname;


@JsonProperty("case_id")
String case_id;

@JsonProperty("Case_id")
String case_id1;


@JsonProperty("Application_order")
String Application_order;



@JsonProperty("Is_ApplicationForOrder")
String Is_ApplicationForOrder;


@JsonProperty("Resname")
String Resname;
	







	
	public String getIs_ApplicationForOrder() {
	return Is_ApplicationForOrder;
}
public void setIs_ApplicationForOrder(String is_ApplicationForOrder) {
	Is_ApplicationForOrder = is_ApplicationForOrder;
}
	public String getApplication_order() {
	return Application_order;
}
public void setApplication_order(String application_order) {
	Application_order = application_order;
}
	public String getCase_id1() {
	return case_id1;
}
public void setCase_id1(String case_id1) {
	this.case_id1 = case_id1;
}
	public String getCase_id() {
	return case_id;
}
public void setCase_id(String case_id) {
	this.case_id = case_id;
}
	public String getPetname() {
	return Petname;
}
public void setPetname(String petname) {
	Petname = petname;
}
public String getResname() {
	return Resname;
}
public void setResname(String resname) {
	Resname = resname;
}
	public String getCause_list_type_id() {
	return cause_list_type_id;
}
public void setCause_list_type_id(String cause_list_type_id) {
	this.cause_list_type_id = cause_list_type_id;
}
	public String getDisplayCaseno() {
	return DisplayCaseno;
}
public void setDisplayCaseno(String displayCaseno) {
	DisplayCaseno = displayCaseno;
}
	public String getRoom_no1() {
	return room_no1;
}
public void setRoom_no1(String room_no1) {
	this.room_no1 = room_no1;
}
	public String getJo_code() {
	return Jo_code;
}
public void setJo_code(String jo_code) {
	Jo_code = jo_code;
}
	public String getHc_id() {
		return hc_id;
	}
	public void setHc_id(String hc_id) {
		this.hc_id = hc_id;
	}
	public String getSerialNo() {
		return SerialNo;
	}
	public void setSerialNo(String serialNo) {
		SerialNo = serialNo;
	}
	public String getCase_Number() {
		return Case_Number;
	}
	public void setCase_Number(String case_Number) {
		Case_Number = case_Number;
	}
	public String getDisplay_Case_no() {
		return Display_Case_no;
	}
	public void setDisplay_Case_no(String display_Case_no) {
		Display_Case_no = display_Case_no;
	}
	public String getCino() {
		return cino;
	}
	public void setCino(String cino) {
		this.cino = cino;
	}
	public String getCase_type_name() {
		return case_type_name;
	}
	public void setCase_type_name(String case_type_name) {
		this.case_type_name = case_type_name;
	}
	public String getRegcase_type() {
		return regcase_type;
	}
	public void setRegcase_type(String regcase_type) {
		this.regcase_type = regcase_type;
	}
	public String getReg_no() {
		return reg_no;
	}
	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}
	public String getReg_year() {
		return reg_year;
	}
	public void setReg_year(String reg_year) {
		this.reg_year = reg_year;
	}
	public String getFor_bench_id() {
		return for_bench_id;
	}
	public void setFor_bench_id(String for_bench_id) {
		this.for_bench_id = for_bench_id;
	}
	public String getRoom_no() {
		return room_no;
	}
	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}
	public String getBench_Name() {
		return Bench_Name;
	}
	public void setBench_Name(String bench_Name) {
		Bench_Name = bench_Name;
	}
	public String getCauselist_type() {
		return causelist_type;
	}
	public void setCauselist_type(String causelist_type) {
		this.causelist_type = causelist_type;
	}
	public String getColumn1() {
		return Column1;
	}
	public void setColumn1(String column1) {
		Column1 = column1;
	}
	public String getCauselist_short_code() {
		return causelist_short_code;
	}
	public void setCauselist_short_code(String causelist_short_code) {
		this.causelist_short_code = causelist_short_code;
	}
	public String getCauselist_date() {
		return causelist_date;
	}
	public void setCauselist_date(String causelist_date) {
		this.causelist_date = causelist_date;
	}
	
	public String getPet_Name() {
		return Pet_Name;
	}
	public void setPet_Name(String pet_Name) {
		Pet_Name = pet_Name;
	}
	public String getRes_Name() {
		return Res_Name;
	}
	public void setRes_Name(String res_Name) {
		Res_Name = res_Name;
	}
	public String getPet_adv() {
		return pet_adv;
	}
	public void setPet_adv(String pet_adv) {
		this.pet_adv = pet_adv;
	}
	public String getConnectedCase() {
		return connectedCase;
	}
	public void setConnectedCase(String connectedCase) {
		this.connectedCase = connectedCase;
	}
	public String getRes_adv() {
		return res_adv;
	}
	public void setRes_adv(String res_adv) {
		this.res_adv = res_adv;
	}
	public String getIa_no() {
		return ia_no;
	}
	public void setIa_no(String ia_no) {
		this.ia_no = ia_no;
	}
	public String getIa_year() {
		return ia_year;
	}
	public void setIa_year(String ia_year) {
		this.ia_year = ia_year;
	}
	
	
	

		
		
		
}
