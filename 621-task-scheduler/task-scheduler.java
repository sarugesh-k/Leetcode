class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];

        for (char t : tasks) {
            freq[t - 'A']++;
        }

        int max = 0;
        for (int f : freq) {
            max = Math.max(max, f);
        }

        int countMax = 0;
        for (int f : freq) {
            if (f == max) countMax++;
        }

        int time = (max - 1) * (n + 1) + countMax;

        return Math.max(tasks.length, time);
    }
}