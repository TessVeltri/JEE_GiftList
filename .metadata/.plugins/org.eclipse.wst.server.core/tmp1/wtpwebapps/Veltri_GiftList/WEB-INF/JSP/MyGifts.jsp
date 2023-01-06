<%@page import="be.veltri.JAVABEANS.Reserve"%>
<%@page import="java.util.ArrayList"%>
<%@page import="be.veltri.JAVABEANS.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My gifts</title>
<link href="/Veltri_GiftList/CSS/infoListCSS.css" rel="stylesheet"
	type="text/css">
</head>
<jsp:include page="Header.jsp"></jsp:include>
<style>
body {
	margin-left: 50px;
}
</style>
<%
	User user = (User) session.getAttribute("user");
	ArrayList<Reserve> allRes = user.getLstReserve();
%>
<body>
<h1>My Gifts</h1>

<div align="center">
<table>
	<tr>
		<th>Name of the gift</th>
		<th>Amount</th>
		<th>Name of the list</th>
	</tr>
	<%
		for (Reserve r : allRes){
			
	%>
	<tr>
		<td><%= r.getGift().getName() %></td>
		<td><%= r.getAmount() %></td>
		<td><%= r.getGift().getGiftList().getNameList() %></td>
		<td><button type="button" class="btn child"
						onclick="location.href='/Veltri_GiftList/myGifts?idReserve=<%=r.getIdReserve()%>'">
						<img width="25px" height="25px"
							src="/Veltri_GiftList/IMG/delete.png"></img>
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