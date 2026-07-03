class WordDictionary {
    class TrieNode {
        // Array to hold links to the next possible characters (a-z)
        TrieNode[] children;
        boolean isWord;

        public TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            // If the path doesn't exist, create a new node
            if (current.children[index] == null) {
                //store the character and attach a new TrieNode;
                current.children[index] = new TrieNode();
            }
            //move down to the new node
            current = current.children[index];
        }
        current.isWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        if (!word.contains(".")) {
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                // If the path doesn't exist, return false
                if (current.children[index] == null) {
                    return false;
                }
                current = current.children[index];
            }
            return current.isWord;
        } else {
            return searchHelper(word, 0, root);
        }
    }

    private boolean searchHelper(String word, int index, TrieNode current) {
        if (index == word.length()) {
            return current.isWord;
        }

        char c = word.charAt(index);

        if (c == '.') {
            for (TrieNode child : current.children) {
                if (child != null && searchHelper(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            int charIndex = c - 'a';
            if (current.children[charIndex] == null) {
                return false;
            }
            return searchHelper(word, index + 1, current.children[charIndex]);
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */