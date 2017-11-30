package textgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An implementation of the MTG interface that uses a list of lists.
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;

	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	private boolean train = false;

	public MarkovTextGeneratorLoL(Random generator) {
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
		train = false;
	}

	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText) {
		// TODO: Implement this method

		if (sourceText.length()==0)
			System.out.println("There is no input string!");
		else {
			String currWord, next, text;
			boolean exist = false;
			//text = sourceText.replaceAll("[!.?]+", "");
			List<String> tokens = getTokens(sourceText);
			ListNode node = new ListNode(tokens.get(tokens.size() - 1));
			wordList.add(node);
			wordList.get(0).addNextWord(tokens.get(0));
			node = new ListNode(tokens.get(0));
			currWord = starter = tokens.get(0);
			for (int i = 1; i < tokens.size(); i++) {
				exist = false;
				next = tokens.get(i);
				for (int j = 0; j < wordList.size(); j++) {
					if (wordList.get(j).getWord().equals(currWord)) {
						wordList.get(j).addNextWord(next);
						exist = true;
						break;
					}
				}
				if (!exist) {
					node = new ListNode(currWord);
					wordList.add(node);
					wordList.get(wordList.size() - 1).addNextWord(next);
				}
				currWord = tokens.get(i);
			}
			train = true;
		}
	}

	/**
	 * Generate the number of words requested.
	 * 
	 * @throws Exception
	 */
	@Override
	public String generateText(int numWords) {
		// TODO: Implement this method
		String word, currWord, output = "";
		int index;
		if (wordList.isEmpty()) {
			System.out.println("Please train text first!!");
			return output;
		}
		if(numWords==0) return output;
		currWord = starter;
		output += currWord;
		ListNode node = null;
		for (int j = 0; j < numWords; j++) {
			for (int i = 0; i < wordList.size(); i++) {
				if (wordList.get(i).getWord().equals(currWord)) {
					node = wordList.get(i);
					break;
				}
			}
			word = node.getRandomNextWord(rnGenerator);
			output += " " + word;
			currWord = word;
		}
		return output;
	}

	// Can be helpful for debugging
	@Override
	public String toString() {
		String toReturn = "";
		for (ListNode n : wordList) {
			toReturn += n.toString();
		}
		return toReturn;
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText) {
		// TODO: Implement this method.
		wordList = new LinkedList<ListNode>();
		starter = "";
		train(sourceText);
	}

	// TODO: Add any private helper methods you need here.
	private List<String> getTokens(String text) {
		String[] tokens = text.split("[\\s]+");

		ArrayList<String> list=new ArrayList<String>();
		for (String token : tokens) {
			list.add(token);
		}
		return list;
	}

	/**
	 * This is a minimal set of tests. Note that it can be difficult to test
	 * methods/classes with randomized behavior.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, " + "You say stop, and I say go, go, go, "
				+ "Oh no. You say goodbye and I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "I say high, you say low, "
				+ "You say why, and I say I don't know. " + "Oh no. "
				+ "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "Why, why, why, why, why, why, "
				+ "Do you say goodbye. " + "Oh no. " + "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "You say yes, I say no, "
				+ "You say stop and I say go, go, go. " + "Oh, oh no. "
				+ "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/**
 * Links a word to the next words in the list You should use this class in your
 * implementation.
 */
class ListNode {
	// The word that is linking to the next words
	private String word;

	// The next words that could follow it
	private List<String> nextWords;

	ListNode(String word) {
		this.word = word;
		nextWords = new LinkedList<String>();
	}

	public String getWord() {
		return word;
	}

	public void addNextWord(String nextWord) {
		nextWords.add(nextWord);
	}

	public String getRandomNextWord(Random generator) {
		// TODO: Implement this method
		// The random number generator should be passed from
		// the MarkovTextGeneratorLoL class
		int num = generator.nextInt(nextWords.size());
		return nextWords.get(num);
	}

	public String toString() {
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}

}
