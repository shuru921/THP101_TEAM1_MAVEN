package explore.core.dao;

import java.util.List;

public interface CoreDao<K, B> {

	int insert(B bean);

	int deleteByKey(K key);

	int update(B bean);

	B selectByKey(K key);

	List<B> selectAll();
}
