<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>HTML5 Video Player</title>

  <!-- Chang URLs to wherever Video.js files will be hosted -->
  <link href="${pageContext.request.contextPath}/video-js/video-js.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/video-js/style.css" rel="stylesheet" type="text/css">
  <!-- video.js must be in the <head> for older IEs to work. -->
  <script src="${pageContext.request.contextPath}/video-js/video.js"></script>

  <!-- Unless using the CDN hosted version, update the URL to the Flash SWF -->
  <script>
    _V_.options.flash.swf = "<%=request.getContextPath()%>/video-js/video-js.swf";
  </script>
  

</head>
<body >

  <div class="container">
      <div class="videobox">
      <div class="videobox_desc">
        <h5>What's HTML5 Video?</h5>
        <p>Playing video in a web page may not seem so special since you can already view video on a web page through plugins like Flash Player, Quicktime, and Silverlight. </p>
      </div>
	  <div class="video_p">
		  <video id="example_video_1" class="video-js vjs-default-skin" controls preload="none" width="640" height="264"
		      poster="${fileInfo.thumbUrl}"
		      data-setup="{}">
		    <source src="${fileInfo.movieUrl}" type='video/mp4' />
		    <source src="oceans-clip.webm" type='video/webm' />
		  </video>
	  </div>
	  </div>
	  <!--
	  <div class="videobox">
      <div class="videobox_desc">
        <h5>What's an HTML5 Video Player?</h5>
        <p>An HTML5 Video Player is a JavaScript library that builds a custom set of controls over top of the HTML5 video element to provide a consistent look between HTML5 browsers.</p>
      </div>
	  <div class="video_p">
		  <video id="example_video_1" class="video-js vjs-default-skin" controls preload="none" width="640" height="264"
		      poster="http://innovergroup.dyndns.org:8080/upload/thumb/9f11319b0363468cb614f46bb9285779-1345183253430.jpg"
		      data-setup="{}">
		    <source src="http://innovergroup.dyndns.org:8080/upload/movies/9f11319b0363468cb614f46bb9285779-1345183253367.mp4" type='video/mp4' />
		    <source src="http://innovergroup.dyndns.org:8080/upload/movies/b54860262904462ba39eee1e5449c778-1345183257733.webm" type='video/webm' />
		  </video>
	  <div>
	  </div>
	  -->
  </div>
</body>
</html>
