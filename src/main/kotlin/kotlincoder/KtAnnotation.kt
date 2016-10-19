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



class KtAnnotation @JvmOverloads constructor(
        val clazz: Class<*>,
        val values:Array<KtAnnotationValue> = emptyArray()
):PrintCode{
    override fun print(parent: PrintCode?, codeCreator: CodeCreator): CodeCreator {
        if(values.isNotEmpty()) {
            codeCreator.put("@%C(", clazz)
            codeCreator.intentIndex++
            values.forEachIndexed { i, it ->
                it.print(this, codeCreator)
                if (values.size - 1 != i) {
                    val index = codeCreator.getLastIndex()
                    val code = codeCreator.getCode(index)
                    codeCreator.modifyCode(index, "$code,")
                }
            }
            codeCreator.intentIndex--
            codeCreator.put(")")
        }else{
            codeCreator.put("@%C", clazz)
        }
        return codeCreator
    }

    class KtAnnotationValue(val name:String,val value:String):PrintCode{
        override fun print(parent: PrintCode?, codeCreator: CodeCreator): CodeCreator {
            codeCreator.put("%F=%S",name,value)
            return codeCreator
        }

    }
}