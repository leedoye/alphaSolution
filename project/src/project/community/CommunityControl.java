package project.community;

import java.sql.*;
import java.util.ArrayList;

public class CommunityControl {
 
   static final String id = "root";
   static final String passwd="1234";
   static final String driverName = "com.mysql.jdbc.Driver";
   static final String dbURL = "jdbc:mysql://localhost:3306/software" ; // ��� ��Ű�� �κ� �ٵ� ���鶧 software
   
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
         
         pstmt = con.prepareStatement(insertQuery); // prepareStatement���� �ش� sql�� �̸� �������Ѵ�.
         
         pstmt.setInt(1,community.communityNo);
         pstmt.setString(2,community.communityName);
         pstmt.setString(3,community.openingDate);
         pstmt.setString(4,community.closingDate);
         pstmt.setString(5,community.communityExplanation);
         
         pstmt.executeUpdate(); //������ �����Ѵ�.
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      System.out.println("Community ���̺� ���ο� ���ڵ带 �߰��߽��ϴ�.");        // ������ �޽��� ���
   }
   
   public void deleteCommunity(int communityNo)
   {
      if (communityNo == 0 )
         return ;
      
      try {
         
         
    	  //�������� ����
    	  //update
    	  
          System.out.println("���ӹ� ����");
          
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
               
               
               //�ٲٰ� ���� ��Ʈ����Ʈ�� set ������ ��õȰ�
               //where ��Ʈ����Ʈ�� �ٲٰ� ���� ���� ã�� ��
               //updateQuery = "update memberdata set �ٲ��ʵ�� = ? where �⺻PK = ?"; 
      
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
               System.out.println("���ӹ� ���� ����");
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
         
            //���Ǹ� �� Statement ����� 
            stmt = con.createStatement();
            
            rs = stmt.executeQuery(selectQuery); //��ȸ ��������� rs�� ����
            
            System.out.println("--- ���̺� table ���� ��ȸ ---");
            
            //rs�� ������ ������
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
