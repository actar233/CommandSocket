package com.arthur.command.socket.server

/**
 * 命令
 */
open interface Command{
    /**
     * 被调用的命令
     */
    open fun command(args: List<String>, channel: Channel)
}