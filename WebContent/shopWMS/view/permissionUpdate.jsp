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
<!-- <script type="text/javascript">
	$(function(){
		$("#lookpermission").click(function(){
			$("#div2").css('display','block');
			$("#div1").css('display','none');
		});
		$("#exit").click(function(){
			$("#div1").css('display','block');
			$("#div2").css('display','none');
		});
	});

</script> -->
<!--<link rel="stylesheet" type="text/css" href="http://cdn.amazeui.org/amazeui/2.4.2/css/amazeui.min.css" />
		<script type="text/javascript" src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
		<script type="text/javascript" src="http://cdn.amazeui.org/amazeui/2.4.2/js/amazeui.min.js"></script>-->
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
					消息管理<span class="am-badge am-badge-danger am-round">6</span>
				</button>
				<ul class="am-dropdown-content">

					<li class="am-dropdown-header">所有消息都在这里</li>
					<li><a href="#">未激活会员 <span
							class="am-badge am-badge-danger am-round">556</span></a></li>
					<li><a href="#">未激活代理 <span
							class="am-badge am-badge-danger am-round">69</span></a> </a></li>
					<li><a href="#">未处理汇款</a></li>
					<li><a href="#">未发放提现</a></li>
					<li><a href="#">未发货订单</a></li>
					<li><a href="#">低库存产品</a></li>
					<li><a href="#">信息反馈</a></li>
				</ul>
			</li>

			<li class="kuanjie"><a href="#">会员管理</a> <a href="#">奖金管理</a> <a
				href="#">订单管理</a> <a href="#">产品管理</a> <a href="#">个人中心</a> <a
				href="#">系统设置</a></li>

			<li class="soso">
				<p>
					<select
						data-am-selected="{btnWidth: 70, btnSize: 'sm', btnStyle: 'default'}">
						<option value="b">全部</option>
						<option value="o">产品</option>
						<option value="o">会员</option>
					</select>
				</p>
				<p class="ycfg">
					<input type="text" class="am-form-field am-input-sm"
						placeholder="圆角表单域" />
				</p>
				<p>
					<button class="am-btn am-btn-xs am-btn-default am-xiao">
						<i class="am-icon-search"></i>
					</button>
				</p>
			</li>
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
				<h3 class="am-icon-flag">
					<em></em> <a href="#">后台权限管理</a>
				</h3>
				<ul>
					<c:forEach items="${sessionScope.plist}" var="plists">
						<li><a href="${plists.purl }">${plists.pname}</a></li>
					</c:forEach>
				</ul>
				<h3 class="am-icon-cart-plus">
					<em></em> <a href="#"> 订单管理</a>
				</h3>
				<ul>
					<li>订单列表</li>
					<li>合并订单</li>
					<li>订单打印</li>
					<li>添加订单</li>
					<li>发货单列表</li>
					<li>换货单列表</li>
				</ul>
				<h3 class="am-icon-users">
					<em></em> <a href="#">会员管理</a>
				</h3>
				<ul>
					<li>会员列表</li>
					<li>未激活会员</li>
					<li>团队系谱图</li>
					<li>会员推荐图</li>
					<li>推荐列表</li>
				</ul>
				<h3 class="am-icon-volume-up">
					<em></em> <a href="#">信息通知</a>
				</h3>
				<ul>
					<li>站内消息 /留言</li>
					<li>短信</li>
					<li>邮件</li>
					<li>微信</li>
					<li>客服</li>
				</ul>
				<h3 class="am-icon-gears">
					<em></em> <a href="#">系统设置</a>
				</h3>
				<ul>
					<li>数据备份</li>
					<li>邮件/短信管理</li>
					<li>上传/下载</li>
					<li>权限</li>
					<li>网站设置</li>
					<li>第三方支付</li>
					<li>提现 /转账 出入账汇率</li>
					<li>平台设置</li>
					<li>声音文件</li>
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
							class="am-btn am-btn-default am-radius am-btn-xs">首页</li>
					<li><button type="button"
							class="am-btn am-btn-default am-radius am-btn-xs">
							帮助中心<a href="javascript: void(0)" class="am-close am-close-spin"
								data-am-modal-close="">×</a>
						</button></li>
					<li><button type="button"
							class="am-btn am-btn-default am-radius am-btn-xs">
							奖金管理<a href="javascript: void(0)" class="am-close am-close-spin"
								data-am-modal-close="">×</a>
						</button></li>
					<li><button type="button"
							class="am-btn am-btn-default am-radius am-btn-xs">
							产品管理<a href="javascript: void(0)" class="am-close am-close-spin"
								data-am-modal-close="">×</a>
						</button></li>
				</ul>
			</div>

			<div class="admin-biaogelist">
			
				<div class="listbiaoti am-cf">
					<ul class="am-icon-flag on">栏目名称
					</ul>
					<dl class="am-icon-home" style="float: right;">
						当前位置： 首页 >
						<a href="#">权限更改</a>
					</dl>
				</div>
				<div  class="fbneirong">
			
					<form class="am-form" action="permissionUpdate" method="post">
						<div class="am-form-group am-cf">
							<div class="zuo">权限Id：</div>
							<div class="you">
								<input type="text" class="am-input-sm" id="pid" name="pid" readOnly
									placeholder="${permissionId}" value="${permissionId}">
							</div>
						</div>
						<div class="am-form-group am-cf">
							<div class="zuo">权限名：</div>
							<div class="you">
								<input type="text" class="am-input-sm" id="pname" name="pname"
									placeholder="${permissionName}" value="${permissionName}">
							</div>
						</div>
						<div class="am-form-group am-cf">
							<div class="zuo">URL：</div>
							<div class="you">
								<input type="text" class="am-input-sm" id="purl" name="purl"
									placeholder="${permissionUrl}" value="${permissionUrl}">
							</div>
						</div>
						<div class="am-form-group am-cf">
							<div class="zuo">权限描述：</div>
							<div class="you">
								<input type="text" class="am-input-sm" id="pmessage" name="pmessage"
									placeholder="${permissionMessage}" value="${permissionMessage}">
							</div>
						</div>
						<div class="am-form-group am-cf">
							<div class="you" style="margin-left: 22%;">
								<button type="submit"
									class="am-btn am-btn-success am-radius">确认修改此权限</button>
							</div>
						</div>
					</form>
				</div>
				<div class="foods" style="margin-top:200px;">
					<ul>
						版权所有@2015. 模板收集自
						<a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a>
						- More Templates
						<a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a>
					</ul>
					<dl>
						<a href="" title="返回头部" class="am-icon-btn am-icon-arrow-up"></a>
					</dl>
				</div>
			</div>
		</div>
	</div>
	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="shopWMS/js/amazeui.min.js"></script>
	<!--<![endif]-->
</body>
</html>