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
class KtValueTest {

    @Test
    fun ktValueTest(){
        val ktValue = KtValue(
                annotations = arrayOf(
                        KtAnnotation(JvmField::class.java),
                        KtAnnotation(NotNull::class.java)
                ),
                name = "test",
                defaultValue = "123",
                clazz = Int::class.java,
                nullable = true
        )

        ktValue.annotations
        ktValue.name
        ktValue.defaultValue
        ktValue.clazz
        ktValue.nullable

        val creator = CodeCreator()
        ktValue.print(null,creator)
        assertThat(creator.outputString(),eq("@JvmField\n@NotNull\ntest:Int?=123"))
    }
}