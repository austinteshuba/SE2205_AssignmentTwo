public class SinglyLinkedList<E>{
	Node<E> head; // we must keep track of head and tail
	Node<E> tail;

  private static class Node<E>{
	E element; // Each node has an element and a pointer to the next node
	Node<E> next;
    public Node(E e, Node<E> n){ // set the private vals based on inputs to constructor
    	this.element = e;
    	this.next = n;
    }
    public E getElement(){
    	return this.element; // Return the stored value
    }
    public Node<E> getNext(){
    	return this.next; // return the pointer to next
    }
    public void setNext(Node<E> n){
    	this.next = n; // set the pointer to the next node as the given params
    }
  }
  
  public SinglyLinkedList(){ // Inititalizing an empty linked list with a null head and tail
	  this.head = null;
	  this.tail = null;
  }
  
  public int size(){ // to get the size, traverse the list until we hit a null element
	  Node<E> traverse = this.head;
	  int size = 0;
	  while(traverse != null) {
		  traverse = traverse.getNext();
		  size++;
	  }
	  return size;
  }
  
  public boolean isEmpty(){ // If the head is null, there are no elements. Otherwise, there is an element (at least the head)
	  return this.head == null;
  }
  
  // Returns the first element in the linked list
  public E first(){
	  if (this.head == null) {
		  return null;
	  }
	  return this.head.getElement();
  }
  
  // returns the last element in the linked list
  public E last(){
	  if (this.tail == null) {
		  return null;
	  }
	  return this.tail.getElement();
  }
  // This adds an element to the front of the linked list. The tail equals the head if the list was empty
  public void addFirst(E element){
	  Node<E> oldFirst = this.head;
	  Node<E> newFirst = new Node(element, oldFirst);
	  this.head = newFirst;
	  if (this.tail == null) {
		  this.tail = newFirst;
	  }
  }
  // This adds an element to the back of the linked list. The tail equals the head if the list was empty
  public void addLast(E element){
	  Node<E> newLast = new Node(element, null);
	  if (this.head == null) {
		  this.head = newLast;
		  this.tail = newLast;
		  return;
	  }
	  	  
	  this.tail.setNext(newLast);
	  this.tail = newLast;
  }
  // This removes the first element. If the head is now null, the tail is as well. Returns value of the element removed
  public E removeFirst(){
	  if (this.head == null) {
		  return null;
	  }
	  E retVal = this.head.getElement();
	  this.head = this.head.getNext();
	  if (this.head == null) {
		  this.tail = null;
	  }
	  return retVal;
  }

}