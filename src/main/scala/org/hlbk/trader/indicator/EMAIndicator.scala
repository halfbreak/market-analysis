package org.hlbk.trader.indicator

import org.hlbk.trader.time.TimeSeries
import rx.{Ctx, Rx}

class EMAIndicator(timeSeries: TimeSeries, period: Integer, sma: Double)(implicit val ctx: Ctx.Owner) extends Indicator {

  private val multiplier: Double = 2 / (period.toDouble + 1)

  private var previousDayEMA = sma

  private val r = Rx {
    val close = timeSeries.getSequence().last.close
    val now = previousDayEMA
    val result = (close - now) * multiplier + now
    result
  }

  r.trigger {
    previousDayEMA = r.now
  }

  override def value: Double = {
    r.now
  }

  def value2: Rx.Dynamic[Double] = {
    r
  }
}
