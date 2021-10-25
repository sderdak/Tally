package sgd.tally.bidding.tally;

import sgd.tally.bidding.common.Auction;
import sgd.tally.bidding.common.Engine;
import sgd.tally.bidding.common.TopTwoByComparator;

/**
 * Assembles the auction engine with tally-auction specific configuration.
 */
final public class TallyAuction {
    public static Auction<Offer, WinningOffer> newAuction(final String name) {
        return new Engine<>(name,
                            TallyBid::new,
                            new TopTwoByComparator<>(TallyBid::compareByFinalBid),
                            TallyPricing::sold);
    }
}
