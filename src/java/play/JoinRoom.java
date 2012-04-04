/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package play;


import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author typelol
 */
public class JoinRoom extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    
    //rn  - комната
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            //получаем пользователя
            String login= "";
            if(request.getSession(true).getAttribute("UserLogin")==null || request.getSession(true).getAttribute("UserLogin").toString().equals(""))
                        login = "lolik";
                    else
                        login = request.getSession(true).getAttribute("UserLogin").toString();
                    
            //получаем комнату
            String roomName = request.getParameter("rn");
            
            String homeDir = System.getProperties().getProperty("user.dir");
            System.out.println(homeDir);
            homeDir = homeDir.substring(0, homeDir.indexOf("/config"));
            System.out.println(homeDir);
            homeDir += "/applications/play";
            System.out.println(homeDir);
            
            //переходим к комнатам /rooms
            homeDir+="/rooms";
            System.out.println(homeDir);
            
            File room = new File(homeDir+"/"+roomName+".xml");
            try
            {
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(room);

                //Получаем корневой элемент
                Node root = document.getFirstChild();

                //Получаем элемент blog по имени тега
                Node players = document.getElementsByTagName("players").item(0);

                

                //Добавим новый элемент
                Element site = document.createElement("player");
                site.setAttribute("name", login);
                players.appendChild(site);

                
                //Запишем содержимое в xml файл
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(room);
                transformer.transform(domSource, streamResult);
                
                out.write("0");
            }
            catch (ParserConfigurationException pce)
            {
                System.out.println(pce.getLocalizedMessage());
                pce.printStackTrace();
            }
            catch (TransformerException te)
            {
                System.out.println(te.getLocalizedMessage());
                te.printStackTrace();
            }
            catch (IOException ioe)
            {
                System.out.println(ioe.getLocalizedMessage());
                ioe.printStackTrace();
            }
            catch (SAXException sae)
            {
                System.out.println(sae.getLocalizedMessage());
                sae.printStackTrace();
            }
            
            
            
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}