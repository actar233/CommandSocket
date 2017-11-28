package com.arthur.command.socket.clent

import org.apache.commons.codec.binary.Base64
import java.net.Socket

/**
 * 通道
 */
open class Channel(ip:String,port:Int){
    val socket=Socket(ip,port)
    val input=socket.getInputStream()
    val bufferedReader=input.bufferedReader(ClientConfig.charset)
    val output=socket.getOutputStream()
    val bufferedWriter=output.bufferedWriter(ClientConfig.charset)
    /**
     * 读取一行数据
     * @return String
     */
    fun read():String{
        return bufferedReader.readLine()
    }

    /**
     * 读取一行数据,并转换为Byte
     * @return Byte
     */
    fun readByte():Byte{
        return read().toByte()
    }

    /**
     * 读取一行数据,并转换为Short
     * @return Short
     */
    fun readShort():Short{
        return read().toShort()
    }

    /**
     * 读取一行数据,并转换为Int
     * @return Int
     */
    fun readInt():Int{
        return read().toInt()
    }

    /**
     * 读取一行数据,并转换为Long
     * @return Long
     */
    fun readLong():Long{
        return read().toLong()
    }

    /**
     * 读取一行数据,并转换为Char
     * @return Char
     */
    fun readChar():Char{
        return readCharArray()[0]
    }

    /**
     * 读取一行数据,并转换为ByteArrray
     * @return CharArray
     */
    fun readCharArray():CharArray{
        return read().toCharArray()
    }

    /**
     * 读取一行数据,并转换为Float
     * @return Float
     */
    fun readFloat():Float{
        return read().toFloat()
    }

    /**
     * 读取一行数据,并转换为Double
     * @return Double
     */
    fun readDouble():Double{
        return read().toDouble()
    }

    /**
     * 读取一行数据,并转换为Boolean
     * @return Boolean
     */
    fun readBoolean():Boolean{
        return read().toBoolean()
    }

    /**
     * 读取一行数据,并通过Base64解码为ByteArray
     * @return ByteArray
     */
    fun readByteArrayByBase64():ByteArray{
        return Base64.decodeBase64(read())
    }

    /**
     * 读取一行数据,并解码转换为List<String>
     * @return List<String>
     */
    fun reads():List<String>{
        return decode(this.read());
    }

    /**
     * 写入一行数据,并立即flush
     * @return Unit
     */
    fun write(text:String){
        bufferedWriter.write(text)
        bufferedWriter.newLine()
        bufferedWriter.flush()
    }

    /**
     * 将ByteArray进行Base64编码,并且写入flush
     * @return Unit
     */
    fun writeByteArrayByBase64(buff:ByteArray){
        write(Base64.encodeBase64String(buff))
    }

    /**
     * 将Array<String>编码后写入,并且flush
     * @return Unit
     */
    fun writes(vararg texts:String){
        if(texts.size==0){
            return
        }
        write(encode(listOf(*texts)))
    }

    /**
     * 关闭通道
     * @return Unit
     */
    fun close(){
        bufferedReader.close()
        bufferedWriter.close()
        input.close()
        output.close()
        socket.close()
    }

    /**
     * 编码
     * 示例:
     * [123,456 56,\56]
     * "123 456\ 56\\56"
     *@return String
     */
    fun encode(texts:List<String>):String{
        val build=StringBuilder()
        for(i in 0 until  texts.size){
            build.append("${texts[i].replace("\\","\\\\").replace(" ","\\ ")} ")
        }
        build.deleteCharAt(build.length-1)
        return build.toString()
    }

    /**
     * 解码
     * 示例:
     * "123 456\ 56\\56"
     * [123,456 56,\56]
     * @return List<String>
     */
    private fun decode(text:String):List<String>{
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