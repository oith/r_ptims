<page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions' prefix='fn'%>
<%@ taglib uri='http://tiles.apache.org/tags-tiles' prefix='tiles'%>
<%@ taglib uri='http://www.springframework.org/tags' prefix='spring'%>
<%@ taglib uri='http://www.springframework.org/tags/form' prefix='form'%>

<tiles:insertDefinition name='main' >

    <tiles:putAttribute name='body'>

        <div class='content-wrapper'><!-- Content Wrapper. Contains page content -->
            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code='default.button.list.label' text='List'/> Proc Out Course Schedule</h1>
                <ul class='top-links'>
                    <sec:access url='/procOutCourseSchedule/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/procOutCourseSchedule/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
                        </li>
                    </sec:access>
                </ul>
            </section><!-- /.content-header -->

            <section class='content-messages'>
                <%--<jsp:include page='../layouts/_flashMessage.jsp'/>--%>
            </section><!-- /.flesh-message -->

            <section class='content'><!-- Main content -->
                <div class='box box-primary'>   
                    <div class='box-body' style='overflow-x: auto'>
                        <table class='dt-default table table-bordered table-striped table-hover table-condensed'>

                            <!--<table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>-->
                            <thead>
                        <th class='center bold'><spring:message code='courseFounded' text='Course Founded'/></th>
                    <th class='center bold'><spring:message code='room' text='Room'/></th>
                    <th class='center bold'><spring:message code='classDate' text='Class Date'/></th>
                    <th class='center bold'><spring:message code='startTime' text='Start Time'/></th>
                    <th class='center bold'><spring:message code='endTime' text='End Time'/></th>
                    <th class='center bold'><spring:message code='courseDayStatus' text='Course Day Status'/></th>

    <th class='center bold'><spring:message code='default.button.action.label' text='Action'/></th> 
            </thead>
            <tbody>
            <c:if test='${not empty procOutCourseSchedules}'>
                <c:forEach items='${procOutCourseSchedules}' var='procOutCourseSchedule'  varStatus='loopStatus'>
                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><c:out value='${procOutCourseSchedule.courseFounded}'/></td>
                        <td><c:out value='${procOutCourseSchedule.room}'/></td>
                        <td><fmt:formatDate value='${procOutCourseSchedule.classDate}' type='date' pattern='dd/MM/yyyy'/></td>
                        <td><fmt:formatDate value='${procOutCourseSchedule.startTime}' type='time' pattern='HH:mm'/></td>
                        <td><fmt:formatDate value='${procOutCourseSchedule.endTime}' type='time' pattern='HH:mm'/></td>
                        <td><c:out value='${procOutCourseSchedule.courseDayStatus}'/></td>

                           <td class='center action'>
                            <ul class='top-links'>
                            <sec:access url='/procOutCourseSchedule/show'>
                                <li>
                                <a class='btn btn-block btn-info btn-xs' href='${pageContext.request.contextPath}/procOutCourseSchedule/show/${procOutCourseSchedule.id}'>
                                    <i class='fa fa-info-circle'></i> <spring:message code='show.link.label'/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url='/procOutCourseSchedule/edit'>
                                <li>
                                <a class='btn btn-block btn-primary btn-xs' href='${pageContext.request.contextPath}/procOutCourseSchedule/edit/${procOutCourseSchedule.id}'>
                                    <i class='fa fa-edit'></i> <spring:message code='edit.link.label'/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url='/procOutCourseSchedule/copy'>
                                <li>
                                <a class='btn btn-block btn-warning btn-xs' href='${pageContext.request.contextPath}/procOutCourseSchedule/copy/${procOutCourseSchedule.id}'>
                                    <i class='fa fa-clone' aria-hidden='true'></i> <spring:message code='default.button.copy.label'/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url='/procOutCourseSchedule/delete'>
                                <li>
                                <a class='btn btn-block btn-danger btn-xs' href='${pageContext.request.contextPath}/procOutCourseSchedule/delete/${procOutCourseSchedule.id}' onclick='return confirm('Are you sure to delete?');'>
                                    <i class='fa fa-remove'></i> <spring:message code='delete.link.label'/>
                                </a>
                                </li>
                            </sec:access>
                            </ul>
                        </td>
                        </tr>
                    </c:forEach>
                    </c:if> 
                </tbody>
            </table>
            <div class='pagination'>
                <!--<g:paginate total='{testInstanceCount ?: 0}'/>-->
            </div>
            </div><!-- /.box-body table-responsive no-padding -->
        </div><!-- /.box box-primary -->
        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->
    </tiles:putAttribute>
                
</tiles:insertDefinition>