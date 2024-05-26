package com.dreamers.transfers.service;

import com.dreamers.transfers.model.response.TransferResponse;
import com.dreamers.transfers.model.response.TransfersResponse;

public interface TransferService {
    TransfersResponse getAllTransfers();

    TransferResponse getTransferById(String id);

    TransfersResponse getTransfersBySearchTerm(String searchTerm);
}
