<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>后台管理主页</title>
<meta name="description" content="这是一个 后台管理主页面">
<meta name="keywords" content="index">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="shopWMS/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="shopWMS/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="shopWMS/css/amazeui.min.css" />
<link rel="stylesheet" href="shopWMS/css/admin.css">
<script src="shopWMS/js/jquery.min.js"></script>
<script src="shopWMS/js/app.js"></script>
</head>

<body>
	<!--页面Header部分-->
	<header class="am-topbar admin-header">
	<div class="am-topbar-brand">
		<img src="shopWMS/i/logo.png">
	</div>
	<div class="am-collapse am-topbar-collapse" id="topbar-collapse">
		<ul class="am-nav am-nav-pills am-topbar-nav admin-header-list">
			<li class="am-dropdown tognzhi" data-am-dropdown>
				<button
					class="am-btn am-btn-primary am-dropdown-toggle am-btn-xs am-radius am-icon-bell-o"
					data-am-dropdown-toggle>
					消息管理<span class="am-badge am-badge-danger am-round">.</span>
				</button>
				<ul class="am-dropdown-content">

					<li class="am-dropdown-header">所有消息都在这里</li>
					<li><a href="findcontactUsList">买家留言</a></li>
					<li><a href="findOrdersList">买家订单 <span
							class="am-badge am-badge-danger am-round">.</span></a> </a></li>
				</ul>
			</li>

			<li class="kuanjie"><a href="BCommoditySales">销量展示</a> <a
				href="findOrdersList">查看已购买订单</a> <a href="findOrdersLists">查看已完成订单</a>
				<a href="findUserList">查看用户中心</a></li>

			<li class="am-hide-sm-only" style="float: right;"><a
				href="javascript:;" id="admin-fullscreen"><span
					class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a>
			</li>
		</ul>
	</div>
	</header>

	<div class="am-cf admin-main">
		<div class="nav-navicon admin-main admin-sidebar">
			<div class="sideMenu am-icon-dashboard"
				style="color: #aeb2b7; margin: 10px 0 0 0;">
				欢迎系统管理员：${sessionScope.admin.aname}</div>
			<div class="sideMenu">
				<h3 class="am-icon-hand-o-left">
					<em></em> <a href="#">后台权限管理</a>
				</h3>
				<ul>
					<c:forEach items="${sessionScope.plist}" var="plists">
						<li><a href="${plists.purl }">${plists.pname}</a></li>
					</c:forEach>
				</ul>
				<h3 class="am-icon-users">
					<em></em> <a href="#"> 用户管理</a>
				</h3>
				<ul>
					<li><a href="findUserList">查看用户信息</a></li>
					<li><a href="updateUser">修改用户信息</a></li>
				</ul>
				<h3 class="am-icon-cart-plus">
					<em></em> <a href="#">商品管理</a>
				</h3>
				<ul>
					<li><a href="findGategoriesList">查看商品分类</a></li>
					<li><a href="updateGategories">操作商品分类</a></li>
				</ul>
				<h3 class="am-icon-pencil-square-o">
					<em></em> <a href="#">订单管理</a>
				</h3>
				<ul>
					<li><a href="findOrdersList">查看已付款订单</a></li>
					<li><a href="findOrdersLists">查看已完成订单</a></li>
				</ul>
				<h3 class="am-icon-comment-o">
					<em></em> <a href="#">买家留言</a>
				</h3>
				<ul>
					<li><a href="findcontactUsList">查看留言</a></li>
				</ul>
				<h3 class="am-icon-comments">
					<em></em> <a href="#">商品评论管理</a>
				</h3>
				<ul>
					<li><a href="findcommodityCommentList">查看商品评论</a></li>
				</ul>
				<!-- <h3 class="am-icon-volume-up"><em></em> <a href="#">商品销量管理</a></h3>
					<ul>
						<li><a href="findsalesVolumeList">查看商品销量</a></li> -->
				</ul>
				<h3 class="am-icon-line-chart">
					<em></em> <a href="#">图表分析</a>
				</h3>
				<ul>
					<li><a href="BCommoditySales">查看商品销量图</a></li>
				</ul>
			</div>
			<!-- sideMenu End -->
			<script type="text/javascript">
					jQuery(".sideMenu").slide({
						titCell: "h3", //鼠标触发对象
						targetCell: "ul", //与titCell一一对应，第n个titCell控制第n个targetCell的显示隐藏
						effect: "slideDown", //targetCell下拉效果
						delayTime: 300, //效果时间
						triggerTime: 150, //鼠标延迟触发时间（默认150）
						defaultPlay: true, //默认是否执行效果（默认true）
						returnDefault: true //鼠标从.sideMen移走后返回默认状态（默认false）
					});
				</script>
		</div>

		<div class=" admin-content">
			<div class="daohang">
				<ul>
					<li><button type="button"
							class="am-btn am-btn-default am-radius am-btn-xs">角色权限列表</li>

				</ul>
			</div>

			<div class="admin-biaogelist">
				<div class="listbiaoti am-cf">
					<ul class="am-icon-flag on">栏目：权限管理
					</ul>
					<dl class="am-icon-home" style="float: right;">
						当前位置： 首页 >
						<a href="#">角色权限列表</a>
					</dl>
				</div>

				<div class="am-form am-g">
					<table width="100%"
						class="am-kai  am-table am-table-bordered am-table-radius am-table-striped">
						<h2 class="am-kai">
							<center style="color: green;">${roleName }角色具有权限：</center>
						</h2>
						<thead>
							<tr class="am-success">
								<th class="table-author am-hide-sm-only">权限名称</th>
								<th class="table-author am-hide-sm-only">权限介绍</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="5">${errtip1}</td>
							</tr>
							<c:forEach items="${plist1}" var="plists">
								<tr>
									<td><c:out value="${plists.pname }"></c:out></td>
									<td><c:out value="${plists.pmessage}"></c:out></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<center>
						<a class="am-btn am-btn-default am-btn-ls am-text-danger am-round"
							href="findRoleList?pageNumberX=1">返回</a>
					</center>
				</div>

				<div class="foods" style="margin-top: 100px;">
					<ul>
						Copyright &copy; 2018.QiChangjian All rights reserved.毕业论文
						<a href="#">服装购物网站（后台）</a>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<!--[if lt IE 9]>
		<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
		<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
		<script src="shopWMS/js/polyfill/rem.min.js"></script>
		<script src="shopWMS/js/polyfill/respond.min.js"></script>
		<script src="shopWMS/js/amazeui.legacy.js"></script>
		<![endif]-->

	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="shopWMS/js/amazeui.min.js"></script>
	<!--<![endif]-->
</body>
</html>