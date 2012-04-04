 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package play;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import javax.servlet.http.HttpSession;

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
    // -2 : ошбибка создания файла
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
                try
                {
                    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

                    //Корневой элемент
                    Document document = documentBuilder.newDocument();
                    Element rootElement = document.createElement("room");
                    document.appendChild(rootElement);

                    //добавляем название игры
                    Attr attr = document.createAttribute("roomName");
                    attr.setValue(roomName);
                    rootElement.setAttributeNode(attr);

                    //Child's корневого элемента
                    Element owners = document.createElement("owners"); //создатели
                    rootElement.appendChild(owners);

                    Element players = document.createElement("owners"); //игроки
                    rootElement.appendChild(players);



                    Element owner = document.createElement("owner");  //создатель
                    owners.appendChild(owner);
                    attr = document.createAttribute("name");         
                    attr.setValue(request.getSession(true).getAttribute("UserLogin").toString());
                    owner.setAttributeNode(attr);


                    //Теперь запишем контент в XML файл
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource domSource = new DOMSource(document);
                    StreamResult streamResult = new StreamResult(roomFile);

                    transformer.transform(domSource, streamResult);
                    System.out.println("Файл создан!!!");
                    
                    out.write("0");
                }
                catch (ParserConfigurationException pce)
                {
                    System.out.println(pce.getLocalizedMessage());
                    out.write("-2");
                    //pce.printStackTrace();
                }
                catch (TransformerException te)
                {
                    System.out.println(te.getLocalizedMessage());
                    out.write("-2");
                    //te.printStackTrace();
                }
                
                
                
                
                
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