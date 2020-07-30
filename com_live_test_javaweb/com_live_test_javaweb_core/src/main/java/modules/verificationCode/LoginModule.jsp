<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录到后台模块界面</title>

<script type="text/javascript">
	function myReload() {
		document.getElementById("CreateCheckCode").src = document
			.getElementById("CreateCheckCode").src
		+ "?nocache=" + new Date().getTime();
	}
</script>

</head>
<body>
	<center>
		<h1 style="color:red">登录</h1>
		<form id="indexform" name="indexForm" action="module_logins.do"
			method="post">
			<table border="0">
				<tr>
					<td>账号：</td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password"></td>
				</tr>

				<tr>
					<td>验证码：</td>
					<td><input title="${trans.请在此处输入验证码}" id="code" name="code"
						type="text" /></td>
					<td><img src="PictureCheckCode" id="CreateCheckCode"
						align="middle" class="img-text"></td>
					<td><a href="" onclick="myReload()"> 看不清,换一个</a></td>
				</tr>
				
				<tr>
					<td>
						<b style="color: red">${requestScope.logErr }</b>
					</td>
				</tr>

				<!-- <div style="width:350px; height:37px" >
						</div> -->
			</table>
			<br> <input type="submit" value="登录" style="color:#BC8F8F">
		</form>

	</center>
</body>
</html>