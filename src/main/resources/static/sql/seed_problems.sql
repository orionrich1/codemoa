-- ============================================================
-- AI 코딩 문제 시드 데이터 (각 카테고리×난이도 8개 추가 → 총 10개)
-- user_id: admin
-- ============================================================

-- =========================================================
-- JAVA - 하 (Easy)
-- =========================================================
INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category) VALUES
('admin',
 '[JAVA-하] 두 수의 합 출력',
 '정수 a와 b를 입력받아 두 수의 합을 출력하는 메서드를 작성하세요.\n\n**입력 예시**\na = 3, b = 7\n\n**출력 예시**\n10',
 'public class Solution {\n    public int sum(int a, int b) {\n        return a + b;\n    }\n\n    public static void main(String[] args) {\n        Solution sol = new Solution();\n        System.out.println(sol.sum(3, 7)); // 10\n    }\n}',
 'return 키워드를 사용해 두 값을 더한 결과를 반환하세요.',
 '하', 'JAVA'),

('admin',
 '[JAVA-하] 홀수 짝수 판별',
 '정수 n을 입력받아 홀수이면 "홀수", 짝수이면 "짝수"를 반환하는 메서드를 작성하세요.\n\n**입력 예시**\nn = 4\n\n**출력 예시**\n짝수',
 'public class Solution {\n    public String check(int n) {\n        return (n % 2 == 0) ? "짝수" : "홀수";\n    }\n}',
 '% 연산자로 2로 나눈 나머지를 확인하세요.',
 '하', 'JAVA'),

('admin',
 '[JAVA-하] 1부터 N까지의 합',
 '양의 정수 n을 입력받아 1부터 n까지의 합을 반환하는 메서드를 작성하세요.\n\n**입력 예시**\nn = 5\n\n**출력 예시**\n15',
 'public class Solution {\n    public int sumToN(int n) {\n        int sum = 0;\n        for (int i = 1; i <= n; i++) {\n            sum += i;\n        }\n        return sum;\n    }\n}',
 'for 반복문을 사용하거나 n*(n+1)/2 공식을 활용하세요.',
 '하', 'JAVA'),

('admin',
 '[JAVA-하] 배열의 최댓값',
 '정수 배열을 입력받아 배열 내 최댓값을 반환하는 메서드를 작성하세요.\n\n**입력 예시**\narr = {3, 1, 4, 1, 5, 9, 2, 6}\n\n**출력 예시**\n9',
 'public class Solution {\n    public int maxValue(int[] arr) {\n        int max = arr[0];\n        for (int num : arr) {\n            if (num > max) max = num;\n        }\n        return max;\n    }\n}',
 '첫 번째 원소를 max로 설정하고 순회하면서 비교하세요.',
 '하', 'JAVA'),

('admin',
 '[JAVA-하] 문자열 뒤집기',
 '문자열 str을 입력받아 뒤집은 문자열을 반환하는 메서드를 작성하세요.\n\n**입력 예시**\nstr = "hello"\n\n**출력 예시**\n"olleh"',
 'public class Solution {\n    public String reverse(String str) {\n        return new StringBuilder(str).reverse().toString();\n    }\n}',
 'StringBuilder의 reverse() 메서드를 활용하거나, 직접 char 배열로 앞뒤를 교환하세요.',
 '하', 'JAVA'),

('admin',
 '[JAVA-하] 팩토리얼 계산',
 '양의 정수 n을 입력받아 n!(팩토리얼)을 반환하는 메서드를 작성하세요. n은 0 이상 12 이하입니다.\n\n**입력 예시**\nn = 5\n\n**출력 예시**\n120',
 'public class Solution {\n    public long factorial(int n) {\n        if (n <= 1) return 1;\n        long result = 1;\n        for (int i = 2; i <= n; i++) {\n            result *= i;\n        }\n        return result;\n    }\n}',
 '0! = 1, 1! = 1 을 기저 조건으로 하고 2부터 n까지 곱하세요.',
 '하', 'JAVA'),

('admin',
 '[JAVA-하] 구구단 출력',
 '정수 n(2~9)을 입력받아 n단 구구단을 문자열 배열로 반환하는 메서드를 작성하세요.\n\n**입력 예시**\nn = 3\n\n**출력 예시**\n["3 x 1 = 3", "3 x 2 = 6", ..., "3 x 9 = 27"]',
 'public class Solution {\n    public String[] gugu(int n) {\n        String[] result = new String[9];\n        for (int i = 1; i <= 9; i++) {\n            result[i-1] = n + " x " + i + " = " + (n * i);\n        }\n        return result;\n    }\n}',
 '9개짜리 배열을 만들고 i를 1~9로 반복하면서 채우세요.',
 '하', 'JAVA'),

('admin',
 '[JAVA-하] 최솟값 찾기',
 '정수 배열을 입력받아 배열 내 최솟값을 반환하는 메서드를 작성하세요.\n\n**입력 예시**\narr = {3, 1, 4, 1, 5, 9}\n\n**출력 예시**\n1',
 'public class Solution {\n    public int minValue(int[] arr) {\n        int min = arr[0];\n        for (int num : arr) {\n            if (num < min) min = num;\n        }\n        return min;\n    }\n}',
 'arr[0]을 초기값으로 설정하고 반복하며 더 작은 값으로 갱신하세요.',
 '하', 'JAVA');

-- =========================================================
-- JAVA - 중 (Medium)
-- =========================================================
INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category) VALUES
('admin',
 '[JAVA-중] 피보나치 수열',
 'n번째 피보나치 수를 반환하는 메서드를 작성하세요. (0-indexed, fib(0)=0, fib(1)=1)\n\n**입력 예시**\nn = 6\n\n**출력 예시**\n8',
 'public class Solution {\n    public long fib(int n) {\n        if (n <= 1) return n;\n        long a = 0, b = 1;\n        for (int i = 2; i <= n; i++) {\n            long c = a + b;\n            a = b;\n            b = c;\n        }\n        return b;\n    }\n}',
 '재귀보다 반복문(반복 DP)으로 구현하면 훨씬 효율적입니다.',
 '중', 'JAVA'),

('admin',
 '[JAVA-중] 소수 판별',
 '양의 정수 n이 소수인지 판별하여 boolean을 반환하는 메서드를 작성하세요.\n\n**입력 예시**\nn = 17\n\n**출력 예시**\ntrue',
 'public class Solution {\n    public boolean isPrime(int n) {\n        if (n < 2) return false;\n        for (int i = 2; i <= Math.sqrt(n); i++) {\n            if (n % i == 0) return false;\n        }\n        return true;\n    }\n}',
 '2부터 √n까지만 나누어 보면 됩니다. Math.sqrt()를 활용하세요.',
 '중', 'JAVA'),

('admin',
 '[JAVA-중] 버블 정렬',
 '정수 배열을 버블 정렬(오름차순)로 정렬하는 메서드를 작성하세요. 새 배열을 반환하거나 in-place로 정렬 후 반환하세요.\n\n**입력 예시**\narr = {5, 3, 8, 1, 2}\n\n**출력 예시**\n{1, 2, 3, 5, 8}',
 'public class Solution {\n    public int[] bubbleSort(int[] arr) {\n        int n = arr.length;\n        for (int i = 0; i < n - 1; i++) {\n            for (int j = 0; j < n - i - 1; j++) {\n                if (arr[j] > arr[j+1]) {\n                    int tmp = arr[j];\n                    arr[j] = arr[j+1];\n                    arr[j+1] = tmp;\n                }\n            }\n        }\n        return arr;\n    }\n}',
 '인접한 두 원소를 비교하여 큰 값이 뒤로 가도록 교환을 반복합니다.',
 '중', 'JAVA'),

