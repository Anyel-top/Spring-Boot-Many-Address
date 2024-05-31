package top.anyel.ncapas.model;

import java.util.List;

public record Person(String identification,
                     String firstName,
                     String lastName,
                     String email,
                     List<Address> address
) {

}
