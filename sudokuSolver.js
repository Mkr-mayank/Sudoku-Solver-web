document.addEventListener('DOMContentLoaded', () => {
    const board = document.getElementById('sudoku-board');
    const solveButton = document.getElementById('solve-button');
    const resetButton = document.getElementById('reset-button');

    // Initialize the Sudoku board
    for (let i = 0; i < 81; i++) {
        const input = document.createElement('input');
        input.setAttribute('type', 'number');
        input.setAttribute('min', '1');
        input.setAttribute('max', '9');
        board.appendChild(input);
    }

    solveButton.addEventListener('click', () => {
        const sudoku = getBoardValues();
        if (solveSudoku(sudoku)) {
            setBoardValues(sudoku);
        } else {
            alert('Solution does not exist');
        }
    });

    resetButton.addEventListener('click', resetBoard);

    function getBoardValues() {
        const inputs = board.getElementsByTagName('input');
        const sudoku = [];
        for (let i = 0; i < 9; i++) {
            sudoku.push([]);
            for (let j = 0; j < 9; j++) {
                const value = inputs[i * 9 + j].value;
                sudoku[i].push(value ? parseInt(value) : 0);
            }
        }
        return sudoku;
    }

    function setBoardValues(sudoku) {
        const inputs = board.getElementsByTagName('input');
        for (let i = 0; i < 9; i++) {
            for (let j = 0; j < 9; j++) {
                inputs[i * 9 + j].value = sudoku[i][j] || '';
            }
        }
    }

    function resetBoard() {
        const inputs = board.getElementsByTagName('input');
        for (let input of inputs) {
            input.value = '';
        }
    }

    function isSafe(sudoku, row, col, digit) {
        for (let i = 0; i < 9; i++) {
            if (sudoku[row][i] == digit || sudoku[i][col] == digit) {
                return false;
            }
        }
        const sr = Math.floor(row / 3) * 3;
        const sc = Math.floor(col / 3) * 3;
        for (let i = sr; i < sr + 3; i++) {
            for (let j = sc; j < sc + 3; j++) {
                if (sudoku[i][j] == digit) {
                    return false;
                }
            }
        }
        return true;
    }

    function solveSudoku(sudoku, row = 0, col = 0) {
        if (row == 9) {
            return true;
        }
        const nextRow = col == 8 ? row + 1 : row;
        const nextCol = col == 8 ? 0 : col + 1;

        if (sudoku[row][col] != 0) {
            return solveSudoku(sudoku, nextRow, nextCol);
        }

        for (let digit = 1; digit <= 9; digit++) {
            if (isSafe(sudoku, row, col, digit)) {
                sudoku[row][col] = digit;
                if (solveSudoku(sudoku, nextRow, nextCol)) {
                    return true;
                }
                sudoku[row][col] = 0;
            }
        }
        return false;
    }
});
