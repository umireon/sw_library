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

import cucumber.api.java.ja.かつ;
import cucumber.api.java.ja.ならば;
import cucumber.api.java.ja.もし;
import cucumber.api.java.ja.前提;
import info.umireon.sw_library.ControlMaterial;
import info.umireon.sw_library.EntityBook;
import info.umireon.sw_library.EntityLoan;
import info.umireon.sw_library.EntityMaterial;
import info.umireon.sw_library.EntityReservation;
import info.umireon.sw_library.EntityUser;
import info.umireon.sw_library.ReservedMaterialException;
import info.umireon.sw_library.UnavailableMaterialException;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author umireon
 */
public class ControlMaterialStepdefs {
    private ControlMaterial ctrlMaterial;
    private HashMap<String, EntityUser> users = new HashMap<>();

    public EntityUser getUser(String name) {
        EntityUser user = users.get(name);
        if (user == null) {
            user = new EntityUser(name);
            users.put(name, user);
        }
        return user;
    }
    
    @前提("^資料コントロールが初期化されている$")
    public void 資料コントロールが初期化されている() {
        ctrlMaterial = new ControlMaterial();
    }

    @かつ("^書籍 (.+) がある$")
    public void 書籍がある(String name) {
        ctrlMaterial.addMaterial(new EntityBook(name));
    }

    @かつ("^(.+) が (.+) に予約されている$")
    public void 書籍がユーザに予約されている(String matname, String username) {
        EntityUser user = getUser(username);
        EntityMaterial mat = ctrlMaterial.getMaterial(matname);
        mat.addReservation(new EntityReservation(user));
    }
    
    @もし("^(.+) が (.+) を貸出する$")
    public void ユーザが資料を貸出する(String username, String matname)
            throws Exception {
        EntityUser user = getUser(username);
        ctrlMaterial.lendMaterial(matname, user);
    }
    
    @ならば("^(.+) は (.+) に貸出中となる$")
    public void 資料はユーザに貸出中となる(String matname, String username)
            throws Exception {
        EntityMaterial mat = ctrlMaterial.getMaterial(matname);
        EntityLoan loan = (EntityLoan)mat.getStatus();
        assertEquals(loan.getBorrower().getName(), username);
    }
    
    @ならば("^(.+) は (.+) を利用不可能である$")
    public void ユーザは資料を利用不可能である(String username, String matname)
            throws Exception {
        EntityUser user = getUser(username);
        try {
            ctrlMaterial.lendMaterial(matname, user);
        } catch (UnavailableMaterialException e) {
            return;
        }
        fail();
    }
    
    @ならば("^(.+) は予約されていない$")
    public void 資料は予約されていない(String matname) throws Exception {
        EntityMaterial mat = ctrlMaterial.getMaterial(matname);
        assertFalse(mat.isReserved());
    }
    
    @ならば("^(.+) は (.+) を予約済みであるために貸出できない$")
    public void ユーザは資料を予約済みであるために貸出できない(String username, String matname)
            throws Exception {
        EntityUser user = getUser(username);
        try {
            ctrlMaterial.lendMaterial(matname, user);
        } catch (ReservedMaterialException e) {
            return;
        }
        fail();
    }
}
