<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

	<div class="jumbotron">
	
		<h1 class="text-center">Please login to access the site</h1>
		
		<div class="center-block col-xs-8">
			<form method="POST" action="login" id="login">
				<ul class="form-flex-outer">
					<li>
						<label for="username">Username: </label>
						<input type="text" name="username" /><br />
					</li>
					<li>
						<label for="password">Password: </label>
						<input type="password" name="password" /><br />
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