
	function getAmt(){
		const amtList = document.getElementsByName("subAmt");
		var totAmt = 0;

		for(let i=0;i<amtList.length; i++){
				var price = Number(amtList[i].value);
				totAmt = totAmt + price;
		}


	//	document.getElementById("totAmount").innerHTML  = totAmt;
		return totAmt ;
	}

	function getPoints(){
		const ptsList = document.getElementsByName("subPoint");
		var totPoint = 0;

		for(let i=0;i<ptsList.length; i++){
				var point = Number(ptsList[i].value);
			totPoint = totPoint + point;
		}

		document.getElementById("totPoint").innerHTML  = totPoint;
		return totPoint;
	}

function printData()
{
   var divToPrint=document.getElementById("printTable");
   newWin= window.open("");
   newWin.document.write(divToPrint.outerHTML);
   newWin.print();
   newWin.close();
}

