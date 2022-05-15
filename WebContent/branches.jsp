<%@page import="com.Branch"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%-- 
    
if (request.getParameter("BranchID") != null)
{
	Branch BranchObj = new Branch();
	String stsMsg = BranchObj.deleteBranch(request.getParameter("BranchID"));
	session.setAttribute("statusMsg", stsMsg);
}
 --%>
    
    
    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Branches</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.5.0.min.js"></script>
<script src="Components/branches.js"></script>

 
<!-- ------------------------------------------------------ -->
</head>
<body style="background-color:powderblue;">
<!-- ---------------------------------------------------------------------------------------------------------- -->
 

<div class="container"><div class="row"><div class="col-6">
<h1> <b>Branch Management</b>  </h1>
<form  method="post" action="branches.jsp"id="formBranch" name="formBranch">
<br>Branch name: 
<input id="BranchName" name="BranchName" type="text"
class="form-control form-control-sm">
<br> Category:
<input id="Category" name="Category" type="text"
class="form-control form-control-sm">
<br> Location:
<input id="Location" name="Location" type="text"
class="form-control form-control-sm">
<br> Power Capacity:
<input id="PowerCapacity" name="PowerCapacity" type="text"
class="form-control form-control-sm">
 <br> Head Engineer:
<input id="HeadEngineer" name="HeadEngineer" type="text"
class="form-control form-control-sm">
<input id="btnSave" name="btnSave" type="button" value="Save"
class="btn btn-primary">
<input type="hidden" id="hidBranchIDSave"
name="hidBranchIDSave" value="">
</form>
 <%--
out.print(session.getAttribute("statusMsg"));
--%>  
    <div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div> 
<br> 
<div id="divBranchesGrid">  
 <%  
  Branch BranchObj = new Branch();
  out.print(BranchObj.readBranch());
  %>
   
</div>
</div> </div> </div>
<!-- -------------------------------------------------------------------------------------------------------------------- -->
<footer class="footer bg-dark mt-5">
		<div class="container">
			<div class="text-center text-light">
				<span>Created by , <span style="color: #87CEEB;">chandima
						 </span> 2022
				</span>
			</div>
		</div>
	</footer>
	<!-- --------------------------------------------------------------- -->
</body>
</html> 