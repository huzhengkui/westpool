package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * okc_mining.
 */
public class OkcMining extends Response<Boolean> {
    public boolean isMining() {
        return getResult();
    }
}
