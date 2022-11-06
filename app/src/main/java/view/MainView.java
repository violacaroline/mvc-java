package view;

import java.util.Scanner;
import model.Time;

/**
 * Class representing a user interface.
 */
public class MainView {
  Scanner scan = new Scanner(System.in, "utf-8");

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
   * Prompt advance time.
   *
   * @return - The amount of time to advance.
   */
  public int promptAdvanceTime() {
    System.out.println("How many days do you want to advance?");
    String input = scan.nextLine();

    while (!isValidInt(input)) {
      System.out.println("TRY AGAIN, you have to type a valid number.");
      input = scan.nextLine();
    }

    int amountDays = Integer.parseInt(input);

    return amountDays;
  }

  /**
   * Test converting input to integer value.
   *
   * @param input - The input to test.
   * @return - True if input is a valid int.
   */
  private boolean isValidInt(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException error) {
      return false;
    }
  }
 
  /**
   * Shows a message by printing it to the console.
   *
   * @param message The message to show.
   */
  public void showMessage(InfoMessage message) {
    switch (message) {
      case GoingBack:
        System.out.println("Going back...");
        break;
      case Quitting:
        System.out.println("Quitting...");
        break;
      case OptionInvalid:
        System.out.println("Option is invalid");
        break;
      case ContractDenied:
        System.out.println("The contract was denied.");
        break;
      case CurrentDay:
        System.out.println("Current day: ");
        break;
      case TypeNumber:
        System.out.println("You have to type a number");
        break;
      case Error:
        System.out.println("Something went wrong, please restart app.");
        break;
      default:
        break;
    }
  }

  /**
   * Display the time.
   *
   * @param time - The current time to display.
   */
  public void showTime(Time time) {
    System.out.println("Current day: " + time.getTime());
  }
}
