package org.web3j.protocol.scenarios;

import java.math.BigInteger;

import org.junit.Test;

import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.OkcGetTransactionCount;
import org.web3j.protocol.core.methods.response.OkcSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Create, sign and send a raw transaction.
 */
public class CreateRawTransactionIT extends Scenario {

    @Test
    public void testTransferOkcer() throws Exception {
        BigInteger nonce = getNonce(ALICE.getAddress());
        RawTransaction rawTransaction = createOkcerTransaction(
                nonce, BOB.getAddress());

        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, ALICE);
        String hexValue = Numeric.toHexString(signedMessage);

        OkcSendTransaction okcSendTransaction =
                web3j.okcSendRawTransaction(hexValue).sendAsync().get();
        String transactionHash = okcSendTransaction.getTransactionHash();

        assertFalse(transactionHash.isEmpty());

        TransactionReceipt transactionReceipt =
                waitForTransactionReceipt(transactionHash);

        assertThat(transactionReceipt.getTransactionHash(), is(transactionHash));
    }

    @Test
    public void testDeploySmartContract() throws Exception {
        BigInteger nonce = getNonce(ALICE.getAddress());
        RawTransaction rawTransaction = createSmartContractTransaction(nonce);

        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, ALICE);
        String hexValue = Numeric.toHexString(signedMessage);

        OkcSendTransaction okcSendTransaction =
                web3j.okcSendRawTransaction(hexValue).sendAsync().get();
        String transactionHash = okcSendTransaction.getTransactionHash();

        assertFalse(transactionHash.isEmpty());

        TransactionReceipt transactionReceipt =
                waitForTransactionReceipt(transactionHash);

        assertThat(transactionReceipt.getTransactionHash(), is(transactionHash));

        assertFalse("Contract execution ran out of gas",
                rawTransaction.getGasLimit().equals(transactionReceipt.getGasUsed()));
    }

    private static RawTransaction createOkcerTransaction(BigInteger nonce, String toAddress) {
        BigInteger value = Convert.toWei("0.5", Convert.Unit.ETHER).toBigInteger();

        return RawTransaction.createOkcerTransaction(
                nonce, GAS_PRICE, GAS_LIMIT, toAddress, value);
    }

    private static RawTransaction createSmartContractTransaction(BigInteger nonce)
            throws Exception {
        return RawTransaction.createContractTransaction(
                nonce, GAS_PRICE, GAS_LIMIT, BigInteger.ZERO, getFibonacciSolidityBinary());
    }

    BigInteger getNonce(String address) throws Exception {
        OkcGetTransactionCount okcGetTransactionCount = web3j.okcGetTransactionCount(
                address, DefaultBlockParameterName.LATEST).sendAsync().get();

        return okcGetTransactionCount.getTransactionCount();
    }
}
