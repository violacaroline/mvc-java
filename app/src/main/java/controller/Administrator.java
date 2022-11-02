package controller;

import java.util.InputMismatchException;
import model.Item;
import model.Member;
import model.MemberList;
import view.InfoMessage;
import view.ItemEditOption;
import view.ItemMenuEvent;
import view.MainMenuEvent;
import view.MemberEditOption;
import view.MemberMenuEvent;

/**
 * Represents an administrator of the stuff lending system.
 */
public class Administrator {
  view.MainView mainView = new view.MainView();
  view.MemberView memberView = new view.MemberView();
  view.ItemView itemView = new view.ItemView();
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
                  String name = memberView.promptName();
                  String email = memberView.promptEmail(this.memberList.showMembers());
                  String phone = memberView.promptPhone(this.memberList.showMembers());
                  stuffLendingSystem.createMember(name, email, phone);
                  break;
                case DeleteMember:
                  stuffLendingSystem.deleteMember(memberView.promptMemberId(this.memberList.showMembers()));
                  break;
                case EditMember:
                  MemberEditOption editOption = memberView.promptEditMember();
                  String currentMember = memberView.promptMemberId(this.memberList.showMembers());

                  switch (editOption) {
                    case Name:
                      for (Member member : this.memberList.getMembers()) {
                        if (member.getId().equals(currentMember)) {
                          String newName = memberView.promptName();

                          member.setName(newName);
                        }
                      }
                      break;
                    case Email:
                      for (Member member : this.memberList.getMembers()) {
                        if (member.getId().equals(currentMember)) {
                          String newEmail = memberView.promptEmail(this.memberList.showMembers());

                          member.setEmail(newEmail);
                        }
                      }
                      break;
                    case Phone:
                      for (Member member : this.memberList.getMembers()) {
                        if (member.getId().equals(currentMember)) {
                          String newPhone = memberView.promptPhone(this.memberList.showMembers());

                          member.setPhone(newPhone);
                        }
                      }
                      break;
                    default:
                      mainView.showMessage(InfoMessage.OptionInvalid);
                      break;
                  }
                  break;
                case ViewMember:
                  this.memberView.showSingleMember(this.memberView.promptMemberId(this.memberList.showMembers()),
                      this.memberList.showMembers());
                  break;
                case SeeAllMembersSimpleList:
                  this.memberView.showMembersSimpleInfo(this.memberList.showMembers());
                  break;
                case SeeAllMembersFullList:
                  this.memberView.showMembersFullInfo(this.memberList.showMembers(),
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
                  String itemCategory = itemView.promptItemCategory();
                  String itemName = itemView.promptItemName();
                  String itemDescription = itemView.promptItemDescription();
                  int itemCost = itemView.promptItemCost();

                  stuffLendingSystem.registerItemToMember(this.memberView.promptMemberId(this.memberList.showMembers()),
                      itemCategory, itemName, itemDescription, itemCost);
                  break;
                case DeleteItem:
                  stuffLendingSystem.deleteItemFromMember(this.memberView.promptMemberId(this.memberList.showMembers()),
                      this.itemView.promptItemName());
                  break;
                case EditItem:
                  ItemEditOption editOption = itemView.promptEditItem();
                  String currentMember = memberView.promptMemberId(this.memberList.showMembers());
                  String currenItemName = itemView.promptItemName();

                  switch (editOption) {
                    case Category:
                      for (Member member : this.memberList.getMembers()) {
                        if (member.getId().equals(currentMember)) {
                          for (Item item : member.getItems()) {
                            if (currenItemName.equals(item.getName())) {
                              String newCategory = itemView.promptItemCategory();

                              item.setName(newCategory);
                            }
                          }
                        }
                      }
                      break;
                    case Name:
                      for (Member member : this.memberList.getMembers()) {
                        if (member.getId().equals(currentMember)) {
                          for (Item item : member.getItems()) {
                            if (currenItemName.equals(item.getName())) {
                              String newName = itemView.promptItemName();

                              item.setName(newName);
                            }
                          }
                        }
                      }
                      break;
                    case Description:
                      for (Member member : this.memberList.getMembers()) {
                        if (member.getId().equals(currentMember)) {
                          for (Item item : member.getItems()) {
                            if (currenItemName.equals(item.getName())) {
                              String newDescription = itemView.promptItemDescription();

                              item.setDescription(newDescription);
                            }
                          }
                        }
                      }
                      break;
                    case Cost:
                      for (Member member : this.memberList.getMembers()) {
                        if (member.getId().equals(currentMember)) {
                          for (Item item : member.getItems()) {
                            if (currenItemName.equals(item.getName())) {
                              int newCost = itemView.promptItemCost();

                              item.setCostPerDay(newCost);
                            }
                          }
                        }
                      }
                      break;
                    default:
                      mainView.showMessage(InfoMessage.OptionInvalid);
                      break;
                  }
                  break;
                case ViewItem:
                  this.itemView.showSingleItem(this.memberView.promptMemberId(this.memberList.showMembers()),
                      this.memberList.showMembers(),
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
            this.mainView.showMessage(InfoMessage.CurrentDay);
            String loaningMember = memberView.promptMemberId(this.memberList.showMembers());
            String owningMember = memberView.promptMemberId(this.memberList.showMembers());
            String itemToLoanName = itemView.promptItemName();
            int startDay = Integer.parseInt(itemView.promptStartDayLendingPeriod());
            int endDay = Integer.parseInt(itemView.promptEndDayLendingPeriod());

            boolean contractEstablished = stuffLendingSystem
                .isContractEstablished(loaningMember, owningMember, itemToLoanName, startDay, endDay);
            if (!contractEstablished) {
              this.mainView.showMessage(InfoMessage.ContractDenied);
            }
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
