/*
 * Copyright 2016 Yuto Uehara
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kotlincoder

import java.io.File
import java.util.*
import kotlin.reflect.KClass


class CodeCreator internal constructor(){
    private val lines:MutableList<String> = ArrayList()
    internal var intentIndex:Int = 0

    internal val clazzSet:MutableSet<Class<*>> = mutableSetOf()

    internal fun modifyCode(index:Int,code:String){
        lines[index] = code
    }

    internal fun getCode(index:Int):String=lines[index]

    internal fun getLastIndex():Int = lines.size-1

    internal fun outputString():String{
        return lines.joinToString(separator = "\n")
    }
    internal fun insert(index:Int,code:String){
        lines.add(index,code)
    }

    internal fun outputFile(file: File){
        file.bufferedWriter().use { writer ->
            lines.forEach {
                writer.appendln(it)
            }
        }
    }

    internal fun put(code:String,vararg obj:Any):Int{
        val encoded = encode(code,obj)
        val tabStringBuilder = StringBuilder()
        for(i in 1..intentIndex){tabStringBuilder.append("  ")}
        lines.add(tabStringBuilder.toString()+encoded)
        return getLastIndex()
    }

    internal fun encode(code:String,obj:Array<out Any>):String{
        val builder = StringBuilder()
        val chars=code.toCharArray()
        var i=0
        var objIndex=0
        while (i<chars.size){
            if(chars[i]=='\\'){
                if(i+1<chars.size && chars[i+1] =='%'){
                    builder.append('%')
                    i++
                }else{
                    builder.append('\\')
                }
            }else if(chars[i]=='%'){
                if(i+1<chars.size){
                    val c:String = when(chars[i+1]){
                        'C'->{//Class
                            when(obj[objIndex]){
                                is Class<*> ->{
                                    val clazz:Class<*> = obj[objIndex] as Class<*>
                                    when(clazz.name){
                                        "void"->"Void"
                                        "long"->"Long"
                                        "int"->"Int"
                                        "double"->"Double"
                                        "float"->"Float"
                                        "java.lang.String"->"String"
                                        else->{
                                            clazzSet.add(clazz)
                                            clazz.simpleName
                                        }
                                    }
                                }
                                else -> throw IllegalArgumentException("request Class object[index:$i, objectIndex:$objIndex] ${obj[objIndex]}")
                            }
                        }
                        'S'->{//String
                            when(obj[objIndex]){
                                is String->"\"${obj[objIndex]}\""
                                else ->throw IllegalArgumentException("request String object[index:$i, objectIndex:$objIndex]")
                            }
                        }
                        'N'->{//Number
                            when(obj[objIndex]){
                                is Int,is Short,is Long,is Float,is Double -> "${obj[objIndex]}"
                                else->throw IllegalArgumentException("request Number object[index:$i, objectIndex:$objIndex]")
                            }
                        }
                        'F'->{//Function or Field
                            when(obj[objIndex]){
                                is String-> "${obj[objIndex]}"
                                else ->throw IllegalArgumentException("request String object[index:$i, objectIndex:$objIndex]")
                            }
                        }
                        else->throw IllegalArgumentException("no good code format.[index:$i]")
                    }
                    builder.append(c)
                    i++
                    objIndex++
                }else{
                    throw IllegalArgumentException("no good code format.[index:$i]")
                }
            }else{
                builder.append(chars[i])
            }
            i++
        }
        return builder.toString()
    }
}