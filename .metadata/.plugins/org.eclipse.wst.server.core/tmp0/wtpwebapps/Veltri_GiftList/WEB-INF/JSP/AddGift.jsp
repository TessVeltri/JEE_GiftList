<%@page import="be.veltri.ENUMS.EnumPriority"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add a gift</title>
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
p{
	font-size:12px;
}
</style>
<jsp:include page="Header.jsp"></jsp:include>
<body>
	<h1>Add a gift</h1>
	<form action="verifAddGift" method="POST" enctype="multipart/form-data">
		<div class="child">
			<div class="form">
				<b>Name of the gift :</b> <input type="text" name="nameGift"
					id="nameGift" value="" required/>
			</div>
			<div>
				<b>Average price :</b> <input type="number" name="price" id="price"
					min="0" step="1" value="" required/> €
			</div>
		</div>
		<div class="child">
			<div class="form">
				<b>Description of the gift :</b>
				<textarea name="descGift" id="descGift" rows="4" cols="50" required></textarea>
			</div>
			
		</div>
		<div class="form">
			<b>Priority : </b> <input type="radio" name="priority" id="high"
				value="<%=EnumPriority.High%>" />
			<%=EnumPriority.High%>
			<input type="radio" name="priority" id="medium"
				value="<%=EnumPriority.Medium%>" />
			<%=EnumPriority.Medium%>
			<input type="radio" name="priority" id="low"
				value="<%=EnumPriority.Low%>" checked />
			<%=EnumPriority.Low%>
		</div>
		<div class="form">
				<b>Web link [optional]: </b><input type="text" name="weblink"
					id="weblink" value="" />
			</div>
		<div>
			<b>Insert an image [optional]:</b> <input type="file" name="image"
				id="image" accept=".jpg, .jpeg, .png"/>
		</div>
		<p class="form"><em>Accept only .jpg, .jpeg and .png</em></p>
		<button type="submit" name="addGiftBtn" id="addGiftBtn" style="margin-top:30px;">ADD</button>
	</form>

</body>
<jsp:include page="Footer.jsp"></jsp:include>
</html>