package controllers

import akka.dispatch.Futures
import play.api.mvc._
import services.{MockProductService, PriceService, ProductService, FlakyMockPriceService}
import model.{CategoryId, ProductId, ProductInfo}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

class Application(productService: ProductService, priceService: PriceService) extends Controller {

  def index = Action.async {
    for {
      categories <- productService.categories
    } yield Ok(views.html.index(categories))
  }

  def category(id: CategoryId) = Action.async{
    for {
      productsIds <- productService.category(id).map(_.map(_.map(productService.productDetails(_))))
      productsInfoOption <- productsIds.map(_.foldLeft(Future.successful[List[Option[ProductInfo]]](List()))((a, b) => a.flatMap(x => b.map(_ :: x))).map(_.flatMap(_.toList))) getOrElse (Future.successful(List()))
      category <- productService.categories.map(_.filter(_.id == id) headOption)
    } yield category match {
      case Some(x) =>Ok(views.html.categories(x.name)(productsInfoOption))
      case None => NotFound("Invalid Category")
    }
  }

  def product(productId: ProductId) = Action.async {
    for {
      productInfo <- productService.productDetails(productId)
      price <- priceService.price(productId).flatMap(x => Future.successful[String](x.map(_ toString()) getOrElse("0"))).recover(PartialFunction(_ => "Price Calculation failed"))
    } yield productInfo match {
      case Some(x) =>Ok(views.html.productInfo(x)(price))
      case None => NotFound("Invalid Product")
    }
  }

}

object Application extends Application(MockProductService, FlakyMockPriceService)