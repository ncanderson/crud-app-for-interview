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
	
//	Start timer and make new task_entry when button is clicked
	
//	$(".start-timer").click(function() {
//		this.preventDefault();
//		
//		$.ajax ({
//			url: "start-time?taskId=" + $(this).val(),
//			type: "POST"
//			
//		})
//		.done(function(data) {
//			
//		})
//		.fail(function(xhr, status, error) {
//			
//		});
//	});
	
});