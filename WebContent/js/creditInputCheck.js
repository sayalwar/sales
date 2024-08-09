	function check() {
		var message = "";
		var creditNo = document.getElementById("creditCardNo").value;
		if (creditNo.length == 0) {
			message = "<p>Credit Card Number is Required.</p>";
		}
		 else if (!creditNo.match(/^\d{16}$/)) {
			message = "<p>Credit Card Number is Invalid</p>";
		}
		if (!(creditNo.length == 16 && creditNo.length!=0)) {
			message = "<p>Credit card number should be 16 Digit.</p>";
		}
		if(message!=""){
			var targetDiv = document.getElementById("target");
			targetDiv.innerHTML = message;
			return false;
		}
		return true;
	}

	window.onload = function(){
		var chkForm = document.getElementById("chkForm");
		chkForm.onsubmit = check;
	};
	function onlyNumbers(evt)
    {
        var e = event || evt; // for trans-browser compatibility
        var charCode = e.which || e.keyCode;
   if(( charCode==8))
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