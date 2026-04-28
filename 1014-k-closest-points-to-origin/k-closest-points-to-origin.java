import java.util.*;

class Solution {
    public int[][] kClosest(int[][] points, int k) {
        quickSelect(points, 0, points.length - 1, k);
        return Arrays.copyOfRange(points, 0, k);
    }

    private void quickSelect(int[][] pts, int l, int r, int k) {
        if (l >= r) return;

        int pivot = partition(pts, l, r);

        if (pivot == k) return;
        else if (pivot < k) quickSelect(pts, pivot + 1, r, k);
        else quickSelect(pts, l, pivot - 1, k);
    }

    private int partition(int[][] pts, int l, int r) {
        int[] pivot = pts[r];
        int p = l;

        for (int i = l; i < r; i++) {
            if (dist(pts[i]) <= dist(pivot)) {
                int[] temp = pts[i];
                pts[i] = pts[p];
                pts[p] = temp;
                p++;
            }
        }

        int[] temp = pts[p];
        pts[p] = pts[r];
        pts[r] = temp;

        return p;
    }

    private int dist(int[] p) {
        return p[0]*p[0] + p[1]*p[1];
    }
}