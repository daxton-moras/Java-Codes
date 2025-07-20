# Gas Station Circuit Problem
This repository contains a Java solution to the classic "Gas Station Circuit" problem. The problem asks us to find a starting gas station from which a car can travel a circular route, given the amount of gas available at each station and the cost to travel to the next station.

## Problem Description
Imagine you have a car and a circular route with n gas stations. You are given two arrays: gas and cost.

gas[i] represents the amount of gas available at station i.

cost[i] represents the cost to travel from station i to station i + 1 (with cost[n-1] being the cost to travel from station n-1 to station 0).

Your goal is to find the starting gas station index from which you can complete the full circular trip. If no such station exists, return -1.

## Solution Approach
The provided canCompleteCircuit method implements an efficient greedy approach to solve this problem.

## Intuition
The core idea is to track the net fuel gain or loss at each station. If, at any point, our accumulated fuel drops below zero, it means that the current starting point is not viable. In such a scenario, we must choose the next station as our potential new starting point, resetting our accumulated fuel.

## There are two crucial observations:

If the total gas available across all stations is less than the total cost to travel the entire circuit, it's impossible to complete the circuit. In this case, no matter where you start, you'll eventually run out of fuel.

If the total gas is greater than or equal to the total cost, a solution must exist. Furthermore, the first station where the cumulative gas[i] - cost[i] never dips below zero (when iterating through potential start points) will be our answer.

## Algorithm Steps
Initialize Variables:

n: The number of gas stations.

totalDiff: Accumulates the total difference between gas and cost across all stations. This helps us check the first crucial observation.

currentDiff: Tracks the cumulative fuel difference starting from the startStation.

startStation: Stores the potential starting index. It's initialized to 0.

## Iterate Through Stations:

For each station i from 0 to n-1:

Calculate diff = gas[i] - cost[i]. This is the net fuel change at the current station.

Add diff to totalDiff.

Add diff to currentDiff.

Check for Insufficient Fuel: If currentDiff becomes less than 0:

This means we cannot reach station i+1 from our current startStation.

We reset startStation to i + 1 (the next potential starting point).

We reset currentDiff to 0, effectively "starting fresh" from the new startStation.

## Final Check:

After iterating through all stations, if totalDiff is less than 0, it's impossible to complete the circuit, so we return -1.

Otherwise, a solution exists, and startStation holds the index of the valid starting gas station. We return startStation.

## Complexity Analysis
Time Complexity: The solution involves a single pass through the gas and cost arrays. Therefore, the time complexity is O(n), where n is the number of gas stations.

Space Complexity: The solution uses a few constant extra variables (n, totalDiff, currentDiff, startStation). Thus, the space complexity is O(1).
