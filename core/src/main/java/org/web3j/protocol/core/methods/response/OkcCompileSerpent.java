package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * okc_compileSerpent.
 */
public class OkcCompileSerpent extends Response<String> {
    public String getCompiledSourceCode() {
        return getResult();
    }
}
