package org.hlbk.trader

import org.hlbk.trader.indicator.SMAIndicator
import org.hlbk.trader.models.Bar
import org.hlbk.trader.time.GrowingTimeSeries
import rx.{Ctx, Var}

object Main {
  def main(args: Array[String]): Unit = {

    val b1 = Bar(1.2, 1.1, 1.3, 1.1)
    val b2 = Bar(1.2, 1.1, 1.4, 1.2)
    val b3 = Bar(1.2, 1.1, 1.4, 1.3)
    val b4 = Bar(1.2, 1.1, 1.4, 1.4)
    val b5 = Bar(1.2, 1.1, 1.4, 1.5)
    val b6 = Bar(1.2, 1.1, 1.4, 1.6)
    val b7 = Bar(1.2, 1.1, 1.4, 1.7)
    val b8 = Bar(1.2, 1.1, 1.4, 1.8)
    val b9 = Bar(1.2, 1.1, 1.4, 1.9)
    val b10 = Bar(1.2, 1.1, 1.4, 1.10)

    implicit val ctx: Ctx.Owner = Ctx.Owner.safe()

    val t2 = new GrowingTimeSeries(Var(List(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10)))
    val smaInd2 = new SMAIndicator(t2, 5)

    println(smaInd2.value)

    t2.add(Bar(1.2, 1.1, 1.4, 5.10))
    println(smaInd2.value)
  }
}
