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
        <h2>List of Menu</h2>	

        <a href="<c:url value='/emp/index' />">Employee</a>
        <a href="<c:url value='/std/index' />">Student</a>
    </body>
</html>