package controller;

import java.util.Random;
import model.Item;
import model.LendingContract;
import model.Member;
import model.MemberList;
import view.InfoMessage;
import view.ItemEditOption;
import view.MemberEditOption;

/**
 * Represents a Stuff Lending System.
 */
public class StuffLendingSystem {
  model.Time time = new model.Time();
  view.MainView mainView = new view.MainView();
  view.MemberView memberView = new view.MemberView();
  view.ItemView itemView = new view.ItemView();
  model.MemberList memberList = new MemberList();

  /**
   * Creates a StuffLendingSystem instance.
   */
  public StuffLendingSystem() {

    /* REGISTER HARD CODED ITEMS */
    this.registerItemToMember("MEMID1", "tool", "mem1item1", "item description", 10);
    this.registerItemToMember("MEMID2", "tool", "mem2item1", "item description", 50);
    this.registerItemToMember("MEMID2", "tool", "mem2item2", "item description", 100);
    this.registerItemToMember("MEMID3", "tool", "mem3item1", "item description", 10);
    this.registerItemToMember("MEMID4", "tool", "mem4item1", "item description", 10);
    this.registerItemToMember("MEMID4", "tool", "mem4item2", "item description", 50);
    this.registerItemToMember("MEMID4", "tool", "mem4item3", "item description", 100);
    this.registerItemToMember("MEMID4", "tool", "mem4item4", "item description", 200);
    this.registerItemToMember("MEMID4", "tool", "mem4item5", "item description", 500);

    /*
     * HARD CODED LENDING CONTRACT, MEMBER 3 LOANING ITEM MEM4ITEM1 FROM MEMBER 4
     */
    this.isContractEstablished("MEMID3", "MEMID4", "mem4item1", 7, 10);
  }

  /**
   * Creates a member.
   */
  public void createMember() {
    /* THE SIMPLE VARIABLES AND THE CALLING TO THE VIEW TO PROMPT USER FEELS LIKE A CONTROLLER JOB */
    /* BUT TO MAKE THIS CLASS BELONG TO MODEL I HAVE TO SEPARATE THAT CONCERN FROM THE CREATING AND ADDING OF MEMBER? */
    /* MAKE YET ANOTHER CONTROLLER THAT HAS THIS STUFFLENDING SYSTEM AS AN INSTANCE - I AM ALREADY DOING THIS IN ADMINISTRATOR THOUGH?! */
    /* FEEL LIKE I WILL BE ADDING AN OVERHEAD OF CLASSES JUST TO MAINTAIN MVC?? */
    /* MEMBERSHIP APPLICATION??? */
    String name = memberView.promptName();
    String email = memberView.promptEmail(this.memberList.showMembers());
    String phone = memberView.promptPhone(this.memberList.showMembers());
    Member member = new Member(name, email, phone,
        createMemberId(), time.getTime());

    memberList.addMember(member);
  }

  /**
   * Creates a unique member ID.
   *
   * @return - The member ID.
   */
  public String createMemberId() {
    Random random = new Random();
    String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    StringBuilder memberId = new StringBuilder();

    for (int i = 0; i < 6; i++) {
      int index = random.nextInt(alphaNumericString.length());

      memberId.append(alphaNumericString.charAt(index));
    }
    return memberId.toString();
  }

