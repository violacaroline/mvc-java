package controller;

import java.util.InputMismatchException;

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
    try {
      controller.StuffLendingSystem stuffLendingSystem = new StuffLendingSystem();
      view.UserInterface ui = new view.UserInterface();

      int optionMainMenu = 0;
      int optionMemberMenu = 0;
      int optionItemMenu = 0;

      do {
        optionMainMenu = ui.mainMenu();
        stuffLendingSystem.advanceTime(1);

        switch (optionMainMenu) {
          case 1:
            do {
              optionMemberMenu = ui.memberMenu();
              stuffLendingSystem.advanceTime(1);

              switch (optionMemberMenu) {
                case 1:
                  stuffLendingSystem.createMember(ui.promptCreateMember(stuffLendingSystem.getMembers()));
                  break;
                case 2:
                  stuffLendingSystem.deleteMember(ui.promptMemberId(stuffLendingSystem.getMembers()));
                  break;
                case 3:
                  stuffLendingSystem.editMember(ui.promptEditMember(ui.promptMemberId(stuffLendingSystem.getMembers()),
                      stuffLendingSystem.getMembers()));
                  break;
                case 4:
                  ui.showSingleMember(ui.promptMemberId(stuffLendingSystem.getMembers()),
                      stuffLendingSystem.getMembers());
                  break;
                case 5:
                  ui.showMembersSimpleInfo(stuffLendingSystem.getMembers());
                  break;
                case 6:
                  ui.showMembersFullInfo(stuffLendingSystem.getMembers(), stuffLendingSystem.getCurrentDay());
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
              stuffLendingSystem.advanceTime(1);

              switch (optionItemMenu) {
                case 1:
                  stuffLendingSystem.registerItemToMember(ui.promptMemberId(stuffLendingSystem.getMembers()),
                      ui.promptCreateItem());
                  break;
                case 2:
                  stuffLendingSystem.deleteItemFromMember(ui.promptMemberId(stuffLendingSystem.getMembers()),
                      ui.promptGetItemName());
                  break;
                case 3:
                  stuffLendingSystem.editItem(ui.promptEditItem(ui.promptMemberId(stuffLendingSystem.getMembers()),
                      stuffLendingSystem.getMembers()));

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
            ui.showMessage("Current day: " + stuffLendingSystem.getCurrentDay());
            boolean contractEstablished = stuffLendingSystem
                .establishLendingContract(ui.promptLoanAnItem(stuffLendingSystem.getMembers()));
            if (!contractEstablished) {
              ui.showMessage("The contract was denied.");
            }
            break;
          case 4:
            stuffLendingSystem.advanceTime(ui.promptAdvanceTime());
            break;
          case 5:
            System.out.println("Quitting...");
            break;
          default:
            System.out.println("Option is invalid");
            break;
        }
      } while (optionMainMenu != 5);
    } catch (InputMismatchException inputMismatchException) {
      System.out.println("You have to type a number");
    } catch (RuntimeException runtimeError) {
      throw runtimeError;
    } catch (Exception error) {
      System.out.println("Something went wrong, please restart app.");
    }
  }
}
