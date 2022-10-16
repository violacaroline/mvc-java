package model;

/**
 * Represents a time counter.
 */
public class Time {
  private int dayCounter = 0;

  /**
   * Instansiates a time object.
   */
  protected Time() {
    setTime(dayCounter);
  }

  /**
   * Gets the day count value.
   *
   * @return - The day count.
   */
  protected int getTime() {
    return dayCounter;
  }

  /**
   * Sets the day count number.
   *
   * @param dayNumber - The number of days.
   */
  protected void setTime(int dayNumber) {
    this.dayCounter = dayNumber;
  }

  /**
   * Increments the day count.
   *
   * @param timeToAdvance - The time to increment.
   */
  protected void incrementDayCounter(int timeToAdvance) {
    int currentDayNumber = this.getTime();

    currentDayNumber += timeToAdvance;

    setTime(currentDayNumber);
  }
}
