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

package info.umireon.sw_library.cucumber;

import cucumber.api.java.ja.前提;
import cucumber.api.java.ja.もし;
import cucumber.api.java.ja.ならば;
import info.umireon.sw_library.SwLibraryMain;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import org.junit.Assert;

public final class TestStepdefs {
    private SwLibraryMain main;
    private final PipedOutputStream in = new PipedOutputStream();
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();

    @前提("^初期化されている$")
    public void 初期化されている() throws IOException {
        PipedInputStream stdin = new PipedInputStream(in);
        PrintStream stdout = new PrintStream(out);
        this.main = new SwLibraryMain(stdin, stdout);
    }
    
    @もし("^(.*)と入力する$")
    public void と入力する(String input) throws IOException {
        PrintWriter writer = new PrintWriter(in, true);
        writer.println(input);
        this.main.start(new String[0]);
    }

    @ならば("^(.*)と出力される")
    public void と出力される(String output) {
        String line = out.toString().trim();
        Assert.assertEquals(output, line);
    }
}
