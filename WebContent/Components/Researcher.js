$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});

function onRSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divRGrid").html(resultSet.data);
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
  $("#hidRIDSave").val("");
 $("#formR")[0].reset();
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
var status = validateRForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
 //$("#formR").submit();
 
 // If valid------------------------
var type = ($("#hidRSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "ResearcherAPI",
 type : type,
 data : $("#formR").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onRSaveComplete(response.responseText, status);
 }
 }); 
 
});


$(document).on("click", ".btnUpdate", function(event)
{
$("#hidRSave").val($(this).data("Rid"));
 $("#name").val($(this).closest("tr").find('td:eq(0)').text());
 $("#address").val($(this).closest("tr").find('td:eq(1)').text());
 $("#phone").val($(this).closest("tr").find('td:eq(2)').text());
 $("#email").val($(this).closest("tr").find('td:eq(3)').text());
});

function onRDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divRGrid").html(resultSet.data);
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
 url : "ResearcherAPI",
 type : "DELETE",
 data : "RID=" + $(this).data("Rid"),
 dataType : "text",
 complete : function(response, status)
 {
 onRDeleteComplete(response.responseText, status);
 }
 });
});



// CLIENT-MODEL================================================================
function validateRForm()
{

if ($("#Rid").val().trim() == "")
 {
 return "Insert Researcher id.";
 }
// NAME
if ($("#name").val().trim() == "")
 {
 return "Insert Researcher Name.";
 } 

if ($("#address").val().trim() == "")
 {
 return "Insert Researcher address.";
 }
 if ($("#phone").val().trim() == "")
 {
 return "Insert Researcher phone.";
 }
 if ($("#phone").val().trim() == "")
 {
 return "Insert Researcher phone.";
 }
  }
 if ($("#email").val().trim() == "")
 {
 return "Insert Researcher email.";
 }
// is numerical value
//var tmpPrice = $("#RPrice").val().trim();
//if (!$.isNumeric(tmpPrice))
 //{
 //return "Insert a numerical value for Researcher Price.";
 //}
// convert to decimal price
 //$("#RPrice").val(parseFloat(tmpPrice).toFixed(2));
// DESCRIPTION------------------------

//return true;
//}
 