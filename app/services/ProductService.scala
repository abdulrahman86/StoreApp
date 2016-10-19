package services

import model.{ProductInfo, ProductCategory, CategoryId, ProductId}
import scala.concurrent.Future

trait ProductService {
  def categories: Future[Seq[ProductCategory]]
  def category(categoryId: CategoryId): Future[Option[Seq[ProductId]]]
  def productDetails(productId: ProductId): Future[Option[ProductInfo]]
}

object MockProductService extends ProductService {

  private val mockProductData = Map(
    "133" -> ProductInfo("133", "Whamo Super Disc", Map("weight" -> "16oz", "colour" -> "red")),
    "115" -> ProductInfo("115", "Acme Ankle Buster", Map("weight" -> "48oz", "colour" -> "blue")),
    "103" -> ProductInfo("103", "Tabby", Map("weight" -> "15 lbs", "colour" -> "tabby")),
    "112" -> ProductInfo("112", "Unlucky", Map("weight" -> "7 lbs", "colour" -> "black")),
    "109" -> ProductInfo("109", "Standard Witches Model", Map("weight" -> "18 lbs", "colour" -> "black")),
    "701" -> ProductInfo("701", "40 Creek", Map("ABV" -> "40%", "colour" -> "amber", "volume" -> "1L")),
    "553" -> ProductInfo("553", "Jameson's", Map("ABV" -> "40%", "colour" -> "gold", "volume" -> "1.44L"))
  )

  private val mockCategoriesData = Map(
    "frisbees" -> Seq("133", "115"),
    "cats" -> Seq("103", "112", "109"),
    "liquor" -> Seq("701", "553")
  )

  def categories = Future.successful{
    Seq(
      ProductCategory("frisbees", "Frisbees"),
      ProductCategory("cats", "Cats"),
      ProductCategory("liquor", "Alcohol"))
  }

  def category(categoryId: CategoryId) = Future.successful {
    mockCategoriesData.get(categoryId)
  }

  def productDetails(productId: ProductId) = Future.successful {
    mockProductData.get(productId)
  }
}

