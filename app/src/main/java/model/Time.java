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
    setTime(dayCounter);
  }

  /**
   * Gets the day count value.
   *
   * @return - The day count.
   */
  public int getTime() {
    return dayCounter;
  }

  /**
   * Sets the day count number.
   *
   * @param dayNumber - The number of days.
   */
  public void setTime(int dayNumber) {
    this.dayCounter = dayNumber;
  }

  /**
   * Increments the day count.
   *
   * @param timeToAdvance - The time to increment.
   */
  public void incrementDayCounter(int timeToAdvance) {
    int currentDayNumber = this.getTime();

    currentDayNumber += timeToAdvance;

    setTime(currentDayNumber);
  }
}
