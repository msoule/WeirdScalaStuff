package tutorial.webapp

import scala.scalajs.js.JSApp
import org.scalajs.jquery.jQuery

import scala.scalajs.js.annotation.JSExport

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
  }

  def addClickedMessage(): Unit = {
    jQuery("body").append("<p>You did a thing!</p>")
  }

  def main(): Unit = {
    jQuery(setupUI _)
  }
}
