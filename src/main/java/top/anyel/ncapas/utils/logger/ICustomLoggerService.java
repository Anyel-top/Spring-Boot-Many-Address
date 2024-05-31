package top.anyel.ncapas.utils.logger;

public interface ICustomLoggerService {
    void logInfo(String message);
    void logDebug(String message);
    void logWarn(String message);
    void logError(String message);

}
