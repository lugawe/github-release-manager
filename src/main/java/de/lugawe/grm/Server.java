package de.lugawe.grm;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Server {

    Server() {}

    static void main(String[] args) {
        Quarkus.run(args);
    }
}
