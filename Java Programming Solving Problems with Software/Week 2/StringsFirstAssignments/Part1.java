
/**
 * Write a description of Part1 here.
 * 
 * @author (ahmedfci93) 
 * @version (a version number or a date)
 */
import java.io.*;
public class Part1 {

    public String findSimpleGene(String dna)
    {
        String ans=null;
        int index1=dna.indexOf("ATG");
        if(index1==-1)return ans;
        int index2=dna.indexOf("TAA",index1+3);
        if(index2==-1)return ans;
        ans=dna.substring(index1, index2+3);
        if(ans.length()%3!=0) return null;
        return ans;
    }
    public void testSimpleGene()
    {
        String s1=new String("ATCGTACTCSGGGTTAATTAA");
        String s2=new String("ATGGTATGCTCSGGGTATA");
        String s3=new String("ATCGTACTCSGGGTTATAT");
        String s4=new String("ATGGTACTCSGGAGTTAATTAA");
        String s5=new String("ATCGTACTCSGGGTTAATTAA");
        System.out.println("DNA String : "+s1+" GENE "+findSimpleGene(s1));
        System.out.println("DNA String : "+s2+" GENE "+findSimpleGene(s2));
        System.out.println("DNA String : "+s3+" GENE "+findSimpleGene(s3));
        System.out.println("DNA String : "+s4+" GENE "+findSimpleGene(s4));
        System.out.println("DNA String : "+s5+" GENE "+findSimpleGene(s5));
    }
}
