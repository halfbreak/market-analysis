package org.hlbk.trader.indicator

import org.hlbk.trader.time.TimeSeries
import rx.{Ctx, Rx}

class SMAIndicator(timeSeries: TimeSeries, period: Integer)(implicit val ctx: Ctx.Owner) extends Indicator {

  override def value: Double = {
    Rx {
      timeSeries.getSequence().takeRight(period).map(_.close).sum / period
    }.now
  }

}
