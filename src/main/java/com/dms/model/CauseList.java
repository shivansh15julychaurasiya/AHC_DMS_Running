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
import javax.persistence.Transient;

@Entity
@Table(name = "cause_list")
public class CauseList {

	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="cause_list_seq")
	@SequenceGenerator(name="cause_list_seq", sequenceName="cause_list_seq", allocationSize=1)
	@Column(name = "cl_id")
	private Long cl_id;
	
	
	@Column(name = "cl_case_type_mid")
	private Long cl_case_type_mid;
	
	@Column(name = "cl_case_no")
	private String cl_case_no;
	
	@Column(name = "cl_case_year")
	private Integer cl_case_year;
	
	@Column(name = "cl_court_no")
	private Integer cl_court_no;
	
	@Column(name = "cl_serial_no")
	private Integer cl_serial_no;
	
	@Column(name = "cl_list_type_mid")
	private Long cl_list_type_mid;
	
	@Column(name = "cl_first_petitioner")
	private String cl_first_petitioner;
	
	@Column(name = "cl_first_respondent")
	private String cl_first_respondent;
	
	@Column(name = "cl_petitioner_council")
	private String cl_petitioner_council;
	
	@Column(name = "cl_respondent_council")
	private String cl_respondent_council;
	
	@Column(name = "cl_ano")
	private Integer cl_ano;
	
	@Column(name = "cl_ayr")
	private Integer cl_ayr;
	
	@Column(name = "cl_applawp")
	private String cl_applawp;
	
	@Column(name = "cl_applawr")
	private String cl_applawr;
	
	@Column(name = "cl_stage")
	private Integer cl_stage;
	
	@Column(name = "cl_dol")
	private Date cl_dol;
	
	@Column(name = "cl_fd_mid")
	private Long cl_fd_mid;
	
	@Column(name = "cl_sequence")
	private Integer cl_sequence;
	
	@Transient
	private Integer count;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cl_case_type_mid",insertable = false, updatable = false)
	private CaseType caseType;
	
	
	public Long getCl_id() {
		return cl_id;
	}

	public void setCl_id(Long cl_id) {
		this.cl_id = cl_id;
	}

	public Long getCl_case_type_mid() {
		return cl_case_type_mid;
	}

	public void setCl_case_type_mid(Long cl_case_type_mid) {
		this.cl_case_type_mid = cl_case_type_mid;
	}

	public String getCl_case_no() {
		return cl_case_no;
	}

	public void setCl_case_no(String cl_case_no) {
		this.cl_case_no = cl_case_no;
	}

	public Integer getCl_case_year() {
		return cl_case_year;
	}

	public void setCl_case_year(Integer cl_case_year) {
		this.cl_case_year = cl_case_year;
	}

	public Integer getCl_court_no() {
		return cl_court_no;
	}

	public void setCl_court_no(Integer cl_court_no) {
		this.cl_court_no = cl_court_no;
	}

	public Integer getCl_serial_no() {
		return cl_serial_no;
	}

	public void setCl_serial_no(Integer cl_serial_no) {
		this.cl_serial_no = cl_serial_no;
	}

	public Long getCl_list_type_mid() {
		return cl_list_type_mid;
	}

	public void setCl_list_type_mid(Long cl_list_type_mid) {
		this.cl_list_type_mid = cl_list_type_mid;
	}

	public String getCl_first_petitioner() {
		return cl_first_petitioner;
	}

	public void setCl_first_petitioner(String cl_first_petitioner) {
		this.cl_first_petitioner = cl_first_petitioner;
	}

	public String getCl_first_respondent() {
		return cl_first_respondent;
	}

	public void setCl_first_respondent(String cl_first_respondent) {
		this.cl_first_respondent = cl_first_respondent;
	}

	public String getCl_petitioner_council() {
		return cl_petitioner_council;
	}

	public void setCl_petitioner_council(String cl_petitioner_council) {
		this.cl_petitioner_council = cl_petitioner_council;
	}

	public String getCl_respondent_council() {
		return cl_respondent_council;
	}

	public void setCl_respondent_council(String cl_respondent_council) {
		this.cl_respondent_council = cl_respondent_council;
	}

	public Integer getCl_ano() {
		return cl_ano;
	}

	public void setCl_ano(Integer cl_ano) {
		this.cl_ano = cl_ano;
	}

	public Integer getCl_ayr() {
		return cl_ayr;
	}

	public void setCl_ayr(Integer cl_ayr) {
		this.cl_ayr = cl_ayr;
	}

	public String getCl_applawp() {
		return cl_applawp;
	}

	public void setCl_applawp(String cl_applawp) {
		this.cl_applawp = cl_applawp;
	}

	public String getCl_applawr() {
		return cl_applawr;
	}

	public void setCl_applawr(String cl_applawr) {
		this.cl_applawr = cl_applawr;
	}

	public Integer getCl_stage() {
		return cl_stage;
	}

	public void setCl_stage(Integer cl_stage) {
		this.cl_stage = cl_stage;
	}

	public Date getCl_dol() {
		return cl_dol;
	}

	public void setCl_dol(Date cl_dol) {
		this.cl_dol = cl_dol;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getCl_fd_mid() {
		return cl_fd_mid;
	}

	public void setCl_fd_mid(Long cl_fd_mid) {
		this.cl_fd_mid = cl_fd_mid;
	}

	public CaseType getCaseType() {
		return caseType;
	}

	public void setCaseType(CaseType caseType) {
		this.caseType = caseType;
	}

	public Integer getCl_sequence() {
		return cl_sequence;
	}

	public void setCl_sequence(Integer cl_sequence) {
		this.cl_sequence = cl_sequence;
	}
	
	

}
