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

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 資料を表すエンティティです.
 * @author Kaito Udagawa
 */
public abstract class EntityMaterial {
    /**
     * 資料の名前です.
     */
    private final String name;

    /**
     * 資料の現況です.
     */
    private EntityStatus status = null;

    /**
     * 資料の予約のキューです.
     */
    private final Queue<EntityReservation> reservations;

    /**
     * 資料を作成します.
     * @param materialName 資料の名前
     */
    public EntityMaterial(final String materialName) {
        if (materialName == null) {
            throw new IllegalArgumentException();
        }

        name = materialName;
        reservations = new ArrayDeque<>();
    }

    /**
     * 資料の名前を取得します.
     * @return 資料の名前
     */
    public final String getName() {
        return name;
    }

    /**
     * 資料の現況を取得します.
     * @return 資料の現況
     */
    public final EntityStatus getStatus() {
        return status;
    }

    /**
     * 資料の現況を設定します.
     * @param newStatus 新しく設定される資料の現況
     */
    public final void setStatus(final EntityStatus newStatus) {
        this.status = newStatus;
    }

    /**
     * 資料の予約をキューの最後に追加します.
     * @param newReservation 新しく追加される予約
     */
    public final void addReservation(final EntityReservation newReservation) {
        reservations.add(newReservation);
    }

    /**
     * 資料の予約のキューの先頭を取得します.
     * @return 資料の先頭の予約
     */
    public final EntityReservation peekReservation() {
        return reservations.peek();
    }

    /**
     * 資料の予約のキューの先頭を取得し，その予約を削除します.
     * @return 資料の先頭の予約
     */
    public final EntityReservation pollReservation() {
        return reservations.poll();
    }

    /**
     * 資料の予約が1件以上あれば，真を返します.
     * @return 資料の予約が1件以上あれば真，さもなくば偽
     */
    public final boolean isReserved() {
        return !reservations.isEmpty();
    }
}
