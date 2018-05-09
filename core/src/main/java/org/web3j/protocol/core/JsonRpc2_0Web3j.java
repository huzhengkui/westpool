package org.web3j.protocol.core;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;

import rx.Observable;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.methods.request.ShhFilter;
import org.web3j.protocol.core.methods.request.ShhPost;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.DbGetHex;
import org.web3j.protocol.core.methods.response.DbGetString;
import org.web3j.protocol.core.methods.response.DbPutHex;
import org.web3j.protocol.core.methods.response.DbPutString;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.NetListening;
import org.web3j.protocol.core.methods.response.NetPeerCount;
import org.web3j.protocol.core.methods.response.NetVersion;
import org.web3j.protocol.core.methods.response.OkcAccounts;
import org.web3j.protocol.core.methods.response.OkcBlock;
import org.web3j.protocol.core.methods.response.OkcBlockNumber;
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
import org.web3j.protocol.core.methods.response.OkcGetWork;
import org.web3j.protocol.core.methods.response.OkcHashrate;
import org.web3j.protocol.core.methods.response.OkcLog;
import org.web3j.protocol.core.methods.response.OkcMining;
import org.web3j.protocol.core.methods.response.OkcProtocolVersion;
import org.web3j.protocol.core.methods.response.OkcSign;
import org.web3j.protocol.core.methods.response.OkcSubmitHashrate;
import org.web3j.protocol.core.methods.response.OkcSubmitWork;
import org.web3j.protocol.core.methods.response.OkcSyncing;
import org.web3j.protocol.core.methods.response.OkcTransaction;
import org.web3j.protocol.core.methods.response.OkcUninstallFilter;


import org.web3j.protocol.core.methods.response.ShhAddToGroup;
import org.web3j.protocol.core.methods.response.ShhHasIdentity;
import org.web3j.protocol.core.methods.response.ShhMessages;
import org.web3j.protocol.core.methods.response.ShhNewFilter;
import org.web3j.protocol.core.methods.response.ShhNewGroup;
import org.web3j.protocol.core.methods.response.ShhNewIdentity;
import org.web3j.protocol.core.methods.response.ShhUninstallFilter;
import org.web3j.protocol.core.methods.response.ShhVersion;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.core.methods.response.Web3Sha3;
import org.web3j.protocol.rx.JsonRpc2_0Rx;
import org.web3j.utils.Async;
import org.web3j.utils.Numeric;

/**
 * JSON-RPC 2.0 factory implementation.
 */
public class JsonRpc2_0Web3j implements Web3j {

    public static final int DEFAULT_BLOCK_TIME = 15 * 1000;

    protected final Web3jService web3jService;
    private final JsonRpc2_0Rx web3jRx;
    private final long blockTime;
    private final ScheduledExecutorService scheduledExecutorService;

    public JsonRpc2_0Web3j(Web3jService web3jService) {
        this(web3jService, DEFAULT_BLOCK_TIME, Async.defaultExecutorService());
    }

