$(document).ready(function () {

	$("form").validate({
		rules : {
			username : {
				required : true
			},
			password : {
				required : true
			}
		},
		messages : {			
			confirmPassword : {
				equalTo : "Passwords do not match"
			}
		},
		errorClass : "error"
	});
	
	$("#sign-up-form").validate({
        rules : {
            password : {
                minlength : 5
            },
            passwordVerify : {
                minlength : 5,
                equalTo : "#password"
            }
        },
		messages : {			
			confirmPassword : {
				equalTo : "Passwords do not match"
			}
		},
		errorClass : "error"
	});
});
