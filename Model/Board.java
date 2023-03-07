package Model;

public class Board {
    private Cell[][] cells;
    int boardSize;
    public Board(int boardSize, int numberOfSnakes, int numberOfLadders){
        this.boardSize = boardSize;
        initializeCells(boardSize);
        addSnakesAndLadders(cells, numberOfSnakes, numberOfLadders);
    }
    private void initializeCells(int boardSize){
        cells = new Cell[boardSize][boardSize];
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                Cell cellObj = new Cell();
                cells[i][j] = cellObj;
            }
        }
    }
    private void addSnakesAndLadders(Cell[][] cells, int numberOfSnakes, int numberOfLadders){
        while(numberOfSnakes>0){
            int snakeHead = (int) Math.floor(Math.random()*(cells.length*cells.length)+1);
            int snakeTail = (int) Math.floor(Math.random()*(cells.length* cells.length)+1);
            if(snakeTail>=snakeHead) continue;
            Jump snakeObj = new Jump();
            snakeObj.start = snakeHead;
            snakeObj.end = snakeTail;
            Cell cell = getCell(snakeHead);
            cell.jump = snakeObj;
            numberOfSnakes--;
        }
        while(numberOfLadders>0){
            int ladderStart = (int) Math.floor(Math.random()*(cells.length*cells.length)+1);
            int ladderEnd = (int) Math.floor(Math.random()*(cells.length*cells.length)+1);
            if(ladderEnd<ladderStart) continue;
            Jump ladderObj = new Jump();
            ladderObj.start = ladderStart;
            ladderObj.end = ladderEnd;
            Cell cell = getCell(ladderStart);
            cell.jump = ladderObj;
            numberOfLadders--;
        }
    }
    public Cell getCell(int position){
        int row = position/ cells.length;
        int column = position% cells.length;
        return cells[row][column];
    }
    public int getWinningPosition(){
        return boardSize*boardSize;
    }
}
