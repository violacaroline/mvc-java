package controller;

import model.Member;

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
                  String[] answerArray = ui.promptCreateMember();

                  Member member = stuffLendingSystem.createMember(answerArray[0], answerArray[1], answerArray[2],
                      time.getCounter());

                  stuffLendingSystem.addMember(member);

                  ui.showMessage("Member was created");
                  break;
                case 2:
                  stuffLendingSystem.deleteMember(ui.promptMemberId());

                  ui.showMessage("Member was deleted");
                  break;
                case 3:
                  ui.promptEditMember(ui.promptMemberId(), stuffLendingSystem.getMembers());

                  ui.showMessage("Member was edited");
                  break;
                case 4:
                  ui.showSingleMember(ui.promptMemberId(), stuffLendingSystem.getMembers());
                  break;
                case 5:
                  ui.showMembersSimpleInfo(stuffLendingSystem.getMembers());
                  break;
                case 6:
                  System.out.println("List members full here");
                  ui.showMembersFullInfo(stuffLendingSystem.getMembers());
                  break;
                case 7:
                  System.out.println("Going back...");

                  break;
                default:
                  System.out.println("Option is invalid");

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
                  stuffLendingSystem.registerItemToMember(ui.promptMemberId(), ui.promptCreateItem(),
                      time.getCounter());
                  break;
                case 2:
                  stuffLendingSystem.deleteItemFromMember(ui.promptMemberId(), ui.promptGetItemName());
                  break;
                case 3:
                  System.out.println("Edit item here");
                  ui.promptEditItem(ui.promptMemberId(), stuffLendingSystem.getMembers());
                  break;
                case 4:
                  System.out.println("View item here");
                  ui.showSingleItem(ui.promptMemberId(), stuffLendingSystem.getMembers(), ui.promptGetItemName());
                  break;
                case 5:
                  System.out.println("Going back...");

                  break;
                default:
                  System.out.println("Option is invalid");

                  break;
              }
            } while (optionItemMenu != 5);
            break;
          case 3:
            System.out.println("Quitting...");
            break;
          default:
            System.out.println("Option is invalid");
            break;
        }
      } while (optionMainMenu != 3);
    } catch (RuntimeException runtimeError) {
      throw runtimeError;
    } catch (Exception error) {
      System.out.println("Something went wrong, please restart app.");
    }
  }
}
