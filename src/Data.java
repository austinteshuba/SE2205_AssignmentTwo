public class Data{
  double arrivalTime; // private vars to store the arrival and departure times of packets
  double departureTime;
  public Data(){ // init private vars to 0
	  this.arrivalTime = 0;
	  this.departureTime = 0;
  }
  
  // Getters and setters for the private vars
  public void setArrivalTime(double a){
	  this.arrivalTime = a;
  }
  public void setDepartureTime(double d){
	  this.departureTime = d;
  }
  public double getDepartureTime(){
	  return this.departureTime;
  }
  public double getArrivalTime(){
	  return this.arrivalTime;
  }
}