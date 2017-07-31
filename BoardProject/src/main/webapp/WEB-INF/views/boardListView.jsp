<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
function delChk(){
	if(confirm("글을 삭제하시겠습니까?")){
		location.href="/web/boardDel?num=${dto.b_num}&lvl=${dto.lvl}&ref=${dto.ref}";
	}
}
	
function editChk(){
	location.href="/web/boardEdit?num=${dto.b_num}";
}
	
</script>
</head>
<body>
	<div class=contentBoard>
		<div class="boardDiv">
			<h2 class="formTitle">게시판</h2>
			<div class="boardInput viewHeight">
				<div class="col-lg-12 viewborderTop">${dto.subject}</div>
				<div class="col-lg-4 viewborder">글쓴이: ${dto.name}</div>
				<div class="col-lg-4 viewborder">작성일: ${fn:substring(dto.write_date,0,10)}</div>
				<div class="col-lg-4 viewborder">조회수: ${dto.hit}</div>
				<div class="col-lg-12 viewborderEmpty"></div>
				<div class="col-lg-12 viewborderEmpty"></div>
				<div class="col-lg-12 viewborderContent">
					<c:set var = "content1" value = "${fn:replace(dto.content, '&lt;', '<')}."/>
      				<c:set var = "content2" value = "${fn:replace(content1, '&gt;', '>')}" />
					<c:set var = "content" value="${fn:replace(content2, '&quot;', '\"')}"/>
					${content}
				</div>
				<div class="col-lg-12 viewborderEmpty"></div>
				<div class="col-lg-12 viewborderEmpty"></div>
				<div class="col-lg-2">
					<a href="/web/board" class="btn btn-info btn-md">목록</a>
				</div>
				<div class="col-lg-offset-6 col-lg-4">
				    <a href="javascript:editChk()" class="btn btn-success btn-md">수정</a>
				    <a href="javascript:delChk()" class="btn btn-warning btn-md">삭제</a>
				    <a href="/web/boardReplyWrite?b_num=${dto.b_num}&lvl=${dto.lvl}" class="btn btn-success btn-md">답글</a>
			    </div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</body>
</html>