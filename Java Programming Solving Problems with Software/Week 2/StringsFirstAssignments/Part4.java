import edu.duke.*;
import java.util.*;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public ArrayList<String> read(String link)
    {
        ArrayList<String> ans=new ArrayList<String>();
        String utube="youtube.com";
        URLResource url=new URLResource(link);
        for (String word : url.words())
        {
            
            if(word.toLowerCase().indexOf(utube)!=-1)
            {
                String s;
                int index=word.indexOf("\"");
                s=word.substring(index+1, word.indexOf("\"",index+1));
                ans.add(s);
                System.out.println(s);
            }
        }
        return ans;
    }
    public void test()
    {
        read("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }
}
