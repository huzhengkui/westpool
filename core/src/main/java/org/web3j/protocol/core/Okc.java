package org.web3j.protocol.core;

import java.math.BigInteger;

import org.web3j.protocol.core.mOkcods.request.ShhFilter;
import org.web3j.protocol.core.mOkcods.response.DbGOkcex;
import org.web3j.protocol.core.mOkcods.response.DbGetString;
import org.web3j.protocol.core.mOkcods.response.DbPutHex;
import org.web3j.protocol.core.mOkcods.response.DbPutString;
import org.web3j.protocol.core.mOkcods.response.OkcAccounts;
import org.web3j.protocol.core.mOkcods.response.OkcBlock;
import org.web3j.protocol.core.mOkcods.response.OkcBlockNumber;
import org.web3j.protocol.core.mOkcods.response.OkcCoinbase;
import org.web3j.protocol.core.mOkcods.response.OkcCompileLLL;
import org.web3j.protocol.core.mOkcods.response.OkcCompileSerpent;
import org.web3j.protocol.core.mOkcods.response.OkcCompileSolidity;
import org.web3j.protocol.core.mOkcods.response.OkcEstimateGas;
import org.web3j.protocol.core.mOkcods.response.OkcFilter;
import org.web3j.protocol.core.mOkcods.response.OkcGasPrice;
import org.web3j.protocol.core.mOkcods.response.OkcGetBalance;
import org.web3j.protocol.core.mOkcods.response.OkcGetBlockTransactionCountByHash;
import org.web3j.protocol.core.mOkcods.response.OkcGetBlockTransactionCountByNumber;
import org.web3j.protocol.core.mOkcods.response.OkcGetCode;
import org.web3j.protocol.core.mOkcods.response.OkcGetCompilers;
import org.web3j.protocol.core.mOkcods.response.OkcGetStorageAt;
import org.web3j.protocol.core.mOkcods.response.OkcGetTransactionCount;
import org.web3j.protocol.core.mOkcods.response.OkcGetTransactionReceipt;
import org.web3j.protocol.core.mOkcods.response.OkcGetUncleCountByBlockHash;
import org.web3j.protocol.core.mOkcods.response.OkcGetUncleCountByBlockNumber;
import org.web3j.protocol.core.mOkcods.response.OkcGetWork;
import org.web3j.protocol.core.mOkcods.response.OkcHashrate;
import org.web3j.protocol.core.mOkcods.response.OkcLog;
import org.web3j.protocol.core.mOkcods.response.OkcMining;
import org.web3j.protocol.core.mOkcods.response.OkcProtocolVersion;
import org.web3j.protocol.core.mOkcods.response.OkcSign;
import org.web3j.protocol.core.mOkcods.response.OkcSubmitHashrate;
import org.web3j.protocol.core.mOkcods.response.OkcSubmitWork;
import org.web3j.protocol.core.mOkcods.response.OkcSyncing;
import org.web3j.protocol.core.mOkcods.response.OkcTransaction;
import org.web3j.protocol.core.mOkcods.response.OkcUninstallFilter;
import org.web3j.protocol.core.mOkcods.response.NetListening;
import org.web3j.protocol.core.mOkcods.response.NetPeerCount;
import org.web3j.protocol.core.mOkcods.response.NetVersion;
import org.web3j.protocol.core.mOkcods.response.ShhAddToGroup;
import org.web3j.protocol.core.mOkcods.response.ShhHasIdentity;
import org.web3j.protocol.core.mOkcods.response.ShhMessages;
import org.web3j.protocol.core.mOkcods.response.ShhNewFilter;
import org.web3j.protocol.core.mOkcods.response.ShhNewGroup;
import org.web3j.protocol.core.mOkcods.response.ShhNewIdentity;
import org.web3j.protocol.core.mOkcods.response.ShhUninstallFilter;
import org.web3j.protocol.core.mOkcods.response.ShhVersion;
import org.web3j.protocol.core.mOkcods.response.Web3ClientVersion;
import org.web3j.protocol.core.mOkcods.response.Web3Sha3;

/**
 * Core Okcereum JSON-RPC API.
 */
public interface Okcereum {
    Request<?, Web3ClientVersion> web3ClientVersion();

    Request<?, Web3Sha3> web3Sha3(String data);

    Request<?, NetVersion> netVersion();

    Request<?, NetListening> netListening();

    Request<?, NetPeerCount> netPeerCount();

    Request<?, OkcProtocolVersion> OkcProtocolVersion();

    Request<?, OkcCoinbase> OkcCoinbase();

    Request<?, OkcSyncing> OkcSyncing();

    Request<?, OkcMining> OkcMining();

    Request<?, OkcHashrate> OkcHashrate();

    Request<?, OkcGasPrice> OkcGasPrice();

    Request<?, OkcAccounts> OkcAccounts();

    Request<?, OkcBlockNumber> OkcBlockNumber();

