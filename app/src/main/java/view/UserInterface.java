package view;

import java.util.Scanner;

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
   * Shows a message by printing it to the console.
   *
   * @param message The message to show.
   */
  public void showMessage(String message) {
    System.out.println(message);
  }
}
