<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>

<head>
<title>Files Manage</title>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/redmond/jquery-ui-1.8.21.custom.css">
<link type="text/css" rel="stylesheet" href="${contextPath}/css/jqGrid/ui.jqgrid.css">
<link type="text/css" rel="stylesheet" href="${contextPath}/js/bootstrap/css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css">

<script type="text/javascript" src="${contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/jqGrid/grid.locale-en.js"></script>
<script type="text/javascript" src="${contextPath}/js/jqGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery-ui-1.8.21.custom.min.js"></script>



<title>Files Manage</title>
</head>
<body>
  <div class="container">
      <jsp:include page="navigation.jsp"/>
      <h2>
          All Users
      </h2>
      <%--
          <c:choose>
              <c:when test="${empty filelist}">
                <p style="text-align:center;">No Data！</p>
              </c:when>
              <c:otherwise>
                      <table>
                          <tr>
                              <td>
                                  File Name
                              </td>
                              <td>
                                  File Path
                              </td>
                              <td>
                                  Uploaded Date
                              </td>
                              <td>

                              </td>
                          </tr>
                          <c:forEach var="fileinfo" items="${filelist}" varStatus="status">
                              <tr>
                              <td>${fileinfo.fileName }</td>
                              <td>${fileinfo.filePath}</td>
                              <td>${fileinfo.uploadDate }</td>
                              <td>
                                  <!--
                                  <a href="javascript:void(0)" onclick="javascript:self.location='<%=request.getContextPath() %>scanInfo/${fileinfo.id }'">DETAIL</a>
                                  -->
                                  <a href="javascript:void(0)" onclick="javascript:self.location='<%=request.getContextPath() %>delFile/${fileinfo.id }'">DELETE</a>
                              </td>
                              </tr>
                          </c:forEach>
                      </table>
              </c:otherwise>
          </c:choose>
      --%>
      <div id="btnContainer">
          <button id="addBtn" class="btn btn-link" onclick="openDialog4Adding()">Add User</button>
          <button id="updateBtn" class="btn btn-link" onclick="openDialog4Updating()">Edit User</button>
          <button id="deleteBtn" class="btn btn-link" onclick="openDialog4Deleting()">Delete User</button>
      </div>
      <div>
          <table id="gridTable"></table>
          <div id="gridPager"></div>
      </div>
      <br/>
      <div id="consoleDlg">
          <div id="formContainer">
              <form id="consoleForm">
                  <input type="hidden" id="selectId"/>
                  <table class="formTable">
                      <tr>
                          <th colspan="2"><span id="msgbox" style="color:red;">aaaa</span></th>
                          <input type="hidden" class="textField" id="selectedId" name="selectedId"/>
                      </tr>
                      <tr>
                          <th>Username：</th>
                          <td>
                              <input type="text" class="textField" id="username" name="username"/>
                          </td>
                      </tr>
                      <tr>
                          <th>Password：</th>
                          <td>
                              <input type="password" class="textField" id="password" name="password"/>
                          </td>
                      </tr>
                  </table>
              </form>
          </div>
      </div>
      <div id="deleteDlg">
          <div id="msgContainer"></div>
          <input type="hidden" class="textField" id="delSelectedIds" name="delSelectedIds"/>
      </div>
  </div>
</body>
</html>

