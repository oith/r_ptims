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
            <form:label class='required' path='courseFounded'><spring:message code='courseFounded' text='Course Founded'/></form:label>
            <form:select class='form-control' path='courseFounded.id' name='courseFounded' id='courseFounded' required='true' >
                <form:options items='${courseFoundeds}' itemValue='id'/>
            </form:select>
            <form:errors path='courseFounded' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='room'><spring:message code='room' text='Room'/></form:label>
            <form:select class='form-control' path='room.id' name='room' id='room' required='true' >
                <form:options items='${rooms}' itemValue='id'/>
            </form:select>
            <form:errors path='room' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='classDate'><spring:message code='classDate' text='Class Date'/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-date' path='classDate' required='true'  />
             </div>
            <form:errors path='classDate' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='startTime'><spring:message code='startTime' text='Start Time'/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-time' path='startTime' required='true'  placeholder='HH:mm' type='time' size='5' maxlength='5' />
             </div>
            <form:errors path='startTime' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='endTime'><spring:message code='endTime' text='End Time'/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-time' path='endTime' required='true'  placeholder='HH:mm' type='time' size='5' maxlength='5' />
             </div>
            <form:errors path='endTime' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='courseDayStatus'><spring:message code='courseDayStatus' text='Course Day Status'/></form:label>
            <form:select class='form-control' path='courseDayStatus' name='courseDayStatus' id='courseDayStatus' >
                <form:option value='PENDING' label='Pending'/>
                <form:option value='DONE' label='Done'/>
                <form:option value='CANCEL' label='Cancel'/>
            </form:select>
            <form:errors path='courseDayStatus' cssClass='error' element='div'/>
        </div>
    </div>

</div>   