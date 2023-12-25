package kr.co.kfs.assetedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.model.Sys01User;
import kr.co.kfs.assetedu.repository.Sys01UserRepository;

@Service
public class Sys01UserService {
	@Autowired
	private Sys01UserRepository userRepository;
	
	public List<Sys01User> selectList(Condition condition){
		return userRepository.selectList(condition);
	}
	public Sys01User selectOne(Sys01User user) {
		return userRepository.selectOne(user);
	}
	
	public Long totalCount(Condition condition) {
		return userRepository.totalCount(condition);
	}
	
	@Transactional
	public int insert(Sys01User user) {
		return userRepository.insert(user);
	}
	
	@Transactional
	public int update(Sys01User user) {
		return userRepository.update(user);
	}
	
	public int passwordMatch(String userId, String userPw) {
		return userRepository.passwordMatch(userId, userPw);
	}
	
	@Transactional
	public int delete(String userId) {
		return userRepository.delete(userId);
	}
}
