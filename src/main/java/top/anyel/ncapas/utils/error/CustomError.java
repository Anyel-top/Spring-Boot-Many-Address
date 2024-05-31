package top.anyel.ncapas.utils.error;

public class CustomError extends Exception {
   final String message;

    public CustomError(String message) {
        this.message = message;
    }
}
