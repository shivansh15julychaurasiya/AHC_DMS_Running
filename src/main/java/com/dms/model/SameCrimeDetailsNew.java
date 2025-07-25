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
@Table(name = "same_crime_details_new")
public class SameCrimeDetailsNew {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="scd_new_seq")
	@SequenceGenerator(name="scd_new_seq", sequenceName="scd_new_seq", allocationSize=1)
	@Column(name = "scd_id_new")
	private Long scd_id_new;
	
	@Column(name = "scd_cl_id")
	private Long scd_cl_id;
	
	@Column(name = "scd_fd_mid")
	private Long scd_fd_mid;
	
	@Column(name = "scd_ct_mid")
	private Long scd_ct_mid;
	
	@Column(name = "scd_pet_name")
	private String scd_pet_name;
	
	@Column(name = "scd_res_name")
	private String scd_res_name;

	@Column(name = "scd_case_status")
	private String scd_case_status;

	@Column(name = "scd_display")
	private String scd_display;
	
	@Column(name = "scd_ps_name")
	private String scd_ps_name;
	
	@Column(name = "scd_dol")
	private Date scd_dol;

	public Long getScd_id_new() {
		return scd_id_new;
	}

	public void setScd_id_new(Long scd_id_new) {
		this.scd_id_new = scd_id_new;
	}

	public Long getScd_cl_id() {
		return scd_cl_id;
	}

	public void setScd_cl_id(Long scd_cl_id) {
		this.scd_cl_id = scd_cl_id;
	}

	public Long getScd_fd_mid() {
		return scd_fd_mid;
	}

	public void setScd_fd_mid(Long scd_fd_mid) {
		this.scd_fd_mid = scd_fd_mid;
	}

	public Long getScd_ct_mid() {
		return scd_ct_mid;
	}

	public void setScd_ct_mid(Long scd_ct_mid) {
		this.scd_ct_mid = scd_ct_mid;
	}

	public String getScd_pet_name() {
		return scd_pet_name;
	}

	public void setScd_pet_name(String scd_pet_name) {
		this.scd_pet_name = scd_pet_name;
	}

	public String getScd_res_name() {
		return scd_res_name;
	}

	public void setScd_res_name(String scd_res_name) {
		this.scd_res_name = scd_res_name;
	}

	public String getScd_case_status() {
		return scd_case_status;
	}

	public void setScd_case_status(String scd_case_status) {
		this.scd_case_status = scd_case_status;
	}

	public String getScd_display() {
		return scd_display;
	}

	public void setScd_display(String scd_display) {
		this.scd_display = scd_display;
	}

	public String getScd_ps_name() {
		return scd_ps_name;
	}

	public void setScd_ps_name(String scd_ps_name) {
		this.scd_ps_name = scd_ps_name;
	}

	public Date getScd_dol() {
		return scd_dol;
	}

	public void setScd_dol(Date scd_dol) {
		this.scd_dol = scd_dol;
	}
	
	

	

}
