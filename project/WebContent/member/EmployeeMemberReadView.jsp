<%@page import="project.member.EmployeeData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="memberControl" class="project.member.MemberControl" />
<jsp:useBean id="em" class="project.member.EmployeeData" />
<jsp:useBean id="nor" class="project.member.NormalMemberData" />
<jsp:useBean id="mem" class="project.member.MemberData" />

<jsp:setProperty name="nor" property="*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="../css/bootstrap-theme.css" />
<link href="../css/innerStyle.css" type="text/css" rel="stylesheet" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<SCRIPT src="../js/function.js"></SCRIPT>

</head>
<body>
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
	<h6>교육센터 통합 운영관리 시스템 - 회원정보관리 - 직원정보 조회</h6>

	<h3>직원 정보 조회</h3>

	<div >

		<div>
			<form id=EmployeeMemberReadForm action="EmployeeMemberUpdateView.jsp">
				<fieldset>
					<legend>직원정보</legend>
					<table width=600px border-top=1 cellspacing=0 cellpadding="10">
						<tr align=center>
							<th>직원번호</th>
							<td><%=em.memberID%></td>
						
						</tr>
						<tr align=center>
							<th>*로그인 아이디</th>
							<td><%=em.ID%></td>
							
						</tr>

					</table>
				</fieldset>

				<fieldset id=contact>
					<legend>연락처 정보 입력</legend>
					<table width=600px >

						<tr align=center>
							<th>*한글성명</th>
							<td><%=em.name%></td>
							<th>*영문 성명</th>
							<td><%=em.enName%></td>
						</tr>

						<tr align=center>
							<th>*성별</th>
							<td colspan=2 ><input type="radio" id=genderStatus
								name=genderStatus
								<%if (em.genderStatus == 1) {
				out.write("checked");
			}%>>
								남자 </td>

							<td><input type="radio" id=genderStatus name=genderStatus
								<%if (em.genderStatus == 0) {
				out.write("checked");
			}%>>
								여자</td>
						</tr>

						<tr align=center>
							<th>주민등록지주소</th>
							<td colspan=3><%=em.truthResidence%></td>
							<td></td>
							<td></td>
						</tr>

						<tr align=center>
							<th>*거주지주소</th>
							<td colspan=3><%=em.address%></td>
							<td></td>
							<td></td>
						</tr>
						<tr align=center>
							<th>*집 전화번호</th>
							<td><%=em.homePhoneNo%></td>
							<td></td>
							<td></td>
						</tr>
						<tr align=center>
							<th>*휴대폰 번호</th>
							<td><%=em.phoneNo%></td>
							<td></td>
							<td></td>
						</tr>
						<tr align=center>
							<th>*비상연락 전화번호번호</th>
							<td><%=em.emergencyContact%></td>
							<td></td>
							<td></td>
						</tr>

						<tr align=center>
							<th>*이메일</th>
							<td colspan=2><%=em.email%></td>
							<td></td>
							<td></td>
						</tr>

					</table>
				</fieldset>


				<fieldset>
					<legend>계좌정보입력</legend>
					<table width=600px>
						<tr align=center>
							<th>은행코드</th>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr align=center>
							<th>계좌번호</th>
							<td><input type="text" id=accountNumber></td>
							<td></td>
							<td></td>
						</tr>

					</table>
				</fieldset>

				<fieldset>
					<legend>추가정보입력</legend>


					<table width=600px >

						<tr align=center>
							<th>근무센터명</th>
							<td><%=em.centerDepartmentName%></td>
							<td></td>
							<td></td>
						</tr>
						<tr align=center>
							<th>강의과목명</th>
							<td><input type="text" name=subjectName></td>
							<td></td>
							<td></td>
						</tr>
						<tr align=center>
							<th>경력명</th>
							<th>경력 유형구분</th>
							<th>경력설명</th>
							<td></td>
						</tr>
						<tr align=center>
							<th>정보처리기사</th>
							<th>자격증</th>
							<td>-</td>
							<td></td>
						</tr>


						<tr align=center>
							<td><input type="checkbox"
							id=personalInformationCollectionUseAgreementStatus> 개인정보
						수집 이용동의 </td>
							
							<td><input type="checkbox"
							id=personalInformationOfferingAgreementStatus> 개인정보 제공동의</td>
						</tr>
					</table>
					<div>
						
					</div>
				</fieldset>
				<div align=cetner>
					<input type="button" class=myButton value="수정"
						onclick="employeeUpdateBtn()"> <input type="button"
						class=myButton value="삭제" onclick="memberDeleteBtn()">
				</div>
			</form>
		</div>
	</div>

</body>
</html>