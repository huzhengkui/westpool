package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * okc_getStorageAt.
 */
public class OkcGetStorageAt extends Response<String> {
    public String getData() {
        return getResult();
    }
}
