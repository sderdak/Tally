package sgd.tally.auction.usage;

import sgd.tally.bidding.common.Auction;
import sgd.tally.bidding.tally.TallyAuction;
import sgd.tally.bidding.tally.WinningOffer;
import sgd.tally.bidding.tally.Offer;

public class HowToMakeAnAuctionEngine {
    /**
     * How to make an auction engine, see the test usages how to add offers and conclude the auction.
     * @return the engine
     */
    public Auction<Offer, WinningOffer> makeAuction() {
        return TallyAuction.newAuction("Example");
    }
}
