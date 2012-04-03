// Timestamp of cart that page was last updated with
var lastCartUpdate = 0;

/*
 * Adds the specified item to the shopping cart, via Ajax call
 * itemCode - product code of the item to add
 */
function sendRegReq() {
 var userName = document.getElementById("UserName");
 var userLogin = document.getElementById("UserLogin");
 var userPassw = document.getElementById("UserPassw");
 var userPassw1 = document.getElementById("UserPassw1");
 if (userPassw.value == userPassw1.value){
    var req = newXMLHttpRequest();
    req.onreadystatechange = getReadyStateHandler(req, getRegReqResults);
    req.open("POST", "register", true);
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send("UserName="+userName.value+"&UserLogin="+userLogin.value+"&UserPassw="+userPassw.value);
 } else {
    alert("Passwords does not match"); 
 }
}


/*
 * Update shopping-cart area of page to reflect contents of cart
 * described in XML document.
 */
function getRegReqResults(resXML) {

 var status = resXML.getElementsByTagName("Result")[0];
 var registered = status.getAttribute("registered");
 if (registered == "true") {
	 window.location = "test.jsp";
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
