package com.bookstore.haid.service;

import com.bookstore.haid.mapper.AddressMapper;
import com.bookstore.haid.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressMapper addressMapper;
    public List<Address> findAddress(String username) {
        List<Address> address = addressMapper.findAddress(username);
        return address;
    }

    public void savaAddress(Address address) {
        addressMapper.savaAddress(address);
    }
}
