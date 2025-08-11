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
	// ▼▼▼ [수정됨] UserGradeRepository 필드 완전 삭제 ▼▼▼
	// private final UserGradeRepository userGradeRepository;

	private final PasswordEncoder passwordEncoder;
	private final PointLogRepository pointLogRepository;
	private final SnsUserService snsUserSerivce;
	private final UserMapper userMapper;
	
	// 아이디 중복 체크
	public boolean checkId(String userId) {
		return userMapper.getLocalUser(userId) != null;
	}

	// 회원가입
	@Transactional
	public String signUp(UserSignUpRequest request, String snsProvider, String snsProviderId) {
		if (userRepository.existsById(request.getUserId())) {
			throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
		}

		// ▼▼▼ [수정됨] DB에서 등급을 조회하는 대신, UserGrade.BRONZE Enum을 직접 사용합니다. ▼▼▼
		User newUser = new User(request.getUserId(), request.getName(), request.getNickname(), request.getEmail(),
				request.getMobile(), UserGrade.BRONZE);

		LocalUser newLocalUser = new LocalUser(newUser, passwordEncoder.encode(request.getPass()));
		localUserRepository.save(newLocalUser);
		localUserRepository.flush();
		
		addPointsForEvent(newUser.getUserId(), PointEventType.SIGN_UP);

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
	        return;
	    }
	    User user = optionalUser.get();

	    if (eventType == PointEventType.DAILY_LOGIN) {
	        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
	        boolean alreadyAwarded = pointLogRepository.existsByUserAndEventTypeAndCreatedAtAfter(user, eventType, startOfDay);
	        if (alreadyAwarded) {
	            return;
	        }
	    }

	    PointLog newPointLog = PointLog.builder()
	            .user(user)
	            .eventType(eventType)
	            .points(eventType.getPoints())
	            .description(eventType.getDescription())
	            .build();
	    pointLogRepository.save(newPointLog);

	    user.addPoints(eventType.getPoints());
	    userRepository.save(user);
	}
}