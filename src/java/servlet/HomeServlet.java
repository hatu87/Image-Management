/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author hatu
 */
public class HomeServlet extends HttpServlet {

    private Boolean isImageFile(Path filePath) {
        String[] supportedExtensions = {".jpg", ".png"};

        for (String extension : supportedExtensions) {
            if (filePath.toString().endsWith(extension)) {
                return true;
            }
        }

        return false;
    }

    private List<String> loadImages(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // this code only runs with java 8
        List<String> images = new ArrayList<String>();
        String absolutePath = request.getServletContext().getRealPath("/data");
        String relativePath = request.getContextPath() + "/data/";
        Path path = FileSystems.getDefault().getPath(absolutePath);

        Files.walk(path).forEach(filePath -> {
            if (Files.isRegularFile(filePath) && isImageFile(filePath)) {
                String imagePath = request.getContextPath() + "/data/" + filePath.getFileName();
                images.add(imagePath);
            }
        });

        return images;
        
    }
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        List<String> images = loadImages(request, response);
        request.setAttribute("images", images);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/home/index.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tempPath = "/temp";
        String absoluteTempPath = this.getServletContext().getRealPath(tempPath);
        String absoluteFilePath = this.getServletContext().getRealPath("/data");
        int maxFileSize = 50 * 1024;
        int maxMemSize = 4 * 1024;

        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(maxMemSize);
            File file = new File(absoluteTempPath);

            if (file == null) {
                // tao thu muc
            }

            factory.setRepository(file);
            ServletFileUpload upload = new ServletFileUpload(factory);

            //upload.setProgressListener(new MyProgressListener(out));
            List<FileItem> items = upload.parseRequest(request);

            if (items.size() > 0) {
                for (FileItem item : items) {
                    if (!item.isFormField()) {
                        if("images".equals(item.getFieldName())){
                            if(item.getName() != null && !item.getName().isEmpty()) {
                                file = new File(absoluteFilePath + "//" + item.getName());

                                item.write(file);
                            }
                        }
                    } else {
                        // this is where we handle other form field
                    }
                }
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }

            List<String> images = loadImages(request, response);
            
            request.setAttribute("images", images);
            request.setAttribute("error", "No file upload");
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/home/index.jsp");
            dispatcher.forward(request, response);            
        } catch (Exception ex) {
            System.err.println(ex);
        }
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
