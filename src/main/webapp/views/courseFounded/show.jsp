<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>

<%@ taglib uri='http://tiles.apache.org/tags-tiles' prefix='tiles'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri='http://www.springframework.org/tags' prefix='spring'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions' prefix='fn'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@ taglib uri='http://www.springframework.org/tags/form' prefix='form'%>

<tiles:insertDefinition name='main' >

    <tiles:putAttribute name='body'>
        
        <div class='content-wrapper'><!-- Content Wrapper. Contains page content -->
            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code='default.button.show.label' text='Show'/> Course Founded</h1>
                <ul class='top-links'>
                    <sec:access url='/courseFounded/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/courseFounded/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
                        </li>
                    </sec:access>
                    <sec:access url='/courseFounded/index'>
                        <li>
                            <a href='${pageContext.request.contextPath}/courseFounded/index' class='btn btn-block btn-info btn-xs'><i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/></a>
                        </li>
                    </sec:access>
                </ul>
            </section><!-- /.content-header -->

            <section class='content-messages'>
                <%--<jsp:include page='../layouts/_flashMessage.jsp'/>--%>
            </section><!-- /.flesh-message -->

            <section class='content'><!-- Main content -->
                <div class='box box-primary'>
                    <div class='box-body'>
                        <fieldset class='show-page'>
                             <form:hidden path='id'/>
        <ol class='property-list'>

        <c:if test='${courseFounded.course!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='course' text='Course'/>: 
                </span>
                <span class='property-value' aria-labelledby='course'>
                    <c:out value='${courseFounded.course}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${courseFounded.instructor!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='instructor' text='Instructor'/>: 
                </span>
                <span class='property-value' aria-labelledby='instructor'>
                    <c:out value='${courseFounded.instructor!=null ? courseFounded.instructor :"N/A"}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${courseFounded.rooms!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='rooms' text='Rooms'/>: 
                </span>
                <span class='property-value' aria-labelledby='rooms'>
                    <c:out value='${courseFounded.rooms}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${courseFounded.totalCourseHour!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='totalCourseHour' text='Total Course Hour'/>: 
                </span>
                <span class='property-value' aria-labelledby='totalCourseHour'>
                    <c:out value='${courseFounded.totalCourseHour}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${courseFounded.totalNoOfClass!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='totalNoOfClass' text='Total No Of Class'/>: 
                </span>
                <span class='property-value' aria-labelledby='totalNoOfClass'>
                    <c:out value='${courseFounded.totalNoOfClass}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${courseFounded.noOfClassInWeek!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='noOfClassInWeek' text='No Of Class In Week'/>: 
                </span>
                <span class='property-value' aria-labelledby='noOfClassInWeek'>
                    <c:out value='${courseFounded.noOfClassInWeek}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${courseFounded.orientationDate!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='orientationDate' text='Orientation Date'/>: 
                </span>
                <span class='property-value' aria-labelledby='orientationDate'>
                    <fmt:formatDate value='${courseFounded.orientationDate}' type='date' pattern='dd/MM/yyyy'/>
                </span>
            </li>
        </c:if>

        <c:if test='${courseFounded.courseFoundedStatus!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='courseFoundedStatus' text='Course Founded Status'/>: 
                </span>
                <span class='property-value' aria-labelledby='courseFoundedStatus'>
                    <c:out value='${courseFounded.courseFoundedStatus}'/>
                </span>
            </li>
        </c:if>

        </ol>

    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class='box-footer'>
                            <a href='${pageContext.request.contextPath}/courseFounded/edit/${courseFounded.id}' class='btn btn-primary'><i class='fa fa-edit'></i> <spring:message code='edit.link.label'/></a> 
                            <a href='${pageContext.request.contextPath}/courseFounded/copy/${courseFounded.id}' class='btn btn-warning'><i class='fa fa-clone'></i> <spring:message code='default.button.copy.label'/></a>             
                            <a href='${pageContext.request.contextPath}/courseFounded/delete/${courseFounded.id}' class='btn btn-danger' onclick='return confirm('Are you sure to delete?');'><i class='fa fa-remove'></i> <spring:message code='delete.link.label'/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>