/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package play;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author typelol
 */
public class FileSystemWorker {
    
    public static String[] GetFilesInFolder(String dir,final String format){
         File folder = new File(dir); 
        
        String[] files = folder.list(new FilenameFilter() { 

            @Override public boolean accept(File folder, String name) { 
                return name.endsWith(format); 
            } 
            
        }); 
        
        return files;
    }
    
}
