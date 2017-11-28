package com.arthur.command.socket.server

/**
 * 命令
 */
interface Command{
    /**
     * 被调用的命令
     */
    fun command(args: List<String>, channel: Channel)
}