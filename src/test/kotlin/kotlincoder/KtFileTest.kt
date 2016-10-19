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
import sun.security.action.GetPropertyAction
import java.io.File
import java.io.Serializable
import java.security.AccessController
import kotlin.concurrent.fixedRateTimer

/**
 * @author yuuto
 */
class KtFileTest {
    @Test
    fun test(){
        var file = KtFile(
                ktClass = KtClass(
                        packageName = "org.packageTest",
                        className = "TestClass"
                )
        )
        file.fileName
        file.printObject
        file.packageName

        Assert.assertThat(file.print(), CoreMatchers.`is`("package org.packageTest\n\nclass TestClass{\n}"))


        val tmpdir = File(AccessController
                .doPrivileged(GetPropertyAction("java.io.tmpdir")))

        val dir = File(tmpdir,"CodeCreatorTest")
        if(dir.exists())dir.deleteRecursively()
        dir.mkdirs()

        file.outputFile(dir)
        file.outputFile(dir)

        file = KtFile(
                ktClass = KtClass(
                        packageName = "java.util",
                        className = "TestClass",
                        extendsClass = KtExtend(java.util.ArrayList::class.java),
                        interfaceClass = arrayOf(
                                Serializable::class.java
                        )

                )
        )

        Assert.assertThat(file.print(), CoreMatchers.`is`("package java.util\nimport java.io.Serializable\n\nclass TestClass\n:ArrayList, Serializable\n{\n}"))

    }

    @Test
    fun ktFileInterfaceTest(){
        val file = KtFile(
                ktInterface = KtInterface(
                        packageName = "com.test",
                        className = "Test"
                )
        )
        Assert.assertThat(file.print(), CoreMatchers.`is`("package com.test\n\ninterface Test{\n}"))

    }
}
