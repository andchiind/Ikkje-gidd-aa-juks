package impl;

import common.AbstractFactoryClient;
import common.InsufficientPointsException;
import common.OwnerAlreadyRegisteredException;
import common.OwnerNotRegisteredException;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOperator;
import interfaces.ILoyaltyCardOwner;

import java.util.ArrayList;

/**
 * This class represents a simple loyalty card operator.
 *
 */
public class LoyaltyCardOperator extends AbstractFactoryClient implements ILoyaltyCardOperator {

    private ArrayList<String> registeredOwners = new ArrayList<>();
    private ArrayList<ILoyaltyCard> cards = new ArrayList<>();

    /**
     * Registers the specified card owner.
     * @param loyaltyCardOwner the owner to register
     * @throws OwnerAlreadyRegisteredException if the owner's email is already registered
     */
    @Override
    public void registerOwner(ILoyaltyCardOwner loyaltyCardOwner) throws OwnerAlreadyRegisteredException {

        if (registeredOwners.contains(loyaltyCardOwner.getEmail())) {
            throw new OwnerAlreadyRegisteredException();
        } else {
            registeredOwners.add(loyaltyCardOwner.getEmail());
            cards.add(Factory.getInstance().makeLoyaltyCard(loyaltyCardOwner));
        }
    }

    /**
     * Unregisters the specified loyalty card owner.
     * @param loyaltyCardOwner the owner to unregister
     * @throws OwnerNotRegisteredException if the given owner's email is not registered
     */
    @Override
    public void unregisterOwner(ILoyaltyCardOwner loyaltyCardOwner) throws OwnerNotRegisteredException {

        if (!registeredOwners.contains(loyaltyCardOwner.getEmail())) {
            throw new OwnerNotRegisteredException();
        } else {
            registeredOwners.remove(loyaltyCardOwner.getEmail());
        }
    }

    /**
     * Processes a monetary purchase for the specified owner and price and adds pence/100 points to the owners loyalty card.
     * @param ownerEmail the loyalty card owner's email
     * @param pence the price of the purchase in pence
     * @throws OwnerNotRegisteredException if the given owner's email is not registered
     */
    @Override
    public void processMoneyPurchase(String ownerEmail, int pence) throws OwnerNotRegisteredException {

        for (ILoyaltyCard card: cards) {
            if (card.getOwner().getEmail().equals(ownerEmail)) {
                card.addPoints(pence/100);
                return;
            }
        }
        throw new OwnerNotRegisteredException();
    }

    /**
     * Processes a purchase of an item for the specified price in pence using points on the owner's loyalty card, once earned, each point on the card is worth 1 pence.
     * @param ownerEmail the loyalty card owner's email
     * @param pence the price of the item in pence
     * @throws InsufficientPointsException if the card does not contain at least the same number of points as the price
     * @throws OwnerNotRegisteredException if the given owner's email has not been registered
     */
    @Override
    public void processPointsPurchase(String ownerEmail, int pence)
            throws InsufficientPointsException, OwnerNotRegisteredException {

        if (pence > 0) {
            for (ILoyaltyCard card : cards) {
                if (card.getOwner().getEmail().equals(ownerEmail)) {

                    if (card.getNumberOfPoints() < pence) {
                        throw new InsufficientPointsException();
                    } else {
                        card.usePoints(pence);
                        return;
                    }
                }
            }
            throw new OwnerNotRegisteredException();
        }
    }

    /**
     * Gets the number of loyalty card customers.
     *
     * @return the number of loyalty card customers
     */
    @Override
    public int getNumberOfCustomers() {

        return registeredOwners.size();
    }

    /**
     * Gets the total count of all points on all loyalty cards in the system.
     *
     * @return the total count of all points on all loyalty cards in the system
     */
    @Override
    public int getTotalNumberOfPoints() {

        int points = 0;

        for (ILoyaltyCard card: cards) {
            points += card.getNumberOfPoints();
        }
        return points;
    }

    /**
     * Gets the number of points on the loyalty card for the specified owner.
     *
     * @param ownerEmail the loyalty card owner's email
     * @return the number of points on the specified owner's loyalty card
     * @throws OwnerNotRegisteredException if the given owner's email is not registered
     */
    @Override
    public int getNumberOfPoints(String ownerEmail) throws OwnerNotRegisteredException {

        for (ILoyaltyCard card: cards) {

            if (card.getOwner().getEmail().equals(ownerEmail)) {
                return card.getNumberOfPoints();
            }
        }
        throw new OwnerNotRegisteredException();
    }

    /**
     * Gets the total number of times that the owner's loyalty card has been used (for money and points purchases).
     *
     * @param ownerEmail the loyalty card owner's email
     * @return the total number of times that the owner's loyalty card has been used
     * @throws OwnerNotRegisteredException if the given owner's email is not registered
     */
    @Override
    public int getNumberOfUses(String ownerEmail) throws OwnerNotRegisteredException {

        for (ILoyaltyCard card: cards) {

            if (card.getOwner().getEmail().equals(ownerEmail)) {
                return card.getNumberOfUses();
            }
        }
        throw new OwnerNotRegisteredException();
    }

    /**
     * Gets the owner who has used their loyalty card the most. The behaviour is undefined if there is not a single most used card.
     * If there are two cards that have been used the same amount, the first one in the list of registered cards will be returned.
     *
     * @return the owner who has used their loyalty card the most
     * @throws OwnerNotRegisteredException if no owners have been registered
     */
    @Override
    public ILoyaltyCardOwner getMostUsed() throws OwnerNotRegisteredException {

        if (registeredOwners.size() < 1) {
            throw new OwnerNotRegisteredException();
        }

        int max = 0;
        ILoyaltyCardOwner mostUsed = null;

        for (ILoyaltyCard card: cards) {

            if (card.getNumberOfUses() > max) {
                max = card.getNumberOfUses();
                mostUsed = card.getOwner();
            }
        }
        return mostUsed;
    }

}
