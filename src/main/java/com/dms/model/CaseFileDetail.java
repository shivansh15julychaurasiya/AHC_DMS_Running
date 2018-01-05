package com.dms.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="case_file_details")
public class CaseFileDetail{

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="case_file_details_seq")
	@SequenceGenerator(name="case_file_details_seq", sequenceName="case_file_details_seq", allocationSize=1)
	@Column(name="fd_id")
	private Long fd_id;
	
	@Column(name="fd_case_type")
	private Long fd_case_type;
	
	@Column(name="fd_case_no")
	private String fd_case_no="";
	
	@Column(name="fd_case_year")
	private Integer fd_case_year;	
	
	@Column(name="fd_document_name")
	private String fd_document_name;
	
	@Column(name="fd_file_source")
	private String fd_file_source;
	
	@Column(name="fd_rec_status")
	private int fd_rec_status;
	
	@Column(name="fd_stage_lid")
	private Long fd_stage_lid;
	
	@Column(name="fd_cr_by")
	private Long fd_cr_by;
	
	@Column(name="fd_cr_date")
	private Date fd_cr_date;	
	
	@Column(name="fd_mod_by")
	private Long fd_mod_by;	
	
	@Column(name="fd_mod_date")
	private Date fd_mod_date;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fd_case_type",insertable = false, updatable = false)
	private CaseType caseType;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "pt_fd_mid")
	private List<Petitioner> petitioners;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "rt_fd_mid")
	private List<Respondent> respondents;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "pc_fd_mid")
	private List<PetitionerCounsel> pCounsels;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "rc_fd_mid")
	private List<RespondentCounsel> rCounsels;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "io_fd_mid")
	private List<ImpugnedOrder> impugnedOrders;
	
	@javax.persistence.Transient
	private String case_type;
	
	
	@javax.persistence.Transient
	private String judgement_date;
	
	@javax.persistence.Transient
	private String bench_code;
	
	@javax.persistence.Transient
	private Long judgement_id;
		
	public Long getFd_id() {
		return fd_id;
	}

	public void setFd_id(Long fd_id) {
		this.fd_id = fd_id;
	}

	public Long getFd_case_type() {
		return fd_case_type;
	}

	public void setFd_case_type(Long fd_case_type) {
		this.fd_case_type = fd_case_type;
	}

	public String getFd_case_no() {
		return fd_case_no;
	}

	public void setFd_case_no(String fd_case_no) {
		this.fd_case_no = fd_case_no;
	}

	public Integer getFd_case_year() {
		return fd_case_year;
	}

	public void setFd_case_year(Integer fd_case_year) {
		this.fd_case_year = fd_case_year;
	}

	public String getFd_document_name() {
		return fd_document_name;
	}

	public void setFd_document_name(String fd_document_name) {
		this.fd_document_name = fd_document_name;
	}

	public String getFd_file_source() {
		return fd_file_source;
	}

	public void setFd_file_source(String fd_file_source) {
		this.fd_file_source = fd_file_source;
	}

	public int getFd_rec_status() {
		return fd_rec_status;
	}

	public void setFd_rec_status(int fd_rec_status) {
		this.fd_rec_status = fd_rec_status;
	}

	public Long getFd_stage_lid() {
		return fd_stage_lid;
	}

	public void setFd_stage_lid(Long fd_stage_lid) {
		this.fd_stage_lid = fd_stage_lid;
	}

	public Long getFd_cr_by() {
		return fd_cr_by;
	}

	public void setFd_cr_by(Long fd_cr_by) {
		this.fd_cr_by = fd_cr_by;
	}

	public Date getFd_cr_date() {
		return fd_cr_date;
	}

	public void setFd_cr_date(Date fd_cr_date) {
		this.fd_cr_date = fd_cr_date;
	}

	public Long getFd_mod_by() {
		return fd_mod_by;
	}

	public void setFd_mod_by(Long fd_mod_by) {
		this.fd_mod_by = fd_mod_by;
	}

	public Date getFd_mod_date() {
		return fd_mod_date;
	}

	public void setFd_mod_date(Date fd_mod_date) {
		this.fd_mod_date = fd_mod_date;
	}

	public CaseType getCaseType() {
		return caseType;
	}

	public void setCaseType(CaseType caseType) {
		this.caseType = caseType;
	}

	public String getCase_type() {
		return case_type;
	}

	public void setCase_type(String case_type) {
		this.case_type = case_type;
	}

	public String getJudgement_date() {
		return judgement_date;
	}

	public void setJudgement_date(String judgement_date) {
		this.judgement_date = judgement_date;
	}

	public String getBench_code() {
		return bench_code;
	}

	public void setBench_code(String bench_code) {
		this.bench_code = bench_code;
	}

	public Long getJudgement_id() {
		return judgement_id;
	}

	public void setJudgement_id(Long judgement_id) {
		this.judgement_id = judgement_id;
	}

	public List<ImpugnedOrder> getImpugnedOrders() {
		return impugnedOrders;
	}

	public void setImpugnedOrders(List<ImpugnedOrder> impugnedOrders) {
		this.impugnedOrders = impugnedOrders;
	}

	public List<Petitioner> getPetitioners() {
		return petitioners;
	}

	public void setPetitioners(List<Petitioner> petitioners) {
		this.petitioners = petitioners;
	}

	public List<Respondent> getRespondents() {
		return respondents;
	}

	public void setRespondents(List<Respondent> respondents) {
		this.respondents = respondents;
	}

	public List<PetitionerCounsel> getpCounsels() {
		return pCounsels;
	}

	public void setpCounsels(List<PetitionerCounsel> pCounsels) {
		this.pCounsels = pCounsels;
	}

	public List<RespondentCounsel> getrCounsels() {
		return rCounsels;
	}

	public void setrCounsels(List<RespondentCounsel> rCounsels) {
		this.rCounsels = rCounsels;
	}
	
}