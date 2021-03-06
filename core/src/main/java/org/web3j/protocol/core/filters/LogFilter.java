package org.web3j.protocol.core.filters;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.OkcFilter;
import org.web3j.protocol.core.methods.response.OkcLog;


/**
 * Log filter handler.
 */
public class LogFilter extends Filter<Log> {

    private final org.web3j.protocol.core.methods.request.OkcFilter okcFilter;

    public LogFilter(
            Web3j web3j, Callback<Log> callback,
            org.web3j.protocol.core.methods.request.OkcFilter okcFilter) {
        super(web3j, callback);
        this.okcFilter = okcFilter;
    }


    @Override
    OkcFilter sendRequest() throws IOException {
        return web3j.okcNewFilter(okcFilter).send();
    }

    @Override
    void process(List<OkcLog.LogResult> logResults) {
        for (OkcLog.LogResult logResult : logResults) {
            if (logResult instanceof OkcLog.LogObject) {
                Log log = ((OkcLog.LogObject) logResult).get();
                callback.onEvent(log);
            } else {
                throw new FilterException(
                        "Unexpected result type: " + logResult.get() + " required LogObject");
            }
        }
    }

    @Override
    protected Optional<Request<?, OkcLog>> getFilterLogs(BigInteger filterId) {
        return Optional.of(web3j.okcGetFilterLogs(filterId));
    }
}
