package pl.edu.agh.to.testerka.sandbox;

import org.python.util.PythonInterpreter;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public class JythonSandbox implements Sandbox {
    static final int TIME_LIMIT = 1000;

    @Override
    public TestResult execute(String pythonCode, String input, String correctOutput) {
        final Reader inputStream = new StringReader(input);
        final Writer outputStream = new StringWriter();
        PythonInterpreter interpreter = PythonInterpreterFactory.createPythonInterpreter(inputStream, outputStream);

        try {
            long codeExecutionTime = measureExecutionTime(interpreter, pythonCode);
            TestResultStatus testResultStatus = getTestResultStatus(outputStream, correctOutput);
            return new TestResult(testResultStatus, codeExecutionTime);
        } catch (TimeLimitExceededException e) {
            return new TestResult(TestResultStatus.TIME_LIMIT_EXCEEDED);
        } catch (Exception e) {
            return new TestResult(TestResultStatus.RUNTIME_ERROR);
        }
    }

    private long measureExecutionTime(PythonInterpreter interpreter, String pythonCode)
            throws InterruptedException, TimeLimitExceededException {
        final long startTime = System.currentTimeMillis();

        executeSafely(interpreter, pythonCode);

        final long finishTime = System.currentTimeMillis();
        return finishTime - startTime;
    }

    private void executeSafely(final PythonInterpreter interpreter, final String pythonCode)
            throws InterruptedException, TimeLimitExceededException {

        Thread interpreterThread = new Thread(() -> interpreter.exec(pythonCode));
        InterpreterSupervisor interpreterSupervisor = new InterpreterSupervisor(interpreterThread, TIME_LIMIT);
        interpreterSupervisor.runInterpreter();

        if (interpreterSupervisor.wasTimeLimitExceeded) {
            throw new TimeLimitExceededException("Timed out");
        }

        if (interpreterSupervisor.wasCaughtException) {
            throw new RuntimeException("Timed out");
        }
    }

    private TestResultStatus getTestResultStatus(Writer outputStream, String correctOutput) {
        final String codeOutput = outputStream.toString();
        if (codeOutput.equals(correctOutput)) {
            return TestResultStatus.OK;
        } else {
            return TestResultStatus.ANSWER;
        }
    }
}
