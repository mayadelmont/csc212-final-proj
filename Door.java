/** 
* Class meant to be used in a Graphs implementation of the 
* house map as the objects used as the edges of the network.
* @authors <Maya Delmont and Rachel Yang>
* @version Fall 22
*/

public class Door {
  /** direction that the door represents from
  * one room to the other */
  String direction;

  /** constructor */
  public Door(String direction) {
    this.direction = direction;
  }

  /** direction getter */
  public String getDirection() {
    return direction;
  }
  
  /** String representation for printing */
  public String toString() {
    return direction;
  }
}