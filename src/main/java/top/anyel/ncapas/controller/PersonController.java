package top.anyel.ncapas.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.anyel.ncapas.model.Address;
import top.anyel.ncapas.model.Person;
import top.anyel.ncapas.service.PersonService;
import top.anyel.ncapas.service.PersonUpperCaseService;
import top.anyel.ncapas.utils.logger.CustomLoggerFactoryService;
import top.anyel.ncapas.utils.logger.ICustomLoggerService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/persons/v1")
public class PersonController {
    private final ICustomLoggerService loggerService;

    @Autowired
    private PersonService personService;

    @Autowired
    public PersonController(CustomLoggerFactoryService loggerFactoryService) {
        this.loggerService = loggerFactoryService.getLogger(PersonController.class);
    }

    @GetMapping("/")
    public void index(HttpServletResponse response) throws IOException {
        try {
            response.sendRedirect("/swagger-ui.html");
        }
        catch (IOException e) {
            loggerService.logError("Hubo un error"+e.getMessage());
        }
    }

    @PostMapping("/save")
    public Person save(@RequestBody Person person) {
        try{
            loggerService.logInfo("Guardando persona");
            Person personWithUpperCase = PersonUpperCaseService.toUpperCase(person);
            return personService.save(personWithUpperCase);
        }
        catch (Exception e){
            loggerService.logError("Error al guardar persona");
            return null;
        }

    }

    @GetMapping("/findAll")
    public List<Person> findAll(){
        try{

        return personService.findAll();
        }
        catch (Exception e){
            loggerService.logWarn("Error al buscar personas");
            return null;
        }
    }

    @GetMapping("/findById/{identification}")
    public Person findById(@PathVariable String identification){
        try{
        return personService.findById(identification);
        }
        catch (Exception e){
            loggerService.logError("Error al buscar persona");
            return null;
        }
    }

    @PutMapping("/updateById/{identification}")
    public Person updateById(@PathVariable String identification, @RequestBody Person person){
        try{
            return personService.updateById(identification, PersonUpperCaseService.toUpperCase(person));
        }
        catch (Exception e){
            loggerService.logError("Error al actualizar persona");
            return null;
        }

    }

    @DeleteMapping("/deleteById/{identification}")
    public String deleteById(@PathVariable String identification){
        return personService.deleteById(identification);
    }

    @GetMapping("/address/house/{identification}")
    public List<Address> findHouseAddressesById(@PathVariable String identification) {
        try {
            return personService.findHouseAddressesById(identification);
        } catch (Exception e) {
            loggerService.logError("Error al buscar direcciones de casa");
            return null;
        }
    }


}
