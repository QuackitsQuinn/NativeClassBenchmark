""" Custom logger because im stubborn and want colors """
from enum import Enum

class Logger:
    class Level(Enum):
        DEBUG = 0
        INFO = 1
        WARNING = 2
        ERROR = 3
        FATAL = 4
    def __init__(self,name: str, level: Level) -> None:
        self.name = name
        self.level = level
    def _should_log(self, level: Level) -> bool:
        return self.level.value <= level.value
    def debug(self, message: str) -> None:
        """Log a debug message. """
        if self._should_log(self.Level.DEBUG):
            print(f"\033[0;34mDEBUG from {self.name}: {message}\033[0m")
    def info(self, message: str) -> None:
        """Log an info message."""
        if self._should_log(self.Level.INFO):
            print(f"\033[0;32mINFO from {self.name}\033[0m: {message}")
    def warning(self, message: str) -> None:
        """Log a warning message."""
        if self._should_log(self.Level.WARNING):
            print(f"\033[0;33mWARNING from {self.name}\033[0m: {message}")
    def error(self, message: str) -> None:
        """Log an error message."""
        if self._should_log(self.Level.ERROR):
            print(f"\033[0;31mERROR from {self.name}\033[0m: {message}")
    def fatal(self, message: str) -> None:
        """Log a fatal message."""
        if self._should_log(self.Level.FATAL):
            print(f"\033[0;31;1mFATAL from {self.name}\033[0m: {message}")
    def success(self, message: str) -> None:
        """Log a success message."""
        print(f"\033[0;32;1mSUCCESS: {message}\033[0m")
    def failure(self, message: str) -> None:
        """Log a failure message."""
        print(f"\033[0;31;1mFAIL: {message}\033[0m")


if __name__ == "__main__":
    logger = Logger("test", Logger.Level.DEBUG)
    logger.debug("This is a debug message!")
    logger.info("This is an info message!")
    logger.warning("This is a warning message!")
    logger.error("This is an error message!")
    logger.fatal("This is a fatal message!")
    logger.success("This is a success message!")
    logger.failure("This is a failure message!")