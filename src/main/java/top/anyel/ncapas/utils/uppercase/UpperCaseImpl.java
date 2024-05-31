package top.anyel.ncapas.utils.uppercase;

import top.anyel.ncapas.utils.uppercase.UpperCase;

public class UpperCaseImpl implements UpperCase {
    public String upperCase(String texto){
        if (texto == null) {
            return "";
        }
        return texto.toUpperCase();
    }
}
