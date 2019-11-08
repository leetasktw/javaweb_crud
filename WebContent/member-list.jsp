<%@ page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>My Database</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="Shortcut Icon" type="image/x-icon" href="favicon.ico"/>
<style>
.profilepic{
	margin:0px auto 20px;
	max-width: 20%;
  	height: auto;
	border-radius: 50%;
}
.navpic{
	position:absolute;
	top:10px;
	right:100px;
	opacity: 1;
  	display: block;
  	width: 50px;
  	height: 50px;
  	transition: .5s ease;
  	backface-visibility: hidden;
  	border-radius: 50%;
  	float:right;
}
.navpic:hover{
  opacity: 0.7;
}
</style>
</head>
<body class="bg-light">

<div class="pos-f-t">
  <nav class="navbar navbar-dark bg-dark">
  	<a class="navbar-brand" href="listUser">MyDataBase</a>
  	<a href="listMember"><img src="files<%= File.separator +(String)session.getAttribute("uname") +".jpg" %>" class="navpic"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
  </nav>
  <div class="collapse" id="navbarToggleExternalContent">
    <div class="bg-dark p-1">
    	<ul class="navbar-nav mr-auto text-right text-white">
		    <li class="nav-item"><%= "Welcome, " + (String) session.getAttribute("uname") %></li>
		    <li class="nav-item"><a href="#" class="text-white">Member Profile</a></li>
		    <li class="nav-item"><%= "Current Users: " + (int) application.getAttribute("currentusers") %></li>
			<li class="nav-item mt-1"><a href="LogoutServlet" class="btn btn-outline-secondary btn-sm text-white">Logout</a></li>    
	    </ul>
    </div>
  </div>  
</div>


<center>
	<h1>Member Profile</h1>
	
</center>
    
    <div align="center">    	
    	<img src="files<%= File.separator +(String)session.getAttribute("uname") +".jpg" %>" class="profilepic">
        <form action="UploadServlet" method="post" enctype="multipart/form-data">
			<label class="btn btn-info" style="margin:0 auto;">
			<input type="file" name="file" style="display:none;" >
			Upload Photo
			</label>
			<input class="btn btn-success" type="submit" value="Submit">
		</form>
        <table class="table table-hover w-50">
            
            <tr class="table-secondary">   
            	<th>ID</th>             
                <th>Name</th>
                <th>Password</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
            
                <tr class="table-info">    
                	<td><c:out value="${listMember.id}" /></td>                
                    <td><c:out value="${listMember.name}" /></td>
                    <td>******</td>
                    <td><c:out value="${listMember.email}" /></td>
                    <td>
                    	<a href="showEditForm2?id=<c:out value='${listMember.id}' />" class="btn btn-primary">Edit</a>                    	                    	
                    </td>
                </tr>
            
        </table>
    </div>	
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script>
var imgs=document.images;
for (var i=0;i<imgs.length;i++){
imgs[i].onerror=function(){this.src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPx9SxsxsXlkdbpKbeCKbbhoFdZgKTcn0DJy8T7_gBtjudTlCF"}
}
</script>
</body>
</html>
