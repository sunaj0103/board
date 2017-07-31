<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/web/js_css/jquery-3.1.1.min.js"></script>
<script src="/web/resources/ckeditor.js"></script>
<script src="/web/js_css/bootstrap.min.js"></script>
<link href="/web/js_css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="/web/resources/samples/css/samples.css" rel="stylesheet"/>
<link href="/web/js_css/boardStyle.css" rel="stylesheet" type="text/css"/>
<title>게시판</title>
<script>
$(function(){
	$("#boardEditBtn").click(function(){
		if($("#subject").val() == ""){
			alert("제목을 입력해 주세요.");
			return false;
		}
		
		var content = CKEDITOR.instances['content'].getData();
		
		if(content == ""){
			alert("내용을 입력해 주세요.");
			return false;
		}
				
		$("#boardEditFrm").submit();
    });
});
</script>
</head>
<body>
	<div class="contentBoard">
		<div class="boardDiv">
			<h2 class="formTitle">게시판</h2>
			<div class="boardInput">
				<form class="form-horizontal" id="boardEditFrm" name="boardEditFrm" method="post" action="/web/boardEditOk">
					<input type="hidden" name="b_num" value="${dto.b_num}"/>
					<div class="form-group">
						<label for="name" class="control-label col-sm-2">작성자 </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="name" id="name" value="${dto.name}" readonly/>
						</div>
					</div>
					<div class="form-group">
						<label for="subject" class="control-label col-sm-2">제목 </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="subject" id="subject" value="${dto.subject}"/>
						</div>
					</div>
					<div class="form-group">
						<label for="content" class="control-label col-sm-2">내용 </label>
						<div class="col-sm-10">
							<textarea class="ckeditor" cols="1" name="content" id="content" rows="15">${dto.content}</textarea>
						</div>
					</div>
					<div class="form-group"></div>
					<div class="form-group"> 
			    		<div class="col-sm-offset-2 col-sm-10">
							<input type="reset" value="다시쓰기" class="btn btn-warning btn-md"/>
							<input type="button" id="boardEditBtn" value="수정" class="btn btn-success btn-md"/>
						</div>
					</div>
					<div class="form-group"> 
						<div class="col-sm-offset-10 col-sm-1">
			    			<a href="/web/board" class="btn btn-info btn-md">목록</a>
			    		</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>