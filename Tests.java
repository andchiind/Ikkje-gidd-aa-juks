package test;

import common.InsufficientPointsException;

import common.OwnerAlreadyRegisteredException;
import impl.Factory;
import interfaces.IFactory;
import interfaces.ILoyaltyCard;
import interfaces.ILoyaltyCardOperator;

//import org.junit.Test;

import common.AbstractFactoryClient;
import interfaces.ILoyaltyCardOwner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

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
    /*@BeforeAll
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

    /*@Test(expected=InsufficientPointsException.class)
    public void loyaltyCardInsufficientPointsException() {
        *//*ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("name@name.com", "Name");
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(loyaltyCardOwner);*//*

        loyaltyCard.addPoints(1);
        loyaltyCard.usePoints(2);
    }*/

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
    public void loyaltyCardUseOnce() {
        /*ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("name@name.com", "Name");
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(loyaltyCardOwner);*/

        loyaltyCard.addPoints(1);

        assertEquals(1, loyaltyCard.getNumberOfUses());
    }

    @Test
    public void loyaltyCardUseThrice() {
        /*ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("name@name.com", "Name");
        ILoyaltyCard loyaltyCard = getFactory().makeLoyaltyCard(loyaltyCardOwner);*/

        loyaltyCard.addPoints(1);
        loyaltyCard.addPoints(5);
        loyaltyCard.addPoints(2);

        assertEquals(3, loyaltyCard.getNumberOfUses());
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

}
