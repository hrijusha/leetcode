class Solution {
    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        boolean[] check = new boolean[n];
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backTrack(nums, check, result, path);
        return result;
    }

    private void backTrack(int[] nums, boolean[] check, List<List<Integer>> result, List<Integer> path) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (check[i]) {
                continue;
            }
            check[i] = true;
            path.add(nums[i]);
            backTrack(nums, check, result, path);

            path.remove(path.size() - 1);
            check[i] = false;
        }
    }
}