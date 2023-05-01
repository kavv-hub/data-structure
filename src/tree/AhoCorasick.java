class AhoCorasick {
    private Node root;

    public Trie(String[] words) {
        this.root = build(words);
    }

    private Node build(String[] words) {
        int n = words.length;
        
        Node root = new Node();
        for (int i = 0; i < n; ++i) {
            Node cur = root;

            for (char c : words[i].toCharArray()) {
                int idx = c - 'a';
                if (cur.children[idx] == null) {
                    cur.children[idx] = new Node();
                }
                cur = cur.children[idx];
            }

            cur.indexes.add(i);
        }

        for (int i = 0; i < 26; ++i) {
            if (root.children[i] == null) {
                root.children[i] = root;
            }
        }

        Queue<Node> q = new ArrayDeque<>(); 
        for (int i = 0; i < 26; ++i) {
            if (root.children[i] != root) {
                root.children[i].fallback = root;
                q.add(root.children[i]);
            }
        }

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 26; ++i) {
                Node child = cur.children[i];

                if (child != null) {
                    Node fallback = cur.fallback;
                    while (fallback.children[i] == null) {
                        fallback = fallback.fallback;
                    }
                    fallback = fallback.children[i];

                    child.fallback = fallback;
                    child.indexes.addAll(fallback.indexes);

                    q.add(child);
                }
            }
        }

        return root;
    }

    class Node {
        Set<Integer> indexes;

        Node fallback;

        Node[] children;

        public Node() {
            this.children = new Node[26];
            this.indexes = new HashSet<>();
        }
    }
}