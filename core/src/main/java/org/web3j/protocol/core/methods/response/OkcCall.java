package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * okc_call.
 */
public class OkcCall extends Response<String> {
    public String getValue() {
        return getResult();
    }
}
