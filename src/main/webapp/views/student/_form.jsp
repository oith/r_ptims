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
            <form:label path='picFile'><spring:message code='picFile' text='Pic File'/></form:label>
                <c:url var='picFile' value='/student/getPhoto/${student.picFile}'/>
                <img height="110px" width="90px" alt='${student.picFile}' src='${picFile}'/>
                <form:hidden path='picFile'/>
                <input id='picFileOBJ' name='picFileOBJ' type='file' accept='image/*'/>
            <form:errors path='picFile' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='fullName'><spring:message code='fullName' text='Full Name'/></form:label>
            <form:input class='form-control' path='fullName' type='text' required='true' size='30' maxlength='100'/>
            <form:errors path='fullName' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='gender'><spring:message code='gender' text='Gender'/></form:label>
            <form:select class='form-control' path='gender' name='gender' id='gender' >
                <form:option value='MALE' label='Male'/>
                <form:option value='FEMALE' label='Female'/>
                <form:option value='OTHER' label='Other'/>
            </form:select>
            <form:errors path='gender' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='dob'><spring:message code='dob' text='Dob'/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-date' path='dob'  />
             </div>
            <form:errors path='dob' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='email'><spring:message code='email' text='Email'/></form:label>
            <form:input class='form-control' path='email' type='email' size='30' maxlength='30'/>
            <form:errors path='email' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='addressPermanent.lineOne'><spring:message code='addressPermanent.lineOne' text='Line One'/></form:label>
            <form:input class='form-control' path='addressPermanent.lineOne' type='text' size='30' maxlength='50'/>
            <form:errors path='addressPermanent.lineOne' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='addressPermanent.lineTwo'><spring:message code='addressPermanent.lineTwo' text='Line Two'/></form:label>
            <form:input class='form-control' path='addressPermanent.lineTwo' type='text' size='30' maxlength='50'/>
            <form:errors path='addressPermanent.lineTwo' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='addressPermanent.street'><spring:message code='addressPermanent.street' text='Street'/></form:label>
            <form:input class='form-control' path='addressPermanent.street' type='text' size='30' maxlength='30'/>
            <form:errors path='addressPermanent.street' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='addressPermanent.city'><spring:message code='addressPermanent.city' text='City'/></form:label>
                <c:url var='addressPermanent_city' value='/student/getPhoto/${student.addressPermanent.city}'/>
                <img height="110px" width="90px" alt='${student.addressPermanent.city}' src='${addressPermanent_city}'/>
                <form:hidden path='addressPermanent.city'/>
                <input id='addressPermanent_cityOBJ' name='addressPermanent_cityOBJ' type='file' accept='image/*'/>
            <form:errors path='addressPermanent.city' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='addressPermanent.zip'><spring:message code='addressPermanent.zip' text='Zip'/></form:label>
            <form:input class='form-control' path='addressPermanent.zip' type='text' size='30' maxlength='10'/>
            <form:errors path='addressPermanent.zip' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='addressPresent.lineOne'><spring:message code='addressPresent.lineOne' text='Line One'/></form:label>
            <form:input class='form-control' path='addressPresent.lineOne' type='text' size='30' maxlength='50'/>
            <form:errors path='addressPresent.lineOne' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='addressPresent.lineTwo'><spring:message code='addressPresent.lineTwo' text='Line Two'/></form:label>
            <form:input class='form-control' path='addressPresent.lineTwo' type='text' size='30' maxlength='50'/>
            <form:errors path='addressPresent.lineTwo' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='addressPresent.street'><spring:message code='addressPresent.street' text='Street'/></form:label>
            <form:input class='form-control' path='addressPresent.street' type='text' size='30' maxlength='30'/>
            <form:errors path='addressPresent.street' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='addressPresent.city'><spring:message code='addressPresent.city' text='City'/></form:label>
                <c:url var='addressPresent_city' value='/student/getPhoto/${student.addressPresent.city}'/>
                <img height="110px" width="90px" alt='${student.addressPresent.city}' src='${addressPresent_city}'/>
                <form:hidden path='addressPresent.city'/>
                <input id='addressPresent_cityOBJ' name='addressPresent_cityOBJ' type='file' accept='image/*'/>
            <form:errors path='addressPresent.city' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='addressPresent.zip'><spring:message code='addressPresent.zip' text='Zip'/></form:label>
            <form:input class='form-control' path='addressPresent.zip' type='text' size='30' maxlength='10'/>
            <form:errors path='addressPresent.zip' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='experienceInInfoTech'><spring:message code='experienceInInfoTech' text='Experience In Info Tech'/></form:label>
            <form:input class='form-control' path='experienceInInfoTech' type='text' size='30' maxlength='30'/>
            <form:errors path='experienceInInfoTech' cssClass='error' element='div'/>
        </div>
    </div>

</div>   