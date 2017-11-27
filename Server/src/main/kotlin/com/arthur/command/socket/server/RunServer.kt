package com.arthur.command.socket.server

import java.net.ServerSocket

/**
 * 运行服务的主类
 */
object RunServer{
    /**
     * 运行
     */
    fun run(port:Int=9090) {
        val server=ServerSocket(port)
        while (true){
            Channel(server.accept())
        }
    }
}