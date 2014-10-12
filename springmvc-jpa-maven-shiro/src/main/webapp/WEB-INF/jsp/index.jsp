<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"> <!--<![endif]-->

<head>
    <link type="text/css" rel="stylesheet" href="css/login.css">

    <title>Admin Login</title>
</head>
<body>

<div id="wrapper">
    <div class="animate form" id="login">
        <form autocomplete="on" action="admin/login">
            <h1>Log in</h1>
            <p style="color:red !important;" id="loginError">${login_error}</p>
            <p>
                <label data-icon="u" class="uname" for="username"> Your email or username </label>
                <input type="text" placeholder="myusername or mymail@mail.com" required="required" name="username" id="username">
            </p>
            <p>
                <label data-icon="p" class="youpasswd" for="password"> Your password </label>
                <input type="password" placeholder="eg. X8df!90EO" required="required" name="password" id="password">
            </p>
            <p class="login button" style="margin-bottom: 0px;">
                <input type="submit" value="Login">
            </p>
        </form>
    </div>

    </div>

</div>


</body>
</html>
