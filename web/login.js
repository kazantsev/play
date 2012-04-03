function sendLoginReq() {

 var userLogin = document.getElementById("UserLogin");
 var userPassw = document.getElementById("UserPassw");
	var req = newXMLHttpRequest();
	req.onreadystatechange = getReadyStateHandler(req, getLoginReqResults);
	req.open("POST", "login", true);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	req.send("UserLogin="+userLogin.value+"&UserPassw="+userPassw.value);

}


/*
 * Update shopping-cart area of page to reflect contents of cart
 * described in XML document.
 */
function getLoginReqResults(resXML) {

 var status = resXML.getElementsByTagName("Result")[0];
 var loggedin = status.getAttribute("loggedin");
 if (loggedin == "true") {
	 alert ("successfully logged in");
 } else {
	 alert ("error!");
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