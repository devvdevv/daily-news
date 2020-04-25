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
						<a href="<c:url value='/article?action=detail&id=${item.id}'/> ">
							<img class="card-img-top" src="http://placehold.it/750x300" alt="Card image cap">
						</a>
						<div class="card-body">
							<a href="<c:url value='/article?action=detail&id=${item.id}'/> " style="text-decoration:none; color: black;">
								<h4 class="card-title">${item.title}</h4>
							</a>
							<p class="card-text">${item.shortDescription}</p>
							<!-- <a href="#" class="btn btn-primary">Read More &rarr;</a> -->
						</div>
						<div class="card-footer text-muted">
							By <a href="#">${item.createdBy}</a> ${item.createdDate} 
						</div>
					</div>
				</c:forEach>


				<!-- Pagination -->
				<ul class="pagination justify-content-center mb-4">
					<li class="page-item"><a class="page-link" href="#">&larr;
							Older</a></li>
					<li class="page-item disabled"><a class="page-link" href="#">Newer
							&rarr;</a></li>
				</ul>

			</div>

			<!-- Sidebar Widgets Column -->
			<div class="col-md-4">

				<!-- Categories Widget -->
				<div class="card my-4">
					<h5 class="card-header">Categories</h5>
					<div class="card-body">
						<h5>
							<c:forEach var="item" items="${categories}">
								<a href="#" class="badge badge-pill badge-secondary">${item.name}</a>
							</c:forEach>
						</h5>

						<!-- <div class="row">
							<div class="col-lg-6">
								<ul class="list-unstyled mb-0">
									<li><a href="#">Web Design</a></li>
									<li><a href="#">HTML</a></li>
									<li><a href="#">Freebies</a></li>
								</ul>
							</div>
							<div class="col-lg-6">
								<ul class="list-unstyled mb-0">
									<li><a href="#">JavaScript</a></li>
									<li><a href="#">CSS</a></li>
									<li><a href="#">Tutorials</a></li>
								</ul>
							</div>
						</div> -->
					</div>
				</div>

				<!-- Side Widget -->
				<div class="card my-4">
					<h5 class="card-header">Side Widget</h5>
					<div class="card-body">You can put anything you want inside
						of these side widgets. They are easy to use, and feature the new
						Bootstrap 4 card containers!</div>
				</div>

			</div>

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->
</body>

</html>