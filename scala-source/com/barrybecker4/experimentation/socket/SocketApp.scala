package com.barrybecker4.experimentation.socket

import com.barrybecker4.experimentation.socket.client.ClientFrame
import com.barrybecker4.experimentation.socket.server.ServerFrame
import concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Opens 2 windows. The ServerFrame runs in a separate thread.
  * Type text in the ClientFrame and send it to the server via a socket.
  */
object SocketApp extends App {

  private val DEFAULT_HOST = "127.0.0.1" /// "192.168.1.100";
  private val PORT = 4444

  Future { ServerFrame(PORT) }

  ClientFrame(DEFAULT_HOST, PORT)
}
