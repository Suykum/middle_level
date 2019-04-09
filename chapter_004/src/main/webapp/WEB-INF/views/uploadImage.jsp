<%--
  Created by IntelliJ IDEA.
  User: Toshiba
  Date: 10-Mar-19
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/main.css">
</head>
<body>
<h2>Upload Car Images</h2>
<br>
<form method="post" action="${pageContext.servletContext.contextPath}/imageUpload" enctype="multipart/form-data">
    <input type="file" name="image"  accept="image/gif, image/jpeg, image/png"><br><br>
    <input type="submit" value="Upload">
</form>
</body>
</html>
