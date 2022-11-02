package view;

/**
 * Represents a membership application.
 */
public class MembershipApplication {
  private String name;
  private String email;
  private String phone;

  public MembershipApplication() {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void addName(String name) {
    String newMemberName = name;
    this.setName(newMemberName);
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void addEmail(String email) {
    String newMemberEmail = email;
    this.setEmail(newMemberEmail);
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void addPhone(String phone) {
    String newMemberPhone = phone;
    this.setPhone(newMemberPhone);
  }
}
