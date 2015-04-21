package edu.ncsu.csc216.carrental.util;

import java.util.NoSuchElementException;

public class Queue<E> implements SimpleQueue<E> {

	/** Variable reference to the head of the linked list for our Queue */
	private Node head;

	/** Variable reference to the head of the linked list for our Queue */
	private Node tail;

	/**
	 * Constructor method used to create an empty Queue
	 */
	public Queue() {
		head = null;
	}

	/**
	 * Method used to add a node to the end of our queue
	 * 
	 * @param item
	 *            , data to be added to the end of the queue
	 */
	@Override
	public void add(E item) {
		// If empty queue, make this item the first item of the queue
		if (head == null) {
			head = new Node(item, null);
			// Set the first item to also be the tail
			tail = head;
		} else {
			// Add item to the end, after the current tail of our queue
			tail.next = new Node(item, null);
			// Set this new item as the tail of the queue
			tail = tail.next;
		}
	}

	/**
	 * Method used to remove the first (front) item of the queue
	 * 
	 * @throws NoSuchElementException
	 *             if the queue is empty
	 * @return removedData the first item of the queue
	 */
	@Override
	public E remove() throws NoSuchElementException {
		if (head == null)
			// Throw exception b/c the list is empty
			throw new NoSuchElementException();
		// Save data from the first item of queue
		E removedData = head.data;
		// Move the head pointer to the next item in the queue
		head = head.next;
		// Check to see if the queue is now empty
		if (head == null) {
			tail = null;
		}
		return removedData;
	}

	/**
	 * Method used to view the top item of the queue without removing it from
	 * the queue
	 * 
	 * @throws NoSuchElementException
	 *             if the queue is empty
	 * @return first item of the top of queue
	 */
	@Override
	public E peek() throws NoSuchElementException {
		if (!this.isEmpty())
			return head.data;
		// Throw exception if the queue is empty
		throw new NoSuchElementException();
	}

	/**
	 * Method used to determine if the queue is empty (null)
	 * 
	 * @return true if the queue is null, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Private inner class representing a node of the linked list implementation
	 * for our Queue
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
