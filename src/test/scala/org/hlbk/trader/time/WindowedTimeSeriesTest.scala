package org.hlbk.trader.time

import org.scalatest.FlatSpec

class WindowedTimeSeriesTest extends FlatSpec {

  import org.hlbk.trader.models.Bar
  import rx.{Ctx, Var}

  "A Window Time Series " should " only keep the determined history " in {
    val b1 = Bar(1.2, 1.1, 1.3, 1.1)
    val b2 = Bar(1.2, 1.1, 1.4, 1.2)
    val b3 = Bar(1.2, 1.1, 1.4, 1.3)
    val b4 = Bar(1.2, 1.1, 1.4, 1.4)
    val b5 = Bar(1.2, 1.1, 1.4, 1.5)
    val b6 = Bar(1.2, 1.1, 1.4, 1.6)
    val b7 = Bar(1.2, 1.1, 1.4, 1.7)
    val b8 = Bar(1.2, 1.1, 1.4, 1.8)
    val b9 = Bar(1.2, 1.1, 1.4, 1.9)
    val b10 = Bar(1.2, 1.1, 1.4, 1.1)

    implicit val ctx: Ctx.Owner = Ctx.Owner.safe()

    val windowSize = 5
    val timeSeries = new WindowedTimeSeries(Var(List(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10)), windowSize)

    assert(timeSeries.getSequence.now.length == windowSize)

    timeSeries.add(Bar(1.2, 1.1, 1.4, 5.10))
    assert(timeSeries.getSequence.now.length == windowSize)
  }
}
