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

class KtValue @JvmOverloads constructor(
        val clazz:Class<*>,
        val name:String,
        val defaultValue:String?=null,
        val nullable:Boolean = false,
        val annotations:Array<KtAnnotation> = emptyArray()
):PrintCode{

    override fun print(parent: PrintCode?, codeCreator: CodeCreator): CodeCreator {
        annotations.forEach {
            it.print(this,codeCreator)
        }
        codeCreator.put("%F:%C${if(nullable)"?" else ""}${if(defaultValue!=null)"=$defaultValue" else ""}",name,clazz)

        return codeCreator
    }
}