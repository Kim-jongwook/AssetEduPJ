package kr.co.kfs.assetedu.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.model.Sys01User;

public interface Sys01UserRepository {

	List<Sys01User> selectList(Condition condition);
	int insert(Sys01User user);
	Sys01User selectOne(Sys01User user);
	Long totalCount(Condition condition);
	int update(Sys01User user);
	int passwordMatch(String userId, String userPw);
	int delete(String userId);
}
