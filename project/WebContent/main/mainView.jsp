<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   <jsp:useBean id="memberControl" class="project.member.MemberControl" />
   <jsp:useBean id="em" class="project.member.EmployeeData" />
   <jsp:useBean id="nor" class="project.member.NormalMemberData" />
   <jsp:useBean id="mem" class="project.member.MemberData"/>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
   href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
   src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/bootstrap-theme.css" />
<link href="../css/mainStyle.css" type="text/css" rel="stylesheet" />
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
      </form>
         </tr>
      </table>
   </div>
   <% }
   %>
   </header>
   
   <nav>
   <%  
   
      if ( isLogin == 0 || isLogin == 1) {
         int type = 1;
         if(isLogin == 0){
            type = memberControl.getMemberType(nor.memberID);      
         }
         else if(isLogin == 1){
            type = memberControl.getMemberType(em.memberID);
         }
         if(type == 1){
   %>
   <table width="100%">
      <tr align="center">
         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">운영과목 </font><i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="../operateSubject/operateSubjectCreateView.jsp"><i class="fa fa-folder"></i>운영과목등록</a></li>
                  <li><a href="../operateSubject/operateSubjectReadView.jsp"><i class="fa fa-folder"></i>운영과목조회</a></li>
               </ul></li>
         </td>

         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">개설과정</font> <i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="../establishProcess/establishProcessCreateView.jsp"><i class="fa fa-folder"></i>개설과정등록</a></li>
                  <li><a href="../establishProcess/establishProcessReadView.jsp"><i class="fa fa-folder"></i>개설과정조회</a></li>
               </ul></li>

         </td>

         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">수강</font> <i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="../lecture/signUpView.jsp"><i class="fa fa-folder"></i> 수강신청</a></li>
                  <li><a href="../lecture/lectureCancelView.jsp"><i class="fa fa-folder"></i>수강취소</a></li>
                  <li><a href="../lecture/lectureReadView.jsp"><i class="fa fa-folder"></i>수강조회</a></li>
                  <li><a href="../lecture/payTuitionView.jsp"><i class="fa fa-folder"></i>수강료납부</a></li>
               </ul></li>

         </td>
         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">출결</font> <i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="../attendanceRecord/attendanceRecordCreateView.jsp"><i class="fa fa-folder"></i>출결등록</a></li>
                  <li><a href="../attendanceRecord/attendanceRecordReadView.jsp"><i class="fa fa-folder"></i>출결조회</a></li>
               </ul></li>

         </td>
         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">업무평가</font> <i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="../member/WorkResultCreateView.jsp"><i class="fa fa-folder"></i>업무평가등록</a></li>
                  <li><a href="../member/WorkResultReadView.jsp.jsp"><i class="fa fa-folder"></i>업무평가조회</a></li>
               </ul></li>

         </td>
         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">강의평가</font> <i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="lectureEvaluationQuestionCreateView.jsp"><i class="fa fa-folder"></i>강의 평가 등록</a></li>
                  <li><a href="../lectureEvaluation/lectureEvaluationQuestionReadView.jsp"><i class="fa fa-folder"></i>강의 평가 조회</a></li>
                  <li><a href="../lectureEvaluation/fixedChoiceQuestionnaireCreateView.jsp"><i class="fa fa-folder"></i>답안 보기 등록</a></li>
                  <li><a href="../lectureEvaluation/fixedChoiceQuestionnaireReadView.jsp"><i class="fa fa-folder"></i>답안 보기 조회</a></li>
                  <li><a href="../lectureEvaluation/evaluationQuestionConstructionCreateView.jsp"><i class="fa fa-folder"></i>평가 질문 구성 등록</a></li>
                  <li><a href="../lectureEvaluation/evaluationQuestionConstructionReadView.jsp"><i class="fa fa-folder"></i>평가 질문 구성 조회</a></li>
                  <li><a href="../lectureEvaluationResult/lectureEvaluationResultCreateView.jsp"><i class="fa fa-folder"></i>강의평가결과 등록</a></li>
                  <li><a href="../lectureEvaluationResult/lectureEvaluationResultReadView.jsp"><i class="fa fa-folder"></i>강의평가결과 조회</a></li>
               </ul></li>

         </td>

         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">업무평가</font><i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="/category"><i class="fa fa-folder"></i>업무평가등록</a></li>
                  <li><a href="/category/1"><i class="fa fa-folder"></i>업무평가조회</a></li>
               </ul></li>

         </td>

         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">수료증 </font><i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="/category"><i class="fa fa-folder"></i>수료증발급</a></li>
                  <li><a href="/category/1"><i class="fa fa-folder"></i>수료증발급정보조회</a></li>
               </ul></li>

         </td>

         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">게시판 </font><i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="/category"><i class="fa fa-folder"></i>게시판등록</a></li>
                  <li><a href="/category/1"><i class="fa fa-folder"></i>게시판조회</a></li>
               </ul></li>

         </td>

         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">모임방 </font><i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="/category"><i class="fa fa-folder"></i>모임방등록</a></li>
                  <li><a href="/category/1"><i class="fa fa-folder"></i>모임방조회</a></li>
               </ul></li>

         </td>
      </tr>
      
   </table>
   <%}
         else if(type == 0){
   %>         
      <table width="100%">
      <tr align="center">
         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">개설과정</font> <i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="../establishProcess/establishProcessReadView.jsp"><i class="fa fa-folder"></i>개설과정조회</a></li>
               </ul></li>

         </td>

         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">수강</font> <i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="../lecture/signUpView.jsp"><i class="fa fa-folder"></i> 수강신청</a></li>
                  <li><a href="../lecture/lectureCancelView.jsp"><i class="fa fa-folder"></i>수강취소</a></li>
                  <li><a href="../lecture/lectureReadView.jsp"><i class="fa fa-folder"></i>수강조회</a></li>
                  <li><a href="../lecture/payTuitionView.jsp"><i class="fa fa-folder"></i>수강료납부</a></li>
               </ul></li>

         </td>
         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">강의평가</font> <i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="../lectureEvaluationResult/lectureEvaluationResultCreateView.jsp"><i class="fa fa-folder"></i>강의평가결과 등록</a></li>
                  <li><a href="../lectureEvaluationResult/lectureEvaluationResultReadView.jsp"><i class="fa fa-folder"></i>강의평가결과 조회</a></li>
               </ul></li>

         </td>

         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">수료증 </font><i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="/category"><i class="fa fa-folder"></i>수료증발급</a></li>
                  <li><a href="/category/1"><i class="fa fa-folder"></i>수료증발급정보조회</a></li>
               </ul></li>

         </td>

         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">게시판 </font><i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="/category"><i class="fa fa-folder"></i>게시판등록</a></li>
                  <li><a href="/category/1"><i class="fa fa-folder"></i>게시판조회</a></li>
               </ul></li>

         </td>

         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">모임방 </font><i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="/category"><i class="fa fa-folder"></i>모임방등록</a></li>
                  <li><a href="/category/1"><i class="fa fa-folder"></i>모임방조회</a></li>
               </ul></li>

         </td>
      </tr>
      
   </table>
   <%      } 
      }
   else {%>
      <table width="100%">
      <tr align="center">
         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">개설과정</font> <i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="../establishProcess/establishProcessReadView.jsp"><i class="fa fa-folder"></i>개설과정조회</a></li>
               </ul></li>

         </td>
   
         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">게시판 </font><i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="/category/1"><i class="fa fa-folder"></i>게시판조회</a></li>
               </ul></li>

         </td>

         <td>
            <li class="dropdown"><a href="#" id="dropdownCategoryMenu"
               data-toggle="dropdown"><i class="fa fa-folder-open"></i><font
                  color="black" size="4">모임방 </font><i class="caret"></i></a>
               <ul class="dropdown-menu" role="menu"
                  aria-labelledby="dropdownCategoryMenu">
                  <li><a href="/category/1"><i class="fa fa-folder"></i>모임방조회</a></li>
               </ul></li>

         </td>
      </tr>
      
   </table>
   
   <%} %>
   </nav> </header>
   <section> </section>


</body>
</html>