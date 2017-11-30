package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
		size=0;
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		String toAdd;
		Character c;
		TrieNode tmp;
    	if(word.equals(null)) throw new NullPointerException("Parameter Type cannot be null");
    	toAdd=word.toLowerCase();
    	tmp=root;
    	for (int i =0;i<toAdd.length();i++) {
    		c=toAdd.charAt(i);
    		if (tmp.getValidNextCharacters().contains(c)) {
				tmp = tmp.getChild(c);
			} else {
				tmp = tmp.insert(c);
			}
		}
    	if (!tmp.endsWord()) {
			tmp.setEndsWord(true);
			size++;
			return true;
		}
	    return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		String toCheck = s.toLowerCase();
		TrieNode node = root;
		for (int i = 0; i < toCheck.length(); i++) {
			char c = toCheck.charAt(i);
			if (node.getValidNextCharacters().contains(c)) {
				node = node.getChild(c);
			} else {
				return false;
			}
		}
		if (node.endsWord()) {
			return true;
		}
		return false;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
    	 TrieNode node=root;
    	 String pref =prefix.toLowerCase();
    	 List<String> ans = new LinkedList<String>();
    	 for (int i = 0; i < pref.length(); i++) {
    		 char c = pref.charAt(i);
    		 if (node.getValidNextCharacters().contains(c)) {
 				node = node.getChild(c);
 			} else {
 				return ans;
 			}
    	 }
    	 int count = 0;
    	 if (node.endsWord()) {
    		 ans.add(node.getText());
    		 count++;
    	 }

    	 List<TrieNode> nodeQueue = new LinkedList<TrieNode>();
    	 List<Character> childrenC = new LinkedList<Character>(node.getValidNextCharacters());
    	 for (int i = 0; i < childrenC.size(); i++) {
    		 char c = childrenC.get(i);
    		 nodeQueue.add(node.getChild(c));
    	 }
    	 
    	 while (!nodeQueue.isEmpty() && count < numCompletions) {
    		 TrieNode tmp = nodeQueue.remove(0);
    		 if (tmp.endsWord()) {
    			 ans.add(tmp.getText());
    			 count++;
    		 }
    		 
    		 List<Character> cs = new LinkedList<Character>(tmp.getValidNextCharacters());
        	 for (int i = 0; i < cs.size(); i++) {
        		 char c = cs.get(i);
        		 nodeQueue.add(tmp.getChild(c));
        	 }
    	 }
         return ans;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}