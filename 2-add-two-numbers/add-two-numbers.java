import java.math.BigInteger;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger sum = getSum(l1).add(getSum(l2));
        return createList(sum);
    }

    private BigInteger getSum(ListNode l) {
        BigInteger sum = BigInteger.ZERO;
        BigInteger place = BigInteger.ONE;

        while (l != null) {
            sum = sum.add(
                BigInteger.valueOf(l.val).multiply(place)
            );

            place = place.multiply(BigInteger.TEN);
            l = l.next;
        }

        return sum;
    }

    private ListNode createList(BigInteger num) {
        if (num.equals(BigInteger.ZERO)) {
            return new ListNode(0);
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (num.compareTo(BigInteger.ZERO) > 0) {
            int digit = num.mod(BigInteger.TEN).intValue();

            current.next = new ListNode(digit);
            current = current.next;

            num = num.divide(BigInteger.TEN);
        }

        return dummy.next;
    }
}