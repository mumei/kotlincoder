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
class KtAnnotationTest {

    @Test
    fun ktAnnotationTest(){
        var annotation = KtAnnotation(
                clazz = Override::class.java
        )
        var creator = CodeCreator()
        annotation.print(null,creator)



        assertThat(creator.outputString(),eq("@Override"))

        annotation = KtAnnotation(
                clazz = Override::class.java,
                values = arrayOf(
                        KtAnnotation.KtAnnotationValue("test","111"),
                        KtAnnotation.KtAnnotationValue("test2","222")
                )
        )

        annotation.clazz
        annotation.values.forEach {
            it.name
            it.value
        }

        creator = CodeCreator()
        annotation.print(null,creator)

        assertThat(creator.outputString(),eq("@Override(\n  test=\"111\",\n  test2=\"222\"\n)"))
    }
}
