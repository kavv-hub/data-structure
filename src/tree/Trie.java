package tree;

public class Trie {
    private static final int ALPHABET_SIZE = 26;

    private TrieNode root;

    public static class TrieNode {
        private final TrieNode[] children = new TrieNode[Trie.ALPHABET_SIZE];

        private boolean isEndOfWord;

        private int count;

        public TrieNode(boolean isEndOfWord) {
            this.isEndOfWord = isEndOfWord;
            this.count = 0;
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

        ++node.children[code].count;

        _insert(node.children[code], c, i + 1);
    }

    public boolean remove(String s) {
        return _remove(root, s.toCharArray(), 0);
    }

    private boolean _remove(TrieNode node, char[] c, int i) {
        if (node == null) return false;
        if (i >= c.length) return node.isEndOfWord;

        int code = c[i] - 'a';
        if (_remove(node.children[code], c, i + 1)) {
            if (i == c.length - 1) node.children[code].isEndOfWord = false;
            if (--node.children[code].count == 0) {
                node.children[code] = null;
            }
            return true;
        }

        return false;
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
