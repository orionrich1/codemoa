-- ============================================================
-- Kotlin AI 코딩 문제 시드 데이터 (난이도별 10개씩, 총 30개)
-- ============================================================

-- =========================================================
-- Kotlin - 하 (Easy)
-- =========================================================
INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category) VALUES
('admin',
 '[Kotlin-하] 두 수의 합',
 '두 정수를 받아 합을 반환하는 함수 `add`를 작성하세요.\n\n**입력 예시**\nadd(3, 7)\n\n**출력 예시**\n10',
 'fun add(a: Int, b: Int): Int = a + b',
 '단순히 + 연산자로 두 인자를 더하세요.',
 '하', 'Kotlin'),

('admin',
 '[Kotlin-하] 홀수 짝수 판별',
 '정수 n을 받아 짝수이면 "even", 홀수이면 "odd"를 반환하는 함수 `checkParity`를 작성하세요.\n\n**입력 예시**\ncheckParity(4)\n\n**출력 예시**\n"even"',
 'fun checkParity(n: Int): String = if (n % 2 == 0) "even" else "odd"',
 'if 식(expression)을 활용하면 한 줄로 작성 가능합니다.',
 '하', 'Kotlin'),

('admin',
 '[Kotlin-하] 1부터 N까지의 합',
 '양의 정수 n을 받아 1부터 n까지의 합을 반환하는 함수 `sumToN`을 작성하세요.\n\n**입력 예시**\nsumToN(5)\n\n**출력 예시**\n15',
 'fun sumToN(n: Int): Int = (1..n).sum()\n\n// 또는\nfun sumToN2(n: Int): Int = n * (n + 1) / 2',
 '(1..n) 범위와 .sum() 확장 함수를 활용하세요.',
 '하', 'Kotlin'),

('admin',
 '[Kotlin-하] 문자열 뒤집기',
 '문자열 str을 뒤집어 반환하는 함수 `reverseStr`을 작성하세요.\n\n**입력 예시**\nreverseStr("hello")\n\n**출력 예시**\n"olleh"',
 'fun reverseStr(str: String): String = str.reversed()',
 'Kotlin String의 확장 함수 reversed()를 활용하세요.',
 '하', 'Kotlin'),

('admin',
 '[Kotlin-하] 리스트 최댓값',
 '정수 리스트를 받아 최댓값을 반환하는 함수 `findMax`를 작성하세요. 리스트가 비어 있으면 null을 반환합니다.\n\n**입력 예시**\nfindMax(listOf(3, 1, 4, 1, 5, 9, 2, 6))\n\n**출력 예시**\n9',
 'fun findMax(list: List<Int>): Int? = list.maxOrNull()',
 'maxOrNull() 확장 함수를 사용하세요.',
 '하', 'Kotlin'),

('admin',
 '[Kotlin-하] 팩토리얼',
 '양의 정수 n을 받아 n!을 반환하는 함수 `factorial`을 작성하세요. (n ≤ 12)\n\n**입력 예시**\nfactorial(5)\n\n**출력 예시**\n120',
 'fun factorial(n: Int): Long {\n    var result = 1L\n    for (i in 2..n) result *= i\n    return result\n}',
 '(2..n) 범위로 반복하며 곱하세요.',
 '하', 'Kotlin'),

('admin',
 '[Kotlin-하] FizzBuzz 리스트',
 '1부터 n까지의 FizzBuzz 결과를 리스트로 반환하는 `fizzBuzz`를 작성하세요.\n3의 배수 "Fizz", 5의 배수 "Buzz", 15의 배수 "FizzBuzz".\n\n**입력 예시**\nfizzBuzz(15)\n\n**출력 예시**\n["1","2","Fizz","4","Buzz",...,"FizzBuzz"]',
 'fun fizzBuzz(n: Int): List<String> = (1..n).map { i ->\n    when {\n        i % 15 == 0 -> "FizzBuzz"\n        i % 3 == 0  -> "Fizz"\n        i % 5 == 0  -> "Buzz"\n        else        -> i.toString()\n    }\n}',
 'when 식과 map 고차함수를 조합하면 간결하게 작성할 수 있습니다.',
 '하', 'Kotlin'),

