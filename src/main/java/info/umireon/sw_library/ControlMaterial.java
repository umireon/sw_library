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
 *
 * @author umireon
 */
public class ControlMaterial {
    public static final int DUE_DAYS = 14;

    public Map<String, EntityMaterial> materials;

    public ControlMaterial() {
        materials = new HashMap<>();
    }

    public Collection<EntityMaterial> getMaterials() {
        return materials.values();
    }

    public EntityMaterial getMaterial(String name) {
        return materials.get(name);
    }

    public void addMaterial(EntityMaterial material) {
        materials.put(material.getName(), material);
    }

    public void lendMaterial(String materialName, EntityUser borrower)
            throws UnknownMaterialException, UnavailableMaterialException,
            ReservedMaterialException {
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
