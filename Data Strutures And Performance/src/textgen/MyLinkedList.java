package textgen;

import java.util.AbstractList;

/**
 * A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E>
 *            The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = tail = null;
	}

	/**
	 * Appends an element to the end of the list
	 * 
	 * @param element
	 *            The element to add
	 */
	public boolean add(E element) {
		// TODO: Implement this method
		if (element.equals(null))
			throw new NullPointerException("Parameter Type cannot be null");
		LLNode<E> node = new LLNode(element);
		if (size == 0) {
			head = tail = node;
		} else {
			node.prev = tail;
			tail.next = node;
			tail = node;
		}
		size++;
		return false;
	}

	/**
	 * Get the element at position index
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E get(int index) {
		// TODO: Implement this method.
		int i, sz = size();
		E ret = null;
		if (index < 0 || index >= sz) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
		} else {
			if (index >= 0) {
				LLNode<E> node = head;
				for (i = 0; i < sz; i++) {
					if (i == index) {
						ret = node.data;
						return ret;
					}
					node = node.next;
				}
			}
		}
		return ret;
	}

	/**
	 * Add an element to the list at the specified index
	 * 
	 * @param The
	 *            index where the element should be added
	 * @param element
	 *            The element to add
	 */
	public void add(int index, E element) {
		// TODO: Implement this method
		int i, sz = size();
		if (element.equals(null))
			throw new NullPointerException("Parameter Type cannot be null");
		if (index < 0 || index > sz) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
		} else {
			if (index == sz) {
				add(element);
			} else {
				LLNode<E> node = new LLNode(element);
				if (index == 0) {
					node.next = head;
					head.prev = node;
					head = node;
				} else {
					LLNode<E> tmp = head.next;
					for (i = 1; i < sz; i++) {
						if (index == i) {
							node.next = tmp;
							node.prev = tmp.prev;
							tmp.prev.next = node;
							tmp.prev = node;
							break;
						}
						tmp = tmp.next;
					}
				}
				size++;
			}
		}
	}

	/** Return the size of the list */
	public int size() {
		// TODO: Implement this method
		return size;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 * 
	 * @param index
	 *            The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException
	 *             If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
		// TODO: Implement this method
		int sz = size();
		E ret = null;
		if (sz > 0) {
			if (index < 0 || index >= sz) {
				throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
			} else {
				if (index == 0) {
					if (sz == 1) {
						ret = head.data;
						head = tail = null;
					} else {
						ret = head.data;
						head = head.next;
					}
				} else if (sz - 1 == index) {
					ret = tail.data;
					tail = tail.prev;
				} else {
					int i;
					LLNode<E> temp = head;
					for (i = 1; i < sz - 1; i++) {
						temp = temp.next;
						if (i == index) {
							ret = temp.data;
							temp.next.prev = temp.prev;
							temp.prev.next = temp.next;
						}
					}
				}
				size--;
			}
		} else {
			throw new NullPointerException("List is Empty");
		}
		return ret;
	}

	/**
	 * Set an index position in the list to a new element
	 * 
	 * @param index
	 *            The index of the element to change
	 * @param element
	 *            The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E set(int index, E element) {
		// TODO: Implement this method
		int i, sz = size();
		E ret;
		if (element.equals(null))
			throw new NullPointerException("Parameter Type cannot be null");
		if (index < 0 || index >= sz) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
		} else {
			LLNode<E> node = head;
			for (i = 0; i < sz; i++) {
				if (i == index) {
					ret = node.data;
					node.data = element;
					return ret;
				}
				node = node.next;
			}
		}
		return null;
	}
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
