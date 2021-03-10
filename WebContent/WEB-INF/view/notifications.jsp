<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
Apply points to:
<table>
	<tr>
		<th>First Name</th>
		<th>Email</th>
		<th>Points</th>
	</tr>
	<c:forEach var="tempPoints" items="${applypoints}">
		<tr>
			<td>${tempPoints.name}</td>
			<td>${tempPoints.email}</td>
			<td>${tempPoints.points}</td>
		</tr>
	</c:forEach>
</table>
<br></br>
Change email to:
<table>
	<tr>
		<th>First Name</th>
		<th>Oldemail</th>
		<th>Newemail</th>
	</tr>
	<c:forEach var="tempChange" items="${changemail}">
		<tr>
			<td>${tempChange.stdname}</td>
			<td>${tempChange.stdoldmail}</td>
			<td>${tempChange.stdnewmail}</td>
		</tr>
	</c:forEach>
</table>
<br></br>
Press back until you reach menu!