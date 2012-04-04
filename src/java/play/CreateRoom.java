/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package play;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ini4j.*;
import org.ini4j.Profile.Section;

/**
 *
 * @author typelol
 */
public class CreateRoom extends HttpServlet {

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
    
    
    //rn - имя комнаты
    
    // -1 : имя комнаты не уникально
    // 0  : комната создана
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            String roomName = request.getParameter("rn");
            
            // получаем расположение домашней папки
            String homeDir = System.getProperties().getProperty("user.dir");
            homeDir = homeDir.substring(0, homeDir.indexOf("/config"));
            homeDir += "/applications/play";
            
            //переходим к комнатам /rooms
            homeDir+="/rooms";
            
            //получаем список комнат
            String[] roomList = FileSystemWorker.GetFilesInFolder(homeDir, ".xml");
            roomList = FileSystemWorker.RemoveFormatFromFileList(roomList, ".xml");
            
            boolean nameAvaiable=true;
            for(String room : roomList){
                if (room == roomName){
                    //такая комната уже есть
                    nameAvaiable = false;
                    break;
                }
            }

            if(nameAvaiable){
                //создаем комнату
                File roomFile = new File(homeDir + "/"+roomName+".xml");
                Wini ini = new Wini(file);
            }else{
                //шлем парня нафиг
                out.write("-1");
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
