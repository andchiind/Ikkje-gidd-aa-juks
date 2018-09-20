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

    @Override
    public ILoyaltyCardOwner getOwner() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getNumberOfUses() {
        // TODO Auto-generated method stub
        return numberOfUses;
    }

    @Override
    public int getNumberOfPoints() {
        // TODO Auto-generated method stub
        return numberOfPoints;
    }

    @Override
    public void addPoints(int points) {
        // TODO Auto-generated method stub
        numberOfPoints += points;
    }

    @Override
    public void usePoints(int points) throws InsufficientPointsException {
        // TODO Auto-generated method stub
    }

}
