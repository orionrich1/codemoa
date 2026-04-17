# AWS EC2 배포 현황 정리

> 대화·SSH 확인 로그를 바탕으로 정리한 **운영/배포 참고용** 문서입니다.  
> 인스턴스를 재시작하거나 배포를 바꾼 뒤에는 아래 **확인 명령**으로 다시 맞춰 두는 것이 좋습니다.

---

## 1. 한 줄 요약

이 EC2(Ubuntu)는 **자바 프로세스가 두 개** 떠 있으며, 역할이 다릅니다.

| 구분 | 포트 | 역할(추정·확인) |
|------|------|------------------|
| **A** | **8080** | Spring Boot **fat JAR** 단독 실행 (`java -jar`). 홈의 `final-project-0.0.1-SNAPSHOT.jar`와 대응하는 형태. |
| **B** | **8081** | **Tomcat 10** + 컨텍스트 **`/team_mini`** (`/usr/local/tomcat10/webapps/team_mini`, `team_mini.war`). |

과거 교육/글에서 나오는 **이클립스 + WAR만** 올리는 구조와 **지금 이 서버의 8080**은 결이 다릅니다. 8080은 **Gradle `bootJar` 산출물을 `java -jar`로 돌리는 방식**에 가깝습니다.

---

## 2. 증거로 확인된 사실 (SSH에서 실행한 결과 기준)

### 2.1 리슨 포트와 프로세스

```bash
sudo ss -tlnp | grep -E '8080|8081'
```

- **8080**, **8081** 각각 **서로 다른 `java` PID**로 리슨한 것이 확인됨.  
- **PID는 재기동·배포마다 바뀝니다.** 문서에 적힌 숫자는 “그때의 예시”로만 보면 됩니다.

### 2.2 Tomcat 10 · team_mini

```bash
ls -la /usr/local/tomcat10/webapps
```

- `team_mini.war`, `team_mini/` 디렉터리 존재 → **8081 + `/team_mini`** URL 패턴과 일치하는 전형적인 배치.

### 2.3 Tomcat 9 백업

- `/usr/local/tomcat9_backup/webapps` 에도 `team_mini` 등이 있음 → **예전 톰캣 9** 쪽을 백업해 둔 것으로 보이며, **현재 8081 서비스의 본체는 tomcat10 쪽**으로 보면 됨.

### 2.4 홈 디렉터리 (`/home/ubuntu`)

`ls -la ~` 기준으로 의미 있는 항목:

| 경로/파일 | 설명 |
|-----------|------|
| `final-project-0.0.1-SNAPSHOT.jar` | Spring Boot 배포 산출물(용량 큰 fat JAR). **8080 서비스의 본체로 보는 것이 자연스러움.** |
| `nohup.out` | `nohup java -jar ... &` 등으로 띄웠을 때 로그가 쌓이는 전형적인 흔적. |
| `uploads/` (예: `uploads/information/`) | 업로드 파일 저장 경로 (`application-server`의 `file.upload.dir`). |
| `*.sql` | DB 덤프·초기화용으로 서버에 보관된 파일로 해석 가능. |
| `src/main/resources` 수준의 얕은 트리 | **전체 Gradle 소스 트리가 아닐 수 있음.** 운영 실행의 근거는 **JAR** 쪽이 우선. |

---

## 3. “예전 글”의 톰캣+WAR과의 관계

- **예전**: EC2에 Tomcat 설치 → `webapps`에 WAR, `WEB-INF/lib`에 수동으로 JAR 복사, 이클립스 Export 등.  
- **지금 이 인스턴스**:  
  - **8081**은 여전히 **Tomcat + WAR(`team_mini`)** 패턴.  
  - **8080**은 **Spring Boot JAR + `java -jar`** 패턴으로, **같은 머신에서 두 세대/두 방식이 공존**하는 형태로 이해하면 됨.

