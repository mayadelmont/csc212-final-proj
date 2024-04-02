/** 
* Tasks to be done in each room, which can add "awards" to player
* inventory.
* @author <Maya Delmont and Rachel Yang>
* @version Fall 22
*/
public class Task {

  /** String to print in the list of tasks */
  private String taskDesc;

  /** String to print when player chooses to do the task */
  private String conseq;

  /** Item to add to player inventory when they do the task */
  private Item award;

  /** Whether the task has been completed by the player */
  private Boolean complete;

  /** Whether the task is a movement one taking the player to 
  * another room 
  */
  private Boolean roomChange;

  /** Constructor */
  public Task (String taskDesc, String conseq, Boolean roomChange) {
    this.taskDesc = taskDesc;
    this.conseq = conseq;
    this.award = null;
    this.complete = false;
    this.roomChange = roomChange; 
  }

  /** getter for task description */
  public String getTaskDesc() {
    return this.taskDesc;
  }

  /** getter for resulting string when player does task */
  public String getConseq() {
    return this.conseq;
  }

  /** setter for award */
  public void setAward(Item item){
    this.award = item;
  }

  /** getter for whether the task has been completed by player */
  public Boolean checkComplete() {
    return this.complete;
  }

  /** getter for whether the task should move the player to a new
  * new room */
  public Boolean isRoomChange() {
    return this.roomChange;
  }

  /** 
  * Carrying out resulting changes after a player performs a task
  * on a room's task list. Contains special case for the office,
  * which only reveals all tasks when a flashlight is used and for
  * the dining room, which similarly reveals additional tasks when
  * another task is performed.
  * @param player -- Person with an inventory that the task may add
  *   to or move to a different room
  * @room Room -- current rooom so that room change tasks may check
  *   if the player can be moved into surrounding rooms
  * @return no return value
  */
  public void setComplete(Person player, Room room) {
    this.complete = true;
    System.out.println("\n" + this.conseq);
    if (this.award != null){
      System.out.println("You found a " + this.award.getName() +"!");
      player.addToInventory(award);
      //remove item from room?
    }
    if (this.roomChange == true) {
      if (this.taskDesc == "Go North") {
        if (room.getNorth().getUnlocked()){
          player.setLocation(room.getNorth());
        } else {
          System.out.println("This room is locked.");
          if (player.getInventory().contains(room.getNorth().getKey())) {
              System.out.println("...but you have the " + room.getNorth().getName() + " key!");
              player.setLocation(room.getNorth());
          }
        }
      } else if (this.taskDesc == "Go East") {
        if (room.getEast().getUnlocked()){
          player.setLocation(room.getEast());
        } else {
          System.out.println("This room is locked.");
          if (player.getInventory().contains(room.getEast().getKey())) {
            System.out.println("...but you have the " + room.getEast().getName() + " key!");
            player.setLocation(room.getEast());
          }
        }
      } else if (this.taskDesc == "Go South") {
        if (room.getSouth().getUnlocked()){
          player.setLocation(room.getSouth());
        } else {
          System.out.println("This room is locked.");
          if (player.getInventory().contains(room.getSouth().getKey())){
            System.out.println("...but you have the " + room.getSouth().getName() + " key!");
            player.setLocation(room.getSouth());
          }
        }
      } else if (this.taskDesc == "Go West") {
        if (room.getWest().getUnlocked()) {
          player.setLocation(room.getWest());
        } else {
          System.out.println("This room is locked.");
          if (player.getInventory().contains(room.getWest().getKey())) {
            System.out.println("...but you have the " + room.getWest().getName() +" key!");
            player.setLocation(room.getWest());
          }
        }
      }
    } else if (this.taskDesc == "Use Flashlight") {
      room.removeTask(this);
      Task office1 = new Task ("Inspect desk", "\033[3mYou walk over to the desk to find a folder with paper in it. You open it up and sift through, finding reports of ghost sightings and news clippings of successful exorcisms, and…\033[0m", false);
      Task office2 = new Task ("Inspect desk drawers", "\033[3mYou open up the first drawer to the desk and hear a little jangle as you pull it open.\033[0m", false);
      Task office3 = new Task("Inspect calendar", "\033[3mYou walk over to the calendar hanging on the wall. It’s set to April 19XX.\033[0m\nHuh. The year is right, but last I checked, it was July.", false);
      Item servQuarKey = new Item("Key to Servant's Quarters", room, false, false);
      Page diaryPage1 = new Page("Journal Entry: January 15th, 19XX", room, false, false, "Things are feeling normal again. We were able to find a new servant after the last one got up and left… God, what a pain! He wouldn’t shut up about “mistreatment” and “abuse of power…” easily the most annoying servant I’ve had in the house yet. Hopefully this new one will know when to shut up and keep invisible. His name is Fredrick or Freddy or something... Other than that, things have been fantastic. Work is good, the family is happy and healthy, what more could I ask for? The world is good to caring, hardworking people like me.", false);
      office1.setAward(diaryPage1);
      office2.setAward(servQuarKey);
      Task[] officeTasks = {office1, office2, office3};
      room.addTasks(officeTasks);
      room.changeFirstVisitDesc("\033[3mYou turn on the light to see a desk with items on it and a calendar on the wall. The room is neat unlike the rest of the house, but now with the lights on you can see some damage has been done to the doors as if someone was trying to get in…\033[0m");
      room.setUnvisited();
      player.removeFromInventory("Flashlight");
    } else if (this.taskDesc == "Open the window blinds") {
      room.removeTask(this);
      Task inspPageDR = new Task("Inspect the moonlit page", "\033[3mYou step over the broken china and carefully pick up the ominously illuminated piece of paper.\033[0m\n", false);
      Page exoPage2 = new Page("Journal Entry: Banshees", room, false, false, "a hunter who will stalk you before going in for a fatal hit. Will cause small orbs to float around the room. May make haunting wailing sounds. Be prepared to defend yourself by any means necessary, including violence. Banshees are nasty ones…", true);
      inspPageDR.setAward(exoPage2);
      room.addItem(exoPage2);
      room.addTask(inspPageDR);
    } else {
      room.setVisited();
      room.removeTask(this);
    }
  }



  
}