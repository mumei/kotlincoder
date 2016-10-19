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

import org.junit.Assert.assertThat
import org.hamcrest.CoreMatchers.`is` as eq
import org.junit.Test

/**
 * @author yuuto
 */
class KtObjectTest {

    @Test
    fun ktObjectTest(){
        var ktObject = KtObject(
                keywords = arrayOf("companion"),
                fields = arrayOf(
                        KtField(rewritable = false,
                                clazz = Int::class.java,
                                name = "test0"),

                        KtField(rewritable = false,
                                clazz = Int::class.java,
                                name = "test1")
                ),
                init = KtInit(
                        codeLine = arrayOf(
                                CodeLine("val %F=%N","test",123)
                        )
                ),
                functions = arrayOf(
                        KtFunction(name = "test"),
                        KtFunction(name = "test2")
                )
        )

        ktObject.keywords
        ktObject.fields
        ktObject.init
        ktObject.functions

        var codeCreator = CodeCreator()
        ktObject.print(null,codeCreator)

        assertThat(codeCreator.outputString(),eq("companion object{\n  val test0:Int\n  val test1:Int\n  \n  init{\n    val test=123\n  }\n  \n  fun test (\n  ){\n  }\n  \n  fun test2 (\n  ){\n  }\n  \n}"))

        ktObject = KtObject(
        )

        codeCreator = CodeCreator()
        ktObject.print(null,codeCreator)

        assertThat(codeCreator.outputString(),eq("object{\n}"))

    }
}