package com.dreamers.transfers.model.response;

import java.util.List;

public record TransfersResponse(List<Transfer> transfers) {
    public record Transfer(String id, String name, String previousClub, String newClub, String price) {

    }
}
