<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en-US">
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta charset="windows-1252">
    <meta name="viewport" content="width=device-width">
    <meta name="Keywords"
          content="HTML,CSS,XML,JavaScript,DOM,jQuery,ASP.NET,PHP,SQL,colors,tutorial,programming,development,training,learning,quiz,primer,lessons,reference,examples,source code,demos,tips,color table,w3c,cascading style sheets,active server pages,Web building,Webmaster">
    <meta name="Description"
          content="Free HTML CSS JavaScript DOM jQuery XML AJAX RSS ASP .NET PHP SQL tutorials, references, examples for web building.">
    <title>HTML Color Mixer</title>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/stdtheme.css"/>
    <style>
        .copycolor{
            text-decoration: none;
            color:blue;
        }
    </style>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script>
        $(function(){
            $(document).delegate(".copycolor", "click",function(e){
                alert($(e.target).parent().prev().text());
                console.log($(e.target).parent().prev());
            });

        });
    </script>
    <script>
        <!--
        var colortophex = "#FF0000"
        var colorbottomhex = "#0000FF"

        function mouseOverColortop(hex) {
            document.getElementById("divpreviewtop").style.backgroundColor = hex
            document.getElementById("divpreviewtxttop").innerHTML = hex
            document.body.style.cursor = "pointer"
        }

        function mouseOverColorbottom(hex) {
            document.getElementById("divpreviewbottom").style.backgroundColor = hex
            document.getElementById("divpreviewtxtbottom").innerHTML = hex
            document.body.style.cursor = "pointer"
        }

        function mouseOutMaptop() {
            document.getElementById("divpreviewtop").style.backgroundColor = colortophex
            document.getElementById("divpreviewtxttop").innerHTML = colortophex
            document.body.style.cursor = ""
        }

        function mouseOutMapbottom() {
            document.getElementById("divpreviewbottom").style.backgroundColor = colorbottomhex
            document.getElementById("divpreviewtxtbottom").innerHTML = colorbottomhex
            document.body.style.cursor = ""
        }

        function clickColor(hex, n, seltop, selleft) {
            var xhttp, c, x, colortop, colorbottom, colorhextop, colorhexbottom
            if (n == 0) {
                x = "bottom"
                colortop = document.getElementById("colortop").value;
                colorbottom = hex
            }
            else {
                x = "top"
                colortop = hex
                colorbottom = document.getElementById("colorbottom").value;
            }
            if (hex == 0) {
                colortop = document.getElementById("colortop").value;
                colorbottom = document.getElementById("colorbottom").value;
            }
            if (colortop.substr(0, 1) == "#") {
                colortop = colortop.substr(1);
            }
            colortop = colortop.substr(0, 10);
            colortophex = "#" + colortop;
            document.getElementById("colortop").value = colortophex;
            if (colorbottom.substr(0, 1) == "#") {
                colorbottom = colorbottom.substr(1);
            }
            colorbottom = colorbottom.substr(0, 10);
            colorbottomhex = "#" + colorbottom;
            document.getElementById("colorbottom").value = colorbottomhex;

            ajaxRequest(seltop,selleft,x,colortop,colorbottom);
//            if (window.XMLHttpRequest) {
//                xhttp = new XMLHttpRequest();
//            }
//            else {
//                xhttp = new ActiveXObject("Microsoft.XMLHTTP");
//            }
//
//            xhttp.open("GET", "mixcolors?colortop=" + colortop + "&colorbottom=" + colorbottom + "&r=" + Math.random(), false);
//            xhttp.send("");
//            document.getElementById("colormixer").innerHTML = xhttp.responseText;
//            if (seltop > -1 && selleft > -1) {
//                document.getElementById("selectedColor" + x).style.top = seltop + "px";
//                document.getElementById("selectedColor" + x).style.left = selleft + "px";
//                document.getElementById("selectedColor" + x).style.visibility = "visible";
//            }
//            else {
//                document.getElementById("divpreviewtop").style.backgroundColor = colortophex;
//                document.getElementById("divpreviewtxttop").innerHTML = colortophex;
//                document.getElementById("selectedColortop").style.visibility = "hidden";
//                document.getElementById("divpreviewbottom").style.backgroundColor = colorbottomhex;
//                document.getElementById("divpreviewtxtbottom").innerHTML = colorbottomhex;
//                document.getElementById("selectedColorbottom").style.visibility = "hidden";
//            }
        }

        function ajaxRequest(seltop,selleft,x,colortop,colorbottom){
            $.ajax({
                type: "POST",
                url: "mixcolors",
                dataType:"html",
                data: "colortop=" + colortop + "&colorbottom=" + colorbottom + "&r=" + Math.random(),
                success: function(responseData){
                    //alert( "Data Saved: " + responseData );
                    document.getElementById("colormixer").innerHTML = responseData.toString();
                    if (seltop > -1 && selleft > -1) {
                        document.getElementById("selectedColor" + x).style.top = seltop + "px";
                        document.getElementById("selectedColor" + x).style.left = selleft + "px";
                        document.getElementById("selectedColor" + x).style.visibility = "visible";
                    }
                    else {
                        document.getElementById("divpreviewtop").style.backgroundColor = colortophex;
                        document.getElementById("divpreviewtxttop").innerHTML = colortophex;
                        document.getElementById("selectedColortop").style.visibility = "hidden";
                        document.getElementById("divpreviewbottom").style.backgroundColor = colorbottomhex;
                        document.getElementById("divpreviewtxtbottom").innerHTML = colorbottomhex;
                        document.getElementById("selectedColorbottom").style.visibility = "hidden";
                    }
                }
            });
        }

        //-->
    </script>
</head>
<body>

<div id="top">
<div style="height:78px;">
    <div id="topLogo">
        <a href="http://www.w3schools.com"><img width="336" height="69" src="images/w3schoolslogoNEW310113.gif"/></a>
    </div>
</div>
<div id="page">
<div style="clear:both;"></div>
<div id='main'>
<h1>HTML <span class="color_h1">Color Mixer</span></h1>

<p class="intro">Mix two colors and see the result.</p>
<hr>
<!--混色区域开始-->
<form action="colormixer" method="get" id="colorform" name="colorform">
<table>
<tr>
<td>Select colors:
<div id='selectedColortop'
     style='visibility:hidden;position:relative;width:21px;height:21px;background-repeat:no-repeat;background-image:url("images/selectedcolor.gif")'></div>
<img style='margin-right:2px;' src='images/colormap.gif' usemap='#colormap_top' alt='colormap'/>
<map name='colormap_top' id='colormap_top' onmouseout='mouseOutMaptop(1)'>
<area style='cursor:pointer' shape='poly' coords='63,0,72,4,72,15,63,19,54,15,54,4'
      onclick='clickColor("#003366",1,20,54)' onmouseover='mouseOverColortop("#003366",1)'
      alt='#003366'/>
<area style='cursor:pointer' shape='poly' coords='81,0,90,4,90,15,81,19,72,15,72,4'
      onclick='clickColor("#336699",1,20,72)' onmouseover='mouseOverColortop("#336699",1)'
      alt='#336699'/>
<area style='cursor:pointer' shape='poly' coords='99,0,108,4,108,15,99,19,90,15,90,4'
      onclick='clickColor("#3366CC",1,20,90)' onmouseover='mouseOverColortop("#3366CC",1)'
      alt='#3366CC'/>
<area style='cursor:pointer' shape='poly' coords='117,0,126,4,126,15,117,19,108,15,108,4'
      onclick='clickColor("#003399",1,20,108)' onmouseover='mouseOverColortop("#003399",1)'
      alt='#003399'/>
<area style='cursor:pointer' shape='poly' coords='135,0,144,4,144,15,135,19,126,15,126,4'
      onclick='clickColor("#000099",1,20,126)' onmouseover='mouseOverColortop("#000099",1)'
      alt='#000099'/>
<area style='cursor:pointer' shape='poly' coords='153,0,162,4,162,15,153,19,144,15,144,4'
      onclick='clickColor("#0000CC",1,20,144)' onmouseover='mouseOverColortop("#0000CC",1)'
      alt='#0000CC'/>
<area style='cursor:pointer' shape='poly' coords='171,0,180,4,180,15,171,19,162,15,162,4'
      onclick='clickColor("#000066",1,20,162)' onmouseover='mouseOverColortop("#000066",1)'
      alt='#000066'/>
<area style='cursor:pointer' shape='poly' coords='54,15,63,19,63,30,54,34,45,30,45,19'
      onclick='clickColor("#006666",1,35,45)' onmouseover='mouseOverColortop("#006666",1)'
      alt='#006666'/>
<area style='cursor:pointer' shape='poly' coords='72,15,81,19,81,30,72,34,63,30,63,19'
      onclick='clickColor("#006699",1,35,63)' onmouseover='mouseOverColortop("#006699",1)'
      alt='#006699'/>
<area style='cursor:pointer' shape='poly' coords='90,15,99,19,99,30,90,34,81,30,81,19'
      onclick='clickColor("#0099CC",1,35,81)' onmouseover='mouseOverColortop("#0099CC",1)'
      alt='#0099CC'/>
<area style='cursor:pointer' shape='poly' coords='108,15,117,19,117,30,108,34,99,30,99,19'
      onclick='clickColor("#0066CC",1,35,99)' onmouseover='mouseOverColortop("#0066CC",1)'
      alt='#0066CC'/>
<area style='cursor:pointer' shape='poly' coords='126,15,135,19,135,30,126,34,117,30,117,19'
      onclick='clickColor("#0033CC",1,35,117)' onmouseover='mouseOverColortop("#0033CC",1)'
      alt='#0033CC'/>
<area style='cursor:pointer' shape='poly' coords='144,15,153,19,153,30,144,34,135,30,135,19'
      onclick='clickColor("#0000FF",1,35,135)' onmouseover='mouseOverColortop("#0000FF",1)'
      alt='#0000FF'/>
<area style='cursor:pointer' shape='poly' coords='162,15,171,19,171,30,162,34,153,30,153,19'
      onclick='clickColor("#3333FF",1,35,153)' onmouseover='mouseOverColortop("#3333FF",1)'
      alt='#3333FF'/>
