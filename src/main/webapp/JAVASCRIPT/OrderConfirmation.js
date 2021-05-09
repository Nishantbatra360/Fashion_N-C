function validateInput(event){
	event.preventDefault();
	
	var streetAddress,city,province,postal;
	
	streetAddress=document.getElementById("streetAddress").value;
	city=document.getElementById("city").value;
	province=document.getElementById("province").value;
	postal=document.getElementById("postal").value;
	
	if(!streetAddress){
		alert("Street address cannot be empty");
	}
	else if(!city){
		alert("City cannot be empty");
	}
	else if(!province){
		alert("Province cannot be empty");
	}
	else if(!postal){
		alert("Please enter postal code");
	}
	else if(postal.length!=6){
		alert("Postal code should be of 6 characters");
	}
	else{
		document.getElementById("orderForm").submit();
	}
}

