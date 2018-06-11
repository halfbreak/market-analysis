package org.hlbk.trader.time

import org.hlbk.trader.models.Bar
import rx.Var

trait TimeSeries {

  def add(bar: Bar): Unit

  def getSequence: Var[Seq[Bar]]

}
