package top.anyel.ncapas.repository;

import org.springframework.stereotype.Repository;
import top.anyel.ncapas.model.Address;
import top.anyel.ncapas.model.Person;
import top.anyel.ncapas.model.enums.EnumAddressType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonRepository {

    List<Person> persons = new ArrayList<>(); // create and initialize a list of persons

    public Person save (Person person){
        persons.add(person);
        return person;
    }

    public List<Person> findAll(){
        return persons;
    }

    public Person findById(String identification){
        for (Person person : persons) {
            if (person.identification().equals(identification)) {
                return person;
            }
        }
        return null;
    }

    public Person updateById(String identification, Person person){
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).identification().equals(identification)) {
                persons.set(i, person);
                return person;
            }
        }
        return null;
    }

    public String deleteById(String identification){
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).identification().equals(identification)) {
                persons.remove(i);
                return "Person with identification " + identification + " was deleted";
            }
        }
        return "Person with identification " + identification + " was not found";
    }

    public List<Address> findHouseAddressesById(String identification) {

        Person person = findById(identification);
        if (person != null) {
            return person.address().stream()
                    .filter(address -> address.addresType() == EnumAddressType.house)
                    .collect(Collectors.toList());
        }
        return null;
    }
}
