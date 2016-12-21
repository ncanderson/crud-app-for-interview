<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:import url="/WEB-INF/jsp/common/side-nav.jsp" />

<div class="jumbotron">
	
	<h2>Your current tasks:</h2>
	<c:forEach var="task" items="${ userTasks }">
		<p>Task: ${ task.taskName }</p>
		<p>From project: ${ task.projectName }</p>
		<form action="start-timer" method="POST">
			<button type="submit" class="btn btn-default" class="start-timer">Start/End time</button>
			<input type="hidden" name="taskId" value="${ task.taskId }" />
			<input type="hidden" name="CSRF_TOKEN" value="${ CSRF_TOKEN }" />
			<input type="hidden" name="destination" value="${ param.destination }" />	
		</form>
	</c:forEach>
	
	<h2>Your Projects:</h2>
	<c:forEach var="project" items="${ userProjects }">
		<c:url var="projectDetails" value="view-project-details?projectId=${ project.projectId }" />
		<p><a href="${ projectDetails }"></a>Project: ${ project.projectName }</p>
		<p>Created: ${ project.createdAt } </p>
		<p>Last updated: ${ project.updatedAt }</p>
	</c:forEach>

</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />

