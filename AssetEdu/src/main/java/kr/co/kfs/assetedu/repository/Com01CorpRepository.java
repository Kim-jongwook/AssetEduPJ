package kr.co.kfs.assetedu.repository;

import java.util.List;

import kr.co.kfs.assetedu.model.Com01Corp;
import kr.co.kfs.assetedu.model.Condition;

public interface Com01CorpRepository {
	
	List<Com01Corp> selectList(Condition condition);
	Long selectCount(Condition condition);
	Com01Corp selectOne(Com01Corp com01Corp);
	int insert(Com01Corp com01Corp);
	int update(Com01Corp com01Corp);
	int delete(String corpCd);
}
