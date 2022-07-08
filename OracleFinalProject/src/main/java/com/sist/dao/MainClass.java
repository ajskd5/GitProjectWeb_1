package com.sist.dao;
import java.util.*;
public class MainClass {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("동/읍/면 : ");
		String dong = scan.next();
		ZipcodeDAO dao = new ZipcodeDAO();
		
		int count = dao.postFindCount(dong);
		
		if(count==0) {
			System.out.println("검색된 결과가 없습니다!!");
		} else {
			System.out.println("검색 결과 : " + count + "건");
			List<ZipcodeVO> list = dao.postFind(dong);
			for(ZipcodeVO vo : list) {
				System.out.println(vo.getZipcode() + " "
						+ vo.getAddresss());
			}
		}

	}

}
