package kr.co.kfs.assetedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.model.Jnl10Acnt;
import kr.co.kfs.assetedu.repository.Jnl10AcntRepository;

@Service
public class Jnl10AcntService {
	@Autowired
	Jnl10AcntRepository jnl10AcntRepository;
	
	public Long totalCount(Condition condition){
		return jnl10AcntRepository.totalCount(condition);
	}
	
	public List<Jnl10Acnt> list(Condition codition){
		return jnl10AcntRepository.list(codition);
	}
	
	public Jnl10Acnt selectOne(Jnl10Acnt jnl10Acnt) {
		return jnl10AcntRepository.selectOne(jnl10Acnt);
	}
	
	@Transactional
	public int insert(Jnl10Acnt jnl10Acnt) {
		return jnl10AcntRepository.insert(jnl10Acnt);
	}
	
}
