import com.google.common.graph.*;
import java.util.Scanner;

/** 
* Contains some tests used during development and testing
* @authors <Maya Delmont and Rachel Yang>
* @version Fall 22
*/

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    //graphTest();
    // System.out.println("|\t|\t|\n|   |   |");
    
  }


  
  public static void enterRoom(Room room) {
    if (room.getVisited() == false) {
      System.out.println(room.getInterior());
      System.out.println(room.getFirstVisitDesc());
      System.out.println("\033[3mWhat would you like to do?\033[0m");
      room.getTaskDescs();
    } else {
      System.out.println(room.getInterior());
      System.out.println("You have entered the " + room.getName() + ".");
      System.out.println("\033[3mWhat would you like to do?\033[0m");
      room.getTaskDescs();
    }
  }

  /** Testing using Guava / graphs to create the house and the
  * doorways between them.
  */
  public static void graphTest() {
    Room foyer = new Room("Foyer", "xx", true, "\033[3mYou have a searing headache.\033[0m \nOuch…my head hurts… \n\033[3mYou open your eyes slowly.\033[0m\nWhere…Where am I? \n \n\033[3mYou look around at what appears to be a Foyer. It looks dilapidated and there is trash on the ground. In front of you there is a carpet with a corner flipped up, and a page on the ground on the other side of the room. There is a door straight ahead. \n \nSomething feels off… \nYou pick up your bag, but it feels extremely light.\033[0m \nWhere are my things…?!");
    
    Room livingRoom = new Room("Living Room", "xx", true, "\033[3mYou enter into what appears to be a living room, but it doesn’t look very liveable. \nThere is a couch facing a fireplace, and a half-empty shelf with items scattered in front of it. \nThere is a door to the west, east, and right in front of you. \nThe room smells like burnt wood. \033[0m");
    
    Room library = new Room("Library", "xx", false, "\033[3mYou enter into a library. Most of the books are still on the shelves, except for a few on the floor. There is a door to the west and another to the north. It has a comfortable feel, almost familiar…\033[0m");
    
    Room diningRoom = new Room("Dining Room", "xx", true, "\033[3mYou enter into a dining room: it is a mess. \nThe table is flipped on its side, and there is broken china and cutlery all over the floor. \nThe windows have all the blinds drawn shut. \nThere is a door to the north.\033[0m");
    
    Room gameRoom = new Room("Game Room", "xx", true, "\033[3mYou enter into a game room, decked out with a pool table, foosball table, and a broken flat-screen television that appears to have a pool cue jammed through the center of it.  \nThere is a box of toys for a small child. \033[0m");
    
    Room office = new Room("Office", "", false, "\033[3mIt is dark and difficult to see anything in here. It smells burnt.\033[0m  \nIf only I had a light of some sort… ");
    
    Room bedroom = new Room("Bedroom", "xx", true, "\033[3m You enter into a… bedroom? It is entirely destroyed. \nIf it weren’t for the bed, you wouldn’t know what this room used to be. \nThe wallpaper is peeling, the bedspreads are ripped apart, the books and shelves have been emptied onto the floor, clothing strewn across the room, and despite the lack of any evidence of a fire, it smells heavily of burning. \033[0m \nWhat the hell happened here…? \n\033[3mThere is a door to the north.\033[0m");
    
    Room bathroom = new Room("Bathroom", "xx", true, "\033[3m You enter into a bathroom. There is a clawfoot tub, a window, and a sink with a mirror.\nSomething about this room feels…safe.\033[0m");
    
    Room kitchen = new Room("Kitchen", "xx", true, "\033[3mYou enter into a kitchen. It is clean, save for a few dishes in the sink. There is a knife laying on the countertop, and a door to the east.\033[0m");
    
    Room servantsQuar = new Room("Servants Quarters", "xx", false, "\033[3mYou enter into what appears to be a servants quarters. \nIt is in pristine condition; the bed is made, the floor is clear of items, and it has a fresh smell. \nIn addition to the bed and shelves, there is also a desk with a portrait, a diary, and a plant on it. \nIt is a red chrysanthemum. \nThere is a door to the north with a lock on it, radiating and ominous energy. \033[0m\nThat must be it…\033[3m\nThis room feels unfamiliar. \033[0m");

    Room basement = new Room("Basement", "xx", false, "xx");
    
    MutableGraph<Room> map = GraphBuilder.undirected().build();
    map.addNode(foyer);
    map.putEdge(foyer, livingRoom);
    map.putEdge(livingRoom, library);
    map.putEdge(livingRoom, bedroom);
    map.putEdge(livingRoom, diningRoom);
    map.putEdge(library, gameRoom);
    map.putEdge(library, office);
    map.putEdge(bedroom, office);
    map.putEdge(bedroom, bathroom);
    map.putEdge(diningRoom, kitchen);
    map.putEdge(kitchen, servantsQuar);
    map.putEdge(servantsQuar, basement);

    // System.out.println(map);

    MutableNetwork<Room, Door> mapnet = NetworkBuilder.directed().build();
    mapnet.addNode(foyer);
    mapnet.addEdge(foyer, livingRoom, new Door("North"));
    mapnet.addEdge(livingRoom, foyer, new Door("South"));
    mapnet.addEdge(livingRoom, library, new Door("West"));
    mapnet.addEdge(library, livingRoom, new Door("East"));
    mapnet.addEdge(livingRoom, bedroom, new Door("North"));
    mapnet.addEdge(bedroom, livingRoom, new Door("South"));
    mapnet.addEdge(livingRoom, diningRoom, new Door("East"));
    mapnet.addEdge(diningRoom, livingRoom, new Door("West"));
    mapnet.addEdge(library, gameRoom, new Door("West"));
    mapnet.addEdge(gameRoom, library, new Door("East"));
    mapnet.addEdge(library, office, new Door("North"));
    mapnet.addEdge(office, library, new Door("South"));
    mapnet.addEdge(bedroom, office, new Door("West"));
    mapnet.addEdge(office, bedroom, new Door("East"));
    mapnet.addEdge(bedroom, bathroom, new Door("North"));
    mapnet.addEdge(bathroom, bedroom, new Door("South"));
    mapnet.addEdge(diningRoom, kitchen, new Door("North"));
    mapnet.addEdge(kitchen, diningRoom, new Door("South"));
    mapnet.addEdge(kitchen, servantsQuar, new Door("East"));
    mapnet.addEdge(servantsQuar, kitchen, new Door("West"));
    mapnet.addEdge(servantsQuar, basement, new Door("North"));
    mapnet.addEdge(basement, servantsQuar, new Door("South"));

    // System.out.println(mapnet);

    for (Room r: mapnet.nodes()) {
      for (Door d: mapnet.outEdges(r)) {
        String direction = d.getDirection();
        if (direction.equals("North")) {
          r.addTask(new Task("Go North", "", true));
        } else if (direction.equals("South")) {
          r.addTask(new Task("Go South", "", true));
        } else if (direction.equals("East")) {
          r.addTask(new Task("Go East", "", true));
        } else if (direction.equals("West")) {
          r.addTask(new Task("Go West", "", true));
        }
      }
    }

    for (Room r: mapnet.nodes()) {
      System.out.println(r);
      r.getTaskDescs();
    }
  }
}