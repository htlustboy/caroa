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
<title>找回密码</title>
</head>
<body >
	<div class="container"  style="height: 600px;">
		<div class="row">
			<div class="col-md-1 column">
				
			</div>
			<div class="col-md-10 column">
				<div class="row clearfix">
					<div class="col-md-12 column">
						<div class="page-header">
							<h2>找回密码 <small>caroa</small></h2>
						</div>
					</div>
				</div>
				<div class="form row-center">  
		            <form class="form-horizontal" id="register_form" action="${base }/login/doregisiter"  method="post">  
		            	<div class="row">
                            <div class="form-group">
                                <label class="control-label control-label-left col-md-3" style="font-size: 16px;">请输入用户名:</label>
                                <div class="col-md-5">
                                   <input class="form-control required" type="text" placeholder="用户名" name="username" value="${username }"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="control-label control-label-left col-md-3" style="font-size: 16px;">请输入注册邮箱:</label>
                                <div class="col-md-5">
                                   <input class="form-control required" type="text" placeholder="邮箱" name="email" value="${email }"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="control-label control-label-left col-md-3" style="font-size: 16px;">请输入验证码:</label>
                                <div class="col-md-2">
                                   <input class="form-control required" type="text" placeholder="" name="email" />
                                </div>
                                <div class="col-md-2">
 									<img id="codevalidate" src="${base}/aaa" width="90" height="20" style="margin-left: 10px">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                        <div class="col-md-12 col-md-offset-6">
                            <div class="btn-toolbar margin-top-10">
                                <div class="btn-group">
                                    <button type="submit" class="btn btn-primary">确定</button>
                                </div>
                                <div class="btn-group">
                                    <a href="${base }/login/index" type="button" class="btn btn-primary">取消</a>
                                </div>
                            </div>
                        </div>
                    </div>
		            </form>  
		        </div>  
			</div>
			<div class="col-md-1 column">
			</div>
		</div>
	</div>
</body>

</html>