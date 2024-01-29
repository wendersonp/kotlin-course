package board

open class SquareBoardImpl(
        override val width: Int
): SquareBoard {
    private val cells: List<Cell>

    init {
        cells = (0..width*width - 1)
                .toList()
                .map { Cell(it/width + 1, it % width + 1) }
    }
    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return cells.find { it.i == i && it.j == j }
    }

    override fun getCell(i: Int, j: Int): Cell {
        return cells.find { it.i == i && it.j == j } ?: throw IllegalArgumentException()
    }

    override fun getAllCells(): Collection<Cell> {
        return cells
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return cells.filter { it.i == i && it.j in jRange }.run {
            if (jRange.first > jRange.last) {
               asReversed()
            } else {
                this
            }
        }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        return cells.filter { it.i in iRange && it.j == j }.run {
            if (iRange.first > iRange.last) {
                asReversed()
            } else {
                this
            }
        }
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when(direction) {
            Direction.UP -> cells.find { element ->
                element.i == this.i-1 &&
                        element.j == this.j
            }
            Direction.LEFT -> cells.find { element ->
                element.i == this.i &&
                        element.j == this.j-1
            }
            Direction.DOWN -> cells.find { element ->
                element.i == this.i+1 &&
                        element.j == this.j
            }
            Direction.RIGHT -> cells.find { element ->
                element.i == this.i &&
                        element.j == this.j+1
            }
        }
    }
}