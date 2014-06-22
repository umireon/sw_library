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

package info.umireon.sw_library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.Logger;

/**
 *
 * @author umireon
 */
public final class SwLibraryMain {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException 入出力エラー
     */
    public static void main(final String[] args) throws IOException {
        SwLibraryMain main;
        main = new SwLibraryMain(System.in, System.out);
        main.start(args);
    }

    /**
     * ロガー.
     */
    public static final Logger LOGGER = Logger.getGlobal();

    /**
     * 標準入力.
     */
    private final transient InputStream stdin;

    /**
     * 標準出力.
     */
    private final transient PrintStream stdout;

    /**
     * 標準入出力とともに，インスタンスを生成する.
     * @param sin 標準入力
     * @param sout 標準出力
     */
    public SwLibraryMain(final InputStream sin, final PrintStream sout) {
        this.stdin = sin;
        this.stdout = sout;
    }

    /**
     * アプリケーションを開始する.
     * @param args 引数
     * @throws IOException 標準入力が読めない
     */
    public void start(final String[] args) throws IOException {
        InputStreamReader isr;
        isr = new InputStreamReader(stdin);
        BufferedReader reader;
        reader = new BufferedReader(isr);

        String line;
        line = reader.readLine();
        stdout.println(line);
    }
}
