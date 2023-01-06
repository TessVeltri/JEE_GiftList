<%@page import="be.veltri.JAVABEANS.Reserve"%>
<%@page import="java.util.Base64"%>
<%@page import="be.veltri.JAVABEANS.Gift"%>
<%@page import="be.veltri.JAVABEANS.GiftList"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Info about a list</title>
<link href="/Veltri_GiftList/CSS/infoListCSS.css" rel="stylesheet"
	type="text/css">
<jsp:include page="Header.jsp"></jsp:include>
</head>
<%
String error = (String) request.getAttribute("error");
GiftList gl = (GiftList) session.getAttribute("giftList");
%>
<body>
	<h2>
		List :
		<%=gl.getNameList()%></h2>

	<h4>
		<u>Occasion :</u>
		<%=gl.getOccasion()%>
	</h4>
	<%
	if (gl.isActive()) {
	%>
	<h4><%=gl.getStatusList().toString()%></h4>
	<%
	} else {
	// false -> afficher la date
	%>
	<h4>
		<div id="getDate" style="display: block;">
			<div class="child">Date :</div>
			<div id="limitDate" class="child"><%=gl.getLimitDate()%></div>
		</div>
	</h4>
	<%
	}
	%>

	<div>
		<div align="right">
			<ul>
				<%
				if (error != null && !error.equals("")) {
				%>
				<li style="color: red;"><%=error%></li>
				<%
				}
				%>
			</ul>
		</div>
		<h3 align="center">
			<em><u>All gifts</u></em>
		</h3>
		<table>
			<tr>
				<th>Image</th>
				<th>Gift Name</th>
				<th>Gift Description</th>
				<th>Price</th>
				<th>Priority</th>
				<th>Website</th>
				<th>Status</th>
				<th>Reserves</th>
			</tr>
			<%
			for (Gift g : gl.getLstGift()) {
				String b64 = "";

				String image = "";

				if (g.getImage() != null && g.getImage().length != 0) {
					b64 = Base64.getEncoder().encodeToString(g.getImage());
					image = "data:image/" + g.getExtensionImage() + ";base64,";
					image += b64;
				}
			%>
			<tr>
				<td>
					<%
					if (g.getImage() != null && g.getImage().length != 0) {
					%> <img src="<%=image%>" width="80px" height="50px"></img> <%
 }
 %>
				</td>
				<td><%=g.getName()%></td>
				<td><%=g.getDescription()%></td>
				<td><%=g.getAveragePrice()%></td>
				<td><%=g.getPriority().toString()%></td>
				<%
				if (g.getWebsiteLink() != null) {
				%>
				<td><a href="<%=g.getWebsiteLink()%>">Link</a></td>
				<%
				} else {
				%>
				<td>-</td>
				<%
				}
				%>
				<td><%=g.getStatusGift().toString()%></td>
				<td>
					<%
					for (Reserve r : g.getLstReserve()) {
					%> <%=r.getAmount()%> €/<%=r.getUser().getFirstname()%> <%=r.getUser().getName()%>
					<%
					}
					%>
				</td>
				<td><button type="button" class="btn child"
						onclick="location.href='/Veltri_GiftList/addReserve?idGift=<%=g.getIdGift()%>'">
						<img width="65px" height="30px"
							src="/Veltri_GiftList/IMG/reserve.png"></img>
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