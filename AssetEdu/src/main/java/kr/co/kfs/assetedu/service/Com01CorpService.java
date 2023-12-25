package kr.co.kfs.assetedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.kfs.assetedu.model.Com01Corp;
import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.repository.Com01CorpRepository;

@Service
public class Com01CorpService {
	@Autowired
	private Com01CorpRepository com01CorpRepository;
	
	public List<Com01Corp> selectList(Condition condition){
		return com01CorpRepository.selectList(condition);
	}
	
	public Long selectCount(Condition condition) {
		return com01CorpRepository.selectCount(condition);
	}
	
	public Com01Corp selectOne(Com01Corp com01Corp) {
		return com01CorpRepository.selectOne(com01Corp);
	}
	
	@Transactional
	public int insert(Com01Corp com01Corp) {
		return com01CorpRepository.insert(com01Corp);		
	}
	
	@Transactional
	public int update(Com01Corp com01Corp) {
		return com01CorpRepository.update(com01Corp);
	}
	
	@Transactional
	public int delete(String corpCd) {
		return com01CorpRepository.delete(corpCd);
	}
}
