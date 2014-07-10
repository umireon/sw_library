/*
 * The MIT License
 *
 * Copyright 2014 umireon.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package info.umireon.sw_library.integraltest;

import info.umireon.sw_library.ControlMaterial;
import info.umireon.sw_library.EntityBook;
import info.umireon.sw_library.EntityLoan;
import info.umireon.sw_library.EntityUser;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class ControlMaterialIntegralTest {
    private ControlMaterial ctrlMaterial;

    @Before
    public void setup() {
        ctrlMaterial = new ControlMaterial();
    }

    @Test
    public void 初期状態では資料を持っていない() {
        assertEquals(ctrlMaterial.getMaterials().size(), 0);
    }

    @Test
    public void 存在しない資料を取得しようとするとnullを返す() {
        assertNull(ctrlMaterial.getMaterial("nonexistent"));
    }
    
    @Test
    public void 資料を持っている() {
        EntityBook book = new EntityBook("book1");
        ctrlMaterial.addMaterial(book);
        assertEquals(ctrlMaterial.getMaterials().size(), 1);
        assertEquals(ctrlMaterial.getMaterial("book1"), book);
    }

    @Test
    public void 貸出を行える() throws Exception {
        EntityUser user = new EntityUser("user1");
        EntityBook book = new EntityBook("book1");
        ctrlMaterial.addMaterial(book);
        ctrlMaterial.lendMaterial("book1", user);
        assertTrue(book.getStatus() instanceof EntityLoan);
    }
}
