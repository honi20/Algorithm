class Solution {
    final int[] DELTA_ROW = {-1,1,0,0};
    final int[] DELTA_COL = {0,0,-1,1};
    
    final int EMPTY = 0;
    final int RED_START = 1;
    final int BLUE_START = 2;
    final int RED_DEST = 3;
    final int BLUE_DEST = 4;
    final int WALL = 5;
    
    final int RED = 0;
    final int BLUE = 1;
    
    class Point {
        int row, col;
        
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    int rowSize, colSize;
    boolean[][][] isVisited;
    int result;

    public int solution(int[][] maze) {
        int answer = 0;
        
        rowSize = maze.length;
        colSize = maze[0].length;
        isVisited = new boolean[rowSize][colSize][2];
        
        Point redPos = null, bluePos = null;
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (maze[row][col] == RED_START) {
                    redPos = new Point(row, col);
                    isVisited[row][col][RED] = true;
                }
                else if (maze[row][col] == BLUE_START) {
                    bluePos = new Point(row, col);
                    isVisited[row][col][BLUE] = true;
                }
            }
        }
        
        result = Integer.MAX_VALUE;
        solve(redPos, bluePos, 0, maze);
        
        answer = (result == Integer.MAX_VALUE) ? 0 : result;
        
        return answer;
    }
    
    private void solve(Point redPos, Point bluePos, int turn, int[][] maze) {
        // System.out.println("===" + turn + "===");
        // System.out.println(redPos.row + " " + redPos.col);
        // System.out.println(bluePos.row + " " + bluePos.col);
        
        // 두 수레가 모두 도착한 경우
        if (isDestination(redPos, RED_DEST, maze) && isDestination(bluePos, BLUE_DEST, maze)) {
            result = Math.min(result, turn);
            return;
        }
        
        if (result <= turn) {
            return;    
        }
        
        // red 수레는 도착한 경우
        if (isDestination(redPos, RED_DEST, maze)) {
            move(bluePos, redPos, BLUE, turn, maze);
        }
        // blue 수레는 도착한 경우
        else if (isDestination(bluePos, BLUE_DEST, maze)) {
            move(redPos, bluePos, RED, turn, maze);
        }
        // 둘 다 도착하지 않은 경우
        else {
            moveTwo(redPos, bluePos, RED, BLUE, turn, maze);
        }
    }
    
    private void move(Point curPos, Point other, int color, int turn, int[][] maze) {
        for (int dir = 0; dir < 4; dir++) {
            int nextRow = curPos.row + DELTA_ROW[dir];
            int nextCol = curPos.col + DELTA_COL[dir];
            
            // 범위를 벗어나는 경우, 패스
            if (nextRow < 0 || nextCol < 0 || nextRow >= rowSize || nextCol >= colSize) {
                continue;
            }
            
            // 벽인 경우, 패스
            if (maze[nextRow][nextCol] == WALL) {
                continue;
            }
            
            // 다른 수레가 존재하는 경우, 패스
            if ((nextRow == other.row) && (nextCol == other.col)) {
                continue;
            }
            
            // 이미 방문한 칸이 경우, 패스
            if (isVisited[nextRow][nextCol][color]) {
                continue;
            }
            
            isVisited[nextRow][nextCol][color] = true;
            if (color == RED) {
                solve(new Point(nextRow, nextCol), other, turn + 1, maze);
            }
            else {
                solve(other, new Point(nextRow, nextCol), turn + 1, maze);
            }
            
            isVisited[nextRow][nextCol][color] = false;
        }
    }
    
    private void moveTwo(Point first, Point second, int firstColor, int secondColor, int turn, int[][] maze) {
        for (int dir1 = 0; dir1 < 4; dir1++) {
            for (int dir2 = 0; dir2 < 4; dir2++) {
                int firstRow = first.row + DELTA_ROW[dir1];
                int firstCol = first.col + DELTA_COL[dir1];
                int secondRow = second.row + DELTA_ROW[dir2];
                int secondCol = second.col + DELTA_COL[dir2];
                
                // 범위를 벗어나는 경우, 패스
                if (firstRow < 0 || firstCol < 0 || firstRow >= rowSize || firstCol >= colSize) {
                    // System.out.println("hi1" + dir1 + " " + dir2);
                    continue;
                }
                if (secondRow < 0 || secondCol < 0 || secondRow >= rowSize || secondCol >= colSize) {
                    // System.out.println("hi2" + dir1 + " " + dir2);
                    continue;
                }
                
                // 벽인 경우, 패스
                if ((maze[firstRow][firstCol] == WALL) || (maze[secondRow][secondCol] == WALL)) {
                    // System.out.println("hi3" + dir1 + " " + dir2);
                    continue;
                }
                
                // 동시에 같은 칸으로 이동하려는 경우, 패스
                if ((firstRow == secondRow) && (firstCol == secondCol)) {
                    // System.out.println("hi4" + dir1 + " " + dir2);
                    continue;
                }
                
                // 수레끼리 자리를 바꾸는 경우, 패스
                if ((firstRow == second.row) && (firstCol == second.col) && (secondRow == first.row) && (secondCol == first.col)) {
                    // System.out.println("hi5" + dir1 + " " + dir2);
                    continue;
                }
                
                // 이미 방문한 칸인 경우, 패스
                if (isVisited[firstRow][firstCol][firstColor] || isVisited[secondRow][secondCol][secondColor]) {
                    // System.out.println("hi6" + dir1 + " " + dir2);
                    continue;
                }
                
                // System.out.println("hi" + dir1 + " " + dir2);
                isVisited[firstRow][firstCol][firstColor] = true;
                isVisited[secondRow][secondCol][secondColor] = true;
                solve(new Point(firstRow, firstCol), new Point(secondRow, secondCol), turn + 1, maze);
                isVisited[firstRow][firstCol][firstColor] = false;
                isVisited[secondRow][secondCol][secondColor] = false;
            }
        }
    }
    
    private boolean isDestination(Point point, int dest, int[][] maze) {
        if (maze[point.row][point.col] == dest) {
            return true;
        }
        
        return false;
    }
}