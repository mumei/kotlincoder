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
class KtFieldTest {

    @Test
    fun KtFieldTest(){
        var ktField = KtField(
                annotations = arrayOf(
                        KtAnnotation(JvmField::class.java),
                        KtAnnotation(NotNull::class.java)
                ),
                name = "test",
                clazz = Int::class.java,
                rewritable = true,
                nullable = true
        )

        ktField.annotations
        ktField.clazz
        ktField.initValue
        ktField.name
        ktField.nullable
        ktField.rewritable

        var creator = CodeCreator()
        ktField.print(null,creator)

        assertThat(creator.outputString(),eq("@JvmField\n@NotNull\nvar test:Int?"))


        ktField = KtField(
                name = "test",
                initValue = "123",
                clazz = Int::class.java,
                rewritable = false,
                nullable = false
        )

        creator = CodeCreator()
        ktField.print(null,creator)

        assertThat(creator.outputString(),eq("val test:Int = 123"))
    }

}