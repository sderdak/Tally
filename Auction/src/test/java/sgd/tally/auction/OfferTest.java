package sgd.tally.auction;
import org.junit.jupiter.api.Test;
import sgd.tally.bidding.tally.Offer;

import static org.junit.jupiter.api.Assertions.*;


class OfferTest {
    @Test
    void testIllegalOfferArguments() {
        testOffer(null, 1, 10, 1);
        testOffer("Jane", -1, 10, 1);
        testOffer("Jane", 10, 9, 1);
        testOffer("Jane", 10, 9, 0);
        testOffer("Jane", 10, 9, -10);
    }

    void testOffer(String bidder, int startingBid, int maxBid, int increment) {
        try {
            var offer = new Offer(bidder, startingBid, maxBid, increment);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