<area style='cursor:pointer' shape='poly' coords='180,15,189,19,189,30,180,34,171,30,171,19'
      onclick='clickColor("#333399",1,35,171)' onmouseover='mouseOverColortop("#333399",1)'
      alt='#333399'/>
<area style='cursor:pointer' shape='poly' coords='45,30,54,34,54,45,45,49,36,45,36,34'
      onclick='clickColor("#669999",1,50,36)' onmouseover='mouseOverColortop("#669999",1)'
      alt='#669999'/>
<area style='cursor:pointer' shape='poly' coords='63,30,72,34,72,45,63,49,54,45,54,34'
      onclick='clickColor("#009999",1,50,54)' onmouseover='mouseOverColortop("#009999",1)'
      alt='#009999'/>
<area style='cursor:pointer' shape='poly' coords='81,30,90,34,90,45,81,49,72,45,72,34'
      onclick='clickColor("#33CCCC",1,50,72)' onmouseover='mouseOverColortop("#33CCCC",1)'
      alt='#33CCCC'/>
<area style='cursor:pointer' shape='poly' coords='99,30,108,34,108,45,99,49,90,45,90,34'
      onclick='clickColor("#00CCFF",1,50,90)' onmouseover='mouseOverColortop("#00CCFF",1)'
      alt='#00CCFF'/>
<area style='cursor:pointer' shape='poly' coords='117,30,126,34,126,45,117,49,108,45,108,34'
      onclick='clickColor("#0099FF",1,50,108)' onmouseover='mouseOverColortop("#0099FF",1)'
      alt='#0099FF'/>
<area style='cursor:pointer' shape='poly' coords='135,30,144,34,144,45,135,49,126,45,126,34'
      onclick='clickColor("#0066FF",1,50,126)' onmouseover='mouseOverColortop("#0066FF",1)'
      alt='#0066FF'/>
<area style='cursor:pointer' shape='poly' coords='153,30,162,34,162,45,153,49,144,45,144,34'
      onclick='clickColor("#3366FF",1,50,144)' onmouseover='mouseOverColortop("#3366FF",1)'
      alt='#3366FF'/>
<area style='cursor:pointer' shape='poly' coords='171,30,180,34,180,45,171,49,162,45,162,34'
      onclick='clickColor("#3333CC",1,50,162)' onmouseover='mouseOverColortop("#3333CC",1)'
      alt='#3333CC'/>
<area style='cursor:pointer' shape='poly' coords='189,30,198,34,198,45,189,49,180,45,180,34'
      onclick='clickColor("#666699",1,50,180)' onmouseover='mouseOverColortop("#666699",1)'
      alt='#666699'/>
<area style='cursor:pointer' shape='poly' coords='36,45,45,49,45,60,36,64,27,60,27,49'
      onclick='clickColor("#339966",1,65,27)' onmouseover='mouseOverColortop("#339966",1)'
      alt='#339966'/>
<area style='cursor:pointer' shape='poly' coords='54,45,63,49,63,60,54,64,45,60,45,49'
      onclick='clickColor("#00CC99",1,65,45)' onmouseover='mouseOverColortop("#00CC99",1)'
      alt='#00CC99'/>
<area style='cursor:pointer' shape='poly' coords='72,45,81,49,81,60,72,64,63,60,63,49'
      onclick='clickColor("#00FFCC",1,65,63)' onmouseover='mouseOverColortop("#00FFCC",1)'
      alt='#00FFCC'/>
<area style='cursor:pointer' shape='poly' coords='90,45,99,49,99,60,90,64,81,60,81,49'
      onclick='clickColor("#00FFFF",1,65,81)' onmouseover='mouseOverColortop("#00FFFF",1)'
      alt='#00FFFF'/>
<area style='cursor:pointer' shape='poly' coords='108,45,117,49,117,60,108,64,99,60,99,49'
      onclick='clickColor("#33CCFF",1,65,99)' onmouseover='mouseOverColortop("#33CCFF",1)'
      alt='#33CCFF'/>
<area style='cursor:pointer' shape='poly' coords='126,45,135,49,135,60,126,64,117,60,117,49'
      onclick='clickColor("#3399FF",1,65,117)' onmouseover='mouseOverColortop("#3399FF",1)'
      alt='#3399FF'/>
<area style='cursor:pointer' shape='poly' coords='144,45,153,49,153,60,144,64,135,60,135,49'
      onclick='clickColor("#6699FF",1,65,135)' onmouseover='mouseOverColortop("#6699FF",1)'
      alt='#6699FF'/>
<area style='cursor:pointer' shape='poly' coords='162,45,171,49,171,60,162,64,153,60,153,49'
      onclick='clickColor("#6666FF",1,65,153)' onmouseover='mouseOverColortop("#6666FF",1)'
      alt='#6666FF'/>
<area style='cursor:pointer' shape='poly' coords='180,45,189,49,189,60,180,64,171,60,171,49'
      onclick='clickColor("#6600FF",1,65,171)' onmouseover='mouseOverColortop("#6600FF",1)'
      alt='#6600FF'/>
<area style='cursor:pointer' shape='poly' coords='198,45,207,49,207,60,198,64,189,60,189,49'
      onclick='clickColor("#6600CC",1,65,189)' onmouseover='mouseOverColortop("#6600CC",1)'
      alt='#6600CC'/>
<area style='cursor:pointer' shape='poly' coords='27,60,36,64,36,75,27,79,18,75,18,64'
      onclick='clickColor("#339933",1,80,18)' onmouseover='mouseOverColortop("#339933",1)'
      alt='#339933'/>
<area style='cursor:pointer' shape='poly' coords='45,60,54,64,54,75,45,79,36,75,36,64'
      onclick='clickColor("#00CC66",1,80,36)' onmouseover='mouseOverColortop("#00CC66",1)'
      alt='#00CC66'/>
<area style='cursor:pointer' shape='poly' coords='63,60,72,64,72,75,63,79,54,75,54,64'
      onclick='clickColor("#00FF99",1,80,54)' onmouseover='mouseOverColortop("#00FF99",1)'
      alt='#00FF99'/>
<area style='cursor:pointer' shape='poly' coords='81,60,90,64,90,75,81,79,72,75,72,64'
      onclick='clickColor("#66FFCC",1,80,72)' onmouseover='mouseOverColortop("#66FFCC",1)'
      alt='#66FFCC'/>
<area style='cursor:pointer' shape='poly' coords='99,60,108,64,108,75,99,79,90,75,90,64'
      onclick='clickColor("#66FFFF",1,80,90)' onmouseover='mouseOverColortop("#66FFFF",1)'
      alt='#66FFFF'/>
<area style='cursor:pointer' shape='poly' coords='117,60,126,64,126,75,117,79,108,75,108,64'
      onclick='clickColor("#66CCFF",1,80,108)' onmouseover='mouseOverColortop("#66CCFF",1)'
      alt='#66CCFF'/>
<area style='cursor:pointer' shape='poly' coords='135,60,144,64,144,75,135,79,126,75,126,64'
      onclick='clickColor("#99CCFF",1,80,126)' onmouseover='mouseOverColortop("#99CCFF",1)'
      alt='#99CCFF'/>
<area style='cursor:pointer' shape='poly' coords='153,60,162,64,162,75,153,79,144,75,144,64'
      onclick='clickColor("#9999FF",1,80,144)' onmouseover='mouseOverColortop("#9999FF",1)'
      alt='#9999FF'/>
<area style='cursor:pointer' shape='poly' coords='171,60,180,64,180,75,171,79,162,75,162,64'
      onclick='clickColor("#9966FF",1,80,162)' onmouseover='mouseOverColortop("#9966FF",1)'
      alt='#9966FF'/>
<area style='cursor:pointer' shape='poly' coords='189,60,198,64,198,75,189,79,180,75,180,64'
      onclick='clickColor("#9933FF",1,80,180)' onmouseover='mouseOverColortop("#9933FF",1)'
      alt='#9933FF'/>
<area style='cursor:pointer' shape='poly' coords='207,60,216,64,216,75,207,79,198,75,198,64'
      onclick='clickColor("#9900FF",1,80,198)' onmouseover='mouseOverColortop("#9900FF",1)'
      alt='#9900FF'/>
<area style='cursor:pointer' shape='poly' coords='18,75,27,79,27,90,18,94,9,90,9,79'
      onclick='clickColor("#006600",1,95,9)' onmouseover='mouseOverColortop("#006600",1)'
      alt='#006600'/>
<area style='cursor:pointer' shape='poly' coords='36,75,45,79,45,90,36,94,27,90,27,79'
      onclick='clickColor("#00CC00",1,95,27)' onmouseover='mouseOverColortop("#00CC00",1)'
      alt='#00CC00'/>
<area style='cursor:pointer' shape='poly' coords='54,75,63,79,63,90,54,94,45,90,45,79'
      onclick='clickColor("#00FF00",1,95,45)' onmouseover='mouseOverColortop("#00FF00",1)'
      alt='#00FF00'/>
<area style='cursor:pointer' shape='poly' coords='72,75,81,79,81,90,72,94,63,90,63,79'
      onclick='clickColor("#66FF99",1,95,63)' onmouseover='mouseOverColortop("#66FF99",1)'
      alt='#66FF99'/>
<area style='cursor:pointer' shape='poly' coords='90,75,99,79,99,90,90,94,81,90,81,79'
      onclick='clickColor("#99FFCC",1,95,81)' onmouseover='mouseOverColortop("#99FFCC",1)'
      alt='#99FFCC'/>
<area style='cursor:pointer' shape='poly' coords='108,75,117,79,117,90,108,94,99,90,99,79'
      onclick='clickColor("#CCFFFF",1,95,99)' onmouseover='mouseOverColortop("#CCFFFF",1)'
      alt='#CCFFFF'/>
<area style='cursor:pointer' shape='poly' coords='126,75,135,79,135,90,126,94,117,90,117,79'
      onclick='clickColor("#CCCCFF",1,95,117)' onmouseover='mouseOverColortop("#CCCCFF",1)'
      alt='#CCCCFF'/>
<area style='cursor:pointer' shape='poly' coords='144,75,153,79,153,90,144,94,135,90,135,79'
      onclick='clickColor("#CC99FF",1,95,135)' onmouseover='mouseOverColortop("#CC99FF",1)'
      alt='#CC99FF'/>