('admin',
 '[JAVA-중] 이진 탐색',
 '오름차순 정렬된 정수 배열에서 target을 이진 탐색으로 찾아 인덱스를 반환하세요. 없으면 -1을 반환합니다.\n\n**입력 예시**\narr = {1, 3, 5, 7, 9, 11}, target = 7\n\n**출력 예시**\n3',
 'public class Solution {\n    public int binarySearch(int[] arr, int target) {\n        int left = 0, right = arr.length - 1;\n        while (left <= right) {\n            int mid = left + (right - left) / 2;\n            if (arr[mid] == target) return mid;\n            else if (arr[mid] < target) left = mid + 1;\n            else right = mid - 1;\n        }\n        return -1;\n    }\n}',
 'left, right, mid 세 포인터를 사용하고 mid 값과 target을 비교하며 범위를 절반씩 줄이세요.',
 '중', 'JAVA'),

('admin',
 '[JAVA-중] 팰린드롬 검사',
 '문자열 s가 팰린드롬(앞뒤가 같은 문자열)인지 확인하여 boolean을 반환하세요. 알파벳 소문자와 숫자만 고려합니다(공백, 특수문자 무시).\n\n**입력 예시**\ns = "A man a plan a canal Panama"\n\n**출력 예시**\ntrue',
 'public class Solution {\n    public boolean isPalindrome(String s) {\n        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();\n        String reversed = new StringBuilder(cleaned).reverse().toString();\n        return cleaned.equals(reversed);\n    }\n}',
 '정규식으로 특수문자를 제거하고 소문자로 변환 후 뒤집은 문자열과 비교하세요.',
 '중', 'JAVA'),

('admin',
 '[JAVA-중] 스택 구현',
 'Integer 타입의 스택을 ArrayList를 사용해 구현하세요. push(int), pop(), peek(), isEmpty() 메서드를 포함해야 합니다.\n\n**요구사항**\n- push: 값을 스택에 추가\n- pop: 최상단 값을 제거하고 반환 (비어있으면 -1)\n- peek: 최상단 값을 반환 (제거하지 않음, 비어있으면 -1)\n- isEmpty: 스택이 비어있으면 true',
 'import java.util.ArrayList;\n\npublic class MyStack {\n    private ArrayList<Integer> list = new ArrayList<>();\n\n    public void push(int val) {\n        list.add(val);\n    }\n\n    public int pop() {\n        if (isEmpty()) return -1;\n        return list.remove(list.size() - 1);\n    }\n\n    public int peek() {\n        if (isEmpty()) return -1;\n        return list.get(list.size() - 1);\n    }\n\n    public boolean isEmpty() {\n        return list.isEmpty();\n    }\n}',
 'ArrayList의 마지막 원소를 top으로 사용하면 push/pop이 O(1)입니다.',
 '중', 'JAVA'),

('admin',
 '[JAVA-중] 문자열 압축',
 '연속된 같은 문자를 "문자+횟수" 형식으로 압축하는 메서드를 작성하세요. 횟수가 1이면 문자만 씁니다.\n\n**입력 예시**\nstr = "aaabbcccc"\n\n**출력 예시**\n"a3b2c4"',
 'public class Solution {\n    public String compress(String s) {\n        if (s.isEmpty()) return s;\n        StringBuilder sb = new StringBuilder();\n        int count = 1;\n        for (int i = 1; i < s.length(); i++) {\n            if (s.charAt(i) == s.charAt(i-1)) {\n                count++;\n            } else {\n                sb.append(s.charAt(i-1));\n                if (count > 1) sb.append(count);\n                count = 1;\n            }\n        }\n        sb.append(s.charAt(s.length()-1));\n        if (count > 1) sb.append(count);\n        return sb.toString();\n    }\n}',
 '현재 문자와 이전 문자를 비교하며 count를 증가시키고, 달라지면 결과에 추가하세요.',
 '중', 'JAVA'),

('admin',
 '[JAVA-중] 두 문자열의 애너그램 검사',
 '두 문자열 s와 t가 애너그램(같은 문자로 이루어진 단어)인지 확인하여 boolean을 반환하세요.\n\n**입력 예시**\ns = "anagram", t = "nagaram"\n\n**출력 예시**\ntrue',
 'import java.util.Arrays;\n\npublic class Solution {\n    public boolean isAnagram(String s, String t) {\n        if (s.length() != t.length()) return false;\n        char[] a = s.toCharArray();\n        char[] b = t.toCharArray();\n        Arrays.sort(a);\n        Arrays.sort(b);\n        return Arrays.equals(a, b);\n    }\n}',
 '두 문자열을 정렬한 뒤 비교하거나, 문자 빈도 배열(int[26])을 사용하세요.',
 '중', 'JAVA');

-- =========================================================
-- JAVA - 상 (Hard)
-- =========================================================
INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category) VALUES
('admin',
 '[JAVA-상] BFS 최단 경로',
 '인접 리스트로 주어진 비가중치 무방향 그래프에서 시작 노드 start부터 목표 노드 end까지의 최단 거리를 BFS로 구하세요. 경로가 없으면 -1을 반환합니다.\n\n**그래프**\nn=6, edges=[[0,1],[0,2],[1,3],[2,4],[3,5]], start=0, end=5\n\n**출력 예시**\n3',
 'import java.util.*;\n\npublic class Solution {\n    public int bfs(int n, int[][] edges, int start, int end) {\n        List<List<Integer>> graph = new ArrayList<>();\n        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());\n        for (int[] e : edges) {\n            graph.get(e[0]).add(e[1]);\n            graph.get(e[1]).add(e[0]);\n        }\n        int[] dist = new int[n];\n        Arrays.fill(dist, -1);\n        Queue<Integer> q = new LinkedList<>();\n        q.add(start);\n        dist[start] = 0;\n        while (!q.isEmpty()) {\n            int cur = q.poll();\n            if (cur == end) return dist[cur];\n            for (int next : graph.get(cur)) {\n                if (dist[next] == -1) {\n                    dist[next] = dist[cur] + 1;\n                    q.add(next);\n                }\n            }\n        }\n        return -1;\n    }\n}',
 'Queue를 사용해 BFS를 구현하고, dist 배열로 방문 여부와 거리를 함께 관리하세요.',
 '상', 'JAVA'),

('admin',
 '[JAVA-상] 동적 프로그래밍 - 0/1 배낭 문제',
 'n개의 물건이 있고 각각 무게 weights[]와 가치 values[]가 주어질 때, 최대 용량 capacity인 배낭에 담을 수 있는 최대 가치를 구하세요.\n\n**입력 예시**\nn=4, weights={1,3,4,5}, values={1,4,5,7}, capacity=7\n\n**출력 예시**\n9',
 'public class Solution {\n    public int knapsack(int n, int[] w, int[] v, int cap) {\n        int[][] dp = new int[n+1][cap+1];\n        for (int i = 1; i <= n; i++) {\n            for (int j = 0; j <= cap; j++) {\n                dp[i][j] = dp[i-1][j];\n                if (j >= w[i-1]) {\n                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-w[i-1]] + v[i-1]);\n                }\n            }\n        }\n        return dp[n][cap];\n    }\n}',
 'dp[i][j] = i번째 물건까지 고려했을 때 용량 j에서의 최대 가치로 정의하세요.',
 '상', 'JAVA'),

('admin',
 '[JAVA-상] 최장 공통 부분 수열 (LCS)',
 '두 문자열 s1, s2의 최장 공통 부분 수열(LCS)의 길이를 동적 프로그래밍으로 구하세요.\n\n**입력 예시**\ns1 = "ABCBDAB", s2 = "BDCAB"\n\n**출력 예시**\n4 (BCAB)',
 'public class Solution {\n    public int lcs(String s1, String s2) {\n        int m = s1.length(), n = s2.length();\n        int[][] dp = new int[m+1][n+1];\n        for (int i = 1; i <= m; i++) {\n            for (int j = 1; j <= n; j++) {\n                if (s1.charAt(i-1) == s2.charAt(j-1)) {\n                    dp[i][j] = dp[i-1][j-1] + 1;\n                } else {\n                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);\n                }\n            }\n        }\n        return dp[m][n];\n    }\n}',
 'dp[i][j]를 s1[0..i-1]과 s2[0..j-1]의 LCS 길이로 정의하세요.',
 '상', 'JAVA'),

