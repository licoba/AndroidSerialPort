package com.temon.serial.easy;

import com.temon.serial.core.SerialConfig;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EasySerialCompatibilityTest {
    @Test
    public void keepsLegacyAndConfigurableOpenMethods() throws Exception {
        Method legacy = EasySerial.class.getMethod("open", String.class, int.class);
        Method configurable = EasySerial.class.getMethod("open", String.class, int.class, int.class);

        assertNotNull(legacy);
        assertNotNull(configurable);
    }

    @Test
    public void serialConfigAcceptsCallerDefinedSendInterval() {
        SerialConfig immediate = new SerialConfig.Builder()
                .port("/dev/ttyS1")
                .baudRate(19200)
                .sendIntervalMs(0)
                .build();
        SerialConfig throttled = new SerialConfig.Builder()
                .port("/dev/ttyS1")
                .baudRate(19200)
                .sendIntervalMs(300)
                .build();

        assertEquals(0, immediate.sendIntervalMs);
        assertEquals(300, throttled.sendIntervalMs);
    }
}
