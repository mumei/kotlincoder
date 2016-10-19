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
import java.io.Serializable
import java.util.*

/**
 * @author yuuto
 */
class KtClassTest {

    @Test
    fun ktClassTest(){
        var ktClass = KtClass(
                packageName = "org.packageTest",
                className = "TestClass",
                annotations = arrayOf(
                        KtAnnotation(Test::class.java)
                ),
                extendsClass = KtExtend(ArrayList::class.java),
                interfaceClass = arrayOf(
                        Serializable::class.java
                ),
                constructor = KtConstructor(
                ),
                companionObject = KtObject(
                        keywords = arrayOf("companion")
                ),
                constructors = arrayOf(
                        KtConstructor(),
                        KtConstructor()
                ),
                fields = arrayOf(
                        KtField(false,Int::class.java,"test")
                ),
                init = KtInit(),
                functions = arrayOf(
                        KtFunction("test")
                )
        )
        ktClass.annotations
        ktClass.packageName
        ktClass.className
        ktClass.extendsClass
        ktClass.interfaceClass
        ktClass.constructor
        ktClass.constructors
        ktClass.companionObject
        ktClass.fields
        ktClass.init
        ktClass.functions

        var creator = CodeCreator()
        ktClass.print(null,creator)

        assertThat(creator.outputString(),eq("@Test\nclass TestClass\nconstructor(\n)\n:ArrayList, Serializable\n{\n  companion object{\n  }\n  \n  constructor(\n  )\n  constructor(\n  )\n  \n  val test:Int\n  \n  init{\n  }\n  \n  fun test (\n  ){\n  }\n  \n}"))


        ktClass = KtClass(
                packageName = "org.packageTest",
                className = "TestClass"
        )

        creator = CodeCreator()
        ktClass.print(null,creator)

        assertThat(creator.outputString(),eq("class TestClass{\n}"))


        ktClass = KtClass(
                packageName = "org.packageTest",
                className = "TestClass",
                extendsClass = KtExtend(ArrayList::class.java)
        )

        creator = CodeCreator()
        ktClass.print(null,creator)

        assertThat(creator.outputString(),eq("class TestClass\n:ArrayList\n{\n}"))



        ktClass = KtClass(
                packageName = "org.packageTest",
                className = "TestClass",
                interfaceClass = arrayOf(
                        Serializable::class.java
                )
        )

        creator = CodeCreator()
        ktClass.print(null,creator)

        assertThat(creator.outputString(),eq("class TestClass\n:Serializable\n{\n}"))



        ktClass = KtClass(
                packageName = "org.packageTest",
                className = "TestClass",
                constructor = KtConstructor()
        )

        creator = CodeCreator()
        ktClass.print(null,creator)

        assertThat(creator.outputString(),eq("class TestClass\nconstructor(\n)\n{\n}"))
    }

}