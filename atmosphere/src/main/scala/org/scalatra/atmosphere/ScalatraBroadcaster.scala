package org.scalatra
package atmosphere

import java.util.concurrent.ConcurrentLinkedQueue

import _root_.akka.actor._
import grizzled.slf4j.Logger
import org.atmosphere.cpr._
//Realtime Client Server Framework for the JVM, supporting WebSockets with Cross-Browser Fallbacks

//http://www.tuicool.com/articles/mUzyeq
//Neal Ford是全球IT咨询公司ThoughtWorks的软件架构师兼Meme Wrangler。
// Atlanta 亚特兰大 美国佐治亚州首府
//Poly Programming

//由于都运行在JVM上，Java与Scala之间基本能做到无缝的集成，
// 区别主要在于各自的API各有不同。
// 由于Scala为集合提供了更多便捷的函数，因此，Java与Scala在集合之间的互操作，或许是在这种多语言平台下使用最为频繁的
import scala.collection.JavaConverters._
import scala.concurrent.{ ExecutionContext, Future }

trait ScalatraBroadcaster extends Broadcaster {

  private[this] val logger: Logger = Logger[ScalatraBroadcaster]
  protected var _resources: ConcurrentLinkedQueue[AtmosphereResource]
  protected var _wireFormat: WireFormat
  protected implicit var _actorSystem: ActorSystem
//http://stackoverflow.com/questions/4531455/whats-the-difference-between-ab-and-b-in-scala
  //means that class T can take any class A that is a subclass of OutboundMessage
  def broadcast[T <: OutboundMessage](msg: T, clientFilter: ClientFilter)(implicit executionContext: ExecutionContext): Future[T] = {
    val selectedResources = _resources.asScala filter clientFilter
    logger.trace("Sending %s to %s".format(msg, selectedResources.map(_.uuid)))
    broadcast(_wireFormat.render(msg), selectedResources.toSet.asJava).map(_ => msg)
  }

}
