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

class KtExtend @JvmOverloads constructor(
        val clazz: Class<*>,
        val argEnable:Boolean = false,
        val args:Array<String> = emptyArray()
){
    fun print():String{
        return "${clazz.simpleName}${if(argEnable) "(${args.joinToString()})" else ""}"
    }
}
