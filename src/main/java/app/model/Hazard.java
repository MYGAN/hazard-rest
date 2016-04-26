package app.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Hazard {
	  @Id
	  private String id;
	  private float longitude;
	  private float latitude;
	  private String time;
	  private String type;
	  private List affected_user;
	  
	  public Hazard() {
		  
	  }
	  
	  public Hazard(float longitude, float latitude, String time, String type, List affected_user) {
		  this.longitude = longitude;
		  this.latitude = latitude;
		  this.time = time;
		  this.type = type;
		  this.affected_user = affected_user;
	  }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List getAffected_user() {
		return affected_user;
	}

	public void setAffected_user(List affected_user) {
		this.affected_user = affected_user;
	}
	  
}
