package com.arthur.command.socket.server.command

import com.arthur.command.socket.server.Channel
import com.arthur.command.socket.server.Command

/**
 * 定义关闭Socket的命令
 */
class CloseSocketCommand : Command{
    override fun command(args: List<String>, channel: Channel) {
        channel.close()
    }
}