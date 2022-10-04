package model;

/**
 * Validating input. String/int values, empty values etc.
 */
public class InputValidator {
  /**
   * Validates that desired start day is today or in the future.
   *
   * @param startDay - Desired start day.
   * @param currentTime - Current time.
   * @return - True if start day is today or in the future.
   */
  public boolean validateTime(String startDay, int currentTime) {
    return Integer.parseInt(startDay) >= currentTime;
  }
}
