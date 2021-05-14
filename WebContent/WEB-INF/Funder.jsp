<%@page import="model.Funder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Funder Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Funder.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Funder Management V10.1</h1>
<form id="formF" name="formF">
 Funder ID:
 <input id="FID" name="FID" type="text"
 class="form-control form-control-sm">
 <br> Funder name:
 <input id="name" name="name" type="text"
 class="form-control form-control-sm">
 <br> Funder amount:
 <input id="amount" name="amount" type="text"
 class="form-control form-control-sm">
 <br> Funder phone:
 <input id="phone" name="phone" type="text"
 class="form-control form-control-sm">
 <br>Funder email:
 <input id="email" name="email" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidFIDSave"
 name="hidFIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divFGrid">
 <%
 Funder fObj = new Funder();
 out.print(fObj.readFunders());
 %>
</div>
</div> </div> </div>
</body>
</html>