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

class KtClass @JvmOverloads constructor(
        val packageName:String,
        val className:String,
        val extendsClass:KtExtend?=null,
        val interfaceClass:Array<Class<*>> = emptyArray(),
        val fields:Array<KtField> = emptyArray(),
        val init:KtInit?=null,
        val constructor: KtConstructor? = null,
        val constructors:Array<KtConstructor> = emptyArray(),
        val companionObject: KtObject?=null,
        val functions: Array<KtFunction> = emptyArray(),
        val annotations:Array<KtAnnotation> = emptyArray()
):PrintCode{
    override fun print(parent: PrintCode?, codeCreator: CodeCreator): CodeCreator {
        annotations.forEach {
            it.print(this,codeCreator)
        }
        var extend:String? = null
        if(extendsClass!=null||interfaceClass.isNotEmpty()){

            val extends = ArrayList<String>()
            if(extendsClass!=null){
                extends.add(extendsClass.print())
                codeCreator.clazzSet.add(extendsClass.clazz)
            }
            interfaceClass.forEach {
                extends.add(it.simpleName)
                codeCreator.clazzSet.add(it)
            }
            extend = ":"+extends.joinToString()

        }

        if(constructor==null && extend == null){
            codeCreator.put("class $className{")
        }else{
            codeCreator.put("class $className")
            constructor?.print(this,codeCreator)
            if(extend!=null) {
                codeCreator.put(extend)
            }
            codeCreator.put("{")
        }
        codeCreator.intentIndex++

        if(companionObject!=null) {
            companionObject.print(this, codeCreator)
            codeCreator.put("")
        }

        if(constructors.isNotEmpty()){
            constructors.forEach {
                it.print(this,codeCreator)
            }
            codeCreator.put("")
        }

        if(fields.isNotEmpty()) {
            fields.forEach {
                it.print(this, codeCreator)
            }
            codeCreator.put("")
        }

        if(init!=null){
            init.print(this, codeCreator)
            codeCreator.put("")
        }

        functions.forEach {
            it.print(this, codeCreator)
            codeCreator.put("")
        }

        codeCreator.intentIndex--
        codeCreator.put("}")

        return codeCreator
    }


}