('admin',
 '[Kotlin-하] 리스트 중복 제거',
 '리스트에서 중복을 제거하고 순서를 유지한 새 리스트를 반환하는 `removeDup`을 작성하세요.\n\n**입력 예시**\nremoveDup(listOf(1, 3, 2, 3, 1, 4))\n\n**출력 예시**\n[1, 3, 2, 4]',
 'fun removeDup(list: List<Int>): List<Int> = list.distinct()',
 'distinct() 확장 함수는 순서를 유지하며 중복을 제거합니다.',
 '하', 'Kotlin'),

('admin',
 '[Kotlin-하] 단어 빈도수',
 '문자열을 받아 각 단어의 등장 횟수를 Map으로 반환하는 `wordCount`를 작성하세요.\n\n**입력 예시**\nwordCount("hello world hello")\n\n**출력 예시**\n{hello=2, world=1}',
 'fun wordCount(text: String): Map<String, Int> =\n    text.split(" ").groupingBy { it }.eachCount()',
 'split(" ")으로 단어 분리 후 groupingBy { it }.eachCount()를 사용하세요.',
 '하', 'Kotlin'),

('admin',
 '[Kotlin-하] 구구단 출력',
 '정수 n(2~9)을 받아 n단 구구단 결과를 문자열 리스트로 반환하는 `gugu`를 작성하세요.\n\n**입력 예시**\ngugu(3)\n\n**출력 예시**\n["3 x 1 = 3", "3 x 2 = 6", ..., "3 x 9 = 27"]',
 'fun gugu(n: Int): List<String> = (1..9).map { i -> "$n x $i = ${n * i}" }',
 '(1..9) 범위에 map을 적용하고 문자열 템플릿($변수)을 활용하세요.',
 '하', 'Kotlin');

-- =========================================================
-- Kotlin - 중 (Medium)
-- =========================================================
INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category) VALUES
('admin',
 '[Kotlin-중] 피보나치 수열',
 'n번째 피보나치 수를 반환하는 함수 `fib`를 작성하세요. (fib(0)=0, fib(1)=1)\n\n**입력 예시**\nfib(10)\n\n**출력 예시**\n55',
 'fun fib(n: Int): Long {\n    if (n <= 1) return n.toLong()\n    var a = 0L; var b = 1L\n    repeat(n - 1) {\n        val c = a + b\n        a = b; b = c\n    }\n    return b\n}',
 'repeat(n-1)을 사용해 반복 DP로 구현하면 재귀보다 효율적입니다.',
 '중', 'Kotlin'),

('admin',
 '[Kotlin-중] 소수 판별',
 '양의 정수 n이 소수인지 판별하여 Boolean을 반환하는 `isPrime`을 작성하세요.\n\n**입력 예시**\nisPrime(17)\n\n**출력 예시**\ntrue',
 'fun isPrime(n: Int): Boolean {\n    if (n < 2) return false\n    return (2..Math.sqrt(n.toDouble()).toInt()).none { n % it == 0 }\n}',
 '2부터 √n까지 범위에서 none { n % it == 0 }를 사용하면 간결합니다.',
 '중', 'Kotlin'),

('admin',
 '[Kotlin-중] 팰린드롬 검사',
 '문자열 s가 팰린드롬인지 확인하는 `isPalindrome`을 작성하세요. 알파벳·숫자만 고려하고 대소문자는 무시합니다.\n\n**입력 예시**\nisPalindrome("A man a plan a canal Panama")\n\n**출력 예시**\ntrue',
 'fun isPalindrome(s: String): Boolean {\n    val cleaned = s.filter { it.isLetterOrDigit() }.lowercase()\n    return cleaned == cleaned.reversed()\n}',
 'filter { it.isLetterOrDigit() }로 정리하고 reversed()와 비교하세요.',
 '중', 'Kotlin'),

('admin',
 '[Kotlin-중] 리스트 그룹화',
 '정수 리스트와 키 추출 람다를 받아 그룹화된 Map을 반환하는 `groupByKey`를 작성하세요.\n\n**입력 예시**\ngroupByKey(listOf(1, 2, 3, 4, 5, 6)) { it % 3 }\n\n**출력 예시**\n{1=[1, 4], 2=[2, 5], 0=[3, 6]}',
 'fun <T, K> groupByKey(list: List<T>, keyFn: (T) -> K): Map<K, List<T>> =\n    list.groupBy(keyFn)',
 'Kotlin 표준 라이브러리의 groupBy 확장 함수를 활용하세요.',
 '중', 'Kotlin'),

