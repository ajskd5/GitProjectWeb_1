package com.sist.vo;
// hotel, guesthouse 데이터베이스 동일
/*
NO      NOT NULL NUMBER         
TITLE   NOT NULL VARCHAR2(1000) 
POSTER  NOT NULL VARCHAR2(1000) 
ADDRESS NOT NULL VARCHAR2(500)  
 */
public class SeoulHGVO {
	private int no;
	private String title, poster, address;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
