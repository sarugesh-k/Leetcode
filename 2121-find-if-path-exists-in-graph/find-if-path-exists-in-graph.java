class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        boolean[] visited = new boolean[n];
        return dfs(graph, source, destination, visited);
    }

    private boolean dfs(List<List<Integer>> g, int curr, int dest, boolean[] vis) {
        if (curr == dest) return true;
        vis[curr] = true;

        for (int nei : g.get(curr)) {
            if (!vis[nei]) {
                if (dfs(g, nei, dest, vis)) return true;
            }
        }
        return false;
    }
}