('admin',
 '[Kotlin-중] 문자열 압축',
 '연속된 같은 문자를 "문자+횟수"로 압축하는 `compress`를 작성하세요. 횟수가 1이면 생략합니다.\n\n**입력 예시**\ncompress("aaabbcccc")\n\n**출력 예시**\n"a3b2c4"',
 'fun compress(s: String): String {\n    if (s.isEmpty()) return s\n    val sb = StringBuilder()\n    var count = 1\n    for (i in 1 until s.length) {\n        if (s[i] == s[i-1]) count++\n        else {\n            sb.append(s[i-1])\n            if (count > 1) sb.append(count)\n            count = 1\n        }\n    }\n    sb.append(s.last())\n    if (count > 1) sb.append(count)\n    return sb.toString()\n}',
 '현재 문자와 이전 문자를 비교하며 count를 관리하세요.',
 '중', 'Kotlin'),

('admin',
 '[Kotlin-중] 데이터 클래스 활용',
 '학생 데이터 클래스 Student(name: String, score: Int)를 정의하고,\n학생 리스트에서 점수 기준 내림차순 정렬 후 상위 3명의 이름 리스트를 반환하는 `topStudents`를 작성하세요.\n\n**입력 예시**\ntopStudents([("Alice",85),("Bob",92),("Charlie",78),("Diana",95),("Eve",88)])\n\n**출력 예시**\n["Diana", "Bob", "Eve"]',
 'data class Student(val name: String, val score: Int)\n\nfun topStudents(students: List<Student>): List<String> =\n    students\n        .sortedByDescending { it.score }\n        .take(3)\n        .map { it.name }',
 'sortedByDescending + take(3) + map을 체이닝하세요.',
 '중', 'Kotlin'),

('admin',
 '[Kotlin-중] sealed class 결과 타입',
 'sealed class Result<T>를 정의하고, 나눗셈 결과를 반환하는 `safeDivide`를 작성하세요.\n0으로 나누면 Result.Error, 성공하면 Result.Success를 반환합니다.\n\n**사용 예시**\nsafeDivide(10, 2) // Result.Success(5.0)\nsafeDivide(10, 0) // Result.Error("0으로 나눌 수 없습니다")',
 'sealed class Result<out T> {\n    data class Success<T>(val value: T) : Result<T>()\n    data class Error(val message: String) : Result<Nothing>()\n}\n\nfun safeDivide(a: Int, b: Int): Result<Double> =\n    if (b == 0) Result.Error("0으로 나눌 수 없습니다")\n    else Result.Success(a.toDouble() / b)',
 'sealed class의 하위 클래스로 Success와 Error를 정의하고 when으로 분기하세요.',
 '중', 'Kotlin'),

('admin',
 '[Kotlin-중] 확장 함수 구현',
 'String 타입에 확장 함수를 추가하여 단어 수를 반환하는 `wordCount()`, 각 단어의 첫 글자를 대문자로 바꾸는 `titleCase()`를 구현하세요.\n\n**사용 예시**\n"hello world foo".wordCount() // 3\n"hello world".titleCase()     // "Hello World"',
 'fun String.wordCount(): Int = this.trim().split("\\s+".toRegex()).size\n\nfun String.titleCase(): String =\n    this.split(" ").joinToString(" ") { word ->\n        word.replaceFirstChar { it.uppercase() }\n    }',
 'fun String.확장함수명() 형태로 기존 타입에 함수를 추가할 수 있습니다.',
 '중', 'Kotlin'),

('admin',
 '[Kotlin-중] 고차 함수 체이닝',
 '정수 리스트를 받아 짝수만 걸러내고, 각 값을 제곱한 뒤, 합계를 반환하는 `sumSquaredEvens`를 고차 함수(filter, map, sum)로 작성하세요.\n\n**입력 예시**\nsumSquaredEvens(listOf(1, 2, 3, 4, 5, 6))\n\n**출력 예시**\n56  // 4 + 16 + 36',
 'fun sumSquaredEvens(nums: List<Int>): Int =\n    nums.filter { it % 2 == 0 }\n        .map { it * it }\n        .sum()',
 'filter → map → sum 순서로 체이닝하세요.',
 '중', 'Kotlin'),

