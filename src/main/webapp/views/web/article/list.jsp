<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- Page Content -->
	<div class="container">
		<c:if test="${not empty message}">
			<br>
			<div class="alert alert-success" role="alert">
			  ${message}
			</div>
		</c:if>
		<div class="row">

			<!-- Blog Entries Column -->
			<div class="col-md-8" style="margin: auto;">

				<h1 class="my-4">
					<b>My Stories</b>
				</h1>

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
							<a class="btn btn-warning btn-sm" href="<c:url value='/article?type=edit&id=${item.id}'/>" role="button" style="float: right;">Update</a>
						</div>
					</div>
				</c:forEach>

			</div>
		</div>

	</div>
</body>
</html>