('admin',
 '[JAVA-상] 이진 트리 레벨 순서 출력',
 '이진 트리의 루트가 주어질 때, 레벨 순서(BFS)로 순회하여 각 레벨의 노드 값을 List<List<Integer>>로 반환하세요.\n\n**입력 예시**\n트리: 3 → [9, 20] → [null,null,15,7]\n\n**출력 예시**\n[[3], [9, 20], [15, 7]]',
 'import java.util.*;\n\npublic class Solution {\n    static class TreeNode {\n        int val;\n        TreeNode left, right;\n        TreeNode(int val) { this.val = val; }\n    }\n\n    public List<List<Integer>> levelOrder(TreeNode root) {\n        List<List<Integer>> result = new ArrayList<>();\n        if (root == null) return result;\n        Queue<TreeNode> q = new LinkedList<>();\n        q.add(root);\n        while (!q.isEmpty()) {\n            int size = q.size();\n            List<Integer> level = new ArrayList<>();\n            for (int i = 0; i < size; i++) {\n                TreeNode node = q.poll();\n                level.add(node.val);\n                if (node.left != null) q.add(node.left);\n                if (node.right != null) q.add(node.right);\n            }\n            result.add(level);\n        }\n        return result;\n    }\n}',
 '각 레벨 처리 시작 시 q.size()를 저장하여 그 수만큼만 처리하면 레벨 구분이 됩니다.',
 '상', 'JAVA'),

('admin',
 '[JAVA-상] 문자열 순열 생성',
 '문자열 s의 모든 순열을 중복 없이 생성하여 정렬된 리스트로 반환하세요.\n\n**입력 예시**\ns = "abc"\n\n**출력 예시**\n["abc", "acb", "bac", "bca", "cab", "cba"]',
 'import java.util.*;\n\npublic class Solution {\n    private List<String> result = new ArrayList<>();\n    private boolean[] used;\n\n    public List<String> permute(String s) {\n        char[] chars = s.toCharArray();\n        Arrays.sort(chars);\n        used = new boolean[chars.length];\n        dfs(chars, new StringBuilder());\n        Collections.sort(result);\n        return result;\n    }\n\n    private void dfs(char[] chars, StringBuilder sb) {\n        if (sb.length() == chars.length) {\n            result.add(sb.toString());\n            return;\n        }\n        for (int i = 0; i < chars.length; i++) {\n            if (used[i]) continue;\n            if (i > 0 && chars[i] == chars[i-1] && !used[i-1]) continue;\n            used[i] = true;\n            sb.append(chars[i]);\n            dfs(chars, sb);\n            sb.deleteCharAt(sb.length()-1);\n            used[i] = false;\n        }\n    }\n}',
 '백트래킹으로 구현하고, 중복 제거를 위해 정렬 후 같은 문자가 연속될 때 조건을 추가하세요.',
 '상', 'JAVA'),

('admin',
 '[JAVA-상] LRU 캐시 구현',
 'capacity를 입력받아 LRU(Least Recently Used) 캐시를 구현하세요. get(key)와 put(key, value) 메서드를 지원하며, get은 없으면 -1을 반환합니다.\n\n**동작 예시**\nLRUCache(2) → put(1,1) → put(2,2) → get(1)=1 → put(3,3) → get(2)=-1 → get(3)=3',
 'import java.util.*;\n\npublic class LRUCache {\n    private final int cap;\n    private final LinkedHashMap<Integer,Integer> map;\n\n    public LRUCache(int capacity) {\n        this.cap = capacity;\n        this.map = new LinkedHashMap<>(capacity, 0.75f, true) {\n            protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {\n                return size() > cap;\n            }\n        };\n    }\n\n    public int get(int key) {\n        return map.getOrDefault(key, -1);\n    }\n\n    public void put(int key, int value) {\n        map.put(key, value);\n    }\n}',
 'LinkedHashMap에 accessOrder=true를 설정하면 get/put 시 자동으로 접근 순서가 갱신됩니다.',
 '상', 'JAVA'),

('admin',
 '[JAVA-상] 다익스트라 최단 경로',
 '가중치 방향 그래프에서 시작 노드 src로부터 모든 노드까지의 최단 거리를 다익스트라 알고리즘으로 구하세요.\n\n**입력 예시**\nn=5, edges=[[0,1,4],[0,2,1],[2,1,2],[1,3,1],[2,3,5],[3,4,3]], src=0\n\n**출력 예시**\n[0, 3, 1, 4, 7]',
 'import java.util.*;\n\npublic class Solution {\n    public int[] dijkstra(int n, int[][] edges, int src) {\n        List<int[]>[] graph = new ArrayList[n];\n        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();\n        for (int[] e : edges) graph[e[0]].add(new int[]{e[1], e[2]});\n\n        int[] dist = new int[n];\n        Arrays.fill(dist, Integer.MAX_VALUE);\n        dist[src] = 0;\n\n        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));\n        pq.add(new int[]{src, 0});\n\n        while (!pq.isEmpty()) {\n            int[] cur = pq.poll();\n            int u = cur[0], d = cur[1];\n            if (d > dist[u]) continue;\n            for (int[] next : graph[u]) {\n                int v = next[0], w = next[1];\n                if (dist[u] + w < dist[v]) {\n                    dist[v] = dist[u] + w;\n                    pq.add(new int[]{v, dist[v]});\n                }\n            }\n        }\n        return dist;\n    }\n}',
 'PriorityQueue(최솟힙)로 현재 최소 거리 노드를 꺼내고, 거리 갱신 시에만 큐에 추가하세요.',
 '상', 'JAVA'),

('admin',
 '[JAVA-상] 병합 정렬',
 '정수 배열을 병합 정렬(오름차순)로 정렬하는 메서드를 작성하세요. 시간복잡도 O(n log n)을 만족해야 합니다.\n\n**입력 예시**\narr = {38, 27, 43, 3, 9, 82, 10}\n\n**출력 예시**\n{3, 9, 10, 27, 38, 43, 82}',
 'public class Solution {\n    public int[] mergeSort(int[] arr) {\n        if (arr.length <= 1) return arr;\n        int mid = arr.length / 2;\n        int[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));\n        int[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));\n        return merge(left, right);\n    }\n\n    private int[] merge(int[] a, int[] b) {\n        int[] res = new int[a.length + b.length];\n        int i = 0, j = 0, k = 0;\n        while (i < a.length && j < b.length) {\n            res[k++] = (a[i] <= b[j]) ? a[i++] : b[j++];\n        }\n        while (i < a.length) res[k++] = a[i++];\n        while (j < b.length) res[k++] = b[j++];\n        return res;\n    }\n}',
 '분할(divide)과 병합(merge) 두 단계로 나누어 재귀로 구현하세요.',
 '상', 'JAVA');

-- =========================================================
-- Javascript - 하 (Easy)
-- =========================================================
INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category) VALUES
('admin',
 '[JS-하] 두 수의 합',
 '두 숫자를 받아 합을 반환하는 함수 `add`를 작성하세요.\n\n**입력 예시**\nadd(3, 7)\n\n**출력 예시**\n10',
 'function add(a, b) {\n  return a + b;\n}',
 '단순히 두 인자를 + 연산자로 더하세요.',
 '하', 'Javascript'),

('admin',
 '[JS-하] 배열에서 최댓값 찾기',
 '숫자 배열을 받아 최댓값을 반환하는 함수 `findMax`를 작성하세요.\n\n**입력 예시**\nfindMax([3, 1, 4, 1, 5, 9, 2, 6])\n\n**출력 예시**\n9',
 'function findMax(arr) {\n  return Math.max(...arr);\n}',
 'Math.max()에 spread operator(...)를 사용하거나 reduce를 활용하세요.',
 '하', 'Javascript'),

('admin',
 '[JS-하] 홀짝 판별',
 '숫자 n을 받아 홀수이면 "odd", 짝수이면 "even"을 반환하는 함수 `checkParity`를 작성하세요.\n\n**입력 예시**\ncheckParity(4)\n\n**출력 예시**\n"even"',
 'function checkParity(n) {\n  return n % 2 === 0 ? "even" : "odd";\n}',
 '% 2 연산 결과가 0이면 짝수입니다.',
 '하', 'Javascript'),

