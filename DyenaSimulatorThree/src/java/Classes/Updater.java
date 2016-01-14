/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Classes.Parser.Line;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Cypher
 */
public class Updater implements Runnable
{

    private Parser parser;
    private Message localMessage = new Message();
    private boolean doneLoading = false;

    public Updater() throws IOException
    {
        parser = new Parser();
        doneLoading = true;
    }

    @Override
    public void run()
    {
        int counter = 0;

        double[] heave, pitch, roll, heading, ax, ay, az;

        heave = new double[25];
        pitch = new double[25];
        roll = new double[25];
        heading = new double[25];
        ax = new double[25];
        ay = new double[25];
        az = new double[25];

        while (true)
        {
            Line line = parser.getLine();

            heave[counter] = line.getHeave();
            pitch[counter] = line.getPitch();
            roll[counter] = line.getRoll();
            heading[counter] = line.getHeading();
            ax[counter] = line.getAccelerationX();
            ay[counter] = line.getAccelerationY();
            az[counter] = line.getAccelerationZ();

            if (counter++ == 24)
            {
                synchronized(localMessage)
                {
                    localMessage = new Message(line, 9999999, heave, pitch, roll, heading, ax, ay, az);
                }
                counter = 0;
                System.gc();
            }
            try
            {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public synchronized Message getMessage()
    {
        return localMessage;
    }

    public int getNum()
    {
        return parser.getSize();
    }
    
    public boolean doneLoading()
    {
        return doneLoading;
    }
}
