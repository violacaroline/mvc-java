package model;

/**
 * Class representing a member.
 */
public class Member {
  private String name;
  private String email;
  private String phone;
  private String id;

  /**
   * Instanciates a new member object.
   */
  public Member(String name, String email, String phone, String id) {
    setName(name);
    setEmail(email);
    setPhone(phone);
    setId(id);
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
}
