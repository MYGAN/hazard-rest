package app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.repository.HazardRepository;
import app.model.Hazard;

@RestController
@RequestMapping("/hazard")
public class HazardController {
	
	@Autowired
	private HazardRepository hazardRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Hazard>> listAllHazard() {
		List<Hazard> hazard = hazardRepository.findAll();
		
		return new ResponseEntity<List<Hazard>>(hazard, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{hazardId}")
	public ResponseEntity<Hazard> getHazardById(@PathVariable("hazardId") String hazardId) {
		Hazard currentHazard = hazardRepository.findOne(hazardId);
		
		return new ResponseEntity<Hazard>(currentHazard, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/search")
	public ResponseEntity<List<Hazard>> searchHazardByAffectedUser(@RequestParam("affected_user") String[] affected_user, @RequestParam("type") String type) {
		
		List<Hazard> currentHazard = hazardRepository.searchHazardByAffectedUsers(affected_user, type);
		
		return new ResponseEntity<List<Hazard>>(currentHazard, HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createHazard(@RequestBody Hazard hazard) {
		Hazard newHazard = new Hazard(hazard.getLatitude(),
				hazard.getLongitude(),
				hazard.getTime(),
				hazard.getType(),
				hazard.getAffected_user());
		
		hazardRepository.save(newHazard);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{hazardId}")
	public ResponseEntity<Hazard> updateHazard(@PathVariable("hazardId") String hazardId, @RequestBody Hazard hazard) {
		Hazard currentHazard = hazardRepository.findOne(hazardId);
		
		currentHazard.setLatitude(hazard.getLatitude());
		currentHazard.setLongitude(hazard.getLongitude());
		currentHazard.setTime(hazard.getTime());
		currentHazard.setType(hazard.getType());
		currentHazard.setAffected_user(hazard.getAffected_user());
		
		hazardRepository.save(currentHazard);
		
		return new ResponseEntity<Hazard>(currentHazard, HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{hazardId}")
	public ResponseEntity<Hazard> deleteHazard(@PathVariable("hazardId") String hazardId) {
		hazardRepository.delete(hazardId);
		
		return new ResponseEntity<Hazard>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Hazard> deleteHazard() {
		hazardRepository.deleteAll();;
		
		return new ResponseEntity<Hazard>(HttpStatus.NO_CONTENT);
	}
}
