package top.anyel.ncapas.service.uppercase;

import top.anyel.ncapas.model.Person;
import top.anyel.ncapas.utils.uppercase.UpperCaseImpl;

import static top.anyel.ncapas.service.uppercase.AddressesUpperCaseService.toUpperCaseAddresses;


public class PersonUpperCaseService {

    private static final UpperCaseImpl helper = new UpperCaseImpl();

    public static Person toUpperCase(Person person) {
        return new Person(
                helper.upperCase(person.identification()),
                helper.upperCase(person.firstName()),
                helper.upperCase(person.lastName()),
                helper.upperCase(person.email()),
                toUpperCaseAddresses(person.address())
        );
    }
}
