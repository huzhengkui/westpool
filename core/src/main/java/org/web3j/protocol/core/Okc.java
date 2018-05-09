package org.web3j.protocol.core;

import java.math.BigInteger;

import org.web3j.protocol.core.methods.request.ShhFilter;
import org.web3j.protocol.core.methods.response.DbGetHex;
import org.web3j.protocol.core.methods.response.DbGetString;
import org.web3j.protocol.core.methods.response.DbPutHex;
import org.web3j.protocol.core.methods.response.DbPutString;
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

/**
 * Core Okc JSON-RPC API.
 */
public interface Okc {
    Request<?, Web3ClientVersion> web3ClientVersion();

    Request<?, Web3Sha3> web3Sha3(String data);

    Request<?, NetVersion> netVersion();

    Request<?, NetListening> netListening();

    Request<?, NetPeerCount> netPeerCount();

    Request<?, OkcProtocolVersion> okcProtocolVersion();

    Request<?, OkcCoinbase> okcCoinbase();

    Request<?, OkcSyncing> okcSyncing();

    Request<?, OkcMining> okcMining();

    Request<?, OkcHashrate> okcHashrate();

    Request<?, OkcGasPrice> okcGasPrice();

    Request<?, OkcAccounts> okcAccounts();

    Request<?, OkcBlockNumber> okcBlockNumber();

    Request<?, OkcGetBalance> okcGetBalance(
            String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, OkcGetStorageAt> okcGetStorageAt(
            String address, BigInteger position,
            DefaultBlockParameter defaultBlockParameter);

    Request<?, OkcGetTransactionCount> okcGetTransactionCount(
            String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, OkcGetBlockTransactionCountByHash> okcGetBlockTransactionCountByHash(
            String blockHash);

    Request<?, OkcGetBlockTransactionCountByNumber> okcGetBlockTransactionCountByNumber(
            DefaultBlockParameter defaultBlockParameter);

    Request<?, OkcGetUncleCountByBlockHash> okcGetUncleCountByBlockHash(String blockHash);

    Request<?, OkcGetUncleCountByBlockNumber> okcGetUncleCountByBlockNumber(
            DefaultBlockParameter defaultBlockParameter);

    Request<?, OkcGetCode> okcGetCode(String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, OkcSign> okcSign(String address, String sha3HashOfDataToSign);

    Request<?, org.web3j.protocol.core.methods.response.OkcSendTransaction> okcSendTransaction(
            org.web3j.protocol.core.methods.request.Transaction transaction);

    Request<?, org.web3j.protocol.core.methods.response.OkcSendTransaction> okcSendRawTransaction(
            String signedTransactionData);

    Request<?, org.web3j.protocol.core.methods.response.OkcCall> okcCall(
            org.web3j.protocol.core.methods.request.Transaction transaction,
            DefaultBlockParameter defaultBlockParameter);

    Request<?, OkcEstimateGas> okcEstimateGas(
            org.web3j.protocol.core.methods.request.Transaction transaction);

    Request<?, OkcBlock> okcGetBlockByHash(String blockHash, boolean returnFullTransactionObjects);

    Request<?, OkcBlock> okcGetBlockByNumber(
            DefaultBlockParameter defaultBlockParameter,
            boolean returnFullTransactionObjects);

    Request<?, OkcTransaction> okcGetTransactionByHash(String transactionHash);

    Request<?, OkcTransaction> okcGetTransactionByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex);

    Request<?, OkcTransaction> okcGetTransactionByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger transactionIndex);

    Request<?, OkcGetTransactionReceipt> okcGetTransactionReceipt(String transactionHash);

    Request<?, OkcBlock> okcGetUncleByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex);

    Request<?, OkcBlock> okcGetUncleByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger transactionIndex);

    Request<?, OkcGetCompilers> okcGetCompilers();

    Request<?, OkcCompileLLL> okcCompileLLL(String sourceCode);

    Request<?, OkcCompileSolidity> okcCompileSolidity(String sourceCode);

    Request<?, OkcCompileSerpent> okcCompileSerpent(String sourceCode);

    Request<?, OkcFilter> okcNewFilter(org.web3j.protocol.core.methods.request.OkcFilter okcFilter);

    Request<?, OkcFilter> okcNewBlockFilter();

    Request<?, OkcFilter> okcNewPendingTransactionFilter();

    Request<?, OkcUninstallFilter> okcUninstallFilter(BigInteger filterId);

    Request<?, OkcLog> okcGetFilterChanges(BigInteger filterId);

    Request<?, OkcLog> okcGetFilterLogs(BigInteger filterId);

    Request<?, OkcLog> okcGetLogs(org.web3j.protocol.core.methods.request.OkcFilter okcFilter);

    Request<?, OkcGetWork> okcGetWork();

    Request<?, OkcSubmitWork> okcSubmitWork(String nonce, String headerPowHash, String mixDigest);

    Request<?, OkcSubmitHashrate> okcSubmitHashrate(String hashrate, String clientId);

    Request<?, DbPutString> dbPutString(String databaseName, String keyName, String stringToStore);

    Request<?, DbGetString> dbGetString(String databaseName, String keyName);

    Request<?, DbPutHex> dbPutHex(String databaseName, String keyName, String dataToStore);

    Request<?, DbGetHex> dbGetHex(String databaseName, String keyName);

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
