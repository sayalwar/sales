//メンバーIDとパスワードの入力チェック
function check() {
	//メッセージ
	var message = "";
	var password = document.getElementById("password").value;
	var pwConfirm = document.getElementById("password_confirm").value;
	var name = document.getElementById("membername").value;
	var address = document.getElementById("address").value;
	var phone = document.getElementById("phone").value;


//名前が未入力の場合
	if (name.length == 0) {
		message += "<p>NAME is required</p>";
	}
if(document.getElementById('female').checked) {
  //Male radio button is checked
}else if(document.getElementById('male').checked) {
  //Female radio button is checked
}else if(document.getElementById('notspecified').checked) {
  //Female radio button is checked
}else
{
message += "<p>GENDER is required</p>";
}
     //住所が未入力の場合
	if (address.length == 0) {
		message += "<p>ADDRESS is required</p>";
	}

	if (phone.length == 0) {
		message += "<p>TELEPHONE is required</p>";
	}
	if (phone.length != 12 && phone.length!= 0) {
		message += "<p>TELEPHONE should be 10 digit</p>";
	}

	//パスワードが未入力の場合
	if (password.length == 0) {
		message += "<p>PASSWORD is required</p>";
	}
	if (password.length  < 6 && password.length != 0) {
	message += "<p>Minimum 6 digit PASSWORD is required</p>";
	}
	//パスワード（確認用）が未入力の場合
	if (pwConfirm.length == 0) {
		message += "<p>PASSWORDCHECK is required</p>";
	}

	if(password!= pwConfirm){
	message += "<p>Please Enter Same Password and Password Check.</p>";
	}

	//メッセージが設定された場合、メッセージを表示し、送信中止
	if (message != "") {
		var targetDiv = document.getElementById("target");
		targetDiv.innerHTML = message;
		return false;
	}
	//送信継続
	return true;
}

//読み込み時
window.onload = function() {
	//submitイベントハンドラの設定
	var chkForm = document.getElementById("chkForm");
	chkForm.onsubmit = check;
};

function onlyNumbers(evt)
    {
	    var e = event || evt; // for trans-browser compatibility
	    var charCode = e.which || e.keyCode;

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