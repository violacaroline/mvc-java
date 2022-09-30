package view;

import controller.StuffLendingSystem;
import java.util.Scanner;
import model.Member;

/**
 * Class representing a user interface.
 */
public class UserInterface {
  Scanner scan = new Scanner(System.in, "utf-8");
  controller.StuffLendingSystem stuffLendingsystem = new StuffLendingSystem();

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
   * Display view member prompts.
   *
   * @return - The member's id.
   */
  public String promptViewMember() {
    System.out.println("Type the member ID:");
    String memberId = scan.next();

    return memberId;
  }

  /**
   * Display delete member prompts.
   *
   * @return - The member's id.
   */
  public String promptDeleteMember() {
    System.out.println("Type the member ID:");
    String memberId = scan.next();

    return memberId;
  }

  /**
   * Show single member.
   *
   * @param memberId - The members ID
   */
  public void showSingleMember(String memberId, Member[] members) {
    for (Member member : members) {
      if (member.getId().equals(memberId)) {
        System.out.println("Name: " + member.getName() + "\nEmail: " + member.getEmail() + "\nPhone: " + member.getPhone()
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
