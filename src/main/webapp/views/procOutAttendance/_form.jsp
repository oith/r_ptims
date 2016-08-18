<%@ page contentType='text/html; charset=UTF-8' language='java' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>

<style>
    .error {
        color: #ff0000;
    }
    .errorblock {
        color: #000;
        background-color: #ffEEEE;
        border: 3px solid #ff0000;
        padding: 8px;
        margin: 16px;
    }
</style>

<script type='text/javascript'>

</script>

<form:errors path='*' cssClass='errorblock' element='div' />
<form:hidden path='id'/>
<div>  
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='student'><spring:message code='student' text='Student'/></form:label>
            <form:select class='form-control' path='student.id' name='student' id='student' required='true' >
                <form:options items='${students}' itemValue='id'/>
            </form:select>
            <form:errors path='student' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='procOutCourseSchedule'><spring:message code='procOutCourseSchedule' text='Proc Out Course Schedule'/></form:label>
            <form:select class='form-control' path='procOutCourseSchedule.id' name='procOutCourseSchedule' id='procOutCourseSchedule' required='true' >
                <form:options items='${procOutCourseSchedules}' itemValue='id'/>
            </form:select>
            <form:errors path='procOutCourseSchedule' cssClass='error' element='div'/>
        </div>
    </div>

</div>   