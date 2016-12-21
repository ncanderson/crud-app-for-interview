<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="jumbotron">
	<h1>Enter new project</h1>
	
	<div>
		<form action="new-project" method="POST" id="new-project-form">
			<ul class="form-flex-outer">
				<li>
					<label for="projectName">Enter project name:</label>
					<input type="text" name="projectName" />
				</li>
				<li>
					<label for="customerName">Select Customer:</label>
					<input type="text" name="customerName" />
				</li>
				<li>
					<button type="submit" class="btn btn-default">Submit</button>
					<input type="hidden" name="CSRF_TOKEN" value="${ CSRF_TOKEN }" />
					<input type="hidden" name="destination" value="${ param.destination }" />
				</li>
			</ul>
		</form>
	</div>
	
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />
