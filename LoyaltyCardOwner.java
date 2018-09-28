package impl;

import interfaces.ILoyaltyCardOwner;

/**
 * This class represents loyalty card owners.
 *
 */
public class LoyaltyCardOwner implements ILoyaltyCardOwner {

    private String email;
    private String name;

    /**
     * This is a constructor for creating a LoyaltyCardOwner. An email and a name String is needed to create the object.
     * @param email of owner
     * @param name of owner
     */
    public LoyaltyCardOwner (String email, String name) throws NullPointerException {

        if (email == null || name == null) {
            throw new NullPointerException();
        }
        this.email = email;
        this.name = name;
    }

    /**
     * This method returns the owner's email.
     * @return the email for this owner
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * This method returns the owner's name.
     * @return the name of the owner
     */
    @Override
    public String getName() {
        return name;
    }

}
