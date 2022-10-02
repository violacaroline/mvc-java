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
        this.members.get(i).createItem(answerArray[0], answerArray[1], answerArray[2], Integer.parseInt(answerArray[3]),
            dayCreated);
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
   * Create new lending contract.
   *
   */
  public void createLendingContract(String[] answerArray) {
    for (int i = 0; i < this.members.size(); i++) {
      if (this.members.get(i).getId().equals(answerArray[1])) {
        for (Item item : this.members.get(i).getItems()) {
          if (item.getName().equals(answerArray[2])) {
            this.deductMemberCredit(answerArray[0], item, answerArray[4], answerArray[3]);
            LendingContract lendingContract = new LendingContract(Integer.parseInt(answerArray[3]),
                +Integer.parseInt(answerArray[4]), item);
            item.addLendingContract(lendingContract);
          }
        }
      }
    }
  }

  /**
   * Deduct member's credit.
   *
   * @param memberId - The member to deduct.
   * @param endDay   - The day when contract ends.
   * @param startDay - The day when contract starts.
   */
  public void deductMemberCredit(String memberId, Item item, String endDay, String startDay) {
    for (Member member : this.members) {
      if (member.getId().equals(memberId)) {
        member.decrementCredit(item.getCostPerDay() * (Integer.parseInt(endDay)
            - Integer.parseInt(startDay)));
      }
    }
  }
}
