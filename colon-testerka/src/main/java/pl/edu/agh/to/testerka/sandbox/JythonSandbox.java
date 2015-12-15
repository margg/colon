package pl.edu.agh.to.testerka.sandbox;

import org.python.util.PythonInterpreter;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public class JythonSandbox implements Sandbox {
    @Override
    public TestResult execute(String pythonCode, String input, String correctOutput) {
        final Reader inputStream = new StringReader(input);
        final Writer outputStream = new StringWriter();
        PythonInterpreter interpreter = PythonInterpreterFactory.createPythonInterpreter(inputStream, outputStream);

        // TODO: handle TLE statuses
        try {
            long codeExecutionTime = measureExecutionTime(interpreter, pythonCode);
            TestResultStatus testResultStatus = getTestResultStatus(outputStream, correctOutput);
            return new TestResult(testResultStatus, codeExecutionTime);
        } catch (Exception e) {
            return new TestResult(TestResultStatus.RuntimeError, 0);
        }
    }

    private long measureExecutionTime(PythonInterpreter interpreter, String pythonCode) {
        final long startTime = System.currentTimeMillis();
        interpreter.exec(pythonCode);
        final long finishTime = System.currentTimeMillis();
        return finishTime - startTime;
    }

    private TestResultStatus getTestResultStatus(Writer outputStream, String correctOutput) {
        final String codeOutput = outputStream.toString();
        if (codeOutput.equals(correctOutput)) {
            return TestResultStatus.OK;
        } else {
            return TestResultStatus.Answer;
        }
    }
}
