package view;

import java.util.Scanner;
import model.Item;
import model.LendingContract;
import model.Member;
import model.MemberValidator;

/**
 * Represents a UI handling item related interaction.
 */
public class ItemView {
  Scanner scan = new Scanner(System.in, "utf-8");
  MemberValidator memberValidator = new MemberValidator();

  /**
   * Asks a user for a category of the item.
   */
  public String promptItemCategory() {
    String category = "";
    boolean validCategory;

    do {
      System.out.println("Type item category: tool / vehicle / game / toy / sport / other");
      category = scan.nextLine();

      validCategory = isValidCategory(category);

      if (!validCategory) {
        System.out.println("TRY AGAIN, not a valid category.");
      }

    } while (!validCategory);

    return category;
  }

  /**
   * Asks user for an item name.
   *
   * @return - The items name.
   */
  public String promptItemName() {
    System.out.println("Type item name:");
    String itemName = scan.nextLine();

    return itemName;
  }

  /**
   * Asks a user for an item description.
   *
   * @return - The item description.
   */
  public String promptItemDescription() {
    System.out.println("Type item description:");
    String itemDescription = scan.nextLine();

    return itemDescription;
  }

  /**
   * Asks a user for an item cost.
   *
   * @return - The item cost.
   */
  public int promptItemCost() {
    System.out.println("Type item cost:");
    String cost = scan.nextLine();

    while (!isValidInt(cost)) {
      System.out.println("Cost has to be a valid number.");
      cost = scan.nextLine();
    }

    int itemCost = Integer.parseInt(cost);

    return itemCost;
  }

  /**
   * Asks a member what to edit.
   */
  public ItemEditOption promptEditItem() {
    System.out.println("What do you want to edit? category / name / description / cost");
    String editOption = scan.nextLine().toLowerCase();

    switch (editOption) {
      case "category":
        return ItemEditOption.Category;
      case "name":
        return ItemEditOption.Name;
      case "description":
        return ItemEditOption.Description;
      case "cost":
        return ItemEditOption.Cost;
      default:
        return ItemEditOption.Nothing;
    }
  }

  /**
   * Asks a user FROM what day an item is to be lent.
   *
   * @return - The start day of a lending period.
   */
  public String promptStartDayLendingPeriod() {
    System.out.println("From what day do you want to loan the item? ");
    String startDay = scan.nextLine();
    while (!isValidInt(startDay)) {
      System.out.println("TRY AGAIN, you have to type a valid number.");
      startDay = scan.nextLine();
    }

    // THIS NEEDS TO BE AN INT??
    return startDay;
  }

  /**
   * Asks a user TO what day an item is to be lent.
   *
   * @return - The end day of a lending period.
   */
  public String promptEndDayLendingPeriod() {
    System.out.println("To what day do you want to loan the item? ");
    String endDay = scan.nextLine();
    while (!isValidInt(endDay)) {
      System.out.println("TRY AGAIN, you have to type a valid number.");
      endDay = scan.nextLine();
    }

    // THIS NEEDS TO BE AN INT??
    return endDay;
  }

  /**
   * Test converting input to integer value.
   *
   * @param input - The input to validate.
   * @return - True if input converts to int.
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
   * Validates item category.
   *
   * @param category - The category to validate.
   * @return - True if item category is valid.
   */
  public boolean isValidCategory(String category) {
    String[] validCategories = { "tool", "vehicle", "game", "toy", "sport", "other" };
    boolean isValidCategory = false;

    for (String validCategory : validCategories) {
      if (validCategory.equals(category.toLowerCase())) {
        isValidCategory = true;
      }
    }

    return isValidCategory;
  }

  /**
   * Validates item name.
   *
   * @param members       - The members in where to find owner.
   * @param memberIdOwner - The id to find.
   * @param itemName      - The name to validate.
   */
  public boolean isValidItemName(Member[] members, String memberIdOwner, String itemName) {
    boolean isValidItemName = false;

    while (!memberValidator.validateMemberItem(members, memberIdOwner, itemName)) {
      System.out.println("TRY AGAIN, the member does not seem to have an item with such a name available.");
      itemName = scan.nextLine();
    }

    isValidItemName = true;

    return isValidItemName;
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
}
