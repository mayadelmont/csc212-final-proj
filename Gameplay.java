import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

/** 
* Main code of the game. Initializes the game world and carries out
* gameplay.
* @authors <Maya Delmont and Rachel Yang>
* @version Fall 22
*/
class Gameplay {

  /** game execution */
  public static void main(String[] args) {

    cutscene("openingScene.txt");
    
    System.out.println("\n_____________________________________________\n\033[3m– the next day – \n\nYou tentatively approach the driveway of a very large house.\033[0m \nI can’t believe I’m doing this.\n\033[3mYou approach the entrance.\nYou reach for the doorknob.\nYou feel something behind you.\033[0m\nWhat the-\033[3mEverything goes black.\033[0m\n");

    //house and player initialization
    Room foyer = new Room("Foyer", "xx", true, "\033[3mYou have a searing headache.\033[0m \nOuch…my head hurts… \n\033[3mYou open your eyes slowly.\033[0m\nWhere…Where am I? \n \n\033[3mYou look around at what appears to be a Foyer. It looks dilapidated and there is trash on the ground. In front of you there is a carpet with a corner flipped up, and a page on the ground on the other side of the room. There is a door straight ahead. \n \nSomething feels off… \nYou pick up your bag, but it feels extremely light.\033[0m \nWhere are my things…?!");
    
    Room livingRoom = new Room("Living Room", "xx", true, "\033[3mYou enter into what appears to be a living room, but it doesn’t look very liveable. \nThere is a couch facing a fireplace, and a half-empty shelf with items scattered in front of it. \nThere is a door to the west, east, and right in front of you. \nThe room smells like burnt wood. \033[0m");
    
    Room library = new Room("Library", "xx", false, "\033[3mYou enter into a library. Most of the books are still on the shelves, except for a few on the floor. There is a door to the west and another to the north. It has a comfortable feel, almost familiar…\033[0m");
    
    Room diningRoom = new Room("Dining Room", "xx", true, "\033[3mYou enter into a dining room: it is a mess. \nThe table is flipped on its side, and there is broken china and cutlery all over the floor. \nThe windows have all the blinds drawn shut. \nThere is a door to the north.\033[0m");
    
    Room gameRoom = new Room("Game Room", "xx", true, "\033[3mYou enter into a game room, decked out with a pool table, foosball table, and a broken flat-screen television that appears to have a pool cue jammed through the center of it.  \nThere is a box of toys for a small child. \033[0m");
    
    Room office = new Room("Office", "", false, "\033[3mIt is dark and difficult to see anything in here. It smells burnt.  \nYou feel against the wall and touch another doorknob.\033[0m\nIf only I had a light of some sort… ");
    
    Room bedroom = new Room("Bedroom", "xx", true, "\033[3m You enter into a… bedroom? It is entirely destroyed. \nIf it weren’t for the bed, you wouldn’t know what this room used to be. \nThe wallpaper is peeling, the bedspreads are ripped apart, the books and shelves have been emptied onto the floor, clothing strewn across the room, and despite the lack of any evidence of a fire, it smells heavily of burning. \033[0m \nWhat the hell happened here…? \n\033[3mThere is a door to the north.\033[0m");
    
    Room bathroom = new Room("Bathroom", "xx", true, "\033[3m You enter into a bathroom. There is a clawfoot tub, a window, and a sink with a mirror.\nSomething about this room feels…safe.\033[0m");
    
    Room kitchen = new Room("Kitchen", "xx", true, "\033[3mYou enter into a kitchen. It is clean, save for a few dishes in the sink. There is a knife laying on the countertop, and a door to the east.\033[0m");
    
    Room servantsQuar = new Room("Servant's Quarters", "xx", false, "\033[3mYou unlock the door and enter into what appears to be a servant's quarters. \nIt is in pristine condition; the bed is made, the floor is clear of items, and it has a fresh smell. \nIn addition to the bed and shelves, there is also a desk with a portrait, a diary, and a plant on it. \nIt is a red chrysanthemum. \nThere is a door to the north with a lock on it, radiating and ominous energy. \033[0m\nThat must be it…\033[3m\nThis room feels unfamiliar.\033[0m");

    Room basement = new Room("Basement", "xx", false, "xx");

    Room[] rooms = {bathroom, basement, office, bedroom, kitchen, servantsQuar, gameRoom, library, livingRoom, diningRoom, foyer};
    //set room pointers,, initialize house structure
    foyer.setNorth(livingRoom);
    livingRoom.setSouth(foyer);
    livingRoom.setWest(library);
    library.setEast(livingRoom);
    library.setWest(gameRoom);
    gameRoom.setEast(library);
    library.setNorth(office);
    office.setSouth(library);
    office.setEast(bedroom);
    bedroom.setWest(office);
    bedroom.setSouth(livingRoom);
    livingRoom.setNorth(bedroom);
    bedroom.setNorth(bathroom);
    bathroom.setSouth(bedroom);
    livingRoom.setEast(diningRoom);
    diningRoom.setWest(livingRoom);
    diningRoom.setNorth(kitchen);
    kitchen.setSouth(diningRoom);
    kitchen.setEast(servantsQuar);
    servantsQuar.setWest(kitchen);
    servantsQuar.setNorth(basement);
    basement.setSouth(servantsQuar);
    
    //create room change tasks (ex. Go north)
    foyer.setDirectionTasks();
    livingRoom.setDirectionTasks();
    library.setDirectionTasks();
    diningRoom.setDirectionTasks();
    gameRoom.setDirectionTasks();
    office.setDirectionTasks();
    bathroom.setDirectionTasks();
    bedroom.setDirectionTasks();
    kitchen.setDirectionTasks();
    servantsQuar.setDirectionTasks();

    //item creation
    Key libKey = new Key("Key to Library", foyer, false, false, library);
    Key officeKey = new Key("Key to Office", bedroom, false, false, office);
    Key servantsQuarKey = new Key("Key to the Servant's Quarters", office, false, false, servantsQuar);
    Key baseKey = new Key("Key to Basement", servantsQuar, false, false, basement);
    Page exoPage1 = new Page("Journal Entry: Poltergeists", foyer, false, false, "Poltergeist: a rowdy ghoul who physically manipulates objects around you, from making noise to hurling them at you. May flicker lights. They have a very noticeable presence. If one is in the room with you, you'll know. If attacked, a silver weapon such as a knife will be the best choice. Call a priest to defeat, use weapon in meantime.", true);
    Page exoPage3 = new Page("Journal Entry: Vengeful Spirit", gameRoom, false, false, "Vengeful One: a spirit with unfinished business with a specific individual. Will take over a territory from their previous life while exacting revenge on the individual by manipulating the physical environment. Common environmental manipulations include breaking or scratching photographs of the individual, tossing personal items of the individual on the ground, etc. Do not approach with hostility!! Under no circumstances, present it with a knife, they can sense it on your person. Person of interest must make amends to defeat.", true);
    Item flashlight = new Item("Flashlight", gameRoom, false, false);
    Page diaryPage2 = new Page("Journal Entry: April 5th, 19XX", servantsQuar, false, false, "Tricky case today -- a jinn was haunting a home across town, the family got scared off and just put it up for sale. Almost got it confused with a vengeful one -- that would not have been fun to deal with. Luckily just a frigid bastard jinn. Should’ve brought my heavy coat! \nI need to remember to call the electrician. The wiring in the living room has been a bit tricky of late.\nI caught the servant boy sneaking out tonight. Probably to see the wench that serves the Astors. They’ve been awfully close of late. I want none of it. I’ll be locking the door to his quarters now at night. The last thing I need is to be held responsible for a pregnant servant girl.", false);
    Page diaryPage3 = new Page("Journal Entry: February 3rd, 19XX", library, false, false, "Another successful day of business — caught one in the Astor home and dined with the family after. Scrumptious lobster and an excellent peach salad. \nThe servant boy continues to be incompetent. My bath water had no oils mixed in and my breakfast came with only one poached egg. What is the point of hiring servants if they won’t serve you properly? And to interfere with my bath time? My one and only place of peace and quiet. I sent him to bed with no supper. He better get his act together.", false);
    Item knife = new Item("Knife", kitchen, false, false);

    //assign items to room
    foyer.addItem(exoPage1);
    foyer.addItem(libKey);
    bedroom.addItem(officeKey);
    gameRoom.addItem(exoPage3);
    gameRoom.addItem(flashlight);
    servantsQuar.addItem(diaryPage2);
    servantsQuar.addItem(baseKey);
    library.addItem(diaryPage3);
    kitchen.addItem(knife);
    library.setKey(libKey);
    office.setKey(officeKey);
    basement.setKey(baseKey);
    servantsQuar.setKey(servantsQuarKey);
    
    //FOYER TASKS
    Task inspBag = new Task("Inspect your bag", "\033[3mYou open your bag to find your journal is missing pages and your things are missing! \nYour journal still contains a map and list of your current inventory. \nDespite your hazy memory, you fortunately remember that you can open your map by pressing M at any time, your inventory by pressing I at any time, and your journal by pressing J at any time.\033[0m", false);
    Task inspPageF = new Task("Inspect the page on the ground", "\033[3mYou pick up the piece of paper from the ground.\033[0m", false);
    inspPageF.setAward(exoPage1);      
    Task inspCarpet = new Task("Inspect lifted carpet", "\033[3mYou lift up the corner of the carpet and see a shiny object.\033[0m", false);
    inspCarpet.setAward(libKey); 
    Task inspTrash = new Task("Inspect the trash on the ground", "\033[3mYou pick up and move around some of the trash on the ground. \nIt starts to smell. \nThat was gross.\033[0m", false);
    Task[] tasks = {inspBag, inspPageF, inspCarpet, inspTrash};
    foyer.addTasks(tasks);

    //LIVING ROOM TASKS
    Task inspFirePlace = new Task("Inspect the Fire Place", "\033[3mThe fireplace itself is charred and damaged, extending out into the room from there.\033[0m \nLooks like something happened here…", false);
    Task napInLR = new Task("Take a nap on the couch", "\033[3mYou lay down on the couch and decide to rest your eyes for a bit.\033[0m\n...\nThat was nice. Now back to business.", false);
    Task inspScatter = new Task("Inspect Scattered Items", "\033[3mThey appear to have fallen from the shelf, but only some of them… \namong the items on the ground there is a charred family portrait with the faces scratched out.\033[0m \nThat is so creepy...", false);    
    Task[] tasks2 = {inspFirePlace, napInLR, inspScatter};
    livingRoom.addTasks(tasks2);
    
    //DINING ROOM TASKS
    Task openBlinds = new Task("Open the window blinds", "\033[3m You step over the broken china and pull apart the window blinds.\nIt is too dark to see anything outside, but somehow, the moon shines a beam of light into the room, possibly reflected or enhanced by the glass of the window and broken china on the floor.\nThe light shines directly onto a piece of paper on the floor\033[0m\nHuh, I didn’t see that before…", false);
    Task inspBrokenChina = new Task("Inspect broken china", "\033[3mThere is broken glass and ceramic scattered across the floor, as well as some high quality forks and knives.\033[0m\nJeez, it almost looks like someone threw a tantrum in here...", false);
    Task[] tasks3 = {openBlinds, inspBrokenChina};
    diningRoom.addTasks(tasks3);
      
    //BEDROOM TASKS
    Task inspClothing = new Task("Inspect clothing pile", "\033[3mIn the pile of clothing you manage to pull out a... familiar jacket. It is your size. You instinctively reach into the pocket and feel a small metal object. \033[0m", false);
    inspClothing.setAward(officeKey);
    bedroom.addTask(inspClothing);
    
    //LIBRARY TASKS
    Task inspFloorBooks = new Task("Inspect the books on the floor", "\n\033[3mYou pick up one of the books on the floor. \033[0m\nI've read this one... actually it's one of my favorites. \n\033[3mYou start to put it back on the shelf when a page slips out.\033[0m", false);
    inspFloorBooks.setAward(diaryPage3);
    library.addTask(inspFloorBooks);
    
    //GAME ROOM TASKS
    Task inspToyBox = new Task("Inspect the box of toys", "\033[3mYou reach into the toy box and push aside a broken doll, a broken toy truck until... \033[0m", false);
    inspToyBox.setAward(flashlight);
    Task inspTV = new Task("Inspect broken television", "\033[3mYou get closer to the damaged television, and see that the pool cue jammed into it is also pinning a piece of paper into the shattered screen.\033[0m", false);
    inspTV.setAward(exoPage3);
    Task playPool = new Task("Play some pool", "\033[3m...By your self? \nDon't be weird. \033[0m \nI need to focus on getting out of here...", false);
    Task[] tasks4 = {inspToyBox, inspTV, playPool};
    gameRoom.addTasks(tasks4);

    //BATHROOM TASKS
    Task inspMirror = new Task("Inspect mirror", "\033[3mYou look into the mirror at yourself. You feel pensive. Staring at yourself, you feel a wave of familiarity and peace and comfort. \nAlmost enough to forget that you are trying to escape from this mysterious nightmare you’ve awoken into. \nInexplicably, you feel a pang of guilt. \033[0m", false);
    Task inspWindow = new Task("Inspect window", "\033[3mYou look outside the bathroom window.\nFrom this side of the house, you can see the full moon shining into the room. It's beautiful. For a moment, you forget your current peril and feel relaxed and in your own space.\nYou get an eerie sense of deja vu.\033[0m", false);
    Task inspBath = new Task("Inspect bathtub", "\033[3mYou peer into the claw foot tub. There are spiders crawling out of the drain. \nIt hasn't been used in a long time. \033[0m", false);
    Task[] tasks5 = {inspMirror, inspWindow, inspBath};
    bathroom.addTasks(tasks5);
    
    //KITCHEN TASKS
    Task takeKnife = new Task("Take the knife", "\033[3mYou hold up the knife. It glistens in the light of the room. Careful, it's sharp.\033[0m", false);
    takeKnife.setAward(knife);
    Task inspDishes = new Task("Inspect the dishes", "\033[3mThe dishes are piled up in the sink, some covered in mold, emitting an unpleasent smell as you get closer. Best not to touch them.\033[0m", false);
    Task[] tasks6 = {takeKnife, inspDishes};
    kitchen.addTasks(tasks6);

    //SERVANTS QUARTERS TASKS
    Task inspBed = new Task("Inspect made bed", "\033[3mYou brutishly unmake the neatly made bed, pulling up the covers and lifting up the pillows.\nYou feel guilty. But...\033[0m", false);
    inspBed.setAward(baseKey);
    Task inspDiary = new Task("Inspect the diary", "\033[3mYou pick up the diary and hold it in your hands. You hesitate.\033[0m \n This feels wrong...\n\033[3mYour curiosity overtakes your hesitance. You open the diary that doesn't belong to you. A loose page falls out, written in handwriting different from the pages in the diary.\033[0m", false);
    inspDiary.setAward(diaryPage2);
    Task inspPortrait = new Task("Inspect framed photograph", "\033[3mYou pick up the framed photograph on the desk. \nThe glass has been shattered with scratch marks carved out into the man in the photo...\nThe moment you even glance at his face you drop the photo in shock.\033[0m\nThat...that's me in the photo...\n\033[3mYou quickly shake it off.\nYou need to get out of here...\033[0m", false);
    Task inspPlant = new Task("Inspect the plant", "\033[3mYou inspect the beautiful red flower sitting on the windowsill.\nSomehow, it is in pristine condition and well-watered, despite the disarray of the rest of the house\033[0m\nA chrysanthemum...\nI always hated these.\nAlthough I think I remember someone who loved them...\n\033[3mYour head pangs.\033[0m", false);
    Task[] tasks7 = {inspBed, inspDiary, inspPortrait, inspPlant};
    servantsQuar.addTasks(tasks7);


    Person Player = new Person();
    Player.setLocation(foyer);
    enterRoom(Player, foyer, rooms); // initializes game loop

    //setting ending depending on which items the player has picked up
    if (Player.getLocation() == basement) {
      if (Player.getInventory().contains(diaryPage2) && Player.getInventory().contains(diaryPage3)) {
        for (int i = 0; i < Player.getInventory().size(); i++){
          if (Player.getInventory().get(i).getName() == "Journal Entry: January 15th, 19XX") {
            if (Player.getInventory().contains(knife)) {
              cutscene("finalScene1Knife.txt");
            } else {
              cutscene("finalScene1.txt");
            }
          }
        }
      } else {
        cutscene("finalScene2.txt");      
      }
    }
    System.out.println("\n\n\033[3mVENGEANCE SERVED\n\nCreated by Maya Delmont and Rachel Yang for @CSC212, Fall 2022\033[0m\n\nThank you for playing!");
  }


  
  /** 
  * Primary game loop. Prints task list for player's current room and
  * allows them to perform tasks, move to other rooms, or access their inventory, 
  * journal, and map. Loop ends when player enters the basement for the final
  * scene.
  * @param player -- the player with their items
  * @param room -- the current room
  * @param rooms -- the list of all the rooms, so that a map can be made with
  *   the current state of the house / how much the player has visited
  * @return no return value
  */
  public static void enterRoom(Person player, Room room, Room[] rooms) {
    if (room.getName() == "Basement"){
      System.out.println("\033[3mYou open the door and walk down the basement stairs...\033[0m\n");
    } else {
      Boolean hasFlashlight = false;
      for (int i = 0; i < player.getInventory().size(); i++){
        if (player.getInventory().get(i).getName() == "Flashlight"){
          hasFlashlight = true;
        }
      }
      if (room.getName().equals("Office")) {
        if (hasFlashlight){
          Task useFlashlight = new Task("Use Flashlight", "\033[3mYou switch on the flashlight you found and are able to make out the interior of the room, as well as a light switch on the opposite side of the room.\nYou flip it on and it works!\n\nAt the same time, the flashlight starts to flicker before going out\033[0m\nWow, good timing!\n \033[3mYou set it on the floor.\033[0m", false);
          room.addTask(useFlashlight);
        }
      } 
      else if (room.getName().equals("Kitchen")) {
        for (int i = 0; i < player.getInventory().size(); i++){
          if (player.getInventory().get(i).getName() == "Key to Servant's Quarters"){
            room.getEast().unlock();
          }
        }
      }
      if (room.getVisited() == false) { //prints description of room on first entry
        System.out.println("_____________________________________________\n");
        //System.out.println(room.getInterior());
        System.out.println(room.getFirstVisitDesc());
        System.out.println("\033[3mWhat would you like to do?\033[0m");
        room.setVisited();
      } else {
        System.out.println("_____________________________________________\n");
        //System.out.println(room.getInterior());
        System.out.println("\nYou are in the " + room.getName() + ".");
        System.out.println("\033[3mWhat would you like to do?\033[0m\n");
      }
      Scanner input = new Scanner(System.in);
      room.getTaskDescs();  
      String answer = input.nextLine();
      //access inventory
      if (answer.equals("i") || answer.equals("I")) {
        player.printInventory();
        enterRoom(player, room, rooms);
      } else if (answer.equals("m") || answer.equals("M")) {
        //prints current map
        ArrayList<String> map = map(rooms);
        for (String m: map) {
          System.out.println(m);
        }
        enterRoom(player, room, rooms);
      } else if (answer.equals("j") || answer.equals("J")){  //access journal
        player.accessJournal();
        enterRoom(player, room, rooms);
      } else if (answer.length() == 1 && (int)answer.charAt(0) >= 49 && (int)answer.charAt(0) <=(48+room.getTasks().size())) {   //task selection and execution
        System.out.println("xx");
        int intAnswer = Integer.parseInt(answer);
        room.getTasks().get(intAnswer-1).setComplete(player, room);
        enterRoom(player, player.getLocation(), rooms);
      } else {
        System.out.println("\n\033[3mHint: Select a task by number, or access your Inventory by pressing I, or access your Map by pressing M, or access your Journal to read entries by pressing J.\033[0m\n");
        enterRoom(player, room, rooms);
      }
    }
  }
  
