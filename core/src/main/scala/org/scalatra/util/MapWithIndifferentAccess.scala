package org.scalatra.util

import scala.collection.Map

/**
  * http://www.cnblogs.com/fanxiaopeng/p/5231602.html
 * Inspired by(受到启发) Rails' MapWithIndifferentAccess, allows the substitution of symbols for strings as map keys.  Note
 * that the map is still keyed with strings; symbols are stored in permgen, so symbol keys maps should not be used
 * for maps with arbitrary（武断) keys.  There is no performance gain using symbols.  It is here to make our Rubyists feel
 * more at home.
 */
//协变 +B
trait MapWithIndifferentAccess[+B] extends Map[String, B] {

  def get(key: Symbol): Option[B] = get(key.name)

  def getOrElse[B1 >: B](key: Symbol, default: => B1): B1 = getOrElse(key.name, default)

  def apply(key: Symbol): B = apply(key.name)

}

