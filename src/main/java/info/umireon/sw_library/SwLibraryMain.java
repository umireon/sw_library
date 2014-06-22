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
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author umireon
 */
public class SwLibraryMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwLibraryMain main = new SwLibraryMain(System.in, System.out, System.err);
        main.start(args);
    }
    
    public Logger logger = Logger.getGlobal();

    public final InputStream stdin;
    public final PrintStream stdout;
    public final PrintStream stderr;

    public SwLibraryMain(InputStream stdin, PrintStream stdout, PrintStream stderr) {
        this.stdin = stdin;
        this.stdout = stdout;
        this.stderr = stderr;
    }
    
    public void start(String[] args) {
        InputStreamReader isr = new InputStreamReader(stdin);
        BufferedReader reader = new BufferedReader(isr);
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException", e);
            System.exit(1);
            return;
        }
        stdout.println(line);
    }
    
}
