class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minPrice = prices[0];
        for (int price : prices) {
            minPrice = Math.min(price, minPrice);
            int profit = price-minPrice;
            maxProfit = Math.max(profit, maxProfit);
        }
        return maxProfit;

    }
}