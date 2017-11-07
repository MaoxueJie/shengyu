<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
    String path = request.getContextPath();  
    String basePath = request.getScheme() + "://"  
            + request.getServerName() + ":" + request.getServerPort()  
            + path + "/";  
    pageContext.setAttribute("basePath",basePath);    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>upload</title>
</head>
<body>
<form action="${basePath}doUpload.htm" method="post" enctype="multipart/form-data">
	 <h2>文件上传</h2>
                文件:<input type="file" name="uploadFile"/><br/><br/>
                文件:<input type="file" name="uploadFile"/><br/><br/>
                文件:<input type="file" name="uploadFile"/><br/><br/>
     <input type="submit" value="上传"/>
</form>
</body>
</html>