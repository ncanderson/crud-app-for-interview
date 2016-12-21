<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:import url="/WEB-INF/jsp/common/side-nav.jsp" />

<div class="jumbotron">

	<h3>Your Projects:</h3>
	<p>Project: ${ project.projectName }</p>
	<p>Created: ${ project.createdAt } </p>
	<p>Last updated: ${ project.updatedAt }</p>
	<c:forEach var="task" items="${ project.projectTasks }">
		<p>Task: ${ task.taskName }</p>
		<p>Created at: ${ task.createAt }</p>
		<p>Last updated: ${ task.updatedAt }</p>
		<c:forEach var="taskEntry" items="${ task.taskEntries }">
			<p>Note: ${ taskEntry.note }</p>
			<p>Start time: ${ taskEntry.startTime }</p>
			<p>Duration: ${ taskEntry.duration }</p>
			<p>Created at: ${ taskEntry.createdAt }</p>
			<p>Last updated: ${ taskEntry.updatedAt }</p>
		</c:forEach>
	</c:forEach>
	<br />
	
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />
