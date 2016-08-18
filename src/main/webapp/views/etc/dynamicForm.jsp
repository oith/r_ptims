<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="main" >

    <tiles:putAttribute name="body">

        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->
            <section class="content-header"><!-- Content Header (Page header) -->
                <h1>${processFullName}</h1>
            </section><!-- /.content-header -->

            <section class="content-messages">
                <%--<jsp:include page="../layouts/_flashMessage.jsp"/>--%>
            </section><!-- /.flesh-message -->

            <section class="content"><!-- Main content -->
                <div class="box box-primary">
                    <div class="box-body">
                        <input name="processId" id="processId" type="hidden" value="${processId}"/>
                        <jsp:include page='_dynamicProcess.jsp' />
                    </div><!-- /.box-body -->
                </div><!-- /.box box-primary -->
            </section><!-- /.content -->
        </div><!-- /.content-wrapper -->

    </tiles:putAttribute>

</tiles:insertDefinition>

<script type='text/javascript'>
    $(document).ready(function () {

        hideAjaxLoadingImageProc();
        $('#LoadingImageLoadProcess').show();
        $('#buttonContent').empty();
        $('#errMsg').empty();
        $('#fixedParam').empty();
        $('#fixedParameterHeader').empty();
        $('#outputMsg').empty();
        $('#qparams').empty();
        $('#searchButton').empty();
        $('#searchButtonContent').empty();
        $('#searchContent').empty();
        $('#searchParameterHeader').empty();
        $('#searchParameterHeader').empty();
        $('#tableContent').empty();
        $('#totalRecordDiv').empty();

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/processCenter/getDynamicContent',
            data: {
                processId: $('#processId').val()
            },
            async: false,
            success: function (d) {
                hideAjaxLoadingImageProc();

                if (d.searchContent !== null && d.searchContent !== '') {
                    $('#searchParameterHeader').append("<div class='form-group'><h4><spring:message code='search.Parameter' text='Search Parameter'/></h4></div>");
                    $('#searchContent').append(d.searchContent.toString());
                    $('#searchButton').append("<button onclick='getDynamicTable()' class='btn btn-info' title='Press to Search' type='button' name='search' id='search' ><i class = 'glyphicon glyphicon-search'></i><spring:message code='search.form.submit.label' text='Search'/></button>");
                }

                if (d.fixedParam !== null && d.fixedParam !== '') {
                    $('#fixedParameterHeader').append("<div class='form - group'><h4><spring:message code='fixed.Parameter' text='Fixed Parameter'/></h4></div>");
                    $('#fixedParam').append(d.fixedParam.toString());
                }

                $('#buttonContent').append(d.processButton.toString());
                $('#qparams').append(d.qparams.toString());
            },
            error: function (err) {
                alert('No Process Is Configured: ' + err);
                hideAjaxLoadingImageProc();
            }
        });
    });
</script>

