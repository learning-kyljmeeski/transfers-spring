package com.dreamers.transfers.service.impl;

import com.dreamers.transfers.model.response.TransferResponse;
import com.dreamers.transfers.model.response.TransfersResponse;
import com.dreamers.transfers.service.TransferService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

@Service
public class TransferServiceImpl implements TransferService {
    @Override
    public TransfersResponse getAllTransfers() {
        List<TransfersResponse.Transfer> transfers = new ArrayList<>();
        try (
                Reader reader = new FileReader("src/main/resources/data/transfers.csv");
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())
        ) {
            for (CSVRecord csvRecord : csvParser) {
                transfers.add(
                        new TransfersResponse.Transfer(
                                csvRecord.get("id"),
                                csvRecord.get("playerName"),
                                csvRecord.get("previousClub"),
                                csvRecord.get("newClub"),
                                csvRecord.get("price")
                        )
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TransfersResponse(transfers);
    }

    @Override
    public TransferResponse getTransferById(String id) {
        Map<String, String> stats = new HashMap<>();
        try (
                Reader reader = new FileReader("src/main/resources/data/transfers.csv");
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())
        ) {
            for (CSVRecord csvRecord : csvParser) {
                if (csvRecord.get("id").equals(id)) {
                    String position = csvRecord.get("position");
                    if (position.equals("GK")) {
                        stats.put("Clean sheets", csvRecord.get("cleanSheets"));
                        stats.put("Saves percentage", csvRecord.get("savesPercentage"));
                        stats.put("Errors leading to goals", csvRecord.get("goalieErrors"));
                    } else if (Set.of("CB", "DM", "LB", "RB", "CM").contains(position)) {
                        stats.put("Tackles won", csvRecord.get("tacklesWon"));
                        stats.put("Interceptions", csvRecord.get("interceptions"));
                        stats.put("Cleaners", csvRecord.get("cleaners"));
                    } else if (Set.of("CF", "RW", "LW", "LM", "RM", "AM").contains(position)) {
                        stats.put("Goals", csvRecord.get("goals"));
                        stats.put("Assists", csvRecord.get("assists"));
                        stats.put("Chances created", csvRecord.get("chancesCreated"));
                    }
//                    String name, String previousClub, String newClub, String price, float successRate, Map<String, String> stats
                    return new TransferResponse(
                            csvRecord.get("playerName"), csvRecord.get("previousClub"), csvRecord.get("newClub"),
                            csvRecord.get("price"), csvRecord.get("successRate"), stats
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TransfersResponse getTransfersBySearchTerm(String searchTerm) {
        List<TransfersResponse.Transfer> transfers = new ArrayList<>();
        try (
                Reader reader = new FileReader("src/main/resources/data/transfers.csv");
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())
        ) {
            for (CSVRecord csvRecord : csvParser) {
                if (
                        csvRecord.get("playerName").toLowerCase().contains(searchTerm) ||
                                csvRecord.get("playerName").toLowerCase().contains(searchTerm) ||
                                csvRecord.get("newClub").toLowerCase().contains(searchTerm)
                ) {
                    transfers.add(
                            new TransfersResponse.Transfer(
                                    csvRecord.get("id"),
                                    csvRecord.get("playerName"),
                                    csvRecord.get("previousClub"),
                                    csvRecord.get("newClub"),
                                    csvRecord.get("price")
                            )
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TransfersResponse(transfers);
    }
}
