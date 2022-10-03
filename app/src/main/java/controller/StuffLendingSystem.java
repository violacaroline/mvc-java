package controller;

import java.util.ArrayList;
import java.util.Random;
import model.Item;
import model.LendingContract;
import model.Member;

/**
 * Represents a stuff lending system.
 */
public class StuffLendingSystem {
  private ArrayList<Member> members = new ArrayList<>();

  /**
   * Creates a StuffLendingSystem instance.
   */
  public StuffLendingSystem() {

  }

  /**
   * Gets the members.
   *
   * @return - The existing members.
   */
  public Member[] getMembers() {
    Member[] showMembers = new Member[this.members.size()];

    showMembers = this.members.toArray(showMembers);

    return showMembers;
  }

  /**
   * Adds new member.
   *
   * @param member - The new member.
   */
  public void addMember(Member member) {
    this.members.add(member);
  }

  /**
   * Creates a member.
   */
  public void createMember(String name, String email, String phone, int dayCreated) {
    Member member = new Member(name, email, phone, createMemberId(), dayCreated);

    this.addMember(member);
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
    for (int i = 0; i < this.members.size(); i++) {
      if (this.members.get(i).getId().equals(memberId)) {
        this.members.remove(i);
      }
    }
  }

  /**
   * Registers an item to a member.
   *
   * @param memberId    - The member to delete.
   * @param answerArray - An array of answers.
   * @param dayCreated  - The day an item was created.
   */
  public void registerItemToMember(String memberId, String[] answerArray, int dayCreated) {
    for (int i = 0; i < this.members.size(); i++) {
      if (this.members.get(i).getId().equals(memberId)) {
        this.members.get(i).createItem(answerArray[0], answerArray[1], answerArray[2],
            Integer.parseInt(answerArray[3]), dayCreated, this.members.get(i));
      }
    }
  }

  /**
   * Deletes an item from a member.
   *
   * @param memberId - The member to delete.
   */
  public void deleteItemFromMember(String memberId, String itemName) {
    for (int i = 0; i < this.members.size(); i++) {
      if (this.members.get(i).getId().equals(memberId)) {
        this.members.get(i).deleteitem(itemName);
      }
    }
  }

  /**
   * Establishes a new lending contract between two members.
   *
   * @param answerArray - The array of answers.
   *
   * @return - True if a lending contract was successfully established.
   */
  public boolean establishLendingContract(String[] answerArray) {
    boolean isContractEstablished = false;

    for (int i = 0; i < this.members.size(); i++) {
      if (this.members.get(i).getId().equals(answerArray[1])) {
        for (Item item : this.members.get(i).getItems()) {
          if (item.getName().equals(answerArray[2])) {
            isContractEstablished = checkCreditCreateContract(answerArray[0], item, answerArray[4], answerArray[3]);
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
  public void transferCredit(String deductMemberId, Item item, String endDay, String startDay) {
    for (Member member : this.members) {
      if (member.getId().equals(deductMemberId)) {
        int costOfItem = item.getCostPerDay() * (Integer.parseInt(endDay)
            - Integer.parseInt(startDay));

        System.out.println("The member to be deducted: " + member.getName());
        System.out.println("The member to be paid: " + item.getOwner().getName());

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
  public boolean checkCreditCreateContract(String memberId, Item item, String endDay, String startDay) {
    boolean isContractCreated = false;

    for (Member member : this.members) {
      if (member.getId().equals(memberId)) {
        // Check credit
        if (member.getCredit() > item.getCostPerDay() * (Integer.parseInt(endDay)
            - Integer.parseInt(startDay))) {
          this.transferCredit(memberId, item, endDay, startDay);
          this.createLendingContract(startDay, endDay, item);
          isContractCreated = true;
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
  public void createLendingContract(String startDay, String endDay, Item item) {
    LendingContract lendingContract = new LendingContract(Integer.parseInt(startDay),
        +Integer.parseInt(endDay), item);
    item.addLendingContract(lendingContract);
  }
}
