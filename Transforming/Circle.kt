class Circle(var x: Int, var y: Int, var radius: Int) : Figure(0), Movable, Transforming {

    constructor(circle: Circle) : this(circle.x, circle.y, circle.radius)

    override fun area(): Float {
        return (3.14 * radius * radius).toFloat();
    }

    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
    }

    override fun resize(zoom: Int) {
        radius *= zoom
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
        return "{x: $x, y: $y, радиус: $radius}"
    }
}