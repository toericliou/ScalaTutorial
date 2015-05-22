/**
 * Created by eerilio on 5/21/15.
 */

case class SimpleParsed(id: Long, test:String)

class SimpleParser {
  val tweetRegex = "\"id\":(.*),\"text\":\"(.*)\"".r

  def parse(str:String)={
    tweetRegex.findFirstMatchIn(str)
    match{
      case Some(m) => {
        val id = str.substring(m.start(1), m.end(1)).toInt
        val text = str.substring(m.start(2), m.end(2))
        Some(SimpleParsed(id, text))
      }
      case _ => None
    }
  }
}
