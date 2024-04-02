/** 
* Class that defines a Key, extends Item.
* Object used to determine whether user can access associated locked room
* @authors <Maya Delmont and Rachel Yang>
* @version Fall 22
*/
public class Key extends Item {

  /** Room that can be unlocked using the key */
  private Room unlocksRoom;

  /** constructor */
  public Key(String name, Room location, Boolean pickedUp, Boolean prevAccess, Room unlocksRoom) {
    super(name, location, pickedUp, prevAccess);
    this.unlocksRoom = unlocksRoom;
  }

  /** returns the room that can be unlocked with this key */
  public Room getUnlocksRoom() {
    return this.unlocksRoom;
  }

  
}