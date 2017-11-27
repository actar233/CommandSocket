package com.arthur.command.socket.server

import com.arthur.command.socket.server.command.CloseSocketCommand

object CommandManager{
    private val list=HashMap<String, Class<out Command>>()
    init {
        register("CloseSocket", CloseSocketCommand::class.java)
    }
    fun register(commandName:String,command: Class<out Command>){
        list.put(commandName,command)
    }
    fun call(args:List<String>,channel: Channel){
        try {
            if(args.size>0){
                val commandName=args[0]
                if(list.containsKey(commandName)){
                    list[commandName]!!.newInstance().command(args.subList(1,args.size),channel)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            channel.close()
        }
    }
}