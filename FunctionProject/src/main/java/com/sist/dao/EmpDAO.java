package com.sist.dao;
import java.util.*;
import java.sql.*;
public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	// PROCEDURE
	//CallableStatement cs;
	private ResultSet rs;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	// 드라이버
	public void EepDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disConnection() {
		try {
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 기능
	// JOIN 이용
	public List<EmpVO> empListData1(){
		List<EmpVO> list = new ArrayList<EmpVO>();
		try {
			getConnection();
			String sql = "SELECT empno, ename, job, hiredate, sal, dname, loc, grade "
					+ "FROM emp, dept, salgrade "
					+ "WHERE emp.deptno = dept.deptno "
					+ "AND sal BETWEEN losal AND hisal";
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			disConnection();
		}
		
		return list;
	}
	// Function 이용
	public List<EmpVO> empListData2(){
		List<EmpVO> list = new ArrayList<EmpVO>();
		try {
			getConnection();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			disConnection();
		}
		
		return list;
	}
}
