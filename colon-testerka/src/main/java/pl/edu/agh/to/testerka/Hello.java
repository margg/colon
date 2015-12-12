package pl.edu.agh.to.testerka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hello {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hello.class);

    public static void main(String[] args) {

        RequestProvider requestProvider = new RequestProvider();
        requestProvider.gets();
        FileProvider fileProvider = new FileProvider();
        fileProvider.gets();

    }
}