('admin',
 '[Kotlin-중] null 안전 처리',
 '이름(nullable)과 나이(nullable)를 받아 둘 다 존재하면 "이름(나이살)" 형식, 하나라도 null이면 "정보 없음"을 반환하는 `formatUser`를 Elvis 연산자와 let을 활용해 작성하세요.\n\n**입력 예시**\nformatUser("Alice", 30)  // "Alice(30살)"\nformatUser(null, 30)     // "정보 없음"',
 'fun formatUser(name: String?, age: Int?): String {\n    return if (name != null && age != null) {\n        "$name(${age}살)"\n    } else {\n        "정보 없음"\n    }\n}\n\n// 또는 let + Elvis 활용\nfun formatUser2(name: String?, age: Int?): String =\n    name?.let { n -> age?.let { a -> "$n(${a}살)" } } ?: "정보 없음"',
 'safe call(?.)과 Elvis(?:) 연산자, let 스코프 함수를 조합하세요.',
 '중', 'Kotlin');

-- =========================================================
-- Kotlin - 상 (Hard)
-- =========================================================
INSERT INTO problems (user_id, title, content, answer, hint, difficulty, category) VALUES
('admin',
 '[Kotlin-상] 제네릭 스택 구현',
 '제네릭 타입 T를 사용하는 스택 클래스 `Stack<T>`를 구현하세요.\npush, pop(없으면 null), peek(없으면 null), isEmpty, size 기능을 포함합니다.\n\n**사용 예시**\nval stack = Stack<Int>()\nstack.push(1); stack.push(2)\nstack.pop()  // 2\nstack.peek() // 1',
 'class Stack<T> {\n    private val data = ArrayDeque<T>()\n\n    fun push(item: T) = data.addLast(item)\n    fun pop(): T? = if (isEmpty()) null else data.removeLast()\n    fun peek(): T? = data.lastOrNull()\n    fun isEmpty(): Boolean = data.isEmpty()\n    val size: Int get() = data.size\n}',
 'ArrayDeque를 내부 자료구조로 활용하면 간결합니다.',
 '상', 'Kotlin'),

('admin',
 '[Kotlin-상] 코루틴 병렬 처리',
 'suspend 함수를 사용해 URL 리스트의 데이터를 병렬로 가져오는 `fetchAll`을 구현하세요.\nasync/await 패턴으로 모든 요청을 동시에 실행하고 결과 리스트를 반환합니다.\n\n**사용 예시**\n// 가상 API 호출\nsuspend fun fetchData(url: String): String = delay(100); url + "_data"\nrunBlocking { fetchAll(listOf("url1","url2","url3")) }\n// 결과: ["url1_data", "url2_data", "url3_data"]',
 'import kotlinx.coroutines.*\n\nsuspend fun fetchAll(urls: List<String>): List<String> = coroutineScope {\n    urls.map { url ->\n        async {\n            fetchData(url)  // 실제 구현체 호출\n        }\n    }.awaitAll()\n}\n\n// 가상 fetchData (테스트용)\nsuspend fun fetchData(url: String): String {\n    delay(100)\n    return url + "_data"\n}',
 'coroutineScope { } 안에서 async를 시작하고 awaitAll()로 모든 결과를 기다리세요.',
 '상', 'Kotlin'),

('admin',
 '[Kotlin-상] DSL 빌더 패턴',
 'HTML처럼 트리 구조를 표현하는 간단한 DSL을 구현하세요.\n\n**사용 예시**\nval html = html {\n    head { title("My Page") }\n    body {\n        h1("Hello")\n        p("World")\n    }\n}\nprintln(html.render())\n// <html><head><title>My Page</title></head><body><h1>Hello</h1><p>World</p></body></html>',
 'class Tag(private val name: String) {\n    private val children = mutableListOf<Tag>()\n    private var text: String = ""\n\n    fun tag(name: String, block: Tag.() -> Unit = {}): Tag {\n        val child = Tag(name).also { it.block() }\n        children.add(child)\n        return child\n    }\n\n    fun text(content: String) { text = content }\n\n    fun render(): String {\n        val inner = if (text.isNotEmpty()) text\n                    else children.joinToString("") { it.render() }\n        return "<$name>$inner</$name>"\n    }\n}\n\nfun html(block: Tag.() -> Unit): Tag = Tag("html").also { it.block() }\nfun Tag.head(block: Tag.() -> Unit) = tag("head", block)\nfun Tag.body(block: Tag.() -> Unit) = tag("body", block)\nfun Tag.title(text: String) = tag("title") { text(text) }\nfun Tag.h1(text: String) = tag("h1") { text(text) }\nfun Tag.p(text: String) = tag("p") { text(text) }',
 '수신 객체 지정 람다(T.() -> Unit)를 활용하면 DSL을 자연스럽게 만들 수 있습니다.',
 '상', 'Kotlin'),

