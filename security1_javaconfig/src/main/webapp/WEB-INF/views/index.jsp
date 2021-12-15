<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>메인 페이지</h1>
<%-- isAuthenticated() : 인증된 사용자의 경우 true 값을 가짐(인증해서 권한을 부여받은 것 --%>

<sec:authorize access="!isAuthenticated()">
	<p>
		<a href="/login">로그인</a>
	</p>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<p>
		<a href="/user-page">유저페이지</a>
	</p>
</sec:authorize>

<!-- 권한을 부여한 것 -->
<sec:authorize access="isAuthenticated()">
	<!-- 인증된 사용자 정보가 들어있는 객체가 principal(인증된 사용자 정보) -->
	<sec:authentication property="principal" var="info"/>
	<!-- 유저 아이디가 admin이라면 보여주고 아니면 보여주지 말라는 의미 -->
	<c:if test="${info.username=='admin'}"> 
		<p>
			<a href="/admin-page">관리자페이지</a>
		</p>
	</c:if>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<div>
		<form action="/logout" method="post">
			<button>로그아웃</button>
		 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
</sec:authorize>
</body>
</html>                                                                       