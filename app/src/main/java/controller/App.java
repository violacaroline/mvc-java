package controller;

/**
 * Responsible for starting the application.
 */
public class App {
  /**
   * Application starting point.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {

    Administrator admin = new Administrator();

    admin.runProgram();
  }
}
