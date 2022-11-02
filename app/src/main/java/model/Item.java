package model;

import java.util.ArrayList;

/**
 * Class representing an Item.
 */
public class Item {
  private String category;
  private String name;
  private String description;
  private int costPerDay;
  private int dayCreated;
  private Member owner;
  private ArrayList<LendingContract> lendingContracts = new ArrayList<>();

  /**
   * Instanciates a new item object.
   */
  public Item(String category, String name, String description, int costPerDay, int dayCreated, Member owner) {
    setCategory(category);
    setName(name);
    setDescription(description);
    setCostPerDay(costPerDay);
    setDayCreated(dayCreated);
    setOwner(owner);
  }

  /**
   * Gets items category.
   *
   * @return - The items category.
   */
  public String getCategory() {
    return category;
  }

  /**
   * Validates and sets an items category.
   *
   * @param category - The items category.
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * Gets an items name.
   *
   * @return - The items name.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the items name.
   *
   * @param name - The items name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets an items description.
   *
   * @return - The items description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the items description.
   *
   * @param description - The items description.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets an items cost per day.
   *
   * @return - The items cost per day.
   */
  public int getCostPerDay() {
    return costPerDay;
  }

  /**
   * Sets the items cost per day.
   *
   * @param costPerDay - The items cost per day.
   */
  public void setCostPerDay(int costPerDay) {
    this.costPerDay = costPerDay;
  }

  /**
   * Gets an items day created.
   *
   * @return - The items day created.
   */
  public int getDayCreated() {
    return dayCreated;
  }

  /**
   * Sets the items day created.
   *
   * @param dayCreated - The items day created.
   */
  protected void setDayCreated(int dayCreated) {
    this.dayCreated = dayCreated;
  }

  /**
   * Gets the owner of the item.
   *
   * @return - The owner.
   */
  public Member getOwner() {
    // Member deepCopyOwner = new Member(this.owner.getName(),
    // this.owner.getEmail(), this.owner.getPhone(),
    // this.owner.getId(), this.owner.getDayCreated());
    // return deepCopyOwner;

    /*
     * EXPOSING MUTABLE OBJECT - I WANT TO CHANGE AN ATTRIBUTE ON IT THOUGH e.g
     * increment credit???
     */
    return this.owner;
  }

  /**
   * Sets the owner of the item.
   *
   * @param owner - The owner.
   */
  protected void setOwner(Member owner) {
    // this.owner = new Member(owner.getName(), owner.getEmail(),
    // owner.getPhone(), owner.getId(), owner.getDayCreated());

    /*
     * EXPOSING MUTABLE OBJECT - I WANT TO CHANGE AN ATTRIBUTE ON IT THOUGH e.g
     * increment credit???
     */
    this.owner = owner;
  }

  /**
   * Add contract to item.
   *
   * @param lendingContract - The lending contract.
   */
  public void addLendingContract(LendingContract lendingContract) {
    this.lendingContracts.add(lendingContract);
  }

  /**
   * Get lending contracts.
   *
   * @return - The lending contracts.
   */
  public LendingContract[] getLendingContracts() {
    LendingContract[] showLendingContracts = new LendingContract[this.lendingContracts.size()];

    showLendingContracts = this.lendingContracts.toArray(showLendingContracts);

    return showLendingContracts;
  }
}
