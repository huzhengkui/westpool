package org.web3j.protocol.core;

import java.math.BigInteger;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.NetListening;
import org.web3j.protocol.core.methods.response.NetPeerCount;
import org.web3j.protocol.core.methods.response.NetVersion;
import org.web3j.protocol.core.methods.response.OkcAccounts;
import org.web3j.protocol.core.methods.response.OkcBlock;
import org.web3j.protocol.core.methods.response.OkcBlockNumber;
import org.web3j.protocol.core.methods.response.OkcCall;
import org.web3j.protocol.core.methods.response.OkcCoinbase;
import org.web3j.protocol.core.methods.response.OkcCompileLLL;
import org.web3j.protocol.core.methods.response.OkcCompileSerpent;
import org.web3j.protocol.core.methods.response.OkcCompileSolidity;
import org.web3j.protocol.core.methods.response.OkcEstimateGas;
import org.web3j.protocol.core.methods.response.OkcFilter;
import org.web3j.protocol.core.methods.response.OkcGasPrice;
import org.web3j.protocol.core.methods.response.OkcGetBalance;
import org.web3j.protocol.core.methods.response.OkcGetBlockTransactionCountByHash;
import org.web3j.protocol.core.methods.response.OkcGetBlockTransactionCountByNumber;
import org.web3j.protocol.core.methods.response.OkcGetCode;
import org.web3j.protocol.core.methods.response.OkcGetCompilers;
import org.web3j.protocol.core.methods.response.OkcGetStorageAt;
import org.web3j.protocol.core.methods.response.OkcGetTransactionCount;
import org.web3j.protocol.core.methods.response.OkcGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.OkcGetUncleCountByBlockHash;
import org.web3j.protocol.core.methods.response.OkcGetUncleCountByBlockNumber;
import org.web3j.protocol.core.methods.response.OkcHashrate;
import org.web3j.protocol.core.methods.response.OkcLog;
import org.web3j.protocol.core.methods.response.OkcMining;
import org.web3j.protocol.core.methods.response.OkcProtocolVersion;
import org.web3j.protocol.core.methods.response.OkcSendTransaction;
import org.web3j.protocol.core.methods.response.OkcSyncing;
import org.web3j.protocol.core.methods.response.OkcTransaction;
import org.web3j.protocol.core.methods.response.OkcUninstallFilter;

import org.web3j.protocol.core.methods.response.ShhNewGroup;
import org.web3j.protocol.core.methods.response.ShhNewIdentity;
import org.web3j.protocol.core.methods.response.ShhVersion;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.core.methods.response.Web3Sha3;
import org.web3j.protocol.http.HttpService;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * JSON-RPC 2.0 Integration Tests.
 */
public class CoreIT {

    private Web3j web3j;

    private IntegrationTestConfig config = new TestnetConfig();

    public CoreIT() { }

    @Before
    public void setUp() {
        this.web3j = Web3j.build(new HttpService());
    }

    @Test
    public void testWeb3ClientVersion() throws Exception {
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        System.out.println("Okcereum client version: " + clientVersion);
        assertFalse(clientVersion.isEmpty());
    }

    @Test
    public void testWeb3Sha3() throws Exception {
        Web3Sha3 web3Sha3 = web3j.web3Sha3("0x68656c6c6f20776f726c64").send();
        assertThat(web3Sha3.getResult(),
                is("0x47173285a8d7341e5e972fc677286384f802f8ef42a5ec5f03bbfa254cb01fad"));
    }

    @Test
    public void testNetVersion() throws Exception {
        NetVersion netVersion = web3j.netVersion().send();
        assertFalse(netVersion.getNetVersion().isEmpty());
    }

    @Test
    public void testNetListening() throws Exception {
        NetListening netListening = web3j.netListening().send();
        assertTrue(netListening.isListening());
    }

    @Test
    public void testNetPeerCount() throws Exception {
        NetPeerCount netPeerCount = web3j.netPeerCount().send();
        assertTrue(netPeerCount.getQuantity().signum() == 1);
    }

    @Test
    public void testOkcProtocolVersion() throws Exception {
        OkcProtocolVersion okcProtocolVersion = web3j.okcProtocolVersion().send();
        assertFalse(okcProtocolVersion.getProtocolVersion().isEmpty());
    }

