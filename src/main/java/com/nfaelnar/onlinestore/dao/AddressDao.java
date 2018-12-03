package com.nfaelnar.onlinestore.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.nfaelnar.onlinestore.base.BaseDao;
import com.nfaelnar.onlinestore.model.Address;

@Repository
public class AddressDao extends BaseDao {
	
	private final String GET_ALL_USER_ADDRESS = "SELECT * FROM address WHERE user_id = ?";
	private final String GET_USER_ADDRESS = "SELECT * FROM address WHERE address_id = ?";
	private final String INSERT_ADDRESS = "INSERT INTO address (user_id, house_bldg_st, brgy, city, prov) VALUES (?, ?, ?, ?, ?)";
	private final String UPDATE_ADDRESS = "UPDATE address SET house_bldg_st = ? , brgy = ?, city = ?, prov = ? WHERE address_id = ? AND user_id = ?";
	private final String DELETE_ADDRESS = "DELETE FROM address WHERE address_id = ? AND user_id = ?";

	public List<Address> getUserAddresses(int userId) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Address> userAddress = getJdbcTemplate().query(GET_ALL_USER_ADDRESS, new Object[] { userId}, new BeanPropertyRowMapper(Address.class));
		return userAddress;
	}
	
	public Address getAddress( int addressId) {
		Address address = (Address) getJdbcTemplate().queryForObject(GET_USER_ADDRESS, new Object[] {addressId },
				new BeanPropertyRowMapper<>(Address.class));
		return address;
	}
	
	public void addAddress(Address address) {
		getJdbcTemplate().update(INSERT_ADDRESS, address.getUserId(), address.getHouseBldgSt(),
				address.getBrgy(), address.getCity(), address.getProvince());
	}
	
	public void updateAddress(Address address) {
		getJdbcTemplate().update(UPDATE_ADDRESS, address.getHouseBldgSt(), address.getBrgy(),
				address.getCity(), address.getProvince(), address.getAddressId(), address.getUserId());
	}
	
	public void deleteAddress(Address address) {
		getJdbcTemplate().update(DELETE_ADDRESS, address.getAddressId(), address.getUserId());
	}
	
	
}
