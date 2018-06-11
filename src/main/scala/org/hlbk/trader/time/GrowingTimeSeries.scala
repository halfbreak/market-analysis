package org.hlbk.trader.time

import org.hlbk.trader.models.Bar
import rx.{Ctx, Var}

class GrowingTimeSeries(bars: Var[Seq[Bar]])(implicit val ctx: Ctx.Owner) extends TimeSeries {

  override def add(bar: Bar): Unit = {
    val tmp = bars.now
    bars() = tmp :+ bar
  }

  override def getSequence: Var[Seq[Bar]] = {
    bars
  }

  override def toString: String = bars.toString()
}
