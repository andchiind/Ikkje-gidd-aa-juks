package test;

import common.InsufficientPointsException;
import common.AbstractFactoryClient;
import common.OwnerAlreadyRegisteredException;
import common.OwnerNotRegisteredException;

import impl.Factory;
import interfaces.IFactory;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOwner;
import interfaces.ILoyaltyCardOperator;

import org.junit.Test;
import org.junit.Before;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a JUnit test class for the loyalty card ADT.
 *
 */
public class Tests extends AbstractFactoryClient {

    //This factory is used to instantiate each object which is to be tested
    private IFactory factory = Factory.getInstance();

    private ILoyaltyCard loyaltyCard;
    private ILoyaltyCardOwner loyaltyCardOwner;
    private ILoyaltyCardOperator loyaltyCardOperator;

    /**
     * This method is run before each test. It creates an object of each class which is tested, which is useful as
     * many tests only involve one object of a class.
     */
    @Before
    public void setupObject() {
        loyaltyCardOperator = factory.makeLoyaltyCardOperator();
        loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("name@name.com", "Name");
        loyaltyCard = getFactory().makeLoyaltyCard(loyaltyCardOwner);
    }

    /**
     * This checks that the factory was able to call a sensible constructor to get a non-null instance of ILoyaltyCardOwner.
     */
    @Test
    public void loyaltyCardOwnerCreationNonNull() {

        assertNotNull(loyaltyCardOwner);
    }

    /**
     * This checks that the InsufficientPointsException is thrown when trying to use more points than the card has.
     * @throws InsufficientPointsException when trying to use more points than are available on the card
     */
    @Test(expected = InsufficientPointsException.class)
    public void loyaltyCardInsufficientPointsException() throws InsufficientPointsException {

        loyaltyCard.addPoints(1);
        loyaltyCard.usePoints(2);
    }

    /**
     * This makes sure that no points are added or removed from a card when trying to use negative points.
     */
    @Test
    public void loyaltyCardUseNegativePoints() {

        try {
            loyaltyCard.usePoints(-1);

            assertEquals(0, loyaltyCard.getNumberOfUses());

        } catch (InsufficientPointsException e) {
            e.printStackTrace();
        }
    }

    /**
     * This makes sure that the getName() method in the LoyaltyCardOwner class successfully returns the name attribute
     * of the LoyaltyCardOwner object.
     */
    @Test
    public void getNameOfOwner() {

        ILoyaltyCardOwner owner1 = factory.makeLoyaltyCardOwner("hello@email.com", "Name");

        assertEquals("Name", owner1.getName());
    }

    /**
     * This makes sure that the getEmail() method in the LoyaltyCardOwner class successfully returns the email attribute
     * of the LoyaltyCardOwner object.
     */
    @Test
    public void getEmailOfOwner() {

        ILoyaltyCardOwner owner1 = factory.makeLoyaltyCardOwner("hello@email.com", "Name");

        assertEquals("hello@email.com", owner1.getEmail());
    }

    /**
     * This checks that the factory was able to call a sensible constructor to get a non-null instance of ILoyaltyCard.
     */
    @Test
    public void loyaltyCardCreationNonNull() {

        assertNotNull(loyaltyCard);
    }

    /**
     * This tests that trying to add negative points to a card does not change the number of points on the card.
     */
    @Test
    public void loyaltyCardAddNegativePoints() {

        loyaltyCard.addPoints(-1);

        assertEquals(0, loyaltyCard.getNumberOfPoints());
    }

    /**
     * This tests that trying to add zero points to a card does not change the number of points on the card and does not
     * throw an exception.
     */
    @Test
    public void loyaltyCardAddZeroPoints() {

        loyaltyCard.addPoints(0);

        assertEquals(0, loyaltyCard.getNumberOfPoints());
    }

    /**
     * This checks that the getNumberOfUses() method registers that the card has been used once to add points.
     */
    @Test
    public void loyaltyCardAddPointsOnce() {

        loyaltyCard.addPoints(1);

        assertEquals(1, loyaltyCard.getNumberOfUses());
    }

    /**
     * This test makes sure that the getNumberOfPoints() method registers that there has been added one point to the card.
     */
    @Test
    public void loyaltyCardAddOnePoint() {

        loyaltyCard.addPoints(1);

        assertEquals(1, loyaltyCard.getNumberOfPoints());
    }

