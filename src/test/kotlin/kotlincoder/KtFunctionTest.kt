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
class KtFunctionTest {

    @Test
    fun ktFunctionTest(){
        var ktFunction = KtFunction(
                annotations = arrayOf(
                        KtAnnotation(clazz = Override::class.java),
                        KtAnnotation(clazz = Throwable::class.java)
                ),
                name = "test",
                clazz = Int::class.java,
                values = arrayOf(
                        KtValue(clazz = Int::class.java,name = "test1"),
                        KtValue(clazz = Int::class.java,name = "test2")
                ),
                modifiers = arrayOf("private"),
                codeLine = arrayOf(
                        CodeLine("val %F=%S","test","abc"),
                        CodeLine("val %F=%S","test2","abc")
                )
        )

        ktFunction.annotations
        ktFunction.name
        ktFunction.clazz
        ktFunction.values
        ktFunction.modifiers
        ktFunction.codeLine

        var creator = CodeCreator()
        ktFunction.print(null,creator)
        assertThat(creator.outputString(),eq("@Override\n@Throwable\nprivate fun test (\n  test1:Int,\n  test2:Int\n):Int{\n  val test=\"abc\"\n  val test2=\"abc\"\n}"))


        ktFunction = KtFunction(
                name = "test"
        )

        creator = CodeCreator()
        ktFunction.print(null,creator)
        assertThat(creator.outputString(),eq("fun test (\n){\n}"))


        ktFunction = KtFunction(
                name = "test",
                clazz = KtFunctionTest::class.java.getMethod("ktFunctionTest").returnType
        )
        creator = CodeCreator()
        ktFunction.print(null,creator)
        assertThat(creator.outputString(),eq("fun test (\n){\n}"))
    }

}