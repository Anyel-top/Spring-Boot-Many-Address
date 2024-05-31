package top.anyel.ncapas.utils.logger;


import org.springframework.stereotype.Component;

@Component
public class CustomLoggerFactoryService {
    public CustomLogger getLogger(Class<?> clazz) {
        return new CustomLogger(clazz);
    }
}