    /**
     * This checks that both the addPoints() and usePoints() methods in the LoyaltyCard class update the numberOfUses
     * attribute. It also makes sure that the point that is added is also successfully removed.
     */
    @Test
    public void loyaltyCardAddOnceUseOnce() {

        try {

            loyaltyCard.addPoints(1);
            loyaltyCard.usePoints(1);

            assertEquals(2, loyaltyCard.getNumberOfUses());

            assertEquals(0, loyaltyCard.getNumberOfPoints());

        } catch (InsufficientPointsException e) {
            e.printStackTrace();
        }
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    /**
     * This test not only checks that a point can be successfully added and used, but also that you can continue to add points
     * afterwards, and that each use updates the numberOfUses attribute.
     */
    @Test
    public void loyaltyCardUseThrice() {

        try {

            loyaltyCard.addPoints(1);
            loyaltyCard.usePoints(1);
            loyaltyCard.addPoints(2);

            assertEquals(3, loyaltyCard.getNumberOfUses());
            assertEquals(2, loyaltyCard.getNumberOfPoints());

        } catch (InsufficientPointsException e) {
            e.printStackTrace();
        }
    }

    /**
     * This ensures that we can add the maximum number of points.
     */
    @Test
    public void loyaltyCardAddMaximumPoint() {

        loyaltyCard.addPoints(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, loyaltyCard.getNumberOfPoints());
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    /**
     * This tests that we can add and remove a random number of points.
     */
    @Test
    public void cardUsePoints() {

        try {

            loyaltyCard.addPoints(20);

            assertEquals(20, loyaltyCard.getNumberOfPoints());

            loyaltyCard.usePoints(10);

            assertEquals(10, loyaltyCard.getNumberOfPoints());

        } catch (InsufficientPointsException e) {
            e.printStackTrace();
        }
    }

    /**
     * This checks that the number of points on a card does not change when we add zero points, and that not exception is
     * thrown.
     */
    @Test
    public void cardUseZeroPoints() {

        try {

            assertEquals(0, loyaltyCard.getNumberOfPoints());

            loyaltyCard.usePoints(0);

            assertEquals(0, loyaltyCard.getNumberOfPoints());

        } catch (InsufficientPointsException e) {
            e.printStackTrace();
        }
    }

    /**
     * This test checks that the getNumberOfCustomers() method returns the correct value when registering one owner.
     */
    @Test
    public void registerOneOwner() {

        try {

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            assertEquals(1, loyaltyCardOperator.getNumberOfCustomers());

        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        }
    }

    /**
     * This test checks that the getNumberOfCustomers() method returns the correct value when registering three owners.
     */
    @Test
    public void registerThreeOwners() {

        try {

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);
            loyaltyCardOperator.registerOwner(factory.makeLoyaltyCardOwner("owner1@owner.com", "owner1"));
            loyaltyCardOperator.registerOwner(factory.makeLoyaltyCardOwner("owner2@owner.com", "owner2"));

            assertEquals(3, loyaltyCardOperator.getNumberOfCustomers());

        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        }
    }

    /**
     * This tests that the LoyaltyCardOperator object successfully registers, unregisters and re-registers a loyaltyCardOwner
     */
    @Test
    public void unregisterAndReRegisterOwner() {

        try {

            assertEquals(0, loyaltyCardOperator.getNumberOfCustomers());

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            assertEquals(1, loyaltyCardOperator.getNumberOfCustomers());

            loyaltyCardOperator.unregisterOwner(loyaltyCardOwner);

            assertEquals(0, loyaltyCardOperator.getNumberOfCustomers());

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            assertEquals(1, loyaltyCardOperator.getNumberOfCustomers());

            loyaltyCardOperator.unregisterOwner(loyaltyCardOwner);

            assertEquals(0, loyaltyCardOperator.getNumberOfCustomers());

        } catch (OwnerNotRegisteredException e) {
            e.printStackTrace();
        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        }
    }

    /**
     * This makes sure that the OwnerAlreadyRegisteredException is thrown when trying to register an owner twice.
     * @throws OwnerAlreadyRegisteredException when registering an already owned loyaltyCardOwner
     */
    @Test (expected = OwnerAlreadyRegisteredException.class)
    public void registerAlreadyRegisteredOwner() throws OwnerAlreadyRegisteredException {

        loyaltyCardOperator.registerOwner(loyaltyCardOwner);

        assertEquals(1, loyaltyCardOperator.getNumberOfCustomers());

        loyaltyCardOperator.registerOwner(loyaltyCardOwner);
    }

    /**
     * This makes sure that the OwnerNotRegisteredException is thrown when trying to unregister an owner that is not
     * already registered.
     * @throws OwnerNotRegisteredException when attempting to unregister an owner who has not been registered
     */
    @Test (expected = OwnerNotRegisteredException.class)
    public void unregisteredNonRegisteredOwner() throws OwnerNotRegisteredException {

        loyaltyCardOperator.unregisterOwner(loyaltyCardOwner);
    }

    /**
     * This tests that the getNumberOfCustomers() method returns the correct value after adding and after removing an owner.
     */
    @Test
    public void unregisterOneOwner() {

        try {

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            assertEquals(1, loyaltyCardOperator.getNumberOfCustomers());

            loyaltyCardOperator.unregisterOwner(loyaltyCardOwner);

            assertEquals(0, loyaltyCardOperator.getNumberOfCustomers());

        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        } catch (OwnerNotRegisteredException e) {
            e.printStackTrace();
        }
    }

    /**
     * This tests that the getNumberOfCustomers() method returns the correct value after adding and after removing three owners.
     */
    @Test
    public void unregisterThreeOwners() {

        try {

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            assertEquals(1, loyaltyCardOperator.getNumberOfCustomers());

            ILoyaltyCardOwner owner1 = factory.makeLoyaltyCardOwner("owner1@owner.com", "owner1");

            loyaltyCardOperator.registerOwner(owner1);

            assertEquals(2, loyaltyCardOperator.getNumberOfCustomers());

            ILoyaltyCardOwner owner2 = factory.makeLoyaltyCardOwner("owner2@owner.com", "owner2");

            loyaltyCardOperator.registerOwner(owner2);

            assertEquals(3, loyaltyCardOperator.getNumberOfCustomers());

            loyaltyCardOperator.unregisterOwner(loyaltyCardOwner);

            assertEquals(2, loyaltyCardOperator.getNumberOfCustomers());

            loyaltyCardOperator.unregisterOwner(owner1);

            assertEquals(1, loyaltyCardOperator.getNumberOfCustomers());

            loyaltyCardOperator.unregisterOwner(owner2);

            assertEquals(0, loyaltyCardOperator.getNumberOfCustomers());

        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        } catch (OwnerNotRegisteredException e) {
            e.printStackTrace();
        }
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    /**
     * This ensures that the getNumberOfPoints() method in the loyaltyCardOperator class returns the correct value for a
     * random number of points.
     */
    @Test
    public void getOwnerPoints() {

        try {

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            loyaltyCardOperator.processMoneyPurchase(loyaltyCardOwner.getEmail(), 1200);

            assertEquals(12, loyaltyCardOperator.getNumberOfPoints(loyaltyCardOwner.getEmail()));

        } catch (OwnerNotRegisteredException e) {
            e.printStackTrace();
        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        }
    }

    /**
     * This ensures that the getNumberOfPoints() method in the loyaltyCardOperator class returns the correct value when
     * the given owner has no points.
     */
    @Test
    public void getOwnerPointsWithZeroPoints() {

        try {

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            assertEquals(0, loyaltyCardOperator.getNumberOfPoints(loyaltyCardOwner.getEmail()));

        } catch (OwnerNotRegisteredException e) {
            e.printStackTrace();
        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        }
    }

    /**
     * This ensures that the getNumberOfPoints() method in the loyaltyCardOperator class returns the correct value when
     * the given owner has the maximum number of points that can be added in a single transaction.
     */
    @Test
    public void getOwnerPointsWithMaxPoints() {

        try {

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            loyaltyCardOperator.processMoneyPurchase(loyaltyCardOwner.getEmail(), Integer.MAX_VALUE);

            assertEquals(Integer.MAX_VALUE / 100, loyaltyCardOperator.getNumberOfPoints(loyaltyCardOwner.getEmail()));

        } catch (OwnerNotRegisteredException e) {
            e.printStackTrace();
        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        }
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    /**
     * This test checks that the getTotalNumberOfPoints() method in the loyaltyCardOperator class works correctly when
     * aggregating the total points of three owners with random points.
     */
    @Test
    public void getTotalPointsFromThreeOwners() {

        try {

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            ILoyaltyCardOwner owner1 = factory.makeLoyaltyCardOwner("owner1@owner.com", "owner1");

            loyaltyCardOperator.registerOwner(owner1);

            ILoyaltyCardOwner owner2 = factory.makeLoyaltyCardOwner("owner2@owner.com", "owner2");

            loyaltyCardOperator.registerOwner(owner2);

            assertEquals(0, loyaltyCardOperator.getTotalNumberOfPoints());

            loyaltyCardOperator.processMoneyPurchase(loyaltyCardOwner.getEmail(), 200);

            assertEquals(2, loyaltyCardOperator.getTotalNumberOfPoints());

            loyaltyCardOperator.processMoneyPurchase(owner1.getEmail(), 500);

            assertEquals(7, loyaltyCardOperator.getTotalNumberOfPoints());

            loyaltyCardOperator.processMoneyPurchase(owner1.getEmail(), 100);

            assertEquals(8, loyaltyCardOperator.getTotalNumberOfPoints());

        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        } catch (OwnerNotRegisteredException e) {
            e.printStackTrace();
        }
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    /**
     * This checks that the getNumberOfUses() method in the loyaltyCardOperator class returns the correct value after
     * using the processMoneyPurchase() method three times.
     */
    @Test
    public void getNumberOfCardUsesFromOperator() {

        try {

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            loyaltyCardOperator.processMoneyPurchase(loyaltyCardOwner.getEmail(), 200);
            loyaltyCardOperator.processMoneyPurchase(loyaltyCardOwner.getEmail(), 200);
            loyaltyCardOperator.processMoneyPurchase(loyaltyCardOwner.getEmail(), 200);

            assertEquals(3, loyaltyCardOperator.getNumberOfUses(loyaltyCardOwner.getEmail()));

        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        } catch (OwnerNotRegisteredException e) {
            e.printStackTrace();
        }
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    /**
     * This tests that the getNumberOfUses() method in the loyaltyCardOperator class returns zero when a registered card
     * has not bee used.
     */
    @Test
    public void getNumberOfCardUsesWithUnusedCardFromOperator() {

        try {

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            assertEquals(0, loyaltyCardOperator.getNumberOfUses(loyaltyCardOwner.getEmail()));

        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        } catch (OwnerNotRegisteredException e) {
            e.printStackTrace();
        }
    }

    /**
     * This tests that the getTotalNumberOfPoints() method in the loyaltyCardOperator class returns zero when no cards
     * have been used.
     */
    @Test
    public void getTotalNumberOfPointsWithNoPointsFromOperator() {

        try {

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            assertEquals(0, loyaltyCardOperator.getTotalNumberOfPoints());

        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        }
    }

    /**
     * This tests that the getTotalNumberOfPoints() method in the loyaltyCardOperator class returns the correct value
     * when only one card has been registered and used.
     */
    @Test
    public void getTotalNumberOfPointsFromOneUser() {

        try {

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            loyaltyCardOperator.processMoneyPurchase(loyaltyCardOwner.getEmail(), 100);

            loyaltyCardOperator.processMoneyPurchase(loyaltyCardOwner.getEmail(), 300);

            loyaltyCardOperator.processMoneyPurchase(loyaltyCardOwner.getEmail(), 20);

            assertEquals(4, loyaltyCardOperator.getTotalNumberOfPoints());

        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        } catch (OwnerNotRegisteredException e) {
            e.printStackTrace();
        }
    }

    /**
     * This tests the getMostUsed() method in the loyaltyCardOperator class when three owners have been registered.
     */
    @Test
    public void getMostUsedCardOutOfThreeOwners() {

        try {

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            ILoyaltyCardOwner owner1 = factory.makeLoyaltyCardOwner("owner1@owner.com", "owner1");

            loyaltyCardOperator.registerOwner(owner1);

            ILoyaltyCardOwner owner2 = factory.makeLoyaltyCardOwner("owner2@owner.com", "owner2");

            loyaltyCardOperator.registerOwner(owner2);

            loyaltyCardOperator.processMoneyPurchase(loyaltyCardOwner.getEmail(), 200);
            loyaltyCardOperator.processMoneyPurchase(loyaltyCardOwner.getEmail(), 200);
            loyaltyCardOperator.processMoneyPurchase(loyaltyCardOwner.getEmail(), 200);

            loyaltyCardOperator.processMoneyPurchase(owner1.getEmail(), 100);

            assertEquals(loyaltyCardOwner, loyaltyCardOperator.getMostUsed());

        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        } catch (OwnerNotRegisteredException e) {
            e.printStackTrace();
        }
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    /**
     * This ensures that the OwnerNotRegisteredException is thrown when using the getMostUsed() method in the
     * loyaltyCardOperator class, when no owners have been registered.
     * @throws OwnerNotRegisteredException when no owner has been registered
     */
    @Test (expected = OwnerNotRegisteredException.class)
    public void getMostUsedCardOutOfNoOwners()  throws OwnerNotRegisteredException {

        assertNull(loyaltyCardOperator.getMostUsed());

    }

    /**
     * This tests that the processPointPurchase() method in the loyaltyCardOperator class correctly uses one point, which
     * is checked with the getNumberOfPoints() method.
     */
    @Test
    public void processPointPurchaseForOnePoint() {

        try {

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            loyaltyCardOperator.processMoneyPurchase(loyaltyCardOwner.getEmail(), 100);

            assertEquals(1, loyaltyCardOperator.getNumberOfPoints(loyaltyCardOwner.getEmail()));

            loyaltyCardOperator.processPointsPurchase(loyaltyCardOwner.getEmail(), 1);

            assertEquals(0, loyaltyCardOperator.getNumberOfPoints(loyaltyCardOwner.getEmail()));

        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        } catch (InsufficientPointsException e) {
            e.printStackTrace();
        } catch (OwnerNotRegisteredException e) {
            e.printStackTrace();
        }
    }

    /**
     * This tests that the processPointPurchase() method in the loyaltyCardOperator class correctly uses the maximum number
     * of points possible, which is checked with the getNumberOfPoints() method.
     */
    @Test
    public void processPointPurchaseForMaximumPence() {

        try {

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            loyaltyCardOperator.processMoneyPurchase(loyaltyCardOwner.getEmail(), Integer.MAX_VALUE);

            assertEquals(Integer.MAX_VALUE / 100, loyaltyCardOperator.getNumberOfPoints(loyaltyCardOwner.getEmail()));

            loyaltyCardOperator.processPointsPurchase(loyaltyCardOwner.getEmail(), Integer.MAX_VALUE / 100);

            assertEquals(0, loyaltyCardOperator.getNumberOfPoints(loyaltyCardOwner.getEmail()));

        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        } catch (InsufficientPointsException e) {
            e.printStackTrace();
        } catch (OwnerNotRegisteredException e) {
            e.printStackTrace();
        }
    }

    /**
     * This ensures that a NullPointerException is thrown when creating a LoyaltyCardOwner with null attributes.
     */
    @Test (expected = NullPointerException.class)
    public void createOwnerWithNullAttributes() {

        ILoyaltyCardOwner owner1 = factory.makeLoyaltyCardOwner(null, null);
    }

    /**
     * This tests that a NullPointerException is thrown when attempting to register using a null value.
     */
    @Test (expected = NullPointerException.class)
    public void registerNullOwner() {

        try {

            loyaltyCardOperator.registerOwner(null);

        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        }
    }

    /**
     * This tests that a NullPointerException is thrown when attempting to unregister using a null value.
     */
    @Test (expected = NullPointerException.class)
    public void unregisterNullOwner() {

        try {

            loyaltyCardOperator.unregisterOwner(null);

        } catch (OwnerNotRegisteredException e) {
            e.printStackTrace();
        }
    }

    /**
     * This ensures that a NullPointerException is thrown when creating a LoyaltyCard with null attributes.
     */
    @Test (expected = NullPointerException.class)
    public void createLoyaltyCardWithNullAttribute() {

        ILoyaltyCard card1 = factory.makeLoyaltyCard(null);
    }

    /**
     * This tests that an InsufficientPointsException is thrown when trying to make a purchase with more points than the card has
     * with the processPointsPurchase() method in the LoyaltyCardOperator class.
     * @throws InsufficientPointsException when trying to use more points than the card has
     */
    @Test (expected = InsufficientPointsException.class)
    public void processPointPurchaseWithTooFewPoints() throws InsufficientPointsException {

        try {

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            loyaltyCardOperator.processPointsPurchase(loyaltyCardOwner.getEmail(), 1);

        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        } catch (OwnerNotRegisteredException e) {
            e.printStackTrace();
        }
    }

    /**
     * This tests that an InsufficientPointsException is thrown when trying to make use more points than a card has.
     * @throws InsufficientPointsException when trying to use more points than the card has
     */
    @Test(expected = InsufficientPointsException.class)
    public void cardHasTooFewPoints() throws InsufficientPointsException {

        loyaltyCard.addPoints(10);

        loyaltyCard.usePoints(20);
    }

}
