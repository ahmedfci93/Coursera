
/**
 * Write a description of Part2 here.
 * 
 * @author ahmedfci93 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public int howMany(String a,String b)
    {
        int index=b.indexOf(a);
        int ans=0;
        while(index!=-1)
        {
            ans++;
            index=b.indexOf(a,a.length()+index);
        }
        return ans;
    }
    public void testHowMany()
    {
        String s="GAA";
        String s1="ATGAACGAATTGAATC";
        System.out.println("occurrence of stringa in stringb equal "+howMany(s,s1));
        s="AA";
        s1="ATAAAA";
        System.out.println("occurrence of stringa in stringb equal "+howMany(s,s1));
        s="AA";
        s1="ATAAA";
        System.out.println("occurrence of stringa in stringb equal "+howMany(s,s1));
        s="A";
        s1="ATAAAA";
        System.out.println("occurrence of stringa in stringb equal "+howMany(s,s1));
        s="AT";
        s1="ATAAAA";
        System.out.println("occurrence of stringa in stringb equal "+howMany(s,s1));
    }
}
