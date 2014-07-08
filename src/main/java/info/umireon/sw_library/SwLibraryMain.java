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

import java.io.IOException;
import java.io.InputStream;
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
        EntityBook book1, book2;
        EntityMagazine mag1;
        EntityUser user1, user2;
        EntityReservation res1;

        user1 = new EntityUser("user1");
        user2 = new EntityUser("user2");

        res1 = new EntityReservation(user1);

        book1 = new EntityBook("book1");
        book2 = new EntityBook("book2");
        book2.setStatus(new EntityLoan(user2, new EntityDate()));
        mag1 = new EntityMagazine("mag1");
        mag1.addReservation(res1);

        ControlMaterial ctrlMaterial = new ControlMaterial();
        ctrlMaterial.addMaterial(book1);
        ctrlMaterial.addMaterial(book2);
        ctrlMaterial.addMaterial(mag1);
        ControlUser ctrlUser = new ControlUser();
        ctrlUser.addUser(user1);
        ctrlUser.addUser(user2);
        BoundaryLend boundLend = new BoundaryLend(ctrlMaterial, ctrlUser,
                stdin, stdout);

        boundLend.listen();
    }
}
