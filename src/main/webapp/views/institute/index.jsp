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
                <h1><spring:message code='default.button.list.label' text='List'/> Institute</h1>
                <ul class='top-links'>
                    <sec:access url='/institute/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/institute/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
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
                    <th class='center bold'><spring:message code='orientationDate' text='Orientation Date'/></th>
                    <th class='center bold'><spring:message code='email' text='Email'/></th>
                    <th class='center bold'><spring:message code='address.lineOne' text='Line One'/></th>
                    <th class='center bold'><spring:message code='address.lineTwo' text='Line Two'/></th>
                    <th class='center bold'><spring:message code='address.street' text='Street'/></th>
                    <th class='center bold'><spring:message code='address.city' text='City'/></th>
                    <th class='center bold'><spring:message code='address.zip' text='Zip'/></th>

    <th class='center bold'><spring:message code='default.button.action.label' text='Action'/></th> 
            </thead>
            <tbody>
            <c:if test='${not empty institutes}'>
                <c:forEach items='${institutes}' var='institute'  varStatus='loopStatus'>
                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><c:out value='${institute.code}'/></td>
                        <td><c:out value='${institute.picFile}'/></td>
                        <td><c:out value='${institute.fullName}'/></td>
                        <td><fmt:formatDate value='${institute.orientationDate}' type='date' pattern='dd/MM/yyyy'/></td>
                        <td><c:out value='${institute.email}'/></td>
                        <td><c:out value='${institute.address.lineOne}'/></td>
                        <td><c:out value='${institute.address.lineTwo}'/></td>
                        <td><c:out value='${institute.address.street}'/></td>
                        <td><c:url var='address_city' value='/institute/getPhotoTumb/${institute.address.city}'/>
<img alt='${institute.address.city}' src='${address_city}'/></td>
                        <td><c:out value='${institute.address.zip}'/></td>

                           <td class='center action'>
                            <ul class='top-links'>
                            <sec:access url='/institute/show'>
                                <li>
                                <a class='btn btn-block btn-info btn-xs' href='${pageContext.request.contextPath}/institute/show/${institute.id}'>
                                    <i class='fa fa-info-circle'></i> <spring:message code='show.link.label'/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url='/institute/edit'>
                                <li>
                                <a class='btn btn-block btn-primary btn-xs' href='${pageContext.request.contextPath}/institute/edit/${institute.id}'>
                                    <i class='fa fa-edit'></i> <spring:message code='edit.link.label'/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url='/institute/copy'>
                                <li>
                                <a class='btn btn-block btn-warning btn-xs' href='${pageContext.request.contextPath}/institute/copy/${institute.id}'>
                                    <i class='fa fa-clone' aria-hidden='true'></i> <spring:message code='default.button.copy.label'/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url='/institute/delete'>
                                <li>
                                <a class='btn btn-block btn-danger btn-xs' href='${pageContext.request.contextPath}/institute/delete/${institute.id}' onclick='return confirm('Are you sure to delete?');'>
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