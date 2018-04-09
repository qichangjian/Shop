<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>AdminLoginPage</title>
<meta name="description" content="这是一个 登陆 页面">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="apple-touch-icon-precomposed"
	href="shopWMS/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="shopWMS/css/amazeui.min.css" />
<link rel="stylesheet" href="shopWMS/css/amazeui.datatables.min.css" />
<link rel="stylesheet" href="shopWMS/css/app.css">
<script src="shopWMS/js/jquery.min.js"></script>
</head>

<body
	style="background-image: url('shopWMS/i/bg.png'); background-size: cover;">
	<script src="shopWMS/js/theme.js"></script>
	<div class="am-g tpl-g">
		<div class="tpl-login">
			<div class="tpl-login-content">
				<div class="tpl-login-logo">
					<center>
						<img src="shopWMS/i/logo2.png" />
					</center>
				</div>
				<form class="am-form tpl-form-line-form" action="adminlogin"
					method="post">
					<div class="am-form-group">
						<input type="text" class="tpl-form-input" id="aname" name="aname"
							placeholder="请输入账号">
					</div>
					<div class="am-form-group">
						<input type="password" class="tpl-form-input" id="apassword"
							name="apassword" placeholder="请输入密码">
						<c:if test="${err == 1}">
							<h6 style="color: red">账号或者密码错误！${err == 1}</h6>
						</c:if>
					</div>
					<div class="am-form-group tpl-login-remember-me">
						<input id="remember-me" type="checkbox" checked> <label
							for="remember-me"> 记住密码 </label>
					</div>
					<div class="am-form-group">
						<button type="submit"
							class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">登录后台管理平台</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="shopWMS/js/amazeui.min.js"></script>
	<script src="shopWMS/js/app.js"></script>
</body>
</html>