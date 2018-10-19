<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Edit</title>
	</head>
	<body>
	<fieldset>
	<h3>Edit an Event</h3>
		<form:form action="/edit/${event.id}" method="POST" modelAttribute="editevent">
			<input type="hidden" name="_method" value="put">
			<p>
				<form:label path="name">Name:</form:label>
				<form:errors path="name"/>
				<form:input value="${event.name}" path="name"/>
			</p>
			Date: <input type="date" name="eventdate">
	  		<p>
	            <form:label path="location">Location:</form:label>
	            <form:errors path="location"/>
	            <form:input value="${event.location}" path="location"/>
	        	<form:select path="state">
	        	<c:forEach items="${states}" var="us">
				  <form:option label="${us.state}" value="${us.id}"></form:option>
				</c:forEach>
				</form:select>
			</p>
			<input type="submit" value="Submit"/>
		</form:form>
	</fieldset>
	</body>
</html>