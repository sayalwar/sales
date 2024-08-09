//メードアドレスの入力チェック
function check() {
	//メッセージ
	var message = "";
	//メードアドレスの入力値
	var email = document.getElementById("email").value;
	//メードアドレスが未入力の場合
	if (email.length == 0) {
		message = "<p>Email Address is required. </p>";
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