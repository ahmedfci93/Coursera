
/**
 * Write a description of Part3 here.
 * 
 * @author ahmedfci93 
 * @version (a version number or a date)
 */
public class Part3 {
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
    public int countGenes(String dna)
    {
        int startIndex=0;
        int ans=0;
        while(true)
        {
            String gene=findGene(dna,startIndex);
            if(gene.isEmpty())break;
            ans++;
            startIndex=dna.indexOf(gene,startIndex)+gene.length();
        }
        return ans;
    }
    public void testCountGenes()
    {
        String dna="GTACTCSGGAGTTAATTAA";
        System.out.println(dna+" number of Genes in that DNA equals "+countGenes(dna));
        dna="GTATGCTCSGGAGTTAATTAAGTATGCTCSGGAGTTAATTAA";
        System.out.println(dna+" number of Genes in that DNA equals "+countGenes(dna));
        dna="GTATGCTCSGGAGTTTAAAATAGTAAGTATGCTCSGGAGTTTAAAATAGTAAGTATGCTCSGGAGTTTAAAATAGTAAATGKKKDDDTTTNNNTAA";
        System.out.println(dna+" number of Genes in that DNA equals "+countGenes(dna));
        dna="GTATGCTCSGAGTTGAAAATAGTAA";
        System.out.println(dna+" number of Genes in that DNA equals "+countGenes(dna));
        dna="GTATGCTCSGGAGTAAATAGTGA";
        System.out.println(dna+" number of Genes in that DNA equals "+countGenes(dna));
        dna="GTATGCTCSGGAGTAAATAG";
        System.out.println(dna+" number of Genes in that DNA equals "+countGenes(dna));
        dna="GTATGCTCSGGAGTAAA";
        System.out.println(dna+" number of Genes in that DNA equals "+countGenes(dna));
        dna="ATGATCTAATTTATGCTGCAACGGTGAAGA";
        System.out.println(dna+" number of Genes in that DNA equals "+countGenes(dna));
        dna="";
        System.out.println(dna+" number of Genes in that DNA equals "+countGenes(dna));
        dna="ATGATCATAAGAAGATAATAGAGGGCCATGTAA";
        System.out.println(dna+" number of Genes in that DNA equals "+countGenes(dna));
        dna="ATGTAAGATGCCCTAG";
        System.out.println(dna+" number of Genes in that DNA equals "+countGenes(dna));
    }
}
