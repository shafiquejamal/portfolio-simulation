package com.eigenroute.portfoliosimulation

import com.eigenroute.portfoliosimulation.db.DBConfig
import scalikejdbc._

class ETFDAO(dBConfig: DBConfig) {

  def by(eTFCodes: Seq[ETFCode]): Seq[ETFData] = {
    NamedDB(Symbol(dBConfig.dBName)) readOnly { implicit session =>
      val codes: Seq[String] = eTFCodes.map(_.code)
      sql"""SELECT code, xnumber, asofdate, nav, exdividend FROM historical WHERE nav != 0 AND code IN (${codes}) ORDER BY asofdate"""
      .map(ETFData.converter).list.apply()
    }
  }

}