
/**
 * Write a description of Part1 here.
 * 
 * @author ahmedfci93 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna,int startIndex,String stopCodon)
    {
        int ans=dna.indexOf(stopCodon,startIndex);
        while(ans!=-1)
        {
            if((ans-(startIndex+3))%3==0)return ans;
            ans=dna.indexOf(stopCodon,ans+3);
        }
        return -1;
    }
    public String findGene(String dna,int stIndex)
    {
        int ans;
        int startIndex=dna.indexOf("ATG",stIndex);
        if(startIndex==-1)return "";
        String stopCodon="TAA";
        int stopIndex=findStopCodon(dna, startIndex, stopCodon);
        String stopCodon2="TAG";
        int stopIndex2=findStopCodon(dna, startIndex, stopCodon2);
        String stopCodon3="TGA";
        int stopIndex3=findStopCodon(dna, startIndex, stopCodon3);
        if(stopIndex2!=-1&&stopIndex!=-1)
            ans=Math.min(stopIndex2,stopIndex);
        else 
            ans=Math.max(stopIndex2,stopIndex);
        if(ans!=-1&&stopIndex3!=-1)
            ans=Math.min(ans,stopIndex3);
        else 
            ans=Math.max(ans,stopIndex3);
        if(ans==-1)
            return "";
        return dna.substring(startIndex,ans+3);
    }
    void printAllGenes(String dna)
    {
        int startIndex=0;
        System.out.println("Testing printsAllGene on "+dna);
        while(true)
        {
            String gene=findGene(dna,startIndex);
            if(gene.isEmpty())break;
            System.out.println(gene);
            startIndex=dna.indexOf(gene,startIndex)+gene.length();
        }
    }
    public void testFindGene()
    {
        String dna="GTACTCSGGAGTTAATTAA";
        System.out.println(dna+" "+findGene(dna,0));
        dna="GTATGCTCSGGAGTTAATTAA";
        System.out.println(dna+" "+findGene(dna,0));
        dna="GTATGCTCSGGAGTTTAAAATAGTAA";
        System.out.println(dna+" "+findGene(dna,0));
        dna="GTATGCTCSGGAGTTGAAAATAGTAA";
        System.out.println(dna+" "+findGene(dna,0));
        dna="GTATGCTCSGGAGTAAATAGTGA";
        System.out.println(dna+" "+findGene(dna,0));
        dna="GTATGCTCSGGAGTAAATAG";
        System.out.println(dna+" "+findGene(dna,0));
        dna="GTATGCTCSGGAGTAAA";
        System.out.println(dna+" "+findGene(dna,0));
    }
    public void testFindStopCodon()
    {
        String dna="acaagtttgtacaaaaaagcagaagggccgtcaaggcccaccatgcctattggatccaaa";
        int startIndex=dna.indexOf("atg");
        String stopCodon="taa";
        System.out.println(dna+"\n"+startIndex+" "+findStopCodon(dna,startIndex,stopCodon));//there is no stop codon
        dna="acaagtttgtacaaaaaagcagaagggccgtcaaggcccaccatgcctattggataaacc";
        System.out.println(dna+"\n"+startIndex+" "+findStopCodon(dna,startIndex,stopCodon));//there is only one vaild codon
        dna="atgtacaaaataagctaaagaagggccgtcaaggcccaccatgcctattggcc";
        startIndex=dna.indexOf("atg");
        System.out.println(dna+"\n"+startIndex+" "+findStopCodon(dna,startIndex,stopCodon));//there is one invaild codon and one valid.
    }
    public void test()
    {
        String dna="ATGATCTAATTTATGCTGCAACGGTGAAGA";
        printAllGenes(dna);
        dna="";
        printAllGenes(dna);
        dna="ATGATCATAAGAAGATAATAGAGGGCCATGTAA";
        printAllGenes(dna);
    }
}
