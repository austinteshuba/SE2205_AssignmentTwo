public class LinkedListQueue<E> implements Queue<E>{
  SinglyLinkedList<E> singlyLinkedList; // private var for the singly linked list
  public LinkedListQueue(){
	  this.singlyLinkedList = new SinglyLinkedList(); // initialize with empty linked list
  }
  public int size(){
	  return this.singlyLinkedList.size(); // return the size of the linked list
  }
  public boolean isEmpty(){
	  return this.singlyLinkedList.isEmpty(); // return the outcome of the isEmpty method on the linked list
  }
  public E first(){
	  return this.singlyLinkedList.first(); // return the first element of the linked list
  }
  public void enqueue(E node){
	  this.singlyLinkedList.addLast(node); // add an element to the end of the linked list
  }
  public E dequeue(){
	  return this.singlyLinkedList.removeFirst(); // remove the element from the start of the linked list and return the data
  }
}