    @Test
    public void testOkcSyncing() throws Exception {
        OkcSyncing okcSyncing = web3j.okcSyncing().send();
        assertNotNull(okcSyncing.getResult());
    }

    @Test
    public void testOkcCoinbase() throws Exception {
        OkcCoinbase okcCoinbase = web3j.okcCoinbase().send();
        assertNotNull(okcCoinbase.getAddress());
    }

    @Test
    public void testOkcMining() throws Exception {
        OkcMining okcMining = web3j.okcMining().send();
        assertNotNull(okcMining.getResult());
    }

    @Test
    public void testOkcHashrate() throws Exception {
        OkcHashrate okcHashrate = web3j.okcHashrate().send();
        assertThat(okcHashrate.getHashrate(), is(BigInteger.ZERO));
    }

    @Test
    public void testOkcGasPrice() throws Exception {
        OkcGasPrice okcGasPrice = web3j.okcGasPrice().send();
        assertTrue(okcGasPrice.getGasPrice().signum() == 1);
    }

    @Test
    public void testOkcAccounts() throws Exception {
        OkcAccounts okcAccounts = web3j.okcAccounts().send();
        assertNotNull(okcAccounts.getAccounts());
    }

    @Test
    public void testOkcBlockNumber() throws Exception {
        OkcBlockNumber okcBlockNumber = web3j.okcBlockNumber().send();
        assertTrue(okcBlockNumber.getBlockNumber().signum() == 1);
    }

    @Test
    public void testOkcGetBalance() throws Exception {
        OkcGetBalance okcGetBalance = web3j.okcGetBalance(
                config.validAccount(), DefaultBlockParameter.valueOf("latest")).send();
        assertTrue(okcGetBalance.getBalance().signum() == 1);
    }

    @Test
    public void testOkcGetStorageAt() throws Exception {
        OkcGetStorageAt okcGetStorageAt = web3j.okcGetStorageAt(
                config.validContractAddress(),
                BigInteger.valueOf(0),
                DefaultBlockParameter.valueOf("latest")).send();
        assertThat(okcGetStorageAt.getData(), is(config.validContractAddressPositionZero()));
    }

    @Test
    public void testOkcGetTransactionCount() throws Exception {
        OkcGetTransactionCount okcGetTransactionCount = web3j.okcGetTransactionCount(
                config.validAccount(),
                DefaultBlockParameter.valueOf("latest")).send();
        assertTrue(okcGetTransactionCount.getTransactionCount().signum() == 1);
    }

    @Test
    public void testOkcGetBlockTransactionCountByHash() throws Exception {
        OkcGetBlockTransactionCountByHash okcGetBlockTransactionCountByHash =
                web3j.okcGetBlockTransactionCountByHash(
                        config.validBlockHash()).send();
        assertThat(okcGetBlockTransactionCountByHash.getTransactionCount(),
                equalTo(config.validBlockTransactionCount()));
    }

    @Test
    public void testOkcGetBlockTransactionCountByNumber() throws Exception {
        OkcGetBlockTransactionCountByNumber okcGetBlockTransactionCountByNumber =
                web3j.okcGetBlockTransactionCountByNumber(
                        DefaultBlockParameter.valueOf(config.validBlock())).send();
        assertThat(okcGetBlockTransactionCountByNumber.getTransactionCount(),
                equalTo(config.validBlockTransactionCount()));
    }

    @Test
    public void testOkcGetUncleCountByBlockHash() throws Exception {
        OkcGetUncleCountByBlockHash okcGetUncleCountByBlockHash =
                web3j.okcGetUncleCountByBlockHash(config.validBlockHash()).send();
        assertThat(okcGetUncleCountByBlockHash.getUncleCount(),
                equalTo(config.validBlockUncleCount()));
    }

    @Test
    public void testOkcGetUncleCountByBlockNumber() throws Exception {
        OkcGetUncleCountByBlockNumber okcGetUncleCountByBlockNumber =
                web3j.okcGetUncleCountByBlockNumber(
                        DefaultBlockParameter.valueOf("latest")).send();
        assertThat(okcGetUncleCountByBlockNumber.getUncleCount(),
                equalTo(config.validBlockUncleCount()));
    }

