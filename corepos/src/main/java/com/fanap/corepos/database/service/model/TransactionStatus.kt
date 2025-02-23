package com.fanap.corepos.database.service.model

enum class TransactionStatus {
    TransactionSent,
    TransactionResReceived,
    TransactionResUnpacked,
    TransactionResUnpackedResponseError,
    TransactionResDidNotUnpack,
    InvalidMac,
    TransactionSentTimeOut,
    StartSuccessPrint,
    ReceiptPrinted,
    ReceiptDidNotPrint,
    AdviceSent,
    AdviceResReceived,
    AdviceResUnpacked,
    AdviceResUnpackedResponseError,
    AdviceResDidNotUnpack,
    AdviceSentTimeOut,
    ReverseSent,
    ReverseResReceived,
    ReverseResUnpacked,
    ReverseResDidNotUnpack,
    ReverseResUnpackedResponseError,
    ReverseSentTimeOut,
    LogonSent,
    LogonResReceived,
    GetInfoSent,
    GetInfoResReceived,
    ResetSent,
    ResetResReceived
}