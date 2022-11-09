package controller;

import java.util.InputMismatchException;
import view.InfoMessage;
import view.ItemMenuEvent;
import view.MainMenuEvent;
import view.MemberMenuEvent;

/**
 * Represents an administrator of the stuff lending system.
 */
public class Administrator {
  view.MainView mainView = new view.MainView();
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
        stuffLendingRegister.advanceTime(1);

        switch (actionMainMenu) {
          case SeeMemberMenu:
            runningMemberMenu = true;

            while (runningMemberMenu) {
              MemberMenuEvent actionMemberMenu = this.mainView.memberMenu();
              stuffLendingRegister.advanceTime(1);

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
                  stuffLendingRegister.viewMember();

                  break;
                case SeeAllMembersSimpleList:
                  stuffLendingRegister.viewAllMembersSimple();
                  break;
                case SeeAllMembersFullList:
                  stuffLendingRegister.viewAllMembersFull();
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
              stuffLendingRegister.advanceTime(1);

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
                  stuffLendingRegister.viewItem();
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
            stuffLendingRegister.advanceTime(this.mainView.promptAdvanceTime());
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