<area style='cursor:pointer' shape='poly' coords='162,75,171,79,171,90,162,94,153,90,153,79'
      onclick='clickColor("#CC66FF",1,95,153)' onmouseover='mouseOverColortop("#CC66FF",1)'
      alt='#CC66FF'/>
<area style='cursor:pointer' shape='poly' coords='180,75,189,79,189,90,180,94,171,90,171,79'
      onclick='clickColor("#CC33FF",1,95,171)' onmouseover='mouseOverColortop("#CC33FF",1)'
      alt='#CC33FF'/>
<area style='cursor:pointer' shape='poly' coords='198,75,207,79,207,90,198,94,189,90,189,79'
      onclick='clickColor("#CC00FF",1,95,189)' onmouseover='mouseOverColortop("#CC00FF",1)'
      alt='#CC00FF'/>
<area style='cursor:pointer' shape='poly' coords='216,75,225,79,225,90,216,94,207,90,207,79'
      onclick='clickColor("#9900CC",1,95,207)' onmouseover='mouseOverColortop("#9900CC",1)'
      alt='#9900CC'/>
<area style='cursor:pointer' shape='poly' coords='9,90,18,94,18,105,9,109,0,105,0,94'
      onclick='clickColor("#003300",1,110,0)' onmouseover='mouseOverColortop("#003300",1)'
      alt='#003300'/>
<area style='cursor:pointer' shape='poly' coords='27,90,36,94,36,105,27,109,18,105,18,94'
      onclick='clickColor("#009933",1,110,18)' onmouseover='mouseOverColortop("#009933",1)'
      alt='#009933'/>
<area style='cursor:pointer' shape='poly' coords='45,90,54,94,54,105,45,109,36,105,36,94'
      onclick='clickColor("#33CC33",1,110,36)' onmouseover='mouseOverColortop("#33CC33",1)'
      alt='#33CC33'/>
<area style='cursor:pointer' shape='poly' coords='63,90,72,94,72,105,63,109,54,105,54,94'
      onclick='clickColor("#66FF66",1,110,54)' onmouseover='mouseOverColortop("#66FF66",1)'
      alt='#66FF66'/>
<area style='cursor:pointer' shape='poly' coords='81,90,90,94,90,105,81,109,72,105,72,94'
      onclick='clickColor("#99FF99",1,110,72)' onmouseover='mouseOverColortop("#99FF99",1)'
      alt='#99FF99'/>
<area style='cursor:pointer' shape='poly' coords='99,90,108,94,108,105,99,109,90,105,90,94'
      onclick='clickColor("#CCFFCC",1,110,90)' onmouseover='mouseOverColortop("#CCFFCC",1)'
      alt='#CCFFCC'/>
<area style='cursor:pointer' shape='poly' coords='117,90,126,94,126,105,117,109,108,105,108,94'
      onclick='clickColor("#FFFFFF",1,110,108)' onmouseover='mouseOverColortop("#FFFFFF",1)'
      alt='#FFFFFF'/>
<area style='cursor:pointer' shape='poly' coords='135,90,144,94,144,105,135,109,126,105,126,94'
      onclick='clickColor("#FFCCFF",1,110,126)' onmouseover='mouseOverColortop("#FFCCFF",1)'
      alt='#FFCCFF'/>
<area style='cursor:pointer' shape='poly' coords='153,90,162,94,162,105,153,109,144,105,144,94'
      onclick='clickColor("#FF99FF",1,110,144)' onmouseover='mouseOverColortop("#FF99FF",1)'
      alt='#FF99FF'/>
<area style='cursor:pointer' shape='poly' coords='171,90,180,94,180,105,171,109,162,105,162,94'
      onclick='clickColor("#FF66FF",1,110,162)' onmouseover='mouseOverColortop("#FF66FF",1)'
      alt='#FF66FF'/>
<area style='cursor:pointer' shape='poly' coords='189,90,198,94,198,105,189,109,180,105,180,94'
      onclick='clickColor("#FF00FF",1,110,180)' onmouseover='mouseOverColortop("#FF00FF",1)'
      alt='#FF00FF'/>
<area style='cursor:pointer' shape='poly' coords='207,90,216,94,216,105,207,109,198,105,198,94'
      onclick='clickColor("#CC00CC",1,110,198)' onmouseover='mouseOverColortop("#CC00CC",1)'
      alt='#CC00CC'/>
<area style='cursor:pointer' shape='poly' coords='225,90,234,94,234,105,225,109,216,105,216,94'
      onclick='clickColor("#660066",1,110,216)' onmouseover='mouseOverColortop("#660066",1)'
      alt='#660066'/>
<area style='cursor:pointer' shape='poly' coords='18,105,27,109,27,120,18,124,9,120,9,109'
      onclick='clickColor("#336600",1,125,9)' onmouseover='mouseOverColortop("#336600",1)'
      alt='#336600'/>
<area style='cursor:pointer' shape='poly' coords='36,105,45,109,45,120,36,124,27,120,27,109'
      onclick='clickColor("#009900",1,125,27)' onmouseover='mouseOverColortop("#009900",1)'
      alt='#009900'/>
<area style='cursor:pointer' shape='poly' coords='54,105,63,109,63,120,54,124,45,120,45,109'
      onclick='clickColor("#66FF33",1,125,45)' onmouseover='mouseOverColortop("#66FF33",1)'
      alt='#66FF33'/>
<area style='cursor:pointer' shape='poly' coords='72,105,81,109,81,120,72,124,63,120,63,109'
      onclick='clickColor("#99FF66",1,125,63)' onmouseover='mouseOverColortop("#99FF66",1)'
      alt='#99FF66'/>
<area style='cursor:pointer' shape='poly' coords='90,105,99,109,99,120,90,124,81,120,81,109'
      onclick='clickColor("#CCFF99",1,125,81)' onmouseover='mouseOverColortop("#CCFF99",1)'
      alt='#CCFF99'/>
<area style='cursor:pointer' shape='poly' coords='108,105,117,109,117,120,108,124,99,120,99,109'
      onclick='clickColor("#FFFFCC",1,125,99)' onmouseover='mouseOverColortop("#FFFFCC",1)'
      alt='#FFFFCC'/>
<area style='cursor:pointer' shape='poly'
      coords='126,105,135,109,135,120,126,124,117,120,117,109'
      onclick='clickColor("#FFCCCC",1,125,117)' onmouseover='mouseOverColortop("#FFCCCC",1)'
      alt='#FFCCCC'/>
<area style='cursor:pointer' shape='poly'
      coords='144,105,153,109,153,120,144,124,135,120,135,109'
      onclick='clickColor("#FF99CC",1,125,135)' onmouseover='mouseOverColortop("#FF99CC",1)'
      alt='#FF99CC'/>
<area style='cursor:pointer' shape='poly'
      coords='162,105,171,109,171,120,162,124,153,120,153,109'
      onclick='clickColor("#FF66CC",1,125,153)' onmouseover='mouseOverColortop("#FF66CC",1)'
      alt='#FF66CC'/>
<area style='cursor:pointer' shape='poly'
      coords='180,105,189,109,189,120,180,124,171,120,171,109'
      onclick='clickColor("#FF33CC",1,125,171)' onmouseover='mouseOverColortop("#FF33CC",1)'
      alt='#FF33CC'/>
<area style='cursor:pointer' shape='poly'
      coords='198,105,207,109,207,120,198,124,189,120,189,109'
      onclick='clickColor("#CC0099",1,125,189)' onmouseover='mouseOverColortop("#CC0099",1)'
      alt='#CC0099'/>
<area style='cursor:pointer' shape='poly'
      coords='216,105,225,109,225,120,216,124,207,120,207,109'
      onclick='clickColor("#993399",1,125,207)' onmouseover='mouseOverColortop("#993399",1)'
      alt='#993399'/>
<area style='cursor:pointer' shape='poly' coords='27,120,36,124,36,135,27,139,18,135,18,124'
      onclick='clickColor("#333300",1,140,18)' onmouseover='mouseOverColortop("#333300",1)'
      alt='#333300'/>
<area style='cursor:pointer' shape='poly' coords='45,120,54,124,54,135,45,139,36,135,36,124'
      onclick='clickColor("#669900",1,140,36)' onmouseover='mouseOverColortop("#669900",1)'
      alt='#669900'/>
<area style='cursor:pointer' shape='poly' coords='63,120,72,124,72,135,63,139,54,135,54,124'
      onclick='clickColor("#99FF33",1,140,54)' onmouseover='mouseOverColortop("#99FF33",1)'
      alt='#99FF33'/>
<area style='cursor:pointer' shape='poly' coords='81,120,90,124,90,135,81,139,72,135,72,124'
      onclick='clickColor("#CCFF66",1,140,72)' onmouseover='mouseOverColortop("#CCFF66",1)'
      alt='#CCFF66'/>
<area style='cursor:pointer' shape='poly' coords='99,120,108,124,108,135,99,139,90,135,90,124'
      onclick='clickColor("#FFFF99",1,140,90)' onmouseover='mouseOverColortop("#FFFF99",1)'
      alt='#FFFF99'/>
<area style='cursor:pointer' shape='poly'
      coords='117,120,126,124,126,135,117,139,108,135,108,124'
      onclick='clickColor("#FFCC99",1,140,108)' onmouseover='mouseOverColortop("#FFCC99",1)'
      alt='#FFCC99'/>
<area style='cursor:pointer' shape='poly'
      coords='135,120,144,124,144,135,135,139,126,135,126,124'
      onclick='clickColor("#FF9999",1,140,126)' onmouseover='mouseOverColortop("#FF9999",1)'
      alt='#FF9999'/>
<area style='cursor:pointer' shape='poly'
      coords='153,120,162,124,162,135,153,139,144,135,144,124'
      onclick='clickColor("#FF6699",1,140,144)' onmouseover='mouseOverColortop("#FF6699",1)'
      alt='#FF6699'/>
<area style='cursor:pointer' shape='poly'
      coords='171,120,180,124,180,135,171,139,162,135,162,124'
      onclick='clickColor("#FF3399",1,140,162)' onmouseover='mouseOverColortop("#FF3399",1)'
      alt='#FF3399'/>
<area style='cursor:pointer' shape='poly'
      coords='189,120,198,124,198,135,189,139,180,135,180,124'
      onclick='clickColor("#CC3399",1,140,180)' onmouseover='mouseOverColortop("#CC3399",1)'
      alt='#CC3399'/>
<area style='cursor:pointer' shape='poly'
      coords='207,120,216,124,216,135,207,139,198,135,198,124'
      onclick='clickColor("#990099",1,140,198)' onmouseover='mouseOverColortop("#990099",1)'
      alt='#990099'/>
<area style='cursor:pointer' shape='poly' coords='36,135,45,139,45,150,36,154,27,150,27,139'
      onclick='clickColor("#666633",1,155,27)' onmouseover='mouseOverColortop("#666633",1)'
      alt='#666633'/>
<area style='cursor:pointer' shape='poly' coords='54,135,63,139,63,150,54,154,45,150,45,139'
      onclick='clickColor("#99CC00",1,155,45)' onmouseover='mouseOverColortop("#99CC00",1)'
      alt='#99CC00'/>
<area style='cursor:pointer' shape='poly' coords='72,135,81,139,81,150,72,154,63,150,63,139'
      onclick='clickColor("#CCFF33",1,155,63)' onmouseover='mouseOverColortop("#CCFF33",1)'
      alt='#CCFF33'/>
<area style='cursor:pointer' shape='poly' coords='90,135,99,139,99,150,90,154,81,150,81,139'
      onclick='clickColor("#FFFF66",1,155,81)' onmouseover='mouseOverColortop("#FFFF66",1)'
      alt='#FFFF66'/>
<area style='cursor:pointer' shape='poly' coords='108,135,117,139,117,150,108,154,99,150,99,139'
      onclick='clickColor("#FFCC66",1,155,99)' onmouseover='mouseOverColortop("#FFCC66",1)'
      alt='#FFCC66'/>
<area style='cursor:pointer' shape='poly'
      coords='126,135,135,139,135,150,126,154,117,150,117,139'
      onclick='clickColor("#FF9966",1,155,117)' onmouseover='mouseOverColortop("#FF9966",1)'
      alt='#FF9966'/>
<area style='cursor:pointer' shape='poly'
      coords='144,135,153,139,153,150,144,154,135,150,135,139'
      onclick='clickColor("#FF6666",1,155,135)' onmouseover='mouseOverColortop("#FF6666",1)'
      alt='#FF6666'/>
<area style='cursor:pointer' shape='poly'
      coords='162,135,171,139,171,150,162,154,153,150,153,139'
      onclick='clickColor("#FF0066",1,155,153)' onmouseover='mouseOverColortop("#FF0066",1)'
      alt='#FF0066'/>
<area style='cursor:pointer' shape='poly'
      coords='180,135,189,139,189,150,180,154,171,150,171,139'
      onclick='clickColor("#CC6699",1,155,171)' onmouseover='mouseOverColortop("#CC6699",1)'
      alt='#CC6699'/>
<area style='cursor:pointer' shape='poly'
      coords='198,135,207,139,207,150,198,154,189,150,189,139'
      onclick='clickColor("#993366",1,155,189)' onmouseover='mouseOverColortop("#993366",1)'
      alt='#993366'/>
<area style='cursor:pointer' shape='poly' coords='45,150,54,154,54,165,45,169,36,165,36,154'
      onclick='clickColor("#999966",1,170,36)' onmouseover='mouseOverColortop("#999966",1)'
      alt='#999966'/>
<area style='cursor:pointer' shape='poly' coords='63,150,72,154,72,165,63,169,54,165,54,154'
      onclick='clickColor("#CCCC00",1,170,54)' onmouseover='mouseOverColortop("#CCCC00",1)'
      alt='#CCCC00'/>
<area style='cursor:pointer' shape='poly' coords='81,150,90,154,90,165,81,169,72,165,72,154'
      onclick='clickColor("#FFFF00",1,170,72)' onmouseover='mouseOverColortop("#FFFF00",1)'
      alt='#FFFF00'/>
<area style='cursor:pointer' shape='poly' coords='99,150,108,154,108,165,99,169,90,165,90,154'
      onclick='clickColor("#FFCC00",1,170,90)' onmouseover='mouseOverColortop("#FFCC00",1)'
      alt='#FFCC00'/>
<area style='cursor:pointer' shape='poly'
      coords='117,150,126,154,126,165,117,169,108,165,108,154'
      onclick='clickColor("#FF9933",1,170,108)' onmouseover='mouseOverColortop("#FF9933",1)'
      alt='#FF9933'/>
<area style='cursor:pointer' shape='poly'
      coords='135,150,144,154,144,165,135,169,126,165,126,154'
      onclick='clickColor("#FF6600",1,170,126)' onmouseover='mouseOverColortop("#FF6600",1)'
      alt='#FF6600'/>
<area style='cursor:pointer' shape='poly'
      coords='153,150,162,154,162,165,153,169,144,165,144,154'
      onclick='clickColor("#FF5050",1,170,144)' onmouseover='mouseOverColortop("#FF5050",1)'
      alt='#FF5050'/>
<area style='cursor:pointer' shape='poly'
      coords='171,150,180,154,180,165,171,169,162,165,162,154'
      onclick='clickColor("#CC0066",1,170,162)' onmouseover='mouseOverColortop("#CC0066",1)'
      alt='#CC0066'/>
<area style='cursor:pointer' shape='poly'
      coords='189,150,198,154,198,165,189,169,180,165,180,154'
      onclick='clickColor("#660033",1,170,180)' onmouseover='mouseOverColortop("#660033",1)'
      alt='#660033'/>
<area style='cursor:pointer' shape='poly' coords='54,165,63,169,63,180,54,184,45,180,45,169'
      onclick='clickColor("#996633",1,185,45)' onmouseover='mouseOverColortop("#996633",1)'
      alt='#996633'/>
<area style='cursor:pointer' shape='poly' coords='72,165,81,169,81,180,72,184,63,180,63,169'
      onclick='clickColor("#CC9900",1,185,63)' onmouseover='mouseOverColortop("#CC9900",1)'
      alt='#CC9900'/>
<area style='cursor:pointer' shape='poly' coords='90,165,99,169,99,180,90,184,81,180,81,169'
      onclick='clickColor("#FF9900",1,185,81)' onmouseover='mouseOverColortop("#FF9900",1)'
      alt='#FF9900'/>
<area style='cursor:pointer' shape='poly' coords='108,165,117,169,117,180,108,184,99,180,99,169'
      onclick='clickColor("#CC6600",1,185,99)' onmouseover='mouseOverColortop("#CC6600",1)'
      alt='#CC6600'/>
<area style='cursor:pointer' shape='poly'
      coords='126,165,135,169,135,180,126,184,117,180,117,169'
      onclick='clickColor("#FF3300",1,185,117)' onmouseover='mouseOverColortop("#FF3300",1)'
      alt='#FF3300'/>
<area style='cursor:pointer' shape='poly'
      coords='144,165,153,169,153,180,144,184,135,180,135,169'
      onclick='clickColor("#FF0000",1,185,135)' onmouseover='mouseOverColortop("#FF0000",1)'
      alt='#FF0000'/>
<area style='cursor:pointer' shape='poly'
      coords='162,165,171,169,171,180,162,184,153,180,153,169'
      onclick='clickColor("#CC0000",1,185,153)' onmouseover='mouseOverColortop("#CC0000",1)'
      alt='#CC0000'/>
<area style='cursor:pointer' shape='poly'
      coords='180,165,189,169,189,180,180,184,171,180,171,169'
      onclick='clickColor("#990033",1,185,171)' onmouseover='mouseOverColortop("#990033",1)'
      alt='#990033'/>
<area style='cursor:pointer' shape='poly' coords='63,180,72,184,72,195,63,199,54,195,54,184'
      onclick='clickColor("#663300",1,200,54)' onmouseover='mouseOverColortop("#663300",1)'
      alt='#663300'/>
<area style='cursor:pointer' shape='poly' coords='81,180,90,184,90,195,81,199,72,195,72,184'
      onclick='clickColor("#996600",1,200,72)' onmouseover='mouseOverColortop("#996600",1)'
      alt='#996600'/>
<area style='cursor:pointer' shape='poly' coords='99,180,108,184,108,195,99,199,90,195,90,184'
      onclick='clickColor("#CC3300",1,200,90)' onmouseover='mouseOverColortop("#CC3300",1)'
      alt='#CC3300'/>
<area style='cursor:pointer' shape='poly'
      coords='117,180,126,184,126,195,117,199,108,195,108,184'
      onclick='clickColor("#993300",1,200,108)' onmouseover='mouseOverColortop("#993300",1)'
      alt='#993300'/>
<area style='cursor:pointer' shape='poly'
      coords='135,180,144,184,144,195,135,199,126,195,126,184'
      onclick='clickColor("#990000",1,200,126)' onmouseover='mouseOverColortop("#990000",1)'
      alt='#990000'/>
<area style='cursor:pointer' shape='poly'
      coords='153,180,162,184,162,195,153,199,144,195,144,184'
      onclick='clickColor("#800000",1,200,144)' onmouseover='mouseOverColortop("#800000",1)'
      alt='#800000'/>
<area style='cursor:pointer' shape='poly'
      coords='171,180,180,184,180,195,171,199,162,195,162,184'
      onclick='clickColor("#993333",1,200,162)' onmouseover='mouseOverColortop("#993333",1)'
      alt='#993333'/>
</map>
<script>
    document.getElementById("selectedColortop").style.top = "185px"
    document.getElementById("selectedColortop").style.left = "135px"
    document.getElementById("selectedColortop").style.visibility = "visible"
</script>
</td>
<td rowspan="3">
    <table>
        <tr>
            <td valign='top' style='height:55px;'>Top color: <input style='width:75px;margin:0px;'
                                                                    name='colortop' value='#FF0000'
                                                                    id='colortop'/> <input type='button'
                                                                                           value='Submit'
                                                                                           onclick='clickColor(0,1,-1,-1)'/>
            </td>
        </tr>
        <tr>
            <td>
                <div id="colormixer">

                    <c:if test="${not empty initMixColor}">
                        ${initMixColor}
                    </c:if>
                    <%--<table cellpadding='0' cellspacing='0'>--%>
                        <%--<tr>--%>
                            <%--<td style='width:220px;height:20px;color:#FFFFFF;background:rgb(255,0,0)'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#FF0000</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(242,0,13)'--%>
                                <%--onmouseover='mouseOverColortop("#F2000D")'--%>
                                <%--onclick='clickColor("#F2000D",1)' onmouseout='mouseOutMaptop()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#F2000D</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(230,0,26)'--%>
                                <%--onmouseover='mouseOverColortop("#E6001A")'--%>
                                <%--onclick='clickColor("#E6001A",1)' onmouseout='mouseOutMaptop()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#E6001A</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(217,0,38)'--%>
                                <%--onmouseover='mouseOverColortop("#D90026")'--%>
                                <%--onclick='clickColor("#D90026",1)' onmouseout='mouseOutMaptop()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#D90026</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(204,0,51)'--%>
                                <%--onmouseover='mouseOverColortop("#CC0033")'--%>
                                <%--onclick='clickColor("#CC0033",1)' onmouseout='mouseOutMaptop()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#CC0033</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(191,0,64)'--%>
                                <%--onmouseover='mouseOverColortop("#BF0040")'--%>
                                <%--onclick='clickColor("#BF0040",1)' onmouseout='mouseOutMaptop()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#BF0040</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(178,0,76)'--%>
                                <%--onmouseover='mouseOverColortop("#B2004C")'--%>
                                <%--onclick='clickColor("#B2004C",1)' onmouseout='mouseOutMaptop()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#B2004C</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(166,0,89)'--%>
                                <%--onmouseover='mouseOverColortop("#A60059")'--%>
                                <%--onclick='clickColor("#A60059",1)' onmouseout='mouseOutMaptop()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#A60059</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(153,0,102)'--%>
                                <%--onmouseover='mouseOverColortop("#990066")'--%>
                                <%--onclick='clickColor("#990066",1)' onmouseout='mouseOutMaptop()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#990066</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(140,0,115)'--%>
                                <%--onmouseover='mouseOverColortop("#8C0073")'--%>
                                <%--onclick='clickColor("#8C0073",1)' onmouseout='mouseOutMaptop()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#8C0073</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(128,0,128)'--%>
                                <%--onmouseover='mouseOverColorbottom("#800080")'--%>
                                <%--onclick='clickColor("#800080",0)' onmouseout='mouseOutMapbottom()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#800080</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(115,0,140)'--%>
                                <%--onmouseover='mouseOverColorbottom("#73008C")'--%>
                                <%--onclick='clickColor("#73008C",0)' onmouseout='mouseOutMapbottom()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#73008C</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(102,0,153)'--%>
                                <%--onmouseover='mouseOverColorbottom("#660099")'--%>
                                <%--onclick='clickColor("#660099",0)' onmouseout='mouseOutMapbottom()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#660099</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(89,0,166)'--%>
                                <%--onmouseover='mouseOverColorbottom("#5900A6")'--%>
                                <%--onclick='clickColor("#5900A6",0)' onmouseout='mouseOutMapbottom()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#5900A6</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(77,0,178)'--%>
                                <%--onmouseover='mouseOverColorbottom("#4D00B2")'--%>
                                <%--onclick='clickColor("#4D00B2",0)' onmouseout='mouseOutMapbottom()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#4D00B2</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(64,0,191)'--%>
                                <%--onmouseover='mouseOverColorbottom("#4000BF")'--%>
                                <%--onclick='clickColor("#4000BF",0)' onmouseout='mouseOutMapbottom()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#4000BF</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(51,0,204)'--%>
                                <%--onmouseover='mouseOverColorbottom("#3300CC")'--%>
                                <%--onclick='clickColor("#3300CC",0)' onmouseout='mouseOutMapbottom()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#3300CC</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(38,0,217)'--%>
                                <%--onmouseover='mouseOverColorbottom("#2600D9")'--%>
                                <%--onclick='clickColor("#2600D9",0)' onmouseout='mouseOutMapbottom()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#2600D9</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(25,0,230)'--%>
                                <%--onmouseover='mouseOverColorbottom("#1900E6")'--%>
                                <%--onclick='clickColor("#1900E6",0)' onmouseout='mouseOutMapbottom()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#1900E6</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='width:200px;height:20px;color:#FFFFFF;background:rgb(13,0,242)'--%>
                                <%--onmouseover='mouseOverColorbottom("#0D00F2")'--%>
                                <%--onclick='clickColor("#0D00F2",0)' onmouseout='mouseOutMapbottom()'>--%>
                                <%--&nbsp;</td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#0D00F2</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td style='height:20px;background:rgb(0,0,255)'></td>--%>
                            <%--<td style='font-family:courier new;font-size:110%;'>&nbsp;&nbsp;#0000FF</td>--%>
                        <%--</tr>--%>
                    <%--</table>--%>
                </div>
            </td>
        </tr>
        <tr>
            <td valign='bottom' style='height:55px;'>Bottom color: <input style='width:65px;margin:0px;'
                                                                          name='colorbottom'
                                                                          value='#0000FF'
                                                                          id='colorbottom'/> <input
                    type='button' value='Submit' onclick='clickColor(0,0,-1,-1)'/></td>
        </tr>
    </table>
</td>
</tr>
<tr>
    <td>
        <div style='width:200px;padding-top:15px;padding-left:67px;'>
            <div>
                <div id='divpreviewtop'
                     style='float:left;height:30px;width:100px;border:1px solid #d4d4d4;border-bottom:none;background-color:#FF0000'>
                    &nbsp;</div>
                <div id='divpreviewtxttop' style='float:left;width:50px;padding-left:15px;padding-top:7px;'>
                    #FF0000
                </div>
            </div>
            <div style='clear:both'>
                <div id='divpreviewbottom'
                     style='float:left;height:30px;width:100px;border:1px solid #d4d4d4;border-top:none;background-color:#0000FF'>
                    &nbsp;</div>
                <div id='divpreviewtxtbottom'
                     style='float:left;width:50px;padding-left:15px;padding-top:7px;'>#0000FF
                </div>
            </div>
        </div>
    </td>
</tr>
<tr>
<td>
<div id='selectedColorbottom'
     style='visibility:hidden;position:relative;width:21px;height:21px;background-repeat:no-repeat;background-image:url("images/selectedcolor.gif")'></div>
<img style='margin-right:2px;' src='images/colormap.gif' usemap='#colormap_bottom' alt='colormap'/>
<map name='colormap_bottom' id='colormap_bottom' onmouseout='mouseOutMapbottom(0)'>
<area style='cursor:pointer' shape='poly' coords='63,0,72,4,72,15,63,19,54,15,54,4'
      onclick='clickColor("#003366",0,20,54)' onmouseover='mouseOverColorbottom("#003366",0)'
      alt='#003366'/>
<area style='cursor:pointer' shape='poly' coords='81,0,90,4,90,15,81,19,72,15,72,4'
      onclick='clickColor("#336699",0,20,72)' onmouseover='mouseOverColorbottom("#336699",0)'
      alt='#336699'/>
<area style='cursor:pointer' shape='poly' coords='99,0,108,4,108,15,99,19,90,15,90,4'
      onclick='clickColor("#3366CC",0,20,90)' onmouseover='mouseOverColorbottom("#3366CC",0)'
      alt='#3366CC'/>
<area style='cursor:pointer' shape='poly' coords='117,0,126,4,126,15,117,19,108,15,108,4'
      onclick='clickColor("#003399",0,20,108)' onmouseover='mouseOverColorbottom("#003399",0)'
      alt='#003399'/>
<area style='cursor:pointer' shape='poly' coords='135,0,144,4,144,15,135,19,126,15,126,4'
      onclick='clickColor("#000099",0,20,126)' onmouseover='mouseOverColorbottom("#000099",0)'
      alt='#000099'/>
<area style='cursor:pointer' shape='poly' coords='153,0,162,4,162,15,153,19,144,15,144,4'
      onclick='clickColor("#0000CC",0,20,144)' onmouseover='mouseOverColorbottom("#0000CC",0)'
      alt='#0000CC'/>
<area style='cursor:pointer' shape='poly' coords='171,0,180,4,180,15,171,19,162,15,162,4'
      onclick='clickColor("#000066",0,20,162)' onmouseover='mouseOverColorbottom("#000066",0)'
      alt='#000066'/>
<area style='cursor:pointer' shape='poly' coords='54,15,63,19,63,30,54,34,45,30,45,19'
      onclick='clickColor("#006666",0,35,45)' onmouseover='mouseOverColorbottom("#006666",0)'
      alt='#006666'/>
<area style='cursor:pointer' shape='poly' coords='72,15,81,19,81,30,72,34,63,30,63,19'
      onclick='clickColor("#006699",0,35,63)' onmouseover='mouseOverColorbottom("#006699",0)'
      alt='#006699'/>
<area style='cursor:pointer' shape='poly' coords='90,15,99,19,99,30,90,34,81,30,81,19'
      onclick='clickColor("#0099CC",0,35,81)' onmouseover='mouseOverColorbottom("#0099CC",0)'
      alt='#0099CC'/>
<area style='cursor:pointer' shape='poly' coords='108,15,117,19,117,30,108,34,99,30,99,19'
      onclick='clickColor("#0066CC",0,35,99)' onmouseover='mouseOverColorbottom("#0066CC",0)'
      alt='#0066CC'/>
<area style='cursor:pointer' shape='poly' coords='126,15,135,19,135,30,126,34,117,30,117,19'
      onclick='clickColor("#0033CC",0,35,117)' onmouseover='mouseOverColorbottom("#0033CC",0)'
      alt='#0033CC'/>
<area style='cursor:pointer' shape='poly' coords='144,15,153,19,153,30,144,34,135,30,135,19'
      onclick='clickColor("#0000FF",0,35,135)' onmouseover='mouseOverColorbottom("#0000FF",0)'
      alt='#0000FF'/>
<area style='cursor:pointer' shape='poly' coords='162,15,171,19,171,30,162,34,153,30,153,19'
      onclick='clickColor("#3333FF",0,35,153)' onmouseover='mouseOverColorbottom("#3333FF",0)'
      alt='#3333FF'/>
<area style='cursor:pointer' shape='poly' coords='180,15,189,19,189,30,180,34,171,30,171,19'
      onclick='clickColor("#333399",0,35,171)' onmouseover='mouseOverColorbottom("#333399",0)'
      alt='#333399'/>
