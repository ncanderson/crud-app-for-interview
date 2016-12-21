<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:import url="/WEB-INF/jsp/common/side-nav.jsp" />

<div class="jumbotron">
	<h3>User:</h3>
	<p>User: ${ currentUser.username }</p>
	<p>Email: ${ currentUser.email }</p>
	<p>Created: ${ currentUser.createdAt }</p>
	<p>Last updated: ${ currentUser.updatedAt }</p>
	
	<h3>Your Projects:</h3>
	<c:forEach var="project" items="${ userProjects }">
		<c:url var="projectDetails" value="view-project-details?projectId=${ project.projectId }" />
		<p><a href="${ projectDetails }"></a>Project: ${ project.projectName }</p>
		<p>Created: ${ project.createdAt } </p>
		<p>Last updated: ${ project.updatedAt }</p>
	</c:forEach>

</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />

