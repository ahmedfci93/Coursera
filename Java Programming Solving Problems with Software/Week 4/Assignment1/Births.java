
/**
 * Write a description of Births here.
 * 
 * @author ahmedfci93
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class Births {
    public CSVParser getCSVParser(int year)
    {
        String fileName="yob"+year+".csv";
        String path="E:/cv/Coursera/Java Programming Solving Problems with Software/Week 4/us_babynames_by_year/"+fileName;
        FileResource fr= new FileResource(path);
        return fr.getCSVParser(false);
    }
    public int getTotalBirthsRankedHigher(int year,String name,String gender)
    {
        int lowRank=getRank(year,name,gender)-1;
        int ans=0;
        CSVParser parser=getCSVParser(year);
        for(CSVRecord currParser : parser)
        {
            if(lowRank==0)
            {
                break;
            }
            ans+=Integer.parseInt(currParser.get(2));
            lowRank--;
        }
        return ans;
    }
    public double getAverageRank(String name,String gender)
    {
        DirectoryResource dr= new DirectoryResource();
        int cnt=0;
        int sum=0;
        for(File f : dr.selectedFiles())
        {
            cnt++;
            int rank=rankInFile(f,name,gender);
            if(rank!=-1)sum+=rank;
        }
        if(sum==0)return -1;
        return (double)sum/cnt;
    }
    public int rankInFile(File f,String name,String gender)
    {
            FileResource fr=new FileResource(f);
            CSVParser parser=fr.getCSVParser(false);
            int rank=0;
            for(CSVRecord currParser : parser)
            {
                if(currParser.get(1).equals(gender))
                {
                    rank++;
                    if(currParser.get(0).equals(name)) 
                        return rank;
                }
            }
            return -1;
    }
    public int yearOfHighestRank (String name,String gender)
    {
        DirectoryResource dr= new DirectoryResource();
        int high=-1;
        int year=-1;
        for(File f : dr.selectedFiles())
        {
            int highInFile=rankInFile(f,name,gender);
            if(highInFile!=-1)
            {
                String tmp;
                if(high==-1)
                {
                    high=highInFile;
                    tmp=f.getName();
                    year=Integer.parseInt(tmp.substring(3,7));
                }
                if(high>=highInFile)
                {
                    high=highInFile;
                    tmp=f.getName();
                    year=Integer.parseInt(tmp.substring(3,7));
                }
            }
        }
        return year;
    }
    public int getRank(int year,String name,String gender)
    {
        int rank=0;
        CSVParser parser=getCSVParser(year);
        for(CSVRecord currParser : parser)
        {
            if(currParser.get(1).equals(gender))
            {
                rank++;
                if(currParser.get(0).equals(name)) 
                    return rank;
            }
        }
        return -1;
    }
    public void testGetTotalBirthsRankedHigher()
    {
        //System.out.println("TotalBirthsRankedHigher "+getTotalBirthsRankedHigher(2012,"Ethan","M"));
        System.out.println("TotalBirthsRankedHigher "+getTotalBirthsRankedHigher(1990,"Emily","F"));
        System.out.println("TotalBirthsRankedHigher "+getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
    public void whatIsNameInYear(String name,int year,int newYear,String gender)
    {
        int rank=getRank(year,name,gender);
        String newName=getName(newYear,rank,gender);
        System.out.println(name+" born in "+year+" would be "+newName+" if she/he was born in "+newYear+".");
    }
    public String getName(int year,int rank,String gender)
    {
        CSVParser parser=getCSVParser(year);
        for(CSVRecord currParser : parser)
        {
            if(currParser.get(1).equals(gender))
            {
                rank--;
                if(rank==0) 
                    return currParser.get(0);
            }
        }
        return "NO NAME";
    }
    public void totalBirths(FileResource fr)
    {
        int totalBirth=0;
        int totalGirlsName=0;
        int totalBoysName=0;
        int totalNames;
        CSVParser parser=fr.getCSVParser(false);
        for(CSVRecord currParser : parser)
        {
            int num=Integer.parseInt(currParser.get(2));
            totalBirth+=num;
            if(currParser.get(1).equals("M")) totalBoysName++;
            else totalGirlsName++;
        }
        totalNames=totalGirlsName+totalBoysName;
        System.out.println("totalBirth equals "+totalBirth);
        System.out.println("totalGirlsName equals "+totalGirlsName);
        System.out.println("totalBoysName equals "+totalBoysName);
        System.out.println("totalNames equals "+totalNames);
    }
    public void testGetAverageRank()
    {
        //System.out.println("getAverageRank "+getAverageRank("Mason","M"));
        //System.out.println("getAverageRank "+getAverageRank("Jacob","M"));
        //System.out.println("getAverageRank "+getAverageRank("Susan","F"));
        System.out.println("getAverageRank "+getAverageRank("Robert","M"));
    }
    public void testYearOfHighestRank()
    {
        //System.out.println("yearOfHighestRank "+ yearOfHighestRank("Mason","M"));
        //System.out.println("yearOfHighestRank "+ yearOfHighestRank("Genevieve","M"));
        System.out.println("yearOfHighestRank "+ yearOfHighestRank("Mich","M"));
    }
    public void testWhatIsNameInYear()
    {
        whatIsNameInYear("Susan",1972,2014,"F");
        //whatIsNameInYear("Owen",1974,2014,"M");
    }
    public void testGetName()
    {
        //System.out.println(getName(2012,6,"M"));
        //System.out.println(getName(2012,1,"M"));
        //System.out.println(getName(1980,350,"F"));
        System.out.println(getName(1982,450,"M"));
    }
    public void testGetRank()
    {
       System.out.println(getRank(1971,"Frank","M"));
       //System.out.println(getRank(1960,"Emily","F"));
    }
    public void testTotalBirths()
    {
        FileResource fr= new FileResource();
        totalBirths(fr);
    }
}
