fun main() {
    val movable: Movable = Rect(0, 0, 1, 1)
    movable.move(1, 1)
    val rect: Figure = Rect(0, 0, 1, 1)
    val circle: Figure = Circle(3, 2, 2)
    val square: Figure = Square(1, 3, 3)

    val transformingRect: Transforming = Rect(8, -1, 1, 2)
    val transformingCircle: Transforming = Circle(3, 0, 2)
    val transformingSquare: Transforming = Square(5, 0, 3)

    print("Прямоугольник: $rect")
    print("\nПлощадь = ")
    println(rect.area())
    transformingRect.resize(5)
    println("Увеличить(5): $transformingRect")
    transformingRect.rotate(RotateDirection.Clockwise, 0, 0)
    println("Повернуть(0, 0): $transformingRect")

    print("\nКруг: $circle")
    print("\nПлощадь = ")
    println(circle.area())
    transformingCircle.resize(2)
    println("Увеличить(2): $transformingCircle")
    transformingCircle.rotate(RotateDirection.Clockwise, 3, 4)
    println("Повернуть(3, 4): $transformingCircle")

    print("\nКвадрат: $square")
    print("\nПлощадь = ")
    println(square.area())
    transformingSquare.resize(2)
    println("Увеличить(2): $transformingSquare")
    transformingSquare.rotate(RotateDirection.Clockwise, 3, 4)
    println("Повернуть(3, 4): $transformingSquare")

}