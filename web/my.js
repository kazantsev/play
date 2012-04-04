/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function handleFocus(fieldId, labelId, defValue, isPassw){
    var field = document.getElementById(fieldId);
    if (field.value==defValue){
        field.value = '';
    }
    if (isPassw){
        field.type='password'; 
    }
    var label=document.getElementById(labelId);
    label.innerHTML=defValue;
    
}

function handleBlur(fieldId, labelId, defValue, isPassw){
    var field = document.getElementById(fieldId);
    var label=document.getElementById(labelId);
    if (field.value==''){
        if (isPassw){
            field.type = 'text';
        }        
        field.value = defValue
        label.innerHTML = '';
    } 
}

var req;

function showPlayers(roomname){
                    req = newXMLHttpRequest();
                    req.onreadystatechange = getReadyStateHandler(req, getExecResults1);
                    req.open("POST","GetRoomMembers", true);
                    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    req.send("rn="+roomname);
            }
            
            function getExecResults1(){
                if(req.readyState==4){}
                   
            }
            
             function joinRoom(name){
                    req = newXMLHttpRequest();
                    req.onreadystatechange = getReadyStateHandler(req, getExecResults2);
                    req.open("POST","JoinRoom", true);
                    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    req.send("rn="+name);
            }
            
            function getExecResults2(){
                if(req.readyState==4){}
                alert ("joined");
               // document.joiner.joinres.value=req.responseText;
            }


