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
                <h1><spring:message code='default.button.list.label' text='List'/> Instructor</h1>
                <ul class='top-links'>
                    <sec:access url='/instructor/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/instructor/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
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
                        <th class='center bold'><spring:message code='code' text='Code'/></th>
                    <th class='center bold'><spring:message code='picFile' text='Pic File'/></th>
                    <th class='center bold'><spring:message code='fullName' text='Full Name'/></th>
                    <th class='center bold'><spring:message code='gender' text='Gender'/></th>
                    <th class='center bold'><spring:message code='dob' text='Dob'/></th>
                    <th class='center bold'><spring:message code='email' text='Email'/></th>
                    <th class='center bold'><spring:message code='addressPermanent.lineOne' text='Line One'/></th>
                    <th class='center bold'><spring:message code='addressPermanent.lineTwo' text='Line Two'/></th>
                    <th class='center bold'><spring:message code='addressPermanent.street' text='Street'/></th>
                    <th class='center bold'><spring:message code='addressPermanent.city' text='City'/></th>
                    <th class='center bold'><spring:message code='addressPermanent.zip' text='Zip'/></th>
                    <th class='center bold'><spring:message code='addressPresent.lineOne' text='Line One'/></th>
                    <th class='center bold'><spring:message code='addressPresent.lineTwo' text='Line Two'/></th>
                    <th class='center bold'><spring:message code='addressPresent.street' text='Street'/></th>
                    <th class='center bold'><spring:message code='addressPresent.city' text='City'/></th>
                    <th class='center bold'><spring:message code='addressPresent.zip' text='Zip'/></th>
                    <th class='center bold'><spring:message code='joiningDate' text='Joining Date'/></th>
                    <th class='center bold'><spring:message code='salary' text='Salary'/></th>
                    <th class='center bold'><spring:message code='speciality' text='Speciality'/></th>
                    <th class='center bold'><spring:message code='courses' text='Courses'/></th>

    <th class='center bold'><spring:message code='default.button.action.label' text='Action'/></th> 
            </thead>
            <tbody>
            <c:if test='${not empty instructors}'>
                <c:forEach items='${instructors}' var='instructor'  varStatus='loopStatus'>
                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><c:out value='${instructor.code}'/></td>
                        <td><c:url var='picFile' value='/instructor/getPhotoTumb/${instructor.picFile}'/>
<img alt='${instructor.picFile}' src='${picFile}'/></td>
                        <td><c:out value='${instructor.fullName}'/></td>
                        <td><c:out value='${instructor.gender}'/></td>
                        <td><fmt:formatDate value='${instructor.dob}' type='date' pattern='dd/MM/yyyy'/></td>
                        <td><c:out value='${instructor.email}'/></td>
                        <td><c:out value='${instructor.addressPermanent.lineOne}'/></td>
                        <td><c:out value='${instructor.addressPermanent.lineTwo}'/></td>
                        <td><c:out value='${instructor.addressPermanent.street}'/></td>
                        <td><c:url var='addressPermanent_city' value='/instructor/getPhotoTumb/${instructor.addressPermanent.city}'/>
<img alt='${instructor.addressPermanent.city}' src='${addressPermanent_city}'/></td>
                        <td><c:out value='${instructor.addressPermanent.zip}'/></td>
                        <td><c:out value='${instructor.addressPresent.lineOne}'/></td>
                        <td><c:out value='${instructor.addressPresent.lineTwo}'/></td>
                        <td><c:out value='${instructor.addressPresent.street}'/></td>
                        <td><c:url var='addressPresent_city' value='/instructor/getPhotoTumb/${instructor.addressPresent.city}'/>
<img alt='${instructor.addressPresent.city}' src='${addressPresent_city}'/></td>
                        <td><c:out value='${instructor.addressPresent.zip}'/></td>
                        <td><fmt:formatDate value='${instructor.joiningDate}' type='date' pattern='dd/MM/yyyy'/></td>
                        <td><c:out value='${instructor.salary}'/></td>
                        <td><c:out value='${instructor.speciality}'/></td>
                        <td><c:out value='${instructor.courses}'/></td>

                           <td class='center action'>
                            <ul class='top-links'>
                            <sec:access url='/instructor/show'>
                                <li>
                                <a class='btn btn-block btn-info btn-xs' href='${pageContext.request.contextPath}/instructor/show/${instructor.id}'>
                                    <i class='fa fa-info-circle'></i> <spring:message code='show.link.label'/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url='/instructor/edit'>
                                <li>
                                <a class='btn btn-block btn-primary btn-xs' href='${pageContext.request.contextPath}/instructor/edit/${instructor.id}'>
                                    <i class='fa fa-edit'></i> <spring:message code='edit.link.label'/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url='/instructor/copy'>
                                <li>
                                <a class='btn btn-block btn-warning btn-xs' href='${pageContext.request.contextPath}/instructor/copy/${instructor.id}'>
                                    <i class='fa fa-clone' aria-hidden='true'></i> <spring:message code='default.button.copy.label'/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url='/instructor/delete'>
                                <li>
                                <a class='btn btn-block btn-danger btn-xs' href='${pageContext.request.contextPath}/instructor/delete/${instructor.id}' onclick='return confirm('Are you sure to delete?');'>
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