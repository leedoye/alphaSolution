package project.bulletinBoard;

import java.sql.*;
import java.util.ArrayList;



public class BulletinBoardMessageControl {
	
	static final String id = "root";
	static final String passwd="1234";
	static final String driverName = "com.mysql.jdbc.Driver";
	static final String dbURL = "jdbc:mysql://localhost:3306/software" ; // 디비 스키마 부분 다들 만들때 software
	
	public static int count = 1;
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	
	String dbTable = "bulletinboardmessage";
	
	// insert into db테이블명 values(?,?,?); -> ? 갯수는 디비에 애트리뷰트의 갯수와 같아야함
	
	public BulletinBoardMessageControl() {
		// TODO Auto-generated constructor stub
		try {

			Class.forName(driverName); 
			con = DriverManager.getConnection(dbURL,id, passwd);
			
		} catch (Exception e) {
			e.printStackTrace();

		} 
	}
	
	public void insertBulletinBoardMessage(BulletinBoardMessage a)
	{
		if ( a == null ) 
			return ;
		
		try {
			
			String insertQuery = "INSERT INTO `"+ dbTable + "` VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(insertQuery); // prepareStatement에서 해당 sql을 미리 컴파일한다.
			
			pstmt.setInt(1,a.bulletinBoardNum);
			pstmt.setInt(2,a.bulletinBoardMessageNum);
			pstmt.setString(3,a.bulletinBoardMessageType);
			pstmt.setString(4,a.bulletinBoardMessage);
			pstmt.setInt(5,a.createDate);
			pstmt.setString(6,a.name);
			pstmt.setString(7,a.attachment);
			pstmt.setString(8,a.bulletinBoardMessageContent);
			
			pstmt.executeUpdate(); //쿼리를 실행한다.
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("BulletinBoardMessage 테이블에 새로운 레코드를 추가했습니다.");        // 성공시 메시지 출력
	}
	
	public void delete(int bulletinBoardMessageNum)
	{
		if (bulletinBoardMessageNum == 0 )
			return ;
		
		try {
			
			
			String deleteQuery = "delete from `"+ dbTable +"` where bulletinBoardMessageNum = ? ";
			
			System.out.println(bulletinBoardMessageNum);
			 pstmt = con.prepareStatement(deleteQuery); 
			 pstmt.setInt(1,bulletinBoardMessageNum);
			 
			 pstmt.executeUpdate();
			 
			 System.out.println("게시글 삭제 성공");
			 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	   
	}
	
	public void updateBulletinBoard(BulletinBoard m)
	{
		
		try {
			
			String sql = "select bulletinboardnum from `" + dbTable +"` where bulletinboardnum = ?";
			
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, m.bulletinBoardNum);

			
			rs = pstmt.executeQuery();

			
			if( rs.next() )
			{
				
				int rId= rs.getInt("bulletinBoardNum");
				
				
				if ( rId == (m.bulletinBoardNum)  )
				{
					
					
					//바꾸고 싶은 애트리뷰트는 set 다음에 명시된것
					//where 애트리뷰트가 바꾸고 싶은 행을 찾는 것
					//updateQuery = "update memberdata set 바꿀필드명 = ? where 기본PK = ?"; 
		
					String updateQuery = "update `"+ dbTable +"` set bulletinBoard = ?"
							+ ", openingDate = ?, closingDate = ?, kindOfbulletinBoard = ?, creator = ?"
							+ ", closer = ? where bulletinboardnum = ? ";
					
					pstmt = con.prepareStatement(updateQuery);
		
					
					
					pstmt.setString(1,m.bulletinBoard);
					pstmt.setInt(2,m.openingDate);
					pstmt.setInt(3,m.closingDate);
					pstmt.setString(4,m.kindOfbulletinBoard);
					pstmt.setString(5,m.creator);
					pstmt.setString(6,m.closer);
					pstmt.setInt(7,m.bulletinBoardNum);
		
								
					int count = pstmt.executeUpdate();
					
					count = pstmt.executeUpdate();
					System.out.println(count);
					System.out.println("게시판 변경완료");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<BulletinBoard> selectBulletinBoard()
	{
		ArrayList<BulletinBoard> arr = new ArrayList<BulletinBoard>();
		try
        {
			String selectQuery = "SELECT * FROM `"+ dbTable +"`" ;
			
            //질의를 할 Statement 만들기 
            stmt = con.createStatement();
            
            rs = stmt.executeQuery(selectQuery); //조회 쿼리결과를 rs에 넣음
            
            System.out.println("--- 테이블 table 내용 조회 ---");
            
            //rs의 내용을 가져옴
            while (rs.next())
            {
            	
            	
            	BulletinBoard a = new BulletinBoard() ;
            	
            	a.bulletinBoardNum = rs.getInt(1);
            	a.bulletinBoard = rs.getString(2);
            	a.openingDate = rs.getInt(3);
            	a.closingDate = rs.getInt(4);
            	a.kindOfbulletinBoard = rs.getString(5);
            	a.creator = rs.getString(6);
            	a.closer = rs.getString(7);

            	arr.add(a);
            }
           
            
        }
        catch (Exception e)
        {            
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
			
		return arr;
	}
	
	public BulletinBoard selectBulletinBoard(String bulletinBoardNum)
	{
		BulletinBoard a = new BulletinBoard();
		try
        {
			String selectQuery = "SELECT * FROM `"+ dbTable + "` where bulletinboardbum = '" + bulletinBoardNum + "'";
			
            //질의를 할 Statement 만들기 
            stmt = con.createStatement();
            
            rs = stmt.executeQuery(selectQuery); //조회 쿼리결과를 rs에 넣음
            
            System.out.println("--- 테이블 student 내용 조회 ---");
            
            //rs의 내용을 가져옴
            if (rs.next())
            {
            	
            	
            	
            	
            	a.bulletinBoardNum = rs.getInt(1);
            	a.bulletinBoard = rs.getString(2);
            	a.openingDate = rs.getInt(3);
            	a.closingDate = rs.getInt(4);
            	a.kindOfbulletinBoard = rs.getString(5);
            	a.creator = rs.getString(6);
            	a.closer = rs.getString(7);

            	
            }
           
            
        }
        catch (Exception e)
        {            
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
			
		return a;
	}
}
