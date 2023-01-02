<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="be.veltri.JAVABEANS.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/Veltri_GiftList/CSS/addParticipantCSS.css" rel="stylesheet"
	type="text/css">
<title>Add participant</title>
</head>
<jsp:include page="Header.jsp"></jsp:include>
<%
ArrayList<User> lstUsers = (ArrayList<User>) request.getAttribute("lstUsers");
String idGiftList = (String) request.getParameter("idGiftList");
String uri = "";
uri = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/inscription?idGiftList=" + idGiftList;
%>
<body>
	<h1>Add a participant</h1>
	<div class="child">
		Name : <input type="search" name="searchName" id="searchName" value="" />
	</div>
	<div class="child">
		Firstname : <input type="search" name="searchFirstname"
			id="searchFirstname" value="" />
	</div>
	<button type="button" onclick="searchUser()">
		<img width="30px" height="30px" src="/Veltri_GiftList/IMG/search.png"></img>
	</button>
	<table>
		<tr>
			<th>Name</th>
			<th></th>
		</tr>
		<%
		if (lstUsers != null) {
			for (User u : lstUsers) {
		%>
		<tr>
			<td><%=u.getName()%> <%=u.getFirstname()%></td>
			<td>
				<button type="button"
					onclick="location.href = '/Veltri_GiftList/verifAddParticipant?idUser=<%=u.getIdUser()%>&idGiftList=<%=idGiftList%>'">
					<img width="20px" height="20px" src="/Veltri_GiftList/IMG/plus.png"></img>
				</button>
			</td>

		</tr>
		<%
		}
		}
		%>
	</table>
	
	<h4>If you don't find the person you can share this link below :</h4>
	<div>
		<h4 class="child" id="uriValue"><%=uri%></h4>
		<button class="child" onclick="copyFunction()">
			<img width="20px" height="20px" src="/Veltri_GiftList/IMG/copy.png"></img>
		</button>
	</div> 


</body>
<jsp:include page="Footer.jsp"></jsp:include>
<script>
	const name = document.getElementById("searchName");
	const firstname = document.getElementById("searchFirstname");
	const uriValue = document.getElementById("uriValue");

	function searchUser() {
		window.location.href = '/Veltri_GiftList/addParticipant?name='
				+ name.value + '&firstname=' + firstname.value + '&idGiftList=<%=idGiftList%>';
	}
	
	function copyFunction(){
		navigator.clipboard.writeText(uriValue.innerHTML);
	}
</script>
</html>