('admin',
 '[Kotlin-상] BFS 최단 경로',
 '인접 리스트 그래프에서 start에서 end까지의 최단 거리를 BFS로 구하는 `shortestPath`를 작성하세요. 경로가 없으면 -1 반환.\n\n**입력 예시**\nval graph = mapOf(0 to listOf(1,2), 1 to listOf(3), 2 to listOf(4), 3 to listOf(5), 4 to listOf(), 5 to listOf())\nshortestPath(graph, 0, 5) // 3',
 'import java.util.LinkedList\n\nfun shortestPath(graph: Map<Int, List<Int>>, start: Int, end: Int): Int {\n    val dist = mutableMapOf(start to 0)\n    val queue: Queue<Int> = LinkedList(listOf(start))\n    while (queue.isNotEmpty()) {\n        val cur = queue.poll()\n        if (cur == end) return dist[cur]!!\n        for (next in graph[cur] ?: emptyList()) {\n            if (next !in dist) {\n                dist[next] = dist[cur]!! + 1\n                queue.add(next)\n            }\n        }\n    }\n    return -1\n}',
 'dist Map으로 방문 여부와 거리를 함께 관리하고 큐로 BFS를 진행하세요.',
 '상', 'Kotlin'),

('admin',
 '[Kotlin-상] 동적 프로그래밍 - 동전 교환',
 '동전 종류 배열과 목표 금액 amount가 주어질 때, 최소 동전 개수를 반환하는 `coinChange`를 DP로 구현하세요. 불가능하면 -1 반환.\n\n**입력 예시**\ncoinChange(intArrayOf(1, 5, 11), 15)\n\n**출력 예시**\n3  // 5+5+5',
 'fun coinChange(coins: IntArray, amount: Int): Int {\n    val dp = IntArray(amount + 1) { Int.MAX_VALUE }\n    dp[0] = 0\n    for (i in 1..amount) {\n        for (coin in coins) {\n            if (coin <= i && dp[i - coin] != Int.MAX_VALUE) {\n                dp[i] = minOf(dp[i], dp[i - coin] + 1)\n            }\n        }\n    }\n    return if (dp[amount] == Int.MAX_VALUE) -1 else dp[amount]\n}',
 'dp[i] = 금액 i를 만드는 최소 동전 수. dp[0]=0으로 초기화하고 나머지는 MAX_VALUE로 설정하세요.',
 '상', 'Kotlin'),

('admin',
 '[Kotlin-상] Flow를 사용한 스트림 처리',
 'Kotlin Flow를 사용해 1부터 100까지 숫자를 생성하고,\n짝수만 필터링, 각 값을 제곱, 처음 5개만 수집하여 리스트로 반환하는 `processFlow`를 작성하세요.\n\n**출력 예시**\n[4, 16, 36, 64, 100] // 2²,4²,6²,8²,10²',
 'import kotlinx.coroutines.flow.*\nimport kotlinx.coroutines.runBlocking\n\nsuspend fun processFlow(): List<Int> =\n    (1..100).asFlow()\n        .filter { it % 2 == 0 }\n        .map { it * it }\n        .take(5)\n        .toList()\n\n// 실행\nfun main() = runBlocking {\n    println(processFlow())\n}',
 'asFlow()로 범위를 Flow로 변환하고, filter/map/take/toList를 체이닝하세요.',
 '상', 'Kotlin'),

('admin',
 '[Kotlin-상] 불변 데이터 업데이트',
 '중첩된 데이터 클래스 구조를 copy()를 사용해 불변으로 업데이트하는 패턴을 구현하세요.\n\n**데이터 구조**\ndata class Address(val city: String, val zip: String)\ndata class User(val name: String, val age: Int, val address: Address)\n\n**요구사항**\nupdateCity(user, "Seoul")을 구현하세요. 원본 user는 변경되지 않아야 합니다.\n\n**사용 예시**\nval user = User("Alice", 30, Address("Busan", "47000"))\nval updated = updateCity(user, "Seoul")\nprintln(updated.address.city) // Seoul\nprintln(user.address.city)    // Busan',
 'data class Address(val city: String, val zip: String)\ndata class User(val name: String, val age: Int, val address: Address)\n\nfun updateCity(user: User, newCity: String): User =\n    user.copy(address = user.address.copy(city = newCity))',
 'copy()를 중첩으로 호출해 중간 레이어도 새 인스턴스로 교체하세요.',
 '상', 'Kotlin'),

