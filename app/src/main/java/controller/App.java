package controller;

import java.util.InputMismatchException;
import model.StuffLendingSystem;

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
      model.StuffLendingSystem stuffLendingSystem = new StuffLendingSystem();
      view.UserInterface ui = new view.UserInterface();

      boolean runningMainMenu = true;
      boolean runningMemberMenu = true;
      boolean runningItemMenu = true;

      while (runningMainMenu) {
        view.UserInterface.MainMenuEvent actionMainMenu = ui.mainMenu();
        stuffLendingSystem.advanceTime(1);

        switch (actionMainMenu) {
          case SeeMemberMenu:
            runningMemberMenu = true;

            while (runningMemberMenu) {
              view.UserInterface.MemberMenuEvent actionMemberMenu = ui.memberMenu();
              stuffLendingSystem.advanceTime(1);

              switch (actionMemberMenu) {
                case CreateMember:
                  stuffLendingSystem.createMember(ui.promptCreateMember(stuffLendingSystem.getMembers()));
                  break;
                case DeleteMember:
                  stuffLendingSystem.deleteMember(ui.promptMemberId(stuffLendingSystem.getMembers()));
                  break;
                case EditMember:
                  stuffLendingSystem.editMember(ui.promptEditMember(ui.promptMemberId(stuffLendingSystem.getMembers()),
                      stuffLendingSystem.getMembers()));
                  break;
                case ViewMember:
                  ui.showSingleMember(ui.promptMemberId(stuffLendingSystem.getMembers()),
                      stuffLendingSystem.getMembers());
                  break;
                case SeeAllMembersSimpleList:
                  ui.showMembersSimpleInfo(stuffLendingSystem.getMembers());
                  break;
                case SeeAllMembersFullList:
                  ui.showMembersFullInfo(stuffLendingSystem.getMembers(), stuffLendingSystem.getCurrentDay());
                  break;
                case GoBack:
                  runningMemberMenu = false;
                  ui.showMessage("Going back...");
                  break;
                default:
                  ui.showMessage("Option is invalid");
                  break;
              }
            }
            break;
          case SeeItemMenu:
            runningItemMenu = true;
            while (runningItemMenu) {
              view.UserInterface.ItemMenuEvent actionItemMenu = ui.itemMenu();
              stuffLendingSystem.advanceTime(1);

              switch (actionItemMenu) {
                case CreateItem:
                  stuffLendingSystem.registerItemToMember(ui.promptMemberId(stuffLendingSystem.getMembers()),
                      ui.promptCreateItem());
                  break;
                case DeleteItem:
                  stuffLendingSystem.deleteItemFromMember(ui.promptMemberId(stuffLendingSystem.getMembers()),
                      ui.promptGetItemName());
                  break;
                case EditItem:
                  stuffLendingSystem.editItem(ui.promptEditItem(ui.promptMemberId(stuffLendingSystem.getMembers()),
                      stuffLendingSystem.getMembers()));

                  break;
                case ViewItem:
                  ui.showSingleItem(ui.promptMemberId(stuffLendingSystem.getMembers()), stuffLendingSystem.getMembers(),
                      ui.promptGetItemName());
                  break;
                case GoBack:
                  runningItemMenu = false;
                  ui.showMessage("Going back...");
                  break;
                default:
                  ui.showMessage("Option is invalid");

                  break;
              }
            }
            break;
          case LoanItem:
            ui.showMessage("Current day: " + stuffLendingSystem.getCurrentDay());
            boolean contractEstablished = stuffLendingSystem
                .isContractEstablished(ui.promptLoanAnItem(stuffLendingSystem.getMembers()));
            if (!contractEstablished) {
              ui.showMessage("The contract was denied.");
            }
            break;
          case AdvanceTime:
            stuffLendingSystem.advanceTime(ui.promptAdvanceTime());
            break;
          case Quit:
            runningMainMenu = false;
            System.out.println("Quitting...");
            break;
          default:
            System.out.println("Option is invalid");
            break;
        }
      }
    } catch (InputMismatchException inputMismatchException) {
      System.out.println("You have to type a number");
    } catch (RuntimeException runtimeError) {
      throw runtimeError;
    } catch (Exception error) {
      System.out.println("Something went wrong, please restart app.");
    }
  }
}
