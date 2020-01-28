package com.flares.countryInfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flares.countryInfo.model.Country;
import com.flares.countryInfo.services.CountryDao;
import com.flares.countryInfo.services.CountryLanguageDao;

@RestController
public class CountryApi {
	
	@Autowired
	CountryDao countryDao;
	
	@Autowired
	CountryLanguageDao countryLanDao;

	@RequestMapping("/{countryCode}")
	public ResponseEntity<Object> getCountry(@PathVariable String countryCode) {
		ResponseEntity<Object> resp;
		try {
			Country country = countryDao.getCountryByCode(countryCode);

			if(null != country) {
				 resp = new ResponseEntity<Object>(country, HttpStatus.OK);
			}else {
				resp = new ResponseEntity<Object>("INVALID_COUNTRY_CODE",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}catch (Exception e) {
			resp = new ResponseEntity<Object>("INTERNAL_ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return resp;
	}
}
