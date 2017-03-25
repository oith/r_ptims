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
                <h1><spring:message code="default.button.report.label" text="Query Center"/></h1>
            </section><!-- /.content-header -->

            <section class="content-messages">
                <%--<jsp:include page="../layouts/_flashMessage.jsp"/>--%>
            </section><!-- /.flesh-message -->

            <section class="content"><!-- Main content -->
                <div class="box box-primary">

                    <h1>Query Center</h1>
                    <table>
                        <tr>
                            <td>
                                Query:
                            </td>
                            <td>   
                                <textarea id="query" rows="4" cols="50">SELECT m FROM AuthMenu m</textarea>
                            </td>
                            <td>
                                <button type="button" onclick="execOrmQuery()">Execute ORM</button>
                                <br>
                                <button type="button" onclick="execNativeQuery()">Execute Native</button>
                            </td>
                        </tr>

                    </table>

                    <div style="height: 400px; width: 995px; margin: auto; overflow-x: auto;">
                        <p id="datatab"></p>
                    </div>
                </div><!-- /.create-zxLookup -->
            </section><!-- /.content -->
        </div><!-- /.content-wrapper -->
    </tiles:putAttribute>

</tiles:insertDefinition>

<script>
    function execNativeQuery() {

        var elem = document.getElementById("query");
        var query = elem.value;

        $.ajax({
            type: "GET",
            url: '${pageContext.request.contextPath}/queryCenter/execNativeQuery',
            data: {query: query},
            success: function (d) {
                //  alert("ok:"+d.id)
                document.getElementById('datatab').innerHTML = d;
            },
            error: function (err) {
                //alert("err mac:"+err)
                document.getElementById('datatab').innerHTML = err;
            }
        });
    }
    function execOrmQuery() {

        var elem = document.getElementById("query");
        var query = elem.value;

        $.ajax({
            type: "GET",
            url: '${pageContext.request.contextPath}/queryCenter/execOrmQuery',
            data: {query: query},
            success: function (d) {
                //  alert("ok:"+d.id)
                document.getElementById('datatab').innerHTML = d;
            },
            error: function (err) {
                // alert("query"+err+">>>")
                //alert("err mac:"+err)
                document.getElementById('datatab').innerHTML = err;
            }
        });
    }
</script>