package downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import org.apache.commons.io.FilenameUtils;

public class DownloadEngine {
    private int duplicateCounter = 1;
    
    public DownloadEngine()
    {
        
    }
    
    public void getFile(URL url)
    {
        new Debug("DownloadEngine: getFile active with" + url);
        try
        {
            String fileName = FilenameUtils.getName(url.getPath());
            fileName = handleDuplicates(fileName);
            ReadableByteChannel rBC = Channels.newChannel(url.openStream());
            FileOutputStream fOS = new FileOutputStream(Main.directory + fileName);
            FileChannel fC = fOS.getChannel();
            fOS.getChannel().transferFrom(rBC, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            System.out.println("IOException:\n");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private String handleDuplicates(String file)
    {
        if(new File(Main.directory + file).isFile())
        {
            int extension = file.lastIndexOf('.');
            if(duplicateCounter>1)
                file = file.substring(0,extension-1) + duplicateCounter + file.substring(extension);
            else
                file = file.substring(0,extension) + duplicateCounter + file.substring(extension);
            ++duplicateCounter;
            return handleDuplicates(file);
        }else{
            duplicateCounter = 1;
            return file;
        }
    }

}