('admin',
 '[JS-하] 배열 중복 제거',
 '배열에서 중복된 값을 제거하고 유일한 값만 담은 배열을 반환하는 함수 `unique`를 작성하세요.\n\n**입력 예시**\nunique([1, 2, 2, 3, 4, 3, 5])\n\n**출력 예시**\n[1, 2, 3, 4, 5]',
 'function unique(arr) {\n  return [...new Set(arr)];\n}',
 'Set 자료구조는 중복을 허용하지 않습니다. Set 생성 후 다시 배열로 변환하세요.',
 '하', 'Javascript'),

('admin',
 '[JS-하] 문자열 뒤집기',
 '문자열을 뒤집어 반환하는 함수 `reverseStr`을 작성하세요.\n\n**입력 예시**\nreverseStr("hello")\n\n**출력 예시**\n"olleh"',
 'function reverseStr(str) {\n  return str.split("").reverse().join("");\n}',
 'split("")으로 문자 배열로 변환 → reverse() → join("")으로 문자열 재조합.',
 '하', 'Javascript'),

('admin',
 '[JS-하] 1부터 N까지 합',
 '양의 정수 n을 받아 1부터 n까지의 합을 반환하는 함수 `sumToN`을 작성하세요.\n\n**입력 예시**\nsumToN(10)\n\n**출력 예시**\n55',
 'function sumToN(n) {\n  return (n * (n + 1)) / 2;\n}',
 'n*(n+1)/2 공식 또는 reduce/for 반복문을 사용하세요.',
 '하', 'Javascript'),

('admin',
 '[JS-하] FizzBuzz',
 '1부터 n까지의 FizzBuzz 결과를 배열로 반환하는 함수 `fizzBuzz`를 작성하세요.\n3의 배수는 "Fizz", 5의 배수는 "Buzz", 15의 배수는 "FizzBuzz", 나머지는 숫자 문자열.\n\n**입력 예시**\nfizzBuzz(15)\n\n**출력 예시**\n["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]',
 'function fizzBuzz(n) {\n  const result = [];\n  for (let i = 1; i <= n; i++) {\n    if (i % 15 === 0) result.push("FizzBuzz");\n    else if (i % 3 === 0) result.push("Fizz");\n    else if (i % 5 === 0) result.push("Buzz");\n    else result.push(String(i));\n  }\n  return result;\n}',
 '15의 배수 조건을 가장 먼저 확인해야 합니다.',
 '하', 'Javascript'),

('admin',
 '[JS-하] 객체 키-값 뒤집기',
 '객체의 키와 값을 서로 뒤집은 새 객체를 반환하는 함수 `invertObject`를 작성하세요.\n\n**입력 예시**\ninvertObject({ a: 1, b: 2, c: 3 })\n\n**출력 예시**\n{ "1": "a", "2": "b", "3": "c" }',
 'function invertObject(obj) {\n  return Object.fromEntries(\n    Object.entries(obj).map(([k, v]) => [v, k])\n  );\n}',
 'Object.entries()로 [key, value] 쌍을 얻고, map으로 [value, key]로 뒤집은 뒤 Object.fromEntries()로 재조합하세요.',
 '하', 'Javascript');

-- =========================================================
-- Javascript - 중 (Medium)
-- =========================================================
INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category) VALUES
('admin',
 '[JS-중] 클로저 카운터',
 '호출할 때마다 1씩 증가하는 카운터 함수를 반환하는 `makeCounter` 함수를 작성하세요.\n\n**사용 예시**\nconst counter = makeCounter();\ncounter(); // 1\ncounter(); // 2\ncounter(); // 3',
 'function makeCounter() {\n  let count = 0;\n  return function () {\n    return ++count;\n  };\n}',
 '외부 함수의 지역 변수를 내부 함수에서 참조하면 클로저가 형성됩니다.',
 '중', 'Javascript'),

('admin',
 '[JS-중] 배열 평탄화',
 '중첩 배열을 지정한 깊이만큼 평탄화(flatten)하는 함수 `flattenArray`를 작성하세요. depth 기본값은 1입니다.\n\n**입력 예시**\nflattenArray([1, [2, [3, [4]]]], 2)\n\n**출력 예시**\n[1, 2, 3, [4]]',
 'function flattenArray(arr, depth = 1) {\n  return arr.reduce((acc, val) => {\n    if (Array.isArray(val) && depth > 0) {\n      acc.push(...flattenArray(val, depth - 1));\n    } else {\n      acc.push(val);\n    }\n    return acc;\n  }, []);\n}',
 'reduce와 재귀를 조합하거나 Array.prototype.flat(depth)을 사용하세요.',
 '중', 'Javascript'),

('admin',
 '[JS-중] Promise 체인 구현',
 '비동기 작업을 순차적으로 실행하는 `runSequential` 함수를 작성하세요.\n함수 배열을 받아 각 함수를 순서대로 실행하고, 모든 결과를 배열로 resolve하는 Promise를 반환합니다.\n각 함수는 Promise를 반환합니다.\n\n**입력 예시**\nconst tasks = [\n  () => Promise.resolve(1),\n  () => Promise.resolve(2),\n  () => Promise.resolve(3)\n];\nrunSequential(tasks).then(console.log); // [1, 2, 3]',
 'async function runSequential(tasks) {\n  const results = [];\n  for (const task of tasks) {\n    const result = await task();\n    results.push(result);\n  }\n  return results;\n}',
 'async/await와 for...of를 조합하면 Promise 체인을 간단히 구현할 수 있습니다.',
 '중', 'Javascript'),

('admin',
 '[JS-중] 디바운스 구현',
 '함수 fn과 지연 시간 delay(ms)를 받아 디바운스된 함수를 반환하는 `debounce`를 구현하세요.\n마지막 호출 후 delay ms가 지나야 fn이 실행됩니다.\n\n**사용 예시**\nconst debounced = debounce(() => console.log("called"), 300);\ndebounced(); // 타이머 시작\ndebounced(); // 타이머 리셋\n// 300ms 후 "called" 출력 (한 번만)',
 'function debounce(fn, delay) {\n  let timer = null;\n  return function (...args) {\n    clearTimeout(timer);\n    timer = setTimeout(() => {\n      fn.apply(this, args);\n    }, delay);\n  };\n}',
 'setTimeout과 clearTimeout을 클로저로 감싸고, 새 호출마다 이전 타이머를 취소하세요.',
 '중', 'Javascript'),

('admin',
 '[JS-중] 깊은 객체 복사',
 '객체를 깊은 복사(deep copy)하는 `deepClone` 함수를 작성하세요. 중첩 객체와 배열을 모두 처리해야 합니다.\n\n**입력 예시**\nconst obj = { a: 1, b: { c: 2, d: [3, 4] } };\nconst copy = deepClone(obj);\ncopy.b.c = 99;\nconsole.log(obj.b.c); // 2 (원본 불변)',
 'function deepClone(value) {\n  if (value === null || typeof value !== "object") return value;\n  if (Array.isArray(value)) return value.map(deepClone);\n  return Object.fromEntries(\n    Object.entries(value).map(([k, v]) => [k, deepClone(v)])\n  );\n}',
 '재귀적으로 각 프로퍼티를 복사합니다. 기본값(원시형)은 그대로 반환, 배열과 객체는 재귀 호출하세요.',
 '중', 'Javascript'),

('admin',
 '[JS-중] 배열 그룹화',
 '배열과 키 추출 함수를 받아 그룹화된 객체를 반환하는 `groupBy` 함수를 작성하세요.\n\n**입력 예시**\ngroupBy([6.1, 4.2, 6.3], Math.floor)\n\n**출력 예시**\n{ 4: [4.2], 6: [6.1, 6.3] }',
 'function groupBy(arr, fn) {\n  return arr.reduce((acc, item) => {\n    const key = fn(item);\n    if (!acc[key]) acc[key] = [];\n    acc[key].push(item);\n    return acc;\n  }, {});\n}',
 'reduce로 누산기 객체에 키를 만들고 아이템을 push하세요.',
 '중', 'Javascript'),

