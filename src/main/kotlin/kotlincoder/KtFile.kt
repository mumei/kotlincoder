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

import java.io.File

class KtFile {
    val packageName:String
    val fileName:String

    val printObject:Array<PrintCode>


    constructor(ktClass: KtClass){
        printObject = arrayOf<PrintCode>(ktClass)
        packageName = ktClass.packageName
        fileName = "${ktClass.className}.kt"
    }

    constructor(ktInterface: KtInterface){
        printObject = arrayOf<PrintCode>(ktInterface)
        packageName = ktInterface.packageName
        fileName = "${ktInterface.className}.kt"
    }

    private fun createCodeLines(): CodeCreator {
        val codeCreator = CodeCreator()
        codeCreator.put("package $packageName")
        codeCreator.put("")
        printObject.forEach {
            it.print(null,codeCreator)
        }

        codeCreator.clazzSet.forEach {
            if(it.`package`.name != packageName)
                codeCreator.insert(1,"import ${it.name}")
        }
        return  codeCreator
    }

    fun print():String{
        val codeLines = createCodeLines()
        return codeLines.outputString()
    }

    fun outputFile(tmpDir: File):File{
        val dirArray = packageName.split(".")
        var dir = tmpDir
        dirArray.forEach {
            dir = File(dir,it)
            if(!dir.exists())dir.mkdir()
        }
        val targetFile = File(dir,fileName)
        val codeLines = createCodeLines()
        codeLines.outputFile(targetFile)
        return targetFile
    }


}