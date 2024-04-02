
/**
*  Class that defines a page, extends class Item.
*  Pages contain text contents.
*
*  @authors <Maya Delmont and Rachel Yang>
*  @version Fall 2022
*/
 public class Page extends Item {

  /** String that is printed when user choses to read the contents of page */
  private String contents;

  /** boolean that indicates whether the page is a diary entry(F) or exorcism information(T) */
  private Boolean exorcismStat;

  /** constructor */
  public Page(String name, Room location, Boolean pickedUp, Boolean prevAccess, String contents, Boolean exorcismStat){
    super(name, location, pickedUp, prevAccess);
    this.contents = contents;
    this.exorcismStat = exorcismStat;
  }

  /** contents getter */
  public String getContents() {
    return this.contents;
  }

  /** exorcism status getter */
  public Boolean getExorcismStat() {
    return this.exorcismStat;
  }
  
  
 }