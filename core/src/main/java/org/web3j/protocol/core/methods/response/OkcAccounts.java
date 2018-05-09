package org.web3j.protocol.core.methods.response;

import java.util.List;

import org.web3j.protocol.core.Response;

/**
 * okc_accounts.
 */
public class OkcAccounts extends Response<List<String>> {
    public List<String> getAccounts() {
        return getResult();
    }
}