('admin',
 '[JS-중] 메모이제이션 구현',
 '함수를 받아 결과를 캐싱하는 메모이제이션 함수 `memoize`를 구현하세요. 같은 인자로 호출하면 캐시된 결과를 반환합니다.\n\n**사용 예시**\nconst memoFib = memoize(function fib(n) {\n  if (n <= 1) return n;\n  return memoFib(n-1) + memoFib(n-2);\n});\nmemoFib(40); // 빠르게 반환',
 'function memoize(fn) {\n  const cache = new Map();\n  return function (...args) {\n    const key = JSON.stringify(args);\n    if (cache.has(key)) return cache.get(key);\n    const result = fn.apply(this, args);\n    cache.set(key, result);\n    return result;\n  };\n}',
 'Map을 캐시 저장소로 사용하고, 인자를 JSON.stringify로 직렬화하여 키로 사용하세요.',
 '중', 'Javascript'),

('admin',
 '[JS-중] 파이프라인 함수 구현',
 '함수들을 왼쪽에서 오른쪽으로 순서대로 적용하는 `pipe` 함수를 구현하세요.\n\n**입력 예시**\nconst double = x => x * 2;\nconst addOne = x => x + 1;\nconst square = x => x * x;\nconst transform = pipe(double, addOne, square);\ntransform(3); // (3*2+1)^2 = 49',
 'function pipe(...fns) {\n  return function (value) {\n    return fns.reduce((acc, fn) => fn(acc), value);\n  };\n}',
 'reduce를 사용해 초기값부터 각 함수를 순서대로 적용하세요.',
 '중', 'Javascript');

-- =========================================================
-- Javascript - 상 (Hard)
-- =========================================================
INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category) VALUES
('admin',
 '[JS-상] Observable 패턴 구현',
 '구독(subscribe), 알림(notify), 구독 해제(unsubscribe) 기능을 갖춘 `EventEmitter` 클래스를 구현하세요.\n\n**요구사항**\n- on(event, listener): 이벤트 리스너 등록\n- off(event, listener): 이벤트 리스너 제거\n- emit(event, ...args): 이벤트 발생 및 리스너 호출\n- once(event, listener): 한 번만 실행되는 리스너 등록',
 'class EventEmitter {\n  constructor() {\n    this._events = {};\n  }\n\n  on(event, listener) {\n    if (!this._events[event]) this._events[event] = [];\n    this._events[event].push(listener);\n    return this;\n  }\n\n  off(event, listener) {\n    if (!this._events[event]) return this;\n    this._events[event] = this._events[event].filter(l => l !== listener && l._original !== listener);\n    return this;\n  }\n\n  emit(event, ...args) {\n    (this._events[event] || []).forEach(listener => listener(...args));\n    return this;\n  }\n\n  once(event, listener) {\n    const wrapper = (...args) => {\n      listener(...args);\n      this.off(event, wrapper);\n    };\n    wrapper._original = listener;\n    return this.on(event, wrapper);\n  }\n}',
 '_events 객체에 이벤트명을 키로 리스너 배열을 관리하세요. once는 한 번 호출 후 자동으로 off합니다.',
 '상', 'Javascript'),

('admin',
 '[JS-상] 커스텀 Promise 구현',
 'then, catch, finally를 지원하는 `MyPromise` 클래스를 구현하세요. 상태(pending/fulfilled/rejected)를 관리하고 비동기 처리를 지원해야 합니다.\n\n**요구사항**\n- resolve/reject 콜백 지원\n- then 체이닝\n- catch 에러 처리\n- 마이크로태스크 큐 모방 (queueMicrotask 사용)',
 'class MyPromise {\n  constructor(executor) {\n    this.state = "pending";\n    this.value = undefined;\n    this.handlers = [];\n    try {\n      executor(this._resolve.bind(this), this._reject.bind(this));\n    } catch (e) {\n      this._reject(e);\n    }\n  }\n\n  _resolve(value) {\n    if (this.state !== "pending") return;\n    this.state = "fulfilled";\n    this.value = value;\n    this.handlers.forEach(h => this._handle(h));\n  }\n\n  _reject(reason) {\n    if (this.state !== "pending") return;\n    this.state = "rejected";\n    this.value = reason;\n    this.handlers.forEach(h => this._handle(h));\n  }\n\n  _handle({ onFulfilled, onRejected, resolve, reject }) {\n    queueMicrotask(() => {\n      if (this.state === "fulfilled") {\n        try { resolve(onFulfilled ? onFulfilled(this.value) : this.value); }\n        catch (e) { reject(e); }\n      } else {\n        try { resolve(onRejected ? onRejected(this.value) : this.value); }\n        catch (e) { reject(e); }\n      }\n    });\n  }\n\n  then(onFulfilled, onRejected) {\n    return new MyPromise((resolve, reject) => {\n      const handler = { onFulfilled, onRejected, resolve, reject };\n      if (this.state === "pending") this.handlers.push(handler);\n      else this._handle(handler);\n    });\n  }\n\n  catch(onRejected) { return this.then(null, onRejected); }\n  finally(fn) { return this.then(v => { fn(); return v; }, e => { fn(); throw e; }); }\n}',
 'state(pending/fulfilled/rejected)와 handlers 배열을 핵심으로 관리하세요. queueMicrotask로 비동기를 모방합니다.',
 '상', 'Javascript'),

('admin',
 '[JS-상] 가상 DOM diffing',
 '두 가상 DOM 트리를 비교하여 변경 사항(patches)을 반환하는 `diff` 함수를 구현하세요.\n노드는 { type, props, children } 형식이며, patches는 배열로 반환합니다.\n\n**Patch 타입**\n- { type: "REPLACE", newNode }\n- { type: "UPDATE_PROPS", props }\n- { type: "REMOVE" }',
 'function diff(oldNode, newNode) {\n  const patches = [];\n\n  if (!newNode) {\n    patches.push({ type: "REMOVE" });\n    return patches;\n  }\n  if (!oldNode || oldNode.type !== newNode.type) {\n    patches.push({ type: "REPLACE", newNode });\n    return patches;\n  }\n\n  const propChanges = {};\n  const allKeys = new Set([...Object.keys(oldNode.props || {}), ...Object.keys(newNode.props || {})]);\n  for (const key of allKeys) {\n    if ((oldNode.props || {})[key] !== (newNode.props || {})[key]) {\n      propChanges[key] = (newNode.props || {})[key];\n    }\n  }\n  if (Object.keys(propChanges).length > 0) {\n    patches.push({ type: "UPDATE_PROPS", props: propChanges });\n  }\n\n  const maxLen = Math.max((oldNode.children || []).length, (newNode.children || []).length);\n  for (let i = 0; i < maxLen; i++) {\n    patches.push(...diff(oldNode.children?.[i], newNode.children?.[i]));\n  }\n\n  return patches;\n}',
 '재귀로 각 노드를 비교합니다. 타입이 다르면 REPLACE, props가 다르면 UPDATE_PROPS, 자식들은 재귀 비교하세요.',
 '상', 'Javascript'),

('admin',
 '[JS-상] 커리 함수 구현',
 '임의의 인자 수를 가진 함수를 커리화하는 `curry` 함수를 구현하세요.\n\n**사용 예시**\nconst add = (a, b, c) => a + b + c;\nconst curriedAdd = curry(add);\ncurriedAdd(1)(2)(3); // 6\ncurriedAdd(1, 2)(3); // 6\ncurriedAdd(1)(2, 3); // 6',
 'function curry(fn) {\n  return function curried(...args) {\n    if (args.length >= fn.length) {\n      return fn.apply(this, args);\n    }\n    return function (...moreArgs) {\n      return curried.apply(this, args.concat(moreArgs));\n    };\n  };\n}',
 'fn.length로 원본 함수가 필요로 하는 인자 수를 확인하고, 충분한 인자가 모이면 실행하세요.',
 '상', 'Javascript'),

