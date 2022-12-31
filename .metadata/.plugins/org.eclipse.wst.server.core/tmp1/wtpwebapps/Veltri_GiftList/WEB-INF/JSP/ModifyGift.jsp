<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="be.veltri.JAVABEANS.*" import="be.veltri.ENUMS.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify a gift</title>
</head>
<style>
body {
	margin-left: 50px;
}

.form {
	margin-bottom: 20px;
}

.child {
	display: inline-block;
	vertical-align: middle;
	width: 30%;
}

p {
	font-size: 12px;
}

#formType {
	display: none;
}
</style>
<jsp:include page="Header.jsp"></jsp:include>
<%
ArrayList<String> errors = (ArrayList<String>)request.getAttribute("errorsModifyGift");
Gift gift = (Gift) request.getAttribute("modifyGift");
String where = (String) request.getAttribute("where");
String idGift = (String) request.getAttribute("idGift");
String priorityH = "";
String priorityM = "";
String priorityL = "";
if (gift.getPriority().toString().equals("High"))
	priorityH = "checked";
else if (gift.getPriority().toString().equals("Medium"))
	priorityM = "checked";
else if (gift.getPriority().toString().equals("Low"))
	priorityL = "checked";
%>
<body>
	<h1>Modify gift</h1>
	<form action="verifModifyGift" method="POST"
		enctype="multipart/form-data">

		<input type="text" name="where" id="formType" value="<%=where%>" />
		<input type="text" name="idGiftModify" id="formType" value="<%=idGift%>" />


		<div class="child">
			<div class="form">
				<b>Name of the gift :</b> <input type="text" name="nameGift"
					id="nameGift" value="<%=gift.getName()%>" required />
			</div>
			<div>
				<b>Average price :</b> <input type="number" name="price" id="price"
					min="0" step="1" value="<%=gift.getAveragePrice()%>" required /> €
			</div>
		</div>
		<div class="child">
			<div class="form">
				<b>Description of the gift :</b>
				<textarea name="descGift" id="descGift" rows="4" cols="50" required><%=gift.getDescription()%></textarea>
			</div>

		</div>
		<div class="form">
			<b>Priority : </b> <input type="radio" name="priority" id="high"
				value="<%=EnumPriority.High%>" <%=priorityH%> />
			<%=EnumPriority.High%>
			<input type="radio" name="priority" id="medium"
				value="<%=EnumPriority.Medium%>" <%=priorityM%> />
			<%=EnumPriority.Medium%>
			<input type="radio" name="priority" id="low"
				value="<%=EnumPriority.Low%>" <%=priorityL%> />
			<%=EnumPriority.Low%>
		</div>
		<div class="form">
			<b>Web link [optional]: </b><input type="text" name="weblink"
				id="weblink" value="<%=gift.getWebsiteLink()%>" />
		</div>
		<div>
			<b>Insert an image [optional]:</b> <input type="file" name="image"
				id="image" accept=".jpg, .jpeg, .png" value="blabla" />
		</div>
		<p class="form">
			<em>Accept only .jpg, .jpeg and .png</em>
		</p>
		<button type="submit" name="addGiftBtn" id="addGiftBtn"
			style="margin-top: 30px;">UPDATE</button>
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
</body>
<jsp:include page="Footer.jsp"></jsp:include>
</html>