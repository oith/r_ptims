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
                <h1><spring:message code='default.button.show.label' text='Show'/> Student</h1>
                <ul class='top-links'>
                    <sec:access url='/student/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/student/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
                        </li>
                    </sec:access>
                    <sec:access url='/student/index'>
                        <li>
                            <a href='${pageContext.request.contextPath}/student/index' class='btn btn-block btn-info btn-xs'><i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/></a>
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

        <c:if test='${student.code!=null && !student.code.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='code' text='Code'/>: 
                </span>
                <span class='property-value' aria-labelledby='code'>
                    <c:out value='${student.code}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${student.picFile!=null && !student.picFile.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='picFile' text='Pic File'/>: 
                </span>
                <span class='property-value' aria-labelledby='picFile'>
                    <c:url var='picFile' value='/student/getPhoto/${student.picFile}'/>
<img height="110px" width="90px" alt='${student.picFile}' src='${picFile}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${student.fullName!=null && !student.fullName.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='fullName' text='Full Name'/>: 
                </span>
                <span class='property-value' aria-labelledby='fullName'>
                    <c:out value='${student.fullName}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${student.gender!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='gender' text='Gender'/>: 
                </span>
                <span class='property-value' aria-labelledby='gender'>
                    <c:out value='${student.gender}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${student.dob!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='dob' text='Dob'/>: 
                </span>
                <span class='property-value' aria-labelledby='dob'>
                    <fmt:formatDate value='${student.dob}' type='date' pattern='dd/MM/yyyy'/>
                </span>
            </li>
        </c:if>

        <c:if test='${student.email!=null && !student.email.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='email' text='Email'/>: 
                </span>
                <span class='property-value' aria-labelledby='email'>
                    <c:out value='${student.email}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${student.addressPermanent.lineOne!=null && !student.addressPermanent.lineOne.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='addressPermanent.lineOne' text='Line One'/>: 
                </span>
                <span class='property-value' aria-labelledby='addressPermanent.lineOne'>
                    <c:out value='${student.addressPermanent.lineOne}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${student.addressPermanent.lineTwo!=null && !student.addressPermanent.lineTwo.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='addressPermanent.lineTwo' text='Line Two'/>: 
                </span>
                <span class='property-value' aria-labelledby='addressPermanent.lineTwo'>
                    <c:out value='${student.addressPermanent.lineTwo}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${student.addressPermanent.street!=null && !student.addressPermanent.street.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='addressPermanent.street' text='Street'/>: 
                </span>
                <span class='property-value' aria-labelledby='addressPermanent.street'>
                    <c:out value='${student.addressPermanent.street}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${student.addressPermanent.city!=null && !student.addressPermanent.city.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='addressPermanent.city' text='City'/>: 
                </span>
                <span class='property-value' aria-labelledby='addressPermanent.city'>
                    <c:url var='addressPermanent_city' value='/student/getPhoto/${student.addressPermanent.city}'/>
<img height="110px" width="90px" alt='${student.addressPermanent.city}' src='${addressPermanent_city}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${student.addressPermanent.zip!=null && !student.addressPermanent.zip.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='addressPermanent.zip' text='Zip'/>: 
                </span>
                <span class='property-value' aria-labelledby='addressPermanent.zip'>
                    <c:out value='${student.addressPermanent.zip}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${student.addressPresent.lineOne!=null && !student.addressPresent.lineOne.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='addressPresent.lineOne' text='Line One'/>: 
                </span>
                <span class='property-value' aria-labelledby='addressPresent.lineOne'>
                    <c:out value='${student.addressPresent.lineOne}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${student.addressPresent.lineTwo!=null && !student.addressPresent.lineTwo.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='addressPresent.lineTwo' text='Line Two'/>: 
                </span>
                <span class='property-value' aria-labelledby='addressPresent.lineTwo'>
                    <c:out value='${student.addressPresent.lineTwo}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${student.addressPresent.street!=null && !student.addressPresent.street.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='addressPresent.street' text='Street'/>: 
                </span>
                <span class='property-value' aria-labelledby='addressPresent.street'>
                    <c:out value='${student.addressPresent.street}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${student.addressPresent.city!=null && !student.addressPresent.city.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='addressPresent.city' text='City'/>: 
                </span>
                <span class='property-value' aria-labelledby='addressPresent.city'>
                    <c:url var='addressPresent_city' value='/student/getPhoto/${student.addressPresent.city}'/>
<img height="110px" width="90px" alt='${student.addressPresent.city}' src='${addressPresent_city}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${student.addressPresent.zip!=null && !student.addressPresent.zip.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='addressPresent.zip' text='Zip'/>: 
                </span>
                <span class='property-value' aria-labelledby='addressPresent.zip'>
                    <c:out value='${student.addressPresent.zip}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${student.experienceInInfoTech!=null && !student.experienceInInfoTech.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='experienceInInfoTech' text='Experience In Info Tech'/>: 
                </span>
                <span class='property-value' aria-labelledby='experienceInInfoTech'>
                    <c:out value='${student.experienceInInfoTech}'/>
                </span>
            </li>
        </c:if>

        </ol>

    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class='box-footer'>
                            <a href='${pageContext.request.contextPath}/student/edit/${student.id}' class='btn btn-primary'><i class='fa fa-edit'></i> <spring:message code='edit.link.label'/></a> 
                            <a href='${pageContext.request.contextPath}/student/copy/${student.id}' class='btn btn-warning'><i class='fa fa-clone'></i> <spring:message code='default.button.copy.label'/></a>             
                            <a href='${pageContext.request.contextPath}/student/delete/${student.id}' class='btn btn-danger' onclick='return confirm('Are you sure to delete?');'><i class='fa fa-remove'></i> <spring:message code='delete.link.label'/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>