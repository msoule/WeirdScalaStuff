package tutorial.webapp

import scala.scalajs.js.JSApp
import org.scalajs.jquery.jQuery

import org.scalajs.dom

/**
 * Created by Mark Soule on 9/1/2015.
 */
object TutorialApp extends JSApp{

  def setupUI(): Unit = {
    jQuery("#click-me-button").click(addClickedMessage _)
    jQuery("body").append("<p>Hello World</p>")
    jQuery("""<button type="button">Click me!</button>""")
      .click(addClickedMessage _)
      .appendTo(jQuery("body"))

    dom.setInterval(() => {jQuery("p:contains('Hello World')").append("<p>click it...</p>")}, 100)
  }

  def addClickedMessage(): Unit = {
    jQuery("p:contains('Hello World')").append("<p>And goodbye</p>")
    //jQuery("body").append("<p>You did a thing!</p>")
  }

  def main(): Unit = {
    jQuery(setupUI _)
  }
}
