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
                <h1><spring:message code='default.button.show.label' text='Show'/> Institute</h1>
                <ul class='top-links'>
                    <sec:access url='/institute/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/institute/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
                        </li>
                    </sec:access>
                    <sec:access url='/institute/index'>
                        <li>
                            <a href='${pageContext.request.contextPath}/institute/index' class='btn btn-block btn-info btn-xs'><i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/></a>
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

        <c:if test='${institute.code!=null && !institute.code.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='code' text='Code'/>: 
                </span>
                <span class='property-value' aria-labelledby='code'>
                    <c:out value='${institute.code}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${institute.picFile!=null && !institute.picFile.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='picFile' text='Pic File'/>: 
                </span>
                <span class='property-value' aria-labelledby='picFile'>
                    <c:out value='${institute.picFile}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${institute.fullName!=null && !institute.fullName.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='fullName' text='Full Name'/>: 
                </span>
                <span class='property-value' aria-labelledby='fullName'>
                    <c:out value='${institute.fullName}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${institute.orientationDate!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='orientationDate' text='Orientation Date'/>: 
                </span>
                <span class='property-value' aria-labelledby='orientationDate'>
                    <fmt:formatDate value='${institute.orientationDate}' type='date' pattern='dd/MM/yyyy'/>
                </span>
            </li>
        </c:if>

        <c:if test='${institute.email!=null && !institute.email.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='email' text='Email'/>: 
                </span>
                <span class='property-value' aria-labelledby='email'>
                    <c:out value='${institute.email}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${institute.address.lineOne!=null && !institute.address.lineOne.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='address.lineOne' text='Line One'/>: 
                </span>
                <span class='property-value' aria-labelledby='address.lineOne'>
                    <c:out value='${institute.address.lineOne}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${institute.address.lineTwo!=null && !institute.address.lineTwo.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='address.lineTwo' text='Line Two'/>: 
                </span>
                <span class='property-value' aria-labelledby='address.lineTwo'>
                    <c:out value='${institute.address.lineTwo}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${institute.address.street!=null && !institute.address.street.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='address.street' text='Street'/>: 
                </span>
                <span class='property-value' aria-labelledby='address.street'>
                    <c:out value='${institute.address.street}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${institute.address.city!=null && !institute.address.city.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='address.city' text='City'/>: 
                </span>
                <span class='property-value' aria-labelledby='address.city'>
                    <c:url var='address_city' value='/institute/getPhoto/${institute.address.city}'/>
<img height="110px" width="90px" alt='${institute.address.city}' src='${address_city}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${institute.address.zip!=null && !institute.address.zip.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='address.zip' text='Zip'/>: 
                </span>
                <span class='property-value' aria-labelledby='address.zip'>
                    <c:out value='${institute.address.zip}'/>
                </span>
            </li>
        </c:if>

        </ol>

    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class='box-footer'>
                            <a href='${pageContext.request.contextPath}/institute/edit/${institute.id}' class='btn btn-primary'><i class='fa fa-edit'></i> <spring:message code='edit.link.label'/></a> 
                            <a href='${pageContext.request.contextPath}/institute/copy/${institute.id}' class='btn btn-warning'><i class='fa fa-clone'></i> <spring:message code='default.button.copy.label'/></a>             
                            <a href='${pageContext.request.contextPath}/institute/delete/${institute.id}' class='btn btn-danger' onclick='return confirm('Are you sure to delete?');'><i class='fa fa-remove'></i> <spring:message code='delete.link.label'/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>