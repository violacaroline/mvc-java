package controller;

import java.util.InputMismatchException;
import model.StuffLendingSystem;
import view.InfoMessage;
import view.ItemMenuEvent;
import view.MainMenuEvent;
import view.MemberMenuEvent;

/**
 * Represents an administrator of the stuff lending system.
 */
public class Administrator {
  view.MainView mainView = new view.MainView();
  view.MemberView memberView = new view.MemberView();
  view.ItemView itemView = new view.ItemView();
  model.StuffLendingSystem stuffLendingSystem = new StuffLendingSystem();
  controller.StuffLendingRegister stuffLendingRegister = new StuffLendingRegister();

  /**
   * Runs stuff lending system program.
   */
  public void startSystem() {
    try {
      boolean runningMainMenu = true;
      boolean runningMemberMenu = true;
      boolean runningItemMenu = true;

      while (runningMainMenu) {
        MainMenuEvent actionMainMenu = this.mainView.mainMenu();
        stuffLendingSystem.advanceTime(1);

        switch (actionMainMenu) {
          case SeeMemberMenu:
            runningMemberMenu = true;

            while (runningMemberMenu) {
              MemberMenuEvent actionMemberMenu = this.mainView.memberMenu();
              stuffLendingSystem.advanceTime(1);

              switch (actionMemberMenu) {
                case CreateMember:
                  stuffLendingRegister.createMember();
                  break;
                case DeleteMember:
                  stuffLendingRegister.deleteMember();
                  break;
                case EditMember:
                  stuffLendingRegister.editMember();
                  break;
                case ViewMember:
                  this.memberView.showSingleMember(
                      this.memberView.promptMemberId(stuffLendingSystem.showMembers()),
                      stuffLendingSystem.showMembers());
                  break;
                case SeeAllMembersSimpleList:
                  this.memberView.showMembersSimpleInfo(stuffLendingSystem.showMembers());
                  break;
                case SeeAllMembersFullList:
                  this.memberView.showMembersFullInfo(stuffLendingSystem.showMembers(),
                      stuffLendingSystem.getCurrentDay());
                  break;
                case GoBack:
                  runningMemberMenu = false;
                  this.mainView.showMessage(InfoMessage.GoingBack);
                  break;
                default:
                  this.mainView.showMessage(InfoMessage.OptionInvalid);
                  break;
              }
            }
            break;
          case SeeItemMenu:
            runningItemMenu = true;
            while (runningItemMenu) {
              ItemMenuEvent actionItemMenu = this.mainView.itemMenu();
              stuffLendingSystem.advanceTime(1);

              switch (actionItemMenu) {
                case CreateItem:
                  stuffLendingRegister.registerItemToMember();
                  break;
                case DeleteItem:
                  stuffLendingRegister.deleteItemFromMember();
                  break;
                case EditItem:
                  stuffLendingRegister.editItem();
                  break;
                case ViewItem:
                  this.itemView.showSingleItem(
                      this.memberView.promptMemberId(stuffLendingSystem.showMembers()),
                      stuffLendingSystem.showMembers(),
                      this.itemView.promptItemName());
                  break;
                case GoBack:
                  runningItemMenu = false;
                  this.mainView.showMessage(InfoMessage.GoingBack);
                  break;
                default:
                  this.mainView.showMessage(InfoMessage.OptionInvalid);
                  break;
              }
            }
            break;
          case LoanItem:
            stuffLendingRegister.loanItem();
            break;
          case AdvanceTime:
            stuffLendingSystem.advanceTime(this.mainView.promptAdvanceTime());
            break;
          case Quit:
            runningMainMenu = false;
            this.mainView.showMessage(InfoMessage.Quitting);
            break;
          default:
            this.mainView.showMessage(InfoMessage.OptionInvalid);
            break;
        }
      }

    } catch (InputMismatchException inputMismatchException) {
      this.mainView.showMessage(InfoMessage.TypeNumber);
    } catch (RuntimeException runtimeError) {
      throw runtimeError;
    } catch (Exception error) {
      this.mainView.showMessage(InfoMessage.Error);
    }
  }
}
