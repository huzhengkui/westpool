package org.web3j.protocol.parity.methods.response;

import java.util.ArrayList;

import org.web3j.protocol.core.Response;

/**
 * parity_listAccounts
 * parity_getGetDappAddresses
 * parity_getGetNewDappsAddresses
 * parity_importGokcAccounts
 * parity_listGokcAccounts.
 */
public class ParityAddressesResponse extends Response<ArrayList<String>> {
    public ArrayList<String> getAddresses() {
        return getResult();
    }
}
