/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> visited = new HashMap<>();
        return clone(node, visited);
    }

    private Node clone(Node originalNode, Map<Node, Node> visited) {
        if (visited.containsKey(originalNode)) {
            return visited.get(originalNode);
        }

        Node cloneNode = new Node(originalNode.val, new ArrayList<>());
        visited.put(originalNode, cloneNode);

        for (Node neighbor : originalNode.neighbors) {
            cloneNode.neighbors.add(clone(neighbor, visited));
        }

        return cloneNode;
    }
}