Gson 등 의존성은 **현재 로컬 프로젝트에서는 `build.gradle`의 `implementation`** 으로 맞추는 것이 일반적이며, **WEB-INF/lib 수동 복사는 이 Spring Boot 저장소의 표준 배포와는 다름.**

---

## 4. 8080(스프링 부트)만 새 버전으로 바꿀 때 (절차 요약)

### 4.0 MySQL(DB) 최초 준비 (인스턴스에 DB가 비어 있을 때)

- 프로젝트의 `spring.profiles.active=server` 설정은 **`127.0.0.1:3306` / DB `codemoa_db` / 사용자 `codemoa_user`** 를 가정합니다 (`application-server.properties`).
- EC2에 MySQL을 설치한 뒤, 저장소의 **`src/main/resources/static/sql/ec2_mysql_bootstrap.sql`** 을 한 번 실행해 DB·유저·권한을 맞춥니다. (비밀번호는 운영 정책에 맞게 바꾼 뒤 설정 파일·`secret.properties`와 동일하게 맞출 것.)
- 애플리케이션 HTTP 포트는 **`server.port=8080`** (`application.properties` 공통).
- **로컬 DB를 서버에 그대로 옮기는 방법**은 **§5.5 ~ §5.7** 참고.

1. **로컬**에서 `./gradlew clean bootJar` → **`build/libs/final-project-0.0.1-SNAPSHOT.jar`** (fat JAR, `plain` 없는 파일) 생성.  
2. **서버**에서 8080을 쓰는 자바 프로세스 확인:  
   `sudo ss -tlnp | grep 8080` 또는 `sudo lsof -i :8080`  
3. **끄기 전에** (가능하면) 기존 실행 인자 확인:  
   `ps -p <PID> -o args=`  
   → `nohup`, `-Dspring.profiles.active=...` 등 **다음 기동에 그대로 맞추기.**  
4. 프로세스 종료: 우선 `kill <PID>`, 필요 시에만 `kill -9`.  
5. 홈의 기존 JAR **백업** 후(이름에 날짜 등), **새 JAR 업로드** (§5.4).  
6. 예전과 동일한 방식으로 기동 (예):  
   `nohup java -jar -Dspring.profiles.active=server final-project-0.0.1-SNAPSHOT.jar >> nohup.out 2>&1 &`  
   (프로파일·파일명은 실제 `ps` 결과에 맞출 것.)  
7. 확인: `sudo ss -tlnp | grep 8080`, `tail`로 `nohup.out`, 브라우저에서 **`http://<퍼블릭IP>:8080/`** (§5.3).

**참고:** `systemd` 유닛으로 관리 중이면 `kill` 대신 `systemctl restart <서비스명>`이 맞을 수 있음. 현재는 **nohup + 홈 JAR** 패턴이 강하게 보였으나, 장기적으로는 systemd 등으로 고정하는 편이 운영에 유리함.

---

## 5. JAR·설정·파일 업로드·DB 복원 (실무 정리)

### 5.1 JAR는 어디서 생기나 / Git에 없는 이유

- **로컬** 프로젝트 루트에서 `./gradlew bootJar` (또는 `clean bootJar`) 후 **`build/libs/final-project-0.0.1-SNAPSHOT.jar`** 에 생깁니다.  
- `build/` 는 보통 **Git에 포함되지 않으므로**, 배포할 때마다 **로컬에서 빌드한 JAR**를 서버로 올립니다.

### 5.2 `application.properties` 를 서버에 따로 올려야 하나?

- **`src/main/resources`의 설정은 JAR 안에 패킹**됩니다. EC2에 `application.properties`만 따로 올릴 필요는 없습니다.  
- 다만 **`spring.config.import=optional:secret.properties`** 등으로 **비밀번호·OAuth 키**를 JAR 밖 파일로 둘 수 있으며, 그때는 서버에 `secret.properties`(또는 환경 변수)를 맞춰 둡니다.

