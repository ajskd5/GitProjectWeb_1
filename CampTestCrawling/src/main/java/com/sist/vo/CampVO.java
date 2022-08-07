package com.sist.vo;
/*
NO             NUMBER         
TITLE          VARCHAR2(500)  
POSTER         VARCHAR2(2000) 
ADDR           VARCHAR2(500)  
TEL            VARCHAR2(100)  
ENVIRONMENT    VARCHAR2(1000) 
TYPE           VARCHAR2(1000) 
M_PERIOD       VARCHAR2(500)  
M_DAY          VARCHAR2(500)  
LINK           VARCHAR2(2000) 
IMGS           CLOB           
CONTENT        CLOB           
INFO           CLOB           
GALLERY        CLOB           

 */
public class CampVO {
	private int no;
	private String title, poster, info, imgs, content, info2, etcinfo, gallery;
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getInfo2() {
		return info2;
	}
	public void setInfo2(String info2) {
		this.info2 = info2;
	}
	public String getEtcinfo() {
		return etcinfo;
	}
	public void setEtcinfo(String etcinfo) {
		this.etcinfo = etcinfo;
	}
	public String getGallery() {
		return gallery;
	}
	public void setGallery(String gallery) {
		this.gallery = gallery;
	}
	
}
