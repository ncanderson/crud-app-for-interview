<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

	<div class="jumbotron">
	
		<h1 class="text-center">Please login to access the site</h1>
		
		<div class="center-block col-xs-8">
			<form method="POST" action="login">
				<ul class="form-flex-outer">
					<li>
						<label for="userName">Username: </label>
						<input type="text" name="userName" /><br />
					</li>
					<li>
						<label for="password">Password: </label>
						<input type="password" name="password" /><br />
					</li>
					<li>
						<input type="submit" class="form-centered-button" value="Submit" />
					</li>
				</ul>
			</form>
		</div>
	
	</div>
            
<c:import url="/WEB-INF/jsp/common/footer.jsp" />