<area style='cursor:pointer' shape='poly' coords='45,30,54,34,54,45,45,49,36,45,36,34'
      onclick='clickColor("#669999",0,50,36)' onmouseover='mouseOverColorbottom("#669999",0)'
      alt='#669999'/>
<area style='cursor:pointer' shape='poly' coords='63,30,72,34,72,45,63,49,54,45,54,34'
      onclick='clickColor("#009999",0,50,54)' onmouseover='mouseOverColorbottom("#009999",0)'
      alt='#009999'/>
<area style='cursor:pointer' shape='poly' coords='81,30,90,34,90,45,81,49,72,45,72,34'
      onclick='clickColor("#33CCCC",0,50,72)' onmouseover='mouseOverColorbottom("#33CCCC",0)'
      alt='#33CCCC'/>
<area style='cursor:pointer' shape='poly' coords='99,30,108,34,108,45,99,49,90,45,90,34'
      onclick='clickColor("#00CCFF",0,50,90)' onmouseover='mouseOverColorbottom("#00CCFF",0)'
      alt='#00CCFF'/>
<area style='cursor:pointer' shape='poly' coords='117,30,126,34,126,45,117,49,108,45,108,34'
      onclick='clickColor("#0099FF",0,50,108)' onmouseover='mouseOverColorbottom("#0099FF",0)'
      alt='#0099FF'/>
<area style='cursor:pointer' shape='poly' coords='135,30,144,34,144,45,135,49,126,45,126,34'
      onclick='clickColor("#0066FF",0,50,126)' onmouseover='mouseOverColorbottom("#0066FF",0)'
      alt='#0066FF'/>
<area style='cursor:pointer' shape='poly' coords='153,30,162,34,162,45,153,49,144,45,144,34'
      onclick='clickColor("#3366FF",0,50,144)' onmouseover='mouseOverColorbottom("#3366FF",0)'
      alt='#3366FF'/>
<area style='cursor:pointer' shape='poly' coords='171,30,180,34,180,45,171,49,162,45,162,34'
      onclick='clickColor("#3333CC",0,50,162)' onmouseover='mouseOverColorbottom("#3333CC",0)'
      alt='#3333CC'/>
<area style='cursor:pointer' shape='poly' coords='189,30,198,34,198,45,189,49,180,45,180,34'
      onclick='clickColor("#666699",0,50,180)' onmouseover='mouseOverColorbottom("#666699",0)'
      alt='#666699'/>
<area style='cursor:pointer' shape='poly' coords='36,45,45,49,45,60,36,64,27,60,27,49'
      onclick='clickColor("#339966",0,65,27)' onmouseover='mouseOverColorbottom("#339966",0)'
      alt='#339966'/>
<area style='cursor:pointer' shape='poly' coords='54,45,63,49,63,60,54,64,45,60,45,49'
      onclick='clickColor("#00CC99",0,65,45)' onmouseover='mouseOverColorbottom("#00CC99",0)'
      alt='#00CC99'/>
<area style='cursor:pointer' shape='poly' coords='72,45,81,49,81,60,72,64,63,60,63,49'
      onclick='clickColor("#00FFCC",0,65,63)' onmouseover='mouseOverColorbottom("#00FFCC",0)'
      alt='#00FFCC'/>
<area style='cursor:pointer' shape='poly' coords='90,45,99,49,99,60,90,64,81,60,81,49'
      onclick='clickColor("#00FFFF",0,65,81)' onmouseover='mouseOverColorbottom("#00FFFF",0)'
      alt='#00FFFF'/>
<area style='cursor:pointer' shape='poly' coords='108,45,117,49,117,60,108,64,99,60,99,49'
      onclick='clickColor("#33CCFF",0,65,99)' onmouseover='mouseOverColorbottom("#33CCFF",0)'
      alt='#33CCFF'/>
<area style='cursor:pointer' shape='poly' coords='126,45,135,49,135,60,126,64,117,60,117,49'
      onclick='clickColor("#3399FF",0,65,117)' onmouseover='mouseOverColorbottom("#3399FF",0)'
      alt='#3399FF'/>
<area style='cursor:pointer' shape='poly' coords='144,45,153,49,153,60,144,64,135,60,135,49'
      onclick='clickColor("#6699FF",0,65,135)' onmouseover='mouseOverColorbottom("#6699FF",0)'
      alt='#6699FF'/>
<area style='cursor:pointer' shape='poly' coords='162,45,171,49,171,60,162,64,153,60,153,49'
      onclick='clickColor("#6666FF",0,65,153)' onmouseover='mouseOverColorbottom("#6666FF",0)'
      alt='#6666FF'/>
<area style='cursor:pointer' shape='poly' coords='180,45,189,49,189,60,180,64,171,60,171,49'
      onclick='clickColor("#6600FF",0,65,171)' onmouseover='mouseOverColorbottom("#6600FF",0)'
      alt='#6600FF'/>
<area style='cursor:pointer' shape='poly' coords='198,45,207,49,207,60,198,64,189,60,189,49'
      onclick='clickColor("#6600CC",0,65,189)' onmouseover='mouseOverColorbottom("#6600CC",0)'
      alt='#6600CC'/>
<area style='cursor:pointer' shape='poly' coords='27,60,36,64,36,75,27,79,18,75,18,64'
      onclick='clickColor("#339933",0,80,18)' onmouseover='mouseOverColorbottom("#339933",0)'
      alt='#339933'/>
<area style='cursor:pointer' shape='poly' coords='45,60,54,64,54,75,45,79,36,75,36,64'
      onclick='clickColor("#00CC66",0,80,36)' onmouseover='mouseOverColorbottom("#00CC66",0)'
      alt='#00CC66'/>
<area style='cursor:pointer' shape='poly' coords='63,60,72,64,72,75,63,79,54,75,54,64'
      onclick='clickColor("#00FF99",0,80,54)' onmouseover='mouseOverColorbottom("#00FF99",0)'
      alt='#00FF99'/>
<area style='cursor:pointer' shape='poly' coords='81,60,90,64,90,75,81,79,72,75,72,64'
      onclick='clickColor("#66FFCC",0,80,72)' onmouseover='mouseOverColorbottom("#66FFCC",0)'
      alt='#66FFCC'/>
<area style='cursor:pointer' shape='poly' coords='99,60,108,64,108,75,99,79,90,75,90,64'
      onclick='clickColor("#66FFFF",0,80,90)' onmouseover='mouseOverColorbottom("#66FFFF",0)'
      alt='#66FFFF'/>
<area style='cursor:pointer' shape='poly' coords='117,60,126,64,126,75,117,79,108,75,108,64'
      onclick='clickColor("#66CCFF",0,80,108)' onmouseover='mouseOverColorbottom("#66CCFF",0)'
      alt='#66CCFF'/>
<area style='cursor:pointer' shape='poly' coords='135,60,144,64,144,75,135,79,126,75,126,64'
      onclick='clickColor("#99CCFF",0,80,126)' onmouseover='mouseOverColorbottom("#99CCFF",0)'
      alt='#99CCFF'/>
<area style='cursor:pointer' shape='poly' coords='153,60,162,64,162,75,153,79,144,75,144,64'
      onclick='clickColor("#9999FF",0,80,144)' onmouseover='mouseOverColorbottom("#9999FF",0)'
      alt='#9999FF'/>
<area style='cursor:pointer' shape='poly' coords='171,60,180,64,180,75,171,79,162,75,162,64'
      onclick='clickColor("#9966FF",0,80,162)' onmouseover='mouseOverColorbottom("#9966FF",0)'
      alt='#9966FF'/>
<area style='cursor:pointer' shape='poly' coords='189,60,198,64,198,75,189,79,180,75,180,64'
      onclick='clickColor("#9933FF",0,80,180)' onmouseover='mouseOverColorbottom("#9933FF",0)'
      alt='#9933FF'/>
<area style='cursor:pointer' shape='poly' coords='207,60,216,64,216,75,207,79,198,75,198,64'
      onclick='clickColor("#9900FF",0,80,198)' onmouseover='mouseOverColorbottom("#9900FF",0)'
      alt='#9900FF'/>
<area style='cursor:pointer' shape='poly' coords='18,75,27,79,27,90,18,94,9,90,9,79'
      onclick='clickColor("#006600",0,95,9)' onmouseover='mouseOverColorbottom("#006600",0)'
      alt='#006600'/>
<area style='cursor:pointer' shape='poly' coords='36,75,45,79,45,90,36,94,27,90,27,79'
      onclick='clickColor("#00CC00",0,95,27)' onmouseover='mouseOverColorbottom("#00CC00",0)'
      alt='#00CC00'/>
<area style='cursor:pointer' shape='poly' coords='54,75,63,79,63,90,54,94,45,90,45,79'
      onclick='clickColor("#00FF00",0,95,45)' onmouseover='mouseOverColorbottom("#00FF00",0)'
      alt='#00FF00'/>
<area style='cursor:pointer' shape='poly' coords='72,75,81,79,81,90,72,94,63,90,63,79'
      onclick='clickColor("#66FF99",0,95,63)' onmouseover='mouseOverColorbottom("#66FF99",0)'
      alt='#66FF99'/>
<area style='cursor:pointer' shape='poly' coords='90,75,99,79,99,90,90,94,81,90,81,79'
      onclick='clickColor("#99FFCC",0,95,81)' onmouseover='mouseOverColorbottom("#99FFCC",0)'
      alt='#99FFCC'/>
<area style='cursor:pointer' shape='poly' coords='108,75,117,79,117,90,108,94,99,90,99,79'
      onclick='clickColor("#CCFFFF",0,95,99)' onmouseover='mouseOverColorbottom("#CCFFFF",0)'
      alt='#CCFFFF'/>
<area style='cursor:pointer' shape='poly' coords='126,75,135,79,135,90,126,94,117,90,117,79'
      onclick='clickColor("#CCCCFF",0,95,117)' onmouseover='mouseOverColorbottom("#CCCCFF",0)'
      alt='#CCCCFF'/>
<area style='cursor:pointer' shape='poly' coords='144,75,153,79,153,90,144,94,135,90,135,79'
      onclick='clickColor("#CC99FF",0,95,135)' onmouseover='mouseOverColorbottom("#CC99FF",0)'
      alt='#CC99FF'/>
