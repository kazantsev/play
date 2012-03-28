// Timestamp of cart that page was last updated with
var lastCartUpdate = 0;

/*
 * Adds the specified item to the shopping cart, via Ajax call
 * itemCode - product code of the item to add
 */
function sendRegReq(queryNo, logFileName) {
 var userName = document.getElementById("UserName");
 var userLogin = document.getElementById("UserLogin");
 var userPassw = document.getElementById("UserPassw");
 var userPassw1 = document.getElementById("UserPassw1");
 if (userPassw == userPassw1){
    var req = newXMLHttpRequest();
    req.onreadystatechange = getReadyStateHandler(req, getExecResults);
    req.open("POST", "register", true);
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send("UserName="+userName+"&UserLogin="+userLogin+"&UserPassw="+userPassw);
 } else {
    alert("Passwords does not match"); 
 }
}


/*
 * Update shopping-cart area of page to reflect contents of cart
 * described in XML document.
 */
function getResults(resXML) {
 var goButton = document.getElementById("goButton");
 goButton.disabled = false;
 var viewButton = document.getElementById("viewButton");
 viewButton.disabled = false;
 var newButton = document.getElementById("newButton");
 newButton.disabled = false;
 var deleteButton = document.getElementById("deleteButton");
 deleteButton.disabled = false;
 var editButton = document.getElementById("editButton");
 editButton.disabled = false;
 var divInfo = document.getElementById("info");
 var status = resXML.getElementsByTagName("Status")[0];
 var error = status.getAttribute("error");
 if (error == null) {
	 divInfo.innerHTML = "Обработка завершена."
	 var time = status.getAttribute("time");
	 var matches = status.getAttribute("matches");
	 var dismatches1 = status.getAttribute("dismatches1");
	 var dismatches2 = status.getAttribute("dismatches2");
	 var partialDismatches = status.getAttribute("partialDismatches");
	 //var dismatches1El = status.firstChild.nodeValue("dismatches1");
	 var dismatches1Rep = status.getAttribute("dismatches1Report");
	 //var dismatches2El = status.getElementById("dismatches2");
	 var dismatches2Rep = status.getAttribute("dismatches2Report");
	 //var partialDismatchesEl = status.getElementById("partialDismatches");
	 var partialDismatchesRep = status.getAttribute("partialDismatchesReport");
	 //var passed = status.getAttribute("passed"); */

	 var divMatches = document.getElementById("matches");
	 divMatches.innerHTML = "Полных совпадений: " + matches;
	 var divDismatches1 = document.getElementById("dismatches1");
	 if (dismatches1Rep != "null"){  
			divDismatches1.innerHTML = "Несовпадений в первой выборке: " + dismatches1 + " <a target=\"_blank\"  href=\"" + dismatches1Rep + "\">Посмотреть</a>";
	 } else {
			divDismatches1.innerHTML = "Несовпадений в первой выборке: " + dismatches1;
	 }
	 var divDismatches2 = document.getElementById("dismatches2");
	 if (dismatches2Rep != "null"){  
			divDismatches2.innerHTML = "Несовпадений в первой выборке: " + dismatches2 + " <a target=\"_blank\" href=\"" + dismatches2Rep + "\">Посмотреть</a>";
	 } else {
			divDismatches2.innerHTML = "Несовпадений в первой выборке: " + dismatches2;
	 }
	 var divPartialDismatches = document.getElementById("partialDismatches");
	 if (partialDismatches != "null"){  
			divPartialDismatches.innerHTML = "Частичных совпадений: " + partialDismatches + " <a target=\"_blank\" href=\"" + partialDismatchesRep + "\">Посмотреть</a>";
	 } else {
			divPartialDismatches.innerHTML = "Частичных совпадений: " + partialDismatches;
	 }
 } else {
	 divInfo.innerHTML = "Во время обработки данных произошла ошибка " + status.getAttribute("errortype") +  ": " + status.getAttribute("message");
	 
 }
/* var divDismatches1Rep = document.getElementById("dismatches1Report");
 divDismatches1Rep.innerHTML = dismatches1Rep;
 var divDismatches2Rep = document.getElementById("dismatches2Report");
 divDismatches2Rep.innerHTML = dismatches2Rep;
 var divPartialDismatchesRep = document.getElementById("partialDismatchesReport");
 divPartialDismatchesRep.innerHTML = partialDismatchesRep; */
 //var divprocessed = document.getElementById("processed");
 //var divtime = document.getElementById("time");
 //var divpassed = document.getElementById("passed");
 //divtime.innerHTML = time;
 //divworking.innerHTML = working;
 //divprocessed.innerHTML = processed;
 //setTimeout(sendReq, 1000)

 //divpassed.innerHTML = passed;
}
