<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var='urlAPI' value='/api-admin-article' />
<c:url var='listURL' value='/admin-article?type=list&sortBy=Latest&page=1&itemsOnPage=2' />
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
		<label for="sortBy"> Sort By:</label>
			<div class="row">
				<div class="col-6 col-md-4">
					<select class="form-control col-md-6" id="sortBy" name="sortBy">
						<option value="none" selected disabled hidden>${model.sortBy}</option>
						<option value="Latest">Latest</option>
						<option value="Oldest">Oldest</option>
					</select>

				</div>
				<div class="col-6 col-md-4"></div>
				<div class="col-6 col-md-4">
					<div class="float-right create-and-delete">
						
						<a class="btn btn-success" href="<c:url value='/admin-article?type=edit'/> " role="button" id="create-new-btn" title="Create">
							<i class="fas fa-plus"></i>
						</a>
						
						<button class="btn btn-danger" type="button" id="delete-btn" title="Delete">
							<i class="fas fa-trash-alt"></i>
						</button>
					</div>
				</div>
			</div>


			<br>
			<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<th><input type="checkbox" id="checkAll" name="checkAll"></th>
						<th>Title</th>
						<th>Short Description</th>
						<th>Author</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${model.listItems}" var="item">
						<tr>
							<th><input type="checkbox" id="check-${item.id}" name="check-model" value="${item.id}"></th>
							<td>${item.title}</td>
							<td>${item.shortDescription}</td>
							<td>${item.createdBy}</td>
							<td>
								<a class="btn btn btn-warning" href="<c:url value='/admin-article?type=edit&id=${item.id}' />" role="button">
									<!-- <input type="hidden" id="id" name="id" value="${item.id}" /> -->
									<i class="fas fa-pen"></i>
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<ul class="pagination" id="pagination"></ul>
			<input type="hidden" id="type" name="type" value="list">
			<input type="hidden" id="page" name="page" value="">
			<input type="hidden" id="itemsOnPage" name="itemsOnPage" value="">
			<!-- <button type="submit" class="btn btn-primary" id="sort">Sort</button> -->
		</form>
	</div>
	<script type="text/javascript">
		var currentPage = ${model.page};
		var totalPages = ${model.totalPages};
		var limit = 2;
		var sortBy = "${model.sortBy}";
		$(function () {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages: totalPages,
				visiblePages: 5,
				startPage: currentPage,
				onPageClick: function (event, page) {
					if (currentPage != page) {
						$("#page").val(page);
						$("#itemsOnPage").val(limit);
						$("#sortBy").val(sortBy);
						$("#formSubmit").submit();
					}
				}
			})
		});

		$(document).ready(function () {
			$("#sortBy").change(function () {
				$("#page").val(1);
				$("#itemsOnPage").val(limit);
				$("#formSubmit").submit();
			});
		});

		$('#checkAll').click(function(){
			if ($('#checkAll').is(':checked')){
				$("tbody input[name = 'check-model']").prop('checked',true);
			} else {
				$("tbody input[name = 'check-model']").prop('checked',false);
			}
		});

		$('#delete-btn').click(function(){
			var data = {};
			var listCheckbox = [];
			$.each($("tbody input[name = 'check-model']:checked"), function(index, item) {
				listCheckbox.push(item.value);
			});
			data["listId"] = listCheckbox;
			console.log(data);
			doDelete(data);
		});

		function doDelete(data) {
			$.ajax({
				url: '${urlAPI}',
				type: 'DELETE',
				contentType: 'application/json',
				data: JSON.stringify(data),
				dataType: 'json',
				success: function(e){
					window.location.href = "${listURL}";
				},
				error: function(e) {
					console.log(e);
				}
			})
		}

	</script>
</body>

</html>