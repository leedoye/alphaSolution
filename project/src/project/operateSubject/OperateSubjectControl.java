package project.operateSubject;


import java.sql.*;

public class OperateSubjectControl {

   static final String id = "root";
   static final String passwd="1234";
   static final String driverName = "com.mysql.jdbc.Driver";
   static final String dbURL = "jdbc:mysql://localhost:3306/software" ; // 디비 스키마 부분 다들 만들때 software ?useSSL=false
   
   
   Connection con = null;
   Statement stmt = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
   
   String dbTable = "OperateSubject";
   // insert into db테이블명 values(?,?,?); -> ? 갯수는 디비에 애트리뷰트의 갯수와 같아야함
   
   

   
   
   public OperateSubjectControl() {
      // TODO Auto-generated constructor stub
      try {

         Class.forName(driverName); 
         con = DriverManager.getConnection(dbURL,id, passwd);
      } catch (Exception e) {
         e.printStackTrace();

      } 
   }
   
   
   public void insert(OperateSubjectData o)
   {
      try{
        
         String insertQuery = "INSERT INTO `" + dbTable + "` VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
         
         pstmt = con.prepareStatement(insertQuery); // prepareStatement에서 해당 sql을 미리 컴파일한다.
         System.out.println(o.subjectProcess);
         System.out.println(o.feature);
         System.out.println(o.lectureContent);
         System.out.println(o.lectureHour);
         System.out.println(o.purpose);
         System.out.println(o.recruitNumber);
         System.out.println(o.subjectCode);
         System.out.println(o.subjectName);
         System.out.println(o.subjectProcess);
         System.out.println(o.target);
         System.out.println(o.totalDate);
         System.out.println(o.totalHour);
         System.out.println(o.tuition);
         
         //애튜리뷰트에 값을 대입함
         pstmt.setString(1, o.subjectProcess);
         pstmt.setString(2,o.target);
         pstmt.setString(3,o.feature);
         pstmt.setInt(4,o.recruitNumber);
         pstmt.setInt(5,o.tuition);
         pstmt.setString(6,o.subjectCode);
         pstmt.setString(7,o.purpose);
         pstmt.setString(8,"없음");
         pstmt.setString(9,o.subjectName);
         pstmt.setInt(10,o.totalDate);
         pstmt.setInt(11,o.totalHour);
         pstmt.setInt(12,o.lectureHour);
         pstmt.setString(13,o.lectureContent);
         
         
         pstmt.executeUpdate(); //쿼리를 실행한다.
         System.out.println("operateSubject 테이블에 새로운 레코드를 추가했습니다.");        // 성공시 메시지 출력
       


      } catch (Exception e) {
         e.printStackTrace();
         System.out.println(e.getMessage());
      }
            

   }
   
   public void delete(OperateSubjectData o)
   {
      try {
         String deleteQuery = "delete from `"+ dbTable +"` where memberID = ? ";
         
          pstmt = con.prepareStatement(deleteQuery); 
             
          pstmt.setString(1,"test");
          
          pstmt.executeUpdate();
          
          System.out.println("삭제 성공");
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println(e.getMessage());
      }
      
   }
   
