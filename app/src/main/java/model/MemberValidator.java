package model;

/**
 * Validating member information - email/phone.
 */
public class MemberValidator {
  /**
   * Validate members name.
   *
   * @param name - The name to validate.
   * @return - True if it's a valid name.
   */
  public boolean validateName(String name) {
    boolean validName = false;
    if (!(name == null || name.length() < 2)) {
      validName = true;
    }
    return validName;
  }
  
  /**
   * Validates email.
   *
   * @param members - The list of members to iterate.
   * @param email - The new email to validate.
   * @return - True if email already exist.
   */
  public boolean validateMemberEmail(Member[] members, String email) {
    boolean emailAlreadyExists = false;

    for (Member member : members) {
      if (member.getEmail().equals(email)) {
        emailAlreadyExists = true;
      }
    }

    return emailAlreadyExists;
  }
  
  /**
   * Validates phone number.
   *
   * @param members - The list of members to iterate.
   * @param phone - The new phone number to validate.
   * @return - True if phone number already exist.
   */
  public boolean validateMemberPhone(Member[] members, String phone) {
    boolean phoneAlreadyExists = false;

    for (Member member : members) {
      if (member.getPhone().equals(phone)) {
        phoneAlreadyExists = true;
      }
    }

    return phoneAlreadyExists;
  }

  /**
   * Validate member ID.
   *
   * @return - True if member ID exists.
   */
  public boolean validateMemberId(Member[] members, String memberId) {
    boolean memberExists = false;

    for (Member member : members) {
      if (member.getId().equals(memberId.toUpperCase())) {
        memberExists = true;
      }
    }

    return memberExists;
  }

  /**
   * Validate a members specific item.
   *
   * @return - True if the member has an item with such a name.
   */
  public boolean validateMemberItem(Member[] members, String memberId, String itemName) {
    boolean itemExists = false;

    for (Member member : members) {
      if (member.getId().equals(memberId.toUpperCase())) {
        for (Item item : member.getItems()) {
          if (item.getName().equals(itemName)) {
            itemExists = true;
          }
        }
      }
    }
    return itemExists;
  }
}
