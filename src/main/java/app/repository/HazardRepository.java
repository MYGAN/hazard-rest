package app.repository;

import app.model.Hazard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HazardRepository extends MongoRepository<Hazard, String>, HazardRepositoryCustom{

}