    @Test
    public void testOkcGetCode() throws Exception {
        OkcGetCode okcGetCode = web3j.okcGetCode(config.validContractAddress(),
                DefaultBlockParameter.valueOf(config.validBlock())).send();
        assertThat(okcGetCode.getCode(), is(config.validContractCode()));
    }

    @Ignore  // TODO: Once account unlock functionality is available
    @Test
    public void testOkcSign() throws Exception {
        // OkcSign okcSign = web3j.okcSign();
    }

    @Ignore  // TODO: Once account unlock functionality is available
    @Test
    public void testOkcSendTransaction() throws Exception {
        OkcSendTransaction okcSendTransaction = web3j.okcSendTransaction(
                config.buildTransaction()).send();
        assertFalse(okcSendTransaction.getTransactionHash().isEmpty());
    }

    @Ignore  // TODO: Once account unlock functionality is available
    @Test
    public void testOkcSendRawTransaction() throws Exception {

    }

    @Test
    public void testOkcCall() throws Exception {
        OkcCall okcCall = web3j.okcCall(config.buildTransaction(),
                DefaultBlockParameter.valueOf("latest")).send();

        assertThat(DefaultBlockParameterName.LATEST.getValue(), is("latest"));
        assertThat(okcCall.getValue(), is("0x"));
    }

    @Test
    public void testOkcEstimateGas() throws Exception {
        OkcEstimateGas okcEstimateGas = web3j.okcEstimateGas(config.buildTransaction())
                .send();
        assertTrue(okcEstimateGas.getAmountUsed().signum() == 1);
    }

    @Test
    public void testOkcGetBlockByHashReturnHashObjects() throws Exception {
        OkcBlock okcBlock = web3j.okcGetBlockByHash(config.validBlockHash(), false)
                .send();

        OkcBlock.Block block = okcBlock.getBlock();
        assertNotNull(okcBlock.getBlock());
        assertThat(block.getNumber(), equalTo(config.validBlock()));
        assertThat(block.getTransactions().size(),
                is(config.validBlockTransactionCount().intValue()));
    }

    @Test
    public void testOkcGetBlockByHashReturnFullTransactionObjects() throws Exception {
        OkcBlock okcBlock = web3j.okcGetBlockByHash(config.validBlockHash(), true)
                .send();

        OkcBlock.Block block = okcBlock.getBlock();
        assertNotNull(okcBlock.getBlock());
        assertThat(block.getNumber(), equalTo(config.validBlock()));
        assertThat(block.getTransactions().size(),
                equalTo(config.validBlockTransactionCount().intValue()));
    }

    @Test
    public void testOkcGetBlockByNumberReturnHashObjects() throws Exception {
        OkcBlock okcBlock = web3j.okcGetBlockByNumber(
                DefaultBlockParameter.valueOf(config.validBlock()), false).send();

        OkcBlock.Block block = okcBlock.getBlock();
        assertNotNull(okcBlock.getBlock());
        assertThat(block.getNumber(), equalTo(config.validBlock()));
        assertThat(block.getTransactions().size(),
                equalTo(config.validBlockTransactionCount().intValue()));
    }

    @Test
    public void testOkcGetBlockByNumberReturnTransactionObjects() throws Exception {
        OkcBlock okcBlock = web3j.okcGetBlockByNumber(
                DefaultBlockParameter.valueOf(config.validBlock()), true).send();

        OkcBlock.Block block = okcBlock.getBlock();
        assertNotNull(okcBlock.getBlock());
        assertThat(block.getNumber(), equalTo(config.validBlock()));
        assertThat(block.getTransactions().size(),
                equalTo(config.validBlockTransactionCount().intValue()));
    }

    @Test
    public void testOkcGetTransactionByHash() throws Exception {
        OkcTransaction okcTransaction = web3j.okcGetTransactionByHash(
                config.validTransactionHash()).send();
        assertTrue(okcTransaction.getTransaction().isPresent());
        Transaction transaction = okcTransaction.getTransaction().get();
        assertThat(transaction.getBlockHash(), is(config.validBlockHash()));
    }

