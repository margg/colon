package pl.edu.agh.to.testerka.sandbox;

import java.util.Timer;
import java.util.TimerTask;

public class InterpreterSupervisor {
    private final Timer timer = new Timer();
    private final Thread interpreterThread;

    boolean wasTimeLimitExceeded = false;
    boolean wasCaughtException = false;

    public InterpreterSupervisor(Thread interpreterThread, int millis) {
        interpreterThread.setUncaughtExceptionHandler((t, e) -> wasCaughtException = true);
        this.interpreterThread = interpreterThread;
        this.timer.schedule(new TimeLimit(), millis);
    }

    public void runInterpreter() throws InterruptedException {
        interpreterThread.start();
        interpreterThread.join();
        timer.cancel();
    }

    class TimeLimit extends TimerTask {
        public void run() {
            wasTimeLimitExceeded = true;
            interpreterThread.stop();
            timer.cancel();
        }
    }
}
