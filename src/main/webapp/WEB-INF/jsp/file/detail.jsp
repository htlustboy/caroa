<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	body{
 		background-image: url('${basePath}/resource/img/bg3.jpg'); 
	}
	.container{
		background-repeat: no-repeat;
 		background-image: url('${basePath}/resource/img/timg.jpg'); 
	}	
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看</title>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-1 column">
			</div>
			<div class="col-md-10 column">
				<div class="page-header">
					<h2 style="width: 80%">
						<small>标题： </small>${file.fileName }
					</h2>
					<span>创建时间：<fmt:formatDate  value="${file.createDate }" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate ></span>
					<span style="margin-left: 650px;"><a href="${base }/index">点击返回</a></span>
				</div>
				<h2>
				</h2>
				<p id="content" style=" word-wrap:break-word; word-break:normal; ">
					
				</p>
			</div>
			<div class="col-md-1 column">
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url:"${base}/file/loadFile.json",
			data:{"filePath":'${file.filePath}'},
			type:"POST",
			success:function(data){
				$("#content").html(data.content);
			}
		});
	});
</script>
</html>