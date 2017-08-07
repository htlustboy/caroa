<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	body{
		margin: 0px;
	}
	.container{
		background-image: url('${basePath}/resource/img/timg.jpg');
		background-repeat: no-repeat;
	}
	.form-signin{
		height:450px;
		max-width: 330px;
		padding: 15px;
		margin: 0 auto;
	}
	input{
		margin-bottom: 20px;
	}
	.checkbox{
		margin-left: 20px;
	}
	.img-responsive{
		height: 330px;
		width: 100%;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆界面</title>
</head>
<body >
	<div class="container"  style="">
		<div class="row">
			<div class="col-md-1 column">
				
			</div>
			<div class="col-md-10 column">
				<div class="row clearfix">
					<div class="col-md-12 column">
						<div class="page-header">
							<h2>Welcome <small>caroa</small></h2>
						</div>
					</div>
				</div>
				
				<div class="row clearfix">
					<div class="col-md-7 column">
						<div class="jumbotron">
							<h1>
							</h1>
							<p>
								This is a template for a simple marketing or informational website. It includes a large 
								callout called the hero unit and three supporting pieces of content. Use it as a 
								starting point to create something more unique.
							</p>
						</div>
					</div>
					<div class="col-md-5 column">
						<form action="${base }/login/login" class="form-signin" role="form" method="post">
							<input type="hidden" name="token" value="${token }" readonly="readonly"><br>
							<h2 class="form-signin-heading">欢迎登陆</h2>
							<input type="text" name="username" class="form-control" placeholder="账号" required autofocus>
							<input type="password" name="password" class="form-control" placeholder="密码" required>
							<label class="checkbox">
								<input type="checkbox" name="remember" value="true">记住我
								<a href="${base }/login/regisiter" style="margin-left: 170px">点击注册</a>
							</label>
							<button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
							<br><span style="color: red;">${message }</span>
						</form>
					</div>
				</div>
			
				<div class="row clearfix" style="margin-bottom: 10px;">
					<div class="col-md-12 column">
						<p style="text-align: center;font-size: 16px">
							作者：lustboy<br>
							联系邮箱：838533527@qq.com<br>
							地址：XXX市XXX街道XXX号<br>
						</p> 
					</div>
				</div>
			</div>
			<div class="col-md-1 column">
			</div>
		</div>
	</div>
</body>
</html>