('admin',
 '[JS-상] 비동기 큐 구현',
 '동시에 실행할 수 있는 작업 수를 제한하는 비동기 큐 `AsyncQueue`를 구현하세요.\n\n**요구사항**\n- concurrency: 동시 실행 수 제한\n- add(task): Promise를 반환하는 task 추가\n- 큐가 가득 차면 빈 슬롯이 생길 때까지 대기',
 'class AsyncQueue {\n  constructor(concurrency) {\n    this.concurrency = concurrency;\n    this.running = 0;\n    this.queue = [];\n  }\n\n  add(task) {\n    return new Promise((resolve, reject) => {\n      this.queue.push({ task, resolve, reject });\n      this._run();\n    });\n  }\n\n  _run() {\n    while (this.running < this.concurrency && this.queue.length > 0) {\n      const { task, resolve, reject } = this.queue.shift();\n      this.running++;\n      Promise.resolve(task())\n        .then(resolve)\n        .catch(reject)\n        .finally(() => {\n          this.running--;\n          this._run();\n        });\n    }\n  }\n}',
 'running 카운터로 현재 실행 수를 추적하고, 작업 완료 시 _run()을 재호출하여 대기 중인 작업을 처리하세요.',
 '상', 'Javascript'),

('admin',
 '[JS-상] 반응형 상태 관리 구현',
 'Vue의 reactive처럼 객체의 변경을 감지하고 구독자에게 알리는 `reactive` 함수를 Proxy를 사용해 구현하세요.\n\n**사용 예시**\nconst state = reactive({ count: 0 });\nwatch(state, "count", (newVal) => console.log("changed:", newVal));\nstate.count++; // "changed: 1" 출력',
 'function reactive(target) {\n  const subscribers = {};\n\n  function watch(obj, key, callback) {\n    if (!subscribers[key]) subscribers[key] = [];\n    subscribers[key].push(callback);\n  }\n\n  const proxy = new Proxy(target, {\n    set(obj, key, value) {\n      const old = obj[key];\n      obj[key] = value;\n      if (old !== value && subscribers[key]) {\n        subscribers[key].forEach(cb => cb(value, old));\n      }\n      return true;\n    }\n  });\n\n  proxy.watch = watch.bind(null, proxy);\n  return proxy;\n}',
 'Proxy의 set 트랩에서 값 변경을 감지하고, 해당 키의 구독자 콜백들을 호출하세요.',
 '상', 'Javascript'),

('admin',
 '[JS-상] 제너레이터 기반 무한 수열',
 '제너레이터 함수를 사용해 피보나치 수열을 무한히 생성하는 `fibGenerator`를 구현하고, 처음 n개를 배열로 반환하는 `take(gen, n)` 함수를 작성하세요.\n\n**사용 예시**\nconst fib = fibGenerator();\ntake(fib, 8); // [0, 1, 1, 2, 3, 5, 8, 13]',
 'function* fibGenerator() {\n  let [a, b] = [0, 1];\n  while (true) {\n    yield a;\n    [a, b] = [b, a + b];\n  }\n}\n\nfunction take(gen, n) {\n  const result = [];\n  for (let i = 0; i < n; i++) {\n    result.push(gen.next().value);\n  }\n  return result;\n}',
 '제너레이터는 function* 키워드와 yield로 정의합니다. while(true) 루프를 사용해 무한 수열을 만드세요.',
 '상', 'Javascript'),

('admin',
 '[JS-상] 불변 상태 업데이트 유틸',
 '중첩 객체를 불변(immutable)으로 업데이트하는 `update` 함수를 구현하세요. 경로(path)와 값(value)을 받아 새 객체를 반환합니다.\n\n**사용 예시**\nconst state = { user: { profile: { age: 25 } } };\nconst newState = update(state, ["user", "profile", "age"], 26);\nconsole.log(state.user.profile.age); // 25 (원본 불변)\nconsole.log(newState.user.profile.age); // 26',
 'function update(obj, path, value) {\n  if (path.length === 0) return value;\n  const [key, ...rest] = path;\n  return {\n    ...obj,\n    [key]: update(obj[key], rest, value)\n  };\n}',
 '재귀로 경로를 따라 내려가면서 spread operator로 새 객체를 만들어 원본을 보존하세요.',
 '상', 'Javascript');

-- =========================================================
-- Python - 하 (Easy)
-- =========================================================
INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category) VALUES
('admin',
 '[Python-하] 두 수의 합',
 '두 숫자를 받아 합을 반환하는 함수 `add`를 작성하세요.\n\n**입력 예시**\nadd(3, 7)\n\n**출력 예시**\n10',
 'def add(a, b):\n    return a + b',
 '+ 연산자로 두 인자를 더하세요.',
 '하', 'Python'),

('admin',
 '[Python-하] 리스트 최댓값',
 '리스트를 받아 최댓값을 반환하는 함수 `find_max`를 작성하세요.\n\n**입력 예시**\nfind_max([3, 1, 4, 1, 5, 9, 2, 6])\n\n**출력 예시**\n9',
 'def find_max(lst):\n    return max(lst)',
 'Python 내장 함수 max()를 활용하거나 직접 반복문으로 구현하세요.',
 '하', 'Python'),

('admin',
 '[Python-하] 문자열 뒤집기',
 '문자열을 뒤집어 반환하는 함수 `reverse_str`을 작성하세요.\n\n**입력 예시**\nreverse_str("hello")\n\n**출력 예시**\n"olleh"',
 'def reverse_str(s):\n    return s[::-1]',
 '파이썬 슬라이싱 s[::-1]을 활용하세요.',
 '하', 'Python'),

('admin',
 '[Python-하] 홀짝 판별',
 '숫자 n을 받아 짝수이면 True, 홀수이면 False를 반환하는 함수 `is_even`을 작성하세요.\n\n**입력 예시**\nis_even(4)\n\n**출력 예시**\nTrue',
 'def is_even(n):\n    return n % 2 == 0',
 '% 2 연산으로 나머지를 확인하세요.',
 '하', 'Python'),

('admin',
 '[Python-하] 팩토리얼',
 '양의 정수 n을 받아 n! 을 반환하는 함수 `factorial`을 작성하세요.\n\n**입력 예시**\nfactorial(5)\n\n**출력 예시**\n120',
 'def factorial(n):\n    result = 1\n    for i in range(2, n + 1):\n        result *= i\n    return result',
 '1부터 n까지 반복하며 곱하거나, math.factorial()을 사용하세요.',
 '하', 'Python'),

('admin',
 '[Python-하] FizzBuzz 리스트',
 '1부터 n까지의 FizzBuzz 결과를 리스트로 반환하는 `fizz_buzz`를 작성하세요.\n3의 배수는 "Fizz", 5의 배수는 "Buzz", 15의 배수는 "FizzBuzz".\n\n**입력 예시**\nfizz_buzz(15)\n\n**출력 예시**\n["1","2","Fizz","4","Buzz",...,"FizzBuzz"]',
 'def fizz_buzz(n):\n    result = []\n    for i in range(1, n + 1):\n        if i % 15 == 0:\n            result.append("FizzBuzz")\n        elif i % 3 == 0:\n            result.append("Fizz")\n        elif i % 5 == 0:\n            result.append("Buzz")\n        else:\n            result.append(str(i))\n    return result',
 '15의 배수 조건을 먼저 검사하세요.',
 '하', 'Python'),

('admin',
 '[Python-하] 단어 빈도수 계산',
 '문자열을 받아 각 단어의 등장 횟수를 딕셔너리로 반환하는 `word_count`를 작성하세요.\n\n**입력 예시**\nword_count("hello world hello")\n\n**출력 예시**\n{"hello": 2, "world": 1}',
 'def word_count(text):\n    counts = {}\n    for word in text.split():\n        counts[word] = counts.get(word, 0) + 1\n    return counts',
 'dict.get(key, 0) 을 사용하면 키가 없어도 기본값 0으로 처리할 수 있습니다.',
 '하', 'Python'),

