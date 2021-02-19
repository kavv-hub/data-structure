package tree;

public class Trie {
    private static final int ALPHABET_SIZE = 26;

    private TrieNode root;

    public static class TrieNode {
        private final TrieNode[] children = new TrieNode[Trie.ALPHABET_SIZE];

        private boolean isEndOfWord;

        public TrieNode(boolean isEndOfWord) {
            this.isEndOfWord = isEndOfWord;
        }

        private boolean isEmpty() {
            for (TrieNode child : children) {
                if (child != null) {
                    return false;
                }
            }

            return true;
        }
    }

    public Trie() {
        this.root = new TrieNode(true);
    }

    public void insert(String s) {
        if (s.isEmpty()) return;
        _insert(root, s.toCharArray(), 0);
    }

    private void _insert(TrieNode node, char[] c, int i) {
        if (i >= c.length) {
            node.isEndOfWord = true;
            return;
        }

        int code = c[i] - 'a';
        if (node.children[code] == null) {
            node.children[code] = new TrieNode(false);
        }

        _insert(node.children[code], c, i + 1);
    }

    public void remove(String s) {
        _remove(root, s.toCharArray(), 0);
    }

    private TrieNode _remove(TrieNode node, char[] c, int i) {
        if (node == null) return null;
        if (i >= c.length) {
            if (!node.isEndOfWord) return node;
            if (node.isEmpty()) return null;
            node.isEndOfWord = false;
            return node;
        }

        int code = c[i] - 'a';
        node.children[code] = _remove(node.children[code], c, i + 1);

        if (!node.isEmpty() || node.isEndOfWord)
            return node;
        else
            return null;
    }

    public boolean search(String word) {
        TrieNode last = find(root, word.toCharArray(), 0);
        return last != null && last.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode last = find(root, prefix.toCharArray(), 0);
        return last != null;
    }

    private TrieNode find(TrieNode node, char[] c, int i) {
        if (node == null) return null;
        if (i >= c.length) return node;
        return find(node.children[c[i] - 'a'], c, i + 1);
    }
}
