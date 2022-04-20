package UnionFindSet;

import java.util.*;

public class CalcEquation {

    /**
     * 除法求值
     * 测试链接：https://leetcode-cn.com/problems/evaluate-division/
     */

    public static class Node { // 图的结点
        public String name;
        public List<Node> nexts;
        public List<Edge> edges;

        public Node(String name) {
            this.name = name;
            this.nexts = new ArrayList<>();
            this.edges = new ArrayList<>();
        }
    }

    public static class Edge { // 图的边
        public Node from;
        public Node to;
        public double weight;

        public Edge(Node from, Node to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static class Graph { // 图
        public Map<String, Node> nodes;
        public Set<Edge> edges;

        public Graph() {
            this.nodes = new HashMap<>();
            this.edges = new HashSet<>();
        }
    }

    public static class UnionFind { // 并查集
        private Map<Node, Node> fatherMap; // 存储结点的父结点
        private Map<Node, Double> weightMap; // 存储结点到其父结点的权值

        public UnionFind(Collection<Node> nodes) {
            fatherMap = new HashMap<>();
            weightMap = new HashMap<>();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                weightMap.put(node, 1.0D);
            }
        }

        private Node find(Node node) {
            if (node != fatherMap.get(node)) {
                Node father = fatherMap.get(node);
                fatherMap.put(node, find(fatherMap.get(node)));
                weightMap.put(node, weightMap.get(node) * weightMap.get(father));

            }
            return fatherMap.get(node);
        }

        public void union(Node a, Node b) {
            Node aF = find(a);
            Node bF = find(b);
            Edge edgeaTob = null;
            for (Edge edge : a.edges) {
                if (edge.to == b) {
                    edgeaTob = edge;
                    break;
                }
            }
            if (aF != bF) {
                fatherMap.put(aF, bF);
                weightMap.put(aF, weightMap.get(b) * edgeaTob.weight / weightMap.get(a));
            }
        }

        public double isConnected(Node a, Node b) { // 计算结点a / 结点b
            Node aF = find(a);
            Node bF = find(b);
            if (aF == bF) {
                return weightMap.get(a) / weightMap.get(b);
            } else {
                return -1.0D;
            }
        }
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Graph graph = new Graph();
        for (int i = 0; i < equations.size(); i++) {
            String from = equations.get(i).get(0);
            String to = equations.get(i).get(1);
            double weight = values[i];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(fromNode, toNode, weight);
            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
        double[] res = new double[queries.size()];
        UnionFind uf = new UnionFind(graph.nodes.values());
        for (int i = 0; i < equations.size(); i++) {
            String from = equations.get(i).get(0);
            String to = equations.get(i).get(1);
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            uf.union(fromNode, toNode);
        }
        for (int i = 0; i < queries.size(); i++) {
            String from = queries.get(i).get(0);
            String to = queries.get(i).get(1);
            if (!graph.nodes.containsKey(from) || !graph.nodes.containsKey(to)) {
                res[i] = -1.0;
            } else {
                res[i] = uf.isConnected(graph.nodes.get(from), graph.nodes.get(to));
            }
        }
        return res;
    }

}
