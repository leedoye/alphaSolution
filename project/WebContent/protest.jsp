<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    
    
    <!-- 여기에 엔티티 클래스 및 컨트롤 클래스 추가할것 -->
  
   <jsp:useBean id="memberControl" class="project.member.MemberControl" />
   <jsp:useBean id="em" class="project.member.EmployeeData" />
   <jsp:useBean id="nor" class="project.member.NormalMemberData" />
   <jsp:useBean id="mem" class="project.member.MemberData"/>
   <!-- 여기는 set해주는 개념임 자신이 jsp에서 받은 데이터를 클래스에 set해주려는 엔티티 클래스에만 setProperty 설정할걳 -->
   <jsp:setProperty name="em" property="*" />
   
   
   
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<%

	String password = request.getParameter("password");
	project.member.EmployeeData newEm = new project.member.EmployeeData();	

	newEm = (project.member.EmployeeData) session.getAttribute("member");
	
	out.println("password1" + newEm.password);
	out.println("password2" + password);
	
	if ( !password.equals(""))
		newEm.password = password ;
	
	newEm.name = em.name ;
	newEm.address = em.address ;
	newEm.email = em.email ;
	newEm.phoneNo = em.phoneNo ;
	newEm.centerDepartmentName = em.centerDepartmentName;
	newEm.emergencyContact = em.emergencyContact ;
	newEm.enName = em.enName;
	newEm.genderStatus = em.genderStatus;
	newEm.homePhoneNo = em.homePhoneNo;
	newEm.truthResidence = em.truthResidence;
	newEm.positionName = em.positionName;
	
	memberControl.updateEmployee(newEm);
	
	session.setAttribute("member", newEm);

%>

<script type="text/javascript">
	alert("수정완료");
	location.href="EmployeeMemberUpdateView.jsp";
</script>

</body>
</html>