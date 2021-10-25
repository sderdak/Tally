package sgd.tally.auction;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;
import sgd.tally.bidding.common.Auction;
import sgd.tally.bidding.tally.Offer;
import sgd.tally.bidding.tally.TallyAuction;
import sgd.tally.bidding.tally.WinningOffer;

import static org.junit.jupiter.api.Assertions.*;

class AuctionTest {

    @Test
    void testFromTally() {
        auction("Skates", "Mason", 85, tallySkateOffers());
        auction("Unicycle", "Alicia", 722, tallyUnicycleOffers());
        auction("Hover Board", "Olivia", 3_001, tallyHoverboardOffers());
    }

    void auction(String name, String expectedWinner, int expectedAmount, List<Offer> offers) {
        Auction<Offer, WinningOffer> auction = TallyAuction.newAuction(name);
        offers.forEach(auction::offer);
        var winbid = auction.conclude();
        var bidder = winbid.offer().bidder();
        var amount = winbid.amount();
        assertEquals(expectedWinner, bidder);
        assertEquals(expectedAmount, amount);
        System.out.printf("%s winner:%s $:%d%n", auction, bidder, amount);
    }


    @Test
    void testAuctionWithNoBids() {
        Auction<Offer, WinningOffer> auction = TallyAuction.newAuction("test");
        var winbid = auction.conclude();
        assertNull(winbid);
    }

    @Test
    void testAuctionWithASingleBid() {
        Auction<Offer, WinningOffer> auction = TallyAuction.newAuction("test");
        boolean isAccepted = auction.offer(new Offer("Alicia", 50, 80, 3));
        assertTrue(isAccepted);
        var winbid = auction.conclude();
        assertEquals(50, winbid.amount());
        boolean isAcceptedAfterClose = auction.offer(new Offer("Jane", 500, 800, 100));
        assertFalse(isAcceptedAfterClose);
        assertEquals(50, winbid.amount());

    }


    List<Offer> tallySkateOffers() {
        return List.of(
                new Offer("Alicia", 50, 80, 3),
                new Offer("Olivia", 60, 82, 2),
                new Offer("Mason", 55, 85, 5)
                      ).shuffle();
    }

    List<Offer> tallyUnicycleOffers() {
        return List.of(
                new Offer("Alicia", 700, 725, 2),
                new Offer("Olivia", 599, 725, 15),
                new Offer("Mason", 625, 725, 8)
                      ).shuffle();
    }

    List<Offer> tallyHoverboardOffers() {
        return List.of(
                new Offer("Alicia", 2_500, 3_000, 500),
                new Offer("Olivia", 2_800, 3_100, 201),
                new Offer("Mason", 2_501, 3_200, 247)
                      ).shuffle();
    }
}