package app.repository;

import java.util.List;

import app.model.Hazard;

public interface HazardRepositoryCustom {

	public List<Hazard> searchHazardByAffectedUsers(String[] affected_user, String type);
	
}
