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
 * 資料を管理するコントロールです.
 * @author Kaito Udagawa
 */
public class ControlMaterial {
    /**
     * 貸出可能日数です.
     */
    public static final int DUE_DAYS = 14;

    /**
     * 資料の一覧です.
     */
    private final Map<String, EntityMaterial> materials;

    /**
     * 空の資料管理を作成します.
     */
    public ControlMaterial() {
        materials = new HashMap<>();
    }

    /**
     * 資料一覧を取得します.
     * @return 資料一覧itirann
     */
    public final Collection<EntityMaterial> getMaterials() {
        return materials.values();
    }

    /**
     * 資料名から, 資料を取得します.
     * @param name 資料名
     * @return 利用者名に対応する利用者, 存在しない場合は <code>null</code>
     */
    public final EntityMaterial getMaterial(final String name) {
        return materials.get(name);
    }

    /**
     * 資料を追加します.
     * @param material 追加する資料
     */
    public final void addMaterial(final EntityMaterial material) {
        materials.put(material.getName(), material);
    }

    /**
     * 資料を貸出します.
     * @param materialName 資料名
     * @param borrower 貸出する利用者
     * @throws UnknownMaterialException 資料が存在しない場合
     * @throws UnavailableMaterialException 資料が利用可能ではない場合
     * @throws ReservedMaterialException 資料が予約されていて, 利用可能ではない場合
     */
    public final void lendMaterial(final String materialName,
            final EntityUser borrower) throws UnknownMaterialException,
            UnavailableMaterialException, ReservedMaterialException {
        EntityMaterial material;
        material = getMaterial(materialName);
        if (material == null) {
            throw new UnknownMaterialException();
        }

        if (material.getStatus() != null) {
            throw new UnavailableMaterialException();
        }

        if (material.isReserved()) {
            if (material.peekReservation().getReserver() == borrower) {
                material.pollReservation();
            } else {
                throw new ReservedMaterialException();
            }
        }

        EntityDate today, due;
        today = new EntityDate();
        due = today.addDays(DUE_DAYS);
        material.setStatus(new EntityLoan(borrower, due));
    }
}
