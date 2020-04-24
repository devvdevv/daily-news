<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var='urlAPI' value='/api-admin-article' />
<c:url var='listURL' value='/admin-article?type=list&sortBy=Latest&page=1&itemsOnPage=2' />
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title></title>
</head>

<body>
	<div class="container">
		<form id="formSubmit">
			<div class="form-group row">
				<label for="category" class="col-sm-2 col-form-label">Category</label>
				<div class="form-group col-sm-6">
					<select class="form-control" id="categoryId" name="categoryId">
						<option value="none" selected disabled hidden> Select category</option>
						<c:forEach var="item" items="${categories}">
							<c:if test="${item.id == model.categoryId}">
								<option value="${item.id}" selected>${item.name}</option>
							</c:if>
							<c:if test="${item.id != model.categoryId}">
								<option value="${item.id}">${item.name}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<br>
			<div class="form-group row">
				<label for="title" class="col-sm-2 col-form-label">Title</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="title" name="title" value="${model.title}">
				</div>
			</div>
			<br>
			<div class="form-group row">
				<label for="short-description" class="col-sm-2 col-form-label">Short Description</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="shortDescription" name="shortDescription" value="${model.shortDescription}">
				</div>
			</div>
			<br>
			<div class="form-group row">
				<label for="thumbnail" class="col-sm-2 col-form-label">Thumbnail</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="thumbnail" name="thumbnail" value="${model.thumbnail}">
				</div>
			</div>
			<br>
			<div class="form-group row">
				<label for="" class="col-sm-2 col-form-label">Content</label>
				<div class="col-sm-10">
					<textarea class="form-control" id="content-area" name="content" rows="8">${model.content}</textarea>
				</div>
			</div>
			<div class="form-group row">
				<c:if test="${model.id != null }">
					<button type="button" class="btn btn-primary" id="create-update">Update</button>
				</c:if>
				<c:if test="${model.id == null }">
					<button type="button" class="btn btn-primary" id="create-update">Publish</button>
				</c:if>
			</div>
			<input type="hidden" class="form-control" id="id" name="id" value="${model.id}">
		</form>
	</div>
	<script>
		var editor;
		$('document').ready(function() {
			editor = CKEDITOR.replace('content-area');
		});

		$('#create-update').click(function(){
			var data = {};
			var dataForm = $('#formSubmit').serializeArray();
			$(dataForm).each(function(index, item) {
				data[""+item.name+""] = item.value;
			});
			data["content"] = editor.getData();
			var id = $("#id").val();
			if (id == ""){
				createNew(data);				
			} else {
				data["id"] = id;
				update(data);
			}
		});

		function createNew(data) {
			$.ajax({
				url: '${urlAPI}',
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(data),
				dataType: 'json',
				success: function(e){
					console.log(e);
					window.location.href = "${listURL}&message=create_success";
				},
				error: function(e) {
					console.log(e);
				}
			})
		}

		function update(data) {
			$.ajax({
				url: '${urlAPI}',
				type: 'PUT',
				contentType: 'application/json',
				data: JSON.stringify(data),
				dataType: 'json',
				success: function(e){
					console.log(e);
					window.location.href = "${listURL}&message=update_success";
				},
				error: function(e) {
					console.log(e);
				}
			})
		}
	</script>
</body>

</html>