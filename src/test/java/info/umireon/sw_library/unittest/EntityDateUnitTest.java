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

package info.umireon.sw_library.unittest;

import info.umireon.sw_library.EntityDate;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class EntityDateUnitTest {
    @Test
    public void 作成時は今日を指す() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = format.format(cal.getTime());

        EntityDate date = new EntityDate();
        assertEquals(dateString, date.toString());
    }
    
    @Test
    public void 数日後の日付を作成できる() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 14);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = format.format(cal.getTime());

        EntityDate date = new EntityDate();
        date = date.addDays(14);
        assertEquals(dateString, date.toString());
    }
}
