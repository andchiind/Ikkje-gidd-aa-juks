package impl;

import interfaces.ILoyaltyCardOwner;

/**
 * This class represents loyalty card owners.
 *
 */
public class LoyaltyCardOwner implements ILoyaltyCardOwner {

    private String email;
    private String name;

    public LoyaltyCardOwner (String email, String name) {
        this.email = email;
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getName() {
        return name;
    }

}
