package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a Stuff Lending System.
 */
public class StuffLendingSystem {
  model.MemberList memberList = new MemberList();
  model.Time time = new model.Time();

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
   * Gets a copy of the list of members.
   *
   * @return - The copied list.
   */
  public Member[] showMembers() {
    Member[] showMembers = new Member[this.memberList.getMembers().size()];

    showMembers = this.memberList.getMembers().toArray(showMembers);

    return showMembers;
  }

  /**
   * Get members.
   */
  public ArrayList<Member> getMemberList() {
    return this.memberList.getMembers();
  }

  /**
   * Creates a member.
   *
   * @param name  - The name of the member.
   * @param email - The email of the member.
   * @param phone - The phone number of the member.
   */
  public void createMember(String name, String email, String phone) {
    Member member = new Member(name, email, phone,
        createMemberId(), this.time.getTime());

    this.memberList.addMember(member);
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
            this.time.getTime(), this.memberList.getMembers().get(i));
      }
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
  public boolean loanItem(String loaningMember, String owningMember, String itemToLoanName, int startDay, int endDay) {

    return this.isContractEstablished(loaningMember, owningMember, itemToLoanName, startDay, endDay);
    // boolean contractEstablished = this
    // .isContractEstablished(loaningMember, owningMember, itemToLoanName, startDay,
    // endDay);
    // if (!contractEstablished) {
    // this.mainView.showMessage(InfoMessage.ContractDenied);
    // } FIX THIS
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
            isContractEstablished = isContractCreated(memberIdLoaner, item, startDay, endDay, this.time.getTime());
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
  public boolean isContractCreated(String memberId, Item item, int startDay, int endDay, int currentTime) {
    boolean isContractCreated = false;

    /* Only create a contract if it's for today or a time period in the future */
    if (startDay >= currentTime) {

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
