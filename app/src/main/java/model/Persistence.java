package model;

import java.util.ArrayList;

/**
 * Loads some hardcoded members.
 */
public class Persistence {
  private ArrayList<Member> hardCodedMembers = new ArrayList<>();

  /*
   * Loads hardcoded members.
   *
   * @return - List of hardcoded members.
   */
  public ArrayList<Member> loadMembers() {
    Member member1 = new Member("memberOne", "memberOneEmail", "0101", "MEMID1", 0);
    Member member2 = new Member("memberTwo", "memberTwoEmail", "0202", "MEMID2", 0);
    Member member3 = new Member("memberThree", "memberThreeEmail", "0303", "MEMID3", 0);
    Member member4 = new Member("memberFour", "memberFourEmail", "0404", "MEMID4", 0);

    this.hardCodedMembers.add(member1);
    this.hardCodedMembers.add(member2);
    this.hardCodedMembers.add(member3);
    this.hardCodedMembers.add(member4);

    return this.hardCodedMembers;
  }
}
