import java.lang.*;

public class QueueSimulator{
  public enum Event { ARRIVAL, DEPARTURE };
  private double currTime;
  private double arrivalRate;
  private double serviceTime;
  private double timeForNextArrival;
  private double timeForNextDeparture;
  private double totalSimTime;
  LinkedListQueue<Data> buffer = new LinkedListQueue<Data>();
  LinkedListQueue<Data> eventQueue = new LinkedListQueue<Data>();
  private Event e;
  
  public double getRandTime(double arrivalRate){
    double num, time1, max=1, min=0, randNUM;
    randNUM= Math.random();
    time1= (-1/arrivalRate) * (Math.log(1-randNUM));
    //System.out.println(time1);
    return time1;
  }
  
  public QueueSimulator(double aR, double servT, double simT){
	  this.arrivalRate = aR; // init private vars as the given params
	  this.serviceTime = servT;
	  this.totalSimTime = simT;
	  this.currTime = 0; // start time at 0
	  this.timeForNextArrival = getRandTime(this.arrivalRate); // the time to next arrival is taken from the random function
	  this.timeForNextDeparture = this.timeForNextArrival + this.serviceTime; // time for departure is time when next packet arrives + time to process it 
  }
  
  // iterates through the linked list and averages all of the differences between arrival and departure times.
  public double calcAverageWaitingTime(){
	  double totalWaitingTime = 0.0;
	  Data traverse = eventQueue.dequeue();
	  int size = eventQueue.size();
	  while(traverse != null) {
		  totalWaitingTime += (traverse.getDepartureTime() - traverse.getArrivalTime());
		  traverse = eventQueue.dequeue();
	  }
//	  System.out.println(this.buffer.size());
	  return (totalWaitingTime) / (size);
  }
  
  public double runSimulation(){
	  while (this.currTime < this.totalSimTime) {
		  // There are two things that will happen in this loop. Adding, and removing. 
		  this.nextEvent(); // this is going to figure out what the next event is. 
		  switch (this.e) {
		  case ARRIVAL:
			  // We do the arrival
			  // adjust the current time, add the node to the buffer, and set the nodes arrival time to right now.
			  this.currTime += this.timeForNextArrival;
			  Data newNode = new Data();
			  newNode.setArrivalTime(this.currTime);
			  buffer.enqueue(newNode);
			  
			  // We calculate time to next arrival. We also move the next departure closer since current time has moved forward
			  this.timeForNextDeparture -= this.timeForNextArrival;
			  this.timeForNextArrival = this.getRandTime(this.arrivalRate);
			  break;
		  case DEPARTURE:
			  // We do the departure
			  // Change the current time, remove the oldest node and set it's departure time to now.
			  this.currTime += this.timeForNextDeparture;
			  Data doneNode = buffer.dequeue();
			  doneNode.setDepartureTime(this.currTime);
			  
			  // Move the next arrival time since the current time has progressed
			  this.timeForNextArrival -= this.timeForNextDeparture;
			  
			  
//			  System.out.println(doneNode.getArrivalTime());
//			  System.out.println(doneNode.getDepartureTime());
			  
			  
			  //Time for next Departure is either the time for another arrival + the service time or, if there are already other nodes, just the service time
			  if (buffer.isEmpty()) {
				  this.timeForNextDeparture = this.timeForNextArrival + this.serviceTime;
			  } else {
				  this.timeForNextDeparture = this.serviceTime;
			  }
			  eventQueue.enqueue(doneNode);
			  break;
		  }
	  }
	  return calcAverageWaitingTime();
  }
  
  // This functions checks which should happen sooner - to departure or the arrival. It changes the event so these actions occur in 
  // chronological order. 
  private void nextEvent() { 
	  if (this.timeForNextArrival >= this.timeForNextDeparture && !buffer.isEmpty()) {
		  this.e = Event.DEPARTURE;
	  } else {
		  this.e = Event.ARRIVAL;
	  }
  }
}






