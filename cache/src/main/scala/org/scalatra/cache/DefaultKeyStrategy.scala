package org.scalatra.cache

import javax.servlet.http.{ HttpServletResponse, HttpServletRequest }
import scala.util.hashing.MurmurHash3
  //用了MurmurHash算法
    //http://comeonbabye.iteye.com/blog/1676199
    //http://blog.csdn.net/wisage/article/details/7104866
object DefaultKeyStrategy extends KeyStrategy {
  override def key(implicit request: HttpServletRequest, response: HttpServletResponse): String = {
    MurmurHash3.stringHash(request.getPathInfo + request.getQueryString).toString
  }
}
