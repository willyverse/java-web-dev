<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 정보</title>
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
<jsp:include page="/Header.jsp" />
<h1>프로젝트 정보</h1>
<form action="update.do" method="post">
<ul>
<li>
<label for="no">번호</label>
<input id="no" name="no" value="${project.no }" readonly />
</li>
<li>
<label for="title">제목</label>
<input id="title" name="title" value="${project.title }" />
</li>
<li>
<label for="content">내용</label>
<textarea id="content" name="content">${project.content }</textarea>
</li>
<li>
<label for="startDate">시작일</label>
<input id="startDate" name="startDate" type="text" placeholder="예) 2022-05-24" value="${project.startDate }" />
</li>
<li>
<label for="endDate">종료일</label>
<input id="endDate" name="endDate" type="text" placeholder="예) 2022-05-24" value="${project.endDate }" />
</li>
<li>
<label for="state">상태</label>
<select id="state" name="state">
<option value="0" ${project.state==0 ? "selected" : ""}>준비</option>
<option value="1" ${project.state==1 ? "selected" : ""}>진행</option>
<option value="2" ${project.state==2 ? "selected" : ""}>완료</option>
<option value="3" ${project.state==3 ? "selected" : ""}>취소</option>
</select>
</li>
<li>
<label for="tags">태그</label>
<input id="tags" name="tags" type="text" placeholder="예) 태그1 태그2 태그3" size="40" />
</li>
</ul>
<input type="submit" value="저장">
<input type="button" value="삭제" onclick='location.href="delete.do?no=${project.no}";'>
<input type="reset" value="취소" onclick='location.href="list.do"'>
</form>
<jsp:include page="/Tail.jsp" />
</body>
</html>