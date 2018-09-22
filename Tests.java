package test;

import common.OwnerAlreadyRegisteredException;
import impl.Factory;
import interfaces.IFactory;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOperator;
import org.junit.Test;

import common.AbstractFactoryClient;
import interfaces.ILoyaltyCardOwner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This is a JUnit test class for the loyalty card ADT.
 *
 */
public class Tests extends AbstractFactoryClient {

    private IFactory factory;
    private ILoyaltyCard loyaltyCard;
    private ILoyaltyCardOwner loyaltyCardOwner;
    private ILoyaltyCardOperator loyaltyCardOperator;

    //CLASS OR JUST BEFORE?????????/
    @BeforeClass
    public void setup() {
        factory = Factory.getInstance();
    }

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
        /*ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("jon@jon.com", "Jon");*/
        assertFalse(loyaltyCardOwner == null);
    }

    @Test(expected=InsufficientPointsException.class)
    public void loyaltyCardInsufficientPointsException() {
        /*ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("name@name.com", "Name");
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(loyaltyCardOwner);*/

        loyaltyCard.addPoints(1);
        loyaltyCard.usePoints(2);
    }

    @Test
    public void loyaltyCardCreationNonNull() {
        /*ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("name@name.com", "Name");
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(loyaltyCardOwner);*/
        assertFalse(loyaltyCard == null);
    }

    @Test
    public void loyaltyCardAddNegativePoints() {
        /*ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("name@name.com", "Name");
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(loyaltyCardOwner);*/

        loyaltyCard.addPoints(-1);

        assertTrue(loyaltyCard.getNumberOfPoints() == 0);

    }

    @Test
    public void loyaltyCardUseOnce() {
        /*ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("name@name.com", "Name");
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(loyaltyCardOwner);*/

        loyaltyCard.addPoints(1);

        assertTrue(loyaltyCard.getNumberOfUses() == 1);
    }

    @Test
    public void registerOneOwner() {
        try {
            loyaltyCardOperator.registerOwner(loyaltyCardOwner);

            assertTrue(loyaltyCardOperator.getNumberOfCustomers() == 1);

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

            assertTrue(loyaltyCardOperator.getNumberOfCustomers() == 3);

        } catch (OwnerAlreadyRegisteredException e) {
            e.printStackTrace();
        }
    }

}
