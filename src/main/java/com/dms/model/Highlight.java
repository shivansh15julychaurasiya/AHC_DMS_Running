package com.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pdf_highlight")
public class Highlight {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="ph_um_id")
    private String userId;

    @Column(name="ph_url")
    private String pdfUrl;

    @Column(name="ph_page_number")
    private Integer pageNumber;

    @Column(name="ph_highlighted_text")
    private String highlightedText;

    @Column(name="ph_color")
    private String color;

    @Column(columnDefinition="TEXT" ,name="ph_react")
    private String rects;

    public Highlight() {
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getHighlightedText() {
		return highlightedText;
	}

	public void setHighlightedText(String highlightedText) {
		this.highlightedText = highlightedText;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRects() {
		return rects;
	}

	public void setRects(String rects) {
		this.rects = rects;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name="ph_comment")
    private String comment;
	
	
	@Override
	public String toString() {
	    return "Highlight{" +
	            "id=" + id +
	            ", userId='" + userId + '\'' +
	            ", pdfUrl='" + pdfUrl + '\'' +
	            ", pageNumber=" + pageNumber +
	            ", highlightedText='" + highlightedText + '\'' +
	            ", color='" + color + '\'' +
	            ", rects='" + rects + '\'' +
	            ", comment='" + comment + '\'' +
	            '}';
	}
	

}