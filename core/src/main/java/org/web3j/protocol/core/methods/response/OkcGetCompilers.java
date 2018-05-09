package org.web3j.protocol.core.methods.response;

import java.util.List;

import org.web3j.protocol.core.Response;

/**
 * okc_getCompilers.
 */
public class OkcGetCompilers extends Response<List<String>> {
    public List<String> getCompilers() {
        return getResult();
    }
}
