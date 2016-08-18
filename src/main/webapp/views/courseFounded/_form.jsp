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
    function getCodableDTOInstructor(code, lblCaption){
        $.ajax({
            type : 'GET',
            url: '${pageContext.request.contextPath}/instructor/getCodableDTO',
            data: {code: code},
            success: function(d) {
                //alert('ok:'+d)
                $('#'+lblCaption).text(d)
            },
            error: function(err) {
                //alert('err mac:'+err)
                $('#'+lblCaption).text(err)
            }
        });
    }
</script>

<form:errors path='*' cssClass='errorblock' element='div' />
<form:hidden path='id'/>
<div>  
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='course'><spring:message code='course' text='Course'/></form:label>
            <form:select class='form-control' path='course.id' name='course' id='course' required='true' >
                <form:options items='${courses}' itemValue='id'/>
            </form:select>
            <form:errors path='course' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='instructor'><spring:message code='instructor' text='Instructor'/></form:label>
            <form:input class='form-control' path='instructor.code' onkeyup='getCodableDTOInstructor(this.value,"instructor_caption")' type='text' size='8'/>
            <label id='instructor_caption'>${courseFounded.instructor.fullName}</label>
            <form:errors path='instructor' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='rooms'><spring:message code='rooms' text='Rooms'/></form:label>
            <form:select class='form-control' path='rooms' name='rooms' id='rooms'  multiple='true'>
                <form:options items='${rooms}' itemValue='id'/>
            </form:select>
            <form:errors path='rooms' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='totalCourseHour'><spring:message code='totalCourseHour' text='Total Course Hour'/></form:label>
            <form:input class='form-control' path='totalCourseHour' type='number' size='15' maxlength='15'/>
            <form:errors path='totalCourseHour' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='totalNoOfClass'><spring:message code='totalNoOfClass' text='Total No Of Class'/></form:label>
            <form:input class='form-control' path='totalNoOfClass' type='number' size='15' maxlength='15'/>
            <form:errors path='totalNoOfClass' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='noOfClassInWeek'><spring:message code='noOfClassInWeek' text='No Of Class In Week'/></form:label>
            <form:input class='form-control' path='noOfClassInWeek' type='number' size='15' maxlength='15'/>
            <form:errors path='noOfClassInWeek' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='orientationDate'><spring:message code='orientationDate' text='Orientation Date'/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-date' path='orientationDate'  />
             </div>
            <form:errors path='orientationDate' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='courseFoundedStatus'><spring:message code='courseFoundedStatus' text='Course Founded Status'/></form:label>
            <form:select class='form-control' path='courseFoundedStatus' name='courseFoundedStatus' id='courseFoundedStatus' >
                <form:option value='NOT_STARTED' label='Not Started'/>
                <form:option value='RUNNING' label='Running'/>
                <form:option value='OTHER' label='Other'/>
                <form:option value='CLOSE' label='Close'/>
            </form:select>
            <form:errors path='courseFoundedStatus' cssClass='error' element='div'/>
        </div>
    </div>

</div>   