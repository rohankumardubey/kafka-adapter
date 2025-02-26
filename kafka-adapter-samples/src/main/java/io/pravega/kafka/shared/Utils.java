/**
 * Copyright (c) Dell Inc., or its subsidiaries. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package io.pravega.kafka.shared;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import io.pravega.kafka.sampleapps.ProducerAndConsumerAppWithMinimalKafkaConfig;

@Slf4j
public class Utils {

    @SuppressWarnings("checkstyle:regexp")
    public static void waitForEnterToContinue(String message) {
        System.out.println("\n" + message + "\n");
        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Properties loadConfigFromClasspath(String propertiesFileName) {
        Properties props = new Properties();
        try (InputStream input = ProducerAndConsumerAppWithMinimalKafkaConfig.class.getClassLoader()
                .getResourceAsStream(propertiesFileName)) {
            if (input == null) {
                log.error("Unable to find app.properties in classpath");
            }
            props.load(input);
        } catch (IOException e) {
            log.error("Unable to load app.properties from classpath");
            throw new RuntimeException(e);
        }
        return props;
    }
}
