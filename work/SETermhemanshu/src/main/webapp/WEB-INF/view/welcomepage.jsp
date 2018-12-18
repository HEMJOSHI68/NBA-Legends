<!DOCTYPE html >
<%@page import="com.hemanshu.Team"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
<title>NBA Legends</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<div role="navigation">
		<div class="navbar navbar-inverse">
			<a href="/show" class="navbar-brand"><img src="https://theundefeated.com/wp-content/uploads/2017/05/nba-logo.png" style="max-width:15px;" style="max-heigth:15px;">   NBA Legends</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="/show">Index</a></li>
					<li><a href="/register">Select Favorite Team</a></li>
				</ul>
			</div>
		</div>
	</div>

	<c:choose>
		

		<c:when test="${mode=='MODE_REGISTER' }">
			<div class="container text-center">
				<h3>Welcome James ,Select Favorite team</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="/save-team">
				
					<select class="js-example-basic-multiple" name="name" multiple="multiple">
                    <option value="Washington Wizards">Washington Wizards</option>
                    <option value="Miami Heat">Miami Heat</option>
                    <option value="Los Angeles Clippers">Los Angeles Clippers</option>
                    
                    </select>
                    
                    
                    <% 
                         String s= request.getParameter("name");
                          Team t= new Team();
                          t.setName(s);
                    %>
                    <div class="form-group ">
						<input type="submit" class="btn btn-primary" value="submit" />
					</div>
				</form>
			</div>
		</c:when>
		<c:when test="${mode=='MODE_HOME' }">
			<div class="container text-center" id="tasksDiv">
				<h3>Selected Teams</h3>
				<hr>
				<div >
					<table   style="float: left">
						<thead>
							<tr>
						
								<th>Name</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${users }">
								<tr>
									<td>${user}</td>
						     <c:set var="getuser12" value="${fn:split(user, ' ')}" />     
						      <c:forEach var="user123" items="${getuser12 }">   
						        <c:set var="string3" value="" />
						  		   <c:set var = "string2" value = "${fn:substring(user123, 0,1)}" />
						  		   <c:set var="string3" value="${string3}${string2}" />
						  		   
								</c:forEach>
								
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<table    style="float: Right">
					<tr>
						
								<th>Abbreviation</th>
							</tr>
					<c:forEach var="abb" items="${abbr }">
								<tr>
									<td>${abb}</td>
									</tr>
									</c:forEach>
					</table>
				</div>
			</div>
		</c:when>

			</c:choose>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
	<script>  
						$(document).ready(function() {
						    $('.js-example-basic-multiple').select2();
						});
						</script>
</body>
</html>