<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Insert title here</title>
  <link href="<c:url value='/style/admin/vendor/fontawesome-free/css/all.min.css' />" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="<c:url value='/style/admin/css/sb-admin-2.min.css' />" rel="stylesheet">
  
</head>

<body>
  <div id="content-wrapper">
    <div class="container-fluid">
  
      <!-- 404 Error Text -->
      <div class="text-center">
        <br>
        <br>
        <div class="error mx-auto" data-text="404">404</div>
        <p class="lead text-gray-800 mb-5">Page Not Found</p>
        <p class="text-gray-500 mb-0">It looks like you found a glitch in the matrix...</p>
        <a href="<c:url value='/home' />">&larr; Back to Dashboard</a>
      </div>
  
    </div>
  </div>
</body>

</html>