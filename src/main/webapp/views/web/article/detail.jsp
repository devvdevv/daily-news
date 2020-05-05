<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var='urlCommentAPI' value='/api-comment' />
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>

<body>
    <div class="container">

        <div class="row">

            <!-- Post Content Column -->
            <div class="col-lg-8" style="margin: auto;">

                <!-- Title -->
                <h1 class="mt-4">${model.title}</h1>

                <!-- Author -->
                <p class="lead">
                    by
                    <a href="#">${model.createdBy}</a>
                </p>

                <hr>

                <!-- Date/Time -->
                <c:set value="${model.createdDate}" var="date" />
                <p>Posted on
                    <fmt:formatDate value="${date}" pattern="dd/MM/yyyy" />
                </p>

                <hr>

                <!-- Preview Image -->
                <!-- <img class="img-fluid rounded" src="http://placehold.it/900x300" alt="">

                <hr> -->

                <!-- Post Content -->
                <div class="content">
                    ${model.content}
                </div>

                <hr>

                <!-- Comments Form -->
                <c:if test="${not empty USERMODEL}">
	                <div class="card my-4">
	                    <div class="card-body">
	                        <form id="commentForm">
	                            <div class="form-group">
	                                <textarea class="form-control" id="comment-content" name="content" rows="3" placeholder="Leave a comment here"></textarea>
	                            </div>
	                            <button type="button" class="btn btn-primary" id="publish-comment">Submit</button>
	                        </form>
	                    </div>
	                </div>
                </c:if>
                
                <c:if test="${empty USERMODEL}">
                	<h4>Comments</h4>
                </c:if>

                <!-- Single Comment -->
                <div class="comment-section" id="comment-section">
                    <c:forEach var="comment" items="${model.listComment}">
                        <div class="media mb-4">
                            <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">
                            <div class="media-body">
                                <h5 class="mt-0">${comment.createdBy}</h5>
                                <div class="comment-content">
                                    ${comment.content}
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

        </div>
    </div>
    </div>

    <script>
        var articleId = ${model.id};
        $('#publish-comment').click(function() {
            var data = new FormData();
            var dataForm = $('#commentForm').serializeArray();
            $(dataForm).each(function(index, item){
                data[item.name] = item.value;
            });
            data['articleId'] = articleId; 
            console.log(data);
            publishComment(data);
            $('#comment-content').val('');
        });

        function publishComment(data) {
			$.ajax({
				url: '${urlCommentAPI}',
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(data),
				dataType: 'json',
				success: function (e) {
                    var commentObj = JSON.parse(JSON.stringify(e));
                    $('#comment-section').append(
                    '<div class="media mb-4">'+
                        '<img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">'+
                        '<div class="media-body">'+
                            '<h5 class="mt-0">'+commentObj.createdBy+'</h5>'+
                            '<div class="comment-content">'+commentObj.content+'</div>'+
                        '</div>'+
                    '</div>');
				},
				error: function (e) {
					console.log(e);
				}
			})
		}
    </script>
</body>

</html>