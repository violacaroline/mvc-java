package controller;

import java.util.ArrayList;
import java.util.Random;
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
   *
   * @return - The new member.
   */
  public Member createMember(String name, String email, String phone) {
    Member member = new Member(name, email, phone, createMemberId());
    return member;
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
   * @param memberId - The member to delete.
   */
  public void registerItemToMember(String memberId, String[] answerArray) {
    for (int i = 0; i < this.members.size(); i++) {
      if (this.members.get(i).getId().equals(memberId)) {
        this.members.get(i).createItem(answerArray[0], answerArray[1], answerArray[2], 100, 6);
      }
    }
  }
}
