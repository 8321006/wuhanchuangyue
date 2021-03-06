<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	System.out.println(basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<title>演示 - Web Uploader</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
<link rel="shortcut icon" href="webuploader/images/favicon.ico">
<link rel="stylesheet" type="text/css"
	href="webuploader/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="webuploader/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css"
	href="webuploader/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="webuploader/css/syntax.css">
<link rel="stylesheet" type="text/css" href="webuploader/css/style.css">

<link rel="stylesheet" type="text/css"
	href="webuploader/css/webuploader.css">

<link rel="stylesheet" type="text/css" href="webuploader/css/demo.css">

</head>

<body>
	<div id="wrapper">
		<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand logo" href="webuploader/"> <span
						class="fa fa-cloud-upload"></span>WebUploader
					</a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a href="webuploader/getting-started.html">Getting
								started</a></li>
						<li><a href="webuploader/document.html">Document</a></li>
						<li><a href="webuploader/doc/index.html">API</a></li>
						<li class="active"><a href="webuploader/demo.html"
							class="active">Demo</a></li>
						<li><a href="webuploader/download.html">Download</a></li>
						<li><a target="_blank"
							href="https://github.com/fex-team/webuploader/issues?labels=faq&amp;page=1&amp;state=open">FAQ</a></li>
					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li><a target="_blank"
							href="https://github.com/fex-team/webuploader">Github</a></li>
					</ul>
				</div>
				<!-- /.nav-collapse -->
			</div>
			<!-- /.container -->
		</div>
		<!-- /.navbar -->
		<div class="page-body">

			<div id="post-container" class="container">

				<div class="page-container">
					<h1 id="demo">Demo</h1>

					<p>您可以尝试文件拖拽，使用QQ截屏工具，然后激活窗口后粘贴，或者点击添加图片按钮，来体验此demo.</p>

					<div id="uploader" class="wu-example">
						<div class="queueList">
							<div id="dndArea" class="placeholder">
								<div id="filePicker"></div>
								<p>或将照片拖到这里，单次最多可选300张</p>
							</div>
						</div>
						<div class="statusBar" style="display: none;">
							<div class="progress">
								<span class="text">0%</span> <span class="percentage"></span>
							</div>
							<div class="info"></div>
							<div class="btns">
								<div id="filePicker2"></div>
								<div class="uploadBtn">开始上传</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
		<div id="footer" class="footer">
			<div class="footer-inner container">
				<div class="row">
					<div class="col-md-4">
						<p class="copyright">
							Webuploader由<a href="https://github.com/fex-team">fex-team</a>团队负责维护
						</p>
						<p>&copy;2013-2018 Baidu Fex Team</p>
					</div>
					<div class="col-md-4">
						<!-- <p>友情链接</p> -->
						<ul class="friends-links">
							<li><a href="http://fis.baidu.com/" title="前端集成解决方案">Fis</a></li>
							<li><a href="http://gmu.baidu.com"
								title="基于zepto的mobile UI组件库">GMU</a></li>
							<li><a href="http://ueditor.baidu.com/website/"
								title="UEditor是由百度web前端研发部开发所见即所得富文本web编辑器，具有轻量，可定制，注重用户体验等特点，开源基于MIT协议，允许自由使用和修改代码...">Ueditor</a></li>
						</ul>
					</div>
					<div class="col-md-4">
						<div class="weixin">
							<img src="webuploader/images/qrcode.jpg" alt="..."
								class="img-rounded weixin-img" />
							<p>微信公共帐号</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		// 添加全局站点信息
		var BASE_URL = '/webuploader';
	</script>
	<script type="text/javascript"
		src="webuploader/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="webuploader/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="webuploader/js/global.js"></script>

	<script type="text/javascript" src="webuploader/js/webuploader.js"></script>

	<script type="text/javascript" src="webuploader/js/demo.js"></script>

	<script type="text/javascript">
		var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
				: " http://");
		document
				.write(unescape("%3Cscript src='"
						+ _bdhmProtocol
						+ "hm.baidu.com/h.js%3F67c4841095cbee8275705e1f6224a3c7' type='text/javascript'%3E%3C/script%3E"));
	</script>
</body>
</html>