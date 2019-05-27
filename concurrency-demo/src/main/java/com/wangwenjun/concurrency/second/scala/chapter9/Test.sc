def curriedSum(x: Int)(y: Int) = x + y
curriedSum(1)(2)

def first(x: Int) = (y: Int) => x + y
val second = first(1)
second(2)

// 下划线 是第二个参数的占位符，结果就是指向一个参数的参考
// 这个函数在被调用的时候，对它唯一的Int 参数加1并返回结果
val onePlus = curriedSum(1) _
onePlus(2)

val twoPlus = curriedSum(2) _
twoPlus(2)


def twice(op: Double ⇒ Double, x: Double) = op.apply(op.apply(x))
twice(_ + 1, 5)
