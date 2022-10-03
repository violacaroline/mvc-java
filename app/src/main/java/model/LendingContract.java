package model;

/**
 * Represents a lending contract.
 */
public class LendingContract {
  private int startDay;
  private int endDay;
  private Item item;

  /**
   * Instansiates a new lending contract object.
   *
   * @param startDay - The day FROM which it is valid.
   * @param endDay   - The day TO which it is valid.
   * @param item     - The item that it covers.
   */
  public LendingContract(int startDay, int endDay, Item item) {
    setStartDay(startDay);
    setEndDay(endDay);
    setItem(item);
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
  public void setStartDay(int startDay) {
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
  public void setEndDay(int endDay) {
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
  public void setItem(Item item) {
    this.item = new Item(item.getCategory(), item.getName(), item.getDescription(),
        +item.getCostPerDay(), item.getDayCreated(), item.getOwner());
  }
}
