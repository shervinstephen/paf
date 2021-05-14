$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});

function onFSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divFGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
  $("#hidFIDSave").val("");
 $("#formF")[0].reset();
}
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateFForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
 //$("#formF").submit();
 
 // If valid------------------------
var type = ($("#hidFSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "FunderAPI",
 type : type,
 data : $("#formF").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onFSaveComplete(response.responseText, status);
 }
 }); 
 
});



$(document).on("click", ".btnUpdate", function(event)
{
$("#hidFSave").val($(this).data("FID"));
 $("#name").val($(this).closest("tr").find('td:eq(0)').text());
 $("#amount").val($(this).closest("tr").find('td:eq(1)').text());
 $("#phone").val($(this).closest("tr").find('td:eq(2)').text());
 $("#email").val($(this).closest("tr").find('td:eq(3)').text());
});

function onFDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divFGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}



$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "FunderAPI",
 type : "DELETE",
 data : "FID=" + $(this).data("FID"),
 dataType : "text",
 complete : function(response, status)
 {
 onFDeleteComplete(response.responseText, status);
 }
 });
});



// CLIENT-MODEL================================================================
function validateFForm()
{

if ($("#FID").val().trim() == "")
 {
 return "Insert Funder id.";
 }
// NAME
if ($("#name").val().trim() == "")
 {
 return "Insert Funder Name.";
 } 

if ($("#amount").val().trim() == "")
 {
 return "Insert Funder amount.";
 }
 if ($("#phone").val().trim() == "")
 {
 return "Insert Funder phone.";
 }
 if ($("#phone").val().trim() == "")
 {
 return "Insert Funder phone.";
 }
  
 if ($("#email").val().trim() == "")
 {
 return "Insert Funder email.";
 }
 }

//return true;
//}
 