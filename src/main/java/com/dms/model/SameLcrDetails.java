package com.dms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "same_lcr_details")
public class SameLcrDetails {
	

	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="sld_seq")
	@SequenceGenerator(name="sld_seq", sequenceName="sld_seq", allocationSize=1)
	@Column(name = "sld_id")
	private Long sld_id;
	
	
	@Column(name = "sld_cl_mid")
	private Long sld_cl_mid;
	
	@Column(name = "sld_fd_mid")
	private Long sld_fd_mid;
	
	@Column(name = "sld_display")
	private String sld_display;
	
	@Column(name = "sld_party")
	private String sld_party;
	
	@Column(name = "sld_status")
	private String sld_status;
	
	@Column(name = "sld_disp_date")
	private String sld_disp_date;
	
	
	@Column(name = "sld_dol")
	private Date sld_dol;
	
	
	
	

	public Date getSld_dol() {
		return sld_dol;
	}

	public void setSld_dol(Date sld_dol) {
		this.sld_dol = sld_dol;
	}

	public Long getSld_fd_mid() {
		return sld_fd_mid;
	}

	public void setSld_fd_mid(Long sld_fd_mid) {
		this.sld_fd_mid = sld_fd_mid;
	}

	public Long getSld_id() {
		return sld_id;
	}

	public void setSld_id(Long sld_id) {
		this.sld_id = sld_id;
	}

	public Long getSld_cl_mid() {
		return sld_cl_mid;
	}

	public void setSld_cl_mid(Long sld_cl_mid) {
		this.sld_cl_mid = sld_cl_mid;
	}

	public String getSld_display() {
		return sld_display;
	}

	public void setSld_display(String sld_display) {
		this.sld_display = sld_display;
	}

	public String getSld_party() {
		return sld_party;
	}

	public void setSld_party(String sld_party) {
		this.sld_party = sld_party;
	}

	public String getSld_status() {
		return sld_status;
	}

	public void setSld_status(String sld_status) {
		this.sld_status = sld_status;
	}

	public String getSld_disp_date() {
		return sld_disp_date;
	}

	public void setSld_disp_date(String sld_disp_date) {
		this.sld_disp_date = sld_disp_date;
	}
	
	



}
