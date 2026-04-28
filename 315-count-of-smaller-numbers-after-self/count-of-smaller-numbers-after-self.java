import java.util.*;

class Solution {
    int[] count;
    int[] index;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        count = new int[n];
        index = new int[n];

        for (int i = 0; i < n; i++) index[i] = i;

        mergeSort(nums, 0, n - 1);

        List<Integer> res = new ArrayList<>();
        for (int c : count) res.add(c);
        return res;
    }

    private void mergeSort(int[] nums, int l, int r) {
        if (l >= r) return;

        int mid = (l + r) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }

    private void merge(int[] nums, int l, int m, int r) {
        List<Integer> temp = new ArrayList<>();
        int i = l, j = m + 1, rightCount = 0;

        while (i <= m && j <= r) {
            if (nums[index[j]] < nums[index[i]]) {
                temp.add(index[j++]);
                rightCount++;
            } else {
                count[index[i]] += rightCount;
                temp.add(index[i++]);
            }
        }

        while (i <= m) {
            count[index[i]] += rightCount;
            temp.add(index[i++]);
        }

        while (j <= r) temp.add(index[j++]);

        for (int k = l; k <= r; k++) {
            index[k] = temp.get(k - l);
        }
    }
}