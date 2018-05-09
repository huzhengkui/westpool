package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * okc_protocolVersion.
 */
public class OkcProtocolVersion extends Response<String> {
    public String getProtocolVersion() {
        return getResult();
    }
}
