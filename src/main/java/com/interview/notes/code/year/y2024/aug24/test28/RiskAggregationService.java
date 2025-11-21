package com.interview.notes.code.year.y2024.aug24.test28;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RiskAggregationService {

    // Example usage
    public static void main(String[] args) {
        RiskAggregationService service = new RiskAggregationService();

        List<RiskData> riskDataList = List.of(
                new RiskData("Credit", 100.0),
                new RiskData("Market", 200.0),
                new RiskData("Credit", 150.0),
                new RiskData("Operational", 50.0),
                new RiskData("Market", 300.0)
        );

        Map<String, Double> aggregatedRisks = service.aggregateRisks(riskDataList);

        aggregatedRisks.forEach((riskType, totalValue) ->
                System.out.println(riskType + ": " + totalValue));
    }

    public Map<String, Double> aggregateRisks(List<RiskData> riskDataList) {
        return riskDataList.stream()
                .collect(Collectors.groupingBy(
                        RiskData::getRiskType,
                        Collectors.summingDouble(RiskData::getValue)
                ));
    }

    public static class RiskData {
        private final String riskType;
        private final double value;

        public RiskData(String riskType, double value) {
            this.riskType = riskType;
            this.value = value;
        }

        public String getRiskType() {
            return riskType;
        }

        public double getValue() {
            return value;
        }
    }
}