    public JsonRpc2_0Web3j(
            Web3jService web3jService, long pollingInterval,
            ScheduledExecutorService scheduledExecutorService) {
        this.web3jService = web3jService;
        this.web3jRx = new JsonRpc2_0Rx(this, scheduledExecutorService);
        this.blockTime = pollingInterval;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    @Override
    public Request<?, Web3ClientVersion> web3ClientVersion() {
        return new Request<>(
                "web3_clientVersion",
                Collections.<String>emptyList(),
                web3jService,
                Web3ClientVersion.class);
    }

    @Override
    public Request<?, Web3Sha3> web3Sha3(String data) {
        return new Request<>(
                "web3_sha3",
                Arrays.asList(data),
                web3jService,
                Web3Sha3.class);
    }

    @Override
    public Request<?, NetVersion> netVersion() {
        return new Request<>(
                "net_version",
                Collections.<String>emptyList(),
                web3jService,
                NetVersion.class);
    }

    @Override
    public Request<?, NetListening> netListening() {
        return new Request<>(
                "net_listening",
                Collections.<String>emptyList(),
                web3jService,
                NetListening.class);
    }

    @Override
    public Request<?, NetPeerCount> netPeerCount() {
        return new Request<>(
                "net_peerCount",
                Collections.<String>emptyList(),
                web3jService,
                NetPeerCount.class);
    }

    @Override
    public Request<?, OkcProtocolVersion> okcProtocolVersion() {
        return new Request<>(
                "okc_protocolVersion",
                Collections.<String>emptyList(),
                web3jService,
                OkcProtocolVersion.class);
    }

    @Override
    public Request<?, OkcCoinbase> okcCoinbase() {
        return new Request<>(
                "okc_coinbase",
                Collections.<String>emptyList(),
                web3jService,
                OkcCoinbase.class);
    }

    @Override
    public Request<?, OkcSyncing> okcSyncing() {
        return new Request<>(
                "okc_syncing",
                Collections.<String>emptyList(),
                web3jService,
                OkcSyncing.class);
    }

    @Override
    public Request<?, OkcMining> okcMining() {
        return new Request<>(
                "okc_mining",
                Collections.<String>emptyList(),
                web3jService,
                OkcMining.class);
    }

    @Override
    public Request<?, OkcHashrate> okcHashrate() {
        return new Request<>(
                "okc_hashrate",
                Collections.<String>emptyList(),
                web3jService,
                OkcHashrate.class);
    }

    @Override
    public Request<?, OkcGasPrice> okcGasPrice() {
        return new Request<>(
                "okc_gasPrice",
                Collections.<String>emptyList(),
                web3jService,
                OkcGasPrice.class);
    }

    @Override
    public Request<?, OkcAccounts> okcAccounts() {
        return new Request<>(
                "okc_accounts",
                Collections.<String>emptyList(),
                web3jService,
                OkcAccounts.class);
    }

    @Override
    public Request<?, OkcBlockNumber> okcBlockNumber() {
        return new Request<>(
                "okc_blockNumber",
                Collections.<String>emptyList(),
                web3jService,
                OkcBlockNumber.class);
    }

    @Override
    public Request<?, OkcGetBalance> okcGetBalance(
            String address, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "okc_getBalance",
                Arrays.asList(address, defaultBlockParameter.getValue()),
                web3jService,
                OkcGetBalance.class);
    }

