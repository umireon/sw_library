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

import info.umireon.sw_library.EntityDate;
import info.umireon.sw_library.EntityLoan;
import info.umireon.sw_library.EntityUser;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class EntityLoanIntegralTest {
    private EntityLoan loan;
    private EntityUser borrower;
    private EntityDate due;

    @Before
    public void setup() {
        borrower = new EntityUser("borrower");
        due = new EntityDate().addDays(14);
        loan = new EntityLoan(borrower, due);
    }

    @Test
    public void 貸出者を持っている() {
        assertEquals(borrower, loan.getBorrower());
    }

    @Test
    public void 貸出期限を持っている() {
        assertEquals(due, loan.getDue());
    }

    @Test
    public void 文字列に変換できる() {
        assertTrue(loan.toString() instanceof String);
    }
}