   public void update(OperateSubjectData o)
   {
      
      try {
         String updateQuery = "update from `"+ dbTable +"` set subjectName = ? where subjectCode =? ";
         
         String sql = "select subjectCode from OperateSubjectData where subjectCode = ?";

         pstmt = con.prepareStatement(sql);

         pstmt.setString(1, "A0000001");

         rs = pstmt.executeQuery();

         
         while( rs.next() )
         {

            String rId= rs.getString("subjectCode");
            
            
            if ( rId.equals("A0000001")  )
            {
               
                
               //바꾸고 싶은 애트리뷰트는 set 다음에 명시된것
               //where 애트리뷰트가 바꾸고 싶은 행을 찾는 것
               updateQuery = "update OperateSubjectData set subjectName = ? where subjectCode = ?"; 
      
               pstmt = con.prepareStatement(updateQuery);
      
               pstmt.setString(1,"asdfsd");
      
               pstmt.setString(2,"a0001");
      
               int count = pstmt.executeUpdate();
               
               System.out.println(count);
               
               System.out.println("변경완료");
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println(e.getMessage());
      }
   }
   
   public OperateSubjectData select(OperateSubjectData o)
   {
	   OperateSubjectData osd = new OperateSubjectData();
	   
      try
        {
    	 System.out.println(o.subjectCode);
    	 System.out.println(o.subjectName);
    	 System.out.println(o.subjectProcess);
    	 System.out.println(o.target);
         String selectQuery = "SELECT * FROM `" + dbTable +"` WHERE ";
         
         selectQuery = selectQuery + "subjectCode = '" + o.subjectCode + "'";
         selectQuery = selectQuery + "and subjectProcess = '" + o.subjectProcess + "'";
         selectQuery = selectQuery + "and subjectName = '" + o.subjectName + "'";
         selectQuery = selectQuery + "and target = '" + o.target + "';";
         
            //질의를 할 Statement 만들기 
            stmt = con.createStatement();
            rs = stmt.executeQuery(selectQuery); //조회 쿼리결과를 rs에 넣음
            
            while (rs.next())
            {  
                osd.subjectProcess = rs.getString(1);  
                System.out.println(rs.getString(1));
                osd.target = rs.getString(2);
                System.out.println(rs.getString(2));
                osd.feature = rs.getString(3);
                System.out.println(rs.getString(3));
                osd.recruitNumber = rs.getInt(4);
                System.out.println(rs.getString(4));
                osd.tuition = rs.getInt(5);
                System.out.println(rs.getString(5));
                osd.subjectCode = rs.getString(6);
                System.out.println(rs.getString(6));
                osd.purpose = rs.getString(7);
                System.out.println(rs.getString(7));
                osd.changeRecord = rs.getString(8);
                System.out.println(rs.getString(8));
                osd.subjectName = rs.getString(9);
                System.out.println(rs.getString(9));
                osd.totalDate = rs.getInt(10);
                System.out.println(rs.getString(10));
                osd.totalHour = rs.getInt(11);
                System.out.println(rs.getString(11));
                osd.lectureHour = rs.getInt(12);    
                System.out.println(rs.getString(12));
                osd.lectureContent = rs.getString(13);    
                System.out.println(rs.getString(13));
                //여러 데이터 타입이 가능함
                //blob도 잇음 
            }
        }
        catch (Exception e)
        {            
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
  
      return osd;
   }
   
   public OperateSubjectData selectSubjectCode(String subjectCode)
   {
	   OperateSubjectData osd = new OperateSubjectData();
	   
      try
        {
    	 
         String selectQuery = "SELECT * FROM `" + dbTable +"` WHERE ";
         
         selectQuery = selectQuery + "subjectCode = '" + subjectCode + "'";
         
         
            //질의를 할 Statement 만들기 
            stmt = con.createStatement();
            rs = stmt.executeQuery(selectQuery); //조회 쿼리결과를 rs에 넣음
            
            while (rs.next())
            {  
                osd.subjectProcess = rs.getString(1);  
                System.out.println(rs.getString(1));
                osd.target = rs.getString(2);
                System.out.println(rs.getString(2));
                osd.feature = rs.getString(3);
                System.out.println(rs.getString(3));
                osd.recruitNumber = rs.getInt(4);
                System.out.println(rs.getString(4));
                osd.tuition = rs.getInt(5);
                System.out.println(rs.getString(5));
                osd.subjectCode = rs.getString(6);
                System.out.println(rs.getString(6));
                osd.purpose = rs.getString(7);
                System.out.println(rs.getString(7));
                osd.changeRecord = rs.getString(8);
                System.out.println(rs.getString(8));
                osd.subjectName = rs.getString(9);
                System.out.println(rs.getString(9));
                osd.totalDate = rs.getInt(10);
                System.out.println(rs.getString(10));
                osd.totalHour = rs.getInt(11);
                System.out.println(rs.getString(11));
                osd.lectureHour = rs.getInt(12);    
                System.out.println(rs.getString(12));
                osd.lectureContent = rs.getString(13);    
                System.out.println(rs.getString(13));
                //여러 데이터 타입이 가능함
                //blob도 잇음 
            }
        }
        catch (Exception e)
        {            
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
  
      return osd;
   }
   
   public String selectSubjectName(String subjectName)
   {
	   String code = null;
	   
      try
        {
    	 
         String selectQuery = "SELECT * FROM `" + dbTable +"` WHERE ";
         
         selectQuery = selectQuery + "subjectName = '" + subjectName + "'";
         
         System.out.println(subjectName);
            //질의를 할 Statement 만들기 
         
            stmt = con.createStatement();
            rs = stmt.executeQuery(selectQuery); //조회 쿼리결과를 rs에 넣음
            
            while (rs.next())
            {  
            	code = rs.getString(1);  
                
            }
        }
        catch (Exception e)
        {            
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
  
      return code;
   }
   
   public void close()
   {
      if ( stmt != null ) try{stmt.close();}catch(SQLException sqle){}
      if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}            // PreparedStatement 객체 해제

      if(con != null) try{con.close();}catch(SQLException sqle){}            // Connection 해제

   }
   /*
   public static void main(String[] args){

	   OperateSubjectControl c = new OperateSubjectControl();
	   OperateSubjectData d = new OperateSubjectData();
	   d.subjectCode = "ss";
	   d.subjectProcess = "ss";
	   d.subjectName = "s";
	   d.target = "ss";
	   c.select(d);
   }
*/
   /*
   public static void main(String[] args){

	   OperateSubjectControl c = new OperateSubjectControl();
	   OperateSubjectData d = new OperateSubjectData();
	   d.feature="d";
	   d.lectureHour=1;
	   d.changeRecord="d";
	   d.purpose="d";
	   d.recruitNumber=1;
	   d.subjectCode="d";
	   d.subjectName="d";
	   d.subjectProcess="d";
	   d.target="d";
	   d.totalDate=1;
	   d.totalHour=1;
	   d.tuition=1;
	   d.lectureContent="d";
	   c.insert(d);
   }
   */
}