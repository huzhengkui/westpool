package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * okc_compileLLL.
 */
public class OkcCompileLLL extends Response<String> {
    public String getCompiledSourceCode() {
        return getResult();
    }
}
