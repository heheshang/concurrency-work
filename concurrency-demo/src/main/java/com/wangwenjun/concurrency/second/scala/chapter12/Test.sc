

trait Philosophical {
	def philosophical(): Unit = {
		println("T counsume memory ,therefore I am")
	}
}

class Frog extends Philosophical {
	override def toString: String = "green"
}

val frog = new Frog

frog.philosophical()

val phli: Philosophical = frog


phli.philosophical()