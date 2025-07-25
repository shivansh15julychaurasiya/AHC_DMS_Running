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
@Table(name = "mediation_docs")
public class MediationDocs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "judgeseq")
	@SequenceGenerator(name = "judgeseq", sequenceName = "judgeseq", allocationSize = 1)
	@Column(name = "mdn_id")
	private Long mdn_id;	
	
	@Column(name = "mdn_fd_mid")
	private Long mdn_fd_mid;
	
	@Column(name = "mdn_doc_name")
	private String mdn_doc_name;
	
	@Column(name = "mdn_doc_type")
	private String mdn_doc_type;
	
	@Column(name = "mdn_stage")
	private String mdn_stage;
	
	@Column(name = "mdn_mediation_case_no")
	private String mdn_mediation_case_no;
	
	@Column(name = "mdn_transaction_no")
	private String mdn_transaction_no;

	@Column(name = "mdn_amount")
	private Integer mdn_amount;
	
	@Column(name = "mdn_pay_date")
	private Date mdn_pay_date;
	
	@Column(name = "mdn_cr_date")
	private Date mdn_cr_date;
	
	@Column(name = "mdn_mod_date")
	private Date mdn_mod_date;
	
	

	@Column(name = "mdn_remark")
	private String mdn_remark;
	

	@Column(name = "mdn_order_no")
	private String mdn_order_no;
	
	
	@Column(name = "mdn_rec_status")
	private Integer mdn_rec_status;


	public Long getMdn_id() {
		return mdn_id;
	}


	public void setMdn_id(Long mdn_id) {
		this.mdn_id = mdn_id;
	}


	public Long getMdn_fd_mid() {
		return mdn_fd_mid;
	}


	public void setMdn_fd_mid(Long mdn_fd_mid) {
		this.mdn_fd_mid = mdn_fd_mid;
	}


	public String getMdn_doc_name() {
		return mdn_doc_name;
	}


	public void setMdn_doc_name(String mdn_doc_name) {
		this.mdn_doc_name = mdn_doc_name;
	}


	public String getMdn_doc_type() {
		return mdn_doc_type;
	}


	public void setMdn_doc_type(String mdn_doc_type) {
		this.mdn_doc_type = mdn_doc_type;
	}


	public String getMdn_stage() {
		return mdn_stage;
	}


	public void setMdn_stage(String mdn_stage) {
		this.mdn_stage = mdn_stage;
	}


	public String getMdn_mediation_case_no() {
		return mdn_mediation_case_no;
	}


	public void setMdn_mediation_case_no(String mdn_mediation_case_no) {
		this.mdn_mediation_case_no = mdn_mediation_case_no;
	}


	public String getMdn_transaction_no() {
		return mdn_transaction_no;
	}


	public void setMdn_transaction_no(String mdn_transaction_no) {
		this.mdn_transaction_no = mdn_transaction_no;
	}


	public Integer getMdn_amount() {
		return mdn_amount;
	}


	public void setMdn_amount(Integer mdn_amount) {
		this.mdn_amount = mdn_amount;
	}


	public Date getMdn_pay_date() {
		return mdn_pay_date;
	}


	public void setMdn_pay_date(Date mdn_pay_date) {
		this.mdn_pay_date = mdn_pay_date;
	}


	public Date getMdn_cr_date() {
		return mdn_cr_date;
	}


	public void setMdn_cr_date(Date mdn_cr_date) {
		this.mdn_cr_date = mdn_cr_date;
	}


	public Date getMdn_mod_date() {
		return mdn_mod_date;
	}


	public void setMdn_mod_date(Date mdn_mod_date) {
		this.mdn_mod_date = mdn_mod_date;
	}


	public String getMdn_remark() {
		return mdn_remark;
	}


	public void setMdn_remark(String mdn_remark) {
		this.mdn_remark = mdn_remark;
	}


	public String getMdn_order_no() {
		return mdn_order_no;
	}


	public void setMdn_order_no(String mdn_order_no) {
		this.mdn_order_no = mdn_order_no;
	}


	public Integer getMdn_rec_status() {
		return mdn_rec_status;
	}


	public void setMdn_rec_status(Integer mdn_rec_status) {
		this.mdn_rec_status = mdn_rec_status;
	}
	  
	
	
	 

}
