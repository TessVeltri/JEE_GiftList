<%@page import="be.veltri.JAVABEANS.Notification"%>
<%@page import="be.veltri.JAVABEANS.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My notifications</title>
</head>
<style>
body {
	margin-left: 50px;
}

.btn {
	background-color: transparent;
	border: transparent;
	margin-left: 20px;
}
</style>
<jsp:include page="Header.jsp"></jsp:include>
<%
	User user = (User) session.getAttribute("user");
	ArrayList<Notification> lstNotif = user.getLstNotification();
	String boldRead = "";
%>
<body>
<h1>My notification</h1>

<div align="center">
<table>
	<tr>
		<th>Comment</th>
		<th>Read</th>
	</tr>
	<%
		for (Notification notif : lstNotif){
			if (!notif.isRead()){
				boldRead = "style=\"font-weight:bold;\""; 
			} else {
				boldRead = "style=\"color:grey;\"";
			}
	%>
	<tr>
		<td><p <%=boldRead %>><%= notif.getComment() %></p></td>
		<td><button class="btn" type="button" onclick="location.href='/Veltri_GiftList/readNotification?idNotif=<%= notif.getIdNotif()%>'">
			<img width="25px" height="25px" src="/Veltri_GiftList/IMG/valid.png"></img>
		</button></td>
	</tr>
	<%
		}
	%>
	</table>
</div>

</body>
<jsp:include page="Footer.jsp"></jsp:include>
</html>