    @Override
    public Request<?, OkcGetStorageAt> okcGetStorageAt(
            String address, BigInteger position, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "okc_getStorageAt",
                Arrays.asList(
                        address,
                        Numeric.encodeQuantity(position),
                        defaultBlockParameter.getValue()),
                web3jService,
                OkcGetStorageAt.class);
    }

    @Override
    public Request<?, OkcGetTransactionCount> okcGetTransactionCount(
            String address, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "okc_getTransactionCount",
                Arrays.asList(address, defaultBlockParameter.getValue()),
                web3jService,
                OkcGetTransactionCount.class);
    }

    @Override
    public Request<?, OkcGetBlockTransactionCountByHash> okcGetBlockTransactionCountByHash(
            String blockHash) {
        return new Request<>(
                "okc_getBlockTransactionCountByHash",
                Arrays.asList(blockHash),
                web3jService,
                OkcGetBlockTransactionCountByHash.class);
    }

    @Override
    public Request<?, OkcGetBlockTransactionCountByNumber> okcGetBlockTransactionCountByNumber(
            DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "okc_getBlockTransactionCountByNumber",
                Arrays.asList(defaultBlockParameter.getValue()),
                web3jService,
                OkcGetBlockTransactionCountByNumber.class);
    }

    @Override
    public Request<?, OkcGetUncleCountByBlockHash> okcGetUncleCountByBlockHash(String blockHash) {
        return new Request<>(
                "okc_getUncleCountByBlockHash",
                Arrays.asList(blockHash),
                web3jService,
                OkcGetUncleCountByBlockHash.class);
    }

    @Override
    public Request<?, OkcGetUncleCountByBlockNumber> okcGetUncleCountByBlockNumber(
            DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "okc_getUncleCountByBlockNumber",
                Arrays.asList(defaultBlockParameter.getValue()),
                web3jService,
                OkcGetUncleCountByBlockNumber.class);
    }

    @Override
    public Request<?, OkcGetCode> okcGetCode(
            String address, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "okc_getCode",
                Arrays.asList(address, defaultBlockParameter.getValue()),
                web3jService,
                OkcGetCode.class);
    }

    @Override
    public Request<?, OkcSign> okcSign(String address, String sha3HashOfDataToSign) {
        return new Request<>(
                "okc_sign",
                Arrays.asList(address, sha3HashOfDataToSign),
                web3jService,
                OkcSign.class);
    }

    @Override
    public Request<?, org.web3j.protocol.core.methods.response.OkcSendTransaction>
            okcSendTransaction(
            Transaction transaction) {
        return new Request<>(
                "okc_sendTransaction",
                Arrays.asList(transaction),
                web3jService,
                org.web3j.protocol.core.methods.response.OkcSendTransaction.class);
    }

    @Override
    public Request<?, org.web3j.protocol.core.methods.response.OkcSendTransaction>
            okcSendRawTransaction(
            String signedTransactionData) {
        return new Request<>(
                "okc_sendRawTransaction",
                Arrays.asList(signedTransactionData),
                web3jService,
                org.web3j.protocol.core.methods.response.OkcSendTransaction.class);
    }

    @Override
    public Request<?, org.web3j.protocol.core.methods.response.OkcCall> okcCall(
            Transaction transaction, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "okc_call",
                Arrays.asList(transaction, defaultBlockParameter),
                web3jService,
                org.web3j.protocol.core.methods.response.OkcCall.class);
    }

    @Override
    public Request<?, OkcEstimateGas> okcEstimateGas(Transaction transaction) {
        return new Request<>(
                "okc_estimateGas",
                Arrays.asList(transaction),
                web3jService,
                OkcEstimateGas.class);
    }

    @Override
    public Request<?, OkcBlock> okcGetBlockByHash(
            String blockHash, boolean returnFullTransactionObjects) {
        return new Request<>(
                "okc_getBlockByHash",
                Arrays.asList(
                        blockHash,
                        returnFullTransactionObjects),
                web3jService,
                OkcBlock.class);
    }

    @Override
    public Request<?, OkcBlock> okcGetBlockByNumber(
            DefaultBlockParameter defaultBlockParameter,
            boolean returnFullTransactionObjects) {
        return new Request<>(
                "okc_getBlockByNumber",
                Arrays.asList(
                        defaultBlockParameter.getValue(),
                        returnFullTransactionObjects),
                web3jService,
                OkcBlock.class);
    }

    @Override
    public Request<?, OkcTransaction> okcGetTransactionByHash(String transactionHash) {
        return new Request<>(
                "okc_getTransactionByHash",
                Arrays.asList(transactionHash),
                web3jService,
                OkcTransaction.class);
    }

    @Override
    public Request<?, OkcTransaction> okcGetTransactionByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex) {
        return new Request<>(
                "okc_getTransactionByBlockHashAndIndex",
                Arrays.asList(
                        blockHash,
                        Numeric.encodeQuantity(transactionIndex)),
                web3jService,
                OkcTransaction.class);
    }

    @Override
    public Request<?, OkcTransaction> okcGetTransactionByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger transactionIndex) {
        return new Request<>(
                "okc_getTransactionByBlockNumberAndIndex",
                Arrays.asList(
                        defaultBlockParameter.getValue(),
                        Numeric.encodeQuantity(transactionIndex)),
                web3jService,
                OkcTransaction.class);
    }

    @Override
    public Request<?, OkcGetTransactionReceipt> okcGetTransactionReceipt(String transactionHash) {
        return new Request<>(
                "okc_getTransactionReceipt",
                Arrays.asList(transactionHash),
                web3jService,
                OkcGetTransactionReceipt.class);
    }

    @Override
    public Request<?, OkcBlock> okcGetUncleByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex) {
        return new Request<>(
                "okc_getUncleByBlockHashAndIndex",
                Arrays.asList(
                        blockHash,
                        Numeric.encodeQuantity(transactionIndex)),
                web3jService,
                OkcBlock.class);
    }

    @Override
    public Request<?, OkcBlock> okcGetUncleByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger uncleIndex) {
        return new Request<>(
                "okc_getUncleByBlockNumberAndIndex",
                Arrays.asList(
                        defaultBlockParameter.getValue(),
                        Numeric.encodeQuantity(uncleIndex)),
                web3jService,
                OkcBlock.class);
    }

    @Override
    public Request<?, OkcGetCompilers> okcGetCompilers() {
        return new Request<>(
                "okc_getCompilers",
                Collections.<String>emptyList(),
                web3jService,
                OkcGetCompilers.class);
    }

    @Override
    public Request<?, OkcCompileLLL> okcCompileLLL(String sourceCode) {
        return new Request<>(
                "okc_compileLLL",
                Arrays.asList(sourceCode),
                web3jService,
                OkcCompileLLL.class);
    }

    @Override
    public Request<?, OkcCompileSolidity> okcCompileSolidity(String sourceCode) {
        return new Request<>(
                "okc_compileSolidity",
                Arrays.asList(sourceCode),
                web3jService,
                OkcCompileSolidity.class);
    }

    @Override
    public Request<?, OkcCompileSerpent> okcCompileSerpent(String sourceCode) {
        return new Request<>(
                "okc_compileSerpent",
                Arrays.asList(sourceCode),
                web3jService,
                OkcCompileSerpent.class);
    }

    @Override
    public Request<?, OkcFilter> okcNewFilter(
            org.web3j.protocol.core.methods.request.OkcFilter okcFilter) {
        return new Request<>(
                "okc_newFilter",
                Arrays.asList(okcFilter),
                web3jService,
                OkcFilter.class);
    }

    @Override
    public Request<?, OkcFilter> okcNewBlockFilter() {
        return new Request<>(
                "okc_newBlockFilter",
                Collections.<String>emptyList(),
                web3jService,
                OkcFilter.class);
    }

    @Override
    public Request<?, OkcFilter> okcNewPendingTransactionFilter() {
        return new Request<>(
                "okc_newPendingTransactionFilter",
                Collections.<String>emptyList(),
                web3jService,
                OkcFilter.class);
    }

    @Override
    public Request<?, OkcUninstallFilter> okcUninstallFilter(BigInteger filterId) {
        return new Request<>(
                "okc_uninstallFilter",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                OkcUninstallFilter.class);
    }

    @Override
    public Request<?, OkcLog> okcGetFilterChanges(BigInteger filterId) {
        return new Request<>(
                "okc_getFilterChanges",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                OkcLog.class);
    }

    @Override
    public Request<?, OkcLog> okcGetFilterLogs(BigInteger filterId) {
        return new Request<>(
                "okc_getFilterLogs",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                OkcLog.class);
    }

    @Override
    public Request<?, OkcLog> okcGetLogs(
            org.web3j.protocol.core.methods.request.OkcFilter okcFilter) {
        return new Request<>(
                "okc_getLogs",
                Arrays.asList(okcFilter),
                web3jService,
                OkcLog.class);
    }

    @Override
    public Request<?, OkcGetWork> okcGetWork() {
        return new Request<>(
                "okc_getWork",
                Collections.<String>emptyList(),
                web3jService,
                OkcGetWork.class);
    }

    @Override
    public Request<?, OkcSubmitWork> okcSubmitWork(
            String nonce, String headerPowHash, String mixDigest) {
        return new Request<>(
                "okc_submitWork",
                Arrays.asList(nonce, headerPowHash, mixDigest),
                web3jService,
                OkcSubmitWork.class);
    }

    @Override
    public Request<?, OkcSubmitHashrate> okcSubmitHashrate(String hashrate, String clientId) {
        return new Request<>(
                "okc_submitHashrate",
                Arrays.asList(hashrate, clientId),
                web3jService,
                OkcSubmitHashrate.class);
    }

    @Override
    public Request<?, DbPutString> dbPutString(
            String databaseName, String keyName, String stringToStore) {
        return new Request<>(
                "db_putString",
                Arrays.asList(databaseName, keyName, stringToStore),
                web3jService,
                DbPutString.class);
    }

    @Override
    public Request<?, DbGetString> dbGetString(String databaseName, String keyName) {
        return new Request<>(
                "db_getString",
                Arrays.asList(databaseName, keyName),
                web3jService,
                DbGetString.class);
    }

    @Override
    public Request<?, DbPutHex> dbPutHex(String databaseName, String keyName, String dataToStore) {
        return new Request<>(
                "db_putHex",
                Arrays.asList(databaseName, keyName, dataToStore),
                web3jService,
                DbPutHex.class);
    }

    @Override
    public Request<?, DbGetHex> dbGetHex(String databaseName, String keyName) {
        return new Request<>(
                "db_getHex",
                Arrays.asList(databaseName, keyName),
                web3jService,
                DbGetHex.class);
    }

    @Override
    public Request<?, org.web3j.protocol.core.methods.response.ShhPost> shhPost(ShhPost shhPost) {
        return new Request<>(
                "shh_post",
                Arrays.asList(shhPost),
                web3jService,
                org.web3j.protocol.core.methods.response.ShhPost.class);
    }

    @Override
    public Request<?, ShhVersion> shhVersion() {
        return new Request<>(
                "shh_version",
                Collections.<String>emptyList(),
                web3jService,
                ShhVersion.class);
    }

    @Override
    public Request<?, ShhNewIdentity> shhNewIdentity() {
        return new Request<>(
                "shh_newIdentity",
                Collections.<String>emptyList(),
                web3jService,
                ShhNewIdentity.class);
    }

    @Override
    public Request<?, ShhHasIdentity> shhHasIdentity(String identityAddress) {
        return new Request<>(
                "shh_hasIdentity",
                Arrays.asList(identityAddress),
                web3jService,
                ShhHasIdentity.class);
    }

    @Override
    public Request<?, ShhNewGroup> shhNewGroup() {
        return new Request<>(
                "shh_newGroup",
                Collections.<String>emptyList(),
                web3jService,
                ShhNewGroup.class);
    }

    @Override
    public Request<?, ShhAddToGroup> shhAddToGroup(String identityAddress) {
        return new Request<>(
                "shh_addToGroup",
                Arrays.asList(identityAddress),
                web3jService,
                ShhAddToGroup.class);
    }

    @Override
    public Request<?, ShhNewFilter> shhNewFilter(ShhFilter shhFilter) {
        return new Request<>(
                "shh_newFilter",
                Arrays.asList(shhFilter),
                web3jService,
                ShhNewFilter.class);
    }

    @Override
    public Request<?, ShhUninstallFilter> shhUninstallFilter(BigInteger filterId) {
        return new Request<>(
                "shh_uninstallFilter",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                ShhUninstallFilter.class);
    }

    @Override
    public Request<?, ShhMessages> shhGetFilterChanges(BigInteger filterId) {
        return new Request<>(
                "shh_getFilterChanges",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                ShhMessages.class);
    }

    @Override
    public Request<?, ShhMessages> shhGetMessages(BigInteger filterId) {
        return new Request<>(
                "shh_getMessages",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                ShhMessages.class);
    }

    @Override
    public Observable<String> okcBlockHashObservable() {
        return web3jRx.okcBlockHashObservable(blockTime);
    }

    @Override
    public Observable<String> okcPendingTransactionHashObservable() {
        return web3jRx.okcPendingTransactionHashObservable(blockTime);
    }

    @Override
    public Observable<Log> okcLogObservable(
            org.web3j.protocol.core.methods.request.OkcFilter okcFilter) {
        return web3jRx.okcLogObservable(okcFilter, blockTime);
    }

    @Override
    public Observable<org.web3j.protocol.core.methods.response.Transaction>
            transactionObservable() {
        return web3jRx.transactionObservable(blockTime);
    }

    @Override
    public Observable<org.web3j.protocol.core.methods.response.Transaction>
            pendingTransactionObservable() {
        return web3jRx.pendingTransactionObservable(blockTime);
    }

    @Override
    public Observable<OkcBlock> blockObservable(boolean fullTransactionObjects) {
        return web3jRx.blockObservable(fullTransactionObjects, blockTime);
    }

    @Override
    public Observable<OkcBlock> replayBlocksObservable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock,
            boolean fullTransactionObjects) {
        return web3jRx.replayBlocksObservable(startBlock, endBlock, fullTransactionObjects);
    }

    @Override
    public Observable<OkcBlock> replayBlocksObservable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock,
            boolean fullTransactionObjects, boolean ascending) {
        return web3jRx.replayBlocksObservable(startBlock, endBlock,
                fullTransactionObjects, ascending);
    }

    @Override
    public Observable<org.web3j.protocol.core.methods.response.Transaction>
            replayTransactionsObservable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        return web3jRx.replayTransactionsObservable(startBlock, endBlock);
    }

    @Override
    public Observable<OkcBlock> catchUpToLatestBlockObservable(
            DefaultBlockParameter startBlock, boolean fullTransactionObjects,
            Observable<OkcBlock> onCompleteObservable) {
        return web3jRx.catchUpToLatestBlockObservable(
                startBlock, fullTransactionObjects, onCompleteObservable);
    }

    @Override
    public Observable<OkcBlock> catchUpToLatestBlockObservable(
            DefaultBlockParameter startBlock, boolean fullTransactionObjects) {
        return web3jRx.catchUpToLatestBlockObservable(startBlock, fullTransactionObjects);
    }

    @Override
    public Observable<org.web3j.protocol.core.methods.response.Transaction>
            catchUpToLatestTransactionObservable(DefaultBlockParameter startBlock) {
        return web3jRx.catchUpToLatestTransactionObservable(startBlock);
    }

    @Override
    public Observable<OkcBlock> catchUpToLatestAndSubscribeToNewBlocksObservable(
            DefaultBlockParameter startBlock, boolean fullTransactionObjects) {
        return web3jRx.catchUpToLatestAndSubscribeToNewBlocksObservable(
                startBlock, fullTransactionObjects, blockTime);
    }

    @Override
    public Observable<org.web3j.protocol.core.methods.response.Transaction>
            catchUpToLatestAndSubscribeToNewTransactionsObservable(
            DefaultBlockParameter startBlock) {
        return web3jRx.catchUpToLatestAndSubscribeToNewTransactionsObservable(
                startBlock, blockTime);
    }

    @Override
    public void shutdown() {
        scheduledExecutorService.shutdown();
    }
}
