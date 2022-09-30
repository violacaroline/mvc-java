package model;

/**
 * Class representing an Item.
 */
public class Item {
  private String category;
  private String name;
  private String description;
  private int costPerDay;
  private int dayCreated;

  public Item(String category, String name, String description, int costPerDay, int dayCreated) {
    setCategory(category);
    setName(name);
    setDescription(description);
    setCostPerDay(costPerDay);
    setDayCreated(dayCreated);
  }

  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public int getCostPerDay() {
    return costPerDay;
  }
  public void setCostPerDay(int costPerDay) {
    this.costPerDay = costPerDay;
  }
  public int getDayCreated() {
    return dayCreated;
  }
  public void setDayCreated(int dayCreated) {
    this.dayCreated = dayCreated;
  }
}
