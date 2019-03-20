<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Autenticação</title>
<script src="js/jquery-3.3.1.js"></script>
<script src="js/autenticar.js"></script>
</head>
<body>

<div id="loginDiv">
<form method="POST" id="loginForm">
<input type="text" id="tf_user_name" placeholder="Login"/>
<input type="password" id="tf_user_password" placeholder="Password">
<input type="button" id="btn-login" value="Login"/>
</form>
</div>


</body>
</html>