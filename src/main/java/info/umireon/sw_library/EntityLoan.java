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

/**
 * 貸出を表すエンティティです.
 * @author Kaito Udagawa
 */
public class EntityLoan extends EntityStatus {
    /**
     * 貸出している利用者です.
     */
    private final EntityUser borrower;

    /**
     * 貸出の期限です.
     */
    private final EntityDate due;

    /**
     * 貸出を作成します.
     * @param borrowUser 貸出している利用者
     * @param loanDue 貸出の期限
     */
    public EntityLoan(final EntityUser borrowUser, final EntityDate loanDue) {
        borrower = borrowUser;
        due = loanDue;
    }

    /**
     * 貸出している利用者を取得します.
     * @return 貸出している利用者
     */
    public final EntityUser getBorrower() {
        return borrower;
    }

    /**
     * 貸出の期限を取得します.
     * @return 貸出の期限
     */
    public final EntityDate getDue() {
        return due;
    }

    /**
     * 貸出の文字列表現を作成します.
     * @return 貸出の文字列表現
     */
    @Override
    public final String toString() {
        StringBuilder str = new StringBuilder();
        str.append("貸出中(");
        str.append(borrower.getName());
        str.append("; ");
        str.append(due);
        str.append(")");
        return str.toString();
    }
}
