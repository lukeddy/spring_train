<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#httpMarkItUp").markItUp(mySettings); //mySettings 
	});
</script>
<div style="margin-left: 5px;">
	<textarea id="httpMarkItUp" style="width: 95%;height: 395px;" cols="100" rows="20"></textarea>
</div>