package kr.co.kfs.assetedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.model.Itm01Item;
import kr.co.kfs.assetedu.repository.Itm01ItemRepository;

@Service
public class Itm01ItemService {
	@Autowired
	Itm01ItemRepository itm01ItemRepository;
	
	public List<Itm01Item> selectList(Condition condition){
		return itm01ItemRepository.selectList(condition);
	}
	
	public Long totalCount(Condition condition) {
		return itm01ItemRepository.totalCount(condition);
	}
	
	@Transactional
	public int insert(Itm01Item itm01Item) {
		return itm01ItemRepository.insert(itm01Item);
	}
	
	public Itm01Item selectOne(Itm01Item itm01Itm) {
		return itm01ItemRepository.selectOne(itm01Itm);
	}
	
	@Transactional
	public int update(Itm01Item itm01Item) {
		return itm01ItemRepository.update(itm01Item);
	}
	
	@Transactional
	public int delete(Itm01Item itm01Item) {
		return itm01ItemRepository.delete(itm01Item);
	}
}
