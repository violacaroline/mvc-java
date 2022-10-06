package view;

import java.util.Scanner;
import model.Item;
import model.LendingContract;
import model.Member;
import model.MemberValidator;

/**
 * Class representing a user interface.
 */
public class UserInterface {
  Scanner scan = new Scanner(System.in, "utf-8");
  MemberValidator memberValidator = new MemberValidator();

  /**
   * Display and await main menu options.
   *
   * @return - The chosen option.
   */
  public int mainMenu() {
    System.out.println("Menu Options:");
    System.out.println("1. See member menu");
    System.out.println("2. See item menu");
    System.out.println("3. Loan an item");
    System.out.println("4. Advance time");
    System.out.println("5. Quit");
    System.out.println("Type Option:");

    int option = scan.nextInt();

    return option;
  }

  /**
   * Display and await member menu options.
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
    System.out.println("7. Go Back");
    System.out.println("Type Option:");

    int option = scan.nextInt();

    return option;
  }

  /**
   * Display and await item menu options.
   *
   * @return - The chosen option.
   */
  public int itemMenu() {
    System.out.println("Menu Options:");
    System.out.println("1. Create item");
    System.out.println("2. Delete item");
    System.out.println("3. Edit item");
    System.out.println("4. View item");
    System.out.println("5. Go Back");
    System.out.println("Type Option:");

    int option = scan.nextInt();

    return option;
  }

  /**
   * Display member ID prompt.
   *
   * @param members - List of members to iterate.
   * @return - The member's id.
   */
  public String promptMemberId(Member[] members) {
    String memberId;
    boolean memberExists = false;
    do {
      System.out.println("Type your member ID:");
      memberId = scan.next().toUpperCase();

      memberExists = memberValidator.validateMemberId(members, memberId);

      if (!memberExists) {
        System.out.println("TRY AGAIN, not a valid member ID");
      }
    } while (!memberExists);

    return memberId;
  }

  /**
   * Display create member prompts.
   *
   * @param members - List of members to iterate.
   * @return - The array of answers.
   */
  public String[] promptCreateMember(Member[] members) {
    String[] answerArray = new String[3];

    System.out.println("Type members name:");
    scan.nextLine();
    answerArray[0] = scan.nextLine();

    boolean emailExists;
    boolean phoneExists;
    do {
      System.out.println("Type members email:");
      answerArray[1] = scan.nextLine();

      emailExists = memberValidator.validateMemberEmail(members, answerArray[1]);

      if (emailExists) {
        System.out.println("TRY AGAIN, email already taken");
      }
    } while (emailExists);

    do {
      System.out.println("Type members phone:");
      answerArray[2] = scan.nextLine();

      phoneExists = memberValidator.validateMemberPhone(members, answerArray[2]);

      if (phoneExists) {
        System.out.println("TRY AGAIN, phone already taken");
      }
    } while (phoneExists);

    return answerArray;
  }

  /**
   * Display edit member prompts.
   *
   * @param memberId - The members ID.
   * @param members  - List of members to iterate.
   * @return - The array of answers.
   */
  public String[] promptEditMember(String memberId, Member[] members) {
    String[] answerArray = new String[3];

    answerArray[0] = memberId;
    System.out.println("What do you want to edit? name / email / phone");
    answerArray[1] = scan.next().toLowerCase();

    System.out.println("Type its new value: ");
    answerArray[2] = scan.next().toLowerCase();

    if (answerArray[1].equals("email") || answerArray[1].equals("phone")) {
      switch (answerArray[1]) {
        case "email":
          while (memberValidator.validateMemberEmail(members, answerArray[2])) {
            System.out.println("TRY AGAIN, email already exists: ");
            answerArray[2] = scan.next().toLowerCase();
          }
          break;
        case "phone":
          while (memberValidator.validateMemberPhone(members, answerArray[2])) {
            System.out.println("TRY AGAIN, phone already exists: ");
            answerArray[2] = scan.next().toLowerCase();
          }
          break;

        default:
          break;
      }
    }

    return answerArray;
  }

