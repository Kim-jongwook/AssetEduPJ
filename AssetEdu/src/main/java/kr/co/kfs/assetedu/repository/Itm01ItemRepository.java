package kr.co.kfs.assetedu.repository;

import java.util.List;

import kr.co.kfs.assetedu.model.Condition;
import kr.co.kfs.assetedu.model.Itm01Item;

public interface Itm01ItemRepository {
	List<Itm01Item> selectList(Condition condition);
	Long totalCount(Condition condition);
}
