package controller;

import model.Item;
import model.Member;
import model.StuffLendingSystem;
import view.InfoMessage;
import view.ItemEditOption;
import view.MemberEditOption;

/**
 * Represents a Stuff Lending register.
 */
public class StuffLendingRegister {
  view.MainView mainView = new view.MainView();
  view.MemberView memberView = new view.MemberView();
  view.ItemView itemView = new view.ItemView();
  model.StuffLendingSystem stuffLendingSystem = new StuffLendingSystem();

  /**
   * Calls model to create member.
   */
  public void createMember() {
    String name = memberView.promptName();
    String email = memberView.promptEmail(stuffLendingSystem.showMembers());
    String phone = memberView.promptPhone(stuffLendingSystem.showMembers());

    stuffLendingSystem.createMember(name, email, phone);
  }

  /**
   * Calls model to edit member.
   */
  public void editMember() {
    String currentMember = memberView.promptMemberId(stuffLendingSystem.showMembers());
    MemberEditOption editOption = memberView.promptEditMember();

    switch (editOption) {
      case Name:
        for (Member member : stuffLendingSystem.getMemberList()) {
          if (member.getId().equals(currentMember)) {
            String newName = memberView.promptName();

            member.setName(newName);
          }
        }
        break;
      case Email:
        for (Member member : stuffLendingSystem.getMemberList()) {
          if (member.getId().equals(currentMember)) {
            String newEmail = memberView.promptEmail(stuffLendingSystem.showMembers());

            member.setEmail(newEmail);
          }
        }
        break;
      case Phone:
        for (Member member : stuffLendingSystem.getMemberList()) {
          if (member.getId().equals(currentMember)) {
            String newPhone = memberView.promptPhone(stuffLendingSystem.showMembers());

            member.setPhone(newPhone);
          }
        }
        break;
      case Nothing:
        mainView.showMessage(InfoMessage.OptionInvalid);
        break;
      default:
        mainView.showMessage(InfoMessage.OptionInvalid);
        break;
    }
  }

  /**
   * Calls model to delete a member.
   */
  public void deleteMember() {
    String memberId = memberView.promptMemberId(stuffLendingSystem.showMembers());

    stuffLendingSystem.deleteMember(memberId);
  }

  /**
   * View a single member.
   */
  public void viewMember() {
    this.memberView.showSingleMember(this.memberView.promptMemberId(stuffLendingSystem.showMembers()),
        stuffLendingSystem.showMembers());
  }

  /**
   * View all members SIMPLE.
   */
  public void viewAllMembersSimple() {
    this.memberView.showMembersSimpleInfo(stuffLendingSystem.showMembers());
  }

  /**
   * View all members FULL.
   */
  public void viewAllMembersFull() {
    this.memberView.showMembersFullInfo(stuffLendingSystem.showMembers(),
        stuffLendingSystem.getCurrentDay());
  }

  /**
   * Calls model to register an item.
   */
  public void registerItemToMember() {
    String memberId = memberView.promptMemberId(stuffLendingSystem.showMembers());
    String itemCategory = itemView.promptItemCategory();
    String itemName = itemView.promptItemName();
    String itemDescription = itemView.promptItemDescription();
    int itemCost = itemView.promptItemCost();

    stuffLendingSystem.registerItemToMember(memberId, itemCategory, itemName, itemDescription, itemCost);
  }

  /**
   * View an item.
   */
  public void viewItem() {
    this.itemView.showSingleItem(
        this.memberView.promptMemberId(stuffLendingSystem.showMembers()),
        stuffLendingSystem.showMembers(),
        this.itemView.promptItemName());
  }

  /**
   * Edits an item.
   */
  public void editItem() {
    String currentMember = memberView.promptMemberId(stuffLendingSystem.showMembers());
    String currenItemName = itemView.promptItemName();
    ItemEditOption editOption = itemView.promptEditItem();

    switch (editOption) {
      case Category:
        for (Member member : stuffLendingSystem.getMemberList()) {
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
        for (Member member : stuffLendingSystem.getMemberList()) {
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
        for (Member member : stuffLendingSystem.getMemberList()) {
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
        for (Member member : stuffLendingSystem.getMemberList()) {
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
      case Nothing:
        mainView.showMessage(InfoMessage.OptionInvalid);
        break;
      default:
        mainView.showMessage(InfoMessage.OptionInvalid);
        break;
    }
  }

  /**
   * Calls model to delete an item.
   */
  public void deleteItemFromMember() {
    String memberId = memberView.promptMemberId(stuffLendingSystem.showMembers());
    String itemName = itemView.promptItemName();

    stuffLendingSystem.deleteItemFromMember(memberId, itemName);
  }

  /**
   * Calls model to loan an item.
   */
  public void loanItem() {
    this.mainView.showTime(stuffLendingSystem.getCurrentDay());
    String loaningMember = memberView.promptMemberId(stuffLendingSystem.showMembers());
    String owningMember = memberView.promptOwnersMemberId(stuffLendingSystem.showMembers());
    String itemToLoanName = itemView.promptItemName();
    int startDay = Integer.parseInt(itemView.promptStartDayLendingPeriod());
    int endDay = Integer.parseInt(itemView.promptEndDayLendingPeriod());

    boolean contractEstablished = stuffLendingSystem.loanItem(loaningMember, owningMember, itemToLoanName, startDay,
        endDay);

    if (!contractEstablished) {
      this.mainView.showMessage(InfoMessage.ContractDenied);
    } else {
      this.mainView.showMessage(InfoMessage.ContractEstablished);
    }
  }

  /**
   * Checks the time.
   *
   * @return - The current day.
   */
  public int getCurrentDay() {
    return stuffLendingSystem.getCurrentDay();
  }

  /**
   * Advances the time.
   *
   * @param timeToAdvance - The current day.
   */
  public void advanceTime(int timeToAdvance) {
    stuffLendingSystem.advanceTime(timeToAdvance);
  }
}
