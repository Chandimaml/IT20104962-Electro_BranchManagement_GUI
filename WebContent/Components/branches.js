$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
{
		$("#alertSuccess").hide();
}
	$("#alertError").hide();
});
// SAVE ============================================
	$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
		$("#alertSuccess").text("");
		$("#alertSuccess").hide();
		$("#alertError").text("");
		$("#alertError").hide();
// Form validation-------------------
var status = validateBranchForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
return;
}
// If valid------------------------
//$("#formItem").submit();
var type = ($("#hidBranchIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
{
url : "BranchesAPI",
type : type,
data : $("#formBranch").serialize(),
dataType : "text",
complete : function(response, status)
{
onBranchSaveComplete(response.responseText, status);
}
});
});
function onBranchSaveComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully saved.");
$("#alertSuccess").show();
$("#divBranchesGrid").html(resultSet.data);
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
$("#hidBranchIDSave").val("");
$("#formBranch")[0].reset();
}
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidBranchIDSave").val($(this).closest("tr").find('#hidBranchIDUpdate').val());
 	$("#hidBranchIDSave").val($(this).data("BranchID"));
 $("#BranchName").val($(this).closest("tr").find('td:eq(1)').text());
$("#Category").val($(this).closest("tr").find('td:eq(2)').text());
$("#Location").val($(this).closest("tr").find('td:eq(3)').text());
$("#PowerCapacity").val($(this).closest("tr").find('td:eq(4)').text());
$("#HeadEngineer").val($(this).closest("tr").find('td:eq(5)').text());
});
//DELETE======================================================================
$(document).on("click", ".btnRemove", function(event)
		{
		$.ajax(
		{
		url : "BranchesAPI",
		type : "DELETE",
		data : "BranchID=" + $(this).data("BID"),
		dataType : "text",
		complete : function(response, status)
		{
		onBranchDeleteComplete(response.responseText, status);
		//window.location.reload(true);
		$("#alertSuccess").fadeTo(2000, 500).slideUp(500, function() {
			$("#alertSuccess").slideUp(500);
		});
		}
		});
		});
function onBranchDeleteComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divBranchesGrid").html(resultSet.data);
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
// CLIENT-MODEL================================================================
function validateBranchForm()
{
// NAME
if ($("#BranchName").val().trim() == "")
{
return "Insert Branch Name.";
}
//CATEGORY
if ($("#Category").val().trim() == "")
{
return "Insert Branch Category.";
}
//LOCATION-------------------------------
if ($("#Location").val().trim() == "")
{
return "Insert Branch Location.";
}
 
// POWERCAPACITY------------------------
if ($("#PowerCapacity").val().trim() == "")
{
return "Insert Branch Power Capacity.";
}
 
//HEADENGINEER------------------------
if ($("#HeadEngineer").val().trim() == "")
{
return "Insert Branch Head name.";
}
return true;
}