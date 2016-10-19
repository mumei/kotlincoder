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
import org.junit.Rule
import org.hamcrest.CoreMatchers.`is` as eq
import org.junit.Test
import org.junit.rules.ExpectedException
import sun.security.action.GetPropertyAction
import java.io.File
import java.security.AccessController

/**
 * @author yuuto
 */
open class CodeCreatorTest {

    companion object{
        val result = "\\ \"test\" % test2 % 123 % String %\\"

    }

    @JvmField
    @Rule
    var thrown = ExpectedException.none()

    @Test
    fun codeCreatorTest(){
        val creator = CodeCreator()
        creator.put("\\ %S \\% %F \\% %N \\% %C \\%\\","test","test2",123,String::class.java)
        assertThat(creator.getLastIndex(),eq(0))
        assertThat(creator.getCode(0),eq(result))
        val string = creator.outputString()
        assertThat(string,eq(result))

        val tmpdir = File(AccessController
                .doPrivileged(GetPropertyAction("java.io.tmpdir")))
        val dir = File(tmpdir,"CodeCreatorTest")

        if(dir.exists())dir.deleteRecursively()
        dir.mkdirs()
        val file = File(dir,"code_creator.txt")
        creator.outputFile(file)

        assertThat(file.readText(),eq("$result\n"))

        creator.modifyCode(0,"123")

        assertThat(creator.getLastIndex(),eq(0))
        assertThat(creator.outputString(),eq("123"))
        assertThat(creator.getCode(0),eq("123"))

        creator.insert(0,"aaaa")
        assertThat(creator.getLastIndex(),eq(1))
        assertThat(creator.outputString(),eq("aaaa\n123"))
        assertThat(creator.getCode(0),eq("aaaa"))
        assertThat(creator.getCode(1),eq("123"))
    }

    @Test
    fun encodeClassVoidTest(){
        val creator = CodeCreator()
        val void = javaClass.getMethod("encodeClassVoidTest").returnType
        creator.put("%C",void)
        assertThat(creator.outputString(),eq("Void"))
        assertThat(creator.clazzSet.size,eq(0))
    }

    @Test
    fun encodeClassJavaVoidTest(){
        val creator = CodeCreator()
        creator.put("%C",java.lang.Void::class.java)
        assertThat(creator.outputString(),eq("Void"))
        assertThat(creator.clazzSet.size,eq(1))
        val clazz:Class<*> = creator.clazzSet.iterator().next()
        assertThat(clazz.name,eq(java.lang.Void::class.java.name))
    }

    @Test
    fun encodeClassStringTest(){
        val creator = CodeCreator()
        creator.put("%C",String::class.java)
        assertThat(creator.outputString(),eq("String"))
        assertThat(creator.clazzSet.size,eq(0))
    }

    @Test
    fun encodeClassIntTest(){
        val creator = CodeCreator()
        creator.put("%C",123.javaClass)
        assertThat(creator.outputString(),eq("Int"))
        assertThat(creator.clazzSet.size,eq(0))
    }

    @Test
    fun encodeClassLongTest(){
        val creator = CodeCreator()
        creator.put("%C",123L.javaClass)
        assertThat(creator.outputString(),eq("Long"))
        assertThat(creator.clazzSet.size,eq(0))
    }

    @Test
    fun encodeClassFloatTest(){
        val creator = CodeCreator()
        creator.put("%C",123.0f.javaClass)
        assertThat(creator.outputString(),eq("Float"))
        assertThat(creator.clazzSet.size,eq(0))
    }

    @Test
    fun encodeClassDoubleTest(){
        val creator = CodeCreator()
        creator.put("%C",123.0.javaClass)
        assertThat(creator.outputString(),eq("Double"))
        assertThat(creator.clazzSet.size,eq(0))
    }

    @Test
    fun encodeClassIntegerTest(){
        val creator = CodeCreator()
        creator.put("%C",Integer::class.java)
        assertThat(creator.outputString(),eq("Integer"))
        assertThat(creator.clazzSet.size,eq(1))
    }

    @Test
    fun encodeNumberShortTest(){
        val creator = CodeCreator()
        creator.put("%N",1.toShort())
        assertThat(creator.outputString(),eq("1"))
    }

    @Test
    fun encodeNumberIntTest(){
        val creator = CodeCreator()
        creator.put("%N",123)
        assertThat(creator.outputString(),eq("123"))
    }

    @Test
    fun encodeNumberLongTest(){
        val creator = CodeCreator()
        creator.put("%N",123L)
        assertThat(creator.outputString(),eq("123"))
    }

    @Test
    fun encodeNumberFloatTest(){
        val creator = CodeCreator()
        creator.put("%N",123.0f)
        assertThat(creator.outputString(),eq("123.0"))
    }

    @Test
    fun encodeNumberDoubleTest(){
        val creator = CodeCreator()
        creator.put("%N",123.0)
        assertThat(creator.outputString(),eq("123.0"))
    }

    @Test
    fun codeCreatorErrorTest(){
        val creator = CodeCreator()
        thrown.expect(IllegalArgumentException::class.java)
        creator.put("%")
    }

    @Test
    fun codeCreatorObjectTypeClassButNumberTest(){
        val creator = CodeCreator()
        thrown.expect(IllegalArgumentException::class.java)
        creator.put("%C",123)
    }

    @Test
    fun codeCreatorObjectTypeClassButStringTest(){
        val creator = CodeCreator()
        thrown.expect(IllegalArgumentException::class.java)
        creator.put("%C","test")
    }

    @Test
    fun codeCreatorObjectTypeMatchNumberButClassTest(){
        val creator = CodeCreator()
        thrown.expect(IllegalArgumentException::class.java)
        creator.put("%N",Void::class.java)
    }

    @Test
    fun codeCreatorObjectTypeMatchNumberButStringTest(){
        val creator = CodeCreator()
        thrown.expect(IllegalArgumentException::class.java)
        creator.put("%N","test")
    }

    @Test
    fun codeCreatorObjectTypeFunctionButNumberTest(){
        val creator = CodeCreator()
        thrown.expect(IllegalArgumentException::class.java)
        creator.put("%F",123)
    }

    @Test
    fun codeCreatorObjectTypeFunctionButClassTest(){
        val creator = CodeCreator()
        thrown.expect(IllegalArgumentException::class.java)
        creator.put("%F",Void::class.java)
    }

    @Test
    fun codeCreatorObjectTypeStringButNumberTest(){
        val creator = CodeCreator()
        thrown.expect(IllegalArgumentException::class.java)
        creator.put("%S",123)
    }

    @Test
    fun codeCreatorObjectTypeStringButClassTest(){
        val creator = CodeCreator()
        thrown.expect(IllegalArgumentException::class.java)
        creator.put("%S",Void::class.java)
    }

    @Test
    fun codeCreatorObjectTypeNotFound(){
        val creator = CodeCreator()
        thrown.expect(IllegalArgumentException::class.java)
        creator.put("%R")
    }
}