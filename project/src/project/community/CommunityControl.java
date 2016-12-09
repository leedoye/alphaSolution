package project.community;

import java.sql.*;
import java.util.ArrayList;

public class CommunityControl {
 
   static final String id = "root";
   static final String passwd="1234";
   static final String driverName = "com.mysql.jdbc.Driver";
   static final String dbURL = "jdbc:mysql://localhost:3306/software" ; // 디비 스키마 부분 다들 만들때 software
   
   public static int count = 1;
   
   Connection con = null;
   Statement stmt = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
   
   String dbTable = "bulletinboard";
   
   public CommunityControl(){
	   try{
		   
		   Class.forName(driverName);
		   con = DriverManager.getConnection(dbURL,id, passwd);
		   
	   }catch (Exception e){
		   e.printStackTrace();
	   }
   }
   
   public void insertCommunity(Community community)
   {
      if ( community == null ) 
         return ;
      
      try {
         
         String insertQuery = "INSERT INTO `"+ dbTable + "` VALUES(?, ?, ?, ?, ?, ?, ?)";
         
         pstmt = con.prepareStatement(insertQuery); // prepareStatement에서 해당 sql을 미리 컴파일한다.
         
         pstmt.setInt(1,community.communityNo);
         pstmt.setString(2,community.communityName);
         pstmt.setString(3,community.openingDate);
         pstmt.setString(4,community.closingDate);
         pstmt.setString(5,community.communityExplanation);
         
         pstmt.executeUpdate(); //쿼리를 실행한다.
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      System.out.println("Community 테이블에 새로운 레코드를 추가했습니다.");        // 성공시 메시지 출력
   }
   
   public void deleteCommunity(int communityNo)
   {
      if (communityNo == 0 )
         return ;
      
      try {
         
         
    	  //폐지일자 설정
    	  //update
    	  
          System.out.println("모임방 폐지");
          
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println(e.getMessage());
      }
      
   }
   
   public void updateCommunity(Community community)
   {
      
      try {
         
         String sql = "select communityNo from `" + dbTable +"` where communityNo = ?";
         
         pstmt = con.prepareStatement(sql);

         pstmt.setInt(1, community.communityNo);

         
         rs = pstmt.executeQuery();

         
         if( rs.next() )
         {
            
            int rId= rs.getInt("communityNo");
            
            
            if ( rId == (community.communityNo)  )
            {
               
               
               //바꾸고 싶은 애트리뷰트는 set 다음에 명시된것
               //where 애트리뷰트가 바꾸고 싶은 행을 찾는 것
               //updateQuery = "update memberdata set 바꿀필드명 = ? where 기본PK = ?"; 
      
               String updateQuery = "update `"+ dbTable +"` set communityNo = ?"
                     + ", communityName = ?, openingDate = ?, closingDate = ?, communityExplanation = ?";
               
               pstmt = con.prepareStatement(updateQuery);
      
               pstmt.setInt(1,community.communityNo);
               pstmt.setString(2,community.communityName);
               pstmt.setString(3,community.openingDate);
               pstmt.setString(4,community.closingDate);
               pstmt.setString(5,community.communityExplanation);
                        
               int count = pstmt.executeUpdate();
               
               count = pstmt.executeUpdate();
               System.out.println(count);
               System.out.println("모임방 정보 수정");
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println(e.getMessage());
      }
   }
   
   public ArrayList<Community> selectCommunity()
   {
      ArrayList<Community> arr = new ArrayList<Community>();
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
            	Community a = new Community() ;
               
				a.communityNo = rs.getInt(1);
				a.communityName = rs.getString(2);
				a.openingDate = rs.getString(3);
				a.closingDate = rs.getString(4);
				a.communityExplanation = rs.getString(5);
				
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
   
   
   
}
