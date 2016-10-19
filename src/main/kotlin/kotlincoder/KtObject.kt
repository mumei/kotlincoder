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

class KtObject @JvmOverloads constructor(
        val keywords:Array<String> = emptyArray(),
        val fields:Array<KtField> = emptyArray(),
        val init:KtInit?=null,
        val functions: Array<KtFunction> = emptyArray()
):PrintCode {


    override fun print(parent: PrintCode?, codeCreator: CodeCreator): CodeCreator {
        codeCreator.put("${if(keywords.isNotEmpty())"${keywords.joinToString(separator = " ")} " else ""}object{")
        codeCreator.intentIndex++

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