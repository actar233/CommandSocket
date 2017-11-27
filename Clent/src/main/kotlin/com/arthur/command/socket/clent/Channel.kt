package com.arthur.command.socket.clent

import org.apache.commons.codec.binary.Base64
import java.net.Socket

open class Channel(ip:String,port:Int){
    val socket=Socket(ip,port)
    val input=socket.getInputStream()
    val bufferedReader=input.bufferedReader(ClientConfig.charset)
    val output=socket.getOutputStream()
    val bufferedWriter=output.bufferedWriter(ClientConfig.charset)
    fun read():String{
        return bufferedReader.readLine()
    }
    fun readByte():Byte{
        return read().toByte()
    }
    fun readShort():Short{
        return read().toShort()
    }
    fun readInt():Int{
        return read().toInt()
    }
    fun readLong():Long{
        return read().toLong()
    }
    fun readChar():Char{
        return readCharArray()[0]
    }
    fun readCharArray():CharArray{
        return read().toCharArray()
    }
    fun readFloat():Float{
        return read().toFloat()
    }
    fun readDouble():Double{
        return read().toDouble()
    }
    fun readBoolean():Boolean{
        return read().toBoolean()
    }
    fun readByteArrayByBase64():ByteArray{
        return Base64.decodeBase64(read())
    }
    fun reads():List<String>{
        return splice(this.read());
    }
    fun write(text:String){
        bufferedWriter.write(text)
        bufferedWriter.newLine()
        bufferedWriter.flush()
    }
    fun writes(vararg texts:String){
        if(texts.size==0){
            return
        }
        val build=StringBuilder()
        for(i in 0 until  texts.size){
            build.append("${texts[i].replace("\\","\\\\").replace(" ","\\ ")} ")
        }
        build.deleteCharAt(build.length-1)
        val text=build.toString()
        write(text)
    }
    fun close(){
        write("CloseSocket")
        bufferedReader.close()
        bufferedWriter.close()
        input.close()
        output.close()
        socket.close()
    }
    private fun splice(text:String):List<String>{
        val list=ArrayList<String>()
        var build=StringBuilder()
        var flag=false
        text.forEach {
            when(it){
                ' '->{
                    if(!flag){
                        list.add(build.toString())
                        build=kotlin.text.StringBuilder()
                    }else{
                        build.append(it)
                        flag=!flag
                    }
                }
                '\\'->{
                    if(flag){
                        build.append(it)
                    }
                    flag=!flag
                }
                else->{
                    build.append(it)
                }
            }
        }
        if(build.length>0){
            list.add(build.toString())
        }
        return list
    }
}