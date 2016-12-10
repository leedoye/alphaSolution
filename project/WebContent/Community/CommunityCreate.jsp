<%@page import="project.community.CommunityControl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<!-- 여기에 엔티티 클래스 및 컨트롤 클래스 추가할것 -->
<jsp:useBean id="memberControl" class="project.member.MemberControl" />
<jsp:useBean id="em" class="project.member.EmployeeData" />
<jsp:useBean id="nor" class="project.member.NormalMemberData" />
<jsp:useBean id="mem" class="project.member.MemberData" />
<jsp:setProperty name="em" property="*" />

<jsp:useBean id="community" class="project.community.Community" />
<jsp:useBean id="communityControl" class="project.community.CommunityControl" />
<jsp:useBean id="communityParticipant" class="project.community.CommunityParticipant" />
<jsp:useBean id="communityParticipantControl" class="project.community.CommunityParticipantControl" />

<jsp:setProperty name="community" property="*" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>


<body>

	<!-- 여기서부터 데이터 처리  -->

	<%
		nor = (project.member.NormalMemberData)session.getAttribute("member");
		
		community.communityNo = communityControl.communityNo; 
		community.closingDate = null;

		communityParticipant.communityNo = communityControl.communityNo;		
		communityParticipant.memberID = nor.memberID;
		out.print(nor.memberID);
		communityParticipant.participationSeparation = 0;
		
		communityControl.insertCommunity(community);
		communityParticipantControl.insertCommunityParticipant(communityParticipant);
		
		communityControl.communityNo++;
	%>

	<!-- 데이터 처리후 원래의 View로 돌려줄것 -->
	<script type="text/javascript">
	alert("등록완료");
	location.href="CommunityReadView.jsp";
	
</script>

</body>
</html>