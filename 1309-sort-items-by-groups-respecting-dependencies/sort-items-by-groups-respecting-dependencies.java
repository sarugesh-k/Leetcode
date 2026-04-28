import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        for (int i = 0; i < n; i++) {
            if (group[i] == -1) group[i] = m++;
        }

        List<List<Integer>> itemGraph = new ArrayList<>();
        List<List<Integer>> groupGraph = new ArrayList<>();

        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[m];

        for (int i = 0; i < n; i++) {
            itemGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            groupGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {
                itemGraph.get(prev).add(i);
                itemIndegree[i]++;

                if (group[i] != group[prev]) {
                    groupGraph.get(group[prev]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        List<Integer> itemOrder = topo(itemGraph, itemIndegree);
        List<Integer> groupOrder = topo(groupGraph, groupIndegree);

        if (itemOrder.isEmpty() || groupOrder.isEmpty()) return new int[0];

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i : itemOrder) {
            map.computeIfAbsent(group[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> res = new ArrayList<>();
        for (int g : groupOrder) {
            if (map.containsKey(g)) {
                res.addAll(map.get(g));
            }
        }

        return res.stream().mapToInt(i -> i).toArray();
    }

    private List<Integer> topo(List<List<Integer>> g, int[] indegree) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            res.add(node);

            for (int nei : g.get(node)) {
                if (--indegree[nei] == 0) {
                    q.offer(nei);
                }
            }
        }

        return res.size() == indegree.length ? res : new ArrayList<>();
    }
}