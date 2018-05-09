package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * okc_uninstallFilter.
 */
public class OkcUninstallFilter extends Response<Boolean> {
    public boolean isUninstalled() {
        return getResult();
    }
}
