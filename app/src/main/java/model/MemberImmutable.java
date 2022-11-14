package model;

import java.util.ArrayList;

/**
 * Represents an immutable member.
 */
public class MemberImmutable {
  private String name;
  private String email;
  private String phone;
  private String id;
  private int dayCreated;
  private int credit;
  private ArrayList<ItemImmutable> immutableItems = new ArrayList<>();

  /**
   * Instanciates a new immutable member object.
   */
  public MemberImmutable(String name, String email, String phone, String id,
      int dayCreated, int credit, ArrayList<Item> items) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.id = id;
    this.dayCreated = dayCreated;
    this.credit = credit;
    // ArrayList<ItemImmutable> immutableItems = new ArrayList<>();
    for (Item item : items) {
      this.immutableItems.add(item.getImmutableItem());
    }
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
   * Gets a members email.
   *
   * @return - The members email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Gets the members phone number.
   */
  public String getPhone() {
    return phone;
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
   * Gets an member's day created.
   *
   * @return - The member's day created.
   */
  public int getDayCreated() {
    return dayCreated;
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
   * Gets a copy of the members items.
   *
   * @return - The list of copied items.
   */
  public ArrayList<ItemImmutable> showItems() {
    ArrayList<ItemImmutable> deepCopyImmutableItems = new ArrayList<>();
    deepCopyImmutableItems.addAll(this.immutableItems);
    return deepCopyImmutableItems;
  }
}
