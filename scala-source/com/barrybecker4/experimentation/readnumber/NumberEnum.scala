// Copyright by Barry G. Becker, 2000 - 2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.readnumber


abstract class NumberEnum(val label: String, val pronunciation: String) {
  override def toString: String = label
}
