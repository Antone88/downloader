package downloader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static String user = System.getProperty("user.name");
    public static String directory = "C:/Users/" + user + "/Downloads/";

    GUI gui;
    Clock clock;
    UrlList urlList;
    DownloadEngine downloadEngine;
    Window window;
    
    public Main()
    {
        gui = new GUI(this);    
        clock = new Clock(this);
        urlList = new UrlList(this);
        downloadEngine = new DownloadEngine();
        window = new Window(this);
        window.setVisible(true);
    }
    
    public static void main(String args[])
    {
        Main main = new Main();
    }
}
