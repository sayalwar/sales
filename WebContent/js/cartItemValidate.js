
	function quantityVal() {
		var message = "";

		const qtyList = document.getElementsByName("productQuantity");
		const stkList  = document.getElementsByName("productInvent");
		const prodList = document.getElementsByName("productId");
		const priceList = document.getElementsByName("productPrice");
		const pointList = document.getElementsByName("prodPoint");
		var targetDiv1 = document.getElementById("success");
		targetDiv1.innerHTML ="";

		for(let i=0;i<qtyList.length; i++){
				var quantity    = Number(qtyList[i].value);
				var totalStock = Number(stkList[i].value);
				var price = Number(priceList[i].value);
				var point = Number(pointList[i].value);

			                if(((qtyList[i].value).length)==0)
                {
                document.getElementsByName("productQuantity")[i].value = 1 ;
                message = "Quantity Quantity cannot be NULL for product ID "+prodList[i].value ;
                //return false;
                }else if (quantity<=0) {
                        document.getElementsByName("productQuantity")[i].value = 1 ;
                        message = "Quantity should be 1 or more than one for product ID "+prodList[i].value;
                }
                else if (totalStock < quantity) {
                    document.getElementsByName("productQuantity")[i].value = 1 ;
                    message = "Cannot increment the cart item due to inavailability for productID "+prodList[i].value ;
                }
		}
		for(let i=0;i<qtyList.length; i++){
				var quantity    = Number(qtyList[i].value);
				var totalStock = Number(stkList[i].value);
				var price = Number(priceList[i].value);
				var point = Number(pointList[i].value);
				document.getElementsByName("productAMt")[i].value = quantity*price ;
				document.getElementsByName("productPoints")[i].value = quantity*point;
				document.getElementById("totAmount").innerHTML = getAmt();
				document.getElementById("totPoint").innerHTML = getPoints();
		}

		var targetDiv = document.getElementById("target");
		targetDiv.innerHTML = message;
		if(message!=""){
		alert(message);
			return false;
		}
		//送信継続
		targetDiv1.innerHTML = "We have updated the order quantity in your shopping cart.";
		return true;
	}

	function getAmt(){
		const qtyList = document.getElementsByName("productQuantity");
		const priceList = document.getElementsByName("productPrice");
		var sum = 0;

		for(let i=0;i<qtyList.length; i++){
				var quantity    = Number(qtyList[i].value);
				var price = Number(priceList[i].value);
				sum = sum + (quantity*price);
		}

		return sum;
	}

	function getPoints(){
		const qtyList = document.getElementsByName("productQuantity");
		const pointList = document.getElementsByName("prodPoint");
		var sum = 0;

		for(let i=0;i<qtyList.length; i++){
				var quantity    = Number(qtyList[i].value);
				var point = Number(pointList[i].value);
				sum = sum + (quantity*point);
		}

		return sum;
	}

function check() {
var newmessage = "";
const memberName = document.getElementsByName("productQuantity");

	for(let i=0;i<memberName.length; i++){
				var quantity    = memberName[i].value;
				if (quantity.length == 0) {
						newmessage += "<p>Quantity  is required.</p>";
				}
		}

	if (newmessage != "") {
		//var targetDiv = document.getElementById("target");
		alert("Cannot empty");
		//targetDiv.innerHTML = newmessage;
		return false;
	}
	return true;
}
window.onload = function() {
	var shoppingCartForm = document.getElementById("shoppingcartid");
	shoppingCartForm.onsubmit = check;
};

