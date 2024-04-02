import java.util.List;
import java.util.ArrayList;

/**
*  Class that defines a Room
*  @authors <Maya Delmont and Rachel Yang>
*  @version Fall 2022
*/

public class Room {

  /** boolean that indicates whether the user has previously visited the room */
  private Boolean visited;

  /** boolean that indicates if room is currently accesible */
  private Boolean unlocked;

  /** String that represents room name */
  private String name;

  /** NOT IN USE: String that would be printed to show room interior */
  private String interiorASCII;

  /** pointer to the room to the north of given room */
  private Room northNext;

  /** pointer to the room to the east of given room */
  private Room eastNext;

  /** pointer to the room to the south of given room */
  private Room southNext;

  /** pointer to the room to the west of given room */
  private Room westNext;

  /** String to print when user is entering room for first time */
  private String firstVisitDesc;

  /** ArrayList of tasks a player can execute while in room */
  private ArrayList<Task> tasks;

  /** ArrayList of items that are accessible via tasks in room */
  private ArrayList<Item> collectibles;

  /** Key associated with room, used to access room when locked */
  private Key key;
  
  
  /** constructor */
  public Room(String roomName, String interior, Boolean unlocked, String firstVisitDesc){
    this.name = roomName;
    this.visited = false;
    this.unlocked = unlocked;
    northNext = eastNext = southNext = westNext = null;
    this.interiorASCII = interior;
    this.firstVisitDesc = firstVisitDesc;
    ArrayList tasks = new ArrayList<Task>();
    this.tasks = tasks;
    ArrayList items = new ArrayList<Item>();
    this.collectibles = items;
  }

  /** name getter */
  public String getName() {
    return name;
  }

  /** interior getter */
  public String getInterior() {
    return interiorASCII;
  }
  
  /** interior setter */
  public void setInterior(String newInterior) {
    this.interiorASCII = newInterior;
  }

  /** north room setter */
  public void setNorth(Room northRoom) {
    this.northNext = northRoom;
  }
  /** north room getter */
  public Room getNorth() {
    return northNext;
  }

  /** east room setter */
  public void setEast(Room eastRoom) {
    this.eastNext = eastRoom;
  }
  /** east room getter */
  public Room getEast() {
    return eastNext;
  }

  /** south room setter */
  public void setSouth(Room southRoom) {
    this.southNext = southRoom;
  }
  /** south room getter */
  public Room getSouth() {
    return southNext;
  }

  /** west room setter */
  public void setWest(Room westRoom) {
    this.westNext = westRoom;
  }
  /** west room getter */
  public Room getWest() {
    return westNext;
  }

  /** room name getter */
  public String getRoomName() {
    return this.name;
  }

  /** room key getter 
  * returns object of type Key
  */
  public Key getKey(){
    return this.key;
  }

  /** room key setter */
  public void setKey(Key key){
    this.key = key;
  }

  /** unlocked status setter */
  public void unlock() {
    this.unlocked = true;
  }

  //UNUSED
  /** locked status setter */
  public void lock() {
    if (this.getUnlocked() == false) {
      System.out.println("This door is already locked.");
    } else {
      this.unlocked = false; 
    }
  }

  /** unlocked status getter */
  public Boolean getUnlocked() {
    return this.unlocked;
  }

  /** visited status setter */
  public void setVisited() {
    this.visited = true;
  }

  /** visited false status setter */
  public void setUnvisited() {
    this.visited = false;
  }

  /** visited status getter */
  public Boolean getVisited() {
    return this.visited;
  }

  /** collectibles getter */
  public List<Item> getCollectibles() {
    return this.collectibles;
  }

  /** method to add an item to a room's list of collectibles */
  public void addItem(Item item) {
    this.collectibles.add(item);
  }

  /** first visit description getter */
  public String getFirstVisitDesc() {
    return this.firstVisitDesc;
  }

  /** first visit description setter */
  public void changeFirstVisitDesc(String newDesc) {
    this.firstVisitDesc = newDesc;
  }

  /** task list getter */
  public ArrayList<Task> getTasks() {
    return this.tasks;
  }

  /** method to print out the descriptions of 
  *   each task in a rooms task list.
  */
  public void getTaskDescs() {
    for (int i = 0; i < this.tasks.size(); i++){
      if (tasks.get(i).checkComplete() == false && tasks.get(i).isRoomChange() == false){
        System.out.println("[" + (i+1) + "] " + tasks.get(i).getTaskDesc());     
      }
      if (tasks.get(i).isRoomChange() == true){
        System.out.println("[" + (i+1) + "] " + tasks.get(i).getTaskDesc());     
      }
    }
  }

  /** method to add tasks to a rooms task list*/
  public void addTasks(Task[] tasks) {
    for (Task t: tasks) {
      addTask(t);
    }
  }

  /** method to add tasks to a rooms task list*/
  public void addTask(Task task) {
    this.tasks.add(task);
  }

  /** method to remove a task from a rooms task list */
  public void removeTask(Task task){
    this.tasks.remove(task);
  }

  /** method to get String name of room*/
  public String toString() {
    return name;
  }
  

  /** 
  * method to create directional tasks based on  
  * rooms connected to a given room.
  */
  public void setDirectionTasks() {
    if (this.northNext != null){
      Task goNorth = new Task("Go North", "", true);
      this.tasks.add(goNorth);
    }
    if (this.eastNext != null){
      Task goEast = new Task("Go East", "", true);
      this.tasks.add(goEast);
    }
    if (this.southNext != null){
      Task goSouth = new Task("Go South", "", true);
      this.tasks.add(goSouth);
    }    
    if (this.westNext != null){
      Task goWest = new Task("Go West", "", true);
      this.tasks.add(goWest);
    }    
  }

}