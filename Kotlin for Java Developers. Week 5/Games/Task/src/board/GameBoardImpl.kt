package board

class GameBoardImpl<T>(width: Int)
    : GameBoard<T>, SquareBoardImpl(width) {
    private val content: MutableMap<Cell, T?> = mutableMapOf()

    init {
        getAllCells().associateWithTo(content) { null }
    }

    override fun get(cell: Cell): T? {
        return content[cell]
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return content.values.all(predicate)
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return content.values.any(predicate)
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return content.asSequence().find {
            predicate(it.value)
        }?.key
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return content.filter {
            predicate(it.value)
        }.keys
    }

    override fun set(cell: Cell, value: T?) {
        content[cell] = value
    }

    operator fun get(i: Int, j: Int): T? {
        return get(getCell(i, j))
    }

    operator fun set(i: Int, j: Int, value: T?) {
        set(getCell(i, j), value)
    }
}