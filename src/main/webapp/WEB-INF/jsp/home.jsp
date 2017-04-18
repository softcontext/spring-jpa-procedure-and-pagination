<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<h2>home.jsp</h2>
	<hr>
	
	<div><a href="/">home</a></div>
	<hr>
	
	<h3>DeptRestController</h3>
	<div><a href="depts/all">http://localhost:8080/depts/all</a></div><br>
	
	<div><a href="depts">http://localhost:8080/depts</a></div>
	<div><a href="depts?page=0&size=2">http://localhost:8080/depts?page=0&size=2</a></div>
	<div><a href="depts?page=0&size=2&sort=deptno,asc">http://localhost:8080/depts?page=0&size=2&sort=deptno,asc</a></div><br>
	
	<div><a href="depts/1?size=2">http://localhost:8080/depts/1?size=2</a></div>
	<hr>
	
	<h3>DeptController</h3>
	<div><a href="departments">http://localhost:8080/departments</a></div>
	<div><a href="departments?page=1&size=2">http://localhost:8080/departments?page=1&size=2</a></div>
	<div><a href="departments?page=1&size=2&sort=deptno,asc">http://localhost:8080/departments?page=1&size=2&sort=deptno,asc</a></div>
	<div><a href="departments?page=1&size=2&sort=deptno,asc&bsize=2">http://localhost:8080/departments?page=1&size=2&sort=deptno,asc&bsize=2</a></div><br>
	
	<div><a href="departments?json">http://localhost:8080/departments?json</a></div>
	<div><a href="departments?json&page=1&size=2">http://localhost:8080/departments?json&page=1&size=2</a></div>
	<div><a href="departments?json&page=1&size=2&sort=deptno,asc">http://localhost:8080/departments?json&page=1&size=2&sort=deptno,asc</a></div>
	<div><a href="departments?json&page=1&size=2&sort=deptno,asc&bsize=2">http://localhost:8080/departments?json&page=1&size=2&sort=deptno,asc&bsize=2</a></div>
	<hr>
	
</body>
</html>