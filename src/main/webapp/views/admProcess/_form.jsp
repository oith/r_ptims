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
    function getCodableDTOAdmModule(code, lblCaption){
        $.ajax({
            type : 'GET',
            url: '${pageContext.request.contextPath}/admModule/getCodableDTO',
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
            <form:label class='required' path='code'><spring:message code='code' text='Code'/></form:label>
            <form:input class='form-control' path='code' type='text' required='true' size='30' maxlength='20'/>
            <form:errors path='code' cssClass='error' element='div'/>
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
            <form:label class='required' path='admModule'><spring:message code='admModule' text='Adm Module'/></form:label>
            <form:input class='form-control' path='admModule.code' required='true' onkeyup='getCodableDTOAdmModule(this.value,"admModule_caption")' type='text' size='8'/>
            <label id='admModule_caption'>${admProcess.admModule.fullName}</label>
            <form:errors path='admModule' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='query'><spring:message code='query' text='Query'/></form:label>
            <form:textarea class='form-control' path='query' type='text' size='30' maxlength='255'/>
            <form:errors path='query' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='queryAlias'><spring:message code='queryAlias' text='Query Alias'/></form:label>
            <form:textarea class='form-control' path='queryAlias' type='text' size='30' maxlength='500'/>
            <form:errors path='queryAlias' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='isActive'><spring:message code='isActive' text='Is Active'/></form:label>
            <br><form:checkbox class='cb' path='isActive'/>
            <form:errors path='isActive' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='slNo'><spring:message code='slNo' text='Sl No'/></form:label>
            <form:input class='form-control' path='slNo' type='number' size='15' maxlength='15'/>
            <form:errors path='slNo' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='paramSearchs'><spring:message code='paramSearchs' text='Param Searchs'/></form:label>
            <form:textarea class='form-control' path='paramSearchs' type='text' size='30' maxlength='1000'/>
            <form:errors path='paramSearchs' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='paramFixeds'><spring:message code='paramFixeds' text='Param Fixeds'/></form:label>
            <form:textarea class='form-control' path='paramFixeds' type='text' size='30' maxlength='1000'/>
            <form:errors path='paramFixeds' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='processBtns'><spring:message code='processBtns' text='Process Btns'/></form:label>
            <form:textarea class='form-control' path='processBtns' type='text' size='30' maxlength='1000'/>
            <form:errors path='processBtns' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='remarks'><spring:message code='remarks' text='Remarks'/></form:label>
            <form:textarea class='form-control' path='remarks' type='text' size='30' maxlength='500'/>
            <form:errors path='remarks' cssClass='error' element='div'/>
        </div>
    </div>

</div>   