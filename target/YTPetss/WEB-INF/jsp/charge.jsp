<%@ page language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>有它宠物- 用户充值管理</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<!--引入css-->
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/bootstrap-theme.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/bootstrap-table.css"/>
		
		<!--一些自定义的样式-->
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css"/>
		

	</head>
	<body>
		<!--导航栏-->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="<%= request.getContextPath() %>/admin/index"><span>YT - Pet</span>后台管理</a>
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
		<!--导航栏结束-->
		
		<!--左侧菜单-->
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
						<li class="active"><a href="<%= request.getContextPath() %>/admin/charge"><span class="glyphicon glyphicon-usd"></span> 充值管理</a></li>
						<li><a href="<%= request.getContextPath() %>/admin/consume"><span class="glyphicon glyphicon-shopping-cart"></span> 消费管理</a></li>
					</ul>
				</div>
			</div>
			<div class="attribution">(c) 有它宠物  2017. 版权所有.  </div>
		</div><!--/.sidebar-->
		<!--左侧菜单结束-->
		
		<!--右侧主内容区-->
		<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
			<div class="row">
				<ol class="breadcrumb">
					<li><a href="#"><span class="glyphicon glyphicon-usd"></span></a></li>
					<li class="active">会员充值信息</li>
				</ol>
			</div><!--/.row-->
			
			<!--这里是表格和相关按钮-->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel panel-heading">
							<button class="btn btn-primary" name="chargebutton" data-toggle="modal" data-target="#chargemodal">充值</button>
						</div>
						<div class="panel-body">
							<table data-toggle="table" id="chargetable" data-striped="true" data-single-select="true" data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-search="true" data-select-item-name="user-row" data-pagination="true" data-sort-name="name" data-sort-order="desc" class="table table-hover">
								<thead>
									<tr>
										<th data-filed="state" data-checkbox="true">选择</th>
										<th data-sortable="true" data-field="customId">用户ID</th>
										<th data-field="customName">用户姓名</th>
										<th data-field="chargeTime">充值时间</th>
										<th data-field="amount">充值金额</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!--modal-->
		<div class="modal fade" id="chargemodal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>
						</button>
						<h4>充值金额</h4>
					</div>
					<div class="modal-body">
						<div id="chargeinfo">
							<h3 style="width: 100%; text-align: center; color: red;">敏感信息 - 注意填写</h3>
							<hr>
							<label for="vipname">会员姓名:</label><input type="text" name="vipname" id="vipname" value="" placeholder="请输入会员姓名"/>
							<label for="vipamount">充值金额:</label><input type="number" name="vipamount" id="vipamount" value="" placeholder="请输入充值金额"/>
							<hr>
							<label for="adminpwd">超级管理员密码:</label><input type="password" name="adminpwd" id="adminpwd" value="" placeholder="管理员密码"/>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="cancelcharge" class="btn btn-primary" data-dismiss="modal">取消</button>
						<button type="button" id="confirmcharge" class="btn btn-primary" onclick="addcharge()">确定</button>
					</div>
				</div>
			</div>
		</div><!--/.addvip-->

		<input id="contextPath" style="display: none" value="<%= request.getContextPath() %>"></input>
		<!--引入js-->
		<script src="<%= request.getContextPath() %>/static/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%= request.getContextPath() %>/static/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%= request.getContextPath() %>/static/js/bootstrap-table.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%= request.getContextPath() %>/static/js/base/loginoff.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%= request.getContextPath() %>/static/js/base/charge.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
