/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package play;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.ini4j.*;
import org.ini4j.Profile.Section;

/**
 *
 * @author kazantsev
 */
public class Register extends HttpServlet {

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
	
	
	/* Сервлет, реализующий регистрацию. */
	
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
					  // получаем данные пользователя из запроса
            String userName = request.getParameter("UserName");
            String userLogin = request.getParameter("UserLogin");
            String userPassw = request.getParameter("UserPassw");
						// получаем расположение домашней папки
						Properties pr = System.getProperties();
						String homeDir = pr.getProperty("user.dir");
						homeDir = homeDir.substring(0, homeDir.indexOf("/config"));
						homeDir += "/applications/play";
						// Открываем ini-файл с рег. данными
            File file = new File(homeDir + "/RegInfo.ini");
            Wini ini = new Wini(file);
						// Проверяем, есть ли пользователь с таким логином
						// Получаем список всех зарег. пользователей
						ArrayList<String> userLogins = new ArrayList<String>();
						userLogins.clear();
						Object[] arr = ini.keySet().toArray();
						for (int i=0; i<ini.size(); i++){
							userLogins.add(arr[i].toString());
						}
						// Проверяем, есть ли указанный логин в списке пользователей.
						if ( userLogins.indexOf(userLogin) == -1){
							// Такого пользователя нет, добавляем.
							ini.put(userLogin, "UserName", userName);
							ini.put(userLogin, "UserLogin", userLogin);
							ini.put(userLogin, "UserPassw", userPassw);
							ini.store();
							// Возвращаем ответ, что пользователь добавлен.
							StringBuffer xml = new StringBuffer();
							xml.append("<?xml version=\"1.0\"?>\n");
							xml.append("<Result registered=\"true\"/>");
							HttpSession session = request.getSession(true);
							session.setAttribute("UserLogin", userLogin);
						  session.setAttribute("UserName", userName);
							String resXml = xml.toString();
							response.setContentType("text/xml");
							response.getWriter().write(resXml);
						} else {
							// Пользователь с таким логином есть
							StringBuffer xml = new StringBuffer();
							xml.append("<?xml version=\"1.0\"?>\n");
							xml.append("<Result registered=\"false\"/>");
							String resXml = xml.toString();
							response.setContentType("text/xml");
							response.getWriter().write(resXml);
						}
      /*      out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Register</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
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
