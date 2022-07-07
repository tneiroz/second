<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp" %>
<div class="container">
	<h1>HOME</h1>
	<p><a href="${contextPath}/security/all">모든 방문자 허용 </a></p>
	<p><a href="${contextPath}/security/member">회원 등급이상 </a></p>
	<p><a href="${contextPath}/security/admin">관리자만</a></p>
</div>
<%@ include file="layout/footer.jsp" %>