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
            <form:textarea class='form-control' path='picFile' type='text' size='30' maxlength='255'/>
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
            <form:label path='email'><spring:message code='email' text='Email'/></form:label>
            <form:input class='form-control' path='email' type='email' size='30' maxlength='30'/>
            <form:errors path='email' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='address.lineOne'><spring:message code='address.lineOne' text='Line One'/></form:label>
            <form:input class='form-control' path='address.lineOne' type='text' size='30' maxlength='50'/>
            <form:errors path='address.lineOne' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='address.lineTwo'><spring:message code='address.lineTwo' text='Line Two'/></form:label>
            <form:input class='form-control' path='address.lineTwo' type='text' size='30' maxlength='50'/>
            <form:errors path='address.lineTwo' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='address.street'><spring:message code='address.street' text='Street'/></form:label>
            <form:input class='form-control' path='address.street' type='text' size='30' maxlength='30'/>
            <form:errors path='address.street' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='address.city'><spring:message code='address.city' text='City'/></form:label>
                <c:url var='address_city' value='/institute/getPhoto/${institute.address.city}'/>
                <img height="110px" width="90px" alt='${institute.address.city}' src='${address_city}'/>
                <form:hidden path='address.city'/>
                <input id='address_cityOBJ' name='address_cityOBJ' type='file' accept='image/*'/>
            <form:errors path='address.city' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='address.zip'><spring:message code='address.zip' text='Zip'/></form:label>
            <form:input class='form-control' path='address.zip' type='text' size='30' maxlength='10'/>
            <form:errors path='address.zip' cssClass='error' element='div'/>
        </div>
    </div>

</div>   