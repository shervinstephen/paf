$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});

function onPSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divPGPID").html(resultSet.data);
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
  $("#hidPIDSave").val("");
 $("#formP")[0].reset();
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
var status = validatePForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
 //$("#formP").submit();
 
 // If valid------------------------
var type = ($("#hidPSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "ProjectAPI",
 type : type,
 data : $("#formP").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onPSaveComplete(response.responseText, status);
 }
 }); 
 
});


$(document).on("click", ".btnUpdate", function(event)
{
$("#hidPSave").val($(this).data("PID"));
 $("#type").val($(this).closest("tr").find('td:eq(0)').text());
 $("#name").val($(this).closest("tr").find('td:eq(1)').text());
 $("#details").val($(this).closest("tr").find('td:eq(2)').text());
});

function onPDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divPGPID").html(resultSet.data);
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
 url : "ProjectAPI",
 type : "DELETE",
 data : "PID=" + $(this).data("PID"),
 dataType : "text",
 complete : function(response, status)
 {
 onPDeleteComplete(response.responseText, status);
 }
 });
});



// CLIENT-MODEL================================================================
function validatePForm()
{

if ($("#PID").val().trim() == "")
 {
 return "Insert Project id.";
 }
 if ($("#type").val().trim() == "")
 {
 return "Insert Project type.";
 } 
// NAME
if ($("#name").val().trim() == "")
 {
 return "Insert Project Name.";
 } 

if ($("#details").val().trim() == "")
 {
 return "Insert Project details.";
 }
}
 

//return true;
//}
 