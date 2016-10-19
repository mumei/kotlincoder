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

import org.jetbrains.annotations.NotNull
import org.junit.Assert.assertThat
import org.hamcrest.CoreMatchers.`is` as eq
import org.junit.Test

/**
 * @author yuuto
 */
class KtConstructorTest{

    @Test
    fun ktConstructorNoArgTest(){
        val ktConstructor = KtConstructor()

        val creator = CodeCreator()
        ktConstructor.print(null,creator)
        val string = creator.outputString()
        assertThat(string,eq("constructor(\n)"))
    }

    @Test
    fun ktConstructorNoModifiersNoCodeTest(){

        val ktConstructor = KtConstructor(
                annotations = arrayOf(
                        KtAnnotation(clazz = JvmField::class.java),
                        KtAnnotation(clazz = NotNull::class.java)
                ),
                values = arrayOf(
                        KtValue(String::class.java,"value0"),
                        KtValue(Int::class.java,"value1")
                )
        )

        val creator = CodeCreator()
        ktConstructor.print(null,creator)
        val string = creator.outputString()
        assertThat(string,eq("@JvmField\n@NotNull\nconstructor(\n  value0:String,\n  value1:Int\n)"))
    }
    @Test
    fun ktConstructorNoCodeTest(){
        val ktConstructor = KtConstructor(
                annotations = arrayOf(
                        KtAnnotation(clazz = JvmField::class.java),
                        KtAnnotation(clazz = NotNull::class.java)
                ),
                modifiers = arrayOf("private"),
                values = arrayOf(
                        KtValue(String::class.java,"value0"),
                        KtValue(Int::class.java,"value1")
                )
        )

        val creator = CodeCreator()
        ktConstructor.print(null,creator)
        val string = creator.outputString()
        assertThat(string,eq("@JvmField\n@NotNull\nprivate constructor(\n  value0:String,\n  value1:Int\n)"))
    }
    @Test
    fun ktConstructorTest(){
        val ktConstructor = KtConstructor(
                annotations = arrayOf(
                        KtAnnotation(clazz = JvmField::class.java),
                        KtAnnotation(clazz = NotNull::class.java)
                ),
                modifiers = arrayOf("private"),
                values = arrayOf(
                        KtValue(String::class.java,"value0"),
                        KtValue(Int::class.java,"value1")
                ),
                codeLine = arrayOf(
                        CodeLine("val %F=%S","test0","abc0"),
                        CodeLine("val %F=%S","test1","abc1")
                )
        )

        ktConstructor.annotations
        ktConstructor.modifiers
        ktConstructor.values
        ktConstructor.codeLine

        val creator = CodeCreator()
        ktConstructor.print(null,creator)
        val string = creator.outputString()
        assertThat(string,eq("@JvmField\n@NotNull\nprivate constructor(\n  value0:String,\n  value1:Int\n){\n  val test0=\"abc0\"\n  val test1=\"abc1\"\n}"))
    }
}