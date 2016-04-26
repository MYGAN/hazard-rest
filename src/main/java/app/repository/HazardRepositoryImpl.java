package app.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import app.model.Hazard;

public class HazardRepositoryImpl implements HazardRepositoryCustom{
	
	@Autowired private MongoOperations operations;
	
	public List<Hazard> searchHazardByAffectedUsers(String[] affected_user, String type) {
		List<String> affectedUserList = new ArrayList<String>();
		
		for(int i = 0; i < affected_user.length; i++) {
			//System.out.println("affecected_user[" + i + "] = " + affected_user[i]);
			affectedUserList.add(affected_user[i]);
		}
		
		Query query = new Query();
		
		if(affectedUserList.size() == 1) {
			query.addCriteria(Criteria.where("affected_user").is(affectedUserList.get(0)).and("type").is(type));
		} else {
			query.addCriteria(Criteria.where("affected_user").exists(true).andOperator(Criteria.where("affected_user").is(affectedUserList.get(0)), Criteria.where("affected_user").is(affectedUserList.get(1))).and("type").is(type));
		}
		
		
		return operations.find(query, Hazard.class);
	}
}