('admin',
 '[Kotlin-상] 위임 프로퍼티 구현',
 'by lazy, by observable을 활용하는 클래스를 구현하세요.\n\n**요구사항**\n1. `lazyValue`: 처음 접근 시에만 계산되는 값 (무거운 연산 가정)\n2. `observedValue`: 값이 변경될 때마다 변경 내역을 로그 리스트에 기록\n\n**사용 예시**\nval obj = DelegateDemo()\nprintln(obj.lazyValue)   // "computed!" 출력 후 "heavy_result"\nobj.observedValue = "A"\nobj.observedValue = "B"\nprintln(obj.logs) // ["" -> "A", "A" -> "B"]',
 'import kotlin.properties.Delegates\n\nclass DelegateDemo {\n    val logs = mutableListOf<String>()\n\n    val lazyValue: String by lazy {\n        println("computed!")\n        "heavy_result"\n    }\n\n    var observedValue: String by Delegates.observable("") { _, old, new ->\n        logs.add("\"$old\" -> \"$new\"")\n    }\n}',
 'by lazy { }는 첫 접근 시 한 번만 실행되고, Delegates.observable은 set 시마다 콜백을 호출합니다.',
 '상', 'Kotlin'),

('admin',
 '[Kotlin-상] 재귀 + Sequence로 트리 순회',
 '이진 트리를 Sequence를 반환하는 확장 함수로 순회하는 `inorder()`, `preorder()`, `postorder()`를 구현하세요.\n\n**데이터 클래스**\ndata class TreeNode(val value: Int, val left: TreeNode? = null, val right: TreeNode? = null)\n\n**사용 예시**\nval tree = TreeNode(1, TreeNode(2, TreeNode(4), TreeNode(5)), TreeNode(3))\ntree.inorder().toList()   // [4, 2, 5, 1, 3]\ntree.preorder().toList()  // [1, 2, 4, 5, 3]',
 'data class TreeNode(val value: Int, val left: TreeNode? = null, val right: TreeNode? = null)\n\nfun TreeNode.inorder(): Sequence<Int> = sequence {\n    left?.let { yieldAll(it.inorder()) }\n    yield(value)\n    right?.let { yieldAll(it.inorder()) }\n}\n\nfun TreeNode.preorder(): Sequence<Int> = sequence {\n    yield(value)\n    left?.let { yieldAll(it.preorder()) }\n    right?.let { yieldAll(it.preorder()) }\n}\n\nfun TreeNode.postorder(): Sequence<Int> = sequence {\n    left?.let { yieldAll(it.postorder()) }\n    right?.let { yieldAll(it.postorder()) }\n    yield(value)\n}',
 'sequence { } 빌더 안에서 yield와 yieldAll을 사용하면 지연 평가 시퀀스를 만들 수 있습니다.',
 '상', 'Kotlin'),

('admin',
 '[Kotlin-상] 커스텀 이터레이터',
 '범위와 스텝을 받아 역방향으로 순회하는 `StepRange` 클래스와 `downTo` infix 함수를 구현하세요.\n\n**사용 예시**\nfor (i in 10 downTo 1 step 3) print("$i ") // 10 7 4 1\n\n// 또는\nval range = StepRange(10, 1, -3)\nrange.toList() // [10, 7, 4, 1]',
 'class StepRange(val start: Int, val endInclusive: Int, val step: Int) : Iterable<Int> {\n    override fun iterator(): Iterator<Int> = object : Iterator<Int> {\n        var current = start\n        override fun hasNext(): Boolean =\n            if (step > 0) current <= endInclusive else current >= endInclusive\n        override fun next(): Int {\n            val value = current\n            current += step\n            return value\n        }\n    }\n}\n\ninfix fun Int.downTo(other: Int) = StepRange(this, other, -1)\ninfix fun StepRange.step(s: Int) = StepRange(start, endInclusive, if (step < 0) -s else s)',
 'Iterator 인터페이스를 익명 객체로 구현하고, infix 함수로 DSL 스타일을 만드세요.',
 '상', 'Kotlin');
