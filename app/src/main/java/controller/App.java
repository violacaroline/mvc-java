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
      controller.StuffLendingSystem stuffLendingsystem = new StuffLendingSystem();
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
              switch (optionMemberMenu) {
                case 1:
                  String[] answerArray = ui.promptCreateMember();

                  Member member = stuffLendingsystem.createMember(answerArray[0], answerArray[1], answerArray[2]);

                  stuffLendingsystem.addMember(member);

                  ui.showMessage("Member was created");
                  break;
                case 2:
                  stuffLendingsystem.deleteMember(ui.promptMemberId());

                  ui.showMessage("Member was deleted");
                  break;
                case 3:
                  System.out.println("Edit a member here");
                  break;
                case 4:
                  System.out.println("View a member here");
                  ui.showSingleMember(ui.promptMemberId(), stuffLendingsystem.getMembers());
                  break;
                case 5:
                  System.out.println("List members simple here");
                  ui.showMembersSimpleInfo(stuffLendingsystem.getMembers());
                  break;
                case 6:
                  System.out.println("List members full here");

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

              switch (optionItemMenu) {
                case 1:
                  System.out.println("Create item here");

                  break;
                case 2:
                  System.out.println("Delete item here");

                  break;
                case 3:
                  System.out.println("Edit item here");

                  break;
                case 4:
                  System.out.println("View item here");

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
            System.out.println("Exiting...");
            break;
          default:
            System.out.println("Option is invalid");
            break;
        }
      } while (optionMainMenu != 3);
    } catch (Exception error) {
      System.out.println("Something went wrong, please restart app. Error message: " + error.getMessage());
    }
  }
}
