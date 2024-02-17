package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.UserDetails;
import pojo.UserAddress;

public class TestDataBuild {

	
	
	public UserDetails addPlacePayLoad(String firstname, String lastname, long contact, 
			String mail,String country,int zip,String state,String plot,String street)
	{
		UserDetails p =new UserDetails();
		p.setUser_first_name(firstname);
		p.setUser_last_name(lastname);
		p.setUser_contact_number(contact);
		p.setUser_email_id(mail);
		UserAddress addressobj =new UserAddress();
		addressobj.setPlotNumber(plot);
		addressobj.setStreet(street);
		addressobj.setState(state);
		addressobj.setCountry(country);
		addressobj.setZipCode(zip);
		p.setUserAddress(addressobj);
		return p;
	}
	
	
}
