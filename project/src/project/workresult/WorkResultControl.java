package project.workresult;

import java.sql.*;
import java.util.ArrayList;



public class WorkResultControl {
	
	static final String id = "root";
	static final String passwd="1234";
	static final String driverName = "com.mysql.jdbc.Driver";
	static final String dbURL = "jdbc:mysql://localhost:3306/software" ; // 디비 스키마 부분 다들 만들때 software
	
	public static int count = 1;
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	
	String dbTable = "workresult";
	
	// insert into db테이블명 values(?,?,?); -> ? 갯수는 디비에 애트리뷰트의 갯수와 같아야함
	
	public WorkResultControl() {
		// TODO Auto-generated constructor stub
		try {

			Class.forName(driverName); 
			con = DriverManager.getConnection(dbURL,id, passwd);
			
		} catch (Exception e) {
			e.printStackTrace();

		} 
	}
	
	public void insertWorkResult(WorkResult a)
	{
		if ( a == null ) 
			return ;
		
		try {
			
			String insertQuery = "INSERT INTO `"+ dbTable + "` VALUES(?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(insertQuery); // prepareStatement에서 해당 sql을 미리 컴파일한다.
			
			pstmt.setInt(1,a.resultNo);
			pstmt.setInt(2,a.workResult);
			pstmt.setString(3,a.workData);
			pstmt.setString(4,a.resultDate);
			pstmt.setString(5,a.memberID);
			
			
			pstmt.executeUpdate(); //쿼리를 실행한다.
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("workResult 테이블에 새로운 레코드를 추가했습니다.");        // 성공시 메시지 출력
	}
	
	public void delete(int resultNo)
	{
		if (resultNo == 0 )
			return ;
		
		try {
			
			
			String deleteQuery = "delete from `"+ dbTable +"` where resultNo = ? ";
			
			System.out.println(resultNo);
			 pstmt = con.prepareStatement(deleteQuery); 
			 pstmt.setInt(1,resultNo);
			 
			 pstmt.executeUpdate();
			 
			 System.out.println("업무평가 삭제 성공");
			 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	   
	}
	
	
	
	
	
	public ArrayList<WorkResult> selectWorkResult(String memberID)
	{
		ArrayList<WorkResult> arr = new ArrayList<WorkResult>();
		try
        {
			String selectQuery = "SELECT * FROM `"+ dbTable + "` where memberID = '" + memberID + "'";
			
            //질의를 할 Statement 만들기 
            stmt = con.createStatement();
            
            rs = stmt.executeQuery(selectQuery); //조회 쿼리결과를 rs에 넣음
            
            System.out.println("--- 테이블 student 내용 조회 ---");
            
            //rs의 내용을 가져옴
            while (rs.next())
            {
            	WorkResult a = new WorkResult();
            	a.resultNo = rs.getInt(1);
            	a.workResult = rs.getInt(2);
            	a.workData = rs.getString(3);
            	a.resultDate = rs.getString(4);
            	a.memberID = rs.getString(5);
            	
            	
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
	
	public int getCount(String memberID) {
		int count = 0 ;
		int max = -1 ;
		try
        {
			String selectQuery = "SELECT * FROM `"+ dbTable + "` where memberID = '" + memberID + "'";
			
            //질의를 할 Statement 만들기 
            stmt = con.createStatement();
            
            rs = stmt.executeQuery(selectQuery); //조회 쿼리결과를 rs에 넣음
            
            System.out.println("--- 테이블 student 내용 조회 ---");
            
            //rs의 내용을 가져옴
            while (rs.next())
            {

            	if ( max < rs.getInt("resultNo"))
            	{
            		max = rs.getInt("resultNo");
            	}
            	

            }
           
            
        }
        catch (Exception e)
        {            
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
		
		if ( max != -1 )
			count = max ;
		
		return count;
	}
	
	public static void main(String[] args)
	{
		WorkResultControl wc = new WorkResultControl();
		int count = wc.getCount("E0000001");
		
		System.out.println(count);
	}
}
