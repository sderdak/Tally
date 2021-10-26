package sgd.tally.bidding.tally;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
        var errors = new ArrayList<String>();
        if (bidder == null) {errors.add("bidder is null");}
        if (startingBid == 0) {errors.add("startingBid == 0 but must be greater");}
        if (startingBid < 0) {errors.add("startingBid <0 but must be greater");}
        if (maxBid < startingBid) {errors.add("maxBid < startingBid but must be greater");}
        if (increment <= 0) {errors.add("increment is 0 but must be greater");}
        if (errors.size() > 0) {
            var args = format("[bidder:%s startingBid:%d maxBid:%d increment:%d] invalid: ",
                              bidder,
                              startingBid,
                              maxBid,
                              increment);
            var errmsg = errors.stream().collect(Collectors.joining(", ", "", "."));
            throw new IllegalArgumentException(args + errmsg);
        }
    }


}
