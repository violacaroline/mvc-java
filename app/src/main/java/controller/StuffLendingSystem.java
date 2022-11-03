package controller;

import java.util.Random;
import model.Item;
import model.LendingContract;
import model.Member;
import model.MemberList;

/**
 * Represents a Stuff Lending System.
 */
public class StuffLendingSystem {
  model.Time time = new model.Time();
  private MemberList memberList;

  /**
   * Creates a StuffLendingSystem instance.
   */
  public StuffLendingSystem(MemberList memberList) {
    this.memberList = memberList;

    // /* MEMBER ONE ITEMS */
    // String[] memberOneItemOne = new String[] { "tool", "mem1item1", "item
    // description", "10" };

    // /* MEMBER TWO ITEMS */
    // String[] memberTwoItemOne = new String[] { "tool", "mem2item1", "item
    // description", "50" };
    // String[] memberTwoItemTwo = new String[] { "tool", "mem2item2", "item
    // description", "100" };

    // /* MEMBER THREE ITEMS */
    // String[] memberThreeItemOne = new String[] { "tool", "mem3item1", "item
    // description", "10" };

    // /* MEMBER FOUR ITEMS */
    // String[] memberFourItemOne = new String[] { "tool", "mem4item1", "item
    // description", "10" };
    // String[] memberFourItemTwo = new String[] { "tool", "mem4item2", "item
    // description", "50" };
    // String[] memberFourItemThree = new String[] { "tool", "mem4item3", "item
    // description", "100" };
    // String[] memberFourItemFour = new String[] { "tool", "mem4item4", "item
    // description", "200" };
    // String[] memberFourItemFive = new String[] { "tool", "mem4item5", "item
    // description", "500" };

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
   *
   * @param name  - The name of the member.
   * @param email - The email of the member.
   * @param phone - The phone of the member.
   */
  public void createMember(String name, String email, String phone) {
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

  // /**
  //  * Edit member.
  //  *
  //  * @param answerArray - An array of answers.
  //  */
  // public void editMember(String[] answerArray) {
  //   for (Member member : this.memberList.getMembers()) {
  //     if (member.getId().equals(answerArray[0])) {
  //       switch (answerArray[1]) {
  //         case "name":
  //           member.setName(answerArray[2]);
  //           break;
  //         case "email":
  //           member.setEmail(answerArray[2]);
  //           break;
  //         case "phone":
  //           member.setPhone(answerArray[2]);
  //           break;
  //         default:
  //           break;
  //       }
  //     }
  //   }
  // }

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
  public void registerItemToMember(String memberId, String category, String name, String description, int cost) {
    for (int i = 0; i < this.memberList.getMembers().size(); i++) {
      if (this.memberList.getMembers().get(i).getId().equals(memberId)) {
        this.memberList.getMembers().get(i).createItem(category, name, description, cost,
            time.getTime(), this.memberList.getMembers().get(i));
      }
    }
  }

  // /**
  //  * Edit item.
  //  *
  //  * @param answerArray - An array of answers.
  //  */
  // public void editItem(String[] answerArray) {
  //   for (Member member : this.memberList.getMembers()) {
  //     if (member.getId().equals(answerArray[0])) {
  //       for (Item item : member.getItems()) {
  //         if (item.getName().equals(answerArray[1])) {
  //           switch (answerArray[2]) {
  //             case "category":
  //               item.setCategory(answerArray[3]);
  //               break;
  //             case "name":
  //               item.setName(answerArray[3]);
  //               break;
  //             case "description":
  //               item.setDescription(answerArray[3]);
  //               break;
  //             case "cost":
  //               item.setCostPerDay(Integer.parseInt(answerArray[3]));
  //               break;
  //             default:
  //               break;
  //           }
  //         }
  //       }
  //     }
  //   }
  // }

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
  public boolean isContractCreated(String memberId, Item item, int endDay, int startDay) {
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
              if (member.getCredit() >= item.getCostPerDay() * endDay
                  - startDay) {
                this.transferCredit(memberId, item, endDay, startDay);
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
    LendingContract lendingContract = new LendingContract(startDay,
        +endDay, item, currentlyLoaningItem);
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
    return time.getTime();
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
