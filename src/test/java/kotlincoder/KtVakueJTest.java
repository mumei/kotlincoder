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

/**
 * @author yuuto
 */
public class KtVakueJTest {

    @Test
    public void ktValueJTest(){
        KtValue ktValue = new KtValue(Integer.class,"test");
        ktValue = new KtValue(Integer.class,"test",null);
        ktValue = new KtValue(Integer.class,"test",null,false);
        ktValue = new KtValue(Integer.class,"test",null,false,new KtAnnotation[0]);
    }
}
