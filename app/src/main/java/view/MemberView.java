package view;

import java.util.Scanner;
import model.Item;
import model.LendingContract;
import model.Member;
import model.MemberEditOption;
import model.MemberValidator;

/**
 * Represents a UI handling member related interaction.
 */
public class MemberView {
  Scanner scan = new Scanner(System.in, "utf-8");
  MemberValidator memberValidator = new MemberValidator();

  /**
   * Asks user for a members name.
   */
  public String promptName() {
    String name = "";
    boolean validName;
    do {
      System.out.println("Type members name:");
      name = scan.nextLine();

      validName = memberValidator.validateName(name);

      if (!validName) {
        System.out.println("TRY AGAIN, you have to type a name of at least two letters.");
      }

    } while (!validName);

    return name;
  }

  /**
   * Asks user for a members email.
   */
  public String promptEmail(Member[] members) {
    String email = "";
    boolean emailExists;
    do {
      System.out.println("Type members email:");
      email = scan.nextLine();

      emailExists = memberValidator.validateMemberEmail(members, email);

      if (emailExists) {
        System.out.println("TRY AGAIN, email already taken");
      }

    } while (emailExists);

    return email;
  }

  /**
   * Asks user for a members phone.
   */
  public String promptPhone(Member[] members) {
    String phone = "";
    boolean phoneExists;
    do {
      System.out.println("Type members phone:");
      phone = scan.nextLine();

      phoneExists = memberValidator.validateMemberPhone(members, phone);

      if (phoneExists) {
        System.out.println("TRY AGAIN, phone already taken");
      }

    } while (phoneExists);

    return phone;
  }

  /**
   * Asks user for a member ID.
   *
   * @param members - List of members to iterate.
   * @return - The member's id.
   */
  public String promptMemberId(Member[] members) {
    String memberId;
    boolean memberExists = false;
    do {
      System.out.println("Type your member ID:");
      memberId = scan.nextLine().toUpperCase();

      memberExists = memberValidator.validateMemberId(members, memberId);

      if (!memberExists) {
        System.out.println("TRY AGAIN, not a valid member ID");
      }
    } while (!memberExists);

    return memberId;
  }

  /**
   * Asks user for a member ID.
   *
   * @param members - List of members to iterate.
   * @return - The member's id.
   */
  public String promptOwnersMemberId(Member[] members) {
    String memberId;
    boolean memberExists = false;
    do {
      System.out.println("Type the member ID of owner of the item: ");
      memberId = scan.nextLine().toUpperCase();

      memberExists = memberValidator.validateMemberId(members, memberId);

      if (!memberExists) {
        System.out.println("TRY AGAIN, not a valid member ID");
      }
    } while (!memberExists);

    return memberId;
  }

  /**
   * Asks a member what to edit.
   */
  public MemberEditOption promptEditMember() {
    System.out.println("What do you want to edit? name / email / phone");
    String editOption = scan.nextLine().toLowerCase();

    switch (editOption) {
      case "name":
        return MemberEditOption.Name;
      case "email":
        return MemberEditOption.Email;
      case "phone":
        return MemberEditOption.Phone;
      default:
        return MemberEditOption.Nothing;
    }
  }

  /**
   * Show single member.
   *
   * @param memberId - The members ID.
   * @param members  - List of members to iterate.
   */
  public void showSingleMember(String memberId, Member[] members) {
    for (Member member : members) {
      if (member.getId().equals(memberId)) {
        System.out
            .println("MEMBER:\nName: " + member.getName() + "\nEmail: " + member.getEmail() + "\nID: " + member.getId()
                + "\nAmount of credit: " + member.getCredit() + "\nAmount of items: " + member.getItems().length
                + "\nCreated day: " + member.getDayCreated());
      }
    }
  }

  /**
   * Display a "simple information" list of members.
   *
   * @param members - List of members to iterate.
   */
  public void showMembersSimpleInfo(Member[] members) {
    for (Member member : members) {
      System.out.println("MEMBER:\nName: " + member.getName() + "\nEmail: "
          + member.getEmail() + "\nID: " + member.getId()
          + "\nAmount of credit: " + member.getCredit()
          + "\nAmount of items: " + member.getItems().length);
    }
  }

  /**
   * Display a "full information" list of members.
   *
   * @param members     - List of members to iterate.
   * @param currentTime - The current time.
   */
  public void showMembersFullInfo(Member[] members, int currentTime) {
    for (Member member : members) {
      System.out.println(
          "MEMBER:\nName: " + member.getName() + "\nEmail: " + member.getEmail() + "\nPhone: "
              + member.getPhone() + "\nID: " + member.getId() + "\nCreated day: "
              + member.getDayCreated() + "\nTotal credit: " + member.getCredit());
      if (member.getItems().length > 0) {
        System.out.println("ITEMS: ");
        for (Item item : member.getItems()) {
          System.out.println("Name: " + item.getName() + "\nDescription: " + item.getDescription()
              + "\nCategory: " + item.getCategory() + "\nCost per day: "
              + item.getCostPerDay() + "\nCreated day: " + item.getDayCreated());
          for (LendingContract lendingContract : item.getLendingContracts()) {
            if (currentTime >= lendingContract.getStartDay() && currentTime <= lendingContract.getEndDay()) {
              System.out.println("Currently lent to: " + lendingContract.getCurrentlyLentTo().getName()
                  + "\nFrom day: " + lendingContract.getStartDay() + "\nTo day: " + lendingContract.getEndDay());
            }
          }
        }
      }
    }
  }
}
