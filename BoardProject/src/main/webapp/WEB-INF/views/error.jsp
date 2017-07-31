<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<%@ page session="true" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/web/js_css/jquery-3.1.1.min.js"></script>
<script src="/web/js_css/bootstrap.min.js"></script>
<link href="/web/js_css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="/web/js_css/boardStyle.css" rel="stylesheet" type="text/css"/>
<title>게시판</title>
<script>
function prePage(){
	history.back();
}
</script>
</head>
<body>
	<div class="contentBoard">
		<div class="boardDiv">
			<h2 class="formTitle">게시판</h2>
			<div class="boardInput">
					<div class="form-group">
						<div class="col-sm-10">
							${msg}
						</div>
					</div>
					
					<div class="form-group"></div>
					<div class="form-group"> 
			    		<div class="col-sm-offset-4 col-sm-10">
			    			<a class="btn btn-warning btn-md" href="javascript:prePage();">이전 페이지</a>
							<a class="btn btn-success btn-md" href="/web/board">게시판</a>
						</div>
					</div>
			</div>
		</div>
	</div>
</body>
</html>