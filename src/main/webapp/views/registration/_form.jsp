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
            <form:label class='required' path='code'><spring:message code='code' text='Code'/></form:label>
            <form:input class='form-control' path='code' type='text' required='true' size='30' maxlength='20'/>
            <form:errors path='code' cssClass='error' element='div'/>
        </div>
    </div>
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
            <form:label class='required' path='student'><spring:message code='student' text='Student'/></form:label>
            <form:select class='form-control' path='student.id' name='student' id='student' required='true' >
                <form:options items='${students}' itemValue='id'/>
            </form:select>
            <form:errors path='student' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='registrationDate'><spring:message code='registrationDate' text='Registration Date'/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-date' path='registrationDate' required='true'  />
             </div>
            <form:errors path='registrationDate' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='coursePrice'><spring:message code='coursePrice' text='Course Price'/></form:label>
            <form:input class='form-control' path='coursePrice' type='number' size='15' maxlength='15'/>
            <form:errors path='coursePrice' cssClass='error' element='div'/>
        </div>
    </div>

</div>   