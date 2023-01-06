<%@page import="be.veltri.ENUMS.EnumStatusList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="be.veltri.JAVABEANS.*"%>
<%@page import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add a list</title>
<link href="/Veltri_GiftList/CSS/infoListCSS.css" rel="stylesheet"
	type="text/css">
</head>
<jsp:include page="Header.jsp"></jsp:include>
<style>
body {
	margin-left: 50px;
}

.child {
	display: inline-block;
	vertical-align: middle;
	width: 30%;
}

.form {
	margin-bottom: 20px;
}

.btnSubmit {
	margin-top: 30px;
	font-size: 17px;
}
</style>
<%
GiftList gl = (GiftList) request.getSession().getAttribute("addList");
if (gl == null) {
	User u = null;
	gl = new GiftList("", "", "", EnumStatusList.Active, true, u);
}
ArrayList<Gift> lstGifts = gl.getLstGift();
String checked;
String disabled;
String requiredSwitch;
String requiredDate;
String disabledBtn = "disabled";
String hideTxt = "";
if (gl.isActive()) {
	checked = "checked";
	disabled = "disabled";
	requiredSwitch = "required";
	requiredDate = "";
} else {
	checked = "";
	disabled = "";
	requiredSwitch = "";
	requiredDate = "required";
}
%>
<body>
	<h1>Add List</h1>
	<form action="verifAddList" method="POST">
		<div class="form">
			<b>Name of the list : </b><input type="text" name="nameList"
				id="nameList" value="<%=gl.getNameList()%>" required />
		</div>
		<div>
			<div class="form child">
				<b>Date : </b><input type="date" name="limitDate" id="limitDate"
					value="<%=gl.getLimitDate()%>" <%=disabled%> <%= requiredDate%>/>
			</div>
			<div class="form child">
				<b>Active/Inactive mode : </b><input type="checkbox" name="isActive"
					id="isActive" onclick="checkClick()" <%=checked%> <%= requiredSwitch%> />
			</div>
		</div>
		<div>
			<b>Occasion : </b><select name="occasion" id="occasion">
				<option value="wedding">Wedding</option>
				<option value="baptism">Baptism</option>
				<option value="bacheloretteParty">Bachelorette Party</option>
				<option value="bachelorParty">Bachelor Party</option>
				<option value="babyShower">Baby Shower</option>
				<option value="other">Other</option>
			</select>
		</div>
		<div>
			<h3>
				<em>Gifts:</em>
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
				</tr>
				<%
				int index = 0;
				for (Gift g : lstGifts) {

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
						%> <img src="<%=image%>" width="50px" height="50px"></img> <%
 }
 %>
					</td>
					<td><%=g.getName()%></td>
					<td><%=g.getDescription()%></td>
					<td><%=g.getAveragePrice()%></td>
					<td><%=g.getPriority().toString()%></td>
					<td><%=g.getWebsiteLink()%></td>
					<td><%=g.getStatusGift().toString()%></td>
					<td><button type="button" class="btn child"
							onclick="location.href='/Veltri_GiftList/modifyGift?idGift=<%=index%>&where=local'">
							<img width="25px" height="25px"
								src="/Veltri_GiftList/IMG/pen.png"></img>
						</button></td>
					<td><button type="button" class="btn child"
							onclick="location.href='/Veltri_GiftList/addList?idGift=<%=index%>'">
							<img width="25px" height="25px"
								src="/Veltri_GiftList/IMG/delete.png"></img>
						</button></td>
				</tr>
				<%
				index++;
				}
				%>
			</table>

			<button type="button" id="addGift" onclick="addGiftBtn()">
				<img width="30px" height="30px" src="/Veltri_GiftList/IMG/plus.png"></img>
			</button>
		</div>
		<div align="center">
			<h4>
				<em>You can add participants after create the list.</em>
			</h4>
		</div>

		<%
		if (lstGifts.size() > 0) {
			disabledBtn = "";
			hideTxt = "display:none;";
		}
		%>

		<div align="right">
			<button type="submit" class="btnSubmit" <%=disabledBtn%>>Send</button>
			<p id="infoTxt" style="font-size:12px;<%=hideTxt%>">To create a
				list, you must add at least one gift.</p>
		</div>

	</form>

</body>
<jsp:include page="Footer.jsp"></jsp:include>
<script>
	const isActive = document.getElementById("isActive");
	const date = document.getElementById("limitDate");
	const nameList = document.getElementById("nameList");
	const occasion = document.getElementById("occasion");

	function checkClick() {
		if (date.disabled == true) {
			date.disabled = false;
			date.required = true;
			isActive.required = false;
		} else {
			date.disabled = true;
			date.value = "";
			date.required = false;
			isActive.required = true;
		}
	}

	function addGiftBtn() {
		window.location.href = '/Veltri_GiftList/addGift?nameList='
				+ nameList.value + '&limitDate=' + date.value + '&isActive='
				+ isActive.value + '&occasion=' + occasion.value;
	}
</script>
</html>
