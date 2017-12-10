<%@ page contentType="text/html; charset=utf-8" language="java" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>有它宠物 - 主面板</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<!--引入css-->
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/bootstrap-theme.min.css"/>
		<!--一些自定义的样式-->
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css"/>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/base/index.css"/>
		<!--引入js-->
		<script src="<%= request.getContextPath() %>/static/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%= request.getContextPath() %>/static/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%= request.getContextPath() %>/static/js/base/loginoff.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#"><span>YT - Pet</span>后台管理</a>
					<ul class="user-menu">
						<li class="dropdown pull-right">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span><span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li onclick="loginoff('<%= request.getContextPath() %>/admin/tologin')"><a href="#"><span class="glyphicon glyphicon-log-out"></span> 退出登录</a></li>
							</ul>
						</li>
					</ul>
				</div>
								
			</div><!-- /.container-fluid -->
		</nav>
		
		<!--左侧的导航栏-->
		<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
			<form role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="查找">
				</div>
			</form>
			<div class="accordion-group">
				<div class="accordion-heading menu-tap">
					<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
						<span class="glyphicon glyphicon-user"></span>会员管理
					</a>
				</div>
				<div id="collapseOne" class="accordion-body collapse in" style="height: 0px; ">
					<ul class="nav menu">
						<li><a href="<%= request.getContextPath() %>/admin/primaryinfo"><span class="glyphicon glyphicon-sunglasses"></span> 会员基本信息</a></li>
						<li><a href="<%= request.getContextPath() %>/admin/balance"><span class="glyphicon glyphicon-jpy"></span> 余额管理</a></li>
						<li><a href="<%= request.getContextPath() %>/admin/charge"><span class="glyphicon glyphicon-usd"></span> 充值管理</a></li>
						<li><a href="<%= request.getContextPath() %>/admin/consume"><span class="glyphicon glyphicon-shopping-cart"></span> 消费管理</a></li>
					</ul>
				</div>
			</div>
			<div class="attribution">(c) 有它宠物  2017. 版权所有.  </div>
		</div><!--/.sidebar-->
		
		<!--这里是右边部分的主面板了-->
		<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
			<div class="row">
				<div class="col-md-6 col-xs-12 col-lg-6 col-lg-offset-3 col-md-offset-3 text-center main">
					<h1>欢迎来到有它宠物后台管理系统</h1>
				</div>
				
			</div>
		</div>
	</body>
</html>
