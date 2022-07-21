package com.sist.dao;
/*
 *  Bean => 데이터를 모아서 한번에 전송 / 출력
 *  ----JSP
 *  ---- VO (Spring) , DTO(MyBatis), Entity(JPA)
 *  ---- 읽기 / 쓰기 => getter / setter
 *  ---- 데이터베이스 컬럼 매칭
 *  
 *  ------------------------------
 *  	name		읽기 / 쓰기
 *  	address		읽기 / 쓰기
 *  	tel			읽기 / 쓰기
 *  	sex			읽기 / 쓰기
 *  	age			읽기 / 쓰기
 *  	content		읽기 			
 *  ------------------------------	=> 모든 내용이 읽기/쓰기가 동시에 들어가지 않고 정할 수 있음
 *  
 */
public class MemberBean {
	private String name, address, tel, sex;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
