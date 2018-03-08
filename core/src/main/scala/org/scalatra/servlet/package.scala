package org.scalatra

package object servlet {
  /**
   * Structural type for the various Servlet API objects that have attributes.
   * These include ServletContext, HttpSession, and ServletRequest.
    /* 结构类型
  http://hongjiang.info/scala-type-system-structural-type/
   */
   */
  private[scalatra]type Attributes = {
    def getAttribute(name: String): AnyRef
    def getAttributeNames(): java.util.Enumeration[String]
    def setAttribute(name: String, value: AnyRef): Unit
    def removeAttribute(name: String): Unit
  }
}
