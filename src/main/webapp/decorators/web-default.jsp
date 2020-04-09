<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Daily News</title>
<!-- Bootstrap core CSS -->
<link href="<c:url value='/style/web/vendor/bootstrap/css/bootstrap.min.css' />" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<c:url value='/style/web/css/blog-home.css' />" rel="stylesheet">

</head>
<body>

	<jsp:include page="/common/web/header.jsp" />

	<decorator:body />

	<jsp:include page="/common/web/footer.jsp" />

	<!-- Bootstrap core JavaScript -->
	<script src="<c:url value='/style/web/vendor/jquery/jquery.min.js' />"></script>
	<script src="<c:url value='/style/web/vendor/bootstrap/js/bootstrap.bundle.min.js' />"></script>
	
	<!-- 
	 <script src="/style/web/vendor/jquery/jquery.min.js"></script>
	<script src="/style/web/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	 -->
</body>
</html>