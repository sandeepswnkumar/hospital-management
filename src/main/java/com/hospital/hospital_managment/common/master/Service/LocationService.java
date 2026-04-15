package com.hospital.hospital_managment.common.master.Service;

import com.hospital.hospital_managment.common.master.dto.CityResponse;
import com.hospital.hospital_managment.common.master.dto.CountryResponse;
import com.hospital.hospital_managment.common.master.dto.StateResponse;
import com.hospital.hospital_managment.common.master.model.City;
import com.hospital.hospital_managment.common.master.model.Country;
import com.hospital.hospital_managment.common.master.model.State;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    public CityResponse mapToCity(City city){
        CityResponse cityResponse = new CityResponse();
        cityResponse.setId(city.getId());
        cityResponse.setName(city.getName());
        cityResponse.setLongitude(city.getLongitude());
        cityResponse.setLatitude(city.getLatitude());
        cityResponse.setState(mapToState(city.getState()));
        return cityResponse;
    }

    public StateResponse mapToState(State state){
        StateResponse stateResponse = new StateResponse();
        stateResponse.setId(state.getId());
        stateResponse.setName(state.getName());
        stateResponse.setStateCode(state.getStateCode());
        stateResponse.setCountry(mapToCountry(state.getCountry()));
        return stateResponse;
    }

    public CountryResponse mapToCountry(Country country){
        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setId(country.getId());
        countryResponse.setName(country.getName());
        countryResponse.setIsoCode(country.getIsoCode());
        countryResponse.setPhoneCode(country.getPhoneCode());
        return countryResponse;
    }
}
