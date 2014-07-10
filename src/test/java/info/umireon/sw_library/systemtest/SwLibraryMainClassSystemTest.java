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

package info.umireon.sw_library.systemtest;

import info.umireon.sw_library.SwLibraryMain;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class SwLibraryMainClassSystemTest {
    private final PipedOutputStream in = new PipedOutputStream();
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private InputStream oldIn;
    private PrintStream oldOut;

    @Before
    public void setup() throws IOException {
        oldIn = System.in;
        oldOut = System.out;

        System.setIn(new PipedInputStream(in));
        System.setOut(new PrintStream(out));
    }
    
    @After
    public void tearDown() {
        System.setIn(oldIn);
        System.setOut(oldOut);
    }

    @Test
    public void 標準入力が閉じられている場合() throws IOException {
        in.close();
        try {
            SwLibraryMain.main(new String[0]);
        } catch (IllegalArgumentException e) {
            assertTrue(out.toString().contains("資料"));
            return;
        }
        fail();
    }

    @Test
    public void 成功する場合() throws IOException {
        PrintWriter writer = new PrintWriter(in, true);
        writer.println("user1");
        writer.println("book1");

        SwLibraryMain.main(new String[0]);
        assertTrue(out.toString().contains("資料"));
    }
}
