package com.tcscodebuffer.bookcatalog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOK_TBL")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int titleid;
	private String title;
	private String authname;
	private String isbn;
	
	@JsonFormat(pattern="DD-MM-YYYY", shape = Shape.STRING)
	@Column(name="pubdate")
	private String pubdate;
	
	public int getTitleid() {
		return titleid;
	}
	public void setTitleid(int titleid) {
		this.titleid = titleid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthname() {
		return authname;
	}
	public void setAuthname(String authname) {
		this.authname = authname;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	
	@Override
	public String toString() {
		return "Book [titleid=" + titleid + ", title=" + title + ", authname=" + authname + ", isbn=" + isbn
				+ ", pubdate=" + pubdate + "]";
	}	
}
