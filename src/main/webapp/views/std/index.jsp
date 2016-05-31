<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JAVA Enrollments</title>

        <style>
            tr:first-child{
                font-weight: bold;
                background-color: #C6C9C4;
            }
        </style>
    </head>

    <body>
        <a href="<c:url value='/' />">Home</a>
        <h2>List of Student</h2>	
        <table>
            <tr>
                <td>Code</td>
                <td>Name</td>
                <td>Gender</td>
                <td>Birth of Date</td>
                <td></td>
            </tr>
            <c:forEach items="${students}" var="student">
                <tr>
                    <td><a href="<c:url value='/std/edit-${student.code}-student' />">${student.code}</a></td>

                    <td>${student.name}</td>
                    <td>${student.gender}</td>
                    <td>${student.dob}</td>
                    <td><a href="<c:url value='/std/delete-${student.code}-student' />">delete</a></td>
                </tr>
            </c:forEach>
        </table>




        <br/>
        <a href="<c:url value='/std/new' />">Add New Student</a>
    </body>
</html>