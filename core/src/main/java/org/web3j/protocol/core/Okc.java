package org.web3j.protocol.core;

import java.math.BigInteger;

import org.web3j.protocol.core.methods.request.ShhFilter;
import org.web3j.protocol.core.methods.response.DbGetHex;
import org.web3j.protocol.core.methods.response.DbGetString;
import org.web3j.protocol.core.methods.response.DbPutHex;
import org.web3j.protocol.core.methods.response.DbPutString;
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
import org.web3j.protocol.core.methods.response.NetListening;
import org.web3j.protocol.core.methods.response.NetPeerCount;
import org.web3j.protocol.core.methods.response.NetVersion;
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

/**
 * Core Okc JSON-RPC API.
 */
public interface Okc {
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

    Request<?, org.web3j.protocol.core.methods.response.OkcSendTransaction> OkcSendTransaction(
            org.web3j.protocol.core.methods.request.Transaction transaction);

    Request<?, org.web3j.protocol.core.methods.response.OkcSendTransaction> OkcSendRawTransaction(
            String signedTransactionData);

    Request<?, org.web3j.protocol.core.methods.response.OkcCall> OkcCall(
            org.web3j.protocol.core.methods.request.Transaction transaction,
            DefaultBlockParameter defaultBlockParameter);

    Request<?, OkcEstimateGas> OkcEstimateGas(
            org.web3j.protocol.core.methods.request.Transaction transaction);

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

    Request<?, OkcFilter> OkcNewFilter(org.web3j.protocol.core.methods.request.OkcFilter OkcFilter);

    Request<?, OkcFilter> OkcNewBlockFilter();

    Request<?, OkcFilter> OkcNewPendingTransactionFilter();

    Request<?, OkcUninstallFilter> OkcUninstallFilter(BigInteger filterId);

    Request<?, OkcLog> OkcGetFilterChanges(BigInteger filterId);

    Request<?, OkcLog> OkcGetFilterLogs(BigInteger filterId);

    Request<?, OkcLog> OkcGetLogs(org.web3j.protocol.core.methods.request.OkcFilter OkcFilter);

    Request<?, OkcGetWork> OkcGetWork();

    Request<?, OkcSubmitWork> OkcSubmitWork(String nonce, String headerPowHash, String mixDigest);

    Request<?, OkcSubmitHashrate> OkcSubmitHashrate(String hashrate, String clientId);

    Request<?, DbPutString> dbPutString(String databaseName, String keyName, String stringToStore);

    Request<?, DbGetString> dbGetString(String databaseName, String keyName);

    Request<?, DbPutHex> dbPutHex(String databaseName, String keyName, String dataToStore);

    Request<?, DbGOkcex> DbGetHex(String databaseName, String keyName);

    Request<?, org.web3j.protocol.core.methods.response.ShhPost> shhPost(
            org.web3j.protocol.core.methods.request.ShhPost shhPost);

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
