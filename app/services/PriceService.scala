package services

import scala.concurrent.Future
import scala.util.Random
import model.{ProductId, Price}

trait PriceService {
  def price(productId: ProductId): Future[Option[Price]]
}

object MockPriceService extends PriceService {
  def price(productId: ProductId) = Future.successful {
    if (productId == "115") Some(9.99D)
    else if (productId == "133") None
    else Some(4.99D)
  }
}

object FlakyMockPriceService extends PriceService {
  def price(productId: ProductId) = {
    val isFailure = Random.nextInt(4) == 0
    if (isFailure) Future.failed(new Exception("Someone snipped the network cable!"))
    else MockPriceService.price(productId)
  }
}