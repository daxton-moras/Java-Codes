class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int totalDiff = 0;   // Total gas - cost difference for the entire circuit
        int currentDiff = 0; // Cumulative difference starting from the current potential startStation
        int startStation = 0; // Index of the potential starting station

        for (int i = 0; i < n; i++) {
            int diff = gas[i] - cost[i]; // Net fuel change at current station
            totalDiff += diff; // Accumulate overall fuel difference
            currentDiff += diff; // Accumulate fuel difference from the current startStation

            // If currentDiff drops below zero, it means we can't reach the current station
            // from the current startStation. We need to try starting from the next station.
            if (currentDiff < 0) {
                startStation = i + 1; // Try starting from the next station
                currentDiff = 0;     // Reset cumulative fuel for the new potential start
            }
        }

        // If totalDiff is negative, it's impossible to complete the circuit,
        // regardless of the starting point.
        if (totalDiff < 0) {
            return -1;
        }

        // Otherwise, a valid starting station exists, and startStation holds its index.
        return startStation;
    }
}
