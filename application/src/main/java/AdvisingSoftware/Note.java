package AdvisingSoftware;

import java.util.Date;

/**
 * Creates a note for the advisor
 * @author Garrett Spillman (@Spillmag), Lia Zhao (@zhaolia9), Stephon Johnson (@stephonj), Yasmine Kennedy (@yask8)
 */
public class Note {
  /**
   * Attributes
   */
  private String note;
  private Date date;
  /**
   * Notes Constructor
   * @param note the note 
   * @param date the date
   */
  public Note(String note, Date date) {
    this.note = note;
    this.date = date;
  }
  /**
   * Gets the note
   * @return the note
   */
  public String getNote() {
    return note;
  }
  /**
   * Gets the date
   * @return the date
   */
  public Date getDate() {
    return date;
  }
  /**
   * Displays the note
   * @return the note
   */
  public String toString() {
    return "Note: " + note + "\tDate: " + date;
  }
}
