// Copyright by Barry G. Becker, 2012 - 2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.socket

import com.barrybecker4.ui.components.Appendable
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket


class ClientWorker (var client: Socket, var text: Appendable) extends Runnable {
  override def run(): Unit = {
    var line: String = null
    var in: BufferedReader = null
    var out: PrintWriter = null
    try {
      in = new BufferedReader(new InputStreamReader(client.getInputStream))
      out = new PrintWriter(client.getOutputStream, true)
    } catch {
      case e: IOException =>
        System.out.println("in or out failed")
        e.printStackTrace()
        System.exit(-1)
    }
    while (true) try {
      line = in.readLine
      //Send data back to client
      out.println("RECIEVED:" + line)
      text.append(line + '\n')
    } catch {
      case e: IOException =>
        throw new IllegalStateException("Read failed")
    }
  }
}
