package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TLSSocketFactoryTest {

    /**
     * Test when the edge case when the both supported and enabled protocols are
     * null.
     */
    @Test
    public void preparedSocket_NullProtocols() {
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket socket = new SSLSocket() {

            @Override
            public String[] getSupportedProtocols() {
                return null;
            }

            @Override
            public String[] getEnabledProtocols() {
                return null;
            }

            @Override
            public void setEnabledProtocols(String[] protocols) {
                fail();
            }
        };
        f.prepareSocket(socket);
    }

    @Test
    public void typical() {
        TLSSocketFactory f = new TLSSocketFactory();
        String [] enabled = new String[]{}; 
        SSLSocket socket = new SSLSocket() {
            @Override
            public String[] getSupportedProtocols() {
                return shuffle(new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2" });
            }

            @Override
            public String[] getEnabledProtocols() {
                return shuffle(new String[] { "SSLv3", "TLSv1" });
            }

            @Override
            public void setEnabledProtocols(String[] protocols) {
                assertTrue(
                Arrays.equals(protocols, new String[] { "TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" }));
            }
        };
        f.prepareSocket(socket);
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

}