    @Test
    public void testOkcGetTransactionByBlockHashAndIndex() throws Exception {
        BigInteger index = BigInteger.ONE;

        OkcTransaction okcTransaction = web3j.okcGetTransactionByBlockHashAndIndex(
                config.validBlockHash(), index).send();
        assertTrue(okcTransaction.getTransaction().isPresent());
        Transaction transaction = okcTransaction.getTransaction().get();
        assertThat(transaction.getBlockHash(), is(config.validBlockHash()));
        assertThat(transaction.getTransactionIndex(), equalTo(index));
    }

    @Test
    public void testOkcGetTransactionByBlockNumberAndIndex() throws Exception {
        BigInteger index = BigInteger.ONE;

        OkcTransaction okcTransaction = web3j.okcGetTransactionByBlockNumberAndIndex(
                DefaultBlockParameter.valueOf(config.validBlock()), index).send();
        assertTrue(okcTransaction.getTransaction().isPresent());
        Transaction transaction = okcTransaction.getTransaction().get();
        assertThat(transaction.getBlockHash(), is(config.validBlockHash()));
        assertThat(transaction.getTransactionIndex(), equalTo(index));
    }

    @Test
    public void testOkcGetTransactionReceipt() throws Exception {
        OkcGetTransactionReceipt okcGetTransactionReceipt = web3j.okcGetTransactionReceipt(
                config.validTransactionHash()).send();
        assertTrue(okcGetTransactionReceipt.getTransactionReceipt().isPresent());
        TransactionReceipt transactionReceipt =
                okcGetTransactionReceipt.getTransactionReceipt().get();
        assertThat(transactionReceipt.getTransactionHash(), is(config.validTransactionHash()));
    }

    @Test
    public void testOkcGetUncleByBlockHashAndIndex() throws Exception {
        OkcBlock okcBlock = web3j.okcGetUncleByBlockHashAndIndex(
                config.validUncleBlockHash(), BigInteger.ZERO).send();
        assertNotNull(okcBlock.getBlock());
    }

    @Test
    public void testOkcGetUncleByBlockNumberAndIndex() throws Exception {
        OkcBlock okcBlock = web3j.okcGetUncleByBlockNumberAndIndex(
                DefaultBlockParameter.valueOf(config.validUncleBlock()), BigInteger.ZERO)
                .send();
        assertNotNull(okcBlock.getBlock());
    }

    @Test
    public void testOkcGetCompilers() throws Exception {
        OkcGetCompilers okcGetCompilers = web3j.okcGetCompilers().send();
        assertNotNull(okcGetCompilers.getCompilers());
    }

    @Ignore  // The method okc_compileLLL does not exist/is not available
    @Test
    public void testOkcCompileLLL() throws Exception {
        OkcCompileLLL okcCompileLLL = web3j.okcCompileLLL(
                "(returnlll (suicide (caller)))").send();
        assertFalse(okcCompileLLL.getCompiledSourceCode().isEmpty());
    }

    @Test
    public void testOkcCompileSolidity() throws Exception {
        String sourceCode = "pragma solidity ^0.4.0;"
                + "\ncontract test { function multiply(uint a) returns(uint d) {"
                + "   return a * 7;   } }"
                + "\ncontract test2 { function multiply2(uint a) returns(uint d) {"
                + "   return a * 7;   } }";
        OkcCompileSolidity okcCompileSolidity = web3j.okcCompileSolidity(sourceCode)
                .send();
        assertNotNull(okcCompileSolidity.getCompiledSolidity());
        assertThat(
                okcCompileSolidity.getCompiledSolidity().get("test2").getInfo().getSource(),
                is(sourceCode));
    }

    @Ignore  // The method okc_compileSerpent does not exist/is not available
    @Test
    public void testOkcCompileSerpent() throws Exception {
        OkcCompileSerpent okcCompileSerpent = web3j.okcCompileSerpent(
                "/* some serpent */").send();
        assertFalse(okcCompileSerpent.getCompiledSourceCode().isEmpty());
    }

