// Copyright by Barry G. Becker, 2012 - 2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.socket

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import com.barrybecker4.ui.components.Appendable

/**
  * Worker thread that runs on the server and listens for messages from client over the socket.
  * @author Barry Becker
  */
class ClientWorker (var client: Socket, var text: Appendable) extends Runnable {
  override def run(): Unit = {
    var line: String = null
    var in: BufferedReader = null
    var out: PrintWriter = null
    try {
      in = new BufferedReader(new InputStreamReader(client.getInputStream))
      out = new PrintWriter(client.getOutputStream, true)
    } catch {
      case e: IOException => throw new IllegalStateException("in or out failed", e)
    }
    while (true) try {
      line = in.readLine
      //Send data back to client
      out.println("SERVER RECEIVED:[ " + line + " ]")
      text.append(line + '\n')
    } catch {
      case e: IOException =>
        throw new IllegalStateException("Read from client failed.")
    }
  }
}
