/**
 * Write a description of CSVMax here.
 * 
 * @author ahmedfci93 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class CSVMax {
    String path="";
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value)
    {
        
        int cnt=0;
        double sum=0.0;
        for(CSVRecord currParser: parser)
        {
            if(Integer.parseInt(currParser.get("Humidity"))>=value)
            {
                cnt++;
                sum+=Double.parseDouble(currParser.get("TemperatureF"));
            }
        }
        if(cnt==0)return 0;
        return sum/cnt;
    }
    public double averageTemperatureInFile(CSVParser parser)
    {
        int cnt=0;
        double sum=0.0;
        for(CSVRecord currParser: parser)
        {
            cnt++;
            sum+=Double.parseDouble(currParser.get("TemperatureF"));
        }
        return sum/cnt;
    }
    public CSVRecord minHumidityInFile(CSVRecord min,CSVRecord currParser)
    {
        if(currParser.get("Humidity").equals("N/A"))return null;
        if(min==null)min=currParser;
        else
        {
            Integer lowest=Integer.parseInt(min.get("Humidity"));
            Integer currHumidity=Integer.parseInt(currParser.get("Humidity"));
            if(lowest>currHumidity) min=currParser;
        }
        return min;
    }
    public CSVRecord lowestHumidityInManyFiles()
    {
        DirectoryResource dr= new DirectoryResource();
        CSVRecord fMin=null;
        for(File f : dr.selectedFiles())
        {
            FileResource fr=new FileResource(f);
            CSVParser parser=fr.getCSVParser();
            CSVRecord min=null;
            for(CSVRecord currParser: parser)
            {
                min=minHumidityInFile(min,currParser);
            }
            if(fMin==null)
            {
                fMin=min;
            }
            else
            {
                double lowest=Integer.parseInt(min.get("Humidity"));
                double fHum=Integer.parseInt(fMin.get("Humidity"));
                if(lowest<fHum)
                {
                    fMin=min;
                }
            }
        }
        return fMin;
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        CSVRecord min=null;
        for(CSVRecord currParser: parser)
        {
            min=minHumidityInFile(min,currParser);
        }
        
        return min;
    }
    
    public CSVRecord ColdestTemperature(CSVRecord min,CSVRecord currParser)
    {
        if(min==null)min=currParser;
        else
        {
            if(min.get("TemperatureF").equals("-9999") || currParser.get("TemperatureF").equals("-9999"))return null;
            double lowest=Double.parseDouble(min.get("TemperatureF"));
            double currTemp=Double.parseDouble(currParser.get("TemperatureF"));
            if(lowest>currTemp) min=currParser;
        }
        return min;
    }
    public String fileWithColdestTemperature()
    {
        DirectoryResource dr= new DirectoryResource();
        String fileName=null;
        CSVRecord fMin=null;
        for(File f : dr.selectedFiles())
        {
            FileResource fr=new FileResource(f);
            CSVParser parser=fr.getCSVParser();
            CSVRecord min=null;
            for(CSVRecord currParser: parser)
            {
                min=ColdestTemperature(min,currParser);
            }
            if(fMin==null)
            {
                fMin=min;
                fileName=f.getName();
                path=f.getPath();
            }
            else
            {
                double lowest=Double.parseDouble(min.get("TemperatureF"));
                double fTemp=Double.parseDouble(fMin.get("TemperatureF"));
                if(lowest<fTemp)
                {
                    fMin=min;
                    fileName=f.getName();
                    path=f.getPath();
                }
            }
        }
        return fileName;
    }
    public CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord min=null;
        for(CSVRecord currParser: parser)
        {
            min=ColdestTemperature(min,currParser);
        }
        return min;
    }
    public void testAverageTemperatureWithHighHumidityInFile()
    {
        int value=80;
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg=averageTemperatureWithHighHumidityInFile(parser,value);
        if(avg==0)System.out.println("No temperatures with that humidity");
        else System.out.println("Average Temp when high Humidity is "+avg);
    }
    public void testAverageTemperatureInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg=averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is "+avg);
    }
    public void testLowestHumidityInManyFiles()
    {
        CSVRecord test=lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+test.get("Humidity")+" at "+ test.get("DateUTC"));
    }
    public void testLowestHumidityInFile() 
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+ csv.get("DateUTC"));
    }
    public void testFileWithColdestTemperature()
    {
        String fileWithColdestTemp=fileWithColdestTemperature();
        System.out.println("Coldest day was in file "+fileWithColdestTemp);
        //path/filename
        FileResource fr=new FileResource(path);
        CSVParser parser=fr.getCSVParser();
        CSVRecord test = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was "+test.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were: ");
        fr=new FileResource(path);
        parser=fr.getCSVParser();
        for(CSVRecord temp: parser)
        {
            System.out.println(temp.get("DateUTC")+" "+temp.get("TimeEST")+": "+temp.get("TemperatureF"));
        }
    }
    public void testColdestHourInFile()
    {
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        CSVRecord test = coldestHourInFile(parser);
        System.out.println("the low temp is "+test.get("TemperatureF")+" on "+test.get("DateUTC"));
    }
}