    @Test
    public void testFiltersByFilterId() throws Exception {
        org.web3j.protocol.core.methods.request.OkcFilter okcFilter =
                new org.web3j.protocol.core.methods.request.OkcFilter(
                DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST,
                config.validContractAddress());

        String eventSignature = config.encodedEvent();
        okcFilter.addSingleTopic(eventSignature);

        // okc_newFilter
        OkcFilter okcNewFilter = web3j.okcNewFilter(okcFilter).send();
        BigInteger filterId = okcNewFilter.getFilterId();

        // okc_getFilterLogs
        OkcLog okcFilterLogs = web3j.okcGetFilterLogs(filterId).send();
        List<OkcLog.LogResult> filterLogs = okcFilterLogs.getLogs();
        assertFalse(filterLogs.isEmpty());

        // okc_getFilterChanges - nothing will have changed in this interval
        OkcLog okcLog = web3j.okcGetFilterChanges(filterId).send();
        assertTrue(okcLog.getLogs().isEmpty());

        // okc_uninstallFilter
        OkcUninstallFilter okcUninstallFilter = web3j.okcUninstallFilter(filterId).send();
        assertTrue(okcUninstallFilter.isUninstalled());
    }

    @Test
    public void testOkcNewBlockFilter() throws Exception {
        OkcFilter okcNewBlockFilter = web3j.okcNewBlockFilter().send();
        assertNotNull(okcNewBlockFilter.getFilterId());
    }

    @Test
    public void testOkcNewPendingTransactionFilter() throws Exception {
        OkcFilter okcNewPendingTransactionFilter =
                web3j.okcNewPendingTransactionFilter().send();
        assertNotNull(okcNewPendingTransactionFilter.getFilterId());
    }

    @Test
    public void testOkcGetLogs() throws Exception {
        org.web3j.protocol.core.methods.request.OkcFilter okcFilter =
                new org.web3j.protocol.core.methods.request.OkcFilter(
                DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST,
                config.validContractAddress()
        );

        okcFilter.addSingleTopic(config.encodedEvent());

        OkcLog okcLog = web3j.okcGetLogs(okcFilter).send();
        List<OkcLog.LogResult> logs = okcLog.getLogs();
        assertFalse(logs.isEmpty());
    }

    // @Test
    // public void testOkcGetWork() throws Exception {
    //     OkcGetWork okcGetWork = requestFactory.okcGetWork();
    //     assertNotNull(okcGetWork.getResult());
    // }

    @Test
    public void testOkcSubmitWork() throws Exception {

    }

    @Test
    public void testOkcSubmitHashrate() throws Exception {
    
    }

    @Test
    public void testDbPutString() throws Exception {
    
    }

    @Test
    public void testDbGetString() throws Exception {
    
    }

    @Test
    public void testDbPutHex() throws Exception {
    
    }

    @Test
    public void testDbGokcex() throws Exception {
    
    }

    @Test
    public void testShhPost() throws Exception {
    
    }

    @Ignore // The method shh_version does not exist/is not available
    @Test
    public void testShhVersion() throws Exception {
        ShhVersion shhVersion = web3j.shhVersion().send();
        assertNotNull(shhVersion.getVersion());
    }

    @Ignore  // The method shh_newIdentity does not exist/is not available
    @Test
    public void testShhNewIdentity() throws Exception {
        ShhNewIdentity shhNewIdentity = web3j.shhNewIdentity().send();
        assertNotNull(shhNewIdentity.getAddress());
    }

    @Test
    public void testShhHasIdentity() throws Exception {
    
    }

    @Ignore  // The method shh_newIdentity does not exist/is not available
    @Test
    public void testShhNewGroup() throws Exception {
        ShhNewGroup shhNewGroup = web3j.shhNewGroup().send();
        assertNotNull(shhNewGroup.getAddress());
    }

    @Ignore  // The method shh_addToGroup does not exist/is not available
    @Test
    public void testShhAddToGroup() throws Exception {

    }

    @Test
    public void testShhNewFilter() throws Exception {
    
    }

    @Test
    public void testShhUninstallFilter() throws Exception {
    
    }

    @Test
    public void testShhGetFilterChanges() throws Exception {
    
    }

    @Test
    public void testShhGetMessages() throws Exception {
    
    }
}
