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
  view.MemberView memberView = new view.MemberView();
  view.ItemView itemView = new view.ItemView();
  // model.MemberList memberList = new MemberList();
  controller.StuffLendingSystem stuffLendingSystem = new StuffLendingSystem();

  /**
   * Creates an Administrator instance.
   */
  public Administrator() {

  }

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
                  stuffLendingSystem.createMember();
                  break;
                case DeleteMember:
                  stuffLendingSystem
                      .deleteMember(memberView.promptMemberId(stuffLendingSystem.memberList.showMembers()));
                  break;
                case EditMember:
                  stuffLendingSystem.editMember();
                  break;
                case ViewMember:
                  this.memberView.showSingleMember(
                      this.memberView.promptMemberId(stuffLendingSystem.memberList.showMembers()),
                      stuffLendingSystem.memberList.showMembers());
                  break;
                case SeeAllMembersSimpleList:
                  this.memberView.showMembersSimpleInfo(stuffLendingSystem.memberList.showMembers());
                  break;
                case SeeAllMembersFullList:
                  this.memberView.showMembersFullInfo(stuffLendingSystem.memberList.showMembers(),
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
                  String memberId = memberView.promptMemberId(stuffLendingSystem.memberList.showMembers());
                  String itemCategory = itemView.promptItemCategory();
                  String itemName = itemView.promptItemName();
                  String itemDescription = itemView.promptItemDescription();
                  int itemCost = itemView.promptItemCost();

                  stuffLendingSystem.registerItemToMember(memberId, itemCategory, itemName, itemDescription, itemCost);
                  break;
                case DeleteItem:
                  stuffLendingSystem.deleteItemFromMember(
                      this.memberView.promptMemberId(stuffLendingSystem.memberList.showMembers()),
                      this.itemView.promptItemName());
                  break;
                case EditItem:
                  stuffLendingSystem.editItem();
                  break;
                case ViewItem:
                  this.itemView.showSingleItem(
                      this.memberView.promptMemberId(stuffLendingSystem.memberList.showMembers()),
                      stuffLendingSystem.memberList.showMembers(),
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
            stuffLendingSystem.loanItem();
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
