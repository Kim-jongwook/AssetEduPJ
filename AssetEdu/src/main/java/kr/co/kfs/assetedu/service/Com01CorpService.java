package kr.co.kfs.assetedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
