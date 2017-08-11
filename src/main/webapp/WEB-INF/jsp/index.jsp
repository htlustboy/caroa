<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	body{
		background-color:#a55c5c;
	}
	li{
		font-size: 16px;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
	<div class="container" style="background-color: white;">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="row clearfix">
				<div class="col-md-12 column" style="padding-top: 10px;">
					<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					 <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span>
					 <span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">Caroa System</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active">
							 <a href="#">首页</a>
						</li>
						<li>
							 <a href="#">介绍</a>
						</li>
						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">资源下载<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="#">图片</a>
								</li>
								<li>
									 <a href="#">文档</a>
								</li>
								<li>
									 <a href="#">代码</a>
								</li>
								<li class="divider">
								</li>
								<li>
									 <a href="#">其它</a>
								</li>
							</ul>
						</li>
					</ul>
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="search"/>
						</div> <button type="submit" class="btn btn-green btn-success">搜索</button>
					</form>
					<ul class="nav navbar-nav navbar-right" <c:if test='${user==null }'>style="margin-top: 20px;margin-right: 20px;"</c:if>>
						<c:if test="${user!=null }">
							<li>
								 <a href="#">
									<img alt="40x40" src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/140/140/default.jpg" class="img-circle" style="width: 20;"/>
								 	<span style="color: green;">${user.displayName}</span>
								 </a>
							</li>
							<li class="dropdown">
								 <a href="#" class="dropdown-toggle" data-toggle="dropdown">个人设置<strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li>
										 <a href="#">个人信息</a>
									</li>
									<li>
										 <a href="#">个人信息</a>
									</li>
									<li>
										 <a href="#">修改密码</a>
									</li>
									<li>
										 <a href="#">查看历史记录</a>
									</li>
									<li class="divider">
									</li>
									<li>
										 <a href="${base }/login/logout">注销</a>
									</li>
								</ul>
							</li>
						</c:if>
						<c:if test="${user==null }">
							<li>
								 <span style="margin-top: 10px;">当前未登陆，点击<a href="${base }/login/index">登陆</a></span>
							</li>
						</c:if>
					</ul>
				</div>
				
			</nav>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-8 column">
					<!-- 滚动图 -->
					<div class="row clearfix">
						<div class="col-md-12 column">
							<div class="carousel slide" id="carousel-735648">
								<ol class="carousel-indicators">
									<li class="active" data-slide-to="0" data-target="#carousel-735648">
									</li>
									<li data-slide-to="1" data-target="#carousel-735648">
									</li>
									<li data-slide-to="2" data-target="#carousel-735648">
									</li>
								</ol>
								<div class="carousel-inner">
									<div class="item active">
										<img alt="" src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/1600/500/sports/1/default.jpg" />
										<div class="carousel-caption">
											<h4>
												First Thumbnail label
											</h4>
											<p>
												Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
											</p>
										</div>
									</div>
									<div class="item">
										<img alt="" src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/1600/500/sports/2/default.jpg" />
										<div class="carousel-caption">
											<h4>
												Second Thumbnail label
											</h4>
											<p>
												Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
											</p>
										</div>
									</div>
									<div class="item">
										<img alt="" src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/1600/500/sports/3/default.jpg" />
										<div class="carousel-caption">
											<h4>
												Third Thumbnail label
											</h4>
											<p>
												Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
											</p>
										</div>
									</div>
								</div> <a class="left carousel-control" href="#carousel-735648" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-735648" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
							</div>
						</div>
					</div>
					
					<br><br>
					
					<!-- 略缩图 -->
					<div class="row clearfix">
						<div class="col-md-12 column">
							<div class="row">
								<div class="col-md-4">
									<div class="thumbnail">
										<img alt="300x200" src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/600/200/people/default.jpg" />
										<div class="caption">
											<h3>
												Thumbnail label
											</h3>
											<p>
												Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
											</p>
											<p>
												 <a class="btn btn-primary" href="#">Action</a> <a class="btn" href="#">Action</a>
											</p>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="thumbnail">
										<img alt="300x200" src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/600/200/city/default.jpg" />
										<div class="caption">
											<h3>
												Thumbnail label
											</h3>
											<p>
												Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
											</p>
											<p>
												 <a class="btn btn-primary" href="#">Action</a> <a class="btn" href="#">Action</a>
											</p>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="thumbnail">
										<img alt="300x200" src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/600/200/sports/default.jpg" />
										<div class="caption">
											<h3>
												Thumbnail label
											</h3>
											<p>
												Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
											</p>
											<p>
												 <a class="btn btn-primary" href="#">Action</a> <a class="btn" href="#">Action</a>
											</p>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="thumbnail">
										<img alt="300x200" src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/600/200/people/default.jpg" />
										<div class="caption">
											<h3>
												Thumbnail label
											</h3>
											<p>
												Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
											</p>
											<p>
												 <a class="btn btn-primary" href="#">Action</a> <a class="btn" href="#">Action</a>
											</p>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="thumbnail">
										<img alt="300x200" src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/600/200/city/default.jpg" />
										<div class="caption">
											<h3>
												Thumbnail label
											</h3>
											<p>
												Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
											</p>
											<p>
												 <a class="btn btn-primary" href="#">Action</a> <a class="btn" href="#">Action</a>
											</p>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="thumbnail">
										<img alt="300x200" src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/600/200/sports/default.jpg" />
										<div class="caption">
											<h3>
												Thumbnail label
											</h3>
											<p>
												Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
											</p>
											<p>
												 <a class="btn btn-primary" href="#">Action</a> <a class="btn" href="#">Action</a>
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4 column">
					<h4 style="color: green">热门文档</h4>
					<ol >
						<c:forEach items="${hotfile }" var="map">
							<li><a href="${base }/file/fileDetail/${map.id }" fid="${map.id }">${map.fileName }</a></li>
						</c:forEach>
					</ol>
					
					<h4 style="color: green">最新文档</h4>
					<ol>
						<c:forEach items="${newfile }" var="map">
							<li><a href="${base }/file/fileDetail/${map.id }" fid="${map.id }">${map.fileName }</a></li>
						</c:forEach>
					</ol>
					
					<h4 style="color: green">历史纪录</h4>
					<ol>	
						<c:forEach var="cookie" items="${history }">
							<li>${cookie.name }</li>
						</c:forEach>
					</ol>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="container">
	<div class="row clearfix" style="margin-top: 30px;">
			<div class="col-md-12 column">
				<p style="text-align: center;font-size: 16px;color: white;">
					copyright@20170809-lustboy-941015ace
				</p> 
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	
</script>
</html>