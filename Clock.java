package downloader;

import java.time.LocalTime;

public class Clock extends Thread
{
    LocalTime startTime = LocalTime.of(2,0);
    LocalTime endTime = LocalTime.of(8,0);
    LocalTime now;
    LocalTime prefStartTime;
    LocalTime prefEndTime;
    boolean running = true;
    Main main;
    
    public Clock(Main main)
    {
        this.main = main;
        this.start();
    }
    
    public void setStartTime(int time)
    {
        startTime = LocalTime.of(time,0);
    }
    
    public void run() 
    {
        while(running)
        {
            if(!isDownloadTime())
            {
                new Debug("Clock: Not time to download. Sleeping...");
                try
                {
                Thread.sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            } else {
                new Debug("Clock: afterStartBeforeEnd == " + isDownloadTime());
                new Debug("Clock: Requested download from mail.urlList");
                main.urlList.download();
                try
                {
                Thread.sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
                
        }
       
    }
    
    public boolean isDownloadTime()
    {
        now = LocalTime.now();
        int compareStart = now.compareTo(startTime);
        int compareEnd = now.compareTo(endTime);
        boolean afterStartBeforeEnd = compareStart == 1 && compareEnd == -1;
        return afterStartBeforeEnd;
    }
}