<script>
$(function() {
    //button配置
    $("#btnContainer").css({"padding-bottom":"10px"});
    $("#addBtn").button();
    $("#updateBtn").button();
    $("#deleteBtn").button();
    // 配置jqGrid组件
    $("#gridTable").jqGrid({
        url: "userpage",
        datatype: "json",
        mtype: "GET",
        height: 350,
        autowidth: true,
        colModel: [
            {name:"id",index:"id",label:"ID",width:20,sortable:false},
            {name:"username",index:"username",label:"User Name",width:60,sortable:false}
        ],
        viewrecords: true,
        rowNum: 15,
        rowList: [15,50,100],
        prmNames: {search: "search"},   //(1)
        jsonReader: {
            root:"rows",       // (2)
            records: "records",      // (3)
            repeatitems : false     // (4)
        },
        pager: "#gridPager",
        caption: "File List",
        multiselect: true,
        hidegrid: false
    });
    $("#gridTable").jqGrid('navGrid', '#gridPager', {edit:false,add:false,del:false,search:false});

    // 配置对话框
    $("#consoleDlg").dialog({
        autoOpen: false,
        modal: true,    // 设置对话框为模态（modal）对话框
        resizable: true,
        width: 400,
        buttons: {    // 为对话框添加按钮
            "Add": addContact,
            "Save": updateContact,
            "Cancel": function() {
                $("#consoleDlg").dialog("close")
            }
        }
    });
    // 配置对话框
    $("#deleteDlg").dialog({
        autoOpen: false,
        modal: true,    // 设置对话框为模态（modal）对话框
        resizable: true,
        width: 400,
        buttons: {    // 为对话框添加按钮
            "OK":function(){
                $("#deleteDlg").dialog("close")
            },
            "Delete": deleteUsers,
            "Cancel": function() {
                $("#deleteDlg").dialog("close")
            }
        }
    });
    $("input","#consoleDlg").bind("focus",function(){
        $("#msgbox").html("");
    });
});
var openDialog4Adding = function() {
    var consoleDlg = $("#consoleDlg");
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");
    consoleDlg.find("input").removeAttr("disabled").val("");
    dialogButtonPanel.find("button:not(:contains('Cancel'))").hide();
    dialogButtonPanel.find("button:contains('Add')").show();
    consoleDlg.dialog("option", "title", "Add User").dialog("open");
};
var openDialog4Updating = function() {
    var consoleDlg = $("#consoleDlg");
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");

    consoleDlg.find("input").removeAttr("disabled");
    dialogButtonPanel.find("button:not(:contains('Cancel'))").hide();
    dialogButtonPanel.find("button:contains('Save')").show();
    consoleDlg.dialog("option", "title", "Edit User");

    loadSelectedRowData();
}
var openDialog4Deleting = function() {
    var deleteDlg = $("#deleteDlg");
    var dialogButtonPanel = deleteDlg.siblings(".ui-dialog-buttonpane");
    deleteDlg.find("input").attr("disabled", true);
    var selectedRowId = $("#gridTable").jqGrid("getGridParam", "selarrrow");
    if (selectedRowId.length==0) {
        //alert("Please select a line to edit!");
        dialogButtonPanel.find("button:not(:contains('OK'))").hide();
        dialogButtonPanel.find("button:contains('OK')").show();
        deleteDlg.find("#msgContainer").html("Please select a line to edit!");
    }else if(selectedRowId.length==1&&( $("#gridTable").jqGrid('getRowData',selectedRowId[0]).username)=="admin"){
        dialogButtonPanel.find("button:not(:contains('OK'))").hide();
        dialogButtonPanel.find("button:contains('OK')").show();
        deleteDlg.find("#msgContainer").html("<span style='color:red;'>'Admin'</span> account can not be deleted!");
    }else{
        dialogButtonPanel.find("button:not(:contains('Cancel'))").hide();
        dialogButtonPanel.find("button:contains('Cancel')").show();
        dialogButtonPanel.find("button:contains('Delete')").show();
        deleteDlg.find("#msgContainer").html("Delete selected record(s)?");
        deleteDlg.find("#delSelectedIds").attr("value",selectedRowId);
    }
    deleteDlg.dialog("option", "title", "Delete User");
    // 打开对话框
    deleteDlg.dialog("open");
}
var loadSelectedRowData = function() {
    var selectedRowIds = $("#gridTable").jqGrid("getGridParam", "selarrrow");
    // alert(typeof selectedRowId );
    if (selectedRowIds.length<=0) {
        alert("Please select a line to edit!");
        return false;
    } else {
        //alert(selectedRowIds instanceof Array);
        if(selectedRowIds.length>1){
            alert("Only one line can be edited once a time!");
            return false;
        }
        var params = {
            "id" : selectedRowIds[0]
        };
        var actionUrl = "getUser";
        // 从Server读取对应ID的JSON数据
        $.ajax({
            url : actionUrl,
            data : params,
            dataType : "json",
            cache : false,
            error : function(textStatus, errorThrown) {
                alert("ajax error!: " + textStatus);
            },
            success : function(data, textStatus) {
                // 如果读取结果成功，则将信息载入到对话框中
                var rowData = data.respObj;
                var consoleDlg = $("#consoleDlg");
                consoleDlg.find("#selectedId").val(rowData.id);
                consoleDlg.find("#username").val(rowData.username);
                consoleDlg.find("#password").val(rowData.password);
                consoleDlg.find("#username").attr("disabled","disabled");

                // 根据新载入的数据将表格中的对应数据行一并更新一下
                var dataRow = {
                    id : rowData.id,
                    lastName : rowData.username
                };

                $("#gridTable").jqGrid("setRowData", data.respObj.id, dataRow);

                // 打开对话框
                consoleDlg.dialog("open");
            }
        });

    }
};
var addContact = function() {
    var consoleDlg = $("#consoleDlg");

    var username = $.trim(consoleDlg.find("#username").val());
    var password = $.trim(consoleDlg.find("#password").val());

    var params = {
        "username" :username,
        "password" : password
    };

    var actionUrl = "addUser"

    $.ajax({
        url : actionUrl,
        data : params,
        dataType : "json",
        cache : false,
        error : function(textStatus, errorThrown) {
            alert("ajax error!: " + textStatus);
        },
        success : function(data, textStatus) {
            /**
             * 数据格式：
             {
             "ajaxResult": "success",
             "user": {
             "id": "4",
             "username": "cccc",
             "password": "cccc"
             }
             }
             **/
            if (data.ajaxResult == "success") {
                var dataRow = {
                    id : data.respObj.id,    // 从Server端得到系统分配的id
                    username : data.respObj.username
                };
                var srcrowid = $("#gridTable").jqGrid("getGridParam", "selrow");

                if (srcrowid) {
                    $("#gridTable").jqGrid("addRowData", data.respObj.id, dataRow, "after", srcrowid);
                } else {
                    $("#gridTable").jqGrid("addRowData", data.respObj.id, dataRow, "first");
                }
                consoleDlg.dialog("close");

                alert("Successfully added user!");

            } else {
                //alert("Add failed!" + data.message);
                $("#msgbox").html(data.message);
            }
        }
    });
};
var updateContact = function() {
    var consoleDlg = $("#consoleDlg");

    var id = $.trim(consoleDlg.find("#selectedId").val());
    var username = $.trim(consoleDlg.find("#username").val());
    var password = $.trim(consoleDlg.find("#password").val());
    var params = {
        "id" : id,
        "username" : username,
        "password" : password
    };
    var actionUrl = "updateUser";
    $.ajax({
        url : actionUrl,
        data : params,
        dataType : "json",
        cache : false,
        error : function(textStatus, errorThrown) {
            alert("ajax error!: " + textStatus);
        },
        success : function(data, textStatus) {
            if (data.ajaxResult == "success") {
                var dataRow = {
                    id : data.respObj.id,
                    username : data.respObj.username
                };
                $("#gridTable").jqGrid("setRowData", data.respObj.id, dataRow, {color:"#FF0000"});

                alert("Successfully edited user!");

                consoleDlg.dialog("close");

            } else {
                alert("Edit failed!");
            }
        }
    });
};
var deleteUsers = function() {
    var deleteDlg = $("#deleteDlg");

    var ids = $.trim(deleteDlg.find("#delSelectedIds").val());
    var params = {
        "ids" : ids
    };
    var actionUrl = "delUser";
    $.ajax({
        url : actionUrl,
        data : params,
        dataType : "json",
        cache : false,
        error : function(textStatus, errorThrown) {
            alert("ajax error!: " + textStatus);
        },
        success : function(data, textStatus) {
            if (data.ajaxResult == "success") {
                var allIds=ids.split(",");
                $.each(allIds,function(i,id){
                    //alert(allIds[i]+","+id+",adminId:"+data.adminId);
                    if(id!=data.adminId){
                        $("#gridTable").jqGrid("delRowData", id);
                    }
                });
                deleteDlg.dialog("close");
                alert("Successfully deleted user!");
            } else {
                alert("Delete failed!");
            }
        }
    });
};

</script>