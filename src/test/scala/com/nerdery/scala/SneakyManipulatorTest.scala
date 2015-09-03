package com.nerdery.scala

import utest._

import org.scalajs.jquery.jQuery

/**
 * Created by Mark Soule on 9/1/2015.
 */
object SneakyManipulatorTest extends TestSuite {
  def tests = TestSuite {
    'DoASwap {
      SneakyManipulator.setupHead()
      SneakyManipulator.setupBody()
      val testContent = SneakyManipulator.contentArray(1)
      var testParagraph = jQuery("p:contains('" + testContent + "')")

      assert(testParagraph.length > 0)

      if(testParagraph(0).toString().equals(testContent)) {
        SneakyManipulator.swapChars(SneakyManipulator.contentArray(1), 0, 1)
        //SneakyManipulator.wait(SneakyManipulator.MAGIC_NUMBER + 1000)
        testParagraph = jQuery("p:contains('" + testContent + "')")
        assert(!testParagraph(0).toString().equals(testContent))
      }


    }

  }
}
