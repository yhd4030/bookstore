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

    public Address findById(Integer id) {
        return addressMapper.findById(id);
    }

    public Boolean delById(Integer addressId) {
        return addressMapper.delById(addressId);
    }

    public void savaOrUpdate(Address address) {
        if (address.getId() != null) {
            isDefault(address.getId());
            if (address.getIs_default() == null || address.getIs_default().equals("")) {
                address.setIs_default("off");
            }
            addressMapper.updateAddress(address);
            System.out.println("更新地址成功");
        } else {
            isDefault(address.getId());
            if (address.getIs_default() == null || address.getIs_default().equals("")) {
                address.setIs_default("off");
            }
            addressMapper.savaAddress(address);
            System.out.println("新增地址成功");
        }
    }

    //判断在新增或者保存收货地址之前是否已经存在默认收货地址，如果有就取消。
    protected void isDefault(Integer id) {
        Address isDefault = addressMapper.findIsDefault();
        if (isDefault != null) {
            if (id != isDefault.getId()) {
                addressMapper.updateIsDefault(isDefault);
            }
        }


    }
}
