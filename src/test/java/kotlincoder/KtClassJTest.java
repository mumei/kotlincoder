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

package kotlincoder;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author yuuto
 */
public class KtClassJTest {

    @Test
    public void ktClassInitTest(){
        KtClass ktClass = new KtClass("com.test","Test");
        ktClass = new KtClass("com.test","Test",new KtExtend(ArrayList.class));
        ktClass = new KtClass("com.test","Test",new KtExtend(ArrayList.class),new Class[0]);
        ktClass = new KtClass(
                "com.test",
                "Test",
                new KtExtend(ArrayList.class),
                new Class[0],
                new KtField[0]);
        ktClass = new KtClass(
                "com.test",
                "Test",
                new KtExtend(ArrayList.class),
                new Class[0],
                new KtField[0],
                null);
        ktClass = new KtClass(
                "com.test",
                "Test",
                new KtExtend(ArrayList.class),
                new Class[0],
                new KtField[0],
                null,
                null);
        ktClass = new KtClass(
                "com.test",
                "Test",
                new KtExtend(ArrayList.class),
                new Class[0],
                new KtField[0],
                null,
                null,
                new KtConstructor[0]);
        ktClass = new KtClass(
                "com.test",
                "Test",
                new KtExtend(ArrayList.class),
                new Class[0],
                new KtField[0],
                null,
                null,
                new KtConstructor[0],
                null);
        ktClass = new KtClass(
                "com.test",
                "Test",
                new KtExtend(ArrayList.class),
                new Class[0],
                new KtField[0],
                null,
                null,
                new KtConstructor[0],
                null,
                new KtFunction[0]);
        ktClass = new KtClass(
                "com.test",
                "Test",
                new KtExtend(ArrayList.class),
                new Class[0],
                new KtField[0],
                null,
                null,
                new KtConstructor[0],
                null,
                new KtFunction[0],
                new KtAnnotation[0]);
    }
}
