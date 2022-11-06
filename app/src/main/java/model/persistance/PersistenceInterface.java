package model.persistance;

/**
 * Responsible for different types of persistence.
 */
public interface PersistenceInterface {

  /*
   * Loads some data.
   */
  public void load();

  /*
   * Saves some data.
   */
  public void save();
}
