<%-- 
    Document   : addroomtest
    Created on : Apr 4, 2012, 11:52:14 PM
    Author     : typelol
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="ajax.js"></script>
        <script>
            var req;
            function addRoom(_str){
                                        
                    req = newXMLHttpRequest();
                    req.onreadystatechange = getReadyStateHandler(req, getExecResults);
                    req.open("POST","CreateRoom", true);
                    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    req.send("rn="+_str);
                    
               
            }

            function getExecResults(){
                if(req.readyState==4)
                document.mainform.reqres.value=req.responseText;
            }
            
            
            function showPlayers(roomname){
                    req = newXMLHttpRequest();
                    req.onreadystatechange = getReadyStateHandler(req, getExecResults1);
                    req.open("POST","GetRoomMembers", true);
                    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    req.send("rn="+roomname);
            }
            
            function getExecResults1(){
                if(req.readyState==4)
                document.players.playres.value=req.responseText;
            }
            
            function joinRoom(name){
                    req = newXMLHttpRequest();
                    req.onreadystatechange = getReadyStateHandler(req, getExecResults2);
                    req.open("POST","JoinRoom", true);
                    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    req.send("rn="+name);
            }
            
            function getExecResults2(){
                if(req.readyState==4)
                document.joiner.joinres.value=req.responseText;
            }
            
        </script>
        
        <title>Room Test</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form name="mainform">
            <input name="roomName"/>
            <input type="button" onClick="addRoom(roomName.value);" value="Добавить комнату"/>
            <br>
        <input name="reqres"/>
        </form>
        
        <br><br><br>
        <form name="players">
            <input name="roomName"/>
            <input type="button" onclick="showPlayers(roomName.value);" value="Показать парней"/>
            <br>
            <input name="playres"/>
            
        </form>
        
        <br><br><br>
        
        
        <form name="joiner">
            <input name="roomName"/>
            <input type="button" onclick="joinRoom(roomName.value);" value="Зайти в комнату"/>
            <br>
            <input name="joinres"/>
        
        </form>
       
        
    </body>
</html>
