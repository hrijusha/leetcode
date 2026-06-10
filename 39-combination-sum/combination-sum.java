class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        findCombinations(0, candidates, target, result, new ArrayList<>());
        return result;
    }

    private void findCombinations(int index, int[] candidates, int target,
            List<List<Integer>> result,
            List<Integer> current) {

        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        if (index == candidates.length) {
            return;
        }

        if (candidates[index] <= target) {
            current.add(candidates[index]);
            findCombinations(index, candidates,
                    target - candidates[index],
                    result, current);
            current.remove(current.size() - 1);
        }
        findCombinations(index + 1, candidates, target, result, current);
    }
}