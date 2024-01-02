package kr.co.kfs.assetedu.repository;

import java.util.List;

import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.model.Fnd01Fund;

public interface Fnd01FundRepository {
	Long totalCount(Condition condition);
	List<Fnd01Fund> selectList(Condition condition);
	Fnd01Fund selectOne(Fnd01Fund fnd01Fund);
	int insert(Fnd01Fund fnd01Fund);
	int update(Fnd01Fund fnd01Fund);
	int delete(String fnd01FundCd);
}
