
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String DNA,String startCodon,String stopCodon)
    {
        String ans=null;
        String dna=DNA.toLowerCase();
        String start=startCodon.toLowerCase();
        String stop=stopCodon.toLowerCase();
        int index1=dna.indexOf(start);
        if(index1==-1)return ans;
        int index2=dna.indexOf(stop,index1+3);
        if(index2==-1)return ans;
        ans=DNA.substring(index1, index2+3);
        if(ans.length()%3!=0) return null;
        return ans;
    }
    public void testSimpleGene()
    {
        String s1=new String("AAATGCCCTAACTAGATTAAGAAACC");
        String s2=new String("ATGGTATGCTCSGGGTATA");
        String s3=new String("ATCGTACTCSGGGTTATAT");
        String s4=new String("ATGGTACTCSGGAGTTAATTAA");
        String s5=new String("ATCGTACTCSGGGTTAATTAA");
        System.out.println("DNA String : "+s1+" GENE "+findSimpleGene(s1,"ATG","TAA"));
        System.out.println("DNA String : "+s2+" GENE "+findSimpleGene(s2,"ATG","TAA"));
        System.out.println("DNA String : "+s3+" GENE "+findSimpleGene(s3,"ATG","TAA"));
        System.out.println("DNA String : "+s4+" GENE "+findSimpleGene(s4,"ATG","TAA"));
        System.out.println("DNA String : "+s5+" GENE "+findSimpleGene(s5,"ATG","TAA"));
    }

}
