<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<title>YTPet - login</title>

		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<!--移动设备优先 禁止用户缩放-->
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

		<!--引入css-->
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/bootstrap-theme.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/base/login.css"/>
		

	</head>
	<body>
		<div class="container">
			<div class="content">
				<div class="row">
					<div class="col-lg-6 col-xs-6 text-right">
						<img src="<%= request.getContextPath() %>/static/img/logo.gif" class="img-circle"/>
					</div>
					<div class="col-lg-6 col-xs-6 text-left">
						<span style="display:block; padding-top: 25px; font-family: 'estrangelo edessa';font-size: 20px; font-weight: 700;">管理員密碼:</span>
						<div class="input-group">
					      <input type="password" id="password" class="form-control" style="background-color: #343434; opacity: 0.5;" placeholder="管理員密碼">
					      <span class="input-group-btn">
					        <button class="btn btn-default" type="button" style="padding: 0; margin: 0;" onclick="ajaxlogin('<%= request.getContextPath() %>/admin/login','<%= request.getContextPath() %>/admin/index')"><img src="<%= request.getContextPath() %>/static/img/btn.jpg" style="height: 32px;"></button>
					      </span>
					    </div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12 text-center footer">
						(c) Copyright 2017 有它宠物. 版权所有. 
					</div>
				</div>
			</div>
		</div>

		<!--引入js-->
		<script src="<%= request.getContextPath() %>/static/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%= request.getContextPath() %>/static/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%= request.getContextPath() %>/static/js/base/login.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
