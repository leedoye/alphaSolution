package project.community;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CommunityParticipantControl {

	static final String id = "root";
	static final String passwd = "qkqhqkqh2";
	static final String driverName = "com.mysql.jdbc.Driver";
	static final String dbURL = "jdbc:mysql://localhost:3306/software"; //디비스키마부분다들만들때software

	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;

	String dbTable = "communityparticipant";

	public CommunityParticipantControl() {
		try {

			Class.forName(driverName);
			con = DriverManager.getConnection(dbURL, id, passwd);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertCommunityParticipant(CommunityParticipant participant) {
		if (participant == null)
			return;

		try {
			String insertQuery = "INSERT INTO `" + dbTable + "` VALUES(?, ?, ?)";

			pstmt = con.prepareStatement(insertQuery);

			pstmt.setInt(1, participant.communityNo);
			pstmt.setString(2, participant.memberID);
			pstmt.setInt(3, participant.participationSeparation);

			pstmt.executeUpdate(); // 쿼리를 실행한다.

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("CommunityParticipant 테이블에 새로운 레코드를 추가했습니다."); // 성공시 메시지 출력
	}

	public void updateCommunityParticipant(CommunityParticipant participant) {

		// 모임방 운영자가 모임방 참여신청자를 모임방 참여회원으로 등록하는 경우 - 모임방 참여회원(1)
		// 모임방 참여회원이 모임방을 탈퇴하는 경우 - 모임방 탈퇴회원 (2)

		// 모임방 운영자는 참여구분 변경 불가
		if( participant.participationSeparation == 0 )
			return;
		
		try {

			String updateQuery = "update `" + dbTable + "` set participationSeparation = ?"
					+ " where communityNo = ? and memberID = ?";

			pstmt = con.prepareStatement(updateQuery);

			pstmt.setInt(1, participant.participationSeparation);
			pstmt.setInt(2, participant.communityNo);
			pstmt.setString(3, participant.memberID);

			pstmt.executeUpdate();
			
			System.out.println("모임방 회원 정보 수정");
		
	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<CommunityParticipant> selectCommunityParticipants(int communityNo) {
		
		ArrayList<CommunityParticipant> arr = new ArrayList<CommunityParticipant>();
		try {
			String selectQuery = "SELECT * FROM `" + dbTable + "` where communityNo = " + communityNo;

			// 질의를 할 Statement 만들기
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectQuery); // 조회 쿼리결과를 rs에 넣음

			System.out.println("--- 테이블 " + dbTable + " 내용 조회 ---");

			// rs의 내용을 가져옴
			while (rs.next()) {
				CommunityParticipant participant = new CommunityParticipant();

				participant.communityNo = rs.getInt(1);
				participant.memberID = rs.getString(2);
				participant.participationSeparation = rs.getInt(3);

				arr.add(participant);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return arr;
	}
	
	public CommunityParticipant selectCommunityOperator(int communityNo){
		CommunityParticipant participant = new CommunityParticipant();
		
		try {
			System.out.println(communityNo);
			String selectQuery = "SELECT * FROM `" + dbTable + "` where communityNo = " + communityNo
					+ " and participationSeparation = 0";

			// 질의를 할 Statement 만들기
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectQuery); // 조회 쿼리결과를 rs에 넣음

			// rs의 내용을 가져옴
			if (rs.next()) {

				participant.communityNo = rs.getInt(1);
				participant.memberID = rs.getString(2);
				participant.participationSeparation = rs.getInt(3);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return participant;
	}
	
	public static void main(String[] args) {
		CommunityParticipantControl participantControl = new CommunityParticipantControl();
		
		CommunityParticipant participant = new CommunityParticipant();
		

		/*
		// community 가 생성될 때 insertCommunity와 함께 실행되어야 함
		participant.communityNo = 1;
		participant.memberID = "S0000001"; // 현재 로그인된 회원의 회원번호를 받아와야 함
		participant.participationSeparation = 0;
		participantControl.insertCommunityParticipant(participant);
		
		// community에 참여신청 했을 때 
		participant.memberID = "S0000002";
		participant.participationSeparation = 3;
		participantControl.insertCommunityParticipant(participant);
		
		// community의 운영자가 해당 회원을 참여회원으로 등록했을 때
		participant.participationSeparation = 1;
		participantControl.updateCommunityParticipant(participant);
		
		// community에 로그인 된 회원이 탈퇴 했을 때
		participant.participationSeparation = 2;
		participantControl.updateCommunityParticipant(participant);
		
		// community참여회원 조회 시
		ArrayList<CommunityParticipant> participants;
		participants = participantControl.selectCommunityParticipants(1);
		for (CommunityParticipant communityParticipant : participants)
			System.out.println(communityParticipant.communityNo + communityParticipant.memberID + communityParticipant.participationSeparation );
		*/
		// communityparticipant
		participant = participantControl.selectCommunityOperator(1);
		System.out.println(participant.memberID);
	}
}
