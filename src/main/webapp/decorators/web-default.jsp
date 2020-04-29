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
	<!-- Bootstrap core JavaScript -->
	<script src="<c:url value='/style/web/vendor/jquery/jquery.min.js' />"></script>
	<script src="<c:url value='/style/web/vendor/bootstrap/js/bootstrap.bundle.min.js' />"></script>
	<!-- ckeditor -->
	<script src="<c:url value='/ckeditor/ckeditor.js' />"></script>

</head>

<body>

	<jsp:include page="/common/web/header.jsp" />
	
	<div style = "min-height: 77vh">
		<decorator:body />
	</div>

	<jsp:include page="/common/web/footer.jsp" />


</body>

</html>