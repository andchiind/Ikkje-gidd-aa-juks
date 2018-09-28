package impl;

import common.InsufficientPointsException;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOwner;

/**
 * This class represents a Loyalty Card, recording information relating to an owners use of the card.
 *
 */
public class LoyaltyCard implements ILoyaltyCard {

    private int numberOfUses;
    private int numberOfPoints;
    private ILoyaltyCardOwner owner;

    /**
     * Constructor for creating a loyaltyCard, which requires assigning a loyaltyCardOwner
     * @param owner of the card
     */
    public LoyaltyCard(ILoyaltyCardOwner owner) throws NullPointerException {

        if (owner == null) {
            throw new NullPointerException();
        }

        this.owner = owner;
    }

    /**
     * Returns the owner of this loyalty card.
     * @return the owner of this loyalty card
     */
    @Override
    public ILoyaltyCardOwner getOwner() {
        return owner;
    }

    /**
     * Returns the number of times this card has been used (i.e. the number of times addPoints and usePoints have been called).
     * @return the number of times the card was used
     */
    @Override
    public int getNumberOfUses() {
        return numberOfUses;
    }

    /**
     * Returns the number points available on the card.
     * @return the number of points on the card
     */
    @Override
    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    /**
     * Adds a number of points to the loyalty card.
     * @param points the number of points to add
     */
    @Override
    public void addPoints(int points) {
        if (points > 0) {
            numberOfUses++;
            numberOfPoints += points;
        }
    }

    /**
     * Use (deduct) a number of points from the loyalty card.
     * @param points the number of points to use (deduct) from this card
     * @throws InsufficientPointsException
     */
    @Override
    public void usePoints(int points) throws InsufficientPointsException {
        if (points < 0) {
            return;
        }
        if (points > numberOfPoints) {
            throw new InsufficientPointsException();
        } else {
            numberOfUses++;
            numberOfPoints -= points;
        }
    }
}
