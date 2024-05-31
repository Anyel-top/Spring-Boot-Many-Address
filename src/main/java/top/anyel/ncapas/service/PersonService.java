package top.anyel.ncapas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.anyel.ncapas.model.Address;
import top.anyel.ncapas.model.Person;
import top.anyel.ncapas.model.enums.EnumAddressType;
import top.anyel.ncapas.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired //in Spring is used to inject a bean in another bean, in this case, PersonRepository is a bean
    private PersonRepository personRepository; // access to methos in PersonRepository

    public Person save(Person person){
        return personRepository.save(person);
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person findById(String identification){
        return personRepository.findById(identification);
    }

    public Person updateById(String identification, Person person){
        return personRepository.updateById(identification, person);
    }

    public String deleteById(String identification){
        return personRepository.deleteById(identification);
    }

    public List<Address> findHouseAddressesById(String identification) {
        return personRepository.findHouseAddressesById(identification);
    }




}
