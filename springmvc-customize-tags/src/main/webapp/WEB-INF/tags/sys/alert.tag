<%@ tag language="java" pageEncoding="UTF-8"%>
<%--<%@ include file="/WEB-INF/views/include/taglib.jsp"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="dialogID" type="java.lang.String" required="true" description="消息盒子ID"%>
<%@ attribute name="content" type="java.lang.String" required="true" description="消息内容"%>
<%@ attribute name="type" type="java.lang.String" description="消息类型：info、success、warning、danger"%>
<%@ attribute name="btnID" type="java.lang.String" required="true" description="触发弹出框事件元素ID"%>
<c:if test="${not empty content}">
    <c:if test="${not empty type}">
        <c:set var="ctype" value="${type}"/>
    </c:if>
    <c:if test="${empty type}">
        <c:set var="ctype" value="info"/>
    </c:if>
    <div class="modal fade" id="${dialogID}">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h6 class="modal-title">弹出框标题</h6>
                </div>
                <div class="modal-body">
                    <div class="alert alert-${ctype}">${content}</div>
                </div>
                <div class="modal-footer">
                    <fieldset id="delFormField">
                        <div class="form-group">
                            <button type="button" class="btn btn-primary" id="btnYes">确定</button>
                            <button type="button" class="btn btn-default" id="btnCancel"  data-dismiss="modal">取消</button>
                        </div>
                    </fieldset>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $(function(){
            var btnID="${btnID}";
            var dialogID="${dialogID}";

            $('#'+btnID).on("click",function(){
                $("#"+dialogID).modal("show");
            });
        });
    </script>
</c:if>
