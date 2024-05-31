package top.anyel.ncapas.utils.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLogger implements ICustomLoggerService {
    private final Logger logger;

    public CustomLogger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    public void logInfo(String message) {
        logger.info(message);
    }

    @Override
    public void logDebug(String message) {
        logger.debug(message);
    }

    @Override
    public void logWarn(String message) {
        logger.warn(message);
    }

    @Override
    public void logError(String message) {
        logger.error(message);
    }
}