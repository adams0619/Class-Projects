package edu.ncsu.csc216.carrental.util;

import java.util.EmptyStackException;

public class Stack<E> implements SimpleStack<E> {

	/** Variable reference to the head of the linked list for our stack */
	private Node head;

	/**
	 * Constructor method used to create an empty stack
	 */
	public Stack() {
		head = null;
	}

	/**
	 * Method used to determine if the stack is empty (null)
	 * 
	 * @return true if the stack is null, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Method used to view the top item of the stack without removing (popping)
	 * it from the stack
	 * 
	 * @throws EmptyStackException
	 *             if the stack is empty
	 * @return data the first item of the top of stack
	 */
	@Override
	public E peek() throws EmptyStackException {
		if (!this.isEmpty())
			return head.data;
		throw new EmptyStackException();
	}

	/**
	 * Method used to return the first item of the Stack if the Stack is not
	 * empty
	 * 
	 * @throws EmptyStackException
	 *             if the Stack is empty
	 * @return data the first (top) item from the stack
	 */
	@Override
	public E pop() throws EmptyStackException {
		if (this.isEmpty()) {
			throw new EmptyStackException();
		}
		// Save top stack item
		E data = head.data;
		// Move head pointer to the next item in stack
		head = head.next;
		return data;
	}

	/**
	 * Method used push a new item onto the top of the stack
	 * 
	 * @param item
	 *            data that will be added on the stack
	 */
	@Override
	public void push(E item) {
		// Create new node object and add it to the top of the stack
		head = new Node(item, head);
	}

	/**
	 * Private inner class representing a node of the linked list implementation
	 * for our Stack
	 * 
	 * @author Adams Ombonga (amombong)
	 * @version 1.0 (April 15, 2015)
	 *
	 */
	private class Node {
		/**
		 * Data reference for the Node class
		 */
		public E data;

		/**
		 * Reference to the next node in the linked list
		 */
		private Node next;

		/**
		 * Constructor method for Node which accepts a DEvice and node as a
		 * parameter. This method is used to place the object (device) onto the
		 * linked list
		 * 
		 * @param device
		 *            device object containing device data
		 * @param node
		 *            position in the linked list that will reference this
		 *            Device
		 */
		public Node(E data, Node node) {
			this.data = data;
			this.next = node;

		}
	}
}