('admin',
 '[Python-하] 리스트 중복 제거',
 '리스트에서 중복을 제거하고 원래 순서를 유지한 새 리스트를 반환하는 `remove_dup`을 작성하세요.\n\n**입력 예시**\nremove_dup([1, 3, 2, 3, 1, 4])\n\n**출력 예시**\n[1, 3, 2, 4]',
 'def remove_dup(lst):\n    seen = set()\n    result = []\n    for x in lst:\n        if x not in seen:\n            seen.add(x)\n            result.append(x)\n    return result',
 'set으로 이미 본 값을 추적하면서 처음 보는 값만 결과에 추가하세요.',
 '하', 'Python');

-- =========================================================
-- Python - 중 (Medium)
-- =========================================================
INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category) VALUES
('admin',
 '[Python-중] 에라토스테네스의 체',
 'n 이하의 모든 소수를 리스트로 반환하는 `sieve`를 에라토스테네스의 체 알고리즘으로 구현하세요.\n\n**입력 예시**\nsieve(30)\n\n**출력 예시**\n[2, 3, 5, 7, 11, 13, 17, 19, 23, 29]',
 'def sieve(n):\n    is_prime = [True] * (n + 1)\n    is_prime[0] = is_prime[1] = False\n    for i in range(2, int(n**0.5) + 1):\n        if is_prime[i]:\n            for j in range(i*i, n+1, i):\n                is_prime[j] = False\n    return [i for i in range(2, n+1) if is_prime[i]]',
 'bool 리스트로 소수 여부를 관리하고, i*i부터 i의 배수를 모두 False로 표시하세요.',
 '중', 'Python'),

('admin',
 '[Python-중] 이진 탐색',
 '정렬된 리스트에서 target을 이진 탐색으로 찾아 인덱스를 반환하는 `binary_search`를 작성하세요. 없으면 -1을 반환합니다.\n\n**입력 예시**\nbinary_search([1, 3, 5, 7, 9, 11], 7)\n\n**출력 예시**\n3',
 'def binary_search(lst, target):\n    left, right = 0, len(lst) - 1\n    while left <= right:\n        mid = (left + right) // 2\n        if lst[mid] == target:\n            return mid\n        elif lst[mid] < target:\n            left = mid + 1\n        else:\n            right = mid - 1\n    return -1',
 'left, right 두 포인터를 사용하고 mid 값과 target을 비교해 범위를 좁히세요.',
 '중', 'Python'),

('admin',
 '[Python-중] 피보나치 메모이제이션',
 '메모이제이션을 사용해 n번째 피보나치 수를 반환하는 `fib`를 작성하세요.\n\n**입력 예시**\nfib(50)\n\n**출력 예시**\n12586269025',
 'def fib(n, memo={}):\n    if n in memo:\n        return memo[n]\n    if n <= 1:\n        return n\n    memo[n] = fib(n-1, memo) + fib(n-2, memo)\n    return memo[n]\n\n# 또는 functools.lru_cache 사용:\nfrom functools import lru_cache\n\n@lru_cache(maxsize=None)\ndef fib_cached(n):\n    if n <= 1:\n        return n\n    return fib_cached(n-1) + fib_cached(n-2)',
 '딕셔너리나 @lru_cache 데코레이터로 중간 결과를 저장하면 지수 시간을 선형으로 줄일 수 있습니다.',
 '중', 'Python'),

('admin',
 '[Python-중] 행렬 곱셈',
 '두 2D 리스트(행렬)를 받아 행렬 곱셈 결과를 반환하는 `matrix_multiply`를 작성하세요.\n입력이 유효하지 않으면 None을 반환합니다.\n\n**입력 예시**\nA = [[1,2],[3,4]], B = [[5,6],[7,8]]\nmatrix_multiply(A, B)\n\n**출력 예시**\n[[19, 22], [43, 50]]',
 'def matrix_multiply(A, B):\n    ra, ca = len(A), len(A[0])\n    rb, cb = len(B), len(B[0])\n    if ca != rb:\n        return None\n    C = [[0]*cb for _ in range(ra)]\n    for i in range(ra):\n        for j in range(cb):\n            for k in range(ca):\n                C[i][j] += A[i][k] * B[k][j]\n    return C',
 'A가 m×k, B가 k×n일 때 C는 m×n. C[i][j] = sum(A[i][k]*B[k][j]).',
 '중', 'Python'),

('admin',
 '[Python-중] 스택으로 괄호 검사',
 '문자열에서 괄호 (), [], {}가 올바르게 열고 닫혔는지 검사하는 `is_valid_brackets`를 작성하세요.\n\n**입력 예시**\nis_valid_brackets("{[()]}")\n\n**출력 예시**\nTrue',
 'def is_valid_brackets(s):\n    stack = []\n    mapping = {")": "(", "]": "[", "}": "{"}\n    for ch in s:\n        if ch in "([{":\n            stack.append(ch)\n        elif ch in ")]}":\n            if not stack or stack[-1] != mapping[ch]:\n                return False\n            stack.pop()\n    return len(stack) == 0',
 '스택에 여는 괄호를 push하고, 닫는 괄호를 만나면 스택 최상단과 매핑을 비교하세요.',
 '중', 'Python'),

('admin',
 '[Python-중] 두 리스트의 교집합',
 '두 리스트의 교집합(중복 포함)을 반환하는 `intersect`를 작성하세요.\n\n**입력 예시**\nintersect([1, 2, 2, 1], [2, 2])\n\n**출력 예시**\n[2, 2]',
 'from collections import Counter\n\ndef intersect(nums1, nums2):\n    c1 = Counter(nums1)\n    c2 = Counter(nums2)\n    result = []\n    for num in c1:\n        if num in c2:\n            result.extend([num] * min(c1[num], c2[num]))\n    return sorted(result)',
 'Counter로 각 원소의 빈도를 세고, min(빈도1, 빈도2)만큼 결과에 추가하세요.',
 '중', 'Python'),

('admin',
 '[Python-중] 올바른 괄호 생성',
 'n 쌍의 괄호로 만들 수 있는 모든 올바른 조합을 반환하는 `generate_parentheses`를 작성하세요.\n\n**입력 예시**\ngenerate_parentheses(3)\n\n**출력 예시**\n["((()))","(()())","(())()","()(())","()()()"]',
 'def generate_parentheses(n):\n    result = []\n    def backtrack(s, open_cnt, close_cnt):\n        if len(s) == 2 * n:\n            result.append(s)\n            return\n        if open_cnt < n:\n            backtrack(s + "(", open_cnt + 1, close_cnt)\n        if close_cnt < open_cnt:\n            backtrack(s + ")", open_cnt, close_cnt + 1)\n    backtrack("", 0, 0)\n    return result',
 '백트래킹으로 구현합니다. 여는 괄호는 n개 미만일 때, 닫는 괄호는 여는 괄호보다 적을 때 추가하세요.',
 '중', 'Python'),

('admin',
 '[Python-중] 숫자 배열에서 두 수의 합',
 '정수 배열 nums와 target이 주어질 때, 합이 target인 두 원소의 인덱스를 반환하는 `two_sum`을 작성하세요. 정확히 하나의 답이 존재합니다.\n\n**입력 예시**\ntwo_sum([2, 7, 11, 15], 9)\n\n**출력 예시**\n[0, 1]',
 'def two_sum(nums, target):\n    seen = {}  # value -> index\n    for i, num in enumerate(nums):\n        complement = target - num\n        if complement in seen:\n            return [seen[complement], i]\n        seen[num] = i\n    return []',
 '해시맵(딕셔너리)에 값→인덱스를 저장하면 O(n)으로 풀 수 있습니다.',
 '중', 'Python');

