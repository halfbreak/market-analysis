package org.hlbk.trader.time

import org.hlbk.trader.models.Bar
import rx.{Ctx, Var}

class WindowedTimeSeries(bars: Var[Seq[Bar]], windowSize: Int)(implicit val ctx: Ctx.Owner) extends TimeSeries {

  private val sizeToRemain = windowSize - 1

  private val myBars = Var(bars.now.takeRight(windowSize))

  override def add(bar: Bar): Unit = {
    val tmp = myBars.now
    myBars() = tmp.takeRight(sizeToRemain) :+ bar
  }

  override def getSequence: Var[Seq[Bar]] = {
    myBars
  }

  override def toString: String = myBars.toString()
}
