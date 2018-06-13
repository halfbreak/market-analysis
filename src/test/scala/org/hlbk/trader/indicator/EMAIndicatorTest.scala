package org.hlbk.trader.indicator

import org.scalatest.FlatSpec

class EMAIndicatorTest extends FlatSpec {

  import org.hlbk.trader.models.Bar
  import rx.{Ctx, Var}

  "A EMA indicator" should " calculate and automatically update on a windowed time series " in {
    import org.hlbk.trader.time.WindowedTimeSeries
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

    val timeSeries = new WindowedTimeSeries(Var(List(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10)), 10)

    val emaInd = new SMAIndicator(timeSeries, 10)
    val emaInd2 = new EMAIndicator(timeSeries, 10, emaInd.value)

    assert(emaInd2.value == 1.3945454545454545)
    assert(emaInd2.value == 1.3945454545454545)

    timeSeries.add(Bar(1.2, 1.1, 1.4, 5.1))
    assert(emaInd2.value == 2.068264462809917)
    assert(emaInd2.value == 2.068264462809917)

    timeSeries.add(Bar(1.2, 1.1, 1.4, 5.1))
    assert(emaInd2.value == 2.619489105935387)
  }
}
