package controller;

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
   * Creates a new member.
   */
  public void createMember() {
    String name = memberView.promptName();
    String email = memberView.promptEmail(stuffLendingSystem.showMemberList());
    String phone = memberView.promptPhone(stuffLendingSystem.showMemberList());

    stuffLendingSystem.createMember(name, email, phone);
  }

  /**
   * Edits an already existing member.
   * SO THE EDIT AND LOOPING THROUGH MEMBERS HAS INDEED BEEN MOVED TO MODEL AND THE
   * IMMUTABLE VERSIONS ARE THE ONES LOOPED THROUGH IN THE VIEW ETC. HOWEVER, IN THE INFORMATION EXPERT
   * STUFF LENDING SYSTEM IN THE MODEL, THE PROBLEMS WITH EXPOSING INTERNAL REPRESENTATION CONTINOUS DUE TO FROM THERE
   * EDITING MEMBERS AND TRANSFERRING CREDIT?
   * IN STUFFLENDINGSYSTEM AND TRANSFERCREDIT() USING GETOWNER() TO INCREMENT CREDIT IS BAD PRACTICE? 
   * CHANGING AN OBJECT BEING A FIELD OF ANOTHER? THE GETOWNER() SHOULD NOT NEED TO BE USED??
   * IN STUFFLENDINGSYSTEM AND EDITMEMBERNAME() IS IT OK LOOPING THROUGH AND CHANGING 
   * LIKE THIS SINCE THE MEMBERLIST IS CREATED IN THIS CLASS?
   * "Many are exposing methods to the controller and view that 
   * can be used to change objects in ways that should not be possible"
   */
  public void editMember() {
    String memberId = memberView.promptMemberId(stuffLendingSystem.showMemberList());
    MemberEditOption editOption = memberView.promptEditMember();

    switch (editOption) {
      case Name:
        String newName = memberView.promptName();
        stuffLendingSystem.editMemberName(memberId, newName);
        break;
      case Email:
        String newEmail = memberView.promptEmail(stuffLendingSystem.showMemberList());
        stuffLendingSystem.editMemberEmail(memberId, newEmail);
        break;
      case Phone:
        String newPhone = memberView.promptPhone(stuffLendingSystem.showMemberList());
        stuffLendingSystem.editMemberPhone(memberId, newPhone);
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
   * Deletes a member.
   */
  public void deleteMember() {
    String memberId = memberView.promptMemberId(stuffLendingSystem.showMemberList());

    stuffLendingSystem.deleteMember(memberId);
  }

  /**
   * View a single member.
   */
  public void viewMember() {
    memberView.showSingleMember(memberView.promptMemberId(stuffLendingSystem.showMemberList()),
        stuffLendingSystem.showMemberList());
  }

  /**
   * View all members SIMPLE.
   */
  public void viewAllMembersSimple() {
    memberView.showMembersSimpleInfo(stuffLendingSystem.showMemberList());
  }

  /**
   * View all members FULL.
   */
  public void viewAllMembersFull() {
    memberView.showMembersFullInfo(stuffLendingSystem.showMemberList(),
        stuffLendingSystem.getCurrentDay());
  }

  /**
   * Registers a new item to a specific member.
   */
  public void registerItemToMember() {
    String memberId = memberView.promptMemberId(stuffLendingSystem.showMemberList());
    String itemCategory = itemView.promptItemCategory();
    String itemName = itemView.promptItemName();
    String itemDescription = itemView.promptItemDescription();
    int itemCost = itemView.promptItemCost();

    stuffLendingSystem.registerItemToMember(memberId, itemCategory, itemName, itemDescription, itemCost);
  }

  /**
   * Views an item.
   */
  public void viewItem() {
    itemView.showSingleItem(
        memberView.promptMemberId(stuffLendingSystem.showMemberList()),
        stuffLendingSystem.showMemberList(),
        itemView.promptItemName());
  }

  /**
   * Edits an item.
   */
  public void editItem() {
    String memberId = memberView.promptMemberId(stuffLendingSystem.showMemberList());
    String itemName = itemView.promptItemName();
    ItemEditOption editOption = itemView.promptEditItem();

    switch (editOption) {
      case Category:
        String newCategory = itemView.promptItemCategory();
        stuffLendingSystem.editItemCategory(memberId, itemName, newCategory);
        break;
      case Name:
        String newName = itemView.promptItemName();
        stuffLendingSystem.editItemName(memberId, itemName, newName);
        break;
      case Description:
        String newDescription = itemView.promptItemDescription();
        stuffLendingSystem.editItemDescription(memberId, itemName, newDescription);
        break;
      case Cost:
        int newCost = itemView.promptItemCost();
        stuffLendingSystem.editItemCost(memberId, itemName, newCost);
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
   * Deletes an item from a member.
   */
  public void deleteItemFromMember() {
    String memberId = memberView.promptMemberId(stuffLendingSystem.showMemberList());
    String itemName = itemView.promptItemName();

    stuffLendingSystem.deleteItemFromMember(memberId, itemName);
  }

  /**
   * Loans an item, calls view to display successful/unsuccessful message.
   */
  public void loanItem() {
    mainView.showTime(stuffLendingSystem.getCurrentDay());
    String loaningMember = memberView.promptMemberId(stuffLendingSystem.showMemberList());
    String owningMember = memberView.promptOwnersMemberId(stuffLendingSystem.showMemberList());
    String itemToLoanName = itemView.promptItemName();
    int startDay = Integer.parseInt(itemView.promptStartDayLendingPeriod());
    int endDay = Integer.parseInt(itemView.promptEndDayLendingPeriod());

    boolean contractEstablished = stuffLendingSystem.loanItem(loaningMember, owningMember, itemToLoanName, startDay,
        endDay);

    if (!contractEstablished) {
      mainView.showMessage(InfoMessage.ContractDenied);
    } else {
      mainView.showMessage(InfoMessage.ContractEstablished);
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
