/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function sendJoinReq(userLogin, roomName){
    req.onreadystatechange = getReadyStateHandler(req, getLoginReqResults);
    req.open("POST", "JoinToRoom", true);
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send("UserLogin="+userLogin+"&RoomName="+roomName);
    
}