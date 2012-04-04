/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package play;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


 

/**
 *
 * @author typelol
 */
public class GetRoomMembers extends HttpServlet {

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
    
    //rn - название комнаты
    
//    <players>
//            <player name=""/>
//            <player name=""/>
//            <player name=""/>
//    </players>
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
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
            //переходим к нашей комнате
            
            
            
            FileReader f = new FileReader(homeDir+"/"+roomName+".xml");
            BufferedReader br = new BufferedReader(f);
            String output ="";
            while (br.ready()){
                output+=(br.readLine());
            }
            String resp=output.substring(output.indexOf("<players>"), output.indexOf("</players>")+10);
            out.write(resp);
 
            
            
            
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
