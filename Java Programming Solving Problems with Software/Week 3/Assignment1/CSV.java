
/**
 * Write a description of CSV here.
 * 
 * @author ahmedfci93 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class CSV {
    public void test3() {
   
      // comparing str1 and str2
       String s1="$999,999,999";  
       String s2="$989,999,999";  
       String s3="$1,999,999,999";  
       System.out.println(s1.compareTo(s2));//0  
       //System.out.println(s1.compareTo(s3));//1(because s1>s3)  
       System.out.println(s3.compareTo(s2));
   }
    public void bigExporters(CSVParser parser,String amount)
    {
        for(CSVRecord prData: parser)
        {
            if(prData.get("Value (dollars)").compareTo(amount)>0 || prData.get("Value (dollars)").length()>amount.length())
            {
                System.out.println(prData.get("Country")+" "+prData.get("Value (dollars)"));
            }
        }
    }
    public int numberOfExporters(CSVParser parser,String exportItem)
    {
        int cnt=0;
        for(CSVRecord prData: parser)
        {
            if(prData.get("Exports").contains(exportItem))
            {
                cnt++;
            }
        }
        return cnt;
    }
    public void listExportersTwoProducts(CSVParser parser,String exportItem1 ,String exportItem2)
    {
        for(CSVRecord prData: parser)
        {
            if(prData.get("Exports").contains(exportItem1)&&prData.get("Exports").contains(exportItem2))
            {
                System.out.println(prData.get("Country"));
            }
        }
    }
    public String countryInfo(CSVParser parser,String country)
    {
        for(CSVRecord prData: parser)
        {
            if(prData.get("Country").equals(country))
            {
                return country +": "+prData.get("Exports")+": "+prData.get("Value (dollars)");
            }
        }
        return "Not Found";
    }
    public void tester()
    {
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        //System.out.println(countryInfo(parser,"Nauru"));
        //listExportersTwoProducts(parser,"cotton","flowers");
        //System.out.println(numberOfExporters(parser,"cocoa"));
        bigExporters(parser,"$999,999,999,999");
        /*for(CSVRecord data: parser)
        {
            System.out.println(data.get("Country"));
        }*/
    }
    
}
