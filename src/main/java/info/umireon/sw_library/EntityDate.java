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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 日付を表すエンティティです.
 * @author Kaito Udagawa
 */
public class EntityDate {
    /**
     * 日時です.
     */
    private final Calendar cal;

    /**
     * 日時を今日として, 日付を作成します.
     */
    public EntityDate() {
        cal = Calendar.getInstance();
    }

    /**
     * 日時を指定して, 日付を作成します.
     * @param date 日時
     */
    private EntityDate(final Calendar date) {
        this.cal = date;
    }

    /**
     * 日時を指定された日数だけ進めた日付を作成します.
     * @param days 進める日数
     * @return 新しく作成された日付
     */
    public final EntityDate addDays(final int days) {
        Calendar newCal;
        newCal = (Calendar) cal.clone();
        newCal.add(Calendar.DAY_OF_MONTH, days);
        return new EntityDate(newCal);
    }

    /**
     * 日付の文字列表現を作成します.
     * @return 日付の文字列表現
     */
    @Override
    public final String toString() {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(cal.getTime());
    }
}
