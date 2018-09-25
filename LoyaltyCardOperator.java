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

    @Override
    public void registerOwner(ILoyaltyCardOwner loyaltyCardOwner) throws OwnerAlreadyRegisteredException {

        if (registeredOwners.contains(loyaltyCardOwner.getEmail())) {
            throw new OwnerAlreadyRegisteredException();
        } else {
            registeredOwners.add(loyaltyCardOwner.getEmail());
            cards.add(Factory.getInstance().makeLoyaltyCard(loyaltyCardOwner));
        }
    }

    @Override
    public void unregisterOwner(ILoyaltyCardOwner loyaltyCardOwner) throws OwnerNotRegisteredException {

        if (!registeredOwners.contains(loyaltyCardOwner.getEmail())) {
            throw new OwnerNotRegisteredException();
        } else {
            registeredOwners.remove(loyaltyCardOwner.getEmail());
        }
    }

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
                    }

                }
            }
            throw new OwnerNotRegisteredException();
        }
    }

    @Override
    public int getNumberOfCustomers() {

        return registeredOwners.size();
    }

    @Override
    public int getTotalNumberOfPoints() {

        int points = 0;
        for (ILoyaltyCard card: cards) {
            points += card.getNumberOfPoints();
        }
        return points;
    }

    @Override
    public int getNumberOfPoints(String ownerEmail) throws OwnerNotRegisteredException {

        for (ILoyaltyCard card: cards) {
            if (card.getOwner().getEmail().equals(ownerEmail)) {
                return card.getNumberOfPoints();
            }
        }
        throw new OwnerNotRegisteredException();
    }

    @Override
    public int getNumberOfUses(String ownerEmail) throws OwnerNotRegisteredException {

        for (ILoyaltyCard card: cards) {
            if (card.getOwner().getEmail().equals(ownerEmail)) {
                return card.getNumberOfUses();
            }
        }
        throw new OwnerNotRegisteredException();
    }

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
            } else if (card.getNumberOfUses() == max) {
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }
        }
        return mostUsed;
    }

}
