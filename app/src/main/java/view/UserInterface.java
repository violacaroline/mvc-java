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
  public MainMenuEvent mainMenu() {
    System.out.println("\n====== MAIN MENU ======");
    System.out.println("Menu Options:");
    System.out.println("1. See member menu");
    System.out.println("2. See item menu");
    System.out.println("3. Loan an item");
    System.out.println("4. Advance time");
    System.out.println("5. Quit");
    System.out.println("Type Option:");

    String choice = scan.nextLine();

    switch (choice) {
      case "1":
        return MainMenuEvent.SeeMemberMenu;
      case "2":
        return MainMenuEvent.SeeItemMenu;
      case "3":
        return MainMenuEvent.LoanItem;
      case "4":
        return MainMenuEvent.AdvanceTime;
      case "5":
        return MainMenuEvent.Quit;
      default:
        return MainMenuEvent.Nothing;
    }
  }

  /**
   * Display and await member menu options.
   *
   * @return - The chosen option.
   */
  public MemberMenuEvent memberMenu() {
    System.out.println("\n====== MEMBER MENU ======");
    System.out.println("Menu Options:");
    System.out.println("1. Create member");
    System.out.println("2. Delete member");
    System.out.println("3. Edit member");
    System.out.println("4. View member");
    System.out.println("5. List all members (Simple view: Name, Email, Credits, No of Items)");
    System.out.println("6. List all members (Full view: Name, Email, Info on all owned items");
    System.out.println("7. Go Back");
    System.out.println("Type Option:");

    String choice = scan.nextLine();

    switch (choice) {
      case "1":
        return MemberMenuEvent.CreateMember;
      case "2":
        return MemberMenuEvent.DeleteMember;
      case "3":
        return MemberMenuEvent.EditMember;
      case "4":
        return MemberMenuEvent.ViewMember;
      case "5":
        return MemberMenuEvent.SeeAllMembersSimpleList;
      case "6":
        return MemberMenuEvent.SeeAllMembersFullList;
      case "7":
        return MemberMenuEvent.GoBack;
      default:
        return MemberMenuEvent.Nothing;
    }
  }

  /**
   * Display and await item menu options.
   *
   * @return - The chosen option.
   */
  public ItemMenuEvent itemMenu() {
    System.out.println("\n====== ITEM MENU ======");
    System.out.println("Menu Options:");
    System.out.println("1. Create item");
    System.out.println("2. Delete item");
    System.out.println("3. Edit item");
    System.out.println("4. View item");
    System.out.println("5. Go Back");
    System.out.println("Type Option:");

    String choice = scan.nextLine();

    switch (choice) {
      case "1":
        return ItemMenuEvent.CreateItem;
      case "2":
        return ItemMenuEvent.DeleteItem;
      case "3":
        return ItemMenuEvent.EditItem;
      case "4":
        return ItemMenuEvent.ViewItem;
      case "5":
        return ItemMenuEvent.GoBack;
      default:
        return ItemMenuEvent.Nothing;
    }
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
      memberId = scan.nextLine().toUpperCase();

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
  public MembershipApplication promptCreateMember(Member[] members) {
    MembershipApplication membershipApplication = new MembershipApplication();

    boolean validName;
    do {
      System.out.println("Type members name:");
      String name = scan.nextLine();

      validName = memberValidator.validateName(name);

      if (!validName) {
        System.out.println("TRY AGAIN, you have to type a name of at least two letters.");
      } else {
        membershipApplication.addName(name);
      }

    } while (!validName);

    boolean emailExists;
    do {
      System.out.println("Type members email:");
      String email = scan.nextLine();

      emailExists = memberValidator.validateMemberEmail(members, email);

      if (emailExists) {
        System.out.println("TRY AGAIN, email already taken");
      } else {
        membershipApplication.addEmail(email);
      }
  
    } while (emailExists);

    boolean phoneExists;
    do {
      System.out.println("Type members phone:");
      String phone = scan.nextLine();

      phoneExists = memberValidator.validateMemberPhone(members, phone);

      if (phoneExists) {
        System.out.println("TRY AGAIN, phone already taken");
      } else {
        membershipApplication.addPhone(phone);
      }

    } while (phoneExists);

    return membershipApplication;
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
    answerArray[1] = scan.nextLine().toLowerCase();

    System.out.println("Type its new value: ");
    answerArray[2] = scan.nextLine();

    if (answerArray[1].equals("email") || answerArray[1].equals("phone")) {
      switch (answerArray[1]) {
        case "email":
          while (memberValidator.validateMemberEmail(members, answerArray[2])) {
            System.out.println("TRY AGAIN, email already exists: ");
            answerArray[2] = scan.nextLine().toLowerCase();
          }
          break;
        case "phone":
          while (memberValidator.validateMemberPhone(members, answerArray[2])) {
            System.out.println("TRY AGAIN, phone already exists: ");
            answerArray[2] = scan.nextLine().toLowerCase();
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
    answerArray[0] = scan.nextLine();
    System.out.println("Type item name:");
    answerArray[1] = scan.nextLine();
    System.out.println("Type item description:");
    answerArray[2] = scan.nextLine();
    System.out.println("Type item cost:");
    answerArray[3] = scan.nextLine();
    while (!testInt(answerArray[3])) {
      System.out.println("Cost has to be a valid number.");
      answerArray[3] = scan.nextLine();
    }

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
    answerArray[1] = scan.nextLine().toLowerCase();

    System.out.println("What do you want to edit? category / name / description / cost");
    answerArray[2] = scan.nextLine().toLowerCase();

    System.out.println("Type new value: ");
    answerArray[3] = scan.nextLine().toLowerCase();

    return answerArray;
  }

  /**
   * Display get item name prompt.
   *
   * @return - The item's name.
   */
  public String promptGetItemName() {
    System.out.println("Type items name:");
    String itemName = scan.nextLine();

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
      memberIdLoaner = scan.nextLine().toUpperCase();

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
      memberIdOwner = scan.nextLine().toUpperCase();

      ownerExists = memberValidator.validateMemberId(members, memberIdOwner);

      if (!ownerExists) {
        System.out.println("TRY AGAIN, not a valid member ID");
      } else {
        answerArray[1] = memberIdOwner;
      }
    } while (!ownerExists);

    answerArray[2] = this.promptGetItemName();

    while (!memberValidator.validateMemberItem(members, memberIdOwner, answerArray[2])) {
      System.out.println("TRY AGAIN, the member does not seem to have an item with such a name available.");
      answerArray[2] = scan.nextLine();
    }

    System.out.println("From what day do you want to loan the item? ");
    answerArray[3] = scan.nextLine();
    while (!testInt(answerArray[3])) {
      System.out.println("TRY AGAIN, you have to type a valid number.");
      answerArray[3] = scan.nextLine();
    }

    System.out.println("To what day do you want to loan the item? ");
    answerArray[4] = scan.nextLine();
    while (!testInt(answerArray[4])) {
      System.out.println("TRY AGAIN, you have to type a valid number.");
      answerArray[4] = scan.nextLine();
    }

    return answerArray;
  }

  /**
   * Prompt advance time.
   *
   * @return - The amount of time to advance.
   */
  public int promptAdvanceTime() {
    System.out.println("How many days do you want to advance?");
    String input = scan.nextLine();

    while (!testInt(input)) {
      System.out.println("TRY AGAIN, you have to type a valid number.");
      input = scan.nextLine();
    }

    int amountDays = Integer.parseInt(input);

    return amountDays;
  }

  /*
   * Test converting input to integer value.
   *
   * @param input - The input to test.
   * 
   * @return - True if input is a valid int.
   */
  private boolean testInt(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException error) {
      return false;
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

  /**
   * Display an item.
   *
   * @param itemName - The items name.
   * @param memberId - The ID of the member owning the item.
   * @param members  - List of members to iterate.
   */
  public void showSingleItem(String memberId, Member[] members, String itemName) {
    if (memberValidator.validateMemberItem(members, memberId, itemName)) {
      for (Member member : members) {
        if (member.getId().equals(memberId)) {
          for (Item item : member.getItems()) {
            if (item.getName().equals(itemName)) {
              System.out
                  .println("ITEM:\nName: " + item.getName() + "\nCategory: " + item.getCategory() + "\nDescription: "
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
    } else {
      System.out.println("Could not find item.");
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
