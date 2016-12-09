<%@ page contentType="text/html;charset=EUC-KR" %>
<% request.setCharacterEncoding("EUC-KR"); %>
<jsp:useBean id="info" class="contact.ContactInfo" />
<jsp:setProperty name="info" property="*" />
<html>
<head><title>setProperty 테스트</title></head>
<body>
<h3>setProperty 테스트</h3>
이름 : <%= info.getMemberID() %>
<p>
Email : <%= info.getEmail() %><p>
Email : <%= info.getId() %><p>
Email : <%= info.getEmail() %>
</body>
</html>