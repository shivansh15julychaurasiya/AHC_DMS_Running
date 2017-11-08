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
@Table(name="order_report_data")
public class OrderReport {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="ord_seq")
	@SequenceGenerator(name="ord_seq", sequenceName="ord_seq", allocationSize=1)
	@Column(name="ord_id")
	private Long ord_id;
	
	@Column(name="ord_remark")
	private  String ord_remark;
	
	@Column(name="ord_created")
	private Date ord_created;
	
	@Column(name="ord_fd_mid")
	private Long ord_fd_mid;
	
	@Column(name="ord_sd_mid")
	private Long ord_sd_mid;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ord_sd_mid",insertable = false, updatable = false)
	private SubDocument subDocument;

	public Long getOrd_id() {
		return ord_id;
	}

	public void setOrd_id(Long ord_id) {
		this.ord_id = ord_id;
	}

	public String getOrd_remark() {
		return ord_remark;
	}

	public void setOrd_remark(String ord_remark) {
		this.ord_remark = ord_remark;
	}

	public Date getOrd_created() {
		return ord_created;
	}

	public void setOrd_created(Date ord_created) {
		this.ord_created = ord_created;
	}

	public Long getOrd_fd_mid() {
		return ord_fd_mid;
	}

	public void setOrd_fd_mid(Long ord_fd_mid) {
		this.ord_fd_mid = ord_fd_mid;
	}

	public Long getOrd_sd_mid() {
		return ord_sd_mid;
	}

	public void setOrd_sd_mid(Long ord_sd_mid) {
		this.ord_sd_mid = ord_sd_mid;
	}

	public SubDocument getSubDocument() {
		return subDocument;
	}

	public void setSubDocument(SubDocument subDocument) {
		this.subDocument = subDocument;
	}
	
	
}
