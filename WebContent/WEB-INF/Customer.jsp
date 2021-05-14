<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Customer.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Customer Management V10.1</h1>
<form id="formC" name="formC">
 Customer ID:
 <input id="CID" name="CID" type="text"
 class="form-control form-control-sm">
 <br> Customer name:
 <input id="name" name="name" type="text"
 class="form-control form-control-sm">
 <br> Customer details:
 <input id="details" name="details" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidCIDSave"
 name="hidCIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divCGrid">
 <%
 Customer cObj = new Customer();
 out.print(cObj.readCustomer());
 %>
</div>
</div> </div> </div>
</body>
</html>