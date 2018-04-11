package com.mariano.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mariano.weather.model.Location;
import com.mariano.weather.model.dto.LocationDTO;
import com.mariano.weather.service.impl.LocationService;

@RestController
public class LocationController {

	@Autowired
	LocationService service;
	
	@RequestMapping(value="/boards/{user}/{boardId}/locations",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Location> addLocation(@PathVariable String user, @PathVariable Integer boardId,@RequestBody LocationDTO locationDTO) {
		Location newLocation=service.addLocation(locationDTO.getCity(), boardId);
		if(newLocation==null)
			return new ResponseEntity<Location>(newLocation,HttpStatus.NOT_FOUND);
		return new ResponseEntity<Location>(newLocation,HttpStatus.CREATED);
	}
	@RequestMapping(value="/boards/{user}/{boardId}/locations/{location}",
			method=RequestMethod.DELETE,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removeLocation(@PathVariable String user, @PathVariable Integer boardId,@PathVariable Integer location) {
		service.removeLocation(location);
		return ResponseEntity.ok("Location deleted");

	}
}