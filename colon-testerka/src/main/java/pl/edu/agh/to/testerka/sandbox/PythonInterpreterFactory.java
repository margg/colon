package pl.edu.agh.to.testerka.sandbox;

import org.python.util.PythonInterpreter;

import java.io.Reader;
import java.io.Writer;

abstract class PythonInterpreterFactory {
    public static PythonInterpreter createPythonInterpreter(Reader inputStream, Writer outputStream) {
        final PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.setIn(inputStream);
        interpreter.setOut(outputStream);
        return interpreter;
    }
}
