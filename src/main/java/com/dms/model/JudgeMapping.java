package com.dms.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name = "ecourt_judge_maping")
public class JudgeMapping {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="ejm_seq")
	@SequenceGenerator(name="ejm_seq", sequenceName="ejm_seq", allocationSize=1)
	@Column(name = "ejm_id")
	private Long ejm_id;
	
	@Column(name = "ejm_cm_mid")
	private Integer ejm_cm_mid;
	
	@Column(name = "ejm_bench_id")
	private Integer ejm_bench_id;
	
	@Column(name = "ejm_jo_code")
	private String ejm_jo_code;
	
	@Column(name = "ejm_rec_stauts")
	private Integer ejm_rec_stauts;
	
	@Column(name = "ejm_date")
	private Date ejm_date;
	
	@Column(name="ejm_judge_name")
	private String ejm_judge_name;
	
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ejm_cm_mid",insertable = false, updatable = false)
	private CourtMaster courtMaster;
	
	

	public CourtMaster getCourtMaster() {
		return courtMaster;
	}

	public void setCourtMaster(CourtMaster courtMaster) {
		this.courtMaster = courtMaster;
	}

	public String getEjm_judge_name() {
		return ejm_judge_name;
	}

	public void setEjm_judge_name(String ejm_judge_name) {
		this.ejm_judge_name = ejm_judge_name;
	}

	public Long getEjm_id() {
		return ejm_id;
	}

	public void setEjm_id(Long ejm_id) {
		this.ejm_id = ejm_id;
	}

	public Integer getEjm_cm_mid() {
		return ejm_cm_mid;
	}

	public void setEjm_cm_mid(Integer ejm_cm_mid) {
		this.ejm_cm_mid = ejm_cm_mid;
	}

	public Integer getEjm_bench_id() {
		return ejm_bench_id;
	}

	public void setEjm_bench_id(Integer ejm_bench_id) {
		this.ejm_bench_id = ejm_bench_id;
	}

	public String getEjm_jo_code() {
		return ejm_jo_code;
	}

	public void setEjm_jo_code(String ejm_jo_code) {
		this.ejm_jo_code = ejm_jo_code;
	}

	public Integer getEjm_rec_stauts() {
		return ejm_rec_stauts;
	}

	public void setEjm_rec_stauts(Integer ejm_rec_stauts) {
		this.ejm_rec_stauts = ejm_rec_stauts;
	}

	public Date getEjm_date() {
		return ejm_date;
	}

	public void setEjm_date(Date ejm_date) {
		this.ejm_date = ejm_date;
	}

	@Override
	public String toString() {
		return "DMSJudge_mapping [ejm_id=" + ejm_id + ", ejm_cm_mid=" + ejm_cm_mid + ", ejm_bench_id=" + ejm_bench_id
				+ ", ejm_jo_code=" + ejm_jo_code + ", ejm_rec_stauts=" + ejm_rec_stauts + ", ejm_date=" + ejm_date
				+ ", ejm_judge_name=" + ejm_judge_name + ", getEjm_judge_name()=" + getEjm_judge_name()
				+ ", getEjm_id()=" + getEjm_id() + ", getEjm_cm_mid()=" + getEjm_cm_mid() + ", getEjm_bench_id()="
				+ getEjm_bench_id() + ", getEjm_jo_code()=" + getEjm_jo_code() + ", getEjm_rec_stauts()="
				+ getEjm_rec_stauts() + ", getEjm_date()=" + getEjm_date() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}


}


