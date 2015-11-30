/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Classes.Parser.Line;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Cypher
 */
public class Updater implements Runnable
{

    private Parser parser;
    private Message localMessage = new Message();

    public Updater() throws IOException
    {
        parser = new Parser();
    }

    @Override
    public void run()
    {
        int counter = 0;

        ArrayList<Double> heave, pitch, roll, heading, ax, ay, az;

        heave = new ArrayList<>();
        pitch = new ArrayList<>();
        roll = new ArrayList<>();
        heading = new ArrayList<>();
        ax = new ArrayList<>();
        ay = new ArrayList<>();
        az = new ArrayList<>();

        while (true)
        {
            Line line = parser.getLine();

            heave.add(new Double(line.getHeave()));
            pitch.add(new Double(line.getPitch()));
            roll.add(new Double(line.getRoll()));
            heading.add(new Double(line.getRoll()));
            ax.add(new Double(line.getAccelerationX()));
            ay.add(new Double(line.getAccelerationY()));
            az.add(new Double(line.getAccelerationZ()));

            if (counter++ == 24)
            {
                localMessage = new Message(line, 9999999, heave, pitch, roll, heading, ax, ay, az);
//                heave.stream().forEach((d) ->
//                {
//                    System.out.print("Updater "+ d + " ");
//                });
//                System.out.println("");
                //System.out.println("Sizes Before: " + heave.size());
                heave.clear();
                pitch.clear();
                roll.clear();
                heading.clear();
                ax.clear();
                ay.clear();
                az.clear();
                //System.out.println("Sizes After: " + heave.size());
                counter = 0;
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
    
    public Message getMessage()
    {
        //System.out.println(localMessage);
        return localMessage;
    }

    public int getNum()
    {
        return parser.getSize();
    }
    
    public boolean doneLoading()
    {
        return parser.isDoneLoading();
    }
}
