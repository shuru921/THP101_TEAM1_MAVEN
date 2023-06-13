package explore.service.impl;

import java.util.List;

import explore.bean.Explore;
import explore.dao.ExploreDao;
import explore.dao.impl.ExploreDaoImpl;
import explore.service.ExploreService;






public class ExploreServiceImpl implements ExploreService {
	private ExploreDao dao;
	
	 public ExploreServiceImpl() {
		 dao = new ExploreDaoImpl();
	}
	 @Override
	public List<Explore> selectByUid(){
		return dao.selectByUid();
	}
}
