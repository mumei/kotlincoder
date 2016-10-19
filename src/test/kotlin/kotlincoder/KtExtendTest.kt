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

import java.util.*
import org.junit.Assert.assertThat
import org.hamcrest.CoreMatchers.`is` as eq
import org.junit.Test

/**
 * @author yuuto
 */
class KtExtendTest {
    @Test
    fun ktExtendArgTest(){
        val ktExtend = KtExtend(
                clazz = ArrayList::class.java,
                argEnable = true,
                args= arrayOf("aaa","bbb")
        )

        ktExtend.clazz
        ktExtend.argEnable
        ktExtend.args

        val string = ktExtend.print()
        assertThat(string,eq("ArrayList(aaa, bbb)"))
    }
    @Test
    fun ktExtendNoArgTest(){
        val ktExtend = KtExtend(
                clazz = ArrayList::class.java,
                argEnable = false,
                args= arrayOf("aaa","bbb")
        )
        val string = ktExtend.print()
        assertThat(string,eq("ArrayList"))
    }



}