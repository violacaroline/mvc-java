package view;

import java.util.Scanner;
import model.Item;
import model.LendingContract;
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
    System.out.println("3. Loan an item");
    System.out.println("4. Quit");
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
   * @return - The member's id.
   */
  public String promptMemberId() {
    System.out.println("Type the member ID:");
    String memberId = scan.next();

    return memberId;
  }

  /**
   * Display create member prompts.
   *
   * @return - The array of answers.
   */
  public String[] promptCreateMember() {
    String[] answerArray = new String[3];

    System.out.println("Type members name:");
    scan.nextLine(); // UGLYYYYYY, HEEEEELP
    answerArray[0] = scan.nextLine();
    System.out.println("Type members email:");
    answerArray[1] = scan.next();
    System.out.println("Type members phone:");
    answerArray[2] = scan.next();

    return answerArray;
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
            scan.nextLine(); // UGLYYYYYY, HEEEEELP
            String newName = scan.nextLine();
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
    // REALLY UGLY SOLUTION - WHAT THE FUCK IS THIS NEXTLINE PROBLEM? ANNOYING.. FIX
    // THIS SHIT
    scan.nextLine();
    System.out.println("Type item description:");
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
   */
  public void promptEditItem(String memberId, Member[] members) {

    // OBS - I AM REALLY EDITING THE MODEL FROM THE VIEW!!!!
    for (Member member : members) {
      if (member.getId().equals(memberId)) {
        System.out.println("Type name of item that you want to edit: ");
        String itemName = scan.next();
        for (Item item : member.getItems()) {
          if (item.getName().equals(itemName)) {
            System.out.println("What do you want to edit? category / name / description / cost");
            String whatToEdit = scan.next();

            switch (whatToEdit) {
              case "category":
                System.out.println("Type new item category: tool / vehicle / game / toy / sport / other");
                String newCategory = scan.next();
                item.setCategory(newCategory);
                break;
              case "name":
                System.out.println("Type new name: ");
                String newName = scan.next();
                item.setName(newName);
                break;
              case "description":
                System.out.println("Type new description: ");
                scan.nextLine(); // UGLYYYYYY, HEEEEELP
                String newDescription = scan.nextLine();
                item.setDescription(newDescription);
                break;
              case "cost":
                System.out.println("Type new cost: ");
                int newCost = scan.nextInt();
                item.setCostPerDay(newCost);
                break;
              default:
                System.out.println("Option is invalid");
                break;
            }
          }
        }
      } else {
        System.out.println("Could not find item...");
      }
    }
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
   * @return - The array of answers.
   */
  public String[] promptLoanAnItem() {
    String[] answerArray = new String[5];

    System.out.println("Please state your member ID: ");
    answerArray[0] = scan.next();
    System.out.println("Please state member ID of the owner of the item: ");
    answerArray[1] = scan.next();
    answerArray[2] = this.promptGetItemName();
    System.out.println("From what day do you want to loan the item? ");
    answerArray[3] = scan.next();
    System.out.println("To what day do you want to loan the item? ");
    answerArray[4] = scan.next();

    return answerArray;
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
            .println("Name: " + member.getName() + "\nEmail: " + member.getEmail() + "\nID: " + member.getId()
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
      System.out.println("Name: " + member.getName() + "\nEmail: " + member.getEmail() + "\nID: " + member.getId()
          + "\nAmount of credit: " + member.getCredit()
          + "\nAmount of items: " + member.getItems().length);
    }
  }

  /**
   * Display a "full information" list of members.
   *
   * @param members - List of members to iterate.
   */
  public void showMembersFullInfo(Member[] members) {
    for (Member member : members) {
      System.out.println(
          "Name: " + member.getName() + "\nEmail: " + member.getEmail() + "\nID: " + member.getId() + "\nCreated day: "
              + member.getDayCreated() + "\nTotal credit: " + member.getCredit());
      if (member.getItems().length > 0) {
        System.out.println("ITEMS: ");
        for (Item item : member.getItems()) {
          System.out.println("Name: " + item.getName() + "\nDescription: " + item.getDescription()
              + "\nCategory: " + item.getCategory() + "\nCost per day: "
              + item.getCostPerDay() + "\nCreated day: " + item.getDayCreated());
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
                System.out.println("Start day: " + lendingContract.getStartDay() + "\nEnd day: "
                    + lendingContract.getEndDay() + "\nItem name: " + item.getName());
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
