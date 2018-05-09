package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * okc_sign.
 */
public class OkcSign extends Response<String> {
    public String getSignature() {
        return getResult();
    }
}
