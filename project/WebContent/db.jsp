
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@page contentType="text/html;charset=UTF-8" %>
    <%@ page import="java.sql.*" %>
    <title>Title</title>
</head>
<body>
<center>
<h1> 사용자 등록 </h1>
<form method=post name=test action="#" >
    이름 : <input type=text name=name>
    이메일 : <input type=text name=email>
    <input type="submit" value="등록">
</form>

<form method=post name=test action="#" >
    이름 : <input type=text name=delName>
    이메일 : <input type=text name=delMail>
    <input type="submit" value="삭제">
</form>
</center>
<hr>
<%
//인코딩 필수
request.setCharacterEncoding("UTF-8");
//////////////////////////////DB 셋팅

String id = "root";
String passwd="1234";
String driverName = "com.mysql.jdbc.Driver";
String dbURL = "jdbc:mysql://localhost:3306/software" ;
int sCount = 0;

Connection con = null;
Statement stmt = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

//쿼리문
String dbTable = "memberdata";
String attu = "memberID"; 
String insertQuery = "INSERT INTO `" + dbTable + "` VALUES(?,?,'1','1','1','1','1','1','1','1','1')";
String me ="1";
String likeQuery = "SELECT * FROM `" + dbTable + "` WHERE name LIKE \"%" + me + "\"";
String selectQuery = "SELECT * FROM `" + dbTable +"`";

//String deleteQuery = "DELETE FROM `" + dbTable + "` WHERE `" + attu + "`= VALUES(?)";

try {

	//DB 연결
	Class.forName(driverName);
	con = DriverManager.getConnection(dbURL, id, passwd);
	out.println("MySql 데이터베이스 " + dbURL + "에 성공적으로 접속했습니다");
	
	// 쿼리문 부분
	pstmt = con.prepareStatement(insertQuery);
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	
	String delName = request.getParameter("delName");
	String delEmail = request.getParameter("delMail");
	
	if(name != null)
        pstmt.setString(1, name);
    if(email != null)
        pstmt.setString(2, email);
    stmt = con.createStatement();
    if(name != null && email != null)
     sCount = pstmt.executeUpdate();
    ///등록
    
    out.println("등록완료");
    pstmt.close();
    
    
    
    String sql= "select * from memberdata where memberID= ?";
	pstmt=con.prepareStatement(sql);
    pstmt.setString(1,delName);
	  rs=pstmt.executeQuery();
    
	if(rs.next()){ 
		  String rId=rs.getString("memberID");
		  String rPasswd=rs.getString("ID");
	      if(delName.equals(rId) && delEmail.equals(rPasswd)){
		    sql= "delete from memberdata where memberID= ? ";
		    pstmt=con.prepareStatement(sql); 
		    pstmt.setString(1,delName);
		    pstmt.executeUpdate();
		
	      }
	      else
	      {
	    	  out.println("삭제실패");
	      }
	}
	
	rs.close();
	rs = stmt.executeQuery(selectQuery);

	
	
	//Table 생성
	%> <table border=1 cellspacing=0><%
//쿼리된 부분 가져오기 하나의 커서씩 가져옴

    if ( rs != null)
    {
    	out.print("#등록 목록\n");
        out.print("<tr>");
        out.print("<td align=center>" + "번호"+  "</td>");
        out.print("<td align=center>" + "이름"+  "</td>");
        out.print("<td align=center>" + "이메일"+  "</td>");
        out.print("</tr>");
        int count = 1 ;

while ( rs.next( ) ) {
 out.println("<tr>");
 out.println("<td align=center>" + count++ + " : " + "</td>");
	 for ( int i = 1 ; i < 3 ; ++i )
	 { 
	  out.println("<td align=center>");
	  out.println(rs.getString(i));
	  out.println("</td>");
	  }//for
     out.println("</tr>");
	}
 out.println("</table>");
    }
    
	 //열은 거 닫기
}
	 

	catch (Exception e) {
		out.println("MySql 데이터베이스 univdb 접속에 문제가 있습니다. <hr>");
		out.println(e.getMessage());
		e.printStackTrace();
	}
	finally {
	    if (pstmt != null) pstmt.close();
	    //if (rs != null) rs.close();
	    if (stmt != null) stmt.close();
	    if (con != null) con.close();
}
%>
</body>
</html>