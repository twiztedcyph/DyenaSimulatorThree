/*
 * The class provides methods for reading data from a *.csv file, which contains a saved 
 * data feed from the Dyena RockBLOCK 2.0.
 */
package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Georgios and Vilius
 */
public class Parser
{

    DateTimeFormatter form = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss.SSS");

    //ArrayList for storring all the messages from the *.csv file
    private ArrayList<Line> lineList = new ArrayList<>();
    

    /**
     * iterationNumber keeps the number of the position of a Line object in the
 lineList ArrayList. It is the position of the next Line to be
     * returned
     */
    public int iterationNumber;
    
    private boolean doneLoading = false;

    public boolean isDoneLoading()
    {
        return doneLoading;
    }

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Parser() throws FileNotFoundException, IOException
    {
        File file = new File("C:\\Users\\Cypher\\Dropbox\\Java Projects\\VMMS_Parser\\CsvData160315_edit.csv");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        Line singleMessage;
        this.iterationNumber = 0;

        //ignoring the first line in the file
        reader.readLine();
        //reading all lines from a file one by one,
        //creating a Line object from strings that were receved after splitting
        //the lines separated by a comma and adding the Line to ListOfMessages array list
        while ((line = reader.readLine()) != null)
        {
            singleMessage = new Line(line.split(","));
            this.lineList.add(singleMessage);
        }
        doneLoading = true;
    }

    /**
     * @return the next Line after the previously returned Line form a
     * messageList If there were none returned, returns the first. If the last
     * was returned, returns the first.
     */
    public Line getLine()
    {
        Line dataToReturn;

        if (this.iterationNumber >= this.lineList.size() - 1)
        {
            this.iterationNumber = 0;
        }

        dataToReturn = this.lineList.get(this.iterationNumber);

        this.iterationNumber++;
        return dataToReturn;
    }
    
    public int getSize()
    {
        return this.lineList.size();
    }

    /**
     *
     * @return a list of messages
     */
    public ArrayList<Line> getListOfMessages()
    {
        return lineList;
    }

    /**
     * A Line class object keeps data of a single Line from the *.csv file
     */
    public class Line
    {

        private DateTime dateTime;
        private final float heave;
        private final float pitch;
        private final float roll;
        private final float heading;
        private final float heaveMin;
        private final float pitchMin;
        private final float rollMin;
        private final float heaveMax;
        private final float pitchMax;
        private final float rollMax;
        private final float accelerationX;
        private final float accelerationY;
        private final float accelerationZ;
        private final float heaveRMS;
        private final float pitchRMS;
        private final float rollRMS;
        private final float heaveTz;
        private final float pitchTz;
        private final float rollTz;
        private final float withinPresetLimits;
        private final float transferring;
        private final float latitude;
        private final float Longitude;

        /**
         *
         * @param stringLine is an array of strings which contains a single line
         * from the *.csv file
         */
        public Line(String[] stringLine)
        {

            this.dateTime = form.parseDateTime(stringLine[0] + " " + stringLine[1]);
            this.heave = Float.valueOf(stringLine[2]);
            this.pitch = Float.valueOf(stringLine[3]);
            this.roll = Float.valueOf(stringLine[4]);
            this.heading = Float.valueOf(stringLine[5]);
            this.heaveMin = Float.valueOf(stringLine[6]);
            this.pitchMin = Float.valueOf(stringLine[7]);
            this.rollMin = Float.valueOf(stringLine[8]);
            this.heaveMax = Float.valueOf(stringLine[9]);
            this.pitchMax = Float.valueOf(stringLine[10]);
            this.rollMax = Float.valueOf(stringLine[11]);
            this.accelerationX = Float.valueOf(stringLine[12]);
            this.accelerationY = Float.valueOf(stringLine[13]);
            this.accelerationZ = Float.valueOf(stringLine[14]);
            this.heaveRMS = Float.valueOf(stringLine[15]);
            this.pitchRMS = Float.valueOf(stringLine[16]);
            this.rollRMS = Float.valueOf(stringLine[17]);
            this.heaveTz = Float.valueOf(stringLine[18]);
            this.pitchTz = Float.valueOf(stringLine[19]);
            this.rollTz = Float.valueOf(stringLine[20]);
            this.withinPresetLimits = Float.valueOf(stringLine[21]);
            this.transferring = Float.valueOf(stringLine[22]);
            this.latitude = Float.valueOf(stringLine[23]);
            this.Longitude = Float.valueOf(stringLine[24]);
        }

        public DateTime getDateTime()
        {
            return dateTime;
        }

        public float getHeave()
        {
            return heave;
        }

        public float getPitch()
        {
            return pitch;
        }

        public float getRoll()
        {
            return roll;
        }

        public float getHeading()
        {
            return heading;
        }

        public float getHeaveMin()
        {
            return heaveMin;
        }

        public float getPitchMin()
        {
            return pitchMin;
        }

        public float getRollMin()
        {
            return rollMin;
        }

        public float getHeaveMax()
        {
            return heaveMax;
        }

        public float getPitchMax()
        {
            return pitchMax;
        }

        public float getRollMax()
        {
            return rollMax;
        }

        public float getAccelerationX()
        {
            return accelerationX;
        }

        public float getAccelerationY()
        {
            return accelerationY;
        }

        public float getAccelerationZ()
        {
            return accelerationZ;
        }

        public float getHeaveRMS()
        {
            return heaveRMS;
        }

        public float getPitchRMS()
        {
            return pitchRMS;
        }

        public float getRollRMS()
        {
            return rollRMS;
        }

        public float getHeaveTz()
        {
            return heaveTz;
        }

        public float getPitchTz()
        {
            return pitchTz;
        }

        public float getRollTz()
        {
            return rollTz;
        }

        public float getWithinPresetLimits()
        {
            return withinPresetLimits;
        }

        public float getTransferring()
        {
            return transferring;
        }

        public float getLatitude()
        {
            return latitude;
        }

        public float getLongitude()
        {
            return Longitude;
        }

        @Override
        public String toString()
        {
            return "Line{" + "dateTime=" + dateTime + ", heave=" + heave + ", "
                    + "\n pitch=" + pitch + ", roll=" + roll + ", "
                    + "\n heading=" + heading + ", heaveMin=" + heaveMin + ", "
                    + "\n pitchMin=" + pitchMin + ", rollMin=" + rollMin + ", "
                    + "\n heaveMax=" + heaveMax + ", pitchMax=" + pitchMax + ", "
                    + "\n rollMax=" + rollMax + ", "
                    + "\n accelerationX=" + accelerationX + ", "
                    + "\n accelerationY=" + accelerationY + ", "
                    + "\n accelerationZ=" + accelerationZ + ", "
                    + "\n heaveRMS=" + heaveRMS + ", pitchRMS=" + pitchRMS + ", "
                    + "\n rollRMS=" + rollRMS + ", heaveTz=" + heaveTz + ", "
                    + "\n pitchTz=" + pitchTz + ", rollTz=" + rollTz + ", "
                    + "\n withinPresetLimits=" + withinPresetLimits + ", "
                    + "\n transferring=" + transferring + ", "
                    + "\n latitude=" + latitude + ", Longitude=" + Longitude + '}';
        }

    }

}
