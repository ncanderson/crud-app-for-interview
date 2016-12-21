<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:import url="/WEB-INF/jsp/common/side-nav.jsp" />

<div class="jumbotron">

	<h3>All Customers</h3>
	<c:forEach var="customer" items="${ customerList }">
		<p>Company: ${ customer.company }</p>
		<p>Address: ${ customer.address }</p>
		<p>         ${ customer.city }, ${ customer.state }</p>
		<p>         ${ customer.zip }</p>
		<p>Created at: ${ customer.createdAt }</p>
		<p>Last updated: ${ customer.updatedAt }</p>
	</c:forEach>

</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />
