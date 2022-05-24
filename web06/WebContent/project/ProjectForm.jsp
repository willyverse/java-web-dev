<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 등록</title>
<style>
ul { padding : 0; }
li { list-style : none; }
label {
	float : left;
	text-align: right;
	width: 60px;
}
</style>
</head>
<body>
<jsp:include page="/Header.jsp"></jsp:include>
<h1>프로젝트 등록</h1>
<form action="add.do" method="post">
<ul>
<li>
<label for="title">제목</label>
<input id="title" type="text" name="title" />
</li>
<li>
<label for="content">내용</label>
<textarea id="content" name="content" rows="4" cols="40"></textarea>
</li>
<li>
<label for="startDate">시작일</label>
<input id="startDate" name="startDate" type="text" placeholder="예) 2022-05-24" />
</li>
<li>
<label for="endDate">종료일</label>
<input id="endDate" name="endDate" type="text" placeholder="예) 2022-05-24" />
</li>
<li>
<label for="tags">태그</label>
<input id="tags" name="tags" type="text" placeholder="예) 태그1 태그2 태그3" size="40" />
</li>
</ul>
<input type="submit" value="추가">
<input type="reset" value="취소">
</form>
<jsp:include page="/Tail.jsp"></jsp:include>
</body>
</html>