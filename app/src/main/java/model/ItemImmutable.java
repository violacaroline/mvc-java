package model;

import java.util.ArrayList;

/**
 * Represents an immutable item.
 */
public class ItemImmutable {
  private String category;
  private String name;
  private String description;
  private int costPerDay;
  private int dayCreated;
  private Member owner;
  private ArrayList<LendingContractImmutable> immutableLendingContracts = new ArrayList<>();

  /**
   * Instanciates a new immutable item object.
   */
  public ItemImmutable(String category, String name, String description, int costPerDay,
      int dayCreated, Member owner, ArrayList<LendingContractImmutable> lendingContracts) {
    this.category = category;
    this.name = name;
    this.description = description;
    this.costPerDay = costPerDay;
    this.dayCreated = dayCreated;
    this.immutableLendingContracts.addAll(lendingContracts);

    Member deepCopyOwner = new Member(owner.getName(), owner.getEmail(), owner.getPhone(),
        owner.getId(), owner.getDayCreated());
    this.owner = deepCopyOwner;
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
   * Gets an items name.
   *
   * @return - The items name.
   */
  public String getName() {
    return name;
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
   * Gets an items cost per day.
   *
   * @return - The items cost per day.
   */
  public int getCostPerDay() {
    return costPerDay;
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
   * Gets the owner of the item.
   *
   * @return - The owner.
   */
  public Member getOwner() {
    return this.owner;
  }

  /**
   * Add contract to item.
   *
   * @param lendingContract - The lending contract.
   */
  public void addLendingContract(LendingContractImmutable lendingContract) {
    this.immutableLendingContracts.add(lendingContract);
  }

  /**
   * Gets a copy of the items lending contracts.
   *
   * @return - The list of copied lending contracts.
   */
  public ArrayList<LendingContractImmutable> showLendingContracts() {
    ArrayList<LendingContractImmutable> deepCopyImmutableContracts = new ArrayList<>();
    deepCopyImmutableContracts.addAll(this.immutableLendingContracts);
    return deepCopyImmutableContracts;
  }
}
