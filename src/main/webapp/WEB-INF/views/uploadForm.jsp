<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>
<body>
<h2>파일 업로드</h2>
<form action="uploadFormAction" method = "post" enctype="multipart/form-data">
	<input type = "file" name = "uploadFile" multiple = "multiple">
	<button>Submit</button>

</form>
</body>
</html>