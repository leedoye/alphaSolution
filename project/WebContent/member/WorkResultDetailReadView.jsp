<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="project.member.EmployeeData"%>
<%@ page import="project.workresult.WorkResult"%>
<!-- 엔티티 클래스 및 컨트롤 추가 하는곳 여기는 기본셋팅이라 건들지 말것 -->
<jsp:useBean id="memberControl" class="project.member.MemberControl" />
<jsp:useBean id="em" class="project.member.EmployeeData" />
<jsp:useBean id="nor" class="project.member.NormalMemberData" />
<jsp:useBean id="mem" class="project.member.MemberData" />

<!-- 엔티티 클래스 및 컨트롤 추가 하는곳 여기에 추가할것 -->
<jsp:useBean id="workControl"
	class="project.workresult.WorkResultControl" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 홈페이지 제목 부분  -->

<title>직원회원가입</title>




<!-- 기본셋팅 값 건들지말것 -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/bootstrap-theme.css" />
<link href="../css/innerStyle.css" type="text/css" rel="stylesheet" />
<title>Insert title here</title>
<SCRIPT type="text/javascript" src="../js/function.js"></SCRIPT>

<!-- 자신의 javascript를 추가하는 곳 -->


</head>


<body>
	<!-- 여기서부터 jsp화면 출력하는 부분 건들지 말것  header에서 부터 드래그해서 복사할것-->
	<header>
   <%
      
      mem = (project.member.MemberData) session.getAttribute("member");
      Integer o = (Integer) session.getAttribute("login");
      Integer isLogin = -1 ;
      
        //out.println(id + " " + password) ;;
      
     if ( o != null )
      {      
         isLogin = (Integer)session.getAttribute("login");
         
         if ( isLogin == 0 )
         {
            nor = (project.member.NormalMemberData) session.getAttribute("member");         
         }
         else
         {
            em = (project.member.EmployeeData) session.getAttribute("member");     
         }
      }
      
   
      
      if ( isLogin == 0 || isLogin == 1) {
         if ( isLogin == 0)
         {
               
   %>
   <div align="right">
      <table clsss="innor" id="innor">
         <tr align=center>
            <td colspan=3> <%= nor.name %> <% out.println( "( " + nor.ID + " ) 환영합니다.") ;%></td>
            
         </tr>
         <form action="../member/logout.jsp">
         <tr align=center>
            <td colspan=1 ><input class="myButton" type="submit" value="로그아웃"></td>
         </form>
         <form action="../member/NormalMemberReadView.jsp">
            <td colspan=1 ><input class="myButton" type="submit" value="마이페이지"></td>
         </form>
         </tr>
         
      </table>
   </div>
   <%      }
         else
         {
            %>
   <div align="right">
      <table clsss="innor" id="innor">
         <tr align=center>
            <td colspan=3> <%= em.name %> <% out.println( "( " + em.ID + " ) 환영합니다.") ;%></td>
            
         </tr>
         <form action="../member/logout.jsp">
         <tr align=center>
            <td colspan=1 ><input class="myButton" type="submit" value="로그아웃"></td>
         </form>
         <form action="../member/EmployeeMemberReadView.jsp">
            <td colspan=1 ><input class="myButton" type="submit" value="마이페이지"></td>
         </form>
         </tr>
         
      </table>
   </div>
            <%
         }
      }
      else {
   %>
      <div align="right">
      
      <table>
      <form action="../member/loginView2.jsp">
         <tr>
            <td>아이디</td>
            <td><input type="text" name="ID" value="s0001"></td>
            <td><input class="myButton" type="submit" value="로그인"></td>
         </tr>
         <tr>
            <td>비밀번호</td>
            <td><input type="password" name="password" value="1234"></td>
      </form>
      <form action="../member/RealNameAuthenticationTypeView.jsp">
            <td><input class="myButton" type="submit" value="회원가입"></td>
      </form>e
         </tr>
      </table>
   </div>
   <% }
   %>
   </header>

	<!-- 여기서 부터 화면에 목록을 출력함 여기는 도예가 수정할 것임 수정되면 붙여넣으면됨 -->


	<nav>
	<table width="100%">
		<tr align="center">
			<td>
				<li class="dropdown"><a href="#" id="dropdownCategoryMenu"
					data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
						color="black" size="4">운영과목 </font><i class="caret"></i></a>
					<ul class="dropdown-menu" role="menu"
						aria-labelledby="dropdownCategoryMenu">
						<li><a href="/category"><i class="fa fa-folder"></i>운영과목등록
						</a></li>
						<li><a href="/category/1"><i class="fa fa-folder"></i>
								운영과목조회</a></li>
					</ul></li>
			</td>

			<td>
				<li class="dropdown"><a href="#" id="dropdownCategoryMenu"
					data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
						color="black" size="4">개설과정</font> <i class="caret"></i></a>
					<ul class="dropdown-menu" role="menu"
						aria-labelledby="dropdownCategoryMenu">
						<li><a href="/category"><i class="fa fa-folder"></i>
								개설과정등록</a></li>
						<li><a href="/category/1"><i class="fa fa-folder"></i>
								개설과정조회</a></li>
					</ul></li>

			</td>

			<td>
				<li class="dropdown"><a href="#" id="dropdownCategoryMenu"
					data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
						color="black" size="4">수강</font> <i class="caret"></i></a>
					<ul class="dropdown-menu" role="menu"
						aria-labelledby="dropdownCategoryMenu">
						<li><a href="/category"><i class="fa fa-folder"></i> 수강신청</a></li>
						<li><a href="/category/1"><i class="fa fa-folder"></i>
								수강조회</a></li>
					</ul></li>

			</td>

			<td>
				<li class="dropdown"><a href="#" id="dropdownCategoryMenu"
					data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
						color="black" size="4">강의평가</font> <i class="caret"></i></a>
					<ul class="dropdown-menu" role="menu"
						aria-labelledby="dropdownCategoryMenu">
						<li><a href="/category"><i class="fa fa-folder"></i>
								강의평가등록</a></li>
						<li><a href="/category/1"><i class="fa fa-folder"></i>
								깡의평가조회</a></li>
					</ul></li>

			</td>

			<td>
				<li class="dropdown"><a href="#" id="dropdownCategoryMenu"
					data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
						color="black" size="4">업무평가</font><i class="caret"></i></a>
					<ul class="dropdown-menu" role="menu"
						aria-labelledby="dropdownCategoryMenu">
						<li><a href="/category"><i class="fa fa-folder"></i>
								업무평가등록</a></li>
						<li><a href="/category/1"><i class="fa fa-folder"></i>
								업무평가조회</a></li>
					</ul></li>

			</td>

			<td>
				<li class="dropdown"><a href="#" id="dropdownCategoryMenu"
					data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
						color="black" size="4">수료증 </font><i class="caret"></i></a>
					<ul class="dropdown-menu" role="menu"
						aria-labelledby="dropdownCategoryMenu">
						<li><a href="/category"><i class="fa fa-folder"></i>
								수료증발급</a></li>
						<li><a href="/category/1"><i class="fa fa-folder"></i>
								수료증발급정보조회</a></li>
					</ul></li>

			</td>

			<td>
				<li class="dropdown"><a href="#" id="dropdownCategoryMenu"
					data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
						color="black" size="4">게시판 </font><i class="caret"></i></a>
					<ul class="dropdown-menu" role="menu"
						aria-labelledby="dropdownCategoryMenu">
						<li><a href="/category"><i class="fa fa-folder"></i>
								게시판등록</a></li>
						<li><a href="/category/1"><i class="fa fa-folder"></i>
								게시판조회</a></li>
					</ul></li>

			</td>

			<td>
				<li class="dropdown"><a href="#" id="dropdownCategoryMenu"
					data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
						color="black" size="4">모임방 </font><i class="caret"></i></a>
					<ul class="dropdown-menu" role="menu"
						aria-labelledby="dropdownCategoryMenu">
						<li><a href="/category"><i class="fa fa-folder"></i>모임방등록</a></li>
						<li><a href="/category/1"><i class="fa fa-folder"></i>
								모임방조회</a></li>
					</ul></li>

			</td>

			<td>
				<li class="dropdown"><a href="#" id="dropdownCategoryMenu"
					data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
						color="black" size="4">회원</font><i class="caret"></i></a>
					<ul class="dropdown-menu" role="menu"
						aria-labelledby="dropdownCategoryMenu">
						<li><a href="/category"><i class="fa fa-folder"></i> 회원등록</a></li>
						<li><a href="/category/1"><i class="fa fa-folder"></i>
								회원조회</a></li>
					</ul></li>
			</td>
		</tr>
	</table>
	</nav>
	<!--  여기까지 화면의 목록!!!!!! -->


	<!-- 여기는 자신의 관리와 기능을 적을것 -->
	<h6>교육센터 통합 운영관리 시스템 - 업무평가관리 - 업무평가 상세조회</h6>

	<!-- 여기는 자신의 기능을 적을것-->
	<h3>업무 평가 상세조회 (업무평가)</h3>

	<!-- 여기서부터 jsp화면 출력하는 부분 건들지 말것  header에서 부터 드래그해서 복사할것-->


	<%
		String memberID = request.getParameter("memberID");
		
		
		em = memberControl.selectEmployeeData(memberID);
		ArrayList<WorkResult> w = new ArrayList<WorkResult>(); 
		w = workControl.selectWorkResult(memberID);
	%>


				<fieldset>
					<legend>회원정보</legend>
					<table width=600px>
						<tr>
							<th>성명</th>
							<%
								if ( em != null )
								{
									%>
									<td><%= em.name %></td>
									<%
								}
								else
								{
									%><td></td><%
								}
							%>
							<th>입사 일자</th>
							<%
								if ( em != null )
								{
									%>
									<td><%= em.joinedDate %></td>
									<%
								}
								else
								{
									%><td></td><%
								}
							%>
							
							<th>소속/직급</th>
							<%
								if ( em != null )
								{
									%>
									<td><%=em.centerDepartmentName %> / <%= em.positionName %></td>
									<%
								}
								else
								{
									%><td></td><%
								}
							%>
						</tr>
						<tr>
							
							
						</tr>
					</table>
					
				</fieldset>
				
	<fieldset>
		<legend>업무평가</legend>
		<table width=600px> 
			<tr align=center>
				<th>번호</th>
				<th>평가 점수</th>
				<th>업무평가결과</th>
				
			</tr>
			
				<%
					
					for( int i = 0 ; i < w.size() ; ++i )
					{
						%>
						<tr>
							<td> <%= i+1 %></td>
							<td><%= w.get(i).workResult  %></td>
							<td> <textarea cols="45" rows="2" name="workData"> <%=w.get(i).workData %>
							</textarea>
							</td>
						</tr>
						<%
					}
				%>
			
		</table>
		<div align=center>
			<input type="button" class="myButton" id="cancleBtn"
				onclick="cancleBtn()" value="뒤로가기">
		</div>
	</fieldset>
</body>
</html>