package controller;

import java.util.ArrayList;
import java.util.Random;
import model.Member;

/**
 * Represents a stuff lending system.
 */
public class StuffLendingSystem {
  // private ArrayList<Member> members = new ArrayList<>();

  /**
   * Creates a StuffLendingSystem instance.
   *
   *
   */
  public StuffLendingSystem() {
    // setMembers(members);
  }

  // /**
  // * Gets the members.
  // *
  // * @return - The existing members.
  // */
  // public Member[] getMembers() {
  // Member[] showMembers = this.members.toArray(new Member[0]);

  // return showMembers;
  // }

  // /**
  // * Sets the members.
  // *
  // * @param members - The existing members.
  // */
  // public void setMembers(ArrayList<Member> members) {
  // this.members = members;
  // }

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
   * Returns a very important message.
   *
   * @return the message.
   */
  public String printMember(Member member) {
    return "Name: " + member.getName() + "\nEmail: " + member.getEmail()
        + "\nID: " + member.getId();
  }
}
