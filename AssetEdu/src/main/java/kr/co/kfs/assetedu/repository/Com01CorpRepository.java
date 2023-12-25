package kr.co.kfs.assetedu.repository;

import java.util.List;

import kr.co.kfs.assetedu.model.Com01Corp;
import kr.co.kfs.assetedu.model.Condition;

public interface Com01CorpRepository {
	
	List<Com01Corp> selectList(Condition condition);
	Long selectCount(Condition condition);
}
