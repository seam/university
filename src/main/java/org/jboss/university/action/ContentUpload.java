package org.jboss.university.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.server.UID;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that handles file uploads and places uploaded files into the TemporaryFile table
 * 
 * @author Shane Bryzak
 */
@WebServlet(name="contentUpload", urlPatterns={"/content/upload"})
public class ContentUpload extends HttpServlet {

    private static final long serialVersionUID = 7155080863520490227L;
    
    private static final byte[] RESULT_SUCCESS = "{success:true}".getBytes();
    private static final byte[] RESULT_FAILURE = "{success:false}".getBytes();
    
    @Inject TempFileManager fileManager;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String fileName = request.getParameter("qqfile");
        InputStream in = null;
        OutputStream os = null;
        
        try {
            File tempFile = File.createTempFile(new UID().toString().replace(":", "-"), ".upload");
            tempFile.deleteOnExit();
            os = new FileOutputStream(tempFile);
            
            in = request.getInputStream();
            byte[] buffer = new byte[8192];
            
            int read = in.read(buffer);
            while (read != -1) {
                os.write(buffer, 0, read);
                read = in.read(buffer);            
            }
            
            os.flush();
            
            fileManager.addTempFile(fileName, tempFile);
            
            response.getOutputStream().write(RESULT_SUCCESS);
            response.getOutputStream().flush();
        }
        catch (IOException ex) {
            response.getOutputStream().write(RESULT_FAILURE);
            response.getOutputStream().flush();
        }
        finally {
            if (in != null) {
                in.close();
            }
            
            if (os != null) {                
                os.close();
            }
        }       
    }
}
