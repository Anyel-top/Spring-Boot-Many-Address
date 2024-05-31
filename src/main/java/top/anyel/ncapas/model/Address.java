package top.anyel.ncapas.model;

import top.anyel.ncapas.model.enums.EnumAddressType;

import java.util.Objects;

public record Address(
        String main_street,
        String secundary_street,
        String n_house,
        String city,
        int postal_code,
        EnumAddressType addresType )
        {

        }
