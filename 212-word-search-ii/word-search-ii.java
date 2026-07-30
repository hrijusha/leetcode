class Solution {
    int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        Trie trie = new Trie();

        // 1. Build the Trie with all words
        for (String word : words) {
            trie.insert(word);
        }

        // 2. Start DFS from every single cell on the board
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dfs(board, trie.getRoot(), "", r, c, result);
            }
        }

        return result;
    }

    private void dfs(char[][] board, TrieNode node, String currWord, int r, int c, List<String> result) {
        //Boundary check
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) {
            return;
        }

        char ch = board[r][c];

        // Base case: Cell is already visited or character isn't in this Trie branch
        if (ch == '#' || node.children.get(ch) == null) {
            return;
        }
        TrieNode nextNode = node.children.get(ch);
        currWord = currWord + ch;
        if (nextNode.isEndOfWord) {
            result.add(currWord);
            nextNode.isEndOfWord = false;
        }
        board[r][c] = '#';

        // 6. Explore all 4 directions
        for (int[] dir : directions) {
            dfs(board, nextNode, currWord, r + dir[0], c + dir[1], result);
        }

        board[r][c] = ch;
    }
}

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            TrieNode nextNode = curr.children.get(c);
            if (nextNode == null) {
                nextNode = new TrieNode();
                curr.children.put(c, nextNode);
            }
            curr = nextNode;
        }
        curr.isEndOfWord = true;
    }
}