import java.util.Scanner;
import java.util.ArrayList;

/**
*  Class that defines a person that
*  exists within the game, including the 
*  representation of the player.
*
*  @authors <Maya Delmont and Rachel Yang>
*  @version Fall 2022
*/

public class Person {

  /** Room where player is located */
  private Room currentLoc;

  /** Person name*/
  private String name;

  /** ArrayList of the person's possessions */
  private ArrayList<Item> inventory;

  /** Constructor for a Person */
  public Person(){
    this.currentLoc = null;
    this.name = null;
    ArrayList<Item> inventory = new ArrayList<Item>();
    this.inventory = inventory;
  }

  /** location setter */
  public void setLocation(Room currentRoom) {
    this.currentLoc = currentRoom;
  }

  /** location getter */
  public Room getLocation() {
    return currentLoc;
  }
  
  /** name setter */
  public void setName(String name) {
    this.name = name;
  }

  /** adds item to inventory */
  public void addToInventory(Item item) {
    this.inventory.add(item);
  }

  /** removes item from inventory */
  public void removeFromInventory(String itemName) {
    for (int i = 0; i < this.getInventory().size(); i++){
        if (this.getInventory().get(i).getName() == itemName){
          this.inventory.remove(this.inventory.get(i));
        }
      }
  }

  /** inventory getter */
  public ArrayList<Item> getInventory() {
    return this.inventory;
  }

  /** prints inventory when player requests to access inventory */
  public void printInventory(){
    System.out.println("\nINVENTORY:");
    System.out.println("________________________");
    for (int i = 0; i < this.inventory.size(); i++){
      System.out.println(this.inventory.get(i).getName());
    }
    System.out.println("________________________");
  }

  /** getter for journal pages */
  public ArrayList<Page> getJournalPages() {
    ArrayList<Page> obtainedPages = new ArrayList<Page>();
    for (int i = 0; i < this.inventory.size(); i++){
      if (this.inventory.get(i) instanceof Page) {
        Page obtainedPage = (Page)this.inventory.get(i);
        obtainedPages.add(obtainedPage); 
      }
    }
    return obtainedPages;
  }

  /** allows player to access journal by displaying all journal
  * pages in a list and using input numbers to display the requested
  * page or close the journal/end the method when player is finished
  */
  public void accessJournal(){
    ArrayList<Page> obtainedPages = this.getJournalPages();
    System.out.println("\nJOURNAL:\n________________________");
    for (int i = 0; i < obtainedPages.size(); i++) {
      System.out.println((i+1) + " " + obtainedPages.get(i).getName());
    }
    System.out.println("________________________\n\033[3mHint: Select a page by number to read it, or exit your Journal by typing 'X.'\033[0m\n");
    if (obtainedPages.size() == 0) {
        System.out.println("\n\033[3mYou don't have any pages!\nYou close the journal.\033[0m");
    } else {
      Scanner input = new Scanner(System.in);
      String answer = input.nextLine();
      if (answer.length() == 1 && (int)answer.charAt(0) >= 48 && (int)answer.charAt(0) <=57) {
        int intAnswer = Integer.parseInt(answer);
        if (intAnswer > obtainedPages.size()) {
          System.out.println("\033[3mHint: Select a page by number to read it, or exit your Journal by typing 'X.'\033[0m\n");
          accessJournal();
        } else {
          System.out.println("\n" + obtainedPages.get(intAnswer-1).getName() + ":\n" + obtainedPages.get(intAnswer-1).getContents());
          accessJournal();
        }
      } else if (answer.equals("X") || answer.equals("x")) {
        System.out.println("\n\033[3mYou close the journal.\033[0m");
      } else {
        System.out.println("\n\033[3mHint: Select a page by number to read it, or exit your Journal by typing 'X.'\033[0m\n");
        accessJournal();
      }      
    }
  }

  

}