package org.jboss.seam.university.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.solder.logging.Logger;

/**
 * Servlet that serves content resources and temporary files
 * 
 * @author Shane Bryzak
 *
 */
@WebServlet(name="contentResourceServlet", urlPatterns={"/content/resource/*"})
public class ContentResourceServlet extends HttpServlet {
    private static final long serialVersionUID = 2207482600528217448L;
    
    private static final String TEMP_PATH = "/temp/";
    
    @Inject TempFileManager fileManager;
    @Inject Logger log;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        String uri = request.getRequestURI();
        
        String resourcePath = uri.substring(uri.indexOf(servletPath) + servletPath.length());
        
        log.info("User requested resource [" + resourcePath + "]");
        
        // If the user is requesting a temporary file, use the TempFileManager to locate it
        if (resourcePath.startsWith(TEMP_PATH)) {
            String filename = resourcePath.substring(TEMP_PATH.length());
            
            File tempFile = fileManager.getFileByName(filename);
            if (tempFile == null) {
                response.sendError(404, "Resource not found");
            } else {
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = new FileInputStream(tempFile);
                    out = response.getOutputStream();
                    
                    byte[] buffer = new byte[8192];
                    int read = in.read(buffer);
                    while (read != -1) {
                        out.write(buffer, 0, read);
                        read = in.read(buffer);
                    }
                    
                } finally {
                  if (in != null) {
                      in.close();
                  }
                  
                  if (out != null) {
                      out.close();
                  }                    
                }
            }        
        } else {
        // Otherwise serve the resource from the JCR repository    
            
        }
    }
}
