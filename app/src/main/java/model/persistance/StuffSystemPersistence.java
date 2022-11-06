package model.persistance;

import java.util.ArrayList;
import model.Member;

/**
 * Handles some Stuff System data storage.
 */
public class StuffSystemPersistence implements PersistenceInterface {
  public ArrayList<Member> members = new ArrayList<>();

  /**
   * Loads some Stuff System data.
   */
  public void load() {
    loadMembers();
  }

  /**
   * Saves some Stuff System data.
   */
  public void save() {
    saveMembers(members);
  }

  /**
   * Load some members.
   *
   * @return - A list of members.
   */
  public ArrayList<Member> loadMembers() {
    Member member1 = new Member("memberOne", "memberOneEmail", "0101", "MEMID1", 0);
    Member member2 = new Member("memberTwo", "memberTwoEmail", "0202", "MEMID2", 0);
    Member member3 = new Member("memberThree", "memberThreeEmail", "0303", "MEMID3", 0);
    Member member4 = new Member("memberFour", "memberFourEmail", "0404", "MEMID4", 0);
    this.members.add(member1);
    this.members.add(member2);
    this.members.add(member3);
    this.members.add(member4);

    return this.members;
  }

  /**
   * Save members.
   *
   * @param members = The members to save.
   */
  public void saveMembers(ArrayList<Member> members) {
    this.members = members;
  }
}
