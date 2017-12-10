<%@ page language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>有它宠物- 会员基本信息</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta http-equiv="content-type" content="text/html" charset="utf-8" >
		<!--引入css-->
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/bootstrap-theme.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/bootstrap-table.css"/>
		<!--一些自定义的样式-->
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css"/>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/base/primaryinfo.css"/>
		

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
						<li class="active"><a href="<%= request.getContextPath() %>/admin/primaryinfo"><span class="glyphicon glyphicon-sunglasses"></span> 会员基本信息</a></li>
						<li><a href="<%= request.getContextPath() %>/admin/balance"><span class="glyphicon glyphicon-jpy"></span> 余额管理</a></li>
						<li><a href="<%= request.getContextPath() %>/admin/charge"><span class="glyphicon glyphicon-usd"></span> 充值管理</a></li>
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
					<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
					<li class="active">会员基本信息</li>
				</ol>
			</div><!--/.row-->
			
			<!--这里是表格和相关按钮-->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel panel-heading">
							<button class="btn btn-primary" data-toggle="modal" data-target="#addvip">增加用户</button>
							<button class="btn btn-primary" name="deleteCustom" data-toggle="modal" data-target="#deletevip" disabled="disabled">删除用户</button>
							<button class="btn btn-primary" name="updateCustom" data-toggle="modal" data-target="#updatevip" disabled="disabled">修改用户信息</button>
							<button class="btn btn-primary" name="findPet" data-toggle="modal" data-target="#findpet" disabled="disabled">查询宠物信息</button>
						</div>
						<div class="panel-body">
							<table id="viptable" data-toggle="table" single-select="true" data-striped="true" data-single-select="true" data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-search="true" data-select-item-name="user-row" data-pagination="true" data-sort-name="name" data-sort-order="desc" class="table table-hover">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true" >选择</th>
										<th data-sortable="true" data-field="customid">用户ID</th>
										<th data-field="customname">用户姓名</th>
										<th data-field="customphone">用户电话</th>
										<th data-field="crttime">创建时间</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!--下面是一些弹出框-->
		<!--增加vip-->
		<div class="modal fade" id="addvip">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>
						</button>
						<h4>增加vip客户</h4>
					</div>
					<div class="modal-body">
						<div id="vipinfo">
							<label for="vipname">会员姓名:</label><input type="text" name="vipname" id="vipname" value="" placeholder="请输入会员姓名"/>
							<label for="vipphone">会员电话:</label><input type="text" name="vipphone" id="vipphone" value="" placeholder="请输入会员电话"/>
							<hr>
							<h3 style="width: 100%; text-align: center">选填信息</h3>
							<hr>
							<label for="petType">宠物体型:</label><select name="petType" id="petType">
									<option value="very small">娇小型(如鼠类)</option>
									<option value="small">小型(如猫)</option>
									<option value="big">大宠物(如狗子)</option>
									<option value="very big">超大型(如大型犬)</option>
								</select>
							<label for="petname">宠物名字:</label><input type="text" name="petname" id="petname" value="" placeholder="请输入宠物名称"/>
							<label for="petimage">宠物照片:</label><input type="file" name="petimage" id="petimage" value="选择宠物照片" />
							<hr>
							<label for="balance">余额:</label><input type="number" name="balance" id="balance" value="0.00" placeholder="余额输入.." />
							<hr>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="canceladd" class="btn btn-primary" data-dismiss="modal">取消</button>
						<button type="button" id="confirmadd" class="btn btn-primary" onclick="addVip('<%= request.getContextPath() %>/vip/add')">确定</button>
					</div>
				</div>
			</div>
		</div><!--/.addvip-->
		<!--删除vip-->
		<div class="modal fade" id="deletevip">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>
						</button>
						<h4>删除vip客户</h4>
					</div>
					<div class="modal-body">
						<h3 style="width: 100%; text-align: center;">确认删除?</h3>
						<p style="color: red">注意:</p>
						<p>删除用户将删除用户的余额信息,宠物信息等(<span style="color:red;">同样会删除充值记录与购物记录</span>),请谨慎操作!</p>
					</div>
					<div class="modal-footer">
						<button type="button" id="canceldelete" class="btn btn-primary" data-dismiss="modal">取消</button>
						<button type="button" id="confirmdelete" class="btn btn-primary" onclick="deletevip()">确定</button>
					</div>
				</div>
			</div>
		</div><!--/.deletevip-->
		<!--updatevip-->
		<div class="modal fade" id="updatevip">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>
						</button>
						<h4>修改vip客户信息</h4>
					</div>
					<div class="modal-body">
						<div id="vipupdateinfo">
							<label for="vipupdateid">会员ID号:</label><input type="text" name="vipupdateid" id="vipupdateid" value="" disabled="disabled"/><span style=" color: red;" >(不可修改内容)</span>
							<hr>
							<label for="vipupdatename">会员姓名:</label><input type="text" name="vipupdatename" id="vipupdatename" value=""/>
							<label for="vipupdatephone">会员电话:</label><input type="text" name="vipupdatephone" id="vipupdatephone" value=""/>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="cancelupdate" class="btn btn-primary" data-dismiss="modal">取消</button>
						<button type="button" id="confirmupdate" class="btn btn-primary" onclick="updatevippriminfo()">确定</button>
					</div>
				</div>
			</div>
		</div><!--/.updatevip-->
		<!--findpet-->
		<div class="modal fade" id="findpet">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>
						</button>
						<h4>查询客户宠物信息</h4>
					</div>
					<div class="modal-body">
						<div id="vippetinfo">
							<label>宠物照片:</label><img name="petimg" src="<%= request.getContextPath() %>/static/img/logo.gif" style="width: 50px; height: 50px;"/><span>此功能暂未实现</span>
							<hr>
							<label for="vippettype">宠物类型:</label><input type="text" name="pettype" id="vippettype" value="" disabled="disabled"/>
								<label for="vippetname">宠物名字:</label><input type="text" name="petname" id="vippetname" value="" disabled="disabled"/>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="cancelfindpet" class="btn btn-primary" data-dismiss="modal">取消</button>
						<button type="button" id="confirmfindpet" class="btn btn-primary" onclick="">确定</button>
					</div>
				</div>
			</div>
		</div><!--/.findpet-->

		<input id="contextPath" style="display: none" value="<%= request.getContextPath() %>"></input>

		<!--引入js-->
		<script src="<%= request.getContextPath() %>/static/js/jquery-2.1.0.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%= request.getContextPath() %>/static/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%= request.getContextPath() %>/static/js/bootstrap-table.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%= request.getContextPath() %>/static/js/base/loginoff.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%= request.getContextPath() %>/static/js/base/primaryinfo.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
