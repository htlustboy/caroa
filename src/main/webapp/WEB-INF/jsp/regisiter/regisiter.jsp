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
<title>用户注册</title>
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
							<h2>注册 <small>caroa</small></h2>
						</div>
					</div>
				</div>
				<div class="form row">  
		            <form class="form-horizontal col-sm-offset-3 col-md-offset-3" id="register_form" action="${base }/login/doregisiter"  method="post">  
		            <input type="hidden" name="token" value="${token }" readonly="readonly">
		                <div class="col-sm-12 col-md-6">  
		                    <div class="form-group">  
		                        <i class="fa fa-user fa-lg"></i>  
		                        <input class="form-control required" type="text" placeholder="用户名" name="username" autofocus="autofocus"/>
		                    </div>
		                    <div class="form-group">  
		                            <i class="fa fa-envelope fa-lg"></i>  
		                            <input class="form-control required" type="text" placeholder="昵称" name="displayName"/>  
		                    </div>  
		                    <div class="form-group">  
		                            <i class="fa fa-lock fa-lg"></i>  
		                            <input class="form-control required" type="password" placeholder="用户密码" name="password"/>  
		                    </div>  
		                    <div class="form-group">  
		                            <i class="fa fa-check fa-lg"></i>  
		                            <input class="form-control required" type="password" placeholder="确认密码" name="rpassword"/>  
		                    </div>  
		                    <div class="form-group">  
		                            <i class="fa fa-envelope fa-lg"></i>  
		                            <input class="form-control eamil" type="text" placeholder="邮箱" name="email"/>  
		                    </div>
		                    <div class="form-group">  
		                            <i class="fa fa-envelope fa-lg"></i>  
		                            <input class="form-control phoneNum" type="text" placeholder="手机号" name="phoneNum"/>  
		                    </div>
		                     <div class="form-group">  
		                            <i class="fa fa-envelope fa-lg"></i>  
		                            <input class="form-control address" type="text" placeholder="地址" name="address"/>  
		                    </div>
		                    <div class="form-group">  
		                            <i class="fa fa-envelope fa-lg"></i>  
		                            <textarea class="form-control memo" type="text" placeholder="备注" name="memo"></textarea>  
		                    </div>  
		                    <div class="form-group">  
		                        <input type="submit" class="btn btn-info pull-right"  value="注册"/>  
		                        <a href="${base }/index" class="btn btn-success pull-right" style="margin-right: 20px;" >返回</a>  
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

<script type="text/javascript">
$(function(){
	$("#register_form").bootstrapValidator({
		fields:{
			username:{
				message : '用户名输入不合法',
				validators:{
					notEmpty:{
						message:'用户名不能为空'
					},
					stringLength: {
                        min: 6,
                        max: 30,
                        message: '用户名长度必须在6-30位'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '用户名必须是字母数字或下划线'
                    }
				}
			},
			displayName:{
				validators:{
					 notEmpty: {
	                        message: '用户昵称不能为空'
	                  }
				}
			},
		    password: {
                validators: {
                    notEmpty: {
                        message: '用户密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 18,
                        message: '用户名密码长度必须在6-18位'
                    },
                }
              },
             rpassword: {
                validators: {
                    notEmpty: {
                        message: '两次密码输入不一致'
                    },
                    identical: {
                        field: 'password',
                        message: '两次密码输入不一致'
                    }
                }
              },
             email: {
                  validators: {
                      notEmpty: {
                          message: '邮箱不能为空'
                      },
                      emailAddress: {
                          message: '邮箱输入格式错误'
                      }
                  }
              }
		}
		
	});
});
</script>
</html>