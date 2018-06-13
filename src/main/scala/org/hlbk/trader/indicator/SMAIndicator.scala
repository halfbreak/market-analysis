package org.hlbk.trader.indicator

import org.hlbk.trader.time.TimeSeries
import rx.{Ctx, Rx}

class SMAIndicator(timeSeries: TimeSeries, period: Integer)(implicit val ctx: Ctx.Owner) extends Indicator {

  val r = Rx {
    timeSeries.getSequence().takeRight(period).map(_.close).sum / period
  }

  override def value: Double = {
    r.now
  }

}
