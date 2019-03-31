package downloader;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.DefaultListModel;
import static javax.swing.JOptionPane.showMessageDialog;

public class UrlList {
    DefaultListModel<String> listModel = new DefaultListModel<>();
    Main main;
    
    public UrlList(Main main)
    {
        this.main = main;
    }
    
    public void download()
    {
        int listSize = listModel.getSize();
        new Debug("UrlList: listModel size: " + listSize);
        
        if(listSize > 0)
        {
            for(int i = (listSize-1); i >= 0; --i)
            {
                String s = getFromList(i);
                URL url = convertUrl(s);
                main.downloadEngine.getFile(url);
                listModel.remove(i);
            }
        }
    }
    
    public boolean isFull()
    {
        /* Disabled
        if(this.listModel.getSize() < 5)
            return false;
        else
            return true;
        
        list can no longer be full.
        */
        return false;
    }
    
    public boolean isUrl(String urlString)
    {
        URL url = null;
        url = convertUrl(urlString);
        if(url != null)
            return true;
        else
            return false;
    }
    
    public URL convertUrl(String urlString)
    {
        URL url = null;
        try
        {
            url = new URL(urlString);
        } catch(MalformedURLException e){
            showMessageDialog(null, "Not a valid URL!");
        }
        return url;
    }
    
    private String getFromList(int i)
    {
        return listModel.elementAt(i);
    }
    
    public void addToList(String urlString)
    {
        if(isUrl(urlString))
            listModel.addElement(urlString);
    }
    
    public void removeSelectedFromList()
    {
        if(main.window.jList.getSelectedIndex() != -1)
            listModel.remove(main.window.jList.getSelectedIndex());
    }
    
    public void removeAllFromList()
    {
        listModel.removeAllElements();
    }
}
