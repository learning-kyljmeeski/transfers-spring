package com.dreamers.transfers.model.response;

import java.util.Map;

public record TransferResponse(
        String name, String previousClub, String newClub, String price, String successRate, Map<String, String> stats
) {
}
