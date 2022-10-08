package model;

/**
 * Represents a lending contract.
 */
public class LendingContract {
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
  protected LendingContract(int startDay, int endDay, Item item, Member currentlyLentTo) {
    setStartDay(startDay);
    setEndDay(endDay);
    setItem(item);
    setCurrentlyLentTo(currentlyLentTo);
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
   * Sets the lending contract's starting day.
   *
   * @param startDay - The day the contract is valid FROM.
   */
  protected void setStartDay(int startDay) {
    this.startDay = startDay;
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
   * Sets the lending contract's ending day.
   *
   * @param endDay - The day the contract is valid TO.
   */
  protected void setEndDay(int endDay) {
    this.endDay = endDay;
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
   * Sets the lending contract's specific item.
   *
   * @param item - The item.
   */
  protected void setItem(Item item) {
    this.item = new Item(item.getCategory(), item.getName(), item.getDescription(),
        +item.getCostPerDay(), item.getDayCreated(), item.getOwner());
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

  /**
   * Sets the member currently loaning the item.
   *
   * @param currentlyLentTo - The member item is lent to.
   */
  protected void setCurrentlyLentTo(Member currentlyLentTo) {
    this.currentlyLentTo = new Member(currentlyLentTo.getName(), currentlyLentTo.getEmail(),
        currentlyLentTo.getPhone(), currentlyLentTo.getId(), currentlyLentTo.getDayCreated());
  }
}
