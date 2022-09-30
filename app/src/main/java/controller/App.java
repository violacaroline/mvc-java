package controller;

/**
 * Responsible for staring the application.
 */
public class App {
  /**
   * Application starting point.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    // Adapt to start the application in your way
    // model.Item item = new model.Item();
    // controller.StuffLendingSystem stuffLendingSystem = new
    // controller.StuffLendingSystem();
    view.UserInterface ui = new view.UserInterface();
    int optionMainMenu = 0;
    int optionMemberMenu = 0;
    int optionItemMenu = 0;

    do {
      optionMainMenu = ui.mainMenu();

      switch (optionMainMenu) {
        case 1:
          do {
            optionMemberMenu = ui.memberMenu();
          } while (optionMemberMenu != 7);
          break;
        case 2:
          do {
            optionItemMenu = ui.itemMenu();
          } while (optionItemMenu != 5);
          break;
        case 3:
          System.out.println("Exiting...");
          break;
        default:
          System.out.println("Option is invalid");
          break;
      }
    } while (optionMainMenu != 3);
  }
}
