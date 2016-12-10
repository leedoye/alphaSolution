package project.bulletinBoard;

import java.sql.*;
import java.util.ArrayList;



public class BulletinBoardMessageControl {
	
	static final String id = "root";
	static final String passwd="qkqhqkqh2";
	static final String driverName = "com.mysql.jdbc.Driver";
	static final String dbURL = "jdbc:mysql://localhost:3306/software" ; // �뵒鍮� �뒪�궎留� 遺�遺� �떎�뱾 留뚮뱾�븣 software
	
	public static int count = 1;
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	
	String dbTable = "bulletinboardmessage";
	
	// insert into db�뀒�씠釉붾챸 values(?,?,?); -> ? 媛��닔�뒗 �뵒鍮꾩뿉 �븷�듃由щ럭�듃�쓽 媛��닔�� 媛숈븘�빞�븿
	
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
			
			pstmt = con.prepareStatement(insertQuery); // prepareStatement�뿉�꽌 �빐�떦 sql�쓣 誘몃━ 而댄뙆�씪�븳�떎.
			
			pstmt.setInt(1,a.bulletinBoardNum);
			pstmt.setInt(2,a.bulletinBoardMessageNum);
			pstmt.setString(3,a.bulletinBoardMessageType);
			pstmt.setString(4,a.bulletinBoardMessage);
			pstmt.setInt(5,a.createDate);
			pstmt.setString(6,a.name);
			pstmt.setString(7,a.attachment);
			pstmt.setString(8,a.bulletinBoardMessageContent);
			
			pstmt.executeUpdate(); //荑쇰━瑜� �떎�뻾�븳�떎.
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("BulletinBoardMessage �뀒�씠釉붿뿉 �깉濡쒖슫 �젅肄붾뱶瑜� 異붽��뻽�뒿�땲�떎.");        // �꽦怨듭떆 硫붿떆吏� 異쒕젰
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
			 
			 System.out.println("寃뚯떆湲� �궘�젣 �꽦怨�");
			 
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
					
					
					//諛붽씀怨� �떢�� �븷�듃由щ럭�듃�뒗 set �떎�쓬�뿉 紐낆떆�맂寃�
					//where �븷�듃由щ럭�듃媛� 諛붽씀怨� �떢�� �뻾�쓣 李얜뒗 寃�
					//updateQuery = "update memberdata set 諛붽��븘�뱶紐� = ? where 湲곕낯PK = ?"; 
		
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
					System.out.println("寃뚯떆�뙋 蹂�寃쎌셿猷�");
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
			
            //吏덉쓽瑜� �븷 Statement 留뚮뱾湲� 
            stmt = con.createStatement();
            
            rs = stmt.executeQuery(selectQuery); //議고쉶 荑쇰━寃곌낵瑜� rs�뿉 �꽔�쓬
            
            System.out.println("--- �뀒�씠釉� table �궡�슜 議고쉶 ---");
            
            //rs�쓽 �궡�슜�쓣 媛��졇�샂
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
			
            //吏덉쓽瑜� �븷 Statement 留뚮뱾湲� 
            stmt = con.createStatement();
            
            rs = stmt.executeQuery(selectQuery); //議고쉶 荑쇰━寃곌낵瑜� rs�뿉 �꽔�쓬
            
            System.out.println("--- �뀒�씠釉� student �궡�슜 議고쉶 ---");
            
            //rs�쓽 �궡�슜�쓣 媛��졇�샂
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
