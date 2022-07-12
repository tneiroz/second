<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
<sec:authentication property="principal.memberVO.userId" var="userId"/>
	<h2>회원 홈페이지</h2>
	<a href="${contextPath}/customLogout">로그아웃하러 가자</a>
	<a href="${contextPath}/anno/myPage/${userId}">마이페이지 가자</a>
		
</div>
<%@ include file="../layout/footer.jsp" %>


