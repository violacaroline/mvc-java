package controller;

import java.util.InputMismatchException;
import model.MemberList;
import view.ItemMenuEvent;
import view.MainMenuEvent;
import view.MemberMenuEvent;

/**
 * Represents an administrator of the stuff lending system.
 */
public class Administrator {
  view.UserInterface view = new view.UserInterface();
  model.MemberList memberList = new MemberList();
  controller.StuffLendingSystem stuffLendingSystem = new StuffLendingSystem(memberList);

  /**
   * Creates an Administrator instance.
   */
  public Administrator() {

  }

  /**
   * Runs stuff lending system program.
   */
  public void runProgram() {
    try {
      boolean runningMainMenu = true;
      boolean runningMemberMenu = true;
      boolean runningItemMenu = true;

      while (runningMainMenu) {
        MainMenuEvent actionMainMenu = this.view.mainMenu();
        stuffLendingSystem.advanceTime(1);

        switch (actionMainMenu) {
          case SeeMemberMenu:
            runningMemberMenu = true;

            while (runningMemberMenu) {
              MemberMenuEvent actionMemberMenu = this.view.memberMenu();
              stuffLendingSystem.advanceTime(1);

              switch (actionMemberMenu) {
                case CreateMember:
                  stuffLendingSystem.createMember(this.view.promptCreateMember(this.memberList.showMembers()));
                  break;
                case DeleteMember:
                  stuffLendingSystem.deleteMember(this.view.promptMemberId(this.memberList.showMembers()));
                  break;
                case EditMember:
                  stuffLendingSystem
                      .editMember(this.view.promptEditMember(this.view.promptMemberId(this.memberList.showMembers()),
                          this.memberList.showMembers()));
                  break;
                case ViewMember:
                  this.view.showSingleMember(this.view.promptMemberId(this.memberList.showMembers()),
                      this.memberList.showMembers());
                  break;
                case SeeAllMembersSimpleList:
                  this.view.showMembersSimpleInfo(this.memberList.showMembers());
                  break;
                case SeeAllMembersFullList:
                  this.view.showMembersFullInfo(this.memberList.showMembers(), stuffLendingSystem.getCurrentDay());
                  break;
                case GoBack:
                  runningMemberMenu = false;
                  this.view.showMessage("Going back...");
                  break;
                default:
                  this.view.showMessage("Option is invalid");
                  break;
              }
            }
            break;
          case SeeItemMenu:
            runningItemMenu = true;
            while (runningItemMenu) {
              ItemMenuEvent actionItemMenu = this.view.itemMenu();
              stuffLendingSystem.advanceTime(1);

              switch (actionItemMenu) {
                case CreateItem:
                  stuffLendingSystem.registerItemToMember(this.view.promptMemberId(this.memberList.showMembers()),
                      this.view.promptCreateItem());
                  break;
                case DeleteItem:
                  stuffLendingSystem.deleteItemFromMember(this.view.promptMemberId(this.memberList.showMembers()),
                      this.view.promptGetItemName());
                  break;
                case EditItem:
                  stuffLendingSystem
                      .editItem(this.view.promptEditItem(this.view.promptMemberId(this.memberList.showMembers()),
                          this.memberList.showMembers()));
                  break;
                case ViewItem:
                  this.view.showSingleItem(this.view.promptMemberId(this.memberList.showMembers()),
                      this.memberList.showMembers(),
                      this.view.promptGetItemName());
                  break;
                case GoBack:
                  runningItemMenu = false;
                  this.view.showMessage("Going back...");
                  break;
                default:
                  this.view.showMessage("Option is invalid");

                  break;
              }
            }
            break;
          case LoanItem:
            this.view.showMessage("Current day: " + stuffLendingSystem.getCurrentDay());
            boolean contractEstablished = stuffLendingSystem
                .isContractEstablished(this.view.promptLoanAnItem(this.memberList.showMembers()));
            if (!contractEstablished) {
              this.view.showMessage("The contract was denied.");
            }
            break;
          case AdvanceTime:
            stuffLendingSystem.advanceTime(this.view.promptAdvanceTime());
            break;
          case Quit:
            runningMainMenu = false;
            this.view.showMessage("Quitting...");
            break;
          default:
            this.view.showMessage("Option is invalid");
            break;
        }
      }

    } catch (InputMismatchException inputMismatchException) {
      this.view.showMessage("You have to type a number");
    } catch (RuntimeException runtimeError) {
      throw runtimeError;
    } catch (Exception error) {
      this.view.showMessage("Something went wrong, please restart app.");
    }
  }
}
