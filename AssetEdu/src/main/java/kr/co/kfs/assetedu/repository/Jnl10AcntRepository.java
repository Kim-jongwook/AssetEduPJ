package kr.co.kfs.assetedu.repository;

import java.util.List;

import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.model.Jnl10Acnt;

public interface Jnl10AcntRepository {
	Long totalCount(Condition condition);
	List<Jnl10Acnt> list(Condition condition);
	Jnl10Acnt selectOne(Jnl10Acnt jnl10Acnt);
	int insert(Jnl10Acnt jnl10Acnt);
	int update(Jnl10Acnt jnl10Acnt);
	int delete(String jnl10AcntCd);
}
