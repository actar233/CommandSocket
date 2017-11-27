package com.arthur.command.socket.server

import java.net.ServerSocket

object RunServer{
    fun run(port:Int=9090) {
        val server=ServerSocket(port)
        while (true){
            Channel(server.accept())
        }
    }
}