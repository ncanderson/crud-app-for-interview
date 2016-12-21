<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="jumbotron">
	<h1>Please create your account</h1>
	
	<div>
		<form action="sign-up" method="POST" id="sign-up-form">
			<ul class="form-flex-outer">
				<li>
					<label for="username">Enter your username:</label>
					<input type="text" name="username" />
				</li>
				<li>
					<label for="email">Enter your email:</label>
					<input type="text" name="email" />
				</li>
				<li>
					<label for="password">Enter your password:</label>
					<input type="password" name="password" id="password" />
				</li>
				<li>
					<label for="passwordVerify">Confirm password:</label>
					<input type="password" name="passwordVerify" id="passwordConfirm" data-rule-equalTo="#password" required />			
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