### 5.3 퍼블릭 IP vs 사설 IP (172.31.x.x)

- AWS 콘솔에 보이는 **`172.31.x.x` 형태는 VPC 사설 IP**입니다.  
- **집·회사 PC에서 `scp` / `ssh` / FileZilla** 로 접속할 때는 **`172.31...` 이 아니라 EC2의 퍼블릭 IPv4**(또는 Elastic IP)를 써야 합니다.  
- 사설 IP만으로 연결하면 **`Connection timed out`** 이 날 수 있습니다.  
- 보안 그룹에서 **SSH(22)**·필요 시 **8080** 이 본인 환경에서 열려 있는지 확인합니다.

### 5.4 서버로 파일 올리기 (JAR·SQL 덤프 등)

**PowerShell `scp` 예시** (키 경로·퍼블릭 IP·로컬 파일 경로는 본인 환경에 맞게 수정):

```powershell
scp -i "C:\경로\키.pem" "C:\경로\로컬파일.jar" ubuntu@<퍼블릭IP>:~/
```

- **`-i` 바로 뒤**가 **키 파일(`.pem`) 전체 경로**입니다.  
- **SFTP(FileZilla 등)**: 프로토콜 **SFTP**, 포트 **22**, 사용자 **`ubuntu`**, 인증에 **키 파일** 지정. (**FTP 21 아님.**)  
- 업로드 위치는 보통 **`/home/ubuntu/`** 홈 디렉터리.

### 5.5 로컬에서 MySQL 덤프 만들기 (Windows)

- PowerShell에서 **`>` 리다이렉트로 `mysqldump` 출력을 파일에 쓰면** UTF-16 등으로 깨져 서버에서 `mysql < dump.sql` 시 **`ERROR 1366` / `ASCII '\0'`** 가 날 수 있습니다.  
- **`mysqldump`가 파일에 직접 쓰도록 `-r`(또는 `--result-file`)** 을 사용합니다.

```powershell
& "C:\Program Files\MySQL\MySQL Server 8.4\bin\mysqldump.exe" -u root -p비밀번호 --single-transaction --routines --triggers codemoa_db -r "C:\원하는\경로\codemoa_dump.sql"
```

### 5.6 EC2에서 덤프 복원 (반드시 Linux 셸에서)

- **`mysql ... < dump.sql` 형태는 bash 리다이렉트**입니다. **Windows PowerShell에서 그대로 실행하면 안 되고**, **SSH로 Ubuntu에 접속한 뒤** 실행합니다.

```bash
mysql -u codemoa_user -p비밀번호 codemoa_db < ~/codemoa_dump.sql
```

### 5.7 문자 집합 (ERROR 1366, 한글)

- 테이블/컬럼이 **latin1** 등이면 한글 `UPDATE`/`INSERT` 시 **1366** 이 날 수 있습니다.  
- 프로젝트의 로컬 정리용 스크립트 예: **`community_local_cleanup_and_seed.sql`** 처럼 상단에 **`SET NAMES utf8mb4`** 및 필요 시 **`ALTER TABLE ... CONVERT TO CHARACTER SET utf8mb4`** 를 두는 방식을 참고할 수 있습니다.  
- 클라이언트 실행 시 **`mysql --default-character-set=utf8mb4`** 를 함께 쓰는 것도 도움이 됩니다.

### 5.8 Cursor(IDE) 안에서 할 수 있는 것 (통합 터미널·에이전트)

