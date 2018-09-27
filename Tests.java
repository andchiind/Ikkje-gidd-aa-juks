package test;

import common.InsufficientPointsException;
import common.AbstractFactoryClient;
import common.InsufficientPointsException;
import common.OwnerAlreadyRegisteredException;
import common.OwnerNotRegisteredException;

import impl.Factory;
import impl.LoyaltyCardOwner;
import interfaces.IFactory;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOperator;

//import org.junit.Test;

import interfaces.ILoyaltyCardOwner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * This is a JUnit test class for the loyalty card ADT.
 *
 */
public class Tests extends AbstractFactoryClient {

    private IFactory factory = Factory.getInstance();
    private ILoyaltyCard loyaltyCard;
    private ILoyaltyCardOwner loyaltyCardOwner;
    private ILoyaltyCardOperator loyaltyCardOperator;

    //CLASS OR JUST BEFORE?????????/
    /*@BeforeClass
    public static void setup() {
        factory = Factory.getInstance();
    }*/

    @BeforeEach
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
        /*ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("jon@jon.com", "Jon");*/
        assertNotNull(loyaltyCardOwner);
    }

    /*@Test(expected = InsufficientPointsException.class)
    public void loyaltyCardInsufficientPointsException() {
        *//*ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("name@name.com", "Name");
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(loyaltyCardOwner);
        *//*

        loyaltyCard.addPoints(1);
        loyaltyCard.usePoints(2);
    }*/

    /*@Test(expected = InsufficientPointsException.class)
    public void loyaltyCardUseNegativePointsException() {

        loyaltyCard.usePoints(-1);
    }*/

    @Test
    public void getNameOfOwner() {
        ILoyaltyCardOwner owner1 = factory.makeLoyaltyCardOwner("hello@email.com", "Name");

        assertEquals("Name", owner1.getName());
    }

    @Test
    public void getEmailOfOwner() {
        ILoyaltyCardOwner owner1 = factory.makeLoyaltyCardOwner("hello@email.com", "Name");

        assertEquals("hello@email.com", owner1.getEmail());
    }

    @Test
    public void loyaltyCardCreationNonNull() {
        /*ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("name@name.com", "Name");
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(loyaltyCardOwner);*/
        assertNotNull(loyaltyCard);
    }

    @Test
    public void loyaltyCardAddNegativePoints() {
        /*ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("name@name.com", "Name");
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(loyaltyCardOwner);*/

        loyaltyCard.addPoints(-1);

        assertEquals(0, loyaltyCard.getNumberOfPoints());
    }

    @Test
    public void loyaltyCardAddZeroPoints() {

        loyaltyCard.addPoints(0);

        assertEquals(0, loyaltyCard.getNumberOfPoints());
    }

    @Test
    public void loyaltyCardAddOnce() {
        /*ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("name@name.com", "Name");
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(loyaltyCardOwner);*/

        loyaltyCard.addPoints(1);

        assertEquals(1, loyaltyCard.getNumberOfUses());
    }

    @Test
    public void loyaltyCardAddOnceUseOnce() {
        /*ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("name@name.com", "Name");
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(loyaltyCardOwner);*/
        try {

            loyaltyCard.addPoints(1);
            loyaltyCard.usePoints(1);

            assertEquals(2, loyaltyCard.getNumberOfUses());

            assertEquals(0, loyaltyCard.getNumberOfPoints());

        } catch (InsufficientPointsException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loyaltyCardUseThrice() {
        /*ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("name@name.com", "Name");
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(loyaltyCardOwner);*/

        try {

            loyaltyCard.addPoints(1);
            loyaltyCard.usePoints(1);
            loyaltyCard.addPoints(2);

            assertEquals(3, loyaltyCard.getNumberOfUses());

        } catch (InsufficientPointsException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loyaltyCardAddOnePoint() {
        loyaltyCard.addPoints(1);

        assertEquals(1, loyaltyCard.getNumberOfPoints());
    }

    @Test
    public void loyaltyCardAddMaximumPoint() {
        loyaltyCard.addPoints(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, loyaltyCard.getNumberOfPoints());
    }

    @Test
    public void cardHasTooFewPoints() {

        try {
            loyaltyCard.addPoints(20);

            assertEquals(20, loyaltyCard.getNumberOfPoints());

            loyaltyCard.usePoints(10);

            assertEquals(10, loyaltyCard.getNumberOfPoints());

        } catch (InsufficientPointsException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registerOneOwner() {
        try {
            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            assertEquals(1, loyaltyCardOperator.getNumberOfCustomers());

        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        }
    }

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

    @Test/*(expected = OwnerAlreadyRegisteredException.class)*/
    public void registerAlreadyRegisteredOwner() throws OwnerAlreadyRegisteredException {
        loyaltyCardOperator.registerOwner(loyaltyCardOwner);

        assertEquals(1, loyaltyCardOperator.getNumberOfCustomers());

        try {

            loyaltyCardOperator.registerOwner(loyaltyCardOwner);
            assertFalse(true);
        } catch (OwnerAlreadyRegisteredException e) {
            assertFalse(false);
        }
    }

    /*@Test(expected = OwnerNotRegisteredException.class)
    public void unregisteredUnregisteredOwner() {

        loyaltyCardOperator.unregisterOwner(loyaltyCardOwner);
    }*/

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

    @Test
    public void getTotalPointsFromThreeOwners() {

        try {
            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            //assertEquals(1, loyaltyCardOperator.getNumberOfCustomers());

            ILoyaltyCardOwner owner1 = factory.makeLoyaltyCardOwner("owner1@owner.com", "owner1");

            loyaltyCardOperator.registerOwner(owner1);

            //assertEquals(2, loyaltyCardOperator.getNumberOfCustomers());

            ILoyaltyCardOwner owner2 = factory.makeLoyaltyCardOwner("owner2@owner.com", "owner2");

            loyaltyCardOperator.registerOwner(owner2);

            //assertEquals(3, loyaltyCardOperator.getNumberOfCustomers());

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

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
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

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
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

    @Test
    public void getTotalNumberOfPointsWithNoPointsFromOperator() {

        try {
            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            assertEquals(0, loyaltyCardOperator.getTotalNumberOfPoints());

        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        }
    }

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

    /*@Test (expected = OwnerNotRegisteredException.class)
    public void getMostUsedCardOutOfNoOwners() {
        try {

            assertEquals(null, loyaltyCardOperator.getMostUsed());

        } catch (OwnerNotRegisteredException e) {
            e.printStackTrace();
        }
    }*/

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

    /*@Test (expected = InsufficientPointsException.class)
    public void processPointPurchaseWithTooFewPoints() {
        try {
            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            loyaltyCardOperator.processPointsPurchase(loyaltyCardOwner.getEmail(), 1);
        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        } catch (OwnerNotRegisteredException e) {
            e.printStackTrace();
        }
    }*/

    /*@Test(expected=InsufficientPointsException.class)
    public void cardHasTooFewPoints() {

        loyaltyCard.addPoints(10);

        loyaltyCard.usePoints(20);

    }*/

}
