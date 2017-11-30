package spelling;

import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class DictionaryBST implements Dictionary 
{
   private TreeSet<String> dict;
	
    // TODO: Implement the dictionary interface using a TreeSet.  
 	// You'll need a constructor here
	
    
    public DictionaryBST() {
	this.dict = new TreeSet<String>();
}


	/** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// TODO: Implement this method
    	String toAdd;
    	if(word.equals(null)) throw new NullPointerException("Parameter Type cannot be null");
    	toAdd=word.toLowerCase();
    	if(!this.dict.contains(toAdd))
    		if(dict.add(toAdd))
    		{
    			return true;
    		}
        return false;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
    	// TODO: Implement this method
    	return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	//TODO: Implement this method
    	String toFind;
    	if(s.equals(null)) throw new NullPointerException("Parameter Type cannot be null");
    	toFind=s.toLowerCase();
        return this.dict.contains(toFind);
    }

}
