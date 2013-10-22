package com.smartcampus.soap.database;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Locations")
public class UserLocationPOJOList {

	@XmlElement(name="Location")
	List<UserLocationPOJO> locationListing=new ArrayList<>();

	public List<UserLocationPOJO> getLocationList() {
		return locationListing;
	}

	public void setLocationList(List<UserLocationPOJO> locationList) {
		this.locationListing = locationList;
	}
	
	
	
	
}
