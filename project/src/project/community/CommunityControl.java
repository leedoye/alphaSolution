package project.community;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CommunityControl {

	static final String id = "root";
	static final String passwd = "qkqhqkqh2";
	static final String driverName = "com.mysql.jdbc.Driver";
	static final String dbURL = "jdbc:mysql://localhost:3306/software"; //디비스키마부분다들만들때software
	public static int communityNo = 0;

	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;

	String dbTable = "community";

	public CommunityControl() {
		try {

			Class.forName(driverName);
			con = DriverManager.getConnection(dbURL, id, passwd);
			
			int max = -1 ;
			 String selectQuery = "SELECT * FROM `"+ dbTable + "`";
	         
            //질의를 할 Statement 만들기 
            stmt = con.createStatement();
            
            rs = stmt.executeQuery(selectQuery); //조회 쿼리결과를 rs에 넣음
            
            //rs의 내용을 가져옴
            while (rs.next())
            {
               if ( max < rs.getInt("communityNo"))
                  max = rs.getInt("communityNo");
            }
            
            if( max == -1 )
            	communityNo = 1;
            else
            	communityNo = max;
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 실제 community 생성할 때엔 communityControl에서 추가해주고, 모임방 운영자 참가회원 추가해주고, 모임방 게시판
	 * 추가해주기
	 */
	public void insertCommunity(Community community) {
		if (community == null)
			return;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar c1 = Calendar.getInstance();
			String today = sdf.format(c1.getTime());
			
			String insertQuery = "INSERT INTO `" + dbTable + "` VALUES(?, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(insertQuery);

			pstmt.setInt(1, community.communityNo);
			pstmt.setString(2, community.communityName);
			pstmt.setString(3, today);
			pstmt.setString(4, community.closingDate);
			pstmt.setString(5, community.communityExplanation);

			pstmt.executeUpdate(); // 쿼리를 실행한다.

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Community 테이블에 새로운 레코드를 추가했습니다."); // 성공시 메시지 출력
	}

	public void updateCommunity(Community community) {

		try {

	
			if( community.closingDate != null ){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				Calendar c1 = Calendar.getInstance();
				String today = sdf.format(c1.getTime());

				String closingDateSettingQuery = "update `" + dbTable + "` set closingDate = ? "
						+ "where communityNo = ?";
				pstmt = con.prepareStatement(closingDateSettingQuery);

				pstmt.setString(1, today);
				pstmt.setInt(2, community.communityNo);
				
				int count = pstmt.executeUpdate();
				
				System.out.println(count);
				return;
			}
			
			String updateQuery = "update `" + dbTable + "` set communityName = ?, openingDate = ?"
					+ ", communityExplanation = ? where communityNo = ? ";

			pstmt = con.prepareStatement(updateQuery);
			
			pstmt.setString(1, community.communityName);
			pstmt.setString(2, community.openingDate);
			pstmt.setString(3, community.communityExplanation);
			pstmt.setInt(4, community.communityNo);
			
			pstmt.executeUpdate();
			
			System.out.println("모임방 정보 수정");
		
	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<Community> selectCommunity() {
		
		ArrayList<Community> arr = new ArrayList<Community>();
		try {
			String selectQuery = "SELECT * FROM `" + dbTable + "`";

			// 질의를 할 Statement 만들기
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectQuery); // 조회 쿼리결과를 rs에 넣음

			System.out.println("--- 테이블 " + dbTable + " 내용 조회 ---");

			// rs의 내용을 가져옴
			while (rs.next()) {
				Community community = new Community();

				community.communityNo = rs.getInt(1);
				community.communityName = rs.getString(2);
				community.openingDate = rs.getString(3);
				community.closingDate = rs.getString(4);
				community.communityExplanation = rs.getString(5);

				arr.add(community);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return arr;
	}
	
	public Community selectCommunity(String communityNo) {
		Community community = new Community();
		
		try {
			String selectQuery = "SELECT * FROM `" + dbTable + "` where communityNo = '" + communityNo + "'";
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectQuery); 

			while (rs.next()) {

				community.communityNo = rs.getInt(1);
				community.communityName = rs.getString(2);
				community.openingDate = rs.getString(3);
				community.closingDate = rs.getString(4);
				community.communityExplanation = rs.getString(5);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return community;
	}

	
	public static void main(String[] args) {
		Community community = new Community();
		
		community.communityNo = CommunityControl.communityNo;
		community.communityName = "모임방이름";
		community.openingDate = "20161210";
		community.closingDate = null;
		community.communityExplanation = "모임방 설명~~";
		
		// 모임방 추가
		CommunityControl communityControl = new CommunityControl();
		communityControl.insertCommunity(community);
		// 1. 모임방 운영자 추가해주기
		// 2. 모임방 게시판 추가해주기 
		
		// 모임방 이름 혹은 설명 수정
		community.communityExplanation = "모임방 설명6";
		communityControl.updateCommunity(community);
		
		// community 폐지 - 모임방 운영자 회원 탈퇴 시 
		community.closingDate = "폐지";
		communityControl.updateCommunity(community);
		
		// selectCommunity
		ArrayList<Community> communityList;
		communityList = communityControl.selectCommunity();
		for (Community com : communityList) {
			System.out.println(com.communityName);
		}
		
	}
}