<area style='cursor:pointer' shape='poly' coords='162,75,171,79,171,90,162,94,153,90,153,79'
      onclick='clickColor("#CC66FF",0,95,153)' onmouseover='mouseOverColorbottom("#CC66FF",0)'
      alt='#CC66FF'/>
<area style='cursor:pointer' shape='poly' coords='180,75,189,79,189,90,180,94,171,90,171,79'
      onclick='clickColor("#CC33FF",0,95,171)' onmouseover='mouseOverColorbottom("#CC33FF",0)'
      alt='#CC33FF'/>
<area style='cursor:pointer' shape='poly' coords='198,75,207,79,207,90,198,94,189,90,189,79'
      onclick='clickColor("#CC00FF",0,95,189)' onmouseover='mouseOverColorbottom("#CC00FF",0)'
      alt='#CC00FF'/>
<area style='cursor:pointer' shape='poly' coords='216,75,225,79,225,90,216,94,207,90,207,79'
      onclick='clickColor("#9900CC",0,95,207)' onmouseover='mouseOverColorbottom("#9900CC",0)'
      alt='#9900CC'/>
<area style='cursor:pointer' shape='poly' coords='9,90,18,94,18,105,9,109,0,105,0,94'
      onclick='clickColor("#003300",0,110,0)' onmouseover='mouseOverColorbottom("#003300",0)'
      alt='#003300'/>
<area style='cursor:pointer' shape='poly' coords='27,90,36,94,36,105,27,109,18,105,18,94'
      onclick='clickColor("#009933",0,110,18)' onmouseover='mouseOverColorbottom("#009933",0)'
      alt='#009933'/>
<area style='cursor:pointer' shape='poly' coords='45,90,54,94,54,105,45,109,36,105,36,94'
      onclick='clickColor("#33CC33",0,110,36)' onmouseover='mouseOverColorbottom("#33CC33",0)'
      alt='#33CC33'/>
<area style='cursor:pointer' shape='poly' coords='63,90,72,94,72,105,63,109,54,105,54,94'
      onclick='clickColor("#66FF66",0,110,54)' onmouseover='mouseOverColorbottom("#66FF66",0)'
      alt='#66FF66'/>
<area style='cursor:pointer' shape='poly' coords='81,90,90,94,90,105,81,109,72,105,72,94'
      onclick='clickColor("#99FF99",0,110,72)' onmouseover='mouseOverColorbottom("#99FF99",0)'
      alt='#99FF99'/>
<area style='cursor:pointer' shape='poly' coords='99,90,108,94,108,105,99,109,90,105,90,94'
      onclick='clickColor("#CCFFCC",0,110,90)' onmouseover='mouseOverColorbottom("#CCFFCC",0)'
      alt='#CCFFCC'/>
<area style='cursor:pointer' shape='poly' coords='117,90,126,94,126,105,117,109,108,105,108,94'
      onclick='clickColor("#FFFFFF",0,110,108)' onmouseover='mouseOverColorbottom("#FFFFFF",0)'
      alt='#FFFFFF'/>
<area style='cursor:pointer' shape='poly' coords='135,90,144,94,144,105,135,109,126,105,126,94'
      onclick='clickColor("#FFCCFF",0,110,126)' onmouseover='mouseOverColorbottom("#FFCCFF",0)'
      alt='#FFCCFF'/>
<area style='cursor:pointer' shape='poly' coords='153,90,162,94,162,105,153,109,144,105,144,94'
      onclick='clickColor("#FF99FF",0,110,144)' onmouseover='mouseOverColorbottom("#FF99FF",0)'
      alt='#FF99FF'/>
<area style='cursor:pointer' shape='poly' coords='171,90,180,94,180,105,171,109,162,105,162,94'
      onclick='clickColor("#FF66FF",0,110,162)' onmouseover='mouseOverColorbottom("#FF66FF",0)'
      alt='#FF66FF'/>
<area style='cursor:pointer' shape='poly' coords='189,90,198,94,198,105,189,109,180,105,180,94'
      onclick='clickColor("#FF00FF",0,110,180)' onmouseover='mouseOverColorbottom("#FF00FF",0)'
      alt='#FF00FF'/>
<area style='cursor:pointer' shape='poly' coords='207,90,216,94,216,105,207,109,198,105,198,94'
      onclick='clickColor("#CC00CC",0,110,198)' onmouseover='mouseOverColorbottom("#CC00CC",0)'
      alt='#CC00CC'/>
<area style='cursor:pointer' shape='poly' coords='225,90,234,94,234,105,225,109,216,105,216,94'
      onclick='clickColor("#660066",0,110,216)' onmouseover='mouseOverColorbottom("#660066",0)'
      alt='#660066'/>
<area style='cursor:pointer' shape='poly' coords='18,105,27,109,27,120,18,124,9,120,9,109'
      onclick='clickColor("#336600",0,125,9)' onmouseover='mouseOverColorbottom("#336600",0)'
      alt='#336600'/>
<area style='cursor:pointer' shape='poly' coords='36,105,45,109,45,120,36,124,27,120,27,109'
      onclick='clickColor("#009900",0,125,27)' onmouseover='mouseOverColorbottom("#009900",0)'
      alt='#009900'/>
<area style='cursor:pointer' shape='poly' coords='54,105,63,109,63,120,54,124,45,120,45,109'
      onclick='clickColor("#66FF33",0,125,45)' onmouseover='mouseOverColorbottom("#66FF33",0)'
      alt='#66FF33'/>
<area style='cursor:pointer' shape='poly' coords='72,105,81,109,81,120,72,124,63,120,63,109'
      onclick='clickColor("#99FF66",0,125,63)' onmouseover='mouseOverColorbottom("#99FF66",0)'
      alt='#99FF66'/>
<area style='cursor:pointer' shape='poly' coords='90,105,99,109,99,120,90,124,81,120,81,109'
      onclick='clickColor("#CCFF99",0,125,81)' onmouseover='mouseOverColorbottom("#CCFF99",0)'
      alt='#CCFF99'/>
<area style='cursor:pointer' shape='poly' coords='108,105,117,109,117,120,108,124,99,120,99,109'
      onclick='clickColor("#FFFFCC",0,125,99)' onmouseover='mouseOverColorbottom("#FFFFCC",0)'
      alt='#FFFFCC'/>
<area style='cursor:pointer' shape='poly'
      coords='126,105,135,109,135,120,126,124,117,120,117,109'
      onclick='clickColor("#FFCCCC",0,125,117)' onmouseover='mouseOverColorbottom("#FFCCCC",0)'
      alt='#FFCCCC'/>
<area style='cursor:pointer' shape='poly'
      coords='144,105,153,109,153,120,144,124,135,120,135,109'
      onclick='clickColor("#FF99CC",0,125,135)' onmouseover='mouseOverColorbottom("#FF99CC",0)'
      alt='#FF99CC'/>
<area style='cursor:pointer' shape='poly'
      coords='162,105,171,109,171,120,162,124,153,120,153,109'
      onclick='clickColor("#FF66CC",0,125,153)' onmouseover='mouseOverColorbottom("#FF66CC",0)'
      alt='#FF66CC'/>
<area style='cursor:pointer' shape='poly'
      coords='180,105,189,109,189,120,180,124,171,120,171,109'
      onclick='clickColor("#FF33CC",0,125,171)' onmouseover='mouseOverColorbottom("#FF33CC",0)'
      alt='#FF33CC'/>
<area style='cursor:pointer' shape='poly'
      coords='198,105,207,109,207,120,198,124,189,120,189,109'
      onclick='clickColor("#CC0099",0,125,189)' onmouseover='mouseOverColorbottom("#CC0099",0)'
      alt='#CC0099'/>
<area style='cursor:pointer' shape='poly'
      coords='216,105,225,109,225,120,216,124,207,120,207,109'
      onclick='clickColor("#993399",0,125,207)' onmouseover='mouseOverColorbottom("#993399",0)'
      alt='#993399'/>
<area style='cursor:pointer' shape='poly' coords='27,120,36,124,36,135,27,139,18,135,18,124'
      onclick='clickColor("#333300",0,140,18)' onmouseover='mouseOverColorbottom("#333300",0)'
      alt='#333300'/>
<area style='cursor:pointer' shape='poly' coords='45,120,54,124,54,135,45,139,36,135,36,124'
      onclick='clickColor("#669900",0,140,36)' onmouseover='mouseOverColorbottom("#669900",0)'
      alt='#669900'/>
<area style='cursor:pointer' shape='poly' coords='63,120,72,124,72,135,63,139,54,135,54,124'
      onclick='clickColor("#99FF33",0,140,54)' onmouseover='mouseOverColorbottom("#99FF33",0)'
      alt='#99FF33'/>
<area style='cursor:pointer' shape='poly' coords='81,120,90,124,90,135,81,139,72,135,72,124'
      onclick='clickColor("#CCFF66",0,140,72)' onmouseover='mouseOverColorbottom("#CCFF66",0)'
      alt='#CCFF66'/>
<area style='cursor:pointer' shape='poly' coords='99,120,108,124,108,135,99,139,90,135,90,124'
      onclick='clickColor("#FFFF99",0,140,90)' onmouseover='mouseOverColorbottom("#FFFF99",0)'
      alt='#FFFF99'/>
<area style='cursor:pointer' shape='poly'
      coords='117,120,126,124,126,135,117,139,108,135,108,124'
      onclick='clickColor("#FFCC99",0,140,108)' onmouseover='mouseOverColorbottom("#FFCC99",0)'
      alt='#FFCC99'/>
<area style='cursor:pointer' shape='poly'
      coords='135,120,144,124,144,135,135,139,126,135,126,124'
      onclick='clickColor("#FF9999",0,140,126)' onmouseover='mouseOverColorbottom("#FF9999",0)'
      alt='#FF9999'/>
<area style='cursor:pointer' shape='poly'
      coords='153,120,162,124,162,135,153,139,144,135,144,124'
      onclick='clickColor("#FF6699",0,140,144)' onmouseover='mouseOverColorbottom("#FF6699",0)'
      alt='#FF6699'/>
