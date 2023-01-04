<%@page import="be.veltri.JAVABEANS.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My account</title>
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
String error = (String) request.getAttribute ("error");
%>
<body>
	<h1>My account</h1>
	<div align="right">
			<ul>
				<%if (error != null && !error.equals("")) {%>
					<li style="color: red;"><%=error%></li>
				<%}%>
			</ul>
		</div>
	<div align="center">
		<form action="myAccount" method="POST">
			<h3>
				Email adress :<%=user.getEmail()%></h3>
			<h3>
				Modify email adress : <input type="text" name="email" id="email"
					size="20" />
			</h3>
			<h3>
				Password :
				<label id="currentPassword">*************</label><button class="btn" type="button" onclick="hideCurrentPassword()">
					<img width="20px" height="20px" src="/Veltri_GiftList/IMG/eye.png"></img>
				</button>
				
			</h3>
			<h3>
				Modify password : <input type="password" name="passwordInput"
					id="passwordInput" />
				<button class="btn" type="button" onclick="hideInput()">
					<img width="20px" height="20px" src="/Veltri_GiftList/IMG/eye.png"></img>
				</button>
			</h3>
			<button type="submit">MODIFY</button>
		</form>
	</div>


</body>
<jsp:include page="Footer.jsp"></jsp:include>
<script>
	const currentPassword = <%= "'" + user.getPassword() + "'"%>
	var isHidden = true;
	
	function hideCurrentPassword(){
		if(isHidden == true)
		{
			isHidden = false;
			document.getElementById("currentPassword").innerHTML = currentPassword;
		}
		else
		{
			isHidden = true;
			document.getElementById("currentPassword").innerHTML = "*************";

		}
		
	}
	
	const password = document.querySelector("#passwordInput");
	function hideInput(){
		const type = password.getAttribute("type") === "password" ? "text" : "password"
			  passwordInput.setAttribute("type", type)
	}
	
</script>
</html>