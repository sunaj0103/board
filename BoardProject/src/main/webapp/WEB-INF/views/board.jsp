<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	var ok = "${ok}";
	
	if(ok == "write"){
		alert("글이 등록되었습니다.");
	}
	if(ok == "edit"){
		alert("글이 수정되었습니다.");
	}
	if(ok == "del"){
		alert("글이 삭제되었습니다.");
	}	
	if(ok == "reWrite"){
		alert("답글이 등록되었습니다.");
	}
	if(ok == "excel"){
		alert("엑셀 파일이 생성되었습니다.");
	}
	
	$(function(){
		$("#searchBtn").click(function(){
			var keyword = $("#keyword").val();
			var searchOption = $("select[name=searchOption]").val();
			
			if(keyword == ""){
				alert("키워드를 입력해 주세요.");
				return false;
			}else{
				location.href="/web/board?searchOption="+searchOption+"&keyword="+keyword;
			}
		});
	});
		
</script>
</head>
<body>
	<div class=contentBoard>
		<div class="boardDiv">
			<h2 class="formTitle">${title}</h2>
			<div class="boardInput">
				<div class="col-sm-offset-6 col-sm-8">
					<select name="searchOption">
						<option value="subject" <c:out value="${searchOption eq 'subject'}"/>>제목</option>
						<option value="name" <c:out value="${searchOption eq 'name'}"/>>글쓴이</option>
						<option value="content" <c:out value="${searchOption eq 'content'}"/>>내용</option>
						<option value="all" <c:out value="${searchOption eq 'all'}"/>>제목+글쓴이+내용</option>
					</select>
					<input type="text" id="keyword" value="${keyword}"/>
					<input type="button" value="조회" id="searchBtn"  class="btn btn-success btn-md"/>
				</div>
				<br/>
				
				<c:set var="number" value="${(pageDto.onePageCount*pageDto.currentPage)-(pageDto.onePageCount-1)}"/>
				<ul id="boardList">
					<li class="boardList1 boardTitle">NO</li>
					<li class="boardList2 boardTitle">제목</li>
					<li class="boardList1 boardTitle">글쓴이</li>
					<li class="boardList1 boardTitle">작성일</li>
					<li class="boardList1 boardTitle">조회수</li>
					
					<c:forEach var="list" items="${list}">
						<li class="boardList1">
							${number}
							<c:set var="number" value="${number+1}"/>
						</li>
						<li class="boardList2">
							<c:forEach begin="1" end="${list.lvl}">
								&nbsp;&nbsp;
							</c:forEach>
							<c:if test="${list.lvl>0}">
								┖
							</c:if>	
							<c:if test="${list.subject != '삭제된 글입니다.' }">	
								<a href="/web/boardListView?num=${list.b_num}">${list.subject}</a>
							</c:if>
							<c:if test="${list.subject == '삭제된 글입니다.' }">	
								${list.subject}
							</c:if>
						</li>
						<li class="boardList1">${list.name}</li>
						<li class="boardList1">${list.write_date}</li>
						<li class="boardList1">${list.hit}</li>
					</c:forEach>					
				</ul>
				<div id="page">
					<ul class="pagination">
						<c:if test="${pageDto.currentPage>1}">
							<c:choose>
								<c:when test="${keyword != '' || keyword ne null}">
									<li><a href="/web/board?currentPage=${pageDto.currentPage-1}&searchOption=${searchOption}&keyword=${keyword}">&#60;</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="/web/board?currentPage=${pageDto.currentPage-1}">&#60;</a></li>
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:forEach var="p" begin="${pageDto.startPageNum }" end="${pageDto.startPageNum+pageDto.onePageCount-1}">
							<c:if test="${p<=pageDto.totalPageCount}">
								<c:if test="${pageDto.currentPage==p }">
									<c:choose>
										<c:when test="${keyword != '' || keyword ne null}">
											<li class="active"><a href="/web/board?currentPage=${p}&searchOption=${searchOption}&keyword=${keyword}">${p}</a></li>
										</c:when>
										<c:otherwise>
											<li class="active"><a href="/web/board?currentPage=${p}">${p}</a></li>
										</c:otherwise>
									</c:choose>
							    </c:if>
							    <c:if test="${pageDto.currentPage!=p }">
							    	<c:choose>
										<c:when test="${keyword != '' || keyword ne null}">
											<li><a href="/web/board?currentPage=${p}&searchOption=${searchOption}&keyword=${keyword}">${p}</a></li>
										</c:when>
										<c:otherwise>
											<li><a href="/web/board?currentPage=${p}">${p}</a></li>
										</c:otherwise>
									</c:choose>
							    </c:if>
						    </c:if>
						</c:forEach>
						<c:if test="${pageDto.currentPage!=pageDto.totalPageCount}">
							<c:choose>
								<c:when test="${keyword != '' || keyword ne null}">
									<li><a href="/web/board?currentPage=${p+1}&searchOption=${searchOption}&keyword=${keyword}">&#62;</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="/web/board?currentPage=${p+1}">&#62;</a></li>
								</c:otherwise>
							</c:choose>	
						</c:if>
					</ul>
				</div>
				<div class="col-sm-7">
					<a href="/web/allExcel" class="btn btn-info btn-md">전체 리스트 엑셀 다운</a>
					<a href="/web/allExcel" class="btn btn-info btn-md">현재 페이지 리스트 엑셀 다운</a>
			    </div>
			    <div class="col-sm-offset-4 col-sm-1">
					<a href="/web/boardWrite" class="btn btn-success btn-md">글쓰기</a>
			    </div>
			</div>
		</div>
	</div>
</body>
</html>