<area style='cursor:pointer' shape='poly'
      coords='171,120,180,124,180,135,171,139,162,135,162,124'
      onclick='clickColor("#FF3399",0,140,162)' onmouseover='mouseOverColorbottom("#FF3399",0)'
      alt='#FF3399'/>
<area style='cursor:pointer' shape='poly'
      coords='189,120,198,124,198,135,189,139,180,135,180,124'
      onclick='clickColor("#CC3399",0,140,180)' onmouseover='mouseOverColorbottom("#CC3399",0)'
      alt='#CC3399'/>
<area style='cursor:pointer' shape='poly'
      coords='207,120,216,124,216,135,207,139,198,135,198,124'
      onclick='clickColor("#990099",0,140,198)' onmouseover='mouseOverColorbottom("#990099",0)'
      alt='#990099'/>
<area style='cursor:pointer' shape='poly' coords='36,135,45,139,45,150,36,154,27,150,27,139'
      onclick='clickColor("#666633",0,155,27)' onmouseover='mouseOverColorbottom("#666633",0)'
      alt='#666633'/>
<area style='cursor:pointer' shape='poly' coords='54,135,63,139,63,150,54,154,45,150,45,139'
      onclick='clickColor("#99CC00",0,155,45)' onmouseover='mouseOverColorbottom("#99CC00",0)'
      alt='#99CC00'/>
<area style='cursor:pointer' shape='poly' coords='72,135,81,139,81,150,72,154,63,150,63,139'
      onclick='clickColor("#CCFF33",0,155,63)' onmouseover='mouseOverColorbottom("#CCFF33",0)'
      alt='#CCFF33'/>
<area style='cursor:pointer' shape='poly' coords='90,135,99,139,99,150,90,154,81,150,81,139'
      onclick='clickColor("#FFFF66",0,155,81)' onmouseover='mouseOverColorbottom("#FFFF66",0)'
      alt='#FFFF66'/>
<area style='cursor:pointer' shape='poly' coords='108,135,117,139,117,150,108,154,99,150,99,139'
      onclick='clickColor("#FFCC66",0,155,99)' onmouseover='mouseOverColorbottom("#FFCC66",0)'
      alt='#FFCC66'/>
<area style='cursor:pointer' shape='poly'
      coords='126,135,135,139,135,150,126,154,117,150,117,139'
      onclick='clickColor("#FF9966",0,155,117)' onmouseover='mouseOverColorbottom("#FF9966",0)'
      alt='#FF9966'/>
<area style='cursor:pointer' shape='poly'
      coords='144,135,153,139,153,150,144,154,135,150,135,139'
      onclick='clickColor("#FF6666",0,155,135)' onmouseover='mouseOverColorbottom("#FF6666",0)'
      alt='#FF6666'/>
<area style='cursor:pointer' shape='poly'
      coords='162,135,171,139,171,150,162,154,153,150,153,139'
      onclick='clickColor("#FF0066",0,155,153)' onmouseover='mouseOverColorbottom("#FF0066",0)'
      alt='#FF0066'/>
<area style='cursor:pointer' shape='poly'
      coords='180,135,189,139,189,150,180,154,171,150,171,139'
      onclick='clickColor("#CC6699",0,155,171)' onmouseover='mouseOverColorbottom("#CC6699",0)'
      alt='#CC6699'/>
<area style='cursor:pointer' shape='poly'
      coords='198,135,207,139,207,150,198,154,189,150,189,139'
      onclick='clickColor("#993366",0,155,189)' onmouseover='mouseOverColorbottom("#993366",0)'
      alt='#993366'/>
<area style='cursor:pointer' shape='poly' coords='45,150,54,154,54,165,45,169,36,165,36,154'
      onclick='clickColor("#999966",0,170,36)' onmouseover='mouseOverColorbottom("#999966",0)'
      alt='#999966'/>
<area style='cursor:pointer' shape='poly' coords='63,150,72,154,72,165,63,169,54,165,54,154'
      onclick='clickColor("#CCCC00",0,170,54)' onmouseover='mouseOverColorbottom("#CCCC00",0)'
      alt='#CCCC00'/>
<area style='cursor:pointer' shape='poly' coords='81,150,90,154,90,165,81,169,72,165,72,154'
      onclick='clickColor("#FFFF00",0,170,72)' onmouseover='mouseOverColorbottom("#FFFF00",0)'
      alt='#FFFF00'/>
<area style='cursor:pointer' shape='poly' coords='99,150,108,154,108,165,99,169,90,165,90,154'
      onclick='clickColor("#FFCC00",0,170,90)' onmouseover='mouseOverColorbottom("#FFCC00",0)'
      alt='#FFCC00'/>
<area style='cursor:pointer' shape='poly'
      coords='117,150,126,154,126,165,117,169,108,165,108,154'
      onclick='clickColor("#FF9933",0,170,108)' onmouseover='mouseOverColorbottom("#FF9933",0)'
      alt='#FF9933'/>
<area style='cursor:pointer' shape='poly'
      coords='135,150,144,154,144,165,135,169,126,165,126,154'
      onclick='clickColor("#FF6600",0,170,126)' onmouseover='mouseOverColorbottom("#FF6600",0)'
      alt='#FF6600'/>
<area style='cursor:pointer' shape='poly'
      coords='153,150,162,154,162,165,153,169,144,165,144,154'
      onclick='clickColor("#FF5050",0,170,144)' onmouseover='mouseOverColorbottom("#FF5050",0)'
      alt='#FF5050'/>
<area style='cursor:pointer' shape='poly'
      coords='171,150,180,154,180,165,171,169,162,165,162,154'
      onclick='clickColor("#CC0066",0,170,162)' onmouseover='mouseOverColorbottom("#CC0066",0)'
      alt='#CC0066'/>
<area style='cursor:pointer' shape='poly'
      coords='189,150,198,154,198,165,189,169,180,165,180,154'
      onclick='clickColor("#660033",0,170,180)' onmouseover='mouseOverColorbottom("#660033",0)'
      alt='#660033'/>
<area style='cursor:pointer' shape='poly' coords='54,165,63,169,63,180,54,184,45,180,45,169'
      onclick='clickColor("#996633",0,185,45)' onmouseover='mouseOverColorbottom("#996633",0)'
      alt='#996633'/>
<area style='cursor:pointer' shape='poly' coords='72,165,81,169,81,180,72,184,63,180,63,169'
      onclick='clickColor("#CC9900",0,185,63)' onmouseover='mouseOverColorbottom("#CC9900",0)'
      alt='#CC9900'/>
<area style='cursor:pointer' shape='poly' coords='90,165,99,169,99,180,90,184,81,180,81,169'
      onclick='clickColor("#FF9900",0,185,81)' onmouseover='mouseOverColorbottom("#FF9900",0)'
      alt='#FF9900'/>
<area style='cursor:pointer' shape='poly' coords='108,165,117,169,117,180,108,184,99,180,99,169'
      onclick='clickColor("#CC6600",0,185,99)' onmouseover='mouseOverColorbottom("#CC6600",0)'
      alt='#CC6600'/>
<area style='cursor:pointer' shape='poly'
      coords='126,165,135,169,135,180,126,184,117,180,117,169'
      onclick='clickColor("#FF3300",0,185,117)' onmouseover='mouseOverColorbottom("#FF3300",0)'
      alt='#FF3300'/>
<area style='cursor:pointer' shape='poly'
      coords='144,165,153,169,153,180,144,184,135,180,135,169'
      onclick='clickColor("#FF0000",0,185,135)' onmouseover='mouseOverColorbottom("#FF0000",0)'
      alt='#FF0000'/>
<area style='cursor:pointer' shape='poly'
      coords='162,165,171,169,171,180,162,184,153,180,153,169'
      onclick='clickColor("#CC0000",0,185,153)' onmouseover='mouseOverColorbottom("#CC0000",0)'
      alt='#CC0000'/>
<area style='cursor:pointer' shape='poly'
      coords='180,165,189,169,189,180,180,184,171,180,171,169'
      onclick='clickColor("#990033",0,185,171)' onmouseover='mouseOverColorbottom("#990033",0)'
      alt='#990033'/>
<area style='cursor:pointer' shape='poly' coords='63,180,72,184,72,195,63,199,54,195,54,184'
      onclick='clickColor("#663300",0,200,54)' onmouseover='mouseOverColorbottom("#663300",0)'
      alt='#663300'/>
<area style='cursor:pointer' shape='poly' coords='81,180,90,184,90,195,81,199,72,195,72,184'
      onclick='clickColor("#996600",0,200,72)' onmouseover='mouseOverColorbottom("#996600",0)'
      alt='#996600'/>
<area style='cursor:pointer' shape='poly' coords='99,180,108,184,108,195,99,199,90,195,90,184'
      onclick='clickColor("#CC3300",0,200,90)' onmouseover='mouseOverColorbottom("#CC3300",0)'
      alt='#CC3300'/>
<area style='cursor:pointer' shape='poly'
      coords='117,180,126,184,126,195,117,199,108,195,108,184'
      onclick='clickColor("#993300",0,200,108)' onmouseover='mouseOverColorbottom("#993300",0)'
      alt='#993300'/>
<area style='cursor:pointer' shape='poly'
      coords='135,180,144,184,144,195,135,199,126,195,126,184'
      onclick='clickColor("#990000",0,200,126)' onmouseover='mouseOverColorbottom("#990000",0)'
      alt='#990000'/>
<area style='cursor:pointer' shape='poly'
      coords='153,180,162,184,162,195,153,199,144,195,144,184'
      onclick='clickColor("#800000",0,200,144)' onmouseover='mouseOverColorbottom("#800000",0)'
      alt='#800000'/>
<area style='cursor:pointer' shape='poly'
      coords='171,180,180,184,180,195,171,199,162,195,162,184'
      onclick='clickColor("#993333",0,200,162)' onmouseover='mouseOverColorbottom("#993333",0)'
      alt='#993333'/>
</map>
<script>
    document.getElementById("selectedColorbottom").style.top = "35px"
    document.getElementById("selectedColorbottom").style.left = "135px"
    document.getElementById("selectedColorbottom").style.visibility = "visible"
</script>
</td>
</tr>
</table>

</form>
<!--混色区域结束-->
</div>
<div style="clear:both;"></div>
<div id="footer">Footer</div>
</div>
</div>
</body>
</html>

