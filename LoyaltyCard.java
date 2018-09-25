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

    public LoyaltyCard(ILoyaltyCardOwner owner) {
        this.owner = owner;
    }

    @Override
    public ILoyaltyCardOwner getOwner() {
        return owner;
    }

    @Override
    public int getNumberOfUses() {
        return numberOfUses;
    }

    @Override
    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    @Override
    public void addPoints(int points) {
        if (points > 0) {
            numberOfUses++;
            numberOfPoints += points;
        }
    }

    @Override
    public void usePoints(int points) throws InsufficientPointsException {
        if (points < 100) {
            throw new InsufficientPointsException();
        } else {
            numberOfUses++;
            numberOfPoints -= 100;
        }
    }

}
