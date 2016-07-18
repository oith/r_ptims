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

        <h2>List of Employees</h2>	
        <table>
            <tr>
                <td>Code</td>
                <td>Full Name</td>
                <td>Facebook ID</td>
                <td>Gender</td>
                <td>Joining Date</td>
                <td>Salary</td>

                <td></td>
            </tr>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <td><a href="<c:url value='/emp/edit-${employee.code}-employee' />">${employee.code}</a></td>

                    <td>${employee.fullName}</td>
                    <td>${employee.fbId}</td>
                    <td>${employee.gender}</td>
                    <td>${employee.joiningDate}</td>
                    <td>${employee.salary}</td>
                    <td><a href="<c:url value='/emp/delete-${employee.code}-employee' />">delete</a></td>
                </tr>
            </c:forEach>
        </table>




        <br/>
        <a href="<c:url value='/emp/new' />">Add New Employee</a>
    </body>
</html>