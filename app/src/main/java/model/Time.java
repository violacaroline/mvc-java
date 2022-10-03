package model;

// import java.util.Timer;
// import java.util.TimerTask;

/**
 * Represents a time counter.
 */
public class Time {
  private int dayCounter;

  /**
   * Instansiates a time object.
   *
   * @param dayNumber - The number of days.
   */
  public Time(int dayNumber) {
    setCounter(dayNumber);
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
   */
  public void incrementDayCounter() {
    int currentDayNumber = this.getCounter();

    currentDayNumber++;

    setCounter(currentDayNumber);

    // int delay = 10000;
    // int period = 1000;

    // TimerTask timerTask = new TimerTask() {
    //   @Override
    //   public void run() {
    //     setCounter(currentDayNumber + 1);
    //   }
    // };

    // Timer timer = new Timer();

    // timer.scheduleAtFixedRate(timerTask, delay, period);

    System.out.println();
  }
}
