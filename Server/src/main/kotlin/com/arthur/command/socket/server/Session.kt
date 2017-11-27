package com.arthur.command.socket.server

class Session{
    private val map:HashMap<String,Any>
    init {
        map=HashMap<String,Any>()
    }
    fun get(key: Any): Any?{
        return map.get(key)
    }
    fun set(key:String,value:Any){
        map.put(key,value)
    }
    fun clear(){
        map.clear()
    }
}