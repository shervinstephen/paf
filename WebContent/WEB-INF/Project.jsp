<%@page import="model.Project"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Project.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Project Management V10.1</h1>
<form id="formP" name="formP">
 Project ID:
 <input id="PID" name="PID" type="text"
 class="form-control form-control-sm">
 <br> Project type:
 <input id="type" name="type" type="text"
 class="form-control form-control-sm">
 <br> Project name:
 <input id="name" name="name" type="text"
 class="form-control form-control-sm">
 <br> Project details:
 <input id="details" name="details" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidPIDSave"
 name="hidPIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divPGrid">
 <%
 Project pObj = new Project();
 out.print(pObj.readProject());
 %>
</div>
</div> </div> </div>
</body>
</html>