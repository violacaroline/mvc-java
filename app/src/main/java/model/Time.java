package model;

/**
 * Represents a time counter.
 */
public class Time {
  private int dayCounter = 0;

  /**
   * Instansiates a time object.
   */
  public Time() {
    setCounter(dayCounter);
  }

  /**
   * Gets the day count value.
   *
   * @return - The day count.
   */
  public int getCounter() {
    return dayCounter;
  }

  /**
   * Sets the day count number.
   *
   * @param dayNumber - The number of days.
   */
  public void setCounter(int dayNumber) {
    this.dayCounter = dayNumber;
  }

  /**
   * Increments the day count.
   *
   * @param timeToAdvance - The time to increment.
   */
  public void incrementDayCounter(int timeToAdvance) {
    int currentDayNumber = this.getCounter();

    currentDayNumber += timeToAdvance;

    setCounter(currentDayNumber);
    System.out.println();
  }
}