  /** 
  * Asks user a question and returns a boolean based on
  * 1/2 response. Reasks if a non 1/2 answer is given.
  * @param prompt - question to be asked
  * @return boolean - true if yes, false if no
  */ 
  public static boolean ask(String prompt) {
    Scanner input = new Scanner(System.in);
    System.out.println(prompt);
    String answer = input.nextLine();
    if (answer.equals("1")) {
      return true;
    } else if (answer.equals("2")) {
      return false;
    } else {
      System.out.println("\033[3mHint: Pick an action by typing the corresponding number.\033[0m");
      return ask(prompt);
    }
  }

  /** 
  * Creates a decision tree based on a given file to act as dialogue/scene
  * @param fileName - the file to be read
  */
  public static void cutscene(String fileName) {
    DecisionTree node = DecisionTree.input(fileName);
    DecisionTree rootSave = node;
    boolean done = false;
    while (!done) {
      String text = node.getData();
      String formattedText = reformat(text);
      if (ask(formattedText)) {
        if (node.isLeaf()) {
          done = true;
        } else {
          node = node.getLeft();
        }
      } else {
        if (node.isLeaf()) {
          done = true;
        } else {
          node = node.getRight();
        }
      }
    }
  }

  /** 
  * Checks which rooms have been accessed by the player and creates
  * a set of strings representing the map of accessed rooms
  * @param rooms -- all the rooms of the game world
  * @return Strings representing the accessed rooms
  */
  public static ArrayList<String> map(Room[] rooms) {
    String noRoom = "           ";
    String roomBound = "|---------|";
    String roomDoor = "|---   ---|";
    String room = "|         |";
    String roomDoorL = "          |";
    String roomDoorR = "|         |";
    ArrayList<String> map = new ArrayList<String>();

    if (rooms[0].getVisited() || rooms[1].getVisited()) {
      String line = noRoom+noRoom;
      String line2 = line;
      String line3 = line;
      String line3b = line;
      Room bathroom = rooms[0];
      if (bathroom.getVisited()) {
        line2 +="|bathroom |";
        line3 += room;
        line += roomBound;
        line3b += roomDoor;
      } else {
        line+=noRoom;
        line2+= noRoom;
        line3+= noRoom;
        line3b +=noRoom;
      }
      line+=noRoom;
      line2+=noRoom;
      line3+=noRoom;
      if (rooms[1].getVisited()) {
        line += roomBound;
        line2 +="|basement |";
        line3+=room;
        line3b+=roomDoor;
      }
      map.add(line);
      map.add(line2);
      map.add(line3);
      // map.add(line3b);
    }
    if (rooms[2].getVisited() || rooms[3].getVisited() || rooms[4].getVisited() || rooms[5].getVisited()) {
      String line4 = noRoom;
      String line5 = line4;
      String line6 = line4;
      String line7 = line4;
      if (rooms[2].getVisited()) {
        line4+=roomBound;
        line5+="| office   ";
        line6+=roomDoorR;
        line7+=roomDoor;
      } else {
        line4+= noRoom;
        line5+= noRoom;
        line6 += noRoom;
        line7+= noRoom;
      }
      if (rooms[3].getVisited()) {
        line4+=roomDoor;
        line5+="  bedroom |";
        line6+= roomDoorL;
        line7+= roomDoor;
      } else {
        line4+= noRoom;
        line5+= noRoom;
        line6 += noRoom;
        line7+= noRoom;
      }
      if (rooms[4].getVisited()) {
        line4+=roomBound;
        line5+="| kitchen  ";
        line6+=roomDoorR;
        line7+=roomDoor;
      } else {
        line4+= noRoom;
        line5+= noRoom;
        line6 += noRoom;
        line7 += noRoom;
      }
      if (rooms[5].getVisited()) {
        line4+=roomBound;
        line5+=" servant's|";
        line6+=" quarters |";
        line7+= roomBound;
      } else {
        line4+= noRoom;
        line5+= noRoom;
        line6 += noRoom;
        line7+= noRoom;
      }
      map.add(line4);
      map.add(line5);
      map.add(line6);
      if (rooms[5].getVisited()) {
        map.add(line7);
      }
    }
    if (rooms[8].getVisited()) {
      String line8 = "";
      String line9 = "";
      String line10 = "";
      String line11 = "";
  
      if (rooms[6].getVisited()) {
        line8+=roomBound;
        line9+="|  game    ";
        line10+="|  room    ";
        line11+=roomBound;
      } else {
        line8+=noRoom;
        line9+= noRoom;
        line10+= noRoom;
        line11+= noRoom;
      }
      if (rooms[7].getVisited()) {
        line8+=roomDoor;
        line9+="  library  ";
        line10+=noRoom;
        line11+=roomBound;
      } else {
        line8+=noRoom;
        line9+= noRoom;
        line10+= noRoom;
        line11+= noRoom;
      }
      if (rooms[8].getVisited()) {
        line8+=roomDoor;
        line9+="  living   ";
        line10+="   room    ";
        line11+=roomDoor;
      } else {
        line8+=noRoom;
        line9+= noRoom;
        line10+= noRoom;
        line11+= noRoom;
      }
      if (rooms[9].getVisited()) {
        line8+=roomDoor;
        line9+="  dining  |";
        line10+="   room   |";
        line11+=roomBound;
      } else {
        line8+=noRoom;
        line9+= noRoom;
        line10+= noRoom;
        line11+= noRoom;
      }
      
      map.add(line8);
      map.add(line9);
      map.add(line10);
      map.add(line11);
    }    
    String line12 = noRoom+noRoom;
    String line13 = noRoom+noRoom;
    String line14 = noRoom+noRoom;
    String line15 = noRoom+noRoom;
    if (rooms[10].getVisited()) {
      line12+=roomDoor;
      line13+="|  foyer  |";
      line14+=room;
      line15+=roomBound;
    }

    if (!rooms[8].getVisited()) {
      map.add(line12);
    }
    map.add(line13);
    map.add(line14);
    map.add(line15);
    
    return map;
  }

  /** 
  * Method to parse through a given string 
  * formatted with adequate keys to print the 
  * text to console with the desired formatting.
  * Typically fed in single line from a txt file.
  * @param text - string to be reformatted
  * @return - reformatted string to be printed to console.
  */  
  public static String reformat(String text) {
    String newText = "";
    for (int i = 0; i < text.length(); i++) {
      if (text.charAt(i) == '(') {
        newText += "\033[3m";
      } else if (text.charAt(i) == ')') {
        newText += "\033[0m";
      } else if (text.charAt(i) == '/') {
        newText += "\n";
      } else {
        newText += text.charAt(i);
      }
    }    
    return newText;
  }



  
  
}