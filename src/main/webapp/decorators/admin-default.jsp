<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Admin Home</title>
	<!-- Custom fonts for this template-->
	<link href="<c:url value='/style/admin/vendor/fontawesome-free/css/all.min.css' />" rel="stylesheet"
		type="text/css">
	<link
		href="<c:url value='https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i' />"
		rel="stylesheet">

	<!-- Custom styles for this template-->
	<link href="<c:url value='/style/admin/css/sb-admin-2.min.css' />" rel="stylesheet">

	<script src="<c:url value='/style/admin/vendor/jquery/jquery.min.js' />"></script>
	<!-- pagin -->
	<script src="<c:url value='/style/pagination/jquery.twbsPagination.js' />" type="text/javascript"></script>


</head>

<body>

	<!-- Page Wrapper -->
	<div id="wrapper">
		<jsp:include page="/common/admin/slicebar.jsp" />

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<jsp:include page="/common/admin/header.jsp" />
				<decorator:body />
			</div>
			<jsp:include page="/common/admin/footer.jsp" />
		</div>
	</div>



	<!-- Bootstrap core JavaScript-->

	<script src="<c:url value='/style/admin/vendor/bootstrap/js/bootstrap.bundle.min.js' />"></script>

	<!-- Core plugin JavaScript-->
	<script src="<c:url value='/style/admin/vendor/jquery-easing/jquery.easing.min.js' />"></script>

	<!-- Custom scripts for all pages-->
	<script src="<c:url value='/style/admin/js/sb-admin-2.min.js' />"></script>

	<!-- Page level plugins -->
	<script src="<c:url value='/style/admin/vendor/chart.js/Chart.min.js' />"></script>

	<!-- Page level custom scripts -->
	<script src="<c:url value='/style/admin/js/demo/chart-area-demo.js' />"></script>
	<script src="<c:url value='/style/admin/js/demo/chart-pie-demo.js' />"></script>

</body>

</html>