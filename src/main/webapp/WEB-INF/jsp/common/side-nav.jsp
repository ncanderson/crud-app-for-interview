<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-xs-12 col-md-3" id="user-nav">

	<div class="text-center">
		<h3>User Options</h3>
	</div>

	<div class="navbar">
		<ul class="nav navbar-nav col-xs-12 col-lg-1" id="side-nav">
		
			<c:url var="dashboardHome" value="/user-home" />
			<li><a href="${ dashboardHome }">User Home</a></li>
		
			<c:url var="updateUserInfo" value="/update-user" />
			<li><a href="${ updateUserInfo }">Update User Information</a></li>
			
			<c:url var="viewCustomers" value="/view-customers" />
			<li><a href="${ viewCustomers }">View Customers</a></li>
			
			<c:url var="newProject" value="/new-project" />
			<li><a href="${ newProject }">New Project</a></li>
			
			<c:url var="newCustomer" value="/new-customer" />
			<li><a href="${ newCustomer }">New Customer</a></li>
			
		</ul>

	</div>

</div>