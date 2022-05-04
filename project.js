function CheckandSubmit(){
	var firstName = document.getElementById ("firstName");
	if (document.getElementById("firstName").value.length < 3){
		alert ("First name must be at least 3 characters in length!");
		return false;
	}
	var lastName = document.getElementById ("lastName");
	if (lastName.value.length < 3){
		alert ("Last name must be at least 3 characters in length!");
		return false;
	}
	var email = document.getElementById("email").value;
	var phone = document.getElementById("Phone").value;
	if (phone.length < 10){
		alert ("Phone number must be at least 10 digit in length!");
		return false;
	}
	if (!isNaN(phone.value)){
		alert ("Phone number must be numeric!");
		return false;
	}
	var address = document.getElementById("Address");
	if (address.value.length < 3){
		alert ("Address must be at least 3 characters in length!");
		return false;
	}
	var city = document.getElementById("City"); 
	if (city.value.length < 3){
		alert ("City name must be at least 3 characters in length!");
		return false;
	}
	var state = document.getElementById ("state");
	if (state.value.length > 2){
		alert ("State must be at least 2 characters in length!");
		return false;
	}
	var zip = document.getElementById("Zip"); 
	if (zip.value.length == 6){
		alert ("Zip Code must be at least 5 digit in length!");
		return false;
	}
	if (isNaN(zip.value)){
		alert ("Zip code must be numeric!");
		return false;
	}
	return true;
}