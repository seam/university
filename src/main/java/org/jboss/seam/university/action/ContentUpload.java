package org.jboss.seam.university.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="contentUpload", urlPatterns={"/content/upload"})
public class ContentUpload extends HttpServlet {

    private static final long serialVersionUID = 7155080863520490227L;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println("Received upload request");        
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        
        InputStream in = request.getInputStream();
        byte[] buffer = new byte[8192];
        
        int read = in.read(buffer);
        while (read != -1) {
            os.write(buffer, 0, read);
            read = in.read(buffer);            
        }
        in.close();
        
        System.out.println("Processed file, size: " + os.size());

        response.getOutputStream().write("{success:true}".getBytes());
        response.getOutputStream().flush();
    }



}
