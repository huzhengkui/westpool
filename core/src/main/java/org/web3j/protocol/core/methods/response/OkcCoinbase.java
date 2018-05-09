package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * okc_coinbase.
 */
public class OkcCoinbase extends Response<String> {
    public String getAddress() {
        return getResult();
    }
}
