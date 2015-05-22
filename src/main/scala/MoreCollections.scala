import scala.collection.mutable

/**
 * Created by eerilio on 5/21/15.
 */
object MoreCollections {
  //*********************************THE BASICS**************************************
  //List - standard linked list
  List(1,2,3)
  1 :: 2 :: 3 :: Nil

  //Set - sets have no duplicates
  Set(1,1,2) // scala.collection.immutable.Set[Int] = Set(1, 2)

  //Seq - have a defined order
  Seq(1,1,2) //List(1, 1, 2)

  //Map - key->value containers
  Map('a' -> 1, 'b' -> 2)

  //*******************************The Hierarchy*************************************
  //Theses are traits
  //Traversable - all collections can be traversed. This defines standard function combinators. Use foreach
  //Iterable - Has an iterator() method to give you an iterator over the elements
  //Seq - Sequence of items with ordering
  //Set - Collection of items with no duplicates
  //Map - key value pairs

  //*********************************The Methods*************************************
  //Traversable
  def map [B] (f: (A) => B) : CC[B]
  def foreach[U](f: Elem => U): Unit
  def find (p: (A) => Boolean) : Option[A]
  def filter (p: (A) => Boolean) : Traversable[A]
  def partition (p: (A) ⇒ Boolean) : (Traversable[A], Traversable[A])
  def groupBy [K] (f: (A) => K) : Map[K, Traversable[A]]

  //conversion
  def toArray : Array[A]
  def toArray [B >: A] (implicit arg0: ClassManifest[B]) : Array[B]
  def toBuffer [B >: A] : mutable.Buffer[B]
  def toIndexedSeq [B >: A] : IndexedSeq[B]
  def toIterable : Iterable[A]
  def toIterator : Iterator[A]
  def toList : List[A]
  def toMap [T, U] (implicit ev: <:<[A, (T, U)]) : Map[T, U]
  def toSeq : Seq[A]
  def toSet [B >: A] : Set[B]
  def toStream : Stream[A]
  def toString () : String
  def toTraversable : Traversable[A]

  //ex:
  Map(1->2).toArray // Array[(Int, Int)] = Array((1,2))

  //Iterable - adds access to an iterator
  def iterator: Iterator[A]
  def hasNext(): Boolean
  def next(): A

  //Set
  def contains(key:A):Boolean
  def +(elem: A): Set[A]
  def -(elem:A):Set[A]

  //Map - sequence of key and value pairs with lookup by key
  Map("a" -> 1, "b" -> 2) //Declaration
  Map(("a", 2), ("b", 2))

  // -> is a method, not a syntax, that returns a tuple
  // ++ add elements to a Map

  //****************Mutable vs. Immutable****************
  //Immutable - cant change in multiple threads,but cant be changed at all
  //scala encourages immutability, similar to var vs val

  //**********************Mutable************************
  //Hashmap - defines getOrElseUpdate

  val numbers = collection.mutable.Map(1->2) //scala.collection.mutable.Map[Int,Int] = Map((1,2))
  numbers.get(1) //Option[Int] = Some(2)
  numbers.getOrElseUpdate(2, 3) //Int=3 -> hashmap now has Map((2,3), (1,2))
  numbers += (4->1) //numbers.type = Map((2,3), (4,1), (1,2))

  /*others include:
  ListBuffer, ArrayBuffer
  LinkedList and DoubleLinkedList
  PriorityQueue
  Stack and ArrayStack
  Stringbuilder
  */
}
