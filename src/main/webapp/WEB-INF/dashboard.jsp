<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Dashboard</title>
	</head>
	<body>
	<h1>Welcome, <c:out value="${user.firstName}"/></h1>
	<p><a href="/logout">Logout</a></p>
	<p>Here are some of the events in your state:</p>
		<table>
		  <tr>
		    <th>Name</th>
		    <th>Date</th> 
		    <th>Location</th>
		    <th>Host</th>
		    <th>Action / Status</th>
		  </tr>
		  <c:forEach var = "row" items="${event}">
		  <tr>
		    <td><a href="/show/${row[4]}">${row[0]}</a></td>
		    <td><fmt:formatDate value="${row[1]}" pattern="yyyy-MM-dd" /></td> 
		    <td>${row[2]}</td>
		    <td>${row[3]}</td>
		    <td>
		    	<c:choose>
		    	<c:when test = "${row[5].equals(user.firstName)}">
		    	<a href="/cancel/${row[4]}">Cancel</a>
		    	</c:when>
		    	<c:otherwise>
		    	<a href="/join/${row[4]}">Join</a>
		    	</c:otherwise>
		    	</c:choose>
		    	<c:choose>
		    	<c:when test = "${row[3].equals(user.firstName)}">
		    	<a href="/edit/${row[4]}">Edit</a>
		    	<a href="/delete/${row[4]}">Delete</a>
		    	</c:when>
		    	</c:choose>
		    </td>
		  </tr>
		  </c:forEach>
		</table>
	<p>Here are some of the events in other states:</p>
		<table>
		  <tr>
		    <th>Name</th>
		    <th>Date</th> 
		    <th>Location</th>
		    <th>State</th>
		    <th>Host</th>
		    <th>Action / Status</th>
		  </tr>
		  <c:forEach var = "out" items="${eventout}">
		  <tr>
		    <td><a href="/show/${out[5]}">${out[0]}</a></td>
		    <td><fmt:formatDate value="${out[1]}" pattern="yyyy-MM-dd" /></td> 
		    <td>${out[2]}</td>
		    <td>${out[3]}</td>
		   	<td>${out[4]}</td>
		    <td>
		    	<c:choose>
		    	<c:when test = "${out[6].equals(user.firstName)}">
		    	<a href="/cancel/${out[5]}">Cancel</a>
		    	</c:when>
		    	<c:otherwise>
		    	<a href="/join/${out[5]}">Join</a>
		    	</c:otherwise>
		    	</c:choose>
		    </td>
		  </tr>
		  </c:forEach>
		</table>
	<p></p>
	<fieldset>
	<h3>Create an Event</h3>
		<p><form:errors path = "event.*"/></p>
		<form:form action="/newevent" method="POST" modelAttribute="newevent">
			<p>
				<form:label path="name">Name:</form:label>
				<form:errors path="name"/>
				<form:input path="name"/>
			</p>
			Date: <input type="date" name="eventdate">
	  		<p>
	            <form:label path="location">Location:</form:label>
	            <form:errors path="location"/>
	            <form:input path="location"/>
	        	<form:select path="state">
	        	<c:forEach items="${states}" var="us">
				  <form:option label="${us.state}" value="${us.id}"></form:option>
				</c:forEach>
				</form:select>
			</p>
			<input type="hidden" name="host" value="${user.firstName}"/>
			<input type="submit" value="Submit"/>
		</form:form>
	</fieldset>
	</body>
</html>