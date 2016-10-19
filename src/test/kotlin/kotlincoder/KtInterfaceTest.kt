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

import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import java.io.Serializable

/**
 * @author yuuto
 */
class KtInterfaceTest{

    @Test
    fun ktInterfaceTest(){
        val ktInterface = KtInterface(
                packageName = "com.test",
                className = "Test",
                interfaceClass = arrayOf(
                        Serializable::class.java
                ),
                functions = arrayOf(
                        KtFunction(name = "test"),
                        KtFunction(
                                name = "test2",
                                clazz = Int::class.java
                        )
                )
        )

        ktInterface.className
        ktInterface.packageName
        ktInterface.functions
        ktInterface.interfaceClass

        val creator = CodeCreator()
        ktInterface.print(null,creator)
        val string = creator.outputString()
        println(string)
        Assert.assertThat(string, CoreMatchers.`is`("interface Test\n:Serializable{\n  fun test (\n  )\n  \n  fun test2 (\n  ):Int\n  \n}"))
    }

    @Test
    fun ktInterfaceSimpleTest(){
        val ktInterface = KtInterface(
                packageName = "com.test",
                className = "Test"
        )

        val creator = CodeCreator()
        ktInterface.print(null,creator)
        val string = creator.outputString()
        println(string)
        Assert.assertThat(string, CoreMatchers.`is`("interface Test{\n}"))
    }
}