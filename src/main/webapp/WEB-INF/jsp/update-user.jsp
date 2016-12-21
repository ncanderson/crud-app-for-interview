<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:import url="/WEB-INF/jsp/common/side-nav.jsp" />

<h3>Update user information for ${ currentUser.username }</h3>

<div class="col-xs-12 col-md-9">
	<form action="update-user" method="POST">
		<ul class="form-flex-outer">
			<li>
				<label for="username">Username:</label>
				<input type="text" name="username" value="${ currentUser.username }" />
			</li>
			<li>
				<label for="password">Password:</label>
				<input type="text" name="password" value="${ currentUser.password }" />
			</li>
			<li>
				<label for="email">Email:</label>
				<input type="text" name="email" value="${ currentUser.email }" />
			</li>
			<li>
				<input type="submit" value="Submit changes" />
				<input type="hidden" name="userId" value="${ currentUser.userId }" />
				<input type="hidden" name="CSRF_TOKEN" value="${ CSRF_TOKEN }" />
				<input type="hidden" name="destination" value="${ param.destination }" />
			</li>
		</ul>
	</form>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />