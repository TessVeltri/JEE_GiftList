<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="be.veltri.JAVABEANS.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gift List Home</title>
</head>
<%
User user = (User) session.getAttribute("user");
ArrayList<GiftList> myLists = user.getMyLists();
ArrayList<GiftList> giftLists = user.getLstGiftList();
%>
<jsp:include page="Header.jsp"></jsp:include>
<style>
body {
	margin-left: 50px;
}

.folder {
	width: 150px;
	height: 105px;
	margin-right: 40px;
	margin-top: 30px;
	margin-bottom: 50px;
	position: relative;
	background-color: #bfc3c7;
	border-radius: 0 6px 6px 6px;
	box-shadow: 4px 4px 7px rgba(0, 0, 0, 0.59);
}

.folder:before {
	content: '';
	width: 50%;
	height: 12px;
	border-radius: 0 20px 0 0;
	background-color: #bfc3c7;
	position: absolute;
	top: -12px;
	left: 0px;
}
</style>
<body>
	<h1>Home</h1>
	
	<div align="center">
		<h2><u>Active lists</u></h2>
		<%
		int order = 0;
		for (GiftList gl : myLists) {
			
		%><button class="folder" onclick="location.href='/Veltri_GiftList/getInfoList?from=my&orderId=<%= order %>'">
			<p style="color: red; margin: 5px;">My list</p><%=gl.getNameList()%>
		  </button>
		<%
		order++;
		}
		%>
		<%
		int order2 = 0;
		for (GiftList gl : giftLists) {
		%><button class="folder" onclick="location.href='/Veltri_GiftList/infoList?from=other&orderId=<%= order %>'"><%=gl.getNameList()%></button>
		<%
		order2++;
		}
		%>
	</div>
	<div align="center">
		<h2><u>Inactive or close lists</u></h2>
	</div>
	
</body>
<jsp:include page="Footer.jsp"></jsp:include>
</html>