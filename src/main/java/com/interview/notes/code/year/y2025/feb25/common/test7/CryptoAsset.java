package com.interview.notes.code.year.y2025.feb25.common.test7;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CryptoAsset {
    private String asset;
    private BigDecimal price;

    public static void main(String[] args) {
        List<CryptoAsset> cryptoAssets = List.of(
                new CryptoAsset("BTC", BigDecimal.valueOf(60000)),
                new CryptoAsset("ETH", BigDecimal.valueOf(2500)),
                new CryptoAsset("SOL", BigDecimal.valueOf(146)),
                new CryptoAsset("AVAX", BigDecimal.valueOf(26)),
                new CryptoAsset("LTC", BigDecimal.valueOf(64)),
                new CryptoAsset("BNB", BigDecimal.valueOf(548))
        );

        // Filtering assets with a price greater than 500 and getting the asset names
        List<String> assets = cryptoAssets.stream()
                .filter(asset -> asset.getPrice().compareTo(BigDecimal.valueOf(500)) > 0)
                .map(CryptoAsset::getAsset)
                .collect(Collectors.toList());

        // Print filtered assets
        assets.forEach(System.out::println);

        // Print all crypto assets
        System.out.println();
        cryptoAssets.forEach(cryptoAsset -> System.out.println(cryptoAsset.getAsset()));
    }
}
