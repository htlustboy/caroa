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
<title>修改密码</title>
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
							<h2>修改密码 <small>-${username }</small></h2>
						</div>
					</div>
				</div>
				<div class="form row-center">  
		            <form class="form-horizontal" id="update_form" action="${base }/login/doUpdatePwd"  method="post">
		            	<input type="hidden" name="username" value="${username }"/>  
		            	<div class="row">
                            <div class="form-group">
                                <label class="control-label control-label-left col-md-3" style="font-size: 16px;">请输入激活码:</label>
                                <div class="col-md-5">
                                   <input class="form-control required" type="text" placeholder="" name="code" value=""/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="control-label control-label-left col-md-3" style="font-size: 16px;">请输入密码:</label>
                                <div class="col-md-5">
                                   <input class="form-control required" type="password" placeholder="密码" name="password" value=""/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="control-label control-label-left col-md-3" style="font-size: 16px;">请确认密码:</label>
                                <div class="col-md-5">
                                   <input class="form-control required" type="password" placeholder="确认密码" name="rpassword" value=""/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                        <div class="col-md-12 col-sm-offset-6 ">
                            <div class="btn-toolbar margin-top-10">
                                <div class="btn-group">
                                    <input type="submit" class="btn btn-info" value="确认">
                                </div>
                                <div class="btn-group">
                                    <a href="${base }/login/index" class="btn btn-danger pull-right" style="margin-right: 20px;" >取消</a>  
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
<script type="text/javascript">
$(function(){
	$("#update_form").bootstrapValidator({
		fields:{
			code:{
				validators:{
					notEmpty:{
						message:'激活码不能为空'
					},
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
              }
		}
		
	});
});
</script>
</html>