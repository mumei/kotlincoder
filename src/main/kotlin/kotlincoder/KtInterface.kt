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

import java.util.*

/**
 * @author yuuto
 * @createAt 2016/10/18.
 */
class KtInterface @JvmOverloads constructor(
        val packageName:String,
        val className:String,
        val interfaceClass:Array<Class<*>> = emptyArray(),
        val functions: Array<KtFunction> = emptyArray()
):PrintCode {
    override fun print(parent: PrintCode?, codeCreator: CodeCreator): CodeCreator {

        var extend:String? = null
        if(interfaceClass.isNotEmpty()){

            val extends = ArrayList<String>()
            interfaceClass.forEach {
                extends.add(it.simpleName)
                codeCreator.clazzSet.add(it)
            }
            extend = ":"+extends.joinToString()

        }


        if(extend == null){
            codeCreator.put("interface $className{")
        }else{
            codeCreator.put("interface $className")
            codeCreator.put("$extend{")
        }

        codeCreator.intentIndex++
        functions.forEach {
            it.print(this, codeCreator)
            codeCreator.put("")
        }
        codeCreator.intentIndex--

        codeCreator.put("}")

        return codeCreator
    }
}