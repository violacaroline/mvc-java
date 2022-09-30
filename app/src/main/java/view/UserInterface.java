package view;

import java.util.Scanner;
import model.Member;

/**
 * Class representing a user interface.
 */
public class UserInterface {
  Scanner scan = new Scanner(System.in, "utf-8");

  /**
   * Display and await main menu options.
   *
   * @return - The chosen option.
   */
  public int mainMenu() {
    System.out.println("Menu Options:");
    System.out.println("1. See member menu");
    System.out.println("2. See item menu");
    System.out.println("3. Exit");
    System.out.println("Type Option:");

    int option = scan.nextInt();

    return option;
  }

  /**
   * Display and await main menu options.
   *
   * @return - The chosen option.
   */
  public int memberMenu() {
    System.out.println("Menu Options:");
    System.out.println("1. Create member");
    System.out.println("2. Delete member");
    System.out.println("3. Edit member");
    System.out.println("4. View member");
    System.out.println("5. List all members (Simple view: Name, Email, Credits, No of Items)");
    System.out.println("6. List all members (Full view: Name, Email, Info on all owned items");
    System.out.println("7. Exit");
    System.out.println("Type Option:");

    int option = scan.nextInt();

    return option;
  }

  /**
   * Display and await main menu options.
   *
   * @return - The chosen option.
   */
  public int itemMenu() {
    System.out.println("Menu Options:");
    System.out.println("1. Create item");
    System.out.println("2. Delete item");
    System.out.println("3. Edit item");
    System.out.println("4. View item");
    System.out.println("5. Exit");
    System.out.println("Type Option:");

    int option = scan.nextInt();

    return option;
  }

  /**
   * Display create member prompts.
   *
   * @return - The array of answers.
   */
  public String[] promptCreateMember() {
    String[] answerArray = new String[3];

    System.out.println("Type members name:");
    answerArray[0] = scan.next();
    System.out.println("Type members email:");
    answerArray[1] = scan.next();
    System.out.println("Type members phone:");
    answerArray[2] = scan.next();

    return answerArray;
  }

  /**
   * Display member ID prompt.
   *
   * @return - The member's id.
   */
  public String promptMemberId() {
    System.out.println("Type the member ID:");
    String memberId = scan.next();

    return memberId;
  }

  /**
   * Display edit member prompts.
   *
   * @param memberId - The members ID.
   * @param members  - List of members to iterate.
   */
  public void promptEditMember(String memberId, Member[] members) {
    
    // OBS - I AM REALLY EDITING THE MODEL FROM THE VIEW!!!!
    for (Member member : members) {
      if (member.getId().equals(memberId)) {
        System.out.println("What do you want to edit? name / email / phone");
        String whatToEdit = scan.next();

        switch (whatToEdit) {
          case "name":
            System.out.println("Type new name: ");
            String newName = scan.next();
            member.setName(newName);
            break;
          case "email":
            System.out.println("Type new email: ");
            String newEmail = scan.next();
            member.setEmail(newEmail);
            break;
          case "phone":
            System.out.println("Type new phone: ");
            String newPhone = scan.next();
            member.setPhone(newPhone);
            break;
          default:
            System.out.println("Option is invalid");
            break;
        }
      } else {
        System.out.println("Could not find member...");
      }
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
            .println("Name: " + member.getName() + "\nEmail: " + member.getEmail() + "\nPhone: " + member.getPhone()
                + "\nID: " + member.getId());
      }
    }
  }

  /**
   * Display a "simple information" list of members.
   */
  public void showMembersSimpleInfo(Member[] members) {
    for (Member member : members) {
      System.out.println("Name: " + member.getName() + "\nEmail: " + member.getEmail() + "\nPhone: " + member.getPhone()
          + "\nID: " + member.getId());
    }
  }

  /**
   * Display a "full information" list of members.
   */
  public void showMembersFullInfo(Member[] members) {
    System.out.println("The length" + members.length);
    for (Member member : members) {
      System.out.println("Name: " + member.getName() + "\nEmail: " + member.getEmail() + "\nPhone: " + member.getPhone()
          + "\nID: " + member.getId());
    }
  }

  /**
   * Shows a message by printing it to the console.
   *
   * @param message The message to show.
   */
  public void showMessage(String message) {
    System.out.println(message);
  }
}
