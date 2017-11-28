package com.arthur.command.socket.server

import com.arthur.command.socket.server.command.CloseSocketCommand

/**
 * 命令管理
 */
object CommandManager{
    private val list=HashMap<String, Class<out Command>>()
    init {
        register("CloseSocket", CloseSocketCommand::class.java)
    }

    /**
     * 注册
     */
    fun register(commandName:String,command: Class<out Command>){
        list.put(commandName,command)
    }

    /**
     * 调用
     */
    fun call(args:List<String>,channel: Channel){
        try {
            if(args.size>0){
                println(args)
                val commandName=args[0]
                if(list.containsKey(commandName)){
                    list[commandName]!!.newInstance().command(args.subList(1,args.size),channel)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
            channel.close()
        }
    }
}