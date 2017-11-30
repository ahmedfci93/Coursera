/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	MyLinkedList<Integer> list2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list2 = new MyLinkedList<Integer>();
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		try {
			list1.remove(list1.size()+1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		a=list1.remove(1);
		assertEquals("Remove: check is correct ",42, a);
		assertEquals("Remove: check size is correct ", 1, list1.size());
		
		a=list1.remove(0);
		assertEquals("Remove: check is correct ",21, a);
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		try {
			list1.add(null);
			fail("Check out of bounds");
		}
		catch (NullPointerException e) {
			
		
		}
		list1.add(20);
		list1.add(50);
		assertEquals("AddEnd: check a is correct ",(Integer)50, list1.get(list1.size()-1));
		emptyList.add(20);
		assertEquals("AddEnd: check a is correct ",(Integer)20, emptyList.get(emptyList.size()-1));
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Size: check size is correct ", 0, emptyList.size());
		list1.add(8);
		assertEquals("Size: check size is correct ", 4, list1.size());
		list1.add(9);
		list1.add(10);
		assertEquals("Size: check size is correct ", 6, list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		try {
			list1.add(-1,5);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		try {
			list1.add(list1.size()+1,5);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		try {
			list1.add(0,null);
			fail("Check out of bounds");
		}
		catch (NullPointerException e) {
			
		
		}
        // TODO: implement this test
		list1.add(0,10);
		assertEquals("AddAtIndex: check add is correct ", (Integer)10, list1.get(0));
		list1.add(0,22);
		assertEquals("AddAtIndex: check add is correct ", (Integer)22, list1.get(0));
		assertEquals("AddAtIndex: check add is correct ", (Integer)10, list1.get(1));
		
		list1.add(list1.size()-1,5);
		assertEquals("AddAtIndex: check add is correct ", (Integer)42, list1.get(list1.size()-1));
		assertEquals("AddAtIndex: check add is correct ", (Integer)5, list1.get(list1.size()-2));
		
		list1.add(3,8);
		list1.add(1,9);
		
		assertEquals("AddAtIndex: check add is correct ", (Integer)22, list1.get(0));
		assertEquals("AddAtIndex: check add is correct ", (Integer)9, list1.get(1));
		assertEquals("AddAtIndex: check add is correct ", (Integer)10, list1.get(2));

		assertEquals("AddAtIndex: check add is correct ", (Integer)65, list1.get(3));
		assertEquals("AddAtIndex: check add is correct ", (Integer)8, list1.get(4));
		assertEquals("AddAtIndex: check add is correct ", (Integer)21, list1.get(5));
		
		list2.add(0, 5);
		int a =list2.remove(0);
		assertEquals("AddAtIndex: check add is correct ", 5,a);
		list2.add(0, 1);
		assertEquals("AddAtIndex: check add is correct ",(Integer)1, list2.get(0));
		
		
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		int a=list1.set(0, 88);
		list1.add(55);
		try {
			list1.set(-1, 1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		try {
			list1.set(list1.size()+1, 1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
	    assertEquals("Set: check is correct ", (Integer)88,list1.get(0));
	    assertEquals("Set: check is correct ", 65,a);
	    a=list1.set(list1.size()-1, 77);
	    assertEquals("Set: check is correct ", (Integer)77,list1.get(list1.size()-1));
	    assertEquals("Set: check is correct ", 55,a);
	}
	
	
	// TODO: Optionally add more test methods.
	
}
