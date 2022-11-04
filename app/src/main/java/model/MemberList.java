package model;

import java.util.ArrayList;
import model.persistance.StuffSystemPersistence;

/**
 * Represents a list of members.
 */
public class MemberList {
  private ArrayList<Member> members = new ArrayList<>();
  model.persistance.StuffSystemPersistence stuffPersistence = new StuffSystemPersistence();

  public MemberList() {
    this.members = stuffPersistence.loadMembers();
  }

  /**
   * Shows the members without exposing the private list.
   *
   * @return - The existing members.
   */
  public Member[] showMembers() {
    Member[] showMembers = new Member[this.members.size()];

    showMembers = this.members.toArray(showMembers);

    return showMembers;
  }

  /**
   * Gets the members.
   *
   * @return - The existing members.
   */
  public ArrayList<Member> getMembers() {
    return this.members;
  }

  /**
   * Adds new member.
   *
   * @param member - The new member.
   */
  public void addMember(Member member) {
    this.members.add(member);
  }
}
