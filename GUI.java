package downloader;

import javax.swing.JFileChooser;

public class GUI {
    public static final String DEFAULT_TXT = "Enter Download URL";
    private Window window;
    private Main main;
    
    public GUI(Main main)
    {
        this.main = main;
    }
    
    void addAction()
    {
        if (!isTxtUrlBlank() && !isTxtUrlDefault() && !main.urlList.isFull())
        {
            main.urlList.addToList(getUrlTxt());
            setUrlTxt("");
        }
    }
    
    void resetAction()
    {
        setUrlTxt(DEFAULT_TXT);
    }
    
    void removeSelectedAction()
    {
        main.urlList.removeSelectedFromList();
    }
    
    void clearAllAction()
    {
        main.urlList.removeAllFromList();
    }
    
    void txtUrlFocusLost()
    {
        if (isTxtUrlBlank())
        {
            setUrlTxt(DEFAULT_TXT);
        }
    }
    
    void txtUrlFocusGained()
    {
        setUrlTxt("");
    }
    
    String getUrlTxt()
    {
        return main.window.txtURL.getText();
    }
    
    void setUrlTxt(String string)
    {
        main.window.txtURL.setText(string);
    }
    
    void setDirectory()
    {
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new java.io.File(Main.directory));
        jfc.setDialogTitle("Select Download Directory...");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setAcceptAllFileFilterUsed(false);

        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
          Main.directory = jfc.getSelectedFile().toString() + "\\";
        }
    }
    
    void setDownloadTime()
    {
        
    }
    
    boolean isTxtUrlBlank()
    {
        if(getUrlTxt().equals(""))
            return true;
        else
            return false;
    }
    
    boolean isTxtUrlDefault()
    {
        if(getUrlTxt().equals(DEFAULT_TXT))
            return true;
        else
            return false;
    }
}
