package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * okc_sendTransaction.
 */
public class OkcSendTransaction extends Response<String> {
    public String getTransactionHash() {
        return getResult();
    }
}
