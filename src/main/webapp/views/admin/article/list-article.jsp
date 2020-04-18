<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Article Management</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
	<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->

	<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"> -->
	<!-- <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
	<!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script> -->

	<!-- <c:url value='/style/pagination/jquery.twbsPagination.js' /> -->

</head>

<body>
	<div class="container">
		<form action='<c:url value="/admin-article" />' id="formSubmit" method="GET">
			<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<th>Title</th>
						<th>Short Description</th>
						<th>Author</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${model.listItems}" var="item">
						<tr>
							<td>${item.title}</td>
							<td>${item.shortDescription}</td>
							<td>Author</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<ul class="pagination" id="pagination"></ul>
			<input type="hidden" id="page" name="page" value="">
			<input type="hidden" id="itemsOnPage" name="itemsOnPage" value="">
		</form>
	</div>
	<script type="text/javascript">
		var currentPage = ${model.page};
		var totalPages = ${model.totalPages};
		var limit = 2;
		$(function () {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages: totalPages,
				visiblePages: 5,
				startPage: currentPage,
				onPageClick: function (event, page) {
					if(currentPage != page){
						$("#page").val(page);
						$("#itemsOnPage").val(limit);
						$("#formSubmit").submit();
					}
				}
			})
		});
	</script>
</body>

</html>