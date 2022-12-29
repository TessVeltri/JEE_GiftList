<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="be.veltri.JAVABEANS.*" import="java.util.Base64"%>
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
GiftList gl = (GiftList) request.getAttribute("giftList");
%>
<body>
	<h2>
		List :
		<%=gl.getNameList()%></h2>
	<%
	if (gl.isActive()) {
		// true -> afficher le status
		String status = gl.getStatusList().toString();
		String check = "";
		if (status.equals("Active"))
			check = "checked";
	%>
	<div>
		Inactive <label class="switch"> <input onclick=""
			type="checkbox" <%=check%>> <span class="slider round"></span>
		</label> Active
	</div>
	<%
	} else {
	// false -> afficher la date
	%>
	<h4>

		<div id="getDate" style="display: block;">
			<div class="child">Date :</div>
			<div id="limitDate" class="child"><%=gl.getLimitDate()%></div>

			<button class="btn child" onclick="btnClick()">
				<img width="25px" height="25px" src="/Veltri_GiftList/IMG/pen.png"></img>
			</button>
		</div>
		<div id="modifyDate" style="display: none;">
			<div class="child">Date :</div>
			<input type="date" name="limitDate" id="limitDate"
				value=<%=gl.getLimitDate()%> class="child"></input>
			<button class="btn child" onclick="btnClick()">
				<img width="25px" height="25px" src="/Veltri_GiftList/IMG/valid.png"></img>
			</button>
		</div>
	</h4>
	<%
	}
	%>

	<div>
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
				<th>Reserve</th>
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
				<td><%=g.getWebsiteLink()%></td>
				<td><%=g.getStatusGift().toString()%></td>
				<td>
					<%
					for (Reserve r : g.getLstReserve()) {
					%> <%=r.getAmount()%> €/<%=r.getUser().getFirstname()%> <%=r.getUser().getName()%>
					<%
					}
					%>
				</td>
				<td><button class="btn child" onclick="">
						<img width="25px" height="25px" src="/Veltri_GiftList/IMG/pen.png"></img>
					</button></td>
				<td><button class="btn child" onclick="">
						<img width="25px" height="25px"
							src="/Veltri_GiftList/IMG/delete.png"></img>
					</button></td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
	<h3 align="center">
		<em><u>All participants</u></em>
	</h3>
	<div align="center">

		<h4>Names</h4>
		<%
		for (User u : gl.getLstParticipant()) {
		%>
		<div style="display: block;">
			<%=u.getName()%>
			<%=u.getFirstname()%>
			<button class="btn child" onclick="">
				<img width="25px" height="25px"
					src="/Veltri_GiftList/IMG/delete.png"></img>
			</button>
		</div>


		<%
		}
		%>
	</div>


</body>
<jsp:include page="Footer.jsp"></jsp:include>
<script>
	const getDate = document.getElementById("getDate");
	const modifyDate = document.getElementById("modifyDate");

	function btnClick() {
		if (getDate.style.display == 'block') {
			getDate.style.display = 'none';
			modifyDate.style.display = 'block';
		} else {
			getDate.style.display = 'block';
			modifyDate.style.display = 'none';
		}

	}
</script>
</html>