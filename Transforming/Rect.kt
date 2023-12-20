class Rect(var x: Int, var y: Int, var width: Int, var height: Int) : Movable, Transforming, Figure(0) {

    constructor(rect: Rect) : this(rect.x, rect.y, rect.width, rect.height)

    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
    }

    override fun area(): Float {
        return (width * height).toFloat()
    }

    override fun resize(zoom: Int) {
        width *= zoom
        height *= zoom
    }

    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        width = height.also { height = width }
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
        return "{x: $x, y: $y, ширина: $width, высота: $height}"
    }
}
