package edu.ncsu.csc216.carrental.util;

import java.util.NoSuchElementException;

/**
 * A simple collection designed for holding elements prior to processing.
 * A Queue typically orders elements in a FIFO (First-In-First-Out) manner.
 * 
 * @param<E> generic object to be stored in this type of queue
 * @author David Wright
 */
public interface SimpleQueue<E> {

	/**
	 * Inserts the specified element into this queue.
	 * 
	 * @param item the element to add
	 */
	public void add(E item);
	
	/**
	 * Retrieves and removes the head of this queue.
	 * 
	 * @return the element at head of this queue
	 * 
	 * @throws NoSuchElementException if this queue is empty
	 */
	public E remove() throws NoSuchElementException;
	
	/**
	 * Retrieves, but does not remove, the head of this queue.
	 * 
	 * @return the head of this queue
	 * 
	 * @throws NoSuchElementException if this queue is empty
	 */
	public E peek() throws NoSuchElementException;
	
	/**
	 * Tests if this queue is empty.
	 * 
	 * @return true if and only if this queue contains no items; false otherwise.
	 */
	public boolean isEmpty();
}