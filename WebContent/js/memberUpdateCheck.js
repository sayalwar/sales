
function check() {
	var message = "";
	var memberName = document.getElementById("memberName").value;
	var memberAddress = document.getElementById("memberAddress").value;
	var phone = document.getElementById("phone").value;
	var memberPassword = document.getElementById("memberPassword").value;
	var memberPasswordCheck = document.getElementById("memberPasswordCheck").value;
	//パスワードの入力値

	if (memberName.length == 0) {
		message += "<p>NAME is required.</p>";
	}

	if (memberAddress.length == 0) {
		message += "<p>ADDRESS is required</p>";
	}

		if (phone.length == 0) {
		message += "<p>TELEPHONE is required</p>";
	}
			if (phone.length != 12 && phone.length!= 0) {
		message += "<p>TELEPHONE should be 10 digit</p>";
	}

		if (memberPassword.length == 0) {
		message += "<p>PASSWORD is required</p>";
	}
if (memberPassword.length  < 6 && memberPassword.length != 0) {
	message += "<p>Minimum 6 digit PASSWORD is required</p>";
	}
	if (memberPasswordCheck.length == 0) {
		message += "<p>PASSWORD CHECk is required</p>";
	}
	if (!(memberPassword==memberPasswordCheck))
	{
	message += "<p>Please Enter Same Password and Password Check.</p>";
	}
	if (message != "") {
		var targetDiv = document.getElementById("target");
		targetDiv.innerHTML = message;
		return false;
	}
	return true;
}
window.onload = function() {
	var B0203G03Form = document.getElementById("B0203G03F");
	B0203G03Form.onsubmit = check;
};
 function onlyNumbers(evt)
    {
	    var e = event || evt; // for trans-browser compatibility
	    var charCode = e.which || e.keyCode;
		alert
   if(( charCode==8) || (charCode==189))
   {
   return true;
   }

	else if(charCode > 31 && (charCode < 48 || charCode > 57))
	    {
	    	alert("Enter Number");
		    return false;
		  }
	    return true;
    }

function allLetter(evt)
    {
	    var e = event || evt; // for trans-browser compatibility
	    var charCode = e.which || e.keyCode;
   if(( charCode==8) || (charCode==9) || (charCode==13)||(charCode==16)||(charCode==20)||(charCode==32)||(charCode >= 65 && charCode <=90)||(charCode >= 97 && charCode <= 122))
   {
   return true;
   }
	else
	   {
	   alert("Enter Alphabet Only");
		    return false;
		  }
    }