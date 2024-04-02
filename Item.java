/** 
* Class that defines an item, an object 
* that can be picked up and used by a 
* user during gameplay.
* @authors <Maya Delmont and Rachel Yang>
* @version Fall 22
*/
public class Item {

  /** String name of item */
  private String name;

  /** Room in which the item is located */
  private Room location;

  /** boolean that indicates if item has been picked up and is in possession of the user */
  private Boolean pickedUp;

  /** boolean that indicates if user has previously accessed the item */
  private Boolean prevAccess;

  /** constructor */
  public Item(String name, Room location, Boolean pickedUp, Boolean prevAccess) {
    this.name = name;
    this.location = location;
    this.pickedUp = pickedUp;
    this.prevAccess = prevAccess;
  }

  /** method to pick up an item and remove pointer to room */
  public void pickUp() {
    this.pickedUp = true;
    this.location = null;
  }

  /** location setter */
  public void drop(Room playerLoc) {
    this.pickedUp = false;
    this.location = playerLoc;
  }

  /** location getter */
  public Room getLocation() {
    return this.location;
  }

  /** NOT IN USE: method for a user to "recall" the location of a 
  * previously accessed and dropped item.
  */
  public void recallLocation() {
    if (prevAccess == true) {
      System.out.println("I think I left it in the" + this.getLocation().getName() + "...");
    }
  }
  
  /** name getter */
  public String getName() {
    return this.name;
  }
  
  
}