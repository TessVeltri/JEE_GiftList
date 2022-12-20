<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="be.veltri.JAVABEANS.*"
		 import="java.util.Base64"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Info about a list</title>
<jsp:include page="Header.jsp"></jsp:include>
<style>
body {
	margin-left: 50px;
}

.switch {
	position: relative;
	display: inline-block;
	width: 50px;
	height: 20px;
}

.switch input {
	opacity: 0;
	width: 0;
	height: 0;
}

.slider {
	position: absolute;
	cursor: pointer;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: #ccc;
	-webkit-transition: .4s;
	transition: .4s;
}

.slider:before {
	position: absolute;
	content: "";
	height: 12px;
	width: 15px;
	left: 4px;
	bottom: 4px;
	background-color: white;
	-webkit-transition: .4s;
	transition: .4s;
}

input:checked+.slider {
	background-color: #2196F3;
}

input:focus+.slider {
	box-shadow: 0 0 1px #2196F3;
}

input:checked+.slider:before {
	-webkit-transform: translateX(26px);
	-ms-transform: translateX(26px);
	transform: translateX(26px);
}

.slider.round {
	border-radius: 34px;
}

.slider.round:before {
	border-radius: 50%;
}

.btn {
	background-color: transparent;
	border: transparent;
	margin-left: 20px;
}

.child {
	display: inline-block;
	vertical-align: middle;
}
table {
  border-collapse: collapse;
}
</style>
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
		<div class="child">Date :</div>
		<div id="getDate" style="display: block;">
			<div id="limitDate" class="child"><%=gl.getLimitDate()%></div>

			<button class="btn child" onclick="btnClick()">
				<img width="25px" height="25px" src="/Veltri_GiftList/IMG/pen.png"></img>
			</button>
		</div>
		<div id="modifyDate" style="display: none;">
			<input type="date" value=<%=gl.getLimitDate()%> class="child"></input>
			<button class="btn child" onclick="btnClick()">
				<img width="25px" height="25px" src="/Veltri_GiftList/IMG/valid.png"></img>
			</button>
		</div>
	</h4>
	<%
	}
	%>
	<div class="child">
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
				String myImage = Base64.getEncoder().encode(g.getImage()).toString();
			%>
			<tr>
				<td><img alt="" src=""></img></td>
				<td><%=g.getName()%></td>
				<td><%=g.getDescription() %></td>
				<td><%=g.getAveragePrice() %></td>
				<td><%=g.getPriority().toString() %></td>
				<td><%=g.getWebsiteLink() %></td>
				<td><%=g.getStatusGift().toString() %></td>
				<td><%=g.getLstReserve() %></td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
	<div class="child"></div>


</body>
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