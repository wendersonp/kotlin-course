package games.gameOfFifteen

import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
    GameOfFifteen(initializer)

class GameOfFifteen(
        private val initializer: GameOfFifteenInitializer
) : Game {
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        val values = initializer.initialPermutation
        board.getAllCells().forEachIndexed { index, cell ->
            board[cell] = runCatching { values[index] }.getOrNull()
        }
    }

    override fun canMove(): Boolean = true

    override fun hasWon(): Boolean =
        board.getAllCells().map { board[it] }
                .slice(0 until 15) == (1..15)
                .toList()


    override fun processMove(direction: Direction) {
        val emptyCell = board.find { it == null }

        with(board) {
            val nonEmptyCell = emptyCell
                    ?.getNeighbour(direction.reversed()) ?: return

            board[emptyCell] = board[nonEmptyCell]
            board[nonEmptyCell] = null
        }
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }

}



