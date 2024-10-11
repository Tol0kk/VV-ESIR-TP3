package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TLSSocketFactoryTestMocks {

    @Test
    public void preparedSocket_NullProtocols() {
        TLSSocketFactory f = new TLSSocketFactory();

        SSLSocket socket = mock(SSLSocket.class);

        // Mock SSLSocket interface
        when(socket.getSupportedProtocols()).thenReturn(null);
        when(socket.getEnabledProtocols()).thenReturn(null);

        // Run 
        f.prepareSocket(socket);

        // Verify that setEnabledProtocols has not been call 
        verify(socket, times(0)).setEnabledProtocols(new String[] {});
    }

    @Test
    public void typical() {
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket socket = mock(SSLSocket.class);

        when(socket.getSupportedProtocols()).thenReturn(shuffle(new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2" }));
        when(socket.getEnabledProtocols()).thenReturn(shuffle(new String[] { "SSLv3", "TLSv1" }));
        f.prepareSocket(socket);

        verify(socket, times(1)).setEnabledProtocols(new String[] { "TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" });
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }


}