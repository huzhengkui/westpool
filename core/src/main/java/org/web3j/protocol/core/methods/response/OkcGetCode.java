package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * okc_getCode.
 */
public class OkcGetCode extends Response<String> {
    public String getCode() {
        return getResult();
    }
}
