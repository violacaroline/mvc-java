package model;

import java.util.ArrayList;

/**
 * Class representing a member.
 */
public class Member {
  private String name;
  private String email;
  private String phone;
  private String id;
  private int dayCreated;
  private int credit;
  private ArrayList<Item> items = new ArrayList<>();

  /**
   * Instanciates a new member object.
   */
  public Member(String name, String email, String phone, String id, int dayCreated) {
    setName(name);
    setEmail(email);
    setPhone(phone);
    setId(id);
    setDayCreated(dayCreated);
  }

  /**
   * Gets members name.
   *
   * @return - The members name.
   */
  public String getName() {
    return name;
  }

  /**
   * Validates and sets a members name.
   *
   * @param name - The members name.
   */
  public void setName(String name) {
    if (name == null || name.length() < 2) {
      throw new IllegalArgumentException();
    } else {
      this.name = name;
    }
  }

  /**
   * Gets a members email.
   *
   * @return - The members email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the members email.
   *
   * @param email - The members email.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the members phone number.
   */
  public String getPhone() {
    return phone;
  }

  /**
   * Sets the members phone number.
   *
   * @param phone - The members phone number.
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * Gets a members id.
   *
   * @return - The members id.
   */
  public String getId() {
    return id;
  }

  /**
   * Sets a members id.
   *
   * @param id - The members id.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets an member's day created.
   *
   * @return - The member's day created.
   */
  public int getDayCreated() {
    return dayCreated;
  }

  /**
   * Sets the member's day created.
   *
   * @param dayCreated - The member's day created.
   */
  public void setDayCreated(int dayCreated) {
    this.dayCreated = dayCreated;
  }

  /**
   * Gets the member's credit.
   *
   * @return - The credit.
   */
  public int getCredit() {
    return credit;
  }

  /**
   * Sets the member's credit.
   *
   * @param credit - The value to set.
   */
  public void setCredit(int credit) {
    this.credit = credit;
  }

  /**
   * Increment the members credit.
   *
   * @param value - The value to increment credit with.
   */
  public void incrementCredit(int value) {
    this.credit = this.getCredit() + value;
  }

  /**
   * Decrement the members credit.
   *
   * @param cost - The value to deduct.
   */
  public void decrementCredit(int cost) {
    this.credit = this.getCredit() - cost;
  }

  /**
   * Creates a new item.
   */
  public void createItem(String category, String name, String description,
      int costPerDay, int dayCreated, Member owner) {
  
    Item item = new Item(category, name, description, costPerDay, dayCreated, owner);

    this.addItem(item);
    this.incrementCredit(100);
  }

  /**
   * Adds new item to member's list of items.
   *
   * @param item - The new item.
   */
  public void addItem(Item item) {
    this.items.add(item);
  }

  /**
   * Gets the items.
   *
   * @return - The existing items.
   */
  public Item[] getItems() {
    Item[] showItems = new Item[this.items.size()];

    showItems = this.items.toArray(showItems);

    return showItems;
  }

  /**
   * Delete item.
   *
   * @param itemName - The item to delete.
   */
  public void deleteitem(String itemName) {
    for (int i = 0; i < this.items.size(); i++) {
      if (this.items.get(i).getName().equals(itemName)) {
        this.items.remove(i);
      }
    }
  }
}
