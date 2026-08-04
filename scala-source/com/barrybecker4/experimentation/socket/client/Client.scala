// Copyright by Barry G. Becker, 2000-2020. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.socket.client

import java.io.{BufferedReader, IOException, InputStreamReader, PrintWriter}
import java.net.{Socket, UnknownHostException}


/**
  * Socket connection to server
  * @author Barry Becker
  */
object Client:
  private def exceptionOccurred(msg: String, t: Throwable): Nothing =
    throw new IllegalStateException(msg, t)

class Client(host: String, port: Int):

  private val (out, in): (PrintWriter, BufferedReader) =
    try
      val socket = new Socket(host, port)
      val outWriter = new PrintWriter(socket.getOutputStream, true)
      val inReader = new BufferedReader(new InputStreamReader(socket.getInputStream))
      println("create listen out_1 = " + outWriter)
      (outWriter, inReader)
    catch
      case e: UnknownHostException => Client.exceptionOccurred("Unknown host: " + host, e)
      case e: IOException => Client.exceptionOccurred("No I/O", e)

  /** @param text text to send to server */
  def send(text: String): Unit = out.println(text)

  /** @return text received from server */
  def receive(): String =
    try in.readLine
    catch
      case e: IOException => Client.exceptionOccurred("Read failed", e)