    Request<?, OkcGetBalance> OkcGetBalance(
            String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, OkcGetStorageAt> OkcGetStorageAt(
            String address, BigInteger position,
            DefaultBlockParameter defaultBlockParameter);

    Request<?, OkcGetTransactionCount> OkcGetTransactionCount(
            String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, OkcGetBlockTransactionCountByHash> OkcGetBlockTransactionCountByHash(
            String blockHash);

    Request<?, OkcGetBlockTransactionCountByNumber> OkcGetBlockTransactionCountByNumber(
            DefaultBlockParameter defaultBlockParameter);

    Request<?, OkcGetUncleCountByBlockHash> OkcGetUncleCountByBlockHash(String blockHash);

    Request<?, OkcGetUncleCountByBlockNumber> OkcGetUncleCountByBlockNumber(
            DefaultBlockParameter defaultBlockParameter);

    Request<?, OkcGetCode> OkcGetCode(String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, OkcSign> OkcSign(String address, String sha3HashOfDataToSign);

    Request<?, org.web3j.protocol.core.mOkcods.response.OkcSendTransaction> OkcSendTransaction(
            org.web3j.protocol.core.mOkcods.request.Transaction transaction);

    Request<?, org.web3j.protocol.core.mOkcods.response.OkcSendTransaction> OkcSendRawTransaction(
            String signedTransactionData);

    Request<?, org.web3j.protocol.core.mOkcods.response.OkcCall> OkcCall(
            org.web3j.protocol.core.mOkcods.request.Transaction transaction,
            DefaultBlockParameter defaultBlockParameter);

    Request<?, OkcEstimateGas> OkcEstimateGas(
            org.web3j.protocol.core.mOkcods.request.Transaction transaction);

    Request<?, OkcBlock> OkcGetBlockByHash(String blockHash, boolean returnFullTransactionObjects);

    Request<?, OkcBlock> OkcGetBlockByNumber(
            DefaultBlockParameter defaultBlockParameter,
            boolean returnFullTransactionObjects);

    Request<?, OkcTransaction> OkcGetTransactionByHash(String transactionHash);

    Request<?, OkcTransaction> OkcGetTransactionByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex);

    Request<?, OkcTransaction> OkcGetTransactionByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger transactionIndex);

    Request<?, OkcGetTransactionReceipt> OkcGetTransactionReceipt(String transactionHash);

    Request<?, OkcBlock> OkcGetUncleByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex);

    Request<?, OkcBlock> OkcGetUncleByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger transactionIndex);

    Request<?, OkcGetCompilers> OkcGetCompilers();

    Request<?, OkcCompileLLL> OkcCompileLLL(String sourceCode);

    Request<?, OkcCompileSolidity> OkcCompileSolidity(String sourceCode);

    Request<?, OkcCompileSerpent> OkcCompileSerpent(String sourceCode);

    Request<?, OkcFilter> OkcNewFilter(org.web3j.protocol.core.mOkcods.request.OkcFilter OkcFilter);

    Request<?, OkcFilter> OkcNewBlockFilter();

    Request<?, OkcFilter> OkcNewPendingTransactionFilter();

    Request<?, OkcUninstallFilter> OkcUninstallFilter(BigInteger filterId);

    Request<?, OkcLog> OkcGetFilterChanges(BigInteger filterId);

    Request<?, OkcLog> OkcGetFilterLogs(BigInteger filterId);

    Request<?, OkcLog> OkcGetLogs(org.web3j.protocol.core.mOkcods.request.OkcFilter OkcFilter);

    Request<?, OkcGetWork> OkcGetWork();

    Request<?, OkcSubmitWork> OkcSubmitWork(String nonce, String headerPowHash, String mixDigest);

    Request<?, OkcSubmitHashrate> OkcSubmitHashrate(String hashrate, String clientId);

    Request<?, DbPutString> dbPutString(String databaseName, String keyName, String stringToStore);

    Request<?, DbGetString> dbGetString(String databaseName, String keyName);

    Request<?, DbPutHex> dbPutHex(String databaseName, String keyName, String dataToStore);

    Request<?, DbGOkcex> dbGOkcex(String databaseName, String keyName);

    Request<?, org.web3j.protocol.core.mOkcods.response.ShhPost> shhPost(
            org.web3j.protocol.core.mOkcods.request.ShhPost shhPost);

    Request<?, ShhVersion> shhVersion();

    Request<?, ShhNewIdentity> shhNewIdentity();

    Request<?, ShhHasIdentity> shhHasIdentity(String identityAddress);

    Request<?, ShhNewGroup> shhNewGroup();

    Request<?, ShhAddToGroup> shhAddToGroup(String identityAddress);

    Request<?, ShhNewFilter> shhNewFilter(ShhFilter shhFilter);

    Request<?, ShhUninstallFilter> shhUninstallFilter(BigInteger filterId);

    Request<?, ShhMessages> shhGetFilterChanges(BigInteger filterId);

    Request<?, ShhMessages> shhGetMessages(BigInteger filterId);
}
