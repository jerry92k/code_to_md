/*
[전략]
1. 배열을 row, column 순으로 정렬하여
2. 각각의 reservedSeat 사이가 4명이 앉을수있는 곳인지 판단한다
*/

import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Arrays.sort(reservedSeats, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int rowNum = 1;
        int lastOccupied = 0;
        int count = 0;
        for (int i = 0; i < reservedSeats.length; i++) {

            if (reservedSeats[i][0] > rowNum) { // row 바뀜

                int diffRow = reservedSeats[i][0] - rowNum;
                // 한줄이 전체 비었을때 최대 2 그룹이 앉을 수 있음
                if (diffRow > 1) {
                    count += (diffRow - 1) * 2;
                }
                // 이전 줄의 마지막 자리가 어디냐에 따라 달라짐
                if (lastOccupied < 2) {
                    count += 2;
                } else if (lastOccupied < 6) {
                    count++;
                }
                rowNum = reservedSeats[i][0];
                lastOccupied = 0;
            }

            if (reservedSeats[i][1] >= 6 && reservedSeats[i][1] <= 7) {
                if (lastOccupied < 2) {
                    count++;
                }
            } else if (reservedSeats[i][1] >= 8 && reservedSeats[i][1] <= 9) {
                if (lastOccupied < 4) {
                    count++;
                }
            } else if (reservedSeats[i][1] == 10) {
                if (lastOccupied < 2) {
                    count += 2;
                } else if (lastOccupied < 6) {
                    count++;
                }
            }

            lastOccupied = reservedSeats[i][1];
        }

        if (lastOccupied < 2) {
            count += 2;
        } else if (lastOccupied < 6) {
            count++;
        }
        if (rowNum < n) {
            count += (n - rowNum) * 2;
        }
        return count;
    }
}