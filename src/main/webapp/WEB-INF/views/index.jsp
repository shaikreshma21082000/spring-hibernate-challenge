<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Artist MC1</title>
</head>

<body onload="">
	<div class="container">
		<div class="jumbotron" style="margin: 45px 0px">
			<h2 class="text-center">ARTIST APP</h2>
		</div>

		<form method="POST" action="${pageContext.request.contextPath}/addArtist" modelAttribute="artist">

			<div class="form-group">
				<label for="artistId">Artist Id</label> <input type="text"
					class="form-control" id="artistId" aria-describedby="artistId"
					placeholder="Enter artist Id" name="artistId"  value="${artist.artistId}" >
			</div>

			<div class="form-group">
				<label for="exampleInputTitle">Artist Name</label>
				<input type="text"
					class="form-control" id="artistName" aria-describedby="artistName"
					placeholder="Enter artist Name" name="artistName" value="${artist.artistName}">
			</div>

			<div class="form-group">
				<label for="exampleInputNoteText">url</label>
				<input type="text" class="form-control" id="url"
					placeholder="Enter url" name="url" value="${artist.url}">
			</div>

			<div class="form-group">
				<label for="exampleInputNoteCategory">ImageUrl</label>
				<input type="text" class="form-control" id="imageUrl"
					placeholder="Enter imageUrl" name="imageUrl" value="${artist.imageUrl}">
			</div>

			<div class="form-group">
            				<label for="exampleInputNoteCategory">imageUrl</label>
            				<input type="text" class="form-control" id="imageSpecs"
            					placeholder="Enter image Specs" name="imageSpecs" value="${artist.imageSpecs}">
            </div>

			<button type="submit" class="btn btn-primary">Submit</button>
		</form>

		<div style="margin-top: 1em; text-align: center">
			<h2>All Artist Information</h2>
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">Artist ID</th>
						<th scope="col">Artist Name</th>
						<th scope="col">Url</th>
						<th scope="col">Image Url</th>
						<th scope="col">Image Specs</th>
						<th scope="col">Update</th>
						<th scope="col">Delete</th>
					</tr>
				</thead>
				<tbody id='table-body'>
					<c:forEach items="${artistList}" var="artistobj">
						<tr>
							<td>${artistobj.artistId}</td>
							<td>${artistobj.artistName}</td>
							<td>${artistobj.url}</td>
							<td>${artistobj.imageUrl}</td>
							<td>${artistobj.imageSpecs}</td>
							<td><a href="${pageContext.request.contextPath}/update/${artistobj.artistId}">Update</a></td>
							<td><a href="${pageContext.request.contextPath}/delete/${artistobj.artistId}">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>
