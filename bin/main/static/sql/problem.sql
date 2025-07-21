DROP TABLE problems;
CREATE TABLE problems(
	problem_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(30) NOT NULL,
    title VARCHAR(100) NOT NULL,
    content TEXT,
    answer TEXT,
    hint TEXT,
    difficulty ENUM ('상', '중', '하') NOT NULL,
    category VARCHAR(20) NOT NULL,
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category)
VALUES
('admin', '배열의 합 구하기', '정수 배열이 주어졌을 때, 배열의 모든 원소의 합을 구하는 함수를 작성하시오.', 'public int sumArray(int[] numbers) {
    int sum = 0;
    for (int num : numbers) {
        sum += num;
    }
    return sum;
}', '향상된 for문을 사용해보세요.', '하', 'JAVA'),
('admin', '문자열 뒤집기', '문자열을 거꾸로 반환하는 함수를 작성하시오.', 'public String reverseString(String input) {
    StringBuilder sb = new StringBuilder(input);
    return sb.reverse().toString();
}', 'StringBuilder 관련 메소드를 사용해보세요.', '하', 'JAVA'),
('admin', '최대 공약수 구하기', '두 정수를 입력받아 최대 공약수를 반환하는 함수를 작성하시오', 'public int gcd(int a, int b) {
    while (b != 0) {
        int temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}', '유클리드 호제법을 사용해보세요.', '중', 'JAVA'),
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
}','앞과 뒤에서 동시에 이동하면서 비교해보세요.', '중', 'JAVA'),
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
}", '괄호 하나를 만날 때마다 어딘가에 저장해보세요.', '상', 'JAVA'),
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
}', '재귀 호출을 이용해보세요.', '상', 'JAVA');


INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category)
VALUES
('admin', '배열의 합 구하기', '정수로 이루어진 리스트가 주어졌을 때, 리스트에 있는 모든 숫자의 합을 반환하는 함수를 작성하시오.', 'def sum_list(numbers):
    total = 0
    for num in numbers:
        total += num
    return total
', 'for문을 이용해보세요.', '하', 'Python'),
('admin', '문자열 뒤집기', '문자열을 입력받아, 뒤집은 문자열을 반환하는 함수를 작성하시오.', 'def reverse_string(s):
    return s[::-1]
', '파이썬 배열 기능을 이용해보세요.', '하', 'Python'),
('admin', '최대 공약수 구하기', '두 개의 정수를 입력받아, 그들의 최대공약수를 반환하는 함수를 작성하시오.', 'def gcd(a, b):
    while b != 0:
        a, b = b, a % b
    return a
', '유클리드 호제법을 이용해보세요.', '중', 'Python'),
('admin', '회문 판별하기', ' 문자열이 앞으로 읽어도, 뒤로 읽어도 같은지 판별하는 함수를 작성하시오.','def is_palindrome(s):
    return s == s[::-1]
', '파이썬 배열의 기능 중 역순으로 찾는 기능을 이용해보세요.', '중', 'Python'),
('admin', '괄호 검사하기', '괄호로만 이루어진 문자열이 주어질 때, 모든 괄호가 올바르게 짝을 이루었는지 검사하는 함수를 작성하시오.', "def is_valid_parentheses(s):
    stack = []
    for ch in s:
        if ch == '(':
            stack.append(ch)
        elif ch == ')':
            if not stack:
                return False
            stack.pop()
    return len(stack) == 0
", '괄호를 찾을 때 마다 어딘가에 저장해보세요.', '상', 'Python'),
('admin', '이진 트리 탐색하기', '이진 탐색 트리의 루트 노드와 찾으려는 값을 입력받아, 해당 값이 트리에 존재하는지 확인하는 함수를 작성하시오.
TreeNode 클래스는 다음과 같이 정의되어 있다.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
', 'def search_bst(root, val):
    if root is None:
        return False
    if root.val == val:
        return True
    elif val < root.val:
        return search_bst(root.left, val)
    else:
        return search_bst(root.right, val)
','재귀 호출을 사용해보세요.', '상', 'Python');

INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category)
VALUES
('admin', '배열의 평균 구하기', '숫자로 이루어진 배열이 주어질 때, 배열의 평균을 반환하는 함수를 작성하세요.', 'function getAverage(arr) {
  let sum = 0;
  for (let num of arr) {
    sum += num;
  }
  return sum / arr.length;
}', 'for문을 사용해보세요.', '하', 'Javascript'),
('admin', '문자 갯수 세기', '문자열과 문자가 주어졌을 때, 해당 문자가 문자열 내에 몇 번 등장하는지 반환하는 함수를 작성하세요.', 'function countChar(str, target) {
  let count = 0;
  for (let ch of str) {
    if (ch === target) count++;
  }
  return count;
}', '향상된 for문을 이용해보세요.', '하', 'Javascript'),
('admin', '배열 중복값 제거하기', '숫자로 이루어진 배열이 주어졌을 때, 중복을 제거한 새 배열을 반환하는 함수를 작성하세요.', 'function uniqueArray(arr) {
  return [...new Set(arr)];
}','중복을 허용하지 않는 컬렉션 타입을 생각해보세요.', '중', 'Javascript'),
('admin', '첫 글자 바꾸기', ' 문자열 문장을 입력받아 각 단어의 첫 글자를 대문자로 변환하는 함수를 작성하세요.','function toTitleCase(sentence) {
  return sentence
    .split(" ")
    .map(word => word.charAt(0).toUpperCase() + word.slice(1))
    .join(" ");
}','먼저 각 단어를 분리한 뒤 생각해보세요.', '중', 'Javascript'),
('admin', '괄호 검사하기', '괄호 문자열이 올바른지 검사하는 함수를 작성하세요. 괄호는 (), [], {} 세 종류가 포함될 수 있습니다.', "function isValidBrackets(s) {
  const stack = [];
  const pairs = { ')': '(', ']': '[', '}': '{' };

  for (let ch of s) {
    if ('([{'.includes(ch)) {
      stack.push(ch);
    } else if (')]}'.includes(ch)) {
      if (stack.pop() !== pairs[ch]) return false;
    }
  }

  return stack.length === 0;
}", '값이 쌓이는 타입의 컬렉션 타입을 생각해보세요.',  '상', 'Javascript'),
('admin', '타임아웃 다루기', '사용자가 빠르게 여러 번 호출하는 함수에 대해, 마지막 호출만 일정 시간 이후에 실행되도록 하는 함수를 작성하세요.
예시)
const debouncedLog = debounce(() => console.log("Hello"), 1000);
debouncedLog(); debouncedLog(); debouncedLog();
', 'function debounce(func, delay) {
  let timeoutId;
  return function (...args) {
    clearTimeout(timeoutId);
    timeoutId = setTimeout(() => func.apply(this, args), delay);
  };
}','timeout을 멈추고 설정해보세요.', '상', 'Javascript');


SELECT * FROM problems ORDER BY category, difficulty;