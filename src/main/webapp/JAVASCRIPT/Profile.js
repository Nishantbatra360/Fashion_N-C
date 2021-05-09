function validateChanges(event){
	event.preventDefault();
	
	var name,contact;
	
	name=document.getElementById("name").value;
	contact=document.getElementById("number").value;
	
	if(!name){
		alert("Name cannot be empty");
	}
	else if(contact.length==0){
		document.getElementById("saveForm").submit();
	}
	else if(contact.length!=10) {
		alert("Please enter 10 digit contact number");
	}
	else{
		document.getElementById("save-form").submit();
	}
}

function validatePassword(event){
	event.preventDefault();
	
	var currentPassword,newPassword,confirmPassword;
	
	currentPassword=document.getElementById("current-password").value;
	newPassword=document.getElementById("new-password").value;
	confirmPassword=document.getElementById("confirm-password").value;
	
	if(!currentPassword){
		alert("Current password cannot be empty");
	}
	else if(currentPassword.length<8){
		alert("Current password should be minimum of 8 characters")
	}
	else if(!newPassword){
		alert("New password cannot be empty");
	}
	else if(newPassword.length<8){
		alert("New password should be minimum of 8 characters");
	}
	else if(newPassword!=confirmPassword){
		alert("Confirm password should be same as new password");
	}
	else if(newPassword==currentPassword){
		alert("New password cannot be same as old password");
	}
	else{
		document.getElementById("change-password-form").submit();
	}
}