DROP TABLE problems;
CREATE TABLE problems(
	problem_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(30) NOT NULL,
    title VARCHAR(100) NOT NULL,
    content TEXT,
    answer TEXT,
    difficulty ENUM ('상', '중', '하') NOT NULL,
    category VARCHAR(20) NOT NULL,
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO problems (user_id, title, content, answer, difficulty, category)
VALUES
('admin', '배열의 합 구하기', '정수 배열이 주어졌을 때, 배열의 모든 원소의 합을 구하는 함수를 작성하시오.', 'public int sumArray(int[] numbers) {
    int sum = 0;
    for (int num : numbers) {
        sum += num;
    }
    return sum;
}', '하', 'JAVA'),
('admin', '문자열 뒤집기', '문자열을 거꾸로 반환하는 함수를 작성하시오.', 'public String reverseString(String input) {
    StringBuilder sb = new StringBuilder(input);
    return sb.reverse().toString();
}', '하', 'JAVA'),
('admin', '최대 공약수 구하기', '두 정수를 입력받아 최대 공약수를 반환하는 함수를 작성하시오', 'public int gcd(int a, int b) {
    while (b != 0) {
        int temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}','중', 'JAVA'),
('admin', '회문 판별하기', '문자열이 앞으로 읽어도, 뒤로 읽어도 같은지 판별하는 함수를 작성하시오.','public boolean isPalindrome(String s) {
    int left = 0, right = s.length() - 1;
    while (left < right) {
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}', '중', 'JAVA'),
('admin', '괄호 검사하기', '괄호로만 이루어진 문자열이 주어질 때, 모든 괄호가 올바르게 짝을 이루었는지 검사하는 함수를 작성하시오.', "public boolean isValidParentheses(String s) {
    java.util.Stack<Character> stack = new java.util.Stack<>();
    for (char ch : s.toCharArray()) {
        if (ch == '(') {
            stack.push(ch);
        } else if (ch == ')') {
            if (stack.isEmpty()) {
                return false;
            }
            stack.pop();
        }
    }
    return stack.isEmpty();
}",'상', 'JAVA'),
('admin', '이진 트리 탐색하기', '이진 탐색 트리의 루트 노드와 찾으려는 값을 입력받아, 해당 값이 트리에 존재하는지 확인하는 함수를 작성하시오.
TreeNode 클래스는 다음과 같이 정의되어 있다.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}', 'public boolean searchBST(TreeNode root, int val) {
    if (root == null) return false;
    if (root.val == val) return true;
    else if (val < root.val) return searchBST(root.left, val);
    else return searchBST(root.right, val);
}','상', 'JAVA');

SELECT * FROM problems ORDER BY category, difficulty;