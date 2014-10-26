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
</head>
<body>
    <div class="container">
        <jsp:include page="navigation.jsp"/>
        <h2>
            File List
        </h2>

        <div id="btnContainer" style="margin-bottom:5px;">
            <button id="updateBtn" class="btn btn-link" onclick="openDialog4Updating()">Edit</button>
            <a id="exportData" class="btn btn-link" href="/report.csv">Export All Records</a>
        </div>
        <div>
            <table id="gridTable"></table>
            <div id="gridPager"></div>
        </div>
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
                            <th>Title：</th>
                            <td>
                                <input type="text" class="textField" id="title" name="title"/>
                            </td>
                        </tr>
                        <tr>
                            <th>Sub Title：</th>
                            <td>
                                <input type="text" class="textField" id="subtitle" name="subtitle"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</body>
</html>

<script>
    $(function(){
        // 配置jqGrid组件
        $("#gridTable").jqGrid({
            url: "filepage",
            datatype: "json",
            mtype: "GET",
            height: 350,
            autowidth: true,
            colModel: [
                {name:"id",index:"id",label:"ID",width:20,sortable:false,formatter:idFormat},
                {name:"fileName",index:"fileName",label:"File Name",width:60,sortable:false},
                {name:"title",index:"title",label:"Title",width:60,sortable:false,editable:true},
                {name:"subTitle",index:"subTitle",label:"Sub Title",width:60,sortable:false,editable:true},
                {name:"thumbUrl",index:"thumbUrl",label:"Thumb URL",width:60,sortable:false,formatter:urlImageFormat},
                {name:"movieUrl",index:"movieUrl",label:"Movie URL",width:60,sortable:false,formatter:urlMovieFormat},
                {name:"movieStorePath",index:"movieStorePath",label:"Movie Store Path",width:60,sortable:false},
                {name:"thumbStorePath",index:"thumbStorePath",label:"Thumb Store Path",width:60,sortable:false},
                {name:"createTime",index:"createTime",label:"Create Time",width:60,sortable:false},
                {name:"updateTime",index:"updateTime",label:"Update Time",width:60,sortable:false},
                {name:"thumbUrl",index:"thumbImage",label:"Thumb Image",width:60,align:"center", sortable:false,formatter:imageFormat}
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
            editurl:"del",
            hidegrid: false
        });
        $("#gridTable").jqGrid('navGrid','#gridPager',{edit:false,add:false,del:true,search:false});

        // 配置对话框
        $("#consoleDlg").dialog({
            autoOpen: false,
            modal: true,    // 设置对话框为模态（modal）对话框
            resizable: true,
            width: 400,
            buttons: {    // 为对话框添加按钮
                "Save": updateContact,
                "Cancel": function() {
                    $("#consoleDlg").dialog("close")
                }
            }
        });
        //updateBtn初始化样式
        $("#updateBtn").button();
        $("#exportData").button();
    });
    //点击ID预览
    function idFormat(cellvalue,options,rowObject){
        return "<a id='unpacked_rowid_"+rowObject.id+"' style='color:blue;text-decoration:underline;' href='${pageContext.request.contextPath}/admin/play?id="+rowObject.id+"' target='_blank'>"+rowObject.id+"</a>";
    }

    //定制jqGrid列，使其显示图片
    function imageFormat(cellvalue, options, rowObject) {
        return '<a href="'+urlExists(cellvalue)+'"><img style="width:60px;height:60px;border:0px;padding-top:3px;" src="' + urlExists(cellvalue) + '" /></a>';
    }
    //定制jqGrid列，使URL可点击
    function urlImageFormat(cellvalue,options,rowObject){
        return '<a href="'+urlExists(cellvalue)+'">'+urlExists(cellvalue)+'</a>';
    }
    //定制jqGrid列，使URL可点击
    function urlMovieFormat(cellvalue,options,rowObject){
        return '<a href="'+urlExists(cellvalue)+'">'+urlExists(cellvalue)+'</a>';
    }
    //检查服务器端文件是否存在,若不存在使用默认图片
    function urlExists(testUrl) {
        var http = $.ajax({
            type:"HEAD",
            url: testUrl,
            async: false
        })
        if (http.status != 404) {
            return testUrl;
        } else {
            var loc = window.location;
            var baseURL = loc.protocol + "//" + loc.host;
            return baseURL + "/images/noshootcut_image.png"
        }
    }
    //打开编辑对话框
    var openDialog4Updating = function() {
        var consoleDlg = $("#consoleDlg");
        var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");
        consoleDlg.find("input").removeAttr("disabled");
        dialogButtonPanel.find("button:not(:contains('Cancel'))").hide();
        dialogButtonPanel.find("button:contains('Save')").show();
        consoleDlg.dialog("option", "title", "Edit FileInfo");
        consoleDlg.find("#msgbox").hide();
        //取得jqGrid选中行的ID
        var selectedRowIds = $("#gridTable").jqGrid("getGridParam", "selarrrow");
        // alert(typeof selectedRowId );
        if (selectedRowIds.length <= 0) {
            alert("Please select a line to edit!");
            return false;
        } else {
            //alert(selectedRowIds instanceof Array);
            if (selectedRowIds.length > 1) {
                alert("Only one line can be edited once a time!");
                return false;
            }
        }
        //jqGrid根据ID取得行的值
        var rowData= $("#gridTable").jqGrid("getRowData", selectedRowIds[0]);
        //alert(rowData.toString()+","+rowData.title+","+rowData.subTitle);
        consoleDlg.find("#selectedId").val(selectedRowIds[0]);
        consoleDlg.find("#title").val(rowData.title);
        consoleDlg.find("#subtitle").val(rowData.subTitle);
        //打开对话框
        consoleDlg.dialog("open");
    }
    //修改服务器数据
    var updateContact = function() {
        var consoleDlg = $("#consoleDlg");
        var id = $.trim(consoleDlg.find("#selectedId").val());
        var title = $.trim(consoleDlg.find("#title").val());
        var subtitle = $.trim(consoleDlg.find("#subtitle").val());
        var params = {
            "id" : id,
            "title" : title,
            "subtitle" : subtitle
        };
        var actionUrl = "updateMovie";
        $.ajax({
            url : actionUrl,
            data : params,
            dataType : "json",
            cache : false,
            error : function(textStatus, errorThrown) {
                alert("ajax error!: " + textStatus+","+errorThrown);
            },
            success : function(data, textStatus) {
                if (data.ajaxResult == "success") {
                    var dataRow = {
                        title : data.respObj.title,
                        subTitle : data.respObj.subTitle,
                        updateTime : data.respObj.updateTime
                    };
                    $("#gridTable").jqGrid("setRowData", data.respObj.id, dataRow, {color:"#FF0000"});
                    consoleDlg.dialog("close");
                    alert("Successfully edited movie!");
                } else {
                    alert("Edit failed!");
                }
            }
        });
    };
</script>