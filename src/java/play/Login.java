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
import org.ini4j.Wini;

/**
 *
 * @author Kazancev
 */
public class Login extends HttpServlet {

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
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		try {
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
			 if ( userLogins.indexOf(userLogin) != -1){
				 // Если есть, проверяем пароль
				 System.out.println("a");
				 System.out.println("UserPassw1 = " + ini.get(userLogin, "UserPassw") + ", " + ini.get(userLogin, "UserPassw").length());
				 System.out.println("UserPassw2 = " + userPassw + ", " + userPassw.length()) ;
				 if (ini.get(userLogin, "UserPassw").trim().equals(userPassw.trim())){
					 System.out.println("b");
					 HttpSession session = request.getSession(true);
					 session.setAttribute("UserLogin", userLogin);
					 StringBuffer xml = new StringBuffer();
					 xml.append("<?xml version=\"1.0\"?>\n");
					 xml.append("<Result loggedin=\"true\"/>");
					 String resXml = xml.toString();
					 response.setContentType("text/xml");
					 response.getWriter().write(resXml);
				 } else {
					 System.out.println("c");
				 }
			 } else {
				 System.out.println("d");
			 }
       
        
       // printPageStart(out);
        
        //Obtain the session object, create a new session if doesn't exist
        //HttpSession session = request.getSession(true);
        
        //Check if our session variable is set, if so, get the session variable value
        //which is an Integer object, and add one to the value.
        //If the value is not set, create an Integer object with the default value 1.
        //Add the variable to the session overwriting any possible present values.
       
        
        
       
        
        //printPageEnd(out);
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