  /**
   * Edit member.
   */
  public void editMember() {
    String currentMember = memberView.promptMemberId(this.memberList.showMembers());
    MemberEditOption editOption = memberView.promptEditMember();

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
      case Nothing:
        mainView.showMessage(InfoMessage.OptionInvalid);
        break;
      default:
        mainView.showMessage(InfoMessage.OptionInvalid);
        break;
    }
  }

  /**
   * Delete member.
   *
   * @param memberId - The member to delete.
   */
  public void deleteMember(String memberId) {
    for (int i = 0; i < this.memberList.getMembers().size(); i++) {
      if (this.memberList.getMembers().get(i).getId().equals(memberId)) {
        this.memberList.getMembers().remove(i);
      }
    }
  }

  /**
   * Registers an item to a member.
   *
   * @param memberId - The member to register the item to.
   */
  public void registerItemToMember(String memberId, String itemCategory,
      String itemName, String itemDescription, int itemCost) {

    for (int i = 0; i < this.memberList.getMembers().size(); i++) {
      if (this.memberList.getMembers().get(i).getId().equals(memberId)) {
        this.memberList.getMembers().get(i).createItem(itemCategory, itemName, itemDescription, itemCost,
            time.getTime(), this.memberList.getMembers().get(i));
      }
    }
  }

  /*
   * IF I TAKE MEMBERID, ITEMNAME AND EDITOPTION AS PARAMETER
   * I COULD MOVE THIS CLASS TO MODEL - HOWEVER IT WOULD BE DEPENDENT
   * ON VIEW SINCE EDITOPTION IS THERE? Should */

  /**
   * Edit item.
   */
  public void editItem() {
    String currentMember = memberView.promptMemberId(this.memberList.showMembers());
    String currenItemName = itemView.promptItemName();
    ItemEditOption editOption = itemView.promptEditItem();

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
   *
   * @param memberId - The member to delete.
   */
  public void deleteItemFromMember(String memberId, String itemName) {
    for (int i = 0; i < this.memberList.getMembers().size(); i++) {
      if (this.memberList.getMembers().get(i).getId().equals(memberId)) {
        this.memberList.getMembers().get(i).deleteitem(itemName);
      }
    }
  }

  /**
   * Loan an item.
   */
  public void loanItem() {
    this.mainView.showTime(this.time);
    String loaningMember = memberView.promptMemberId(this.memberList.showMembers());
    String owningMember = memberView.promptOwnersMemberId(this.memberList.showMembers());
    String itemToLoanName = itemView.promptItemName();
    int startDay = Integer.parseInt(itemView.promptStartDayLendingPeriod());
    int endDay = Integer.parseInt(itemView.promptEndDayLendingPeriod());

    boolean contractEstablished = this
        .isContractEstablished(loaningMember, owningMember, itemToLoanName, startDay, endDay);
    if (!contractEstablished) {
      this.mainView.showMessage(InfoMessage.ContractDenied);
    }
  }

  /**
   * Establishes a new lending contract between two members.
   *
   * @return - True if a lending contract was successfully established.
   */
  public boolean isContractEstablished(String memberIdLoaner, String memberIdowner, String itemName, int startDay,
      int endDay) {
    boolean isContractEstablished = false;

    for (int i = 0; i < this.memberList.getMembers().size(); i++) {
      if (this.memberList.getMembers().get(i).getId().equals(memberIdowner)) {
        for (Item item : this.memberList.getMembers().get(i).getItems()) {
          if (item.getName().equals(itemName)) {
            isContractEstablished = isContractCreated(memberIdLoaner, item, startDay, endDay);
          }
        }
      }
    }

    return isContractEstablished;
  }

  /**
   * Deduct member's credit.
   *
   * @param deductMemberId - The ID of the member to deduct.
   * @param endDay         - The day when contract ends.
   * @param startDay       - The day when contract starts.
   */
  public void transferCredit(String deductMemberId, Item item, int startDay, int endDay) {
    for (Member member : this.memberList.getMembers()) {
      if (member.getId().equals(deductMemberId)) {
        int costOfItem = item.getCostPerDay() * (endDay
            - startDay);

        member.decrementCredit(costOfItem);
        item.getOwner().incrementCredit(costOfItem);
      }
    }
  }

  /**
   * Check member's credit, create contract if sufficient funds.
   *
   * @param memberId - The member to check.
   * @param endDay   - The day when contract ends.
   * @param startDay - The day when contract starts.
   *
   * @return - True if contract was created.
   */
  public boolean isContractCreated(String memberId, Item item, int startDay, int endDay) {
    boolean isContractCreated = false;

    /* Only create a contract if it's for today or a time period in the future */
    if (startDay >= time.getTime()) {

      /* Only create a contract if there is not already one */
      if (!isItemReserved(item.getLendingContracts(), startDay)) {

        /* If it is the owning member create contract, don't deduct credit */
        if (memberId.equals(item.getOwner().getId())) {

          this.createLendingContract(startDay, endDay, item, item.getOwner());
          isContractCreated = true;
        } else {

          /* If it is another member check and transfer credit */
          for (Member member : this.memberList.getMembers()) {
            if (member.getId().equals(memberId)) {
              if (member.getCredit() >= item.getCostPerDay() * (endDay - startDay)) {
                this.transferCredit(memberId, item, startDay, endDay);
                this.createLendingContract(startDay, endDay, item, member);
                isContractCreated = true;
              }
            }
          }
        }
      }
    }

    return isContractCreated;
  }

  /**
   * Create new contract.
   *
   * @param startDay - The day when contract starts.
   * @param endDay   - The day when contract ends.
   * @param item     - The item it covers.
   */
  public void createLendingContract(int startDay, int endDay, Item item, Member currentlyLoaningItem) {
    LendingContract lendingContract = new LendingContract(startDay, endDay, item, currentlyLoaningItem);
    item.addLendingContract(lendingContract);
  }

  /**
   * Check if item is available.
   *
   * @param lendingContracts - The lending contracts to iterate.
   * @param desiredStartDay  - The day FROM when the item would need to be free.
   * @return - True if item is reserved.
   */
  public boolean isItemReserved(LendingContract[] lendingContracts, int desiredStartDay) {
    boolean isReserved = false;

    for (LendingContract lendingContract : lendingContracts) {
      if (desiredStartDay >= lendingContract.getStartDay()
          && desiredStartDay <= lendingContract.getEndDay()) {
        isReserved = true;
      }
    }

    return isReserved;
  }

  /**
   * Checks the time.
   *
   * @return - The current day.
   */
  public int getCurrentDay() {
    return this.time.getTime();
  }

  /**
   * Advances the time.
   *
   * @param timeToAdvance - The current day.
   */
  public void advanceTime(int timeToAdvance) {
    time.incrementDayCounter(timeToAdvance);
  }
}
