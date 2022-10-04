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
    try {
      controller.StuffLendingSystem stuffLendingSystem = new StuffLendingSystem();
      view.UserInterface ui = new view.UserInterface();
      model.Time time = new model.Time(0);

      time.incrementDayCounter();

      int optionMainMenu = 0;
      int optionMemberMenu = 0;
      int optionItemMenu = 0;

      do {
        optionMainMenu = ui.mainMenu();
        time.incrementDayCounter();

        switch (optionMainMenu) {
          case 1:
            do {
              optionMemberMenu = ui.memberMenu();
              time.incrementDayCounter();

              switch (optionMemberMenu) {
                case 1:
                  stuffLendingSystem.createMember(ui.promptCreateMember(stuffLendingSystem.getMembers()),
                      time.getCounter());
                  break;
                case 2:
                  stuffLendingSystem.deleteMember(ui.promptMemberId(stuffLendingSystem.getMembers()));
                  break;
                case 3:
                  ui.promptEditMember(ui.promptMemberId(stuffLendingSystem.getMembers()),
                      stuffLendingSystem.getMembers());
                  break;
                case 4:
                  ui.showSingleMember(ui.promptMemberId(stuffLendingSystem.getMembers()),
                      stuffLendingSystem.getMembers());
                  break;
                case 5:
                  ui.showMembersSimpleInfo(stuffLendingSystem.getMembers());
                  break;
                case 6:
                  ui.showMembersFullInfo(stuffLendingSystem.getMembers(), time.getCounter());
                  break;
                case 7:
                  ui.showMessage("Going back...");
                  break;
                default:
                  ui.showMessage("Option is invalid");
                  break;
              }
            } while (optionMemberMenu != 7);
            break;
          case 2:
            do {
              optionItemMenu = ui.itemMenu();
              time.incrementDayCounter();

              switch (optionItemMenu) {
                case 1:
                  /* VIEW DEPENDS ON CONTROLLER HERE? FOR TAKING THE MEMBERS AS A PARAMETER? */
                  stuffLendingSystem.registerItemToMember(ui.promptMemberId(stuffLendingSystem.getMembers()),
                      ui.promptCreateItem(),
                      time.getCounter());
                  break;
                case 2:
                  stuffLendingSystem.deleteItemFromMember(ui.promptMemberId(stuffLendingSystem.getMembers()),
                      ui.promptGetItemName());
                  break;
                case 3:
                  ui.promptEditItem(ui.promptMemberId(stuffLendingSystem.getMembers()),
                      stuffLendingSystem.getMembers());
                  break;
                case 4:
                  ui.showSingleItem(ui.promptMemberId(stuffLendingSystem.getMembers()), stuffLendingSystem.getMembers(),
                      ui.promptGetItemName());
                  break;
                case 5:
                  ui.showMessage("Going back...");

                  break;
                default:
                  ui.showMessage("Option is invalid");

                  break;
              }
            } while (optionItemMenu != 5);
            break;
          case 3:
            ui.showMessage("Current time: " + time.getCounter());
            boolean contractEstablished = stuffLendingSystem.establishLendingContract(ui.promptLoanAnItem());
            if (!contractEstablished) {
              ui.showMessage("The contract was denied.");
            }
            break;
          case 4:
            System.out.println("Quitting...");
            break;
          default:
            System.out.println("Option is invalid");
            break;
        }
      } while (optionMainMenu != 4);
    } catch (RuntimeException runtimeError) {
      throw runtimeError;
    } catch (Exception error) {
      System.out.println("Something went wrong, please restart app.");
    }
  }
}
