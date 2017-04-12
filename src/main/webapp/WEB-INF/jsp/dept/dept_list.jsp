<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Department's List</title>
<style>
table {
	border-collapse: collapse;
}

table, td, th {
	border: 1px solid black;
}

td, th {
	padding: 3px;
}
</style>
</head>
<body>
	<h2>Department's List</h2>
	<table>
		<thead>
			<tr>
				<th>Dept No.</th>
				<th>Dept Name</th>
				<th>Location</th>
				<th>Management</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.content }" var="dept">
				<tr>
					<td>${dept.deptno }</td>
					<td>${dept.dname }</td>
					<td>${dept.loc }</td>
					<td></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div>
		<c:if test="${pager.totalRows==0 }">
			<!-- 페이징 정보가 없습니다. -->
		</c:if>
		<c:if test="${pager.totalRows > 0 }">

			<c:if test="${pager.sort != null }">
				<c:set var="sortItem" value="${pager.sort.iterator().next() }"/>
				<c:if test="${sortItem != null }">
					<c:set var="sort" value="sort=${sortItem.property },${sortItem.ascending ? 'asc' : 'desc' }"/>
				</c:if>
			</c:if>
			
			<c:set var="common" value="size=${pager.rowsPerPage }&bsize=${pager.pagesPerBlock }&${sort }"/>
			
			<c:if test="${pager.thisBlockStartPage > pager.pagesPerBlock }">
				<c:set var="home" value="page=1&${common }"/>
				<c:set var="prev" value="page=${pager.thisBlockStartPage-1 }&${common }"/>
				<a href="departments?${home }">Home</a>&nbsp;
				<a href='<c:url value="departments?${prev }"/>'>Prev</a>&nbsp;
			</c:if>
			
			<c:forEach var="pno" begin="${pager.thisBlockStartPage }" end="${pager.thisBlockEndPage }">
				<c:if test="${pno==pager.thisPage }"><font color="red" style="font-weight: bold;">${pno }</font></c:if>
				<c:if test="${pno!=pager.thisPage }">
					<c:if test="${pno <= pager.totalPages }">
						<c:set var="page" value="page=${pno }&${common }"/>
						<a href='<c:url value="departments?${page }"/>'>${pno }</a>
					</c:if>
				</c:if>
				&nbsp;
			</c:forEach>
			
			<c:if test="${pager.thisBlockEndPage < pager.totalPages }">
				<c:set var="next" value="page=${pager.thisBlockEndPage+1 }&${common }"/>
				<c:set var="last" value="page=${pager.totalPages }&${common }"/>
				<a href='<c:url value="departments?${next }"/>'>Next</a>&nbsp;
				<a href="departments?${last }">Last</a>
			</c:if>
			
			<%
			/*
				ObjectMapper mapper = new ObjectMapper();
				Object pager = request.getAttribute("pager");
				out.print("<br/><div>"+mapper.writeValueAsString(pager)+"</div>");

				{
					"totalRows":11,
					"thisPage":2,
					"rowsPerPage":2,
					"pagesPerBlock":2,
					"totalPages":6,
					"totalBlocks":3,
					"thisBlock":1,
					"thisBlockEndPage":2,
					"thisBlockStartPage":1,
					"numberOfElements":2,
					"sort":[
						{
							"direction":"ASC",
							"property":"deptno",
							"ignoreCase":false,
							"nullHandling":"NATIVE",
							"ascending":true,
							"descending":false
						}
					],
					"content":[
						{"deptno":30,"dname":"SALES","loc":"CHICAGO"},
						{"deptno":40,"dname":"OPERATIONS","loc":"BOSTON"}
					],
					"first":false,
					"last":false,
					"zeroBase":false
				}
				*/
			%>
		</c:if>
	</div>
				
</body>
</html>