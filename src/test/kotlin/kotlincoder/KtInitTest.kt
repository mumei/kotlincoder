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
class KtInitTest {

    @Test
    fun ktInitTest(){
        val ktInit = KtInit(
                codeLine = arrayOf(
                        CodeLine("val %F=%S","value0","test0"),
                        CodeLine("val %F=%S","value1","test1")
                )
        )

        ktInit.codeLine

        val creator = CodeCreator()
        ktInit.print(null,creator)

        assertThat(creator.outputString(),eq("init{\n  val value0=\"test0\"\n  val value1=\"test1\"\n}"))

    }
}