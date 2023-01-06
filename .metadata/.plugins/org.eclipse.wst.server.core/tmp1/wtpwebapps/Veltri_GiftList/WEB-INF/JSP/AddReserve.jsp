<%@page import="be.veltri.JAVABEANS.Reserve"%>
<%@page import="be.veltri.JAVABEANS.Gift"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reserve a gift</title>
</head>
<jsp:include page="Header.jsp"></jsp:include>
<style>
body {
	margin-left: 50px;
}
div {
	margin-bottom: 20px;
}

</style>
<%
Gift gift = (Gift) session.getAttribute("gift");
int rest = 0;
int total = 0;
for (Reserve r : gift.getLstReserve()) {
	total += r.getAmount();
}
rest = gift.getAveragePrice() - total;
%>
<body>
	<h2>Reserve a gift</h2>
	<form action="verifAddReserve" method="POST"> 
		<h4>
			Name of the gift :
			<%=gift.getName()%></h4>
		<table>
			<%
			if (gift.getLstReserve().size() == 0) {
				rest = 0;
			%>
			<tr>
				<td>Reserve totally</td>
				<td><input type="checkbox" name="totally" id="totally" value="true"
				onclick="checkClick()" /></td>
			</tr>
			<%
			}
			%>
			<tr>
				<td>Reserve partially</td>
				<td><input type="number" name="partially"
				id="partially" value="<%=rest%>"/></td>
			</tr>
		</table>
		
		<button type="submit">RESERVE</button>
	</form>

</body>
<jsp:include page="Footer.jsp"></jsp:include>
<script>
	const totally = document.getElementById("totally");
	const partially = document.getElementById("partially");
	
	function checkClick(){
		if (totally.checked == false){
			partially.disabled = false;
		} else {
			partially.disabled = true;
			partially.value = 0;
		}
	}

</script>
</html>