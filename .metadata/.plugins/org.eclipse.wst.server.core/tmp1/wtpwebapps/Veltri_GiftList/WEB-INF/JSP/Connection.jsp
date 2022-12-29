<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="be.veltri.JAVABEANS.User" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gift List Website</title>
</head>
<style>
body {
	width: 100%;
	height: 100%;
	background-image: url("/Veltri_GiftList/IMG/background.jpg");
	background-size: cover;
}

table {
	background-color: white;
}

.center {
	margin: 0;
	position: absolute;
	top: 40%;
	left: 50%;
	-ms-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
}

.smallText {
	font-size: 12px;
}
</style>
<%
	ArrayList<String> errors = (ArrayList<String>)request.getAttribute("errorsConnect");
	User user = (User) session.getAttribute("userConnect");
	if (user == null) {
		user = new User("", "", "", "");
	}
%>
<body>
	<div class="center">
		<form action="authentication" method="POST">
			<h1>Welcome to GiftList Website</h1>
			<table border="1" cellspacing="0" cellpadding="5" align="center">
				<tr>
					<td>Email :</td>
					<td><input type="text" name="email" id="email" value="<%= user.getEmail() %>"
						size="20" /></td>
				</tr>
				<tr>
					<td>Password :</td>
					<td><input type="password" name="password" id="password"
						value="<%= user.getPassword() %>" size="20" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						name="submit" id="submit" value="Log in" /></td>
				</tr>
			</table>
			<br>
			<p align="center">
				Not registered? click <a href="/Veltri_GiftList/inscription">here</a>
				to create your account
			</p>
			<p class="smallText" align="center">By Veltri Tess</p>
			<ul>
			<%
				if (errors != null){
					for (String err : errors){
						%>
							<li style="color:red;"><%=err%> </li>
						<%
					}
				}
				
			%></ul>
		</form>
	</div>

</body>
</html>