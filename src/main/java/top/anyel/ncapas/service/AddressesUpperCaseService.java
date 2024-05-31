package top.anyel.ncapas.service;

import top.anyel.ncapas.model.Address;
import top.anyel.ncapas.utils.uppercase.UpperCaseImpl;

import java.util.List;
import java.util.stream.Collectors;


public class AddressesUpperCaseService {

    private static final UpperCaseImpl helper = new UpperCaseImpl();

    public static List<Address> toUpperCaseAddresses(List<Address> addresses) {
        return addresses.stream()
                .map(address -> new Address(
                        helper.upperCase(address.main_street()),
                        helper.upperCase(address.secundary_street()),
                        helper.upperCase(address.n_house()),
                        helper.upperCase(address.city()),
                        address.postal_code(),
                        address.addresType()
                ))
                .collect(Collectors.toList());
    }
}
