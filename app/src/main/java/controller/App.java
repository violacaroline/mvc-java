package controller;

/**
 * Responsible for staring the application.
 */
public class App {
  /**
   * Application starting point.

   * @param args command line arguments.
   */
  public static void main(String[] args) {
    // Adapt to start the application in your way
    model.Item item = new model.Item();
    Member member = new Member("Caroline", "c@c.com", "0702018918", "r1a2n3d4o5m");
    view.UserInterface ui = new view.UserInterface();

    member.doSomethingSimple(item, ui);
  }
}
