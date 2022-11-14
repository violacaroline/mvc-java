package model;

/**
 * Representing an immutable lending contract.
 */
public class LendingContractImmutable {
  private int startDay;
  private int endDay;
  private Item item;
  private Member currentlyLentTo;

  /**
   * Instansiates a new lending contract object.
   *
   * @param startDay - The day FROM which it is valid.
   * @param endDay   - The day TO which it is valid.
   * @param item     - The item that it covers.
   */
  public LendingContractImmutable(int startDay, int endDay, Item item, Member currentlyLentTo) {
    Item deepCopyItem = new Item(item.getCategory(), item.getName(), item.getDescription(),
        item.getCostPerDay(), item.getDayCreated(), item.getOwner());
    Member deepCopyCurrentlyLentTo = new Member(currentlyLentTo.getName(), currentlyLentTo.getEmail(),
        currentlyLentTo.getPhone(), currentlyLentTo.getId(), currentlyLentTo.getDayCreated());
    this.item = deepCopyItem;
    this.currentlyLentTo = deepCopyCurrentlyLentTo;
    this.startDay = startDay;
    this.endDay = endDay;
  }

  /**
   * Gets the lending contract's starting day.
   *
   * @return - The day the contract is valid FROM..
   */
  public int getStartDay() {
    return startDay;
  }

  /**
   * Gets the lending contract's ending day.
   *
   * @return - The end day.
   */
  public int getEndDay() {
    return endDay;
  }

  /**
   * Gets the lending contract's specific item.
   *
   * @return - The item.
   */
  public Item getItem() {
    Item deepCopyItem = new Item(this.item.getCategory(), this.item.getName(), this.item.getDescription(),
        +this.item.getCostPerDay(), this.item.getDayCreated(), this.item.getOwner());

    return deepCopyItem;
  }

  /**
   * Gets the member currently loaning the item.
   *
   * @return - The member item is lent to.
   */
  public Member getCurrentlyLentTo() {
    Member deepCopyMember = new Member(this.currentlyLentTo.getName(), this.currentlyLentTo.getEmail(),
        this.currentlyLentTo.getPhone(), this.currentlyLentTo.getId(), this.currentlyLentTo.getDayCreated());
    return deepCopyMember;
  }
}
