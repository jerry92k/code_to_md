/**
 * @param {character[][]} grid
 * @return {number}
 */
var numIslands = function(grid) {
    
    let rowLen = grid.length;
    let colLen = grid[0].length;
    let count = 0;

    for (let i = 0; i < rowLen; i++){
        for (let j = 0; j < colLen; j++){

            if (grid[i][j] == '1') {
                //console.log(`grid[i][j] : ${i} , ${j}`);
                count++;
                dfs(grid,i,j,rowLen,colLen);
            }// 0 일결우는 다음으로 넘어감
        }
    }
    return count;
};

var dfs = function (grid, i, j,rowLen,colLen) {

    grid[i][j] = '2';
    if (i > 0 && grid[i - 1][j] == '1') {
        dfs(grid, i - 1, j, rowLen, colLen);
    }
    if (i < rowLen - 1 && grid[i + 1][j] == '1') {
        dfs(grid, i + 1, j, rowLen, colLen);
    }

    if (j > 0 && grid[i][j - 1] == '1') {
        dfs(grid, i, j - 1, rowLen, colLen);
    }

    if (j < colLen-1 && grid[i][j + 1] == '1') {
        dfs(grid, i, j + 1, rowLen, colLen);
    }

};


