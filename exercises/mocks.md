# Mocks to the rescue

The classes `SSLSocket`, `TLSProtocol` and `TLSSocketFactory` are included in the `sockets` package of the [`tp3-ssl`](../code/tp3-ssl) project.

The test class `TLSSocketFactoryTest` tests `TLSSocketFactory` and manually builds stubs and mocks for SSLSocket objects.

Rewrite these tests with the help of Mockito.

The initial tests fail to completely test the `TLSSockeetFactory`. In fact, if we *entirely* remove the code inside the body of `prepareSocket` no test case fails.

Propose a solution to this problem in your new Mockito-based test cases.

# Answer 

The tests given in `TLSSocketFactoryTest` are testing `TLSSocketFactory` and `SSLSocket` at the same time. We can use Mockito to do test only on  `TLSSocketFactory` by simulating `SSLSocket` inside the test. 

## preparedSocket_NullProtocols()

The test `preparedSocket_NullProtocols()` can only fail if `setEnableProtocols()` is call. 
If we remove the content of `prepareSocket()` that call `setEnableProtocols()`. It is sure that `preparedSocket_NullProtocols()` will not failed.  
So with a mock we can test if `setEnableProtocols()` is call, we fail. 

```java
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
```

## typical()

The test `typical()` need to simulate the socket interface well. We also need to make sure that  `setEnabledProtocols()` is call with the right parameters. If the code inside `prepareSocket()` is deleted, `setEnabledProtocols()` will not be call thus the test will failed as we want. 

```java
@Test
public void typical() {
    TLSSocketFactory f = new TLSSocketFactory();
    SSLSocket socket = mock(SSLSocket.class);

    when(socket.getSupportedProtocols()).thenReturn(shuffle(new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2" }));
    when(socket.getEnabledProtocols()).thenReturn(shuffle(new String[] { "SSLv3", "TLSv1" }));
    f.prepareSocket(socket);

    verify(socket, times(1)).setEnabledProtocols(new String[] { "TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" });
}
```


