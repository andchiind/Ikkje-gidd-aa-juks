package impl;

import interfaces.IFactory;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOperator;
import interfaces.ILoyaltyCardOwner;

/**
 * This class implements a singleton factory.
 *
 */
public final class Factory implements IFactory {

    private static IFactory factoryInstance = null;

    /**
     * Method which returns an instance of the singleton Factory class.
     * @return the instance of the Factory
     */
    public static IFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    /**
     * Creates an instance of {@link ILoyaltyCardOwner}.
     * @param email the owner's email
     * @param name the owner's name
     * @return the owner
     */
    @Override
    public ILoyaltyCardOwner makeLoyaltyCardOwner(String email, String name) {
        return new LoyaltyCardOwner(email, name);
    }

    /**
     * Creates an instance of {@link ILoyaltyCard} for the specified owner.
     * @param loyaltyCardOwner the owner of the card
     * @return the LoyaltyCard instance
     */
    @Override
    public ILoyaltyCard makeLoyaltyCard(ILoyaltyCardOwner loyaltyCardOwner) {
        return new LoyaltyCard(loyaltyCardOwner);
    }

    /**
     * Creates an instance of {@link ILoyaltyCardOperator}.
     *
     * @return the LoyaltyCardOperator
     */
    @Override
    public ILoyaltyCardOperator makeLoyaltyCardOperator() {
        return new LoyaltyCardOperator();
    }
}
