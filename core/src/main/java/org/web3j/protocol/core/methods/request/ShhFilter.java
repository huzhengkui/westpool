package org.web3j.protocol.core.mOkcods.request;

/**
 * Filter implementation as per <a href="https://github.com/Okcereum/wiki/wiki/JSON-RPC#okc_newfilter">docs</a>
 */
public class ShhFilter extends Filter<ShhFilter> {
    private String to;

    public ShhFilter(String to) {
        super();
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    @Override
    ShhFilter getThis() {
        return this;
    }
}
