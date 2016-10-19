package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

/**
 * add your integration spec here.
 * An integration test will fire up a whole play application in a real (or headless) browser
 */
class IntegrationSpec extends Specification {
  
  "Application" should {
    
    "work from within a browser" in {
      running(TestServer(3333), HTMLUNIT) { browser =>

        //Index page test
        browser.goTo("http://localhost:3333/")
        browser.pageSource must contain("Main product categories")

        //Valid category test
        browser.goTo("http://localhost:3333/category/frisbees")
        browser.pageSource must contain ("Frisbees")

        //Invalid category test
        browser.goTo("http://localhost:3333/category/frisbeess")
        browser.pageSource must contain ("Invalid Category")

        //Valid product test
        browser.goTo("http://localhost:3333/product/133")
        browser.pageSource must contain ("Whamo Super Disc")

        //Invalid product test
        browser.goTo("http://localhost:3333/product/1333")
        browser.pageSource must contain ("Invalid Product")


      }
    }
    
  }
  
}