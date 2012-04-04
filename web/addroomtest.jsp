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
            
        </script>
        
        <title>Room Test</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form name="mainform">
        <input type="button" onClick="addRoom('newRoom');"/>
        <input name="reqres"/>
        </form>
        
    </body>
</html>
