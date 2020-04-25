// Copyright by Barry G. Becker, 2000-2020. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.socket.client

import java.io.{BufferedReader, IOException, InputStreamReader, PrintWriter}
import java.net.{Socket, UnknownHostException}


/**
  * Socket connection to server
  * @author Barry Becker
  */
object Client {
  private def exceptionOccurred(msg: String, t: Throwable): Unit = throw new IllegalStateException(msg, t)
}

class Client(host: String, port: Int) {

  private var out: PrintWriter = _
  private var in: BufferedReader = _

  try {
    val socket: Socket = new Socket(host, port)
    out = new PrintWriter(socket.getOutputStream, true)
    in = new BufferedReader(new InputStreamReader(socket.getInputStream))
    println("create listen out_1 = " + out)
  } catch {
    case e: UnknownHostException => Client.exceptionOccurred("Unknown host: " + host, e)
    case e: IOException => Client.exceptionOccurred("No I/O", e)
  }
  assert(out != null, "Failed to create socket")

  /** @param text text to send to server */
  def send(text: String): Unit = out.println(text)

  /** @return text received from server */
  def receive(): String = {
    try {
      return in.readLine
    } catch {
      case e: IOException => Client.exceptionOccurred("Read failed", e)
    }
    null
  }
}