<%@page import="model.Researcher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Researcher Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Researcher.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Researcher Management V10.1</h1>
<form id="formR" name="formR">
 Researcher ID:
 <input id="RID" name="RID" type="text"
 class="form-control form-control-sm">
 <br> Researcher name:
 <input id="name" name="name" type="text"
 class="form-control form-control-sm">
 <br> Researcher address:
 <input id="address" name="address" type="text"
 class="form-control form-control-sm">
 <br> Researcher phone:
 <input id="phone" name="phone" type="text"
 class="form-control form-control-sm">
 <br>Researcher email:
 <input id="email" name="email" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidRIDSave"
 name="hidRIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divRGrid">
 <%
 Researcher rObj = new Researcher();
 out.print(rObj.readResearchers());
 %>
</div>
</div> </div> </div>
</body>
</html>