package Utilities;

import org.apache.logging.log4j.LogManager;

public class LogsUtil {
    public static String LOGS_Path = "TestOutputs/logs";

    public static void trace(String message){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .trace(message);
    }

    public static void debug(String message){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .debug(message);
    }

    public static void info(String message){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .info(message);
    }

    public static void Warn(String message){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .warn(message);
    }
    public static void Error(String message){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .error(message);
    }
    public static void fatal(String message){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .fatal(message);
    }
}
