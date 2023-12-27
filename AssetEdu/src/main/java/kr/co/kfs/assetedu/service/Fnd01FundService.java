package kr.co.kfs.assetedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.model.Fnd01Fund;
import kr.co.kfs.assetedu.repository.Fnd01FundRepository;

@Service
public class Fnd01FundService {
	@Autowired
	Fnd01FundRepository fnd01FundRepository;
	
	public Long totalCount(Condition condition) {
		return fnd01FundRepository.totalCount(condition);
	}
	
	public List<Fnd01Fund> selectList(Condition condition){
		return fnd01FundRepository.selectList(condition);
	}
}