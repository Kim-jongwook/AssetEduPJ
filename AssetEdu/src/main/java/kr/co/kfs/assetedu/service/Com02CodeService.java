package kr.co.kfs.assetedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kfs.assetedu.model.Com02Code;
import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.repository.Com02CodeRepository;

@Service
public class Com02CodeService {
	@Autowired
	Com02CodeRepository codeRepository;
	
	public List<Com02Code> selectList(Condition condition){
		return codeRepository.selectList(condition);
	}
	
	public Long totalCount(Condition condition) {
		return codeRepository.totalCount(condition);
	}
}
