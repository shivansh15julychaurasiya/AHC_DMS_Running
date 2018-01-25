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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name="download_report")
public class DownloadReport {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="download_report_seq")
	@SequenceGenerator(name="download_report_seq", sequenceName="download_report_seq", allocationSize=1)
	@Column(name="dr_id")
	private Long dr_id;
	
	@Column(name="dr_amount")
	private Double dr_amount;
	
	@Column(name="dr_fd_mid")
	private Long dr_fd_mid;
	
	@Column(name="dr_cr_by")
	private Long dr_cr_by;
	
	@Column(name="dr_cr_date")
	private Date dr_cr_date;
	
	@Column(name="dr_rec_status")
	private Integer dr_rec_status;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "df_dr_mid")
	@OrderBy("df_submitted_date")
	private List<DownloadFile> files;
	
	public Long getDr_id() {
		return dr_id;
	}

	public void setDr_id(Long dr_id) {
		this.dr_id = dr_id;
	}

	public Double getDr_amount() {
		return dr_amount;
	}

	public void setDr_amount(Double dr_amount) {
		this.dr_amount = dr_amount;
	}

	public Long getDr_fd_mid() {
		return dr_fd_mid;
	}

	public void setDr_fd_mid(Long dr_fd_mid) {
		this.dr_fd_mid = dr_fd_mid;
	}

	public Long getDr_cr_by() {
		return dr_cr_by;
	}

	public void setDr_cr_by(Long dr_cr_by) {
		this.dr_cr_by = dr_cr_by;
	}

	public Date getDr_cr_date() {
		return dr_cr_date;
	}

	public void setDr_cr_date(Date dr_cr_date) {
		this.dr_cr_date = dr_cr_date;
	}

	public Integer getDr_rec_status() {
		return dr_rec_status;
	}

	public void setDr_rec_status(Integer dr_rec_status) {
		this.dr_rec_status = dr_rec_status;
	}

	public List<DownloadFile> getFiles() {
		return files;
	}

	public void setFiles(List<DownloadFile> files) {
		this.files = files;
	}
	
	
}
