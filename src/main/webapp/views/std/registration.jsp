<%@ page language="java" contentType="text/html;  charset=UTF-8"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Employee Registration Form</title>

        <style>
            .error {
                color: #0000ff;
            }
        </style>

    </head>

    <body>

        <h2>Registration Form Student</h2>

        <form:form method="POST" modelAttribute="student">
            <form:input type="hidden" path="id" id="id"/>
            <table>
                <tr>
                    <td><label for="name">Name: </label> </td>
                    <td><form:input path="name" id="name"/></td>
                    <td><form:errors path="name" cssClass="error"/></td>
                </tr>
               

                <tr>
                    <td><label for="gender">Gender: </label> </td>
                    <!--<td><form:input path="gender" id="gender"/></td>-->
                    <td>
                        <form:select path="gender">
                            <form:option value="FEMALE" label="Female"/>
                            <form:option value="MALE" label="Male" />
                        </form:select>
                    </td>
                    <td><form:errors path="gender" cssClass="error"/></td>
                </tr>

                <tr>
                    <td><label for="dob">Birth Date: </label> </td>
                    <td><form:input path="dob" id="dob"/></td>
                    <td><form:errors path="dob" cssClass="error"/></td>
                </tr>

              

                <tr>
                    <td><label for="code">Code: </label> </td>
                    <td><form:input path="code" id="code"/></td>
                    <td><form:errors path="code" cssClass="error"/></td>
                </tr>

                <tr>
                    <td colspan="3">
                        <c:choose>
                            <c:when test="${edit}">
                                <input type="submit" value="Update"/>
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="Register"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
        </form:form>
        <br/>
        <br/>
        Go back to <a href="<c:url value='/std/index' />">List of All Students</a>
    </body>
</html>