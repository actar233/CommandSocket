package com.arthur.command.socket.server

/**
 * 会话
 */
class Session{
    private val map:HashMap<String,Any>
    init {
        map=HashMap<String,Any>()
    }

    /**
     * 取值
     */
    fun get(key: Any): Any?{
        return map.get(key)
    }
    /**
     * 存值
     */
    fun set(key:String,value:Any){
        map.put(key,value)
    }

    /**
     * 清空
     */
    fun clear(){
        map.clear()
    }
}