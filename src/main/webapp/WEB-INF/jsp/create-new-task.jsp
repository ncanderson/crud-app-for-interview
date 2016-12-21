<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:import url="/WEB-INF/jsp/common/side-nav.jsp" />

<div class="jumbotron">
	<h1>Enter new task for project ${ project.projectName }</h1>
	
	<div>
		<form action="create-new-task" method="POST" id="new-task-form">
			<ul class="form-flex-outer">
				<li>
					<label for="taskName">Enter task name:</label>
					<input type="text" name="taskName" />
				</li>
				<li>
					<button type="submit" class="btn btn-default">Submit</button>
					<input type="hidden" name="projectId" value="${ project.projectId }" />
					<input type="hidden" name="CSRF_TOKEN" value="${ CSRF_TOKEN }" />
					<input type="hidden" name="destination" value="${ param.destination }" />
				</li>
			</ul>
		</form>
	</div>
	
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />
