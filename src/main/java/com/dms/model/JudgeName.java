package com.dms.model;

import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "judge_name")
public class JudgeName {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="judge_name_seq")
	@SequenceGenerator(name="judge_name_seq", sequenceName="judge_name_seq", allocationSize=1)
	@Column(name = "jn_id")
	private Long jn_id;
	
	@Column(name = "jn_name")
	private String jn_name;

	@Column(name = "jn_short_name")
	private String jn_short_name;
	
	@Column(name = "jn_jo_code")
	private String jn_jo_code;
	
	@Column(name = "jn_cr_date")
	private Date jn_cr_date;
	
	@Column(name = "jn_rec_status")
	private Long jn_rec_status;
	
	@Column(name = "jn_type")
	private Long jn_type;
	
	@Column(name = "jn_joining_year")
	private Date jn_joining_year;
	
	@Column(name = "jn_retirement_year")
	private Date jn_retirement_year;
	
	@Column(name = "jn_otp")
	private Integer jn_otp;
	
	
	@Column(name = "jn_mobile")
	private String jn_mobile;
	
	
	

	public String getJn_mobile() {
		return jn_mobile;
	}

	public void setJn_mobile(String jn_mobile) {
		this.jn_mobile = jn_mobile;
	}

	public Integer getJn_otp() {
		return jn_otp;
	}

	public void setJn_otp(Integer jn_otp) {
		this.jn_otp = jn_otp;
	}

	public Long getJn_id() {
		return jn_id;
	}

	public void setJn_id(Long jn_id) {
		this.jn_id = jn_id;
	}

	public String getJn_name() {
		return jn_name;
	}

	public void setJn_name(String jn_name) {
		this.jn_name = jn_name;
	}

	public String getJn_short_name() {
		return jn_short_name;
	}

	public void setJn_short_name(String jn_short_name) {
		this.jn_short_name = jn_short_name;
	}

	public String getJn_jo_code() {
		return jn_jo_code;
	}

	public void setJn_jo_code(String jn_jo_code) {
		this.jn_jo_code = jn_jo_code;
	}

	public Date getJn_cr_date() {
		return jn_cr_date;
	}

	public void setJn_cr_date(Date jn_cr_date) {
		this.jn_cr_date = jn_cr_date;
	}

	public Long getJn_rec_status() {
		return jn_rec_status;
	}

	public void setJn_rec_status(Long jn_rec_status) {
		this.jn_rec_status = jn_rec_status;
	}

	public Long getJn_type() {
		return jn_type;
	}

	public void setJn_type(Long jn_type) {
		this.jn_type = jn_type;
	}

	public Date getJn_joining_year() {
		return jn_joining_year;
	}

	public void setJn_joining_year(Date jn_joining_year) {
		this.jn_joining_year = jn_joining_year;
	}

	public Date getJn_retirement_year() {
		return jn_retirement_year;
	}

	public void setJn_retirement_year(Date jn_retirement_year) {
		this.jn_retirement_year = jn_retirement_year;
	}

	@Override
	public String toString() {
		return "JudgeName [jn_id=" + jn_id + ", jn_name=" + jn_name + ", jn_short_name=" + jn_short_name
				+ ", jn_jo_code=" + jn_jo_code + ", jn_cr_date=" + jn_cr_date + ", jn_rec_status=" + jn_rec_status
				+ ", jn_type=" + jn_type + ", jn_joining_year=" + jn_joining_year + ", jn_retirement_year="
				+ jn_retirement_year + "]";
	}

	
}