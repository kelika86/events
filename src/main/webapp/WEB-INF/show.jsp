<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Event: <c:out value="${event.name}"/></title>
	</head>
	<body>
		<h1><c:out value="${event.name}"/></h1>
		<p>Host: ${event.host}</p>
		<p>Date: <fmt:formatDate value="${event.date}" pattern="yyyy-MM-dd" /></p>
		<p>Location: ${event.location}</p>
		<p>People attending this event:</p>
		<table>
		  <tr>
		    <th>Name</th>
		    <th>Location</th>
		  </tr>
		  <c:forEach items="${eventuser}" var="um">
		  <tr>
		    <td><c:out value="${um.firstName}"/></td>
		    <td><c:out value="${um.location}"/></td> 
		  </tr>
		  </c:forEach>
		</table>
		<h1>Message Board</h1>
		<div>
		<c:forEach items="${messageboard}" var="message">
			<p><c:out value="${message.user.firstName}"/>:<c:out value="${message.message}"/></p>
		</c:forEach>
		</div>
		<form:form action="/newcomment" method="POST" modelAttribute="newcomment">
		<p>
			<form:label path="message">Add Comment:</form:label>
			<form:errors path="message"/>
			<form:input path="message"/>
		</p>
		<input type="hidden" name="user" value="${user.id}"/>
		<input type="hidden" name="event" value="${event.id}"/>
		<input type="submit" value="Submit"/>
		</form:form>
		<p><a href="/dashboard">Return</a></p>
	</body>
</html>