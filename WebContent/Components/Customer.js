$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});

function onCSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divCGCID").html(resultSet.data);
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
  $("#hidCIDSave").val("");
 $("#formC")[0].reset();
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
var status = validateCForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
 //$("#formC").submit();
 
 // If valid------------------------
var type = ($("#hidCSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "CustomerAPI",
 type : type,
 data : $("#formC").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onCSaveComplete(response.responseText, status);
 }
 }); 
 
});


$(document).on("click", ".btnUpdate", function(event)
{
$("#hidCSave").val($(this).data("CID"));
 $("#name").val($(this).closest("tr").find('td:eq(0)').text());
 $("#details").val($(this).closest("tr").find('td:eq(1)').text());
});

function onCDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divCGCID").html(resultSet.data);
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
 url : "CustomerAPI",
 type : "DELETE",
 data : "CID=" + $(this).data("CID"),
 dataType : "text",
 complete : function(response, status)
 {
 onCDeleteComplete(response.responseText, status);
 }
 });
});



// CLIENT-MODEL================================================================
function validateCForm()
{

if ($("#CID").val().trim() == "")
 {
 return "Insert Customer id.";
 }
// NAME
if ($("#name").val().trim() == "")
 {
 return "Insert Customer Name.";
 } 

if ($("#details").val().trim() == "")
 {
 return "Insert Customer details.";
 }
 }
//return true;
//}
 