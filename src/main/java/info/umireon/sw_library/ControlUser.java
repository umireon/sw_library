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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 利用者を管理するコントロールです.
 * @author Kaito Udagawa
 */
public class ControlUser {
    /**
     * 利用者一覧です.
     */
    private final Map<String, EntityUser> users;

    /**
     * 空の利用者管理を作成します.
     */
    public ControlUser() {
        users = new HashMap<>();
    }

    /**
     * 利用者一覧を取得します.
     * @return 利用者一覧
     */
    public final Collection<EntityUser> getUsers() {
        return users.values();
    }

    /**
     * 利用者名から, 利用者を取得します.
     * @param name 利用者名
     * @return 利用者名に対応する利用者, 存在しない場合は <code>null</code>
     */
    public final EntityUser getUser(final String name) {
        return users.get(name);
    }

    /**
     * 利用者を追加します.
     * @param user 追加する利用者
     */
    public final void addUser(final EntityUser user) {
        users.put(user.getName(), user);
    }
}
