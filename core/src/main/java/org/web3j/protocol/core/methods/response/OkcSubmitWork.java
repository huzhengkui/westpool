package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * okc_submitWork.
 */
public class OkcSubmitWork extends Response<Boolean> {

    public boolean solutionValid() {
        return getResult();
    }
}