-- =========================================================
-- Python - 상 (Hard)
-- =========================================================
INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category) VALUES
('admin',
 '[Python-상] 그래프 BFS/DFS',
 '인접 리스트로 주어진 그래프에서 BFS와 DFS 순회 결과를 각각 반환하는 `bfs`와 `dfs`를 구현하세요.\n\n**입력 예시**\ngraph = {0:[1,2], 1:[0,3], 2:[0,4], 3:[1], 4:[2]}\nbfs(graph, 0) → [0, 1, 2, 3, 4]\ndfs(graph, 0) → [0, 1, 3, 2, 4]',
 'from collections import deque\n\ndef bfs(graph, start):\n    visited = set([start])\n    queue = deque([start])\n    result = []\n    while queue:\n        node = queue.popleft()\n        result.append(node)\n        for neighbor in graph[node]:\n            if neighbor not in visited:\n                visited.add(neighbor)\n                queue.append(neighbor)\n    return result\n\ndef dfs(graph, start):\n    visited = set()\n    result = []\n    def _dfs(node):\n        visited.add(node)\n        result.append(node)\n        for neighbor in graph[node]:\n            if neighbor not in visited:\n                _dfs(neighbor)\n    _dfs(start)\n    return result',
 'BFS는 deque(큐)를 사용하고, DFS는 재귀 또는 스택을 사용합니다.',
 '상', 'Python'),

('admin',
 '[Python-상] 최장 증가 부분 수열 (LIS)',
 '정수 배열에서 최장 증가 부분 수열의 길이를 O(n log n)으로 반환하는 `lis_length`를 작성하세요.\n\n**입력 예시**\nlis_length([10, 9, 2, 5, 3, 7, 101, 18])\n\n**출력 예시**\n4',
 'import bisect\n\ndef lis_length(nums):\n    tails = []\n    for num in nums:\n        pos = bisect.bisect_left(tails, num)\n        if pos == len(tails):\n            tails.append(num)\n        else:\n            tails[pos] = num\n    return len(tails)',
 'bisect_left로 tails 배열의 삽입 위치를 찾고, 크기를 관리하면 O(n log n)입니다.',
 '상', 'Python'),

('admin',
 '[Python-상] 다익스트라 최단 경로',
 '가중치 방향 그래프에서 시작 노드부터 모든 노드까지의 최단 거리를 구하는 `dijkstra`를 작성하세요.\n\n**입력 예시**\ngraph = {0:[(1,4),(2,1)], 1:[(3,1)], 2:[(1,2),(3,5)], 3:[(4,3)], 4:[]}, start=0\n\n**출력 예시**\n{0:0, 1:3, 2:1, 3:4, 4:7}',
 'import heapq\n\ndef dijkstra(graph, start):\n    dist = {node: float("inf") for node in graph}\n    dist[start] = 0\n    pq = [(0, start)]  # (거리, 노드)\n    while pq:\n        d, u = heapq.heappop(pq)\n        if d > dist[u]:\n            continue\n        for v, w in graph[u]:\n            if dist[u] + w < dist[v]:\n                dist[v] = dist[u] + w\n                heapq.heappush(pq, (dist[v], v))\n    return dist',
 'heapq(최솟힙)를 사용해 최소 거리 노드를 먼저 처리하세요.',
 '상', 'Python'),

('admin',
 '[Python-상] 편집 거리 (Edit Distance)',
 '두 문자열 word1, word2 사이의 편집 거리(삽입/삭제/교체 각 1회 비용)를 동적 프로그래밍으로 구하는 `edit_distance`를 작성하세요.\n\n**입력 예시**\nedit_distance("horse", "ros")\n\n**출력 예시**\n3',
 'def edit_distance(word1, word2):\n    m, n = len(word1), len(word2)\n    dp = [[0]*(n+1) for _ in range(m+1)]\n    for i in range(m+1):\n        dp[i][0] = i\n    for j in range(n+1):\n        dp[0][j] = j\n    for i in range(1, m+1):\n        for j in range(1, n+1):\n            if word1[i-1] == word2[j-1]:\n                dp[i][j] = dp[i-1][j-1]\n            else:\n                dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])\n    return dp[m][n]',
 'dp[i][j]는 word1[0..i-1]을 word2[0..j-1]로 변환하는 최소 비용입니다.',
 '상', 'Python'),

('admin',
 '[Python-상] 유니온 파인드 (Union-Find)',
 'n개의 노드와 엣지 리스트로 연결 컴포넌트 수를 구하는 `count_components`를 Union-Find로 구현하세요.\n\n**입력 예시**\ncount_components(5, [[0,1],[1,2],[3,4]])\n\n**출력 예시**\n2',
 'def count_components(n, edges):\n    parent = list(range(n))\n    rank = [0] * n\n\n    def find(x):\n        while parent[x] != x:\n            parent[x] = parent[parent[x]]  # 경로 압축\n            x = parent[x]\n        return x\n\n    def union(x, y):\n        px, py = find(x), find(y)\n        if px == py:\n            return False\n        if rank[px] < rank[py]:\n            px, py = py, px\n        parent[py] = px\n        if rank[px] == rank[py]:\n            rank[px] += 1\n        return True\n\n    components = n\n    for u, v in edges:\n        if union(u, v):\n            components -= 1\n    return components',
 'find에 경로 압축, union에 rank 기반 합치기를 적용하면 거의 O(1) 연산입니다.',
 '상', 'Python'),

('admin',
 '[Python-상] 위상 정렬',
 '방향 그래프의 위상 정렬 결과를 반환하는 `topological_sort`를 Kahn 알고리즘으로 구현하세요. 사이클이 있으면 빈 리스트를 반환합니다.\n\n**입력 예시**\ntopological_sort(6, [[5,2],[5,0],[4,0],[4,1],[2,3],[3,1]])\n\n**출력 예시**\n[4, 5, 0, 2, 3, 1] (여러 정답 중 하나)',
 'from collections import deque\n\ndef topological_sort(n, edges):\n    graph = [[] for _ in range(n)]\n    in_degree = [0] * n\n    for u, v in edges:\n        graph[u].append(v)\n        in_degree[v] += 1\n\n    queue = deque(i for i in range(n) if in_degree[i] == 0)\n    result = []\n    while queue:\n        node = queue.popleft()\n        result.append(node)\n        for neighbor in graph[node]:\n            in_degree[neighbor] -= 1\n            if in_degree[neighbor] == 0:\n                queue.append(neighbor)\n\n    return result if len(result) == n else []',
 '진입 차수(in-degree)가 0인 노드부터 시작하고, 처리 후 이웃의 진입 차수를 줄여가세요.',
 '상', 'Python'),

('admin',
 '[Python-상] 슬라이딩 윈도우 최대 부분합',
 '정수 배열 nums에서 길이 k인 연속 부분 배열의 최대 합을 반환하는 `max_subarray_sum`을 슬라이딩 윈도우로 구현하세요.\n\n**입력 예시**\nmax_subarray_sum([2, 1, 5, 1, 3, 2], 3)\n\n**출력 예시**\n9',
 'def max_subarray_sum(nums, k):\n    if len(nums) < k:\n        return 0\n    window_sum = sum(nums[:k])\n    max_sum = window_sum\n    for i in range(k, len(nums)):\n        window_sum += nums[i] - nums[i - k]\n        max_sum = max(max_sum, window_sum)\n    return max_sum',
 '처음 k개의 합을 구한 뒤, 한 칸씩 이동하며 새 원소를 더하고 빠진 원소를 빼세요.',
 '상', 'Python'),

('admin',
 '[Python-상] N-Queen 문제',
 'N×N 체스판에 N개의 퀸을 서로 공격하지 못하도록 배치하는 모든 경우의 수를 반환하는 `solve_n_queens`를 작성하세요.\n\n**입력 예시**\nsolve_n_queens(4)\n\n**출력 예시**\n2',
 'def solve_n_queens(n):\n    count = 0\n    cols = set()\n    diag1 = set()  # row - col\n    diag2 = set()  # row + col\n\n    def backtrack(row):\n        nonlocal count\n        if row == n:\n            count += 1\n            return\n        for col in range(n):\n            if col in cols or (row-col) in diag1 or (row+col) in diag2:\n                continue\n            cols.add(col)\n            diag1.add(row-col)\n            diag2.add(row+col)\n            backtrack(row + 1)\n            cols.remove(col)\n            diag1.remove(row-col)\n            diag2.remove(row+col)\n\n    backtrack(0)\n    return count',
 '세 집합(열, 두 대각선)으로 충돌을 O(1)에 검사하고, 백트래킹으로 모든 경우를 탐색하세요.',
 '상', 'Python');
