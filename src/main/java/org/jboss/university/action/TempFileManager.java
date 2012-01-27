package org.jboss.university.action;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.SessionScoped;

/**
 * Session scoped file management for temporary uploaded files before they get placed
 * in the JCR repository
 * 
 * @author Shane Bryzak
 *
 */
public @SessionScoped class TempFileManager implements Serializable {
    private static final long serialVersionUID = 6611578461800877723L;

    private Map<String,File> tempFiles = new HashMap<String,File>();
    
    public void addTempFile(String filename, File file) {
        // If a file with the same name has already been uploaded, delete it
        if (tempFiles.containsKey(filename)) {
            removeFileByName(filename);
        }
        
        tempFiles.put(filename, file);
    }
    
    public Set<String> getFilenames() {
        return tempFiles.keySet();
    }
    
    public File getFileByName(String filename) {
        return tempFiles.get(filename);
    }
    
    public void removeFileByName(String filename) {
        tempFiles.get(filename).delete();
        tempFiles.remove(filename);
    }
}
