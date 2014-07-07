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

package info.umireon.sw_library.integral;

import info.umireon.sw_library.EntityBook;
import info.umireon.sw_library.EntityLoan;
import info.umireon.sw_library.EntityReservation;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author umireon
 */
public class EntityBookIntegralTest {
    private EntityBook book;
    
    @Before
    public void setup() {
        book = new EntityBook("book");
    }
    
    @Test
    public void statusInitiallyEmpty() {
        assertNull(book.getStatus());
    }
    
    @Test
    public void statusInitiallyNotReserved() {
        assertFalse(book.isReserved());
    }
    
    @Test
    public void setStatus() {
        EntityLoan loan = new EntityLoan(null, null);
        book.setStatus(loan);
        assertEquals(loan, book.getStatus());
    }
    
    @Test
    public void reservation() {
        EntityReservation reserve = new EntityReservation(null);
        book.addReservation(reserve);
        assertTrue(book.isReserved());
        assertEquals(book.peekReservation(), reserve);
        assertEquals(book.pollReservation(), reserve);
        assertFalse(book.isReserved());
    }
}
