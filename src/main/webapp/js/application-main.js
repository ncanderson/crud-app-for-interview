$(document).ready(function() {
	
	$("#logoutLink").click(function(event) {
		$("#logoutForm").submit();
	});
	
	$("input, textarea").keypress(function(event) {
	    if (event.which == 13) {
	        event.preventDefault();
	        $("form").submit();
	    }
	});
	
	$("button.start-timer").click(function() {
		alert("Time recorded!");
	});
	
});