<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="be.veltri.JAVABEANS.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<%
	User user = (User) session.getAttribute("user");
%>
<style>
nav {
	width: 100%;
	margin: 0 auto;
	background-color: white;
	position: sticky;
	top: 0px;
}

nav ul {
	list-style-type: none;
}

nav li {
	float: left;
	width: 18%;
	text-align: center;
}

nav ul::after {
	content: "";
	display: table;
	clear: both;
}

nav a {
	display: block;
	text-decoration: none;
	color: black;
	border-bottom: 2px solid transparent;
	padding: 10px 0px;
}

nav a:hover {
	color: #bfc3c7;
	border-bottom: 2px solid #bfc3c7;
}
</style>
<body>
	<div align="right">
		<button onclick="location.href='/Veltri_GiftList/connection'">
			<img width="30px" height="30px" src="/Veltri_GiftList/IMG/logOut.png"></img>
		</button>
	</div>
	<div align="center">
		<h1>Gifts List</h1>
	</div>
	<p>Welcome <%= user.getFirstname() %></p>
	<nav>
		<ul>
			<li><a href="/Veltri_GiftList/home">Home</a></li>
			<li><a href="/Veltri_GiftList/addList">Add a new gifts list</a></li>
			<li><a href="/Veltri_GiftList/myGifts">My gifts</a></li>
			<li><a href="/Veltri_GiftList/myAccount">My account</a></li>
			<li><a href="/Veltri_GiftList/myNotifications">My notifications</a></li>
		</ul>
	</nav>


</body>
</html>