
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.regex.Pattern;
public class Part3 {
    public boolean twoOccurrences(String a,String b)
    {
        int cnt=0;
        int indexKey=b.indexOf(a,0);
        while(indexKey!=-1)
        {
            cnt++;
            indexKey=b.indexOf(a,indexKey+a.length());
        }
        return cnt>=2;
    }
    public String lastPart(String a,String b)
    {
        int index =b.indexOf(a);
        if(index!=-1)return b.substring(index+a.length());
        return b;
    }
    public void testing()
    {
        String s1,s2;
        s1="atg";
        s2="ctgtatgta";
        System.out.println(s1+" "+s2+" "+ twoOccurrences(s1, s2));
        s1="by";
        s2="A story by Abby Long";
        System.out.println(s1+" "+s2+" "+ twoOccurrences(s1, s2));
        s1="a";
        s2="banana";
        System.out.println(s1+" "+s2+" "+ twoOccurrences(s1, s2));
        s1="an";
        s2="banana";
        System.out.println(s1+" "+s2+" "+ lastPart(s1, s2));
        s1="zoo";
        s2="forest";
        System.out.println(s1+" "+s2+" "+ lastPart(s1, s2));
        
    }
}
