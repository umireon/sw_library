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
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author umireon
 */
public class SwLibraryMainSystemTest {
    private SwLibraryMain main;
    private PipedOutputStream in;
    private ByteArrayOutputStream out;

    @Before
    public void setup() throws IOException {
        in = new PipedOutputStream();
        out = new ByteArrayOutputStream();
        PipedInputStream stdin = new PipedInputStream(in);
        PrintStream stdout = new PrintStream(out);
        main = new SwLibraryMain(stdin, stdout);
    }

    @Test
    public void 貸出可能() throws IOException {
        PrintWriter writer = new PrintWriter(in, true);
        writer.println("user1");
        writer.println("book1");
        this.main.start(new String[0]);
        
        assertTrue(out.toString().contains("- book1 貸出中(user1; "));
    }
    
    @Test
    public void 貸出不可能() throws IOException {
        PrintWriter writer = new PrintWriter(in, true);
        writer.println("user1");
        writer.println("book2");
        this.main.start(new String[0]);
        
        assertTrue(out.toString().contains("指定された資料は貸出できません"));
    }
    
    @Test
    public void 予約中() throws IOException {
        PrintWriter writer = new PrintWriter(in, true);
        writer.println("user2");
        writer.println("mag1");
        this.main.start(new String[0]);
        
        assertTrue(out.toString().contains("指定された資料は予約されています"));
    }
    
    @Test
    public void 資料未登録() throws IOException {
        PrintWriter writer = new PrintWriter(in, true);
        writer.println("user1");
        writer.println("book3");
        this.main.start(new String[0]);
        
        assertTrue(out.toString().contains("指定された資料は存在しません"));
    }
    
    @Test
    public void 利用者未登録() throws IOException {
        PrintWriter writer = new PrintWriter(in, true);
        writer.println("user3");
        writer.println("book1");
        this.main.start(new String[0]);
        
        assertTrue(out.toString().contains("次のユーザが登録されました: user3"));
    }
}