  /**
   * Display create item prompts.
   *
   * @return - The array of answers.
   */
  public String[] promptCreateItem() {
    String[] answerArray = new String[4];

    System.out.println("Type item category: tool / vehicle / game / toy / sport / other");
    answerArray[0] = scan.next();
    System.out.println("Type item name:");
    answerArray[1] = scan.next();
    System.out.println("Type item description:");
    scan.nextLine();
    answerArray[2] = scan.nextLine();
    System.out.println("Type item cost:");
    answerArray[3] = scan.next();

    return answerArray;
  }

  /**
   * Display edit item prompts.
   *
   * @param memberId - The members ID.
   * @param members  - List of members to iterate.
   * @return - An array of answers.
   */
  public String[] promptEditItem(String memberId, Member[] members) {
    String[] answerArray = new String[4];

    answerArray[0] = memberId;

    System.out.println("Type name of item: ");
    answerArray[1] = scan.next().toLowerCase();

    System.out.println("What do you want to edit? category / name / description / cost");
    answerArray[2] = scan.next().toLowerCase();

    System.out.println("Type new value: ");
    answerArray[3] = scan.next().toLowerCase();

    return answerArray;
  }

  /**
   * Display get item name prompt.
   *
   * @return - The item's name.
   */
  public String promptGetItemName() {
    System.out.println("Type items name:");
    String itemName = scan.next();

    return itemName;
  }

  /**
   * Display loan an item prompts.
   *
   * @param members - List of members to iterate.
   * @return - The array of answers.
   */
  public String[] promptLoanAnItem(Member[] members) {
    String[] answerArray = new String[5];

    boolean loanerExists;
    String memberIdLoaner;
    do {
      System.out.println("Please state your member ID: ");
      memberIdLoaner = scan.next().toUpperCase();

      loanerExists = memberValidator.validateMemberId(members, memberIdLoaner);

      if (!loanerExists) {
        System.out.println("TRY AGAIN, not a valid member ID");
      } else {
        answerArray[0] = memberIdLoaner;
      }
    } while (!loanerExists);

    boolean ownerExists;
    String memberIdOwner;
    do {
      System.out.println("Please state member ID of the owner of the item: ");
      memberIdOwner = scan.next().toUpperCase();

      ownerExists = memberValidator.validateMemberId(members, memberIdOwner);

      if (!ownerExists) {
        System.out.println("TRY AGAIN, not a valid member ID");
      } else {
        answerArray[1] = memberIdOwner;
      }
    } while (!ownerExists);

    answerArray[2] = this.promptGetItemName();
    System.out.println("From what day do you want to loan the item? ");
    answerArray[3] = scan.next();
    System.out.println("To what day do you want to loan the item? ");
    answerArray[4] = scan.next();

    return answerArray;
  }

  /**
   * Prompt advance time.
   *
   * @return - The amount of time to advance.
   */
  public int promptAdvanceTime() {
    System.out.println("How many days do you want to advance?");
    int amountDays = scan.nextInt();

    return amountDays;
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

  /**
   * Display an item.
   *
   * @param itemName - The items name.
   * @param memberId - The ID of the member owning the item.
   * @param members  - List of members to iterate.
   */
  public void showSingleItem(String memberId, Member[] members, String itemName) {
    for (Member member : members) {
      if (member.getId().equals(memberId)) {
        for (Item item : member.getItems()) {
          if (item.getName().equals(itemName)) {
            System.out
                .println("Name: " + item.getName() + "\nCategory: " + item.getCategory() + "\nDescription: "
                    + item.getDescription()
                    + "\nCost: " + item.getCostPerDay() + "\nCreated day: " + item.getDayCreated());
            if (item.getLendingContracts().length > 0) {
              System.out.println("CONTRACTS:");
              for (LendingContract lendingContract : item.getLendingContracts()) {
                System.out.println(
                    "Item name: " + item.getName() + "\nStart day: " + lendingContract.getStartDay() + "\nEnd day: "
                        + lendingContract.getEndDay());
              }
            }
          }
        }
      }
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