- **Cursor에 붙은 터미널**은 **이 PC(작업 공간)에서 셸 명령을 실행**할 수 있으므로, 아래는 **별도 PowerShell 창을 열지 않고** 같은 환경에서 처리할 수 있습니다 (에이전트/사용자가 명령을 실행하는 경우 포함).
  - **로컬**: `./gradlew clean bootJar`, `mysqldump ... -r ...` 로 JAR·덤프 파일 생성.
  - **EC2로 전송**: `scp -i 키.pem ... ubuntu@<퍼블릭IP>:~/` 로 JAR·SQL 업로드 (키·IP·보안 그룹이 맞을 때).
  - **원격 조작(한 줄 SSH)**: `ssh ... '명령'` 으로 **8080 프로세스 종료**, **`mysql < 덤프`**, **`nohup java -jar ... &`** 재기동, `tail nohup.out` / `ss -tlnp` 확인 등.
- **한계**
  - **사용자가 직접 연 SSH 창**에 타이핑하는 것과는 별개이며, **여기서 쓰는 건 “Cursor가 실행하는 터미널”** 입니다.
  - **네트워크·방화벽·키 권한**에 따라 `scp`/`ssh`가 실패할 수 있으며, 그때는 **본인 PC 터미널에서 동일 명령**을 실행하면 됩니다.
  - **브라우저로 사이트 동작 확인**은 IDE가 대신할 수 없고, 배포 후 **`http://<퍼블릭IP>:8080/`** 등으로 직접 확인하는 것이 좋습니다.

---

## 6. SSH 접속 시 자주 겪는 이슈 (Windows)

### 6.1 `Bad permissions` / `UNPROTECTED PRIVATE KEY FILE`

- `-i`에는 **폴더가 아니라 `.pem` 파일 전체 경로**를 지정.  
- Windows에서 OpenSSH 사용 시, 해당 `.pem`에 대해 권한 축소 (예: `icacls`로 상속 제거 후 본인만 읽기).  
- PowerShell에서는 `%USERNAME%` 대신 **`$env:USERNAME`** 또는 **cmd**에서 실행하는 편이 안전한 경우가 있음.

### 6.2 붙여넣기 시 `^[[200~` … `~` 가 보임

- 터미널의 **bracketed paste** 제어 시퀀스가 화면에 섞여 보이는 현상.  
- 해당 줄을 지우고 명령만 다시 입력하거나, **Windows Terminal**에서 `Ctrl+Shift+V` 등으로 붙여넣기 방식을 바꿔 볼 것.

### 6.3 `Connection reset`

- 네트워크 끊김에 가깝고, **다시 SSH 접속**하면 됨.

---

## 7. 보안·운영 메모

- **`.pem`을 Git 저장소 안에 두면** 실수로 커밋·푸시될 위험이 큼. 가능하면 `~/.ssh/` 등 저장소 밖으로 옮기고, 저장소에는 넣지 않는 것을 권장.  
- EC2 **퍼블릭 IP는 변경될 수 있음.** Elastic IP를 쓰지 않으면 접속 주소가 바뀔 때가 있음.  
- MOTD의 `*** System restart required ***`는 **커널/패치 적용을 위해 재부팅이 필요하다는 알림**일 수 있음. 유지보수 창에서 재부팅·서비스 기동 순서를 정해 두면 좋음.

---

## 8. 다음에 확인하면 좋은 명령 (스냅샷)

```bash
# 포트 ↔ 프로세스
sudo ss -tlnp | grep -E '8080|8081'

# 8080 JVM이 실제로 어떤 JAR/인자로 떴는지
ps aux | grep java

# 톰캣 10 webapps
ls -la /usr/local/tomcat10/webapps
```

---

## 9. 문서 갱신 시점

- **배포 방식 변경** (예: 8080만 systemd로 전환, JAR 경로 변경, 리버스 프록시 추가)  
- **인스턴스 교체·IP 고정 정책 변경**  
- **Tomcat/team_mini 제거 또는 포트 변경**  
- **DB 덤프·문자집합·업로드 절차**를 팀에서 바꾼 경우 (§5 보강)  
- **Cursor·IDE에서 배포 자동화 범위**를 바꾼 경우 (§5.8)

위와 같이 인프라가 바뀔 때 이 파일을 함께 수정하는 것을 권장합니다.
