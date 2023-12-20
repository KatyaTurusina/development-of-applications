class Square(var x: Int, var y: Int, var side_len: Int) : Figure(0), Movable, Transforming {

    constructor(square: Square) : this(square.x, square.y, square.side_len)

    override fun area(): Float {
        return (side_len * 2).toFloat()
    }

    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
    }

    override fun resize(zoom: Int) {
        side_len *= zoom
    }

    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        if (centerX == x && centerY == y) {
            return
        }

        if (direction == RotateDirection.Clockwise) {
            x = y - centerY + centerX.also { y = -1 * (x - centerX) + centerY }
        } else {
            x = -1 * (y - centerY) + centerX.also { y = x - centerX + centerY }
        }
    }

    override fun toString(): String {
        return "{x: $x, y: $y, сторона: $side_len}"
    }
}