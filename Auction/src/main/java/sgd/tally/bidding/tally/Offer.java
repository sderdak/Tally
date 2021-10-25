package sgd.tally.bidding.tally;

import static java.lang.String.format;

/**
 * Record to encapsulate an offer.
 * The client code instantiates this using data from their API.
 * The amounts are meant to be the same currency full amount.
 * The client code decides the interpretation of the amounts,
 * what major or minor currency they represent, for example  $ or ¢,
 * or 元.
 *
 * Invalid offers fail to instantiate with IllegalArgumentException.
 */
public record Offer(String bidder, int startingBid, int maxBid, int increment) {
    public Offer {
        if (bidder == null
                || startingBid < 0
                || increment <= 0
                || maxBid < startingBid)
            throw new IllegalArgumentException(format("bidder:%s startingBid:%d maxBid:%d increment:%d",
                                                      bidder,
                                                      startingBid,
                                                      maxBid,
                                                      increment));

    }
}
