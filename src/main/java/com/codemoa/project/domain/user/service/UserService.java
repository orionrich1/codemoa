package com.codemoa.project.domain.user.service;

import com.codemoa.project.domain.user.dto.request.UserFindRequest;
import com.codemoa.project.domain.user.dto.request.UserPassUpdateRequest;
import com.codemoa.project.domain.user.dto.request.UserSignUpRequest;
import com.codemoa.project.domain.user.dto.request.UserUpdateRequest;
import com.codemoa.project.domain.user.dto.response.UserResponse;
import com.codemoa.project.domain.user.entity.LocalUser;
import com.codemoa.project.domain.user.entity.PointEventType;
import com.codemoa.project.domain.user.entity.PointLog;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.entity.UserGrade;
import com.codemoa.project.domain.user.mapper.UserMapper;
import com.codemoa.project.domain.user.repository.LocalUserRepository;
import com.codemoa.project.domain.user.repository.PointLogRepository;
import com.codemoa.project.domain.user.repository.UserGradeRepository;
import com.codemoa.project.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final LocalUserRepository localUserRepository;
	private final UserGradeRepository userGradeRepository;

	private final PasswordEncoder passwordEncoder;
	private final PointLogRepository pointLogRepository;
	private final SnsUserService snsUserSerivce;
	private final UserMapper userMapper;

	// 회원가입
	@Transactional
	public String signUp(UserSignUpRequest request, String snsProvider, String snsProviderId) {
		if (userRepository.existsById(request.getUserId())) {
			throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
		}

		UserGrade defaultGrade = userGradeRepository.findById("BRONZE")
				.orElseThrow(() -> new RuntimeException("DB 확인 필요: 기본 등급(BRONZE) 없음"));

		User newUser = new User(request.getUserId(), request.getName(), request.getNickname(), request.getEmail(),
				request.getMobile(), defaultGrade); // 중간에 있던 , 0 을 제거
		// SNS 관련 정보가 없으면 Local 회원가입만 진행
		// userRepository.save(newUser); // <-- 이 라인을 삭제하거나 주석 처리

		LocalUser newLocalUser = new LocalUser(newUser, passwordEncoder.encode(request.getPass()));

		// LocalUser만 저장합니다.
		// 만약 LocalUser에 cascade 옵션이 있다면 User도 함께 저장됩니다.
		// 만약 cascade 옵션이 없다면, 아래의 해결책 2를 시도해야 합니다.
		localUserRepository.save(newLocalUser);
		localUserRepository.flush();

		// 만약 SNS 관련 정보가 있다면 SNS과 연동되는 과정도 추가로 진행
		if (!(snsProvider == null || snsProvider.isBlank() || snsProviderId == null || snsProviderId.isBlank())) {
			snsUserSerivce.linkSnsAccount(newUser.getUserId(), snsProvider, snsProviderId);
		}

		return newUser.getUserId();
	}

	// 차단 당한 상태인지 확인
	public boolean checkIsBan(User user) {
		LocalDateTime banLeftDay = user.getUnbanDate();
		LocalDateTime now = LocalDateTime.now();

		if (banLeftDay.isAfter(now)) {
			return true;
		} else {
			return false;
		}

	}

	// 차단 사유 가져오기
	public String getBanReason(String userId) {
		return userMapper.getBanReason(userId);
	}

	// 아이디, 비밀번호 찾기 실행 결과
	public List<User> findResult(UserFindRequest request) {
		List<User> user = new ArrayList<User>();
		String id = request.getUserId();
		String name = request.getUserName();
		String phone = request.getUserPhone();
		boolean isPassFind = !(request.getUserId() == null || request.getUserId().isBlank());

		if (!isPassFind) {
			user = userMapper.findId(name, phone);
		} else {
			User u = userMapper.findPass(id, name, phone);
			if (u != null) {
				user.add(userMapper.findPass(id, name, phone));
			}
		}
		return user;
	}

	// 비밀번호 찾기 이 후 비밀번호 재설정하기
	public void updatePass(UserPassUpdateRequest request) {
		userMapper.updatePass(request.getUserId(), passwordEncoder.encode(request.getPass()));
	}

	// 유저 정보 수정 중 비밀번호 체크용 Local User 가져옴
	public LocalUser getLocalUser(String userId) {
		return userMapper.getLocalUser(userId);
	}

	public void updateUser(UserUpdateRequest request) {
		userMapper.updateUser(request);

		boolean isPasschange = "1".equals(request.getIsPassChange());
		if (isPasschange) {
			userMapper.updatePass(request.getUserId(), passwordEncoder.encode(request.getNewPass()));
		}
	}

	// 유저 계정 삭제
	public void deleteUser(String userId) {
		userMapper.deleteUser(userId);
	}

	@Transactional(readOnly = true)
	public UserResponse getUserInfo(String userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다. id=" + userId));
		return new UserResponse(user);
	}
	
	
	@Transactional
	public void addPointsForEvent(String userId, PointEventType eventType) {
	    Optional<User> optionalUser = userRepository.findByUserId(userId);
	    if (optionalUser.isEmpty()) {
	        // 사용자가 존재하지 않으면 아무 작업도 하지 않음
	        return;
	    }
	    User user = optionalUser.get();

	    // --- 일일 로그인 포인트 중복 지급 방지 ---
	    if (eventType == PointEventType.DAILY_LOGIN) {
	        LocalDateTime startOfDay = LocalDate.now().atStartOfDay(); // 오늘 날짜의 0시 0분
	        boolean alreadyAwarded = pointLogRepository.existsByUserAndEventTypeAndCreatedAtAfter(user, eventType, startOfDay);
	        if (alreadyAwarded) {
	            return; // 이미 오늘 로그인 포인트를 받았으면 종료
	        }
	    }

	    // 1. 포인트 로그 생성
	    PointLog newPointLog = PointLog.builder()
	            .user(user)
	            .eventType(eventType)
	            .points(eventType.getPoints())
	            .description(eventType.getDescription())
	            .build();
	    pointLogRepository.save(newPointLog);

	    // 2. 사용자 총 포인트 업데이트
	    user.addPoints(eventType.getPoints());
	    userRepository.save(user);
	}
}