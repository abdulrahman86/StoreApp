package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends Specification {
  
  "Application" should {
    
    "send 404 on a bad request" in {
      running(FakeApplication()) {
        route(FakeRequest(GET, "/boum")) must beNone        
      }
    }
    
    "render the index page" in {
      running(FakeApplication()) {
        val home = route(FakeRequest(GET, "/")).get
        
        status(home) must equalTo(OK)
        contentType(home) must beSome.which(_ == "text/html")
        contentAsString(home) must contain ("Main product categories")
      }
    }

    "render categories page" in {
      running(FakeApplication()) {
        val category = route(FakeRequest(GET, "/category/frisbees")).get

        status(category) must equalTo(OK)
        contentType(category) must beSome.which(_ == "text/html")
        contentAsString(category) must contain ("Frisbees")
      }
    }

    "render not valid category" in {
      running(FakeApplication()) {
        val category = route(FakeRequest(GET, "/category/frisbeess")).get

        status(category) must equalTo(NOT_FOUND)
        contentType(category) must beSome.which(_ == "text/plain")
        contentAsString(category) must contain ("Invalid Category")
      }
    }

    "render products page" in {
      running(FakeApplication()) {
        val category = route(FakeRequest(GET, "/product/133")).get

        status(category) must equalTo(OK)
        contentType(category) must beSome.which(_ == "text/html")
        contentAsString(category) must contain ("Whamo Super Disc")
      }
    }

    "render not valid product" in {
      running(FakeApplication()) {
        val category = route(FakeRequest(GET, "/product/1331")).get

        status(category) must equalTo(NOT_FOUND)
        contentType(category) must beSome.which(_ == "text/plain")
        contentAsString(category) must contain ("Invalid Product")
      }
    }
  }
}