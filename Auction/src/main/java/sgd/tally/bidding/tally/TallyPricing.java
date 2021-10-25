package sgd.tally.bidding.tally;

import sgd.tally.bidding.common.TopTwo;

class TallyPricing {
    public static WinningOffer sold(TopTwo<TallyBid> topTwo) {
        var first = topTwo.first();
        if (first == null) {
            return null;
        }
        var second = topTwo.second();
        final int effectiveBid;
        if (second == null) {
            effectiveBid = first.offer().startingBid();
        } else {
            effectiveBid = first.overcall(second);
        }
        return new WinningOffer(first.offer(), effectiveBid);
    }

}
