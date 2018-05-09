package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * okc_sendRawTransaction.
 */
public class OkcSendRawTransaction extends Response<String> {
    public String getTransactionHash() {
        return getResult();
    }
}
