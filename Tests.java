package test;

import impl.Factory;
import interfaces.IFactory;
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

    //CLASS OR JUST BEFORE?????????/
    @BeforeClass
    public void setup() {
        factory = Factory.getInstance();
    }


    /**
     * This checks that the factory was able to call a sensible constructor to get a non-null instance of ILoyaltyCardOwner.
     */
    @Test
    public void loyaltyCardOwnerCreationNonNull() {
        ILoyaltyCardOwner loyaltyCardOwner = getFactory().makeLoyaltyCardOwner("jon@jon.com", "Jon");
        assertFalse(loyaltyCardOwner == null);
    }

}
