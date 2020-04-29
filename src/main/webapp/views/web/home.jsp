<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Home title</title>
</head>

<body>
	<!-- Page Content -->
	<div class="container">

		<div class="row">

			<!-- Blog Entries Column -->
			<div class="col-md-8">

				<!-- <h1 class="my-4">
					Page Heading <small>Secondary Text</small>
				</h1> -->
				<c:forEach var="item" items="${model.listItems}">
					<br>
					<!-- Blog Post -->
					<div class="card mb-4">
						<!-- <a href="<c:url value='/article?type=detail&id=${item.id}'/> ">
							<img class="card-img-top" src="http://placehold.it/750x300" alt="Card image cap">
						</a> -->
						<div class="card-body">
							<a href="<c:url value='/article?type=detail&id=${item.id}'/> " style="text-decoration:none; color: black;">
								<h4 class="card-title">${item.title}</h4>
							</a>
							<c:forEach var="cate" items="${categories}">
								<c:if test="${item.categoryId == cate.id}">
									<span class="badge badge-pill badge-primary">${cate.name}</span>
								</c:if>
							</c:forEach>
							<p class="card-text">${item.shortDescription}</p>
						</div>
						<c:set value="${item.createdDate}" var="date" />
						<div class="card-footer text-muted">
							By <a href="#">${item.createdBy}</a> <fmt:formatDate value="${date}" pattern="dd/MM/yyyy"/>
						</div>
					</div>
				</c:forEach>

			</div>

			<!-- Sidebar Widgets Column -->
			<div class="col-md-4">

				<!-- Categories Widget -->
				<div class="card my-4">
					<h5 class="card-header">Categories</h5>
					<div class="card-body">
						<h5>
							<c:forEach var="item" items="${categories}">
								<c:if test="${model.categoryId == item.id}">
									<a href='<c:url value="/article?type=list&categoryId=${item.id}" />' class="badge badge-pill badge-primary">${item.name}</a>
								</c:if>
								<c:if test="${model.categoryId != item.id}">
									<a href='<c:url value="/article?type=list&categoryId=${item.id}" />' class="badge badge-pill badge-secondary">${item.name}</a>
								</c:if>
							</c:forEach>
						</h5>

					</div>
				</div>

			</div>

		</div>

